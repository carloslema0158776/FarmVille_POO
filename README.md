# FarmVille — Simulador de granja en Java

**Proyecto académico de Programación Orientada a Objetos (POO).**

FarmVille es un juego de **consola** desarrollado en Java. El jugador administra una granja, compra parcelas y animales, siembra y cosecha cultivos, obtiene productos y controla sus monedas. El programa permite guardar y recuperar la partida en un archivo de texto.

## Funcionalidades

- Consultar el estado de las parcelas, los cultivos y los animales.
- Comprar nuevas parcelas, sembrar y cosechar diferentes cultivos.
- Comprar, alimentar y obtener productos de animales.
- Consultar el saldo y administrar compras y ganancias.
- Simular el avance del tiempo para permitir el crecimiento de cultivos.
- Guardar la partida al salir y cargarla en una ejecución posterior.

## Conceptos de programación orientada a objetos

- **Encapsulamiento y composición:** clases `Jugador`, `Granja` y `Parcela`.
- **Herencia y polimorfismo:** clases abstractas `Cultivo` y `Animal` con implementaciones concretas.
- **Interfaces:** `Cosechable`, `Alimentable`, `Producible` y `Vendible`.
- **Manejo de excepciones:** `CultivoNoListoException`, `AnimalNoAlimentadoException` y `ParcelaVaciaException`.
- **Colecciones:** uso de listas para organizar parcelas y animales.
- **Persistencia:** lectura y escritura de `partida_farmville.txt`.

## Cultivos y animales

Incluye cultivos como tomate, trigo, maíz, papa, lechuga, zanahoria y calabaza. Los animales implementados incluyen vaca, gallina y oveja, capaces de producir leche, huevos y lana, respectivamente.

## Tecnologías

**Java**, **IntelliJ IDEA**, programación orientada a objetos, archivos de texto y **JavaDoc**.

## Estado de publicación

✅ **Código fuente publicado:** los 28 archivos `.java` originales se encuentran en `src/farmville/`, junto con el README y el instructivo. Se excluyen los `.class`, configuraciones privadas del IDE y partidas guardadas. La documentación JavaDoc generada y los informes académicos originales no se publicaron.

## Estructura del repositorio

- `src/farmville/`: código Java de las clases, interfaces, excepciones y menú de consola.
- `docs/INSTRUCTIVO.md`: instrucciones de ejecución.
- `README.md`: documentación general.
- `.gitignore`: exclusión de archivos de compilación, configuración personal del IDE y partidas locales.

Los tres archivos originales aportados para este proyecto son el código, el JavaDoc generado y el informe académico. En la versión de GitHub priorizamos el código fuente y su documentación técnica, sin publicar configuraciones privadas ni una partida guardada.

## Cómo ejecutarlo

1. Tener instalado un **JDK compatible** y, opcionalmente, **IntelliJ IDEA**.
2. Abrir el proyecto en IntelliJ IDEA y localizar `src/farmville/Main.java`.
3. Ejecutar el método `main` de la clase `farmville.Main`.
4. Escribir el número correspondiente a la acción deseada.

También puedes compilar desde la raíz del proyecto con un JDK y ejecutar la clase principal:

```bash
javac -encoding UTF-8 -d out src/farmville/*.java
java -cp out farmville.Main
```

Al cerrar mediante la opción **10**, la partida se guarda automáticamente en `partida_farmville.txt`. La siguiente vez, el programa permite cargarla.

## Nota

Proyecto académico desarrollado para practicar los fundamentos de POO. Se trata de una aplicación de **consola**, no de un videojuego gráfico ni de una implementación oficial de FarmVille.
