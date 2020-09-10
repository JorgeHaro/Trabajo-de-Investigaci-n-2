package Modelo;

/**
 *
 * @author Kevin
 */
public class Interrupciones {
    static final int NINGUNO = 0, REQ_IO = 1, FIN_REQ_IO = 2, TIMER_QUANTUM = 3;
    int reqIO = 0;
    static final double PROB_IO = 0.05, PROB_FIN_IO = 0.1;

    public Interrupciones() {
    }
    
    public int generarPosibleInterrupcionIO(){
        Double random = Math.random();
        if(random<PROB_IO){
            reqIO++;
            return REQ_IO;
        }
        return NINGUNO;
    }
    
    public int generarPosibleInterrupcionFinIO(){
        Double random = Math.random();
        if(reqIO > 0 && random<PROB_FIN_IO){
            reqIO--;
            return FIN_REQ_IO;
        }
        return NINGUNO;
    }
    
    
    
}
