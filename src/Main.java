public class Main {
    public static void main(String[] args) {
        Main programa = new Main();
        programa.inicio();
    }

    public void inicio() {
        final int MAX_INTENTOS = 10;
        final String PELICULAS = "Titulos_Peliculas.txt";

        Juego juego = new Juego(MAX_INTENTOS, PELICULAS);
        juego.inicio();
    }
}