package farmville;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la granja administrada por el jugador.
 * Contiene las parcelas y los animales.
 */
public class Granja {

    private List<Parcela> parcelas;
    private List<Animal> animales;

    /**
     * Constructor de la clase Granja.
     *
     * La granja comienza con una parcela gratuita.
     */
    public Granja() {
        parcelas = new ArrayList<>();
        animales = new ArrayList<>();

        parcelas.add(new Parcela(1));
    }

    /**
     * Agrega una parcela a la granja.
     *
     * @param parcela parcela que se desea agregar
     */
    public void agregarParcela(Parcela parcela) {
        if (parcela == null) {
            throw new IllegalArgumentException(
                    "La parcela no puede ser nula."
            );
        }

        parcelas.add(parcela);
    }

    /**
     * Agrega un animal a la granja.
     *
     * @param animal animal que se desea agregar
     */
    public void agregarAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException(
                    "El animal no puede ser nulo."
            );
        }

        animales.add(animal);
    }

    /**
     * Muestra las parcelas y los animales de la granja.
     */
    public void mostrarEstado() {
        System.out.println();
        System.out.println("       ESTADO DE LA GRANJA");

        System.out.println("Parcelas:");

        for (Parcela parcela : parcelas) {

            if (parcela.estaVacia()) {
                System.out.println(
                        parcela.getNumero() + ". Vacía"
                );
            } else {
                Cultivo cultivo = parcela.getCultivo();
                String estado;

                if (cultivo.estaListo()) {
                    estado = "Listo para cosechar";
                } else {
                    estado = "En crecimiento";
                }

                System.out.println(
                        parcela.getNumero() + ". "
                                + cultivo.getNombre()
                                + " (" + estado + ")"
                );
            }
        }

        System.out.println();
        System.out.println("Animales:");

        if (animales.isEmpty()) {
            System.out.println("No hay animales en la granja.");
        } else {
            for (int i = 0; i < animales.size(); i++) {
                Animal animal = animales.get(i);
                String estado;

                if (animal.estaAlimentado()) {
                    estado = "Alimentado";
                } else {
                    estado = "Sin alimentar";
                }

                System.out.println(
                        (i + 1) + ". "
                                + animal.getNombre()
                                + " (" + estado + ")"
                );
            }
        }

    }

    /**
     * Obtiene la lista de parcelas.
     *
     * @return lista de parcelas
     */
    public List<Parcela> getParcelas() {
        return parcelas;
    }

    /**
     * Obtiene la lista de animales.
     *
     * @return lista de animales
     */
    public List<Animal> getAnimales() {
        return animales;
    }
}
