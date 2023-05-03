
import java.util.Iterator;
import java.util.NoSuchElementException;


 public class Cola<T> implements Coleccionable<T>{

   private class Nodo {

       /**
        * El elemento del nodo.
        */
       public T elemento;
       /**
        * El siguiente nodo.
        */
       public Nodo siguiente;

       /**
        * Construye un nodo con un elemento.
        *
        * @param elemento el elemento del nodo.
        */
       public Nodo(T elemento) {
           this.elemento = elemento;
       }
   }

  private class IteradorCola implements Iterator<T> {
        private Nodo siguiente;

        private IteradorCola() {

        }

        @Override
        public boolean hasNext() {
            return siguiente != null;
        }

       @Override
       public T next() {
         T elemento= siguiente.elemento;
         siguiente = siguiente.siguiente;
         return elemento;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

    }



    /* Nodo inicial de la cola*/
    private Nodo inicio;
    /* Nodo final de la cola*/
    private Nodo rabo;
    /* Tamaño de la cola*/
    private int elementos;

    /**
       * Constructor por omisión de la clase.
       */
      public Cola() {

      }


      public Cola(T[] elementos) {
          for (T aux : elementos) {
            queue(aux);
          }
      }

      public Cola(Coleccionable<T> elementos) {
          Iterator<T> aux = elementos.iterator();
          while (aux.hasNext()) {
            queue(aux.next());
          }
      }

      public void queue(T elemento) throws IllegalArgumentException{
          if (elemento == null) {
              throw new IllegalArgumentException("El elemento no puede ser null");
          }
          Nodo nuevoNodo = new Nodo(elemento);
          if(inicio == null){
              inicio = rabo = nuevoNodo;
          } else {
              rabo.siguiente = nuevoNodo;
              rabo = nuevoNodo;
          }
          elementos++;
      }

       public T dequeue() throws NoSuchElementException{
           if(esVacia()){
               throw new NoSuchElementException("El elemento no existe");
           }
           T elemento = inicio.elemento;
           if(elementos == 1){
               inicio = rabo = null;
           } else {
               inicio = inicio.siguiente;
           }
           elementos--;
           return elemento;
       }


        public T peek() {
            if(inicio == null){
                return null;
            }
            return inicio.elemento;
        }


        @Override
        public String toString() {
            String s = "[";
            Nodo n = this.inicio;
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
