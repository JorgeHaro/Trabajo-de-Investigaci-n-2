package Recursos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Kevin
 */
public class RecolectorEstadistico {
    public static final int FCFS = 0, SJF = 1, RR = 2, PRIORI = 3;
    static final File DIREC_FCFS = new File("src/Recursos/datosFCFS.txt");
    static final File DIREC_SJF = new File("src/Recursos/datosSJF.txt");
    static final File DIREC_ROUND = new File("src/Recursos/datosRoundRobin.txt");
    static final File DIREC_POR_PRIORI = new File("src/Recursos/datosPorPrioridad.txt");
    
    static BufferedReader br;
    static BufferedWriter bw;
    
    private static String leerDatos(File f){
        String h = new String();
        try {
            br = new BufferedReader(new FileReader(f));
            String l;
            while ((l = br.readLine()) != null){
                h += l + "\n";
            }
            br.close();
            return h;
        } catch (IOException ioe) {
            System.err.println("Error I/O: "+ioe.getMessage());
        }
        return "----Datos---\nNro. Procesos\tTiempo espera prom.\tTiempo de duración\n";
    }
    
    private static void escribirDatos(String datos, File f){
        try {
            String h = leerDatos(f);
            bw = new BufferedWriter(new FileWriter(f));
            bw.write(h);
            if(datos.endsWith("\n"))
                bw.write(datos);
            else
                bw.write(datos+"\n");
            bw.close();
            System.out.println("Datos escritos correctamente");
        } catch (IOException ioe) {
            System.err.println("Error en abir archivo de datos: "
                    +ioe.getMessage());
        }
    }
    
    private static void limpiarDatos(File f){
        try {
            bw = new BufferedWriter(new FileWriter(f));
            bw.write("");
            bw.close();
        } catch (IOException ioe) {
            System.err.println("Error en abir archivo de datos: "
                    +ioe.getMessage());
        }
        
    }
    
    public static void escribirEn(String str, int t){
        switch(t){
            case FCFS:
                escribirDatos(str, DIREC_FCFS);
                break;
            case SJF:
                escribirDatos(str, DIREC_SJF);
                break;
            case RR:
                escribirDatos(str, DIREC_ROUND);
                break;
            case PRIORI:
                escribirDatos(str, DIREC_POR_PRIORI);
                break;
            default:
                System.err.println("Direc. no soportada");
        }
    }
    
    public static void main(String[] args) {
        limpiarDatos(DIREC_FCFS);
        limpiarDatos(DIREC_SJF);
        limpiarDatos(DIREC_ROUND);
        limpiarDatos(DIREC_POR_PRIORI);
        
        for (int i = 0; i <= PRIORI; i++) {
            escribirEn("----Datos---\nNro. Procesos\tTiempo espera prom.\tTiempo de duración\n", i);
        }
    }
}
