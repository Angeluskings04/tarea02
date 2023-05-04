

public class Laberinto{

  private Casilla[][] casillas;

  private int alto;

  private int ancho;

  public Laberinto(int alto, int ancho){
    this.alto = alto;
    this.ancho = ancho;
    this.casillas = generarCasillas();
    generarLaberinto();
  }

  private void marcarTodosSinVisitar(){
      for (int i = 0; i < casillas.length; i++) {
          for (int j = 0; j < casillas[i].length; j++) {
             casillas[i][j].desmarcarVisitada();
          }
      }
  }

  private void generarLaberinto(){
      Casilla casilla = obtenerCasillaAlAzar();
      casilla.visitar();
      Pila<Casilla> pila = new Pila<Casilla>();
      pila.push(casilla);
      while(!pila.esVacio()){
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

  protected Casilla[] obtenerCamino(Casilla inicio, Casilla fin){
      Pila<Casilla> pila = new Pila<Casilla>();
      Casilla casilla = inicio;
      casilla.visitar();
      pila.push(casilla);
      while(!pila.esVacio()){
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

      // En reversa, debido al como agrega elementos la pila
      for (int i = camino.length - 1; i >= 0; i--) {
          camino[i] = pila.pop();
      }
      return camino;
  }

  private Casilla obtenerCasillaAlAzar(){
      Random r = new Random();
      int x = r.nextInt(ancho);
      int y = r.nextInt(alto);
      return casillas[y][x];
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
