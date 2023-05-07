

public class MainLaberinto{
  public static void main(String[] args) {

    Laberinto l = new Laberinto(5,5);

    //l.impresion();

    Casilla nueva = l.obtenerCasillaAlAzar();
    nueva.representacionCasilla();

    System.out.println("Casillas: "+nueva.getImagen()+nueva.getImagen());

    //l.imagenLaberinto();

    Casilla alzar1 = l.obtenerCasillaAlAzar();
    Casilla alzar2 = l.obtenerCasillaAlAzar();

    /*System.out.println(alzar1.getY() + " y "+alzar1.getX());
    System.out.println(alzar2.getY() + " y "+alzar2.getX());*/



    Casilla [] camino = l.obtenerCamino(alzar1,alzar2);

  for(int i = 0;i<camino.length;i++){
    System.out.println(camino[i]);
  }

    l.imagenLaberinto();
    
  }
}
