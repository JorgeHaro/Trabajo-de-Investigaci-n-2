package Modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Kevin
 */
public class Usuario implements ActionListener, Arrancable{
    Timer t = new Timer(51, this);
    int maxDelay = 2000;
    SO refSO;
    
    public Usuario(SO so) {
        refSO = so;
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
    public void setDelay(int delay) {
        maxDelay = 100+100*delay;
    }

    @Override
    public int getDelay() {
        return (int)maxDelay;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!refSO.crearNuevoProceso(new Proceso()))
            Proceso.cantProcesos--;
        
        genTiempoAleatorio();
    }
    
    private void genTiempoAleatorio(){
        int nDelay = (int)(1000+Math.random()*maxDelay);
        t.setDelay(nDelay);
    }
    
}
