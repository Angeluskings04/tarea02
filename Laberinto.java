import java.util.Random;

/**
 * Un laberinto es una colección de Casillas donde puedes llegar de un punto a otro
 */
public class Laberinto{

    /**
     * La cuadrícula o matriz donde se guardan las Casillas
     */
  public Casilla[][] casillas;

  /**
     * El número de casillas que tiene de alto el Laberinto
     */
  private int alto;

  /**
     * El número de casillas que tiene de ancho el Laberinto
     */
  private int ancho;

  /**
     * Genera un Laberinto de acuerdo a un ancho y alto de longitud
     * @param alto el número de casillas que tendrá de alto el Laberinto
     * @param ancho el número de casillas que tendrá de ancho el laberinto
     */
  public Laberinto(int alto, int ancho){
    this.alto = alto;
    this.ancho = ancho;
    this.casillas = generarCasillas();
    generarLaberinto();
  }

  public void impresion(){

    for (int i = 0; i< alto; i++) {
      for (int j = 0; j < ancho; j++) {
        System.out.println(casillas[i][j]);
        System.out.println();
      }
    }
  }

  /**
     * Marca a todas las Casillas del laberinto como no visitadas
     */
  private void marcarTodosSinVisitar(){
      for (int i = 0; i < casillas.length; i++) {
          for (int j = 0; j < casillas[i].length; j++) {
             casillas[i][j].desmarcarVisitada();
          }
      }
  }

  /**
     * Genera al laberinto
     */
  private void generarLaberinto(){
      Casilla casilla = obtenerCasillaAlAzar();
      casilla.visitar();
      Pila<Casilla> pila = new Pila<Casilla>();
      pila.push(casilla);
      while(!pila.esVacia()){
          casilla = pila.peek();
          if(casilla.noHayVecinosSinVisitar()){
              pila.pop();
          } else {
              Casilla vecino = casilla.obtenerVecinoSinVisitar();
              if(casilla.getX() < 0) continue;
              casilla.visitar(vecino);
              pila.push(vecino);
          }
      }
      // Esto se hace para poder encontrar un camino de una Casilla a otra con el mismo algoritmo
      marcarTodosSinVisitar();
  }

  /**
     * Obtiene un camino de Casillas de una posiciońa  otra
     * @param x1 la x-coordenada de la posición 1
     * @param y1 la x-coordenada de la posición 1
     * @param x2 la x-coordenada de la posición 2
     * @param y2 la x-coordenada de la posición 2
     * @return una arreglo con las Casillas a recorrer
     */
  public Casilla[] obtenerCamino(int x1, int y1, int x2, int y2){
      if(x1 < 0 || x1 >= ancho || y1 < 0 || y1 >= alto){
          throw new IllegalArgumentException();
      }
      if(x2 < 0 || x2 >= ancho || y2 < 0 || y2 >= alto){
          throw new IllegalArgumentException();
      }
      Casilla inicio = obtenerCasilla(x1, y1);
      Casilla fin = obtenerCasilla(x2, y2);

      return obtenerCamino(inicio, fin);

  }

  /**
     * Obtiene un camino de una Casilla a otra
     * @param inicio la Casilla de inicio
     * @param fin la Casilla a llegar
     * @return una arreglo con las Casillas que se deben de recorrer para llegear del inicio al fin
     */
  protected Casilla[] obtenerCamino(Casilla inicio, Casilla fin){
      Pila<Casilla> pila = new Pila<Casilla>();
      Casilla casilla = inicio;
      casilla.visitar();
      pila.push(casilla);
      System.out.println("Pila es vacia?"+pila.esVacia());
      System.out.println(pila.getTamanio());
      while(!pila.esVacia()){
          casilla = pila.peek();
          if(casilla.equals(fin)) break;
          if(casilla.noHayVecinosSinVisitarConPaso()){
              pila.pop();
          } else {
              Casilla vecino = casilla.obtenerVecinoDisponible();
              vecino.visitar();
              pila.push(vecino);
          }
      }

      marcarTodosSinVisitar();
      Casilla[] camino = new Casilla[pila.getTamanio()];
      for (int i = camino.length - 1; i >= 0; i--) {
        camino[i] = pila.pop();
      }
      return camino;
  }

  public Casilla obtenerCasillaAlAzar(){
      Random r = new Random();
      int x = r.nextInt(ancho);
      int y = r.nextInt(alto);
      return casillas[y][x];
  }

  public void imagenLaberinto(){
    for(int k = 0;k<this.getAncho();k++){
        System.out.print(" _ ");
    }
    System.out.println();
    for(int j=0;j<this.getAlto();j++){
        for(int i = 0;i<ancho;i++){
            casillas[j][i].representacionCasilla();
            System.out.print(casillas[j][i].getImagen());
        }
        System.out.println();
    }
  }

  private Casilla[][] generarCasillas(){
      Casilla[][] casillas = new Casilla[alto][ancho];
      for (int i = 0; i < casillas.length; i++) {
          for (int j = 0; j < casillas[i].length; j++) {
              casillas[i][j] = new Casilla(j,i, this);
          }
      }
      return casillas;
  }

  protected Casilla obtenerCasilla(int x, int y){
      if(x < 0 || x >= ancho){
          throw new IllegalArgumentException("El laberinto no tiene el alcance para la coordenada x: " + x);
      }
      if(y < 0 || y >= alto){
          throw new IllegalArgumentException("El laberinto no tiene el alcance para la coordenada y: " + y);
      }
      return casillas[y][x];
  }

  protected Casilla[][] getCasillas(){
      return casillas;
  }

  public int getAlto(){
      return alto;
  }

  public int getAncho(){
      return ancho;
  }

  @Override
  public String toString() {
      return "Laberinto [alto=" + alto + ", ancho=" + ancho + "]";
  }


}
