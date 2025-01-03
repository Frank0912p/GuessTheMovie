import java.util.*;
import java.io.File;
//Se crea la clase Juego y declaramos sus atributos
public class Juego {
    private int intentosRestantes;
    private final String peliculas;
    private String tituloEscogido;
    private String tituloEscogidoOculto;
    private final char[] letrasAcertadas;
    private int contadorAciertos;
    private final Scanner scanner;
    //Creacion del metodo contructor
    public Juego(int maxIntentos, String peliculas) {
        this.intentosRestantes = maxIntentos;
        this.peliculas = peliculas;
        this.letrasAcertadas = new char[26];
        this.contadorAciertos = 0;
        this.scanner = new Scanner(System.in);
    }
    //Metodo que inicia todo el proceso del juego
    public void inicio() {
        String[] titulos = leerFichero(peliculas);
        tituloAleatorio(titulos);
        menuInicioJuego();
    }
    //Metodo que lee el fichero externo y lo devuelve como un array
    private String[] leerFichero(String peliculas) {
        ArrayList<String> listaTitulos = new ArrayList<>();
        File fichero = new File(peliculas);
        //Control de excepciones
        try (Scanner scanner = new Scanner(fichero)) {
            while (scanner.hasNextLine()) {
                listaTitulos.add(scanner.nextLine());
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return listaTitulos.toArray(new String[0]);
    }
    //Metodo que escoge el titulo aleatoriamente
    private void tituloAleatorio(String[] titulos) {
        Random random = new Random();
        this.tituloEscogido = titulos[random.nextInt(titulos.length)].toLowerCase();
        this.tituloEscogidoOculto = tituloEscogidoOculto(this.tituloEscogido);
    }
    //Metodo que oculta el titulo aleatorio con * usando StringBuilder y devolviendo una String
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
    //Metodo que inicia el menu y lo muestra por pantalla
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
    //Metodo solicita un valor al usuario y lo valida con control de excepciones
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
    //Metodo que solicita una letra al usuario y la valida
    private void adivinarLetra() {
        System.out.println("Ingresa una letra: ");
        String letra = scanner.nextLine().toLowerCase();
        if (letra.length() != 1 || !Character.isLetter(letra.charAt(0))) {
            System.out.println("\uD83D\uDE45\u200D♂\uFE0F Debes ingresar solo una letra.");
            return;
        }
        //Comprueba si la letra ya fue adivinada y se lo indica al usuario
        char letraAdivinada = letra.charAt(0);
        if (compruebaLetraAdivinada(letraAdivinada)) {
            System.out.println("Esta letra ya la adivinaste, intenta con otra.");
            return;
        }
        //Comprueba si la letra coincide con alguna del titulo y se lo indica al usuario
        letrasAcertadas[contadorAciertos++] = letraAdivinada;
        if (tituloEscogido.contains(String.valueOf(letraAdivinada))) {
            tituloEscogidoOculto = actualizaTituloOculto(tituloEscogido, tituloEscogidoOculto, letraAdivinada);
            System.out.println("✅ Correcto! Vas por buen camino.");
        } else {
            System.out.println("❌ Incorrecto, pierdes un intento.");
            intentosRestantes--;
        }
        //Mensaje que se indica al usuario cuando ya ha adivinado todo el titulo
        if (!tituloEscogidoOculto.contains("*")) {
            System.out.println("Bien jugado, adivinaste la pelicula: " + tituloEscogido);
            System.exit(0);
        }
    }
    //Metodo que comprueba si la letra insertada por el usuario, ya fue insertada anteriormente
    private boolean compruebaLetraAdivinada(char letraInsertada) {
        for (int i = 0; i < contadorAciertos; i++) {
            if (letrasAcertadas[i] == letraInsertada) {
                return true;
            }
        }
        return false;
    }
    //Metodo que actualiza el titulo oculto con las letras que ya han sido acertadas
    private String actualizaTituloOculto(String titulo, String tituloOculto, char letraAcertada) {
        StringBuilder actual = new StringBuilder(tituloOculto);
        for (int i = 0; i < titulo.length(); i++) {
            if (titulo.charAt(i) == letraAcertada) {
                actual.setCharAt(i, letraAcertada);
            }
        }
        return actual.toString();
    }
    //Metodo que solicita al usuario el titulo completo y comprueba si coincide con el aleatorio.
    private void adivinarTitulo() {
        System.out.println("Wow que arriesgado! Ingresa el titulo: ");
        String tituloIsertado = scanner.nextLine().toLowerCase();
        if (tituloIsertado.equals(tituloEscogido)) {
            System.out.println("\uD83D\uDE31 Adivinaste! La pelicula es: " + tituloEscogido);
            System.exit(0);
        } else {
            System.out.println("Esa no es la pelicula, perdiste \uD83D\uDC80.");
            System.exit(0);
        }
    }
}
