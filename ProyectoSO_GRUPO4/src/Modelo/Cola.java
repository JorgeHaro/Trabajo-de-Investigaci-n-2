package Modelo;

import java.util.LinkedList;

/**
 *
 * @author Kevin
 */
class ColaTotales extends LinkedList<Proceso> {

    @Override
    public void addLast(Proceso e) {
        super.addLast(e);
        e.setEstado(Proceso.CREADO);
    }
    
    public int getProcesosVivos(){
        int n = 0;
        if(size()>0){
            for (Proceso p : this) {
                //if(p.getEstado()!=Proceso.FINALIZADO)
                    n++;
            }
        }
        return n;
    }
}

class ColaListo extends Cola {

    @Override
    public void addLast(Proceso e) {
        super.addLast(e);
        e.setEstado(Proceso.LISTO);
    }

    public int getCantMemoriaUsada(){
        int sumMemoria = 0;
        for (int i = 0; i < size(); i++) {
            sumMemoria+=get(i).getMemoria();
        }
        return sumMemoria;
    }
}

class ColaES extends Cola {
    @Override
    public void addLast(Proceso e) {
        super.addLast(e);
        e.setEstado(Proceso.BLOQUEADO);
        //e.disp = (int)(Math.random()*5);
        e.disp = (int)(Math.random()*9);
    }
    
    public int getCantMemoriaUsada(){
        int memoriaTotal = 0;
        for (Proceso p : this) {
            memoriaTotal += p.getMemoria();
        }
        return memoriaTotal;
    }
}

public abstract class Cola extends LinkedList<Proceso>{
    
}
