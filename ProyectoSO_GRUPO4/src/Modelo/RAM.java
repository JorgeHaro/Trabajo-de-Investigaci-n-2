package Modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author Kevin
 */
class Hueco {
    private int capacidad;
    private int dirInicio;

    public Hueco() {
    }

    public Hueco(int capacidad, int direccionIncio) {
        this.capacidad = capacidad;
        dirInicio = direccionIncio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    public void setDirInicio(int dirInicio) {
        this.dirInicio = dirInicio;
    }

    public int getDirInicio() {
        return dirInicio;
    }
    
    public int getDirFin(){
        return dirInicio + capacidad;
    }
}

class MemoriaSO {
    private int capacidad;
    
    public MemoriaSO() {
    }

    public MemoriaSO(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }
}

class Comparador implements Comparator<Hueco> {
    @Override
    public int compare(Hueco t, Hueco t1) {
        if(t.getCapacidad()==t1.getCapacidad())
            return 0;
        if(t.getCapacidad()>t1.getCapacidad())
            return 1;
        return -1;
    }
}

public class RAM extends LinkedList<Object>{
    public static final int PRIMER_HUECO = 0, MEJOR_HUECO = 1, PEOR_HUECO = 2;
    private int capTotal;
    private int politica = PRIMER_HUECO;

    public RAM() {
        
    }
    
    public RAM(int capacidadTotal) {
        capTotal = capacidadTotal;
        agregarSO(800);
    }

    //Interfaz
    public boolean agregarProceso(Proceso p){
        switch(politica){
            case PRIMER_HUECO:
                for (Object o : this) {
                    if(o instanceof Hueco){
                        Hueco h = (Hueco)o;
                        if(h.getCapacidad()>p.getMemoria()){
                            p.setMemoriaInicio(h.getDirInicio());
                            h.setDirInicio(h.getDirInicio()+p.getMemoria()+1);
                            h.setCapacidad(h.getCapacidad()-p.getMemoria()-1);
                            add(indexOf(o), p);
                            return true;
                        }
                    }
                }
                break;
            case MEJOR_HUECO:
                ArrayList<Hueco> huecos = new ArrayList<>();
                for (Object o : this) {
                    if(o instanceof Hueco){
                        huecos.add((Hueco)o);
                    }
                }
                huecos.sort(new Comparador());
                for (Hueco h : huecos) {
                    if(h.getCapacidad()>p.getMemoria()){
                        p.setMemoriaInicio(h.getDirInicio());
                        h.setDirInicio(h.getDirInicio()+p.getMemoria()+1);
                        h.setCapacidad(h.getCapacidad()-p.getMemoria()-1);
                        add(indexOf(h), p);
                        return true;
                    }
                }
                break;
            case PEOR_HUECO:
                ArrayList<Hueco> huecos1 = new ArrayList<>();
                for (Object o : this) {
                    if(o instanceof Hueco){
                        huecos1.add((Hueco)o);
                    }
                }
                huecos1.sort(new Comparador().reversed());
                for (Hueco h : huecos1) {
                    if(h.getCapacidad()>p.getMemoria()){
                        p.setMemoriaInicio(h.getDirInicio());
                        h.setDirInicio(h.getDirInicio()+p.getMemoria()+1);
                        h.setCapacidad(h.getCapacidad()-p.getMemoria()-1);
                        add(indexOf(h), p);
                        return true;
                    }
                }
                break;
        }
      
        return false;
    }
    
    public boolean quitarProceso(Proceso p){
        int i = indexOf(p);
        if(i>-1){
            Hueco h1=null, h2=null;
            if(i-1>-1 && (get(i-1) instanceof Hueco)){
                h1 = (Hueco)get(i-1);
            }
            if(i+1<size() && (get(i+1) instanceof Hueco)){
                h2 = (Hueco)get(i+1);
            }
            if(h1!=null && h2!=null){
                add(i, new Hueco(p.getMemoria()+h1.getCapacidad()+2
                        +h2.getCapacidad(), h1.getDirInicio()));
                remove(h1);
                remove(h2);
                remove(p);
                return true;
            }
            if(h1!=null){
                add(i, new Hueco(p.getMemoria()+h1.getCapacidad()+1, 
                        h1.getDirInicio()));
                remove(h1);
                remove(p);
                return true;
            }
            if(h2!=null){
                add(i, new Hueco(p.getMemoria()+h2.getCapacidad()+1, 
                        p.getMemoriaInicio()));
                remove(h2);
                remove(p);
                return true;
            }
            add(i, new Hueco(p.getMemoria(), p.getMemoriaInicio()));
            remove(p);
            return true;
        }
        return false;
    }
    
    //Métodos privados
    private void agregarSO(int memoria){
        add(new MemoriaSO(memoria));
        add(new Hueco(capTotal - memoria-1, memoria+1));
    }
    
    //G y S
    public void setCapTotal(int capTotal) {
        this.capTotal = capTotal;
        Object o = getLast();
        if(o instanceof Hueco){
            int dirI = ((Hueco)o).getDirInicio();
            remove(o);
            addLast(new Hueco(capTotal-dirI, dirI));
        }
    }

    public int getCapTotal() {
        return capTotal;
    }

    public void setPolitica(int politica) {
        this.politica = politica;
    }

    public int getPolitica() {
        return politica;
    }
    
}
