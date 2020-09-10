package GUI;
import GUI.GUI.*;
import Modelo.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
/**
 *
 * @author PCB
 */
public class ProcesoPanel extends javax.swing.JPanel {
    static final int MAX_ANCHO = 160;
    static final Color EJECUTANDO = Color.cyan, LISTO = Color.green, 
            BLOQUEADO = Color.MAGENTA, FINALIZADO = Color.BLUE, ERROR = Color.red;
    
    public Proceso proc;
    public ProcesoPanel(Proceso p) {
        initComponents();
        proc = p;
        
        jLabel1.setText("ID: "+(p.getID()+1)+", Prior.: "+p.getPrioridad()+", Restante: "+p.getRestante());
        /*jProgressBar1.setValue(p.getProgreso());
        jProgressBar1.setPreferredSize(new Dimension((int)(MAX_ANCHO*p.getTamanio()), 14));*/
    }

    public Proceso getProc() {
        return proc;
    }

    public void setProc(Proceso proc) {
        this.proc = proc;
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        //jProgressBar1.setValue(proc.getProgreso());
        setToolTipText("PC: "+proc.getPC()+" Burst Time: "
                +proc.getBurstTime()
                +" Memoria: "+proc.getMemoria()
                +" Prioridad: "+proc.getPrioridad()
                +" Estado: "+proc.getEstado());
        switch(proc.getEstado()){
            case Proceso.EJECUTANDO:
                jPanel2.setBackground(EJECUTANDO);
                break;
            case Proceso.LISTO:
                jPanel2.setBackground(LISTO);
                break;
            case Proceso.BLOQUEADO:
                jPanel2.setBackground(BLOQUEADO);
                break;
            case Proceso.FINALIZADO:
                jPanel2.setBackground(FINALIZADO);
                if(proc.isError())
                    jPanel2.setBackground(ERROR);
                break;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setToolTipText("a");
        setMinimumSize(new java.awt.Dimension(41, 22));
        setLayout(new java.awt.BorderLayout());

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel1.setText(":id:");
        jPanel2.add(jLabel1);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        GUI gui = new GUI();
        gui.procesoPanel = proc;
    }//GEN-LAST:event_jPanel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
