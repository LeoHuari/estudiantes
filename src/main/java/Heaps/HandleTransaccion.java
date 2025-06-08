package Heaps;


import ListaDE.Nodo;
import aed.Transaccion;

public class HandleTransaccion {
    private Nodo anterior;
    private Nodo siguiente;
    private Nodo actual;

    public HandleTransaccion(Nodo anterior, Nodo siguiente, Nodo actual){
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.actual = actual;
    }

    public Transaccion getValor (){
        return ((Transaccion)this.actual.valor());
    }

    public Nodo getAnterior(){
        return this.anterior;
    }

    public Nodo getSiguiente(){
        return this.siguiente;
    }

}
