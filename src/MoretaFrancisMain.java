public class MoretaFrancisMain {
    public static void main(String[] args) {
        MoretaFrancisMain programa = new MoretaFrancisMain();
        programa.inicio();
    }

    public void inicio() {
        //Definimos el numero de intentos y el fichero donde se ubican los titulos
        final int MAX_INTENTOS = 10;
        final String PELICULAS = "Titulos_Peliculas.txt";
        //Creamos un objeto de la clase Juego
        MoretaFrancisJuego juego = new MoretaFrancisJuego(MAX_INTENTOS, PELICULAS);
        //Llamamos al metodo que inicia todo el proceso del juego
        juego.inicio();
    }
}