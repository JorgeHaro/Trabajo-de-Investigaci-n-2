package GUI;

import Modelo.Proceso;
import java.awt.Color;
import java.awt.Dimension;

public final class EspacioRAMPanel extends javax.swing.JPanel {
    
    public EspacioRAMPanel() {
        initComponents();
    }
    
    public EspacioRAMPanel(Proceso p) {
        initComponents();
        jLabel1.setText("P"+(p.getID()+1)+" ("+p.getMemoria()
                +"MB)");
        setDirInicial(p.getMemoriaInicio());
        setDirFinal(p.getMemoriaInicio() + p.getMemoria());
        setColorFondo(Color.WHITE);
    }
    
    public static EspacioRAMPanel createHueco(int dirInicial, int dirFinal){
        EspacioRAMPanel e = new EspacioRAMPanel();
        e.setDirInicial(dirInicial);
        e.setDirFinal(dirFinal);
        e.jLabel1.setText("Hueco "+(dirFinal-dirInicial)+"MB");
        e.setColorFondo(Color.lightGray);
        return e;
    }
    
    public static EspacioRAMPanel createSO(int memoria){
        EspacioRAMPanel e = new EspacioRAMPanel();
        e.setDirInicial(0);
        e.setDirFinal(memoria);
        e.jLabel1.setText("Sistema Operativo "+memoria+"MB");
        e.setColorFondo(Color.orange);
        e.setPreferredSize(new Dimension(100, 100));
        return e;
    }
    
    //El espacio de memoria del SO
    public EspacioRAMPanel(String texto, int dirInicial, int dirFinal) {
        initComponents();
        jLabel1.setText(texto);
        setBackground(Color.green);
        jPanel1.setBackground(Color.green);
        setDirInicial(dirInicial);
        setDirFinal(dirFinal);
    }
    
    private void setColorFondo(Color c){
        setBackground(c);
        jPanel1.setBackground(c);
    }
    
    public void setDirInicial(int dirInicial){
        jlDirInicial.setText(Integer.toString(dirInicial));
    }
    
    public void setDirFinal(int dirFinal){
        jlDirFinal.setText(Integer.toString(dirFinal));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlDirFinal = new javax.swing.JLabel();
        jlDirInicial = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(new java.awt.BorderLayout());

        jlDirFinal.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        jlDirFinal.setText("finMemoria");
        add(jlDirFinal, java.awt.BorderLayout.SOUTH);

        jlDirInicial.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        jlDirInicial.setText("incioMemoria");
        add(jlDirInicial, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ID: ");
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlDirFinal;
    private javax.swing.JLabel jlDirInicial;
    // End of variables declaration//GEN-END:variables
}
