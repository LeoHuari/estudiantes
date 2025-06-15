package Heaps;


import ListaDE.Nodo;

public class HandleListaDE<T extends Comparable<T>> implements Handle, Comparable<HandleListaDE<T>>{
    private Nodo<T> actual;
    
    public HandleListaDE(Nodo<T> actual){
        this.actual = actual;
    }

    public T getValor (){
        return this.actual.valor();
    }

    public Nodo<T> getNodo(){
        return actual;
    }

    @Override
    public int compareTo(HandleListaDE<T> o) {
        return this.getValor().compareTo(o.getValor());
    }

    @Override
    public String toString(){
        return this.getValor().toString();
    }
}
