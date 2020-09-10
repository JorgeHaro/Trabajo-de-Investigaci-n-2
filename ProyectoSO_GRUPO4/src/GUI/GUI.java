package GUI;

import Modelo.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

public class GUI extends javax.swing.JFrame implements ActionListener{
    static int delay = 51;
    private boolean encendido = false;
    private boolean encendidoComparativo = false;
    
    private final Timer t = new Timer(delay, this);
    private final SO so = new SO();
    private final Usuario u = new Usuario(so);
    
    private final ArrayList<SO> listSO = new ArrayList<>();
    private final ArrayList<Proceso> listProcesos = new ArrayList<>();
    
    private boolean compLimpio = true;
    public Proceso procesoPanel;
    ArrayList<String> nombreProcesos=new ArrayList();
    
    private LinkedList<Proceso> ct= so.obtenerColaTotales();
    //Variable javaFX
    private final JFXPanel jfxPanel = new JFXPanel();    
    /**
     * Para almacenar los datos del grafico
     */
    private ObservableList<PieChart.Data> data2d = FXCollections.observableArrayList(); 

    private Modelo modelo;
    
    
    //Grafico de barras
    CategoryAxis xAxis = new CategoryAxis();
     NumberAxis yAxis = new NumberAxis();
     StackedBarChart<String, Number> sbc /*=
            new StackedBarChart<String, Number>(xAxis, yAxis)*/;
     /*XYChart.Series<String, Number> series1 =
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
            new XYChart.Series<String, Number>();*/
    
     //ArrayList<String> nombreProcesos=new ArrayList();
       //JFXPanel jfxPanel = new JFXPanel();  
    
    //
     
    public GUI() {
        
        initComponents();
        
        jPanel23.setLayout(new BorderLayout());
        //jPanel23.add(jfxPanel,BorderLayout.CENTER);
        jPanel23.add(jfxPanel,BorderLayout.CENTER);

        jbPlayStop.setBackground(Color.green);
        jPanel23.setSize(1180, 364);
            jfxPanel.setSize(1180, 364);
        
        setSize(1200, 1500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(new Point((screenSize.width - frameSize.width) / 2,
                              (screenSize.height - frameSize.height) / 2));
        jComboBox2.setEnabled(false);
        jComboBox2.setSelectedItem("No");
        jsQuantum.setEnabled(false);    
        
        jsCapMemoria.setEnabled(false);
    }
    
    /*public void carga(){
        sbc=so.pintadoBarra2();
        jPanel23.removeAll();
        jfxPanel.removeAll();
        jfxPanel.setScene(new Scene(sbc));
        jPanel23.add(jfxPanel);
        jPanel23.repaint();        
        //jPanel23.show(true);
        
        
        //});
        
    }*/
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(encendido){
            modelo=new Modelo(so.EnviarListaInterrupciones());
            jTable1.setModel(modelo);
            so.pintarTodos(jpTotalLista);
            so.pintadoBarra(jPanel23);
            so.pintarEjecutando(jpEjecutando);
            so.pintarListos(jpListos);
            JPanel [] ljp = {jpImpresora, jpDisco, jpMouse, jpTeclado, jpUsb,jpUsb3,jpUsb1,jpUsb2,jpUsb5};
            so.pintarBloqueados(ljp);
            so.pintarEstadisticas(jlTiempoUso, jlTiempoOcioso, jlTiempoEspProm, jlDuracion);
            so.pintarRAM(jpRam);
            jTextField1.setText(Integer.toString(Proceso.cantProcesos));
            validate();

        }
        
    }
    
    
    
    
    private void comenzarIndividual(){
        if(!encendido){
            so.comenzar();
            if (jcbActivarUsuario.isSelected()) {
                u.comenzar();
            }
            t.start();
            encendido = true;
            jbPlayStop.setText("Detener");
            jbPlayStop.setBackground(Color.pink);
        }else{
            t.stop();
            so.parar();
            u.parar();
            encendido = false;
            jbPlayStop.setText("Comenzar");
            jbPlayStop.setBackground(Color.green);
        }
    }
    
    
    
    private void clonarProcesos(){
        for (SO sot : listSO) {
            for (Proceso proct : listProcesos) {
                sot.crearNuevoProceso((Proceso)proct.clone());
            }
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jpCenter = new javax.swing.JPanel();
        jpSimulacion = new javax.swing.JPanel();
        jpTotalesMarco = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpTotalLista = new javax.swing.JPanel();
        jpContEjecList = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jpListos = new javax.swing.JPanel();
        jpEjecutando = new javax.swing.JPanel();
        jpBloqueadosEstadisticas = new javax.swing.JPanel();
        jpBloqueados = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jpDispositivos = new javax.swing.JPanel();
        jpImpresora = new javax.swing.JPanel();
        jpDisco = new javax.swing.JPanel();
        jpTeclado = new javax.swing.JPanel();
        jpMouse = new javax.swing.JPanel();
        jpUsb = new javax.swing.JPanel();
        jpUsb3 = new javax.swing.JPanel();
        jpUsb1 = new javax.swing.JPanel();
        jpUsb2 = new javax.swing.JPanel();
        jpUsb5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jpRam = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jlTiempoUso = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jlTiempoOcioso = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jlTiempoEspProm = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jlDuracion = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jpConfig = new javax.swing.JPanel();
        jpNuevoProceso = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfBurstTime = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jlErrorNuevoProceso = new javax.swing.JLabel();
        jpPlanificadore = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jsQuantum = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jsCapMemoria = new javax.swing.JSpinner();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jcbActivarUsuario = new javax.swing.JCheckBox();
        jpSur = new javax.swing.JPanel();
        jbPlayStop = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulación de Administración de Procesos | Sistemas Operativos");

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel12.setLayout(null);

        jpCenter.setLayout(null);

        jpSimulacion.setLayout(new java.awt.GridLayout(1, 0));

        jpTotalesMarco.setBorder(javax.swing.BorderFactory.createTitledBorder("Totales"));
        jpTotalesMarco.setLayout(new java.awt.BorderLayout());

        jpTotalLista.setLayout(new javax.swing.BoxLayout(jpTotalLista, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jpTotalLista);

        jpTotalesMarco.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jpSimulacion.add(jpTotalesMarco);

        jpContEjecList.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listos"));

        jpListos.setVerifyInputWhenFocusTarget(false);
        jpListos.setLayout(new javax.swing.BoxLayout(jpListos, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(jpListos);

        jpContEjecList.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jpEjecutando.setBorder(javax.swing.BorderFactory.createTitledBorder("Ejecutando"));
        jpContEjecList.add(jpEjecutando, java.awt.BorderLayout.PAGE_START);

        jpSimulacion.add(jpContEjecList);

        jpBloqueadosEstadisticas.setLayout(new javax.swing.BoxLayout(jpBloqueadosEstadisticas, javax.swing.BoxLayout.Y_AXIS));

        jpBloqueados.setBorder(javax.swing.BorderFactory.createTitledBorder("Bloqueados"));
        jpBloqueados.setLayout(new java.awt.BorderLayout());

        jpDispositivos.setLayout(new javax.swing.BoxLayout(jpDispositivos, javax.swing.BoxLayout.Y_AXIS));

        jpImpresora.setBorder(javax.swing.BorderFactory.createTitledBorder("Impresora"));
        jpImpresora.setLayout(new javax.swing.BoxLayout(jpImpresora, javax.swing.BoxLayout.Y_AXIS));
        jpDispositivos.add(jpImpresora);

        jpDisco.setBorder(javax.swing.BorderFactory.createTitledBorder("Disco"));
        jpDisco.setLayout(new javax.swing.BoxLayout(jpDisco, javax.swing.BoxLayout.Y_AXIS));
        jpDispositivos.add(jpDisco);

        jpTeclado.setBorder(javax.swing.BorderFactory.createTitledBorder("Teclado"));
        jpTeclado.setLayout(new javax.swing.BoxLayout(jpTeclado, javax.swing.BoxLayout.Y_AXIS));
        jpDispositivos.add(jpTeclado);

        jpMouse.setBorder(javax.swing.BorderFactory.createTitledBorder("Mouse"));
        jpMouse.setLayout(new javax.swing.BoxLayout(jpMouse, javax.swing.BoxLayout.Y_AXIS));
        jpDispositivos.add(jpMouse);

        jpUsb.setBorder(javax.swing.BorderFactory.createTitledBorder("USB"));
        jpUsb.setLayout(new javax.swing.BoxLayout(jpUsb, javax.swing.BoxLayout.Y_AXIS));
        jpDispositivos.add(jpUsb);

        jpUsb3.setBorder(javax.swing.BorderFactory.createTitledBorder("Timer"));
        jpUsb3.setLayout(new javax.swing.BoxLayout(jpUsb3, javax.swing.BoxLayout.Y_AXIS));
        jpDispositivos.add(jpUsb3);

        jpUsb1.setBorder(javax.swing.BorderFactory.createTitledBorder("Division"));
        jpUsb1.setLayout(new javax.swing.BoxLayout(jpUsb1, javax.swing.BoxLayout.Y_AXIS));
        jpDispositivos.add(jpUsb1);

        jpUsb2.setBorder(javax.swing.BorderFactory.createTitledBorder("Null"));
        jpUsb2.setLayout(new javax.swing.BoxLayout(jpUsb2, javax.swing.BoxLayout.Y_AXIS));
        jpDispositivos.add(jpUsb2);

        jpUsb5.setBorder(javax.swing.BorderFactory.createTitledBorder("Punto_flotante"));
        jpUsb5.setLayout(new javax.swing.BoxLayout(jpUsb5, javax.swing.BoxLayout.Y_AXIS));
        jpDispositivos.add(jpUsb5);

        jScrollPane3.setViewportView(jpDispositivos);

        jpBloqueados.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jpBloqueadosEstadisticas.add(jpBloqueados);

        jpSimulacion.add(jpBloqueadosEstadisticas);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jpRam.setBorder(javax.swing.BorderFactory.createTitledBorder("RAM "+SO.maxMemoria+"MB"));
        jpRam.setLayout(new javax.swing.BoxLayout(jpRam, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane6.setViewportView(jpRam);

        jPanel11.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jpSimulacion.add(jPanel11);

        jpCenter.add(jpSimulacion);
        jpSimulacion.setBounds(0, 39, 730, 370);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("CPU"));
        jPanel14.setPreferredSize(new java.awt.Dimension(70, 79));
        jPanel14.setLayout(new java.awt.GridLayout(4, 2));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Tiempo de uso: ");
        jPanel14.add(jLabel16);

        jlTiempoUso.setText("0");
        jPanel14.add(jlTiempoUso);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Tiempo ocioso: ");
        jPanel14.add(jLabel15);

        jlTiempoOcioso.setText("0");
        jPanel14.add(jlTiempoOcioso);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Tiempo espera prom.: ");
        jPanel14.add(jLabel25);

        jlTiempoEspProm.setText("0");
        jPanel14.add(jlTiempoEspProm);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Tiempo simulación: ");
        jPanel14.add(jLabel24);

        jlDuracion.setText("0");
        jPanel14.add(jlDuracion);

        jpCenter.add(jPanel14);
        jPanel14.setBounds(790, 60, 276, 110);

        jPanel3.setLayout(null);

        jLabel6.setText("ID");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(120, 20, 12, 16);

        jLabel7.setText("Estado");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(20, 100, 38, 16);

        jLabel9.setText("Burst Time ");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(190, 110, 70, 16);

        jLabel10.setText("Memoria (MB)");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(190, 140, 80, 16);
        jPanel3.add(jTextField2);
        jTextField2.setBounds(150, 20, 50, 22);
        jPanel3.add(jTextField3);
        jTextField3.setBounds(90, 100, 60, 22);
        jPanel3.add(jTextField5);
        jTextField5.setBounds(290, 100, 50, 22);

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField6);
        jTextField6.setBounds(290, 140, 50, 22);

        jButton2.setText("Consultar PCB");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(120, 60, 130, 25);
        jPanel3.add(jTextField4);
        jTextField4.setBounds(90, 140, 70, 22);
        jPanel3.add(jTextField7);
        jTextField7.setBounds(190, 180, 60, 22);

        jLabel8.setText("Progreso");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(20, 140, 51, 16);

        jLabel11.setText("Prioridad");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(110, 190, 60, 16);

        jpCenter.add(jPanel3);
        jPanel3.setBounds(750, 180, 350, 220);

        jPanel12.add(jpCenter);
        jpCenter.setBounds(20, 560, 1170, 420);

        jpConfig.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración"));
        jpConfig.setLayout(null);

        jpNuevoProceso.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo proceso:"));
        java.awt.GridBagLayout jpNuevoProcesoLayout = new java.awt.GridBagLayout();
        jpNuevoProcesoLayout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        jpNuevoProcesoLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0};
        jpNuevoProceso.setLayout(jpNuevoProcesoLayout);

        jLabel3.setText("Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jpNuevoProceso.add(jLabel3, gridBagConstraints);

        jTextField1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jpNuevoProceso.add(jTextField1, gridBagConstraints);

        jLabel4.setText("BurstTime:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jpNuevoProceso.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpNuevoProceso.add(jtfBurstTime, gridBagConstraints);

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 9;
        jpNuevoProceso.add(jButton1, gridBagConstraints);

        jlErrorNuevoProceso.setForeground(new java.awt.Color(255, 0, 0));
        jlErrorNuevoProceso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpNuevoProceso.add(jlErrorNuevoProceso, gridBagConstraints);

        jpConfig.add(jpNuevoProceso);
        jpNuevoProceso.setBounds(30, 30, 254, 94);

        jpPlanificadore.setBorder(javax.swing.BorderFactory.createTitledBorder("Planificador"));
        jpPlanificadore.setLayout(new java.awt.GridLayout(2, 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Política: ");
        jpPlanificadore.add(jLabel1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FCFS", "SJF", "RonRobin", "Por proriedades" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jpPlanificadore.add(jComboBox1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Apropiativo: ");
        jpPlanificadore.add(jLabel2);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));
        jComboBox2.setToolTipText("");
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jpPlanificadore.add(jComboBox2);

        jpConfig.add(jpPlanificadore);
        jpPlanificadore.setBounds(610, 50, 248, 69);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Otros"));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel5.setText("Quantum: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jLabel5, gridBagConstraints);

        jsQuantum.setModel(new javax.swing.SpinnerNumberModel(10, 1, 100, 1));
        jsQuantum.setPreferredSize(new java.awt.Dimension(60, 20));
        jsQuantum.setValue(10);
        jsQuantum.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsQuantumStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jsQuantum, gridBagConstraints);

        jLabel12.setText("Cap. Memoria: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jLabel12, gridBagConstraints);

        jsCapMemoria.setModel(new javax.swing.SpinnerNumberModel(1500, 150, null, 1));
        jsCapMemoria.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsCapMemoriaStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jsCapMemoria, gridBagConstraints);

        jLabel26.setText(" MB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jLabel26, gridBagConstraints);

        jLabel27.setText("Asig. de Memoria:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jLabel27, gridBagConstraints);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primero hueco", "Mejor hueco", "Peor hueco" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        jPanel1.add(jComboBox5, gridBagConstraints);

        jpConfig.add(jPanel1);
        jPanel1.setBounds(320, 40, 254, 89);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Simulación"));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jcbActivarUsuario.setSelected(true);
        jcbActivarUsuario.setText("Generar procesos aleatorios");
        jcbActivarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbActivarUsuarioActionPerformed(evt);
            }
        });
        jPanel10.add(jcbActivarUsuario);

        jpConfig.add(jPanel10);
        jPanel10.setBounds(910, 60, 254, 50);

        jPanel12.add(jpConfig);
        jpConfig.setBounds(0, 0, 1190, 170);

        jpSur.setLayout(new java.awt.BorderLayout());

        jbPlayStop.setText("Play");
        jbPlayStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPlayStopActionPerformed(evt);
            }
        });
        jpSur.add(jbPlayStop, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1180, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        jpSur.add(jPanel23, java.awt.BorderLayout.CENTER);

        jPanel12.add(jpSur);
        jpSur.setBounds(10, 180, 1180, 390);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel2);
        jPanel2.setBounds(40, 1030, 1090, 180);

        jTabbedPane1.addTab("Simulacion", jPanel12);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Proceso", "Tiempo arribo", "Tipo", "Dispositivo", "Estado"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jTabbedPane2.addTab("Reporte de Interrupciones", jScrollPane4);

        jTabbedPane1.addTab("Interrupciones", jTabbedPane2);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int tabSelected = jTabbedPane1.getSelectedIndex();
        if(tabSelected == 0 && encendidoComparativo)
            //comenzarComparativo();
        if(tabSelected == 1 && encendido)
            comenzarIndividual();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jcbActivarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbActivarUsuarioActionPerformed
        if(jcbActivarUsuario.isSelected()){
            u.comenzar();
        }else{
            u.parar();
        }
    }//GEN-LAST:event_jcbActivarUsuarioActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        so.setAsignacionMemoria(jComboBox5.getSelectedIndex());
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jsCapMemoriaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsCapMemoriaStateChanged
        t.stop();
        so.parar();
        int ncm = (int)jsCapMemoria.getValue();
        so.cambiarCapMemoria(ncm);
        jpRam.setBorder(BorderFactory.createTitledBorder("RAM "+ncm+"MB"));
        if(encendido){
            t.start();
            so.comenzar();
        }
    }//GEN-LAST:event_jsCapMemoriaStateChanged

    private void jsQuantumStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsQuantumStateChanged
        t.stop();
        so.parar();
        int nq = (int)jsQuantum.getValue();
        so.setQuantum(nq);
        if(encendido){
            t.start();
            so.comenzar();
        }
    }//GEN-LAST:event_jsQuantumStateChanged

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        so.setApropiativa(jComboBox2.getSelectedIndex()==0);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(jComboBox1.getSelectedItem()=="FCFS"){
            jComboBox2.setSelectedItem("No");
            jComboBox2.setEnabled(false);
        }
        
        if(jComboBox1.getSelectedItem()=="Por proriedades"){
            jComboBox2.setSelectedItem("Si");
            jComboBox2.setEnabled(false);
        }
        
        if(jComboBox1.getSelectedItem()=="SJF"){            
            jComboBox2.setEnabled(true);
        }
        
        if(jComboBox1.getSelectedItem()=="RonRobin"){
            jComboBox2.setSelectedItem("Si");
            jComboBox2.setEnabled(false);
            jsQuantum.setEnabled(true);
        }else{
            jsQuantum.setEnabled(false);
        }
        t.stop();
        so.parar();
        so.setPolitica(jComboBox1.getSelectedIndex());
        if(encendido){
            t.start();
            so.comenzar();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int bt = Integer.parseInt(jtfBurstTime.getText());
            if(so.crearProcesoPersonalizado(bt)){
                System.out.println("Se creo nuevo proceso personalizado");
                so.pintarTodos(jpTotalLista);
                so.pintarListos(jpListos);
                jTextField1.setText(Proceso.cantProcesos+"");
                validate();
            }else{
                System.out.println("No se pudo crear nuevo proceso");
                jlErrorNuevoProceso.setText("Nivel de multiprogramación: "+SO.maxCantProcesos);
            }
        } catch (NumberFormatException e) {
            jlErrorNuevoProceso.setText("Introducir un entero");
            System.out.println("Numero no valido");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbPlayStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPlayStopActionPerformed
        comenzarIndividual();
    }//GEN-LAST:event_jbPlayStopActionPerformed
    public String nombreEstado(int estado){
        String nombre="";
        switch (estado){
            case 0:
                nombre="Creado";
                break;
            case 1:
                nombre="Listo";
                break;
            case 2:
                nombre="Ejecucion";
                break;
            case 3:
                nombre="Bloqueado";
                break;
            case 4:
                nombre="Finalizado";
                break;       
            }
        return nombre;
        }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Proceso prox = so.consulta(Integer.parseInt(jTextField2.getText())-1);
        jTextField3.setText(nombreEstado(prox.getEstado()));
        jTextField4.setText(String.valueOf(prox.getProgreso()));
        jTextField7.setText(String.valueOf(prox.getPrioridad()));
        jTextField5.setText(String.valueOf(prox.getBurstTime()) );
        jTextField6.setText(String.valueOf(prox.getMemoria()) );
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    @Override
    public void setVisible(boolean bln) {
        super.setVisible(bln);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
            
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JButton jbPlayStop;
    private javax.swing.JCheckBox jcbActivarUsuario;
    private javax.swing.JLabel jlDuracion;
    private javax.swing.JLabel jlErrorNuevoProceso;
    private javax.swing.JLabel jlTiempoEspProm;
    private javax.swing.JLabel jlTiempoOcioso;
    private javax.swing.JLabel jlTiempoUso;
    private javax.swing.JPanel jpBloqueados;
    private javax.swing.JPanel jpBloqueadosEstadisticas;
    private javax.swing.JPanel jpCenter;
    private javax.swing.JPanel jpConfig;
    private javax.swing.JPanel jpContEjecList;
    private javax.swing.JPanel jpDisco;
    private javax.swing.JPanel jpDispositivos;
    private javax.swing.JPanel jpEjecutando;
    private javax.swing.JPanel jpImpresora;
    private javax.swing.JPanel jpListos;
    private javax.swing.JPanel jpMouse;
    private javax.swing.JPanel jpNuevoProceso;
    private javax.swing.JPanel jpPlanificadore;
    private javax.swing.JPanel jpRam;
    private javax.swing.JPanel jpSimulacion;
    private javax.swing.JPanel jpSur;
    private javax.swing.JPanel jpTeclado;
    private javax.swing.JPanel jpTotalLista;
    private javax.swing.JPanel jpTotalesMarco;
    private javax.swing.JPanel jpUsb;
    private javax.swing.JPanel jpUsb1;
    private javax.swing.JPanel jpUsb2;
    private javax.swing.JPanel jpUsb3;
    private javax.swing.JPanel jpUsb5;
    private javax.swing.JSpinner jsCapMemoria;
    private javax.swing.JSpinner jsQuantum;
    private javax.swing.JTextField jtfBurstTime;
    // End of variables declaration//GEN-END:variables
}
