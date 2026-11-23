package farmville;

/**
 * Representa un espacio de la granja donde se puede sembrar un cultivo.
 */
public class Parcela {

    private int numero;
    private Cultivo cultivo;

    /**
     * Constructor de la clase Parcela.
     *
     * @param numero número identificador de la parcela
     */
    public Parcela(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "El número de la parcela debe ser mayor que cero."
            );
        }

        this.numero = numero;
        this.cultivo = null;
    }

    /**
     * Verifica si la parcela está vacía.
     *
     * @return true si no tiene cultivo; false si está ocupada
     */
    public boolean estaVacia() {
        return cultivo == null;
    }

    /**
     * Siembra un cultivo en la parcela.
     *
     * @param nuevoCultivo cultivo que se desea sembrar
     * @return true si se sembró; false si la parcela estaba ocupada
     */
    public boolean sembrar(Cultivo nuevoCultivo) {
        if (nuevoCultivo == null) {
            throw new IllegalArgumentException(
                    "El cultivo no puede ser nulo."
            );
        }

        if (!estaVacia()) {
            return false;
        }

        cultivo = nuevoCultivo;
        return true;
    }

    /**
     * Cosecha el cultivo contenido en la parcela.
     *
     * @return monedas obtenidas por la cosecha
     * @throws ParcelaVaciaException si la parcela está vacía
     * @throws CultivoNoListoException si el cultivo no está listo
     */
    public int cosechar() {
        if (estaVacia()) {
            throw new ParcelaVaciaException(
                    "La parcela " + numero + " está vacía."
            );
        }

        int ganancia = cultivo.cosechar();
        cultivo = null;

        return ganancia;
    }

    /**
     * Obtiene el número de la parcela.
     *
     * @return número de la parcela
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtiene el cultivo sembrado.
     *
     * @return cultivo de la parcela o null si está vacía
     */
    public Cultivo getCultivo() {
        return cultivo;
    }
}
