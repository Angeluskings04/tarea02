
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
/**
 * <p> Clase concreta para modelar la estructura de datos Lista</p>
 * <p>Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.1
 */
public class Lista<T>  implements Listable<T>,  Coleccionable <T>, Iterable <T>{

  /* Clase interna para construir la estructura */
public class Nodo{
  /* Referencias a los nodos anterior y siguiente */
    public Nodo anterior, siguiente;
    /* El elemento que almacena un nodo */
    public T elemento;

    /* Unico constructor de la clase */
    public Nodo(T elemento){
        this.elemento = elemento;
    }

    public boolean equals(Nodo n){
      if (n.elemento.equals(this.elemento)) {
        return true;
      }
      return false;
    }
}

public  class IteradorLista implements Iterator{
    /* La lista a recorrer*/
    public Lista<T> lista;
    /* Elementos del centinela que recorre la lista*/
    public  Lista<T>.Nodo anterior, siguiente;


    public IteradorLista(Lista<T> lista){
       this.lista = lista;
       siguiente = lista.cabeza;
       anterior = null;

    }
    @Override
    public boolean hasNext() {
        if (siguiente != null) {
          return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (siguiente == null) {
            return null;
        }
        T elemento = siguiente.elemento;
        siguiente = siguiente.siguiente;
        return elemento;
    }

    @Override
    public void remove(){
   throw new UnsupportedOperationException("Operacion no valida");
    }
}

    protected Nodo cabeza, cola;
    protected int longitud;

    public boolean esVacia(){
        return longitud == 0;

    }

    public void vaciar(){
        cabeza = null;
        cola = null;
        longitud =0;

    }

    public int getTamanio(){
        return longitud;

    }

    public int longitud() {
    return longitud;
}



    public void agregar(T elemento) {
    Nodo nodo = new Nodo(elemento);
    if (esVacia()) {
        cabeza = nodo;
        cola = nodo;
    } else {
        cola.siguiente = nodo;
        nodo.anterior = cola;
        cola = nodo;
    }
    longitud++;
  }

  public void agregarAlInicio(T elemento) {
      Nodo nodo = new Nodo(elemento);
      if (esVacia()) {
          cola = nodo;
      } else {
          cabeza.anterior = nodo;
      }
      nodo.siguiente = cabeza;
      cabeza = nodo;
      longitud++;
  }

  public void agregarAlFinal(T elemento) {
      Nodo nodo = new Nodo(elemento);
      if (esVacia()) {
          cabeza = nodo;
      } else {
          cola.siguiente = nodo;
      }
      nodo.anterior = cola;
      cola = nodo;
      longitud++;
  }

  public boolean contiene(T elemento){

    for (T  e: this) {
      if (e.equals(elemento)) {
        return true;
      }
    }
      return false;
  }

  public void eliminar(T elemento) throws NoSuchElementException{

    if (esVacia()) {
      return;
    }

    Nodo aux = cabeza;
    while (aux != null && !aux.elemento.equals(elemento)) {
      aux = aux.siguiente;
    }

    if (aux != null) {
      if (aux == cabeza) {
        if (longitud == 1) {
          vaciar();
        }
        if (aux == cabeza) {
          cabeza.siguiente.anterior = null;
          cabeza = cabeza.siguiente;
          longitud--;
        } else if (aux == cola) {
          cola.anterior.siguiente = null;
          cola = cola.anterior;
        }
        else {
          aux.anterior.siguiente = aux.siguiente;
          aux.siguiente.anterior = aux.anterior;
          longitud--;
        }
      }
    }
  }

  public int indiceDe(T elemento){
      int indice = 0;
      for (T  e : this) {
        if (e.equals(elemento)) {
          indice++;
          return indice;
        }
      }
      return  -1;
  }

  public Nodo buscar(T elemento) {
      if (elemento == null) {
          return null;
      }
      Nodo aux = cabeza;
      while (aux != null) {
          if (aux.elemento.equals(elemento)) {
              return aux;
          }
          aux = aux.siguiente;
      }
      return null;
  }

  public T getElemento(int i)throws IndexOutOfBoundsException{

    if (longitud <= 1) {
      throw new IndexOutOfBoundsException();
    }

    Iterator it = iterator();
    int posicion = 0;
    T elemento = null;
    while (posicion < i) {
      elemento = (T) it.next();
      posicion++;
    }
      return elemento;
  }

  public Lista<T> reversa(){
    Lista<T> lista = new Lista<>();
    for (T e : this) {
      lista.agregarAlInicio(e);
    }
    return lista;
  }

  public Lista<T> copia() {
      Lista<T> lista = new Lista<T>();
      for (T elemento : this) {
          lista.agregar(elemento);
      }
      return lista;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
      if (o == null)
          return false;
      if (o instanceof Lista<?>) {
          Lista<T> lista = (Lista<T>) o;
          if (lista.longitud == this.longitud) {
              // Se iteran las dos listas
              Iterator<T> iterador1 = this.iterator();
              Iterator<T> iterador2 = lista.iterator();
              while (iterador1.hasNext()) {
                  if (!iterador1.next().equals(iterador2.next())) {
                      return false;
                  }
              }
              return true;
          }
      }
      return false;
  }

  @Override
  public java.util.Iterator iterator(){
      return new IteradorLista(this);
  }


  public static <T extends Comparable<T>> Lista<T> mergesort(Lista<T> l) {
      if (l.longitud < 2)
          return l;
      Lista<T> l1 = new Lista<T>();
      Lista<T> l2 = new Lista<T>();
      int contador = 0;
      for (T elemento : l) {
          if (contador++ < l.longitud / 2) {
              l1.agregar(elemento);
          } else {
              l2.agregar(elemento);
          }
      }
      return merge(mergesort(l1), mergesort(l2));
  }

  private static <T extends Comparable<T>> Lista<T> merge(Lista<T> l1, Lista<T> l2) {
      Lista<T> l = new Lista<T>();
      Lista<T>.Nodo aux1 = l1.cabeza;
      Lista<T>.Nodo aux2 = l2.cabeza;

      // Se recorren los nodos de las listas
      while (aux1 != null && aux2 != null) {
          if (aux1.elemento.compareTo(aux2.elemento) <= 0) {
              l.agregar(aux1.elemento);
              aux1 = aux1.siguiente;
          } else {
              l.agregar(aux2.elemento);
              aux2 = aux2.siguiente;
          }
      }

      while (aux1 != null) {
          T elemento = aux1.elemento;
          l.agregar(elemento);
          aux1 = aux1.siguiente;

      }
      while (aux2 != null) {
          T elemento = aux2.elemento;
          l.agregar(elemento);
          aux2 = aux2.siguiente;
      }

      return l;
  }


  @Override
  public String toString() {
      String s = "[";
      Iterator<T> it = iterator();
      while(it.hasNext()){
          s += it.next();
          if(it.hasNext()){
              s += ",";
          }
      }

      s += "]";
      return s;
  }







//ultimo
}
