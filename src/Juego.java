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
}
