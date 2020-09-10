/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author LUIS ESPINOZA
 */
public class Interrup {
    int idproceso;
    long arribo;
    int disp;

    public Interrup(int idproceso, long arribo, int disp) {
        this.idproceso = idproceso;
        this.arribo = arribo;
        this.disp = disp;
    }

    public int getIdproceso() {
        return idproceso;
    }

    public long getArribo() {
        return arribo;
    }

    public int getDisp() {
        return disp;
    }

       
        
}
