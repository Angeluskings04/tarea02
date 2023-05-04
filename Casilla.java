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

  public boolean Arriba() {
      return arriba;
  }

  public void setArriba(boolean arriba) {
      this.arriba = arriba;
  }

  public boolean Abajo() {
      return abajo;
  }

  public void setAbajo(boolean abajo) {
      this.abajo = abajo;
  }

  public boolean Derecha() {
      return derecha;
  }

  public void setDerecha(boolean derecha) {
      this.derecha = derecha;
  }

  public boolean Izquierda() {
      return izquierda;
  }

  public void setIzquierda(boolean izquierda) {
      this.izquierda = izquierda;
  }

  public int getX() {
      return x;
  }

  public void setX(int x) {
      this.x = x;
  }

  public int getY() {
      return y;
  }

  public void setY(int y) {
      this.y = y;
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

  public Casilla obtenerVecinoDisponible() {
      Lista<Casilla> vecinos = obtenerVecinos();
      Random r = new Random();
      int rango = vecinos.longitud();
      do {
          int posicion = r.nextInt(rango);
          Casilla vecino = vecinos.getElemento(posicion);
          if (!vecino.fueVisitado() && hayPaso(vecino)) {
              return vecino;
          } else {
              rango--;
              vecinos.eliminar(vecino);
          }
      } while (!vecinos.esVacia());
      return new Casilla();
  }

  public boolean hayPaso(Casilla vecino) {

      switch (obtenerPuntoCardinal(vecino)) {
          case ARRIBA:
              return arriba;
          case ABAJO:
              return abajo;

          case DERECHA:
              return derecha;
          case IZQUIERDA:
              return izquierda;
          default:
              return false;
      }

  }

  public Lista<Casilla> obtenerVecinos() {
      Lista<Casilla> vecinos = new Lista<Casilla>();

      // Se añaden las coordenadas así para poder recorrer los vecinos más fácilmente
      int[][] vecinosCoordenadas = { { y, x + 1 }, { y, x - 1 }, { y + 1, x }, { y - 1, x } };
      for (int i = 0; i < vecinosCoordenadas.length; i++) {
          int x = vecinosCoordenadas[i][1];
          int y = vecinosCoordenadas[i][0];

          try {
              Casilla casilla = laberinto.obtenerCasilla(x, y);
              vecinos.agregar(casilla);
          } catch (IllegalArgumentException e) {
              continue;
          }
      }
      return vecinos;
  }

  public boolean fueVisitado() {
      return visitada;
  }

  public void visitar() {
      this.visitada = true;
  }

  public void desmarcarVisitada() {
      this.visitada = false;
  }

  public void visitar(Casilla casilla) {
      Lista<Casilla> vecinos = obtenerVecinos();
      if (vecinos.contiene(casilla) && !casilla.fueVisitado()) {
          switch (obtenerPuntoCardinal(casilla)) {
              case ARRIBA:
                  arriba = casilla.abajo = casilla.visitada = true;
                  break;
              case ABAJO:
                  abajo = casilla.arriba = casilla.visitada = true;
                  break;
              case DERECHA:
                  derecha = casilla.izquierda = casilla.visitada = true;
                  break;
              case IZQUIERDA:
                  izquierda = casilla.derecha = casilla.visitada = true;
                  break;
              default:
                  break;
          }
      }
  }

  private PuntoCardinal obtenerPuntoCardinal(Casilla casilla) {
      if (casilla.x - x == 1) {
          return PuntoCardinal.DERECHA;
      } else if (casilla.x - x == -1) {
          return PuntoCardinal.IZQUIERDA;
      } else if (casilla.y - y == 1) {
          return PuntoCardinal.ARRIBA;
      } else {
          return PuntoCardinal.ABAJO;
      }
  }



  @Override
  public String toString() {
      return "Casilla [este=" + este + ", norte=" + norte + ", oeste=" + oeste + ", sur=" + sur + ", visitada="
              + visitada + ", x=" + x + ", y=" + y + "]";
  }

  @Override
  public boolean equals(Object obj) {
      if (obj == null)
          return false;
      if (obj instanceof Casilla) {
          Casilla c = (Casilla) obj;
          if (c.x == x && c.y == y) {
              if (c.norte != norte || c.sur != sur) {
                  return false;
              }
              if (c.este != este || c.oeste != oeste) {
                  return false;
              }
              return true;
          }
      }
      return false;
  }


//ultimo
}
