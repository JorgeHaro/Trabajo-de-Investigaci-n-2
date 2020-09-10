/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author LUIS ESPINOZA
 */

import Modelo.Interrup;
import Modelo.Proceso;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Modelo extends AbstractTableModel {
    ArrayList<Interrup> listaInterrupciones ;
    


    public Modelo(ArrayList<Interrup> listaInterrupciones){
        this.listaInterrupciones=listaInterrupciones;
    }

   
    //String[] encabezados = {"DNI" ,"PrimerNombre","SegundoNombre","ApellidoPaterno","ApellidoMaterno","Telefono1","Telefono2","Direccion","Email","DatosDelPadre","DatosDeLaMadre"};
    //String[] encabezados = {"N°","Telefono2","Seguro","TipoSeguro","LugarSeguro","Direccion","Email","DatosDelPadre","DatosDeLaMadre"};
    String[] encabezados = {"ID Proceso","Tiempo Arribo","Tipo","Dispositivo"};
    public int getColumnCount() {
        return encabezados.length;

    }

    public int getRowCount() {
        return listaInterrupciones.size();

    }

    @Override
    public String getColumnName(int x) {
        return encabezados[x];
    }
    
    
    public String nombreDispositivo(int disp){
        String dispositivo="";
        switch(disp){
            case 0: dispositivo="Impresora";break;
            case 1: dispositivo="Disco";break;
            case 2: dispositivo="Mouse";break;
            case 3: dispositivo="Teclado";break;
            case 4: dispositivo="USB";break;
        }
        return dispositivo;
    }
    
    public String tipoInterrupcion(int disp){
        String interrupcion="E/S";
        switch(disp){
            case 5: interrupcion="Timer";break;
            case 6: interrupcion="Error de division";break;
            case 7: interrupcion="Error de null";break;
            case 8: interrupcion="Error de punto flotante";break;
        }
        return interrupcion;
    }

    
    public Object getValueAt(int x, int y) {
        String retorno = "";
        Interrup interrupcion = listaInterrupciones.get(x);

        switch(y){
            case 0: retorno = String.valueOf(interrupcion.getIdproceso());
                break;
            case 1: retorno = String.valueOf(interrupcion.getArribo());
                break;
            case 2: retorno = tipoInterrupcion(interrupcion.getDisp());
                break;
            case 3: retorno = nombreDispositivo(interrupcion.getDisp());
                break;
        }

        return retorno;

    }
}
