import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p> Clase concreta para modelar la estructura de datos Pila</p>
 * <p>Esta clase implementa una Pila genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 * @param <T> Tipo que tienen los objetos que guarda esta pila.
 */
public class Pila<T> extends Lista<T> implements Coleccionable<T>  {

  private class Nodo {

     public T elemento;

     public Nodo siguiente;


     public Nodo(T elemento) {
         this.elemento = elemento;
     }
 }

  private class IteradorPila implements Iterator<T> {

    public Nodo siguiente;

    public IteradorPila() {
        siguiente = tope;
    }

    @Override
    public boolean hasNext() {
        return siguiente != null;
    }

    @Override
    public T next() {
        T elemento = siguiente.elemento;
        siguiente = siguiente.siguiente;
        return elemento;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("No implementado");
    }
 }


    private Nodo tope;
    private int elementos;

    /**
       * Constructor por omisión de la clase;
       */
      public Pila() {

      }

      public Pila(T[] elementos) {
          for (T e : elementos) {
            push(e);
          }
      }

      public Pila(Coleccionable<T> elementos) {
          for (T e : elementos) {
              push(e);
          }
      }

      public void push(T elemento) throws IllegalArgumentException {
          if (elemento == null) {
              throw new IllegalArgumentException("El elemento no puede ser null");
          }
          Nodo nuevoNodo = new Nodo(elemento);
          nuevoNodo.siguiente = tope;
          tope = nuevoNodo;
          elementos++;

      }

      public T pop() throws NoSuchElementException{
          if (esVacia()) {
              throw new NoSuchElementException("No hay elementos");
          }
          T elemento = tope.elemento;
          tope = tope.siguiente;
          elementos--;
          return elemento;
      }

      public T peek() {
          if (esVacia()) {
              return null;
          }
          return tope.elemento;
      }


      @Override
      public String toString() {
          String s = "[";
          Nodo n = this.tope;
          while (n != null) {
              if (n.siguiente == null) {
                  s += n.elemento;
              } else {
                  s += n.elemento + ",";
              }
              n = n.siguiente;
          }
          s += "]";
          return s;
      }









//ultimo
}
