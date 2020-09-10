package Modelo;

import java.util.Comparator;

/**
 *
 * @author Kevin
 */
public class Comparadores {
    private static final ComparadorFCFS CFCFS = new ComparadorFCFS();
    private static final ComparadorSJF CSJF = new ComparadorSJF();
    private static final ComparadorPrioridades CPRIORI = new ComparadorPrioridades();
    
    public static Comparator<Proceso> getComparador(int politica){
        switch(politica){
            case SO.Scheduler.FCFS:
                return CFCFS;
            case SO.Scheduler.SJF:
                return CSJF;
            case SO.Scheduler.RONROBIN:
                return CFCFS;
            case SO.Scheduler.POR_PRIORIDADES:
                return CPRIORI;
        }
        return null;
    }
    
    static class ComparadorFCFS implements Comparator<Proceso>{
        @Override
        public int compare(Proceso t, Proceso t1) {
            if(t.getID()==t1.getID())
                return 0;
            if(t.getID()>t1.getID())
                return 1;
            return -1;
        }
    }
    
    static class ComparadorSJF implements Comparator<Proceso>{
        @Override
        public int compare(Proceso t, Proceso t1) {
            if(t.getRestante()==t1.getRestante())
                return 0;
            if(t.getRestante()>t1.getRestante())
                return 1;
            return -1;
        }
    }
    
    static class ComparadorPrioridades implements Comparator<Proceso>{
        @Override
        public int compare(Proceso t, Proceso t1) {
            if(t.getPrioridad()==t1.getPrioridad())
                return 0;
            if(t.getPrioridad()<t1.getPrioridad())
                return 1;
            return -1;
        }
    }
    
}
