import java.util.*;
import java.io.File;

public class Juego {
    private int intentosRestantes;
    private final String peliculas;
    private String tituloEscogido;
    private String tituloEscogidoOculto;
    private final char[] letrasAcertadas;
    private int contadorAciertos;
    private final Scanner scanner;

    public Juego(int maxIntentos, String peliculas) {
        this.intentosRestantes = maxIntentos;
        this.peliculas = peliculas;
        this.letrasAcertadas = new char[26];
        this.contadorAciertos = 0;
        this.scanner = new Scanner(System.in);
    }

    public void inicio() {
        String[] titulos = leerFichero(peliculas);
        tituloAleatorio(titulos);
        menuInicioJuego();
    }

    private String[] leerFichero(String peliculas) {
        ArrayList<String> listaTitulos = new ArrayList<>();
        File fichero = new File(peliculas);
        try (Scanner scanner = new Scanner(fichero)) {
            while (scanner.hasNextLine()) {
                listaTitulos.add(scanner.nextLine());
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return listaTitulos.toArray(new String[0]);
    }

    private void tituloAleatorio(String[] titulos) {
        Random random = new Random();
        this.tituloEscogido = titulos[random.nextInt(titulos.length)].toLowerCase();
        this.tituloEscogidoOculto = tituloEscogidoOculto(this.tituloEscogido);
    }

    private String tituloEscogidoOculto(String tituloEscogido) {
        StringBuilder oculto = new StringBuilder();
        for (char c : tituloEscogido.toCharArray()) {
            if (Character.isLetter(c)) {
                oculto.append("*");
            } else {
                oculto.append(c);
            }
        }
        return oculto.toString();
    }

    public void menuInicioJuego() {
        while (intentosRestantes > 0) {
            System.out.println("\uD83C\uDFAC\uD83C\uDFAC\uD83C\uDFAC Guess the Movie \uD83C\uDFAC\uD83C\uDFAC\uD83C\uDFAC");
            System.out.println("El titulo de la pelicula tiene " + tituloEscogido.length() + " caracteres (incluyendo espacios y puntuacion)");
            System.out.println("Estas adivinando: " + tituloEscogidoOculto);
            System.out.println("Te quedan " + intentosRestantes + " intentos");
            System.out.println("Escoge una opcion: ");
            System.out.println("[1] Adivinar una letra\n[2] Adivinar el titulo completo\n[3] Salir");

            int opcionEscogida = validaOpcion();

            switch (opcionEscogida) {
                case 1:
                    adivinarLetra();
                    break;
                case 2:
                    adivinarTitulo();
                    break;
                case 3:
                    System.out.println("El juego ha terminado.");
                    return;
                default:
                    System.out.println("Opcion invalida, escoge 1, 2 o 3");
            }
        }
        System.out.println("Agotaste todos tus intentos \uD83D\uDC4E, la pelicula era: " + tituloEscogido);
    }

    private int validaOpcion() {
        while (true) {
            String opcionEscogida = scanner.nextLine();
            try {
                return Integer.parseInt(opcionEscogida);
            } catch (Exception e) {
                System.out.println("Opcion invalida, escoge 1, 2 o 3");
                System.out.println("Ingresa un numero del 1 al 3:");
            }
        }
    }
}
