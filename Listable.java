

public interface Listable<T> {

  // Método que devuelve la posición en la lista que tiene la primera
  public int indiceDe(T elemento);

  // Método que nos dice en qué posición está un elemento en la lista
  public T getElemento(int i)throws IndexOutOfBoundsException;

  //Método que devuelve una copia de la lista, pero en orden inverso
  public Lista<T> reversa();

  //Método que devuelve una copi exacta de la lista
  public Lista<T> copia();

  // Método que nos dice si las lista está vacía.
  public boolean esVacia();

  //Método para eliminar todos los elementos de una lista
  public void vaciar();

  //Método para agregar un elemento a la lista.
  public void agregar(T elemento);

  //Método para verificar si un elemento pertenece a la lista.
  public boolean contiene(T elemento);

  //Método para eliminar un elemento de la lista.
  public void eliminar(T elemento);

  public int longitud();


}
