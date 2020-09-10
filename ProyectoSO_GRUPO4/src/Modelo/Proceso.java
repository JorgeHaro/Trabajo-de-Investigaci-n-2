package Modelo;

/**
 *
 * @author Kevin
 */
class PCB implements Cloneable{
    int id;
    int pc;
    int bursTime;
    int prioridad;
    int estado;
    int memoria;

    public PCB(int id) {
        this.id = id;
        genValoresAleatorios();
    }

    public PCB(int id, int bt) {
        this.id = id;
        bursTime = bt;
        estado = Proceso.CREADO;
        genMemoria();
    }

    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
        return o;
    }
    
    private void genValoresAleatorios(){
        bursTime = (int)(50+Math.random()*(Proceso.MAX_BT-40));
        prioridad = (int)(Math.random()*6);
        genMemoria();
    }
    
    private void genMemoria(){
        //memoria = bursTime;
        memoria = (int)(bursTime - 40 + 80 * Math.random());
    }
}

public class Proceso implements Cloneable{
    public static final int CREADO = 0, LISTO = 1, EJECUTANDO = 2, BLOQUEADO = 3, FINALIZADO = 4;
    static final int MAX_BT = 100;
    public static int cantProcesos;
    int disp;
    private boolean error = false;
    private int pcError = 0;
    private long tCreado;
    private long tFinalizado;
    private int memoriaInicio;
    
    private PCB pcb;

    public long gettCreado() {
        return tCreado;
    }

    public int getDisp() {
        return disp;
    }
    
    

    public Proceso() {
        pcb = new PCB(cantProcesos);
        genError();
        cantProcesos++;
        tCreado = System.currentTimeMillis();
    }

    public Proceso(int bt) {
        pcb = new PCB(cantProcesos, bt);
        genError();
        cantProcesos++;
        tCreado = System.currentTimeMillis();
    }

    public PCB getPcb() {
        return pcb;
    }

    public void setPcb(PCB pcb) {
        this.pcb = pcb;
    }
    
    //Métodos privados
    private void genError(){
        double r = Math.random();
        if(r<0.001){
            error = true;
            pcError = (int)(1+pcb.bursTime*Math.random());
        }
    }

    //Interfaz
    @Override
    public Object clone(){
        Proceso p = null;
        try {
            p = (Proceso)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.out);
        }
        pcb = (PCB)pcb.clone();
        return p;
    }
    
    public boolean ejecutarSiguiente(){
        if(pcb.pc<pcb.bursTime){
            if(error && pcb.pc == pcError){
                tFinalizado = System.currentTimeMillis();
                pcb.estado = FINALIZADO;
                return true;
            }
            if(pcb.estado == EJECUTANDO){
                pcb.pc++;
                return true;
            }
            return false;
        }else{
            if(pcb.estado!=FINALIZADO){
                tFinalizado = System.currentTimeMillis();
                pcb.estado = FINALIZADO;
                return true;
            }
            return false;
        }
    }
    
    //G y S
    public int getMemoria(){
        return pcb.memoria;
    }
    
    public long getTiempoEspera(){
        return (tFinalizado-tCreado);
    }
    
    public int getID(){
        return pcb.id;
    }
    
    public int getEstado(){
        return pcb.estado;
    }
    
    public int getProgreso(){
        if(pcb.pc<pcb.bursTime)
            return (int)(100*pcb.pc/pcb.bursTime);
        return 100;
    }
    
    public double getTamanio(){
        return (1.0*pcb.bursTime/MAX_BT);
    }
    
    public int getPC(){
        return pcb.pc;
    }
    
    public int getPrioridad(){
        return pcb.prioridad;
    }
    
    public int getBurstTime(){
        return pcb.bursTime;
    }
    
    public int getRestante(){
        return (pcb.bursTime - pcb.pc);
    }

    public void setMemoriaInicio(int memoriaInicio) {
        this.memoriaInicio = memoriaInicio;
    }
    
    public int getMemoriaInicio() {
        return memoriaInicio;
    }
    
    public boolean setEstado(int estado){
        if(estado<5){
            pcb.estado = estado;
            return true;
        }
        return false;
    }
    
    public boolean isError(){
        return error;
    }
}
