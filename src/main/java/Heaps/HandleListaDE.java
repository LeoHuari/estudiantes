package Heaps;


import ListaDE.Nodo;

public class HandleListaDE<T extends Comparable<T>> implements Handle, Comparable<HandleListaDE<T>>{
    private Nodo<T> anterior;
    private Nodo<T> siguiente;
    private Nodo<T> actual;
    
    public HandleListaDE(Nodo<T> anterior, Nodo<T> siguiente, Nodo<T> actual){
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.actual = actual;
    }

    public T getValor (){
        return this.actual.valor();
    }

    public Nodo<T> getAnterior(){
        return this.anterior;
    }

    public Nodo<T> getSiguiente(){
        return this.siguiente;
    }

    @Override
    public int compareTo(HandleListaDE<T> o) {
        return this.getValor().compareTo(o.getValor());
    }

}
