import java.util.Random;


public class Casilla {

  private enum PuntoCardinal {
    ARRIBA, ABAJO, DERECHA, IZQUIERDA
  }

  private boolean arriba;

  private boolean abajo;

  private boolean derecha;

  private boolean izquierda;

  private int x;

  private Laberinto laberinto;

  private boolean visitada;

  private Casilla() {
      this.x = -1;
      this.y = -1;
  }

  private Casilla(int x, int y, Laberinto laberinto) {
      this.arriba = this.abajo = this.derecha = this.izquierda = false;
      this.x = x;
      this.y = y;
      this.laberinto = laberinto;
      this.visitada = false;
  }

  public boolean noHayVecinosSinVisitar() {
      Lista<Casilla> vecinos = obtenerVecinos();
      boolean todosVisitados = true;
      for (Casilla vecino : vecinos) {
          if (!vecino.fueVisitado()) {
              todosVisitados = false;
              break;
          }
      }
      return todosVisitados;
  }

  public boolean noHayVecinosSinVisitarConPaso() {
      Lista<Casilla> vecinos = obtenerVecinos();
      boolean todosVisitados = true;
      for (Casilla vecino : vecinos) {
          if (!vecino.fueVisitado() && hayPaso(vecino)) {
              todosVisitados = false;
              break;
          }
      }
      return todosVisitados;
  }

  public Casilla obtenerVecinoSinVisitar() {
      Lista<Casilla> vecinos = obtenerVecinos();
      Random r = new Random();
      int rango = vecinos.longitud();
      do {
          int posicion = r.nextInt(rango);
          Casilla vecino = vecinos.getElemento(posicion);
          if (!vecino.fueVisitado()) {
              return vecino;
          } else {
              rango--;
              vecinos.eliminar(vecino);
          }
      } while (!vecinos.esVacia());
      return new Casilla();
  }



//ultimo
}
