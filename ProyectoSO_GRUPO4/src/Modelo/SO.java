package Modelo;

import GUI.ProcesoPanel;
import GUI.EspacioRAMPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Kevin
 */
public class SO implements Arrancable{
    public static int maxMemoria = 1500;
    public static int maxCantProcesos = 20;//nivel de multiprogramacion (n procesos como maximo en memoria)
    
    CPU cpu;
    Scheduler scdl;
    RAM ram;
    
    long tiempoInicio;
    long duracion;

    public SO() {
        cpu = new CPU();
        scdl = new Scheduler();
        ram = new RAM(maxMemoria);
    }

    public SO(int politca, boolean apropiativa, int asinacionMemoria) {
        cpu = new CPU();
        scdl = new Scheduler(politca, apropiativa);
        ram = new RAM(maxMemoria);
        ram.setPolitica(asinacionMemoria);
    }
    
    @Override
    public void comenzar(){
        cpu.comenzar();
        scdl.comenzar();
        tiempoInicio = System.currentTimeMillis();
        System.out.println("SO.comenzar()");
    }
    
    @Override
    public void parar(){
        scdl.parar();
        cpu.parar();
        System.out.println("SO.parar()");
    }

    @Override
    public void setDelay(int delay) {
        scdl.setDelay(delay);
        cpu.setDelay(delay);
    }

    @Override
    public int getDelay() {
        return cpu.getDelay();
    }
    
    public void setPolitica(int p){
        scdl.setPolitica(p);
    }
    
    public int getPolitica(){
        return scdl.getPolitica();
    }
    
    public int getTipoAsignacionMemoria(){
        return ram.getPolitica();
    }
    
    public void setApropiativa(boolean a){
        scdl.apropiativa = a;
    }
    
    public boolean isApropiativa(){
        return scdl.apropiativa;
    }
    
    public void setQuantum(int q){
        scdl.quantum = q;
    }
    
    public void cambiarDelay(int delay){
        cpu.setDelay(delay);
        scdl.setDelay(delay);
    }
    
    public void cambiarCapMemoria(int m){
        maxMemoria = m;
        ram.setCapTotal(m);
    }
    
    public boolean crearNuevoProceso(Proceso p){
        return scdl.agregarNuevo(p);
    }
    
    public void insertarNProcesos(int n){
        for (int i = 0; i < n; i++) {
            crearNuevoProceso(new Proceso());
        }
    }
    
    public boolean crearProcesoPersonalizado(int bursTime){
        return scdl.agregarNuevo(new Proceso(bursTime));
    }
    
    public void setAsignacionMemoria(int i){
        ram.setPolitica(i);
    }

    //**********Scheduler*******************//
    class Scheduler implements ActionListener, Arrancable{
        static final int FCFS = 0, SJF = 1, RONROBIN = 2, POR_PRIORIDADES = 3;
        int quantum = 10;
        private final Timer t = new Timer(51, this);

        private final ColaTotales ct = new ColaTotales();
        private final ColaES ces = new ColaES();
        private final ColaListo cl = new ColaListo();
        
        private int politica = FCFS;
        private boolean apropiativa = true;
        private int quantumRest = quantum;

        Dispatcher dsptc = new Dispatcher();
        
        ArrayList <Interrup> ListaInterrupciones = new ArrayList<>(); 

        public Scheduler() {
        }
        
        public Scheduler(int politica, boolean apropiativa){
            this.politica = politica;
            this.apropiativa = apropiativa;
        }
        
        @Override
        public void comenzar(){
            t.start();
        }

        @Override
        public void parar(){
            t.stop();
        }

        @Override
        public void setDelay(int d){
            t.setDelay(d);
        }

        @Override
        public int getDelay() {
            return t.getDelay();
        }
        
        public void setPolitica(int p){
            if(p>-1 && p<4)
                politica = p;
        }
        
        public float getTiempoEsperaProm(){
            int n = 0;
            float tProm = 0;
            long tTotal = 0;
            for (Proceso p : ct) {
                if(p.getEstado()==Proceso.FINALIZADO){
                    n++;
                    tTotal += p.getTiempoEspera();
                }
            }
            if(n>0){
                tProm = (tTotal)/n;
            }
            return tProm;
        }
        
        public long getTiempoFinal(){
            if(ct.size()>0 && ct.getProcesosVivos()==0 && duracion<1){
                duracion = System.currentTimeMillis() - tiempoInicio;
                String estadist = ct.size()+"\t"+getTiempoEsperaProm()+"\t"+duracion+"\n";
                //Recursos.RecolectorEstadistico.escribirEn(estadist, politica);
            }
            return duracion;
        }
        
        public int getPolitica(){
            return politica;
        }
        
        public ColaTotales getColaTotales(){
            return ct;
        }

        public ColaES getColaES(){
            return ces;
        }

        public ColaListo getColaListos(){
            return cl;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            ordenarListos();
            planificar();
            dsptc.despachar();
            agregarSigListo();
        }

        //Largo plazo
        public boolean agregarNuevo(Proceso p){
            if(ct.getProcesosVivos()<maxCantProcesos){
                ct.addLast(p);
                return true;
            }
            return false;
        }

        //Mediano plazo
        public boolean agregarListo(Proceso p){
            if(ram.agregarProceso(p)){
                cl.addLast(p);
                return true;
            }
            return false;
        }

        public boolean agregarSigListo(){
            ArrayList<Proceso> nuevos = new ArrayList<>();
            for (Proceso proc : ct) {
                if(proc.getEstado()==Proceso.CREADO){
                    nuevos.add(proc);
                }
            }
            if(politica!=RONROBIN)
                nuevos.sort(Comparadores.getComparador(politica));
            if(nuevos.size()>0)
                return agregarListo(nuevos.get(0));
            return false;
        }

        //Corto plazo
        Proceso pSiguiente = null;

        private void ordenarListos(){
            if(politica!=RONROBIN)
                cl.sort(Comparadores.getComparador(politica));
        }
        
        private void planificarNoApropiativa(){
            Proceso act = cpu.getActual();
            pSiguiente = null;
            switch (politica){
                case FCFS:
                    if(act==null && cl.size()>0){
                        Proceso pTemp = cl.getFirst();
                        if(pTemp.getEstado()!=Proceso.FINALIZADO)
                            pSiguiente = pTemp;
                        break;
                    }
              
                    break;
                case SJF:
                    if(act!=null && act.getEstado()!=Proceso.FINALIZADO)
                        break;
                    int grand = 1000;
                    Proceso pt = null;
                    for (Proceso proc : ct) {
                        if(proc.getRestante()<grand && proc.getEstado()!=Proceso.FINALIZADO&&proc.getEstado()!=Proceso.BLOQUEADO){
                            grand = proc.getRestante();
                            pt = proc;
                        }
                    }
                    if(pt!=null)
                        pSiguiente = pt;
                    break;
                case RONROBIN:
                    if(quantumRest<1){
                        if(cl.size()>0)
                            pSiguiente = cl.getFirst();
                      
                        quantumRest = quantum;
                    }else{
                        if(act!=null && act.getEstado()!=Proceso.FINALIZADO){
                            if(act.getEstado()!=Proceso.BLOQUEADO)
                                quantumRest--;
                            break;
                        }
                        if(cl.size()>0)
                            pSiguiente = cl.getFirst();
                
                    }
                    break;
                case POR_PRIORIDADES:
                    if(act!=null && act.getEstado()!=Proceso.FINALIZADO)
                        break;
                    int priori = 1000;
                    Proceso pt2 = null;
                    for (Proceso proc : ct) {
                        if(proc.getPrioridad()<priori && proc.getEstado()!=Proceso.FINALIZADO&&proc.getEstado()!=Proceso.BLOQUEADO){
                            priori = proc.getPrioridad();
                            pt2 = proc;
                        }
                    }
                    if(pt2!=null)
                        pSiguiente = pt2;
                    break;
            }
        }
        
        private void planificarApropiativa(){
            pSiguiente = null;
            Proceso act = cpu.getActual();
            if(act==null && cl.size()>0){
                pSiguiente = cl.getFirst();
            }
            if(politica==RONROBIN){
                quantumRest--;
                if(quantumRest<0 && cl.size()>0){
                    pSiguiente = cl.getFirst();
                }
            }
        }
        
        private void planificar(){
            if(apropiativa)
                planificarApropiativa();
            else
                planificarNoApropiativa();
        }

        class Dispatcher {
            void despachar(){
                if(pSiguiente!=null){
                    Proceso pt = cpu.getActual();
                    cpu.setActual(pSiguiente);
                    cl.remove(pSiguiente);
                    if(pt != null){
                        switch (pt.getEstado()){
                            case Proceso.EJECUTANDO:
                                cl.addLast(pt);
                                break;
                            case Proceso.BLOQUEADO:
                                ces.addLast(pt);
                                break;
                            case Proceso.FINALIZADO:
                                cl.remove(pt);
                                break;
                            default:
                                System.out.println("default!");
                        }
                    }
                    quantumRest = quantum;
                }
            }
        }
        
        
        
       
        
        public void gestionarInterrupcion(int tipo){
            switch(tipo){
                case Interrupciones.REQ_IO:
                    Proceso pa = cpu.getActual();
                    if(pa!=null){
                        cpu.setActual(null);
                        if (!ces.contains(pa)){
                        ces.addLast(pa);
                        ListaInterrupciones.add(new Interrup(pa.getID()+1,cpu.tiempoCorriendo,pa.getDisp()));
                            System.out.println(pa.getID()+1+" "+cpu.tiempoCorriendo+" "+pa.getDisp());
                        }                            
                    }
                    actionPerformed(new ActionEvent(this, tipo, "reqIO"));
                    break;
                case Interrupciones.FIN_REQ_IO:
                    Proceso pt = null;                   
                        if(ces.size()>0)
                            pt = ces.getFirst();
                        if(pt!=null){
                            ces.remove(pt);
                            cl.addLast(pt);
                        }                    
                    actionPerformed(new ActionEvent(this, tipo, "reqIO"));
                    break;
            }
        }
    }
    
    //**************CPU************************//
    class CPU implements ActionListener, Arrancable{
        Proceso pActual;
        Timer t = new Timer(51, this);
        Interrupciones itr = new Interrupciones();

        //Datos
        long tiempoUso;
        long tiempoCorriendo;
        long tiempoOcioso;
        long tCreacion;
        long tFinal;

        public CPU() {
            itr = new Interrupciones();
            tCreacion = System.currentTimeMillis();
        }
        
        @Override
        public void comenzar(){
            t.start();
        }

        @Override
        public void parar(){
            t.stop();
        }

        @Override
        public void setDelay(int d){
            t.setDelay(d);
        }

        @Override
        public int getDelay() {
            return t.getDelay();
        }
        
        public void comunicarInterrupciones(){
            int inter1 = itr.generarPosibleInterrupcionIO();
            int inter2 = itr.generarPosibleInterrupcionFinIO();
            
            if(inter1 > 0)
                scdl.gestionarInterrupcion(Interrupciones.REQ_IO);
            if(inter2 > 0)
                scdl.gestionarInterrupcion(Interrupciones.FIN_REQ_IO);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            tiempoCorriendo++;
            if(pActual!=null){
                if(pActual.getEstado()!=Proceso.FINALIZADO && pActual.ejecutarSiguiente()){
                    tiempoUso++;
                }else{
                    tiempoOcioso++;
                }
                if(pActual.getEstado()==Proceso.FINALIZADO){
                    ram.quitarProceso(pActual);
                    pActual = null;
                }
                    
            }else{
                tiempoOcioso++;
            }
            comunicarInterrupciones();
        }

        public void setActual(Proceso p){
            pActual = p;
            if(p!=null && p.getEstado()!=Proceso.BLOQUEADO 
                    && p.getEstado()!=Proceso.FINALIZADO)
                p.setEstado(Proceso.EJECUTANDO);
        }

        public Proceso getActual(){
            return pActual;
        }

    }
    
    //Pintado
    public void pintarTodos(JPanel jp){
        
        jp.removeAll();
        for (int i = 0; i < scdl.getColaTotales().size(); i++) {
            jp.add(new ProcesoPanel(scdl.getColaTotales().get(scdl.getColaTotales().size()-1-i)));
        }
        jp.repaint();
    }
    
   /* class Interrup{
        Proceso proceso;
        long arribo=0;  

        public Interrup(Proceso proceso, long arribo) {
            this.proceso = proceso;
            this.arribo = arribo;
        }       
        
    }*/
    
    public ArrayList<Interrup> EnviarListaInterrupciones () {
            return scdl.ListaInterrupciones;
        }
    
    //GRAFICO
    public void pintadoBarra(JPanel jp){        
        
        CategoryAxis xAxis = new CategoryAxis();
     NumberAxis yAxis = new NumberAxis();
     StackedBarChart<String, Number> sbc =
            new StackedBarChart<String, Number>(xAxis, yAxis);
     XYChart.Series<String, Number> series1 =
            new XYChart.Series<String, Number>();
     XYChart.Series<String, Number> series2 =
            new XYChart.Series<String, Number>();
     XYChart.Series<String, Number> series3 =
            new XYChart.Series<String, Number>();
     XYChart.Series<String, Number> series4 =
            new XYChart.Series<String, Number>();
     XYChart.Series<String, Number> series5 =
            new XYChart.Series<String, Number>();
     XYChart.Series<String, Number> series6 =
            new XYChart.Series<String, Number>();
     XYChart.Series<String, Number> series7 =
            new XYChart.Series<String, Number>();
     XYChart.Series<String, Number> series8 =
            new XYChart.Series<String, Number>();
    
     ArrayList<String> nombreProcesos=new ArrayList();
       JFXPanel jfxPanel = new JFXPanel();  
     jfxPanel.setSize(1180, 364);
       //jp.removeAll();
        //jp.show(false);
     //jp.repaint();
       
        Platform.runLater(() -> { 
        sbc.setTitle("Procesos Totales");
        xAxis.setLabel("Procesos");
        sbc.setAnimated(false);   
     
       for(int i=0;i<scdl.ct.size();i++){
            nombreProcesos.add("PCB "+(i+1));
        }
        
        xAxis.setCategories(FXCollections.<String>observableArrayList(nombreProcesos));
        yAxis.setLabel("Burst time");

        series3.setName("Listo");
        series4.setName("Ejecucion");
        series5.setName("Finalizado");
        series6.setName("Bloqueado");
        series7.setName("Error");
        series8.setName("Nuevo");

        for(int i=0;i<scdl.ct.size();i++) {
            series3.getData().add(new XYChart.Data<String, Number>(nombreProcesos.get(i), 0));
            series4.getData().add(new XYChart.Data<String, Number>(nombreProcesos.get(i), 0));
            series5.getData().add(new XYChart.Data<String, Number>(nombreProcesos.get(i), 0));
            series6.getData().add(new XYChart.Data<String, Number>(nombreProcesos.get(i), 0));
            series7.getData().add(new XYChart.Data<String, Number>(nombreProcesos.get(i), 0));
            series8.getData().add(new XYChart.Data<String, Number>(nombreProcesos.get(i), 0));
            
        }
           
        for(int i=0;i<nombreProcesos.size();i++) {
            Proceso proc = scdl.ct.get(i);
            switch(proc.getEstado()){
                case Proceso.EJECUTANDO://serie4
                    series4.getData().get(i).setYValue(proc.getPC());
                    break;
                case Proceso.LISTO://serie3
                    series3.getData().get(i).setYValue(proc.getPC());
                    break;
                case Proceso.BLOQUEADO://serie6
                    series6.getData().get(i).setYValue(proc.getPC());
                    break;
                case Proceso.FINALIZADO://serie5
                    series5.getData().get(i).setYValue(proc.getPC());
                    if(proc.isError()){
                        series5.getData().get(i).setYValue(0);
                        series7.getData().get(i).setYValue(proc.getPC());}//serie7
                    break;
            }
            series8.getData().get(i).setYValue(proc.getRestante());//serie8
        }
        sbc.getData().addAll(series1, series2, series3,series4,series5, series6,series7,series8);
        jp.removeAll();
        jfxPanel.setScene(new Scene(sbc,1180,364));
        
        jp.setSize(1180, 364);
        jp.add(jfxPanel,BorderLayout.CENTER);
        jp.repaint();
        
        //jp.show(true);
        });
        
        
        //return jfxPanel;
    }
    
     
     
    //FIN GRAFICO
     
    
     
     
    public void pintarEjecutando(JPanel jp){
        jp.removeAll();
        if(cpu.getActual()!=null){
            jp.add(new ProcesoPanel(cpu.getActual()));
        }
        jp.repaint();
    }
    
    public void pintarListos(JPanel jp){
        jp.removeAll();
        if(scdl.getColaListos().size()>0){
            for (Proceso procListo : scdl.getColaListos()) {
                jp.add(new ProcesoPanel(procListo));
            }
        }
        jp.repaint();
    }
    
    public void pintarEstadisticas(JLabel tUso, JLabel tOcio,
            JLabel tEsperaProm, JLabel tDuracion){
        tUso.setText(Long.toString(cpu.tiempoUso));
        tOcio.setText(Long.toString(cpu.tiempoOcioso));
        tEsperaProm.setText(" "+scdl.getTiempoEsperaProm());
        tDuracion.setText(Long.toString(scdl.getTiempoFinal()));
    }

    public void pintarBloqueados(JPanel[] jp){
        if(jp.length>0){
            for (JPanel jPanel : jp) {
                jPanel.removeAll();
            }
            for (Proceso proc : scdl.getColaES()) {
                jp[proc.disp].add(new ProcesoPanel(proc));
            }
        }
    }
    
    public Proceso consulta(int id){
        
        return  scdl.getColaTotales().get(id);
    }
    
    public void pintarRAM(JPanel jp){
        jp.removeAll();
        for (Object o : ram) {
            if(o instanceof MemoriaSO){
                jp.add(EspacioRAMPanel.createSO(((MemoriaSO) o).getCapacidad()));
            }else if(o instanceof Hueco){
                Hueco h = (Hueco)o;
                jp.add(EspacioRAMPanel.createHueco(h.getDirInicio(), 
                        h.getDirFin()));
            }else if(o instanceof Proceso){
                Proceso p = (Proceso)o;
                jp.add(new EspacioRAMPanel(p));
            }
        }
    }
    
    public LinkedList<Proceso> obtenerColaTotales(){
        return scdl.getColaTotales();
    }
}
