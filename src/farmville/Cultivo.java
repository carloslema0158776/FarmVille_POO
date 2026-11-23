package farmville;

/**
 * Representa un cultivo que puede sembrarse en una parcela.
 */
public abstract class Cultivo implements Cosechable {

    private String nombre;
    private int costo;
    private int ganancia;
    private boolean listo;

    /**
     * Constructor de la clase Cultivo.
     *
     * @param nombre nombre del cultivo
     * @param costo costo para sembrar el cultivo
     * @param ganancia monedas obtenidas al cosechar
     */
    public Cultivo(String nombre, int costo, int ganancia) {
        this.nombre = nombre;
        this.costo = costo;
        this.ganancia = ganancia;
        this.listo = false;
    }

    /**
     * Simula el crecimiento del cultivo.
     */
    public void crecer() {
        listo = true;
    }

    /**
     * Indica si el cultivo está listo.
     *
     * @return true si está listo para cosechar
     */
    public boolean estaListo() {
        return listo;
    }

    /**
     * Obtiene el nombre del cultivo.
     *
     * @return nombre del cultivo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el costo del cultivo.
     *
     * @return costo del cultivo
     */
    public int getCosto() {
        return costo;
    }

    /**
     * Obtiene la ganancia del cultivo.
     *
     * @return ganancia obtenida al cosechar
     */
    public int getGanancia() {
        return ganancia;
    }

    /**
     * Realiza la validación general de la cosecha.
     *
     * @return monedas obtenidas
     * @throws CultivoNoListoException si el cultivo no está listo
     */
    protected int realizarCosecha() {
        if (!listo) {
            throw new CultivoNoListoException(
                    "El cultivo " + nombre + " todavía no está listo."
            );
        }

        listo = false;
        return ganancia;
    }

    /**
     * Cosecha el cultivo.
     *
     * @return monedas obtenidas
     */
    @Override
    public abstract int cosechar();
}
