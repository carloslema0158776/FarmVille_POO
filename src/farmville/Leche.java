package farmville;

/**
 * Representa la leche producida por una vaca.
 */
public class Leche extends Producto {

    /**
     * Constructor de la clase Leche.
     */
    public Leche() {
        super("Leche", 15);
    }

    /**
     * Vende la leche.
     *
     * @return precio de venta de la leche
     */
    @Override
    public int vender() {
        return getPrecioVenta();
    }
}
