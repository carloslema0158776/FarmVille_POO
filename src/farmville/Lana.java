package farmville;

/**
 * Representa la lana producida por una oveja.
 */
public class Lana extends Producto {

    /**
     * Constructor de la clase Lana.
     */
    public Lana() {
        super("Lana", 20);
    }

    /**
     * Vende la lana.
     *
     * @return precio de venta de la lana
     */
    @Override
    public int vender() {
        return getPrecioVenta();
    }
}
