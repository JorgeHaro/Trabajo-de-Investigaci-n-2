package Modelo;

/**
 *
 * @author Kevin
 */
public interface Arrancable {
    public void comenzar();
    public void parar();
    public void setDelay(int delay);
    public int getDelay();
}
