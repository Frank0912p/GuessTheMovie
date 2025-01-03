public class Main {
    public static void main(String[] args) {
        Main programa = new Main();
        programa.inicio();
    }

    public void inicio() {
        //Definimos el numero de intentos y el fichero donde se ubican los titulos
        final int MAX_INTENTOS = 10;
        final String PELICULAS = "Titulos_Peliculas.txt";
        //Creamos un objeto de la clase Juego
        Juego juego = new Juego(MAX_INTENTOS, PELICULAS);
        //Llamamos al metodo que inicia todo el proceso del juego
        juego.inicio();
    }
}