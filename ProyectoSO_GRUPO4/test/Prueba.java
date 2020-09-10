
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
public class Prueba {
    static final File DATOS = new File("src/Recursos/datos.txt");
    static BufferedReader br;
    static BufferedWriter bw;
    
    private static String leerDatos(){
        String h = new String();
        try {
            br = new BufferedReader(new FileReader(DATOS));
            String l = br.readLine();
            while (l!=null) {                
                h += l;
                l = br.readLine();
            }
            br.close();
        } catch (Exception e) {
        }
        return h;
    }
    
    public static void escribirDatos(String datos){
        try {
            String h = leerDatos();
            bw = new BufferedWriter(new FileWriter(DATOS));
            bw.write(h);
            if(datos.endsWith("\n"))
                bw.write(datos);
            else
                bw.write(datos+"\n");
            bw.close();
        } catch (IOException ioe) {
            System.err.println("Error en abir archivo de datos: "
                    +ioe.getMessage());
        }
    }
    
    public static void limpiarDatos(){
        try {
            bw = new BufferedWriter(new FileWriter(DATOS));
            bw.write("");
            bw.close();
        } catch (IOException ioe) {
            System.err.println("Error en abir archivo de datos: "
                    +ioe.getMessage());
        }
        
    }
    
    public static void main(String[] args) {
        escribirDatos("abc\tfgh\tasd");
        escribirDatos("123\t321\t444");
    }
}
