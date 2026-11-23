package farmville;

/**
 * Representa un animal perteneciente a la granja.
 */
public abstract class Animal implements Alimentable, Producible {

    private String nombre;
    private int costo;
    private boolean alimentado;

    /**
     * Constructor de la clase Animal.
     *
     * @param nombre nombre del animal
     * @param costo precio de compra del animal
     */
    public Animal(String nombre, int costo) {
        this.nombre = nombre;
        this.costo = costo;
        this.alimentado = false;
    }

    /**
     * Alimenta al animal para que pueda producir.
     */
    @Override
    public void alimentar() {
        alimentado = true;
    }

    /**
     * Indica si el animal ya fue alimentado.
     *
     * @return true si está alimentado; false si no lo está
     */
    public boolean estaAlimentado() {
        return alimentado;
    }

    /**
     * Obtiene el nombre del animal.
     *
     * @return nombre del animal
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el costo del animal.
     *
     * @return costo del animal
     */
    public int getCosto() {
        return costo;
    }

    /**
     * Cambia el animal a estado no alimentado después de producir.
     */
    protected void consumirAlimento() {
        alimentado = false;
    }

    /**
     * Produce el recurso correspondiente al animal.
     *
     * @return producto generado
     */
    @Override
    public abstract Producto producir();
}
