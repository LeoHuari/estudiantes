package Heaps;


import ListaDE.Nodo;
import aed.Transaccion;

public class HandleTransaccion {
    private Nodo<Transaccion> anterior;
    private Nodo<Transaccion> siguiente;
    private Nodo<Transaccion> actual;
    
    public HandleTransaccion(Nodo<Transaccion> anterior, Nodo<Transaccion> siguiente, Nodo<Transaccion> actual){
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.actual = actual;
    }

    public Transaccion getValor (){
        return this.actual.valor();
    }

    public Nodo<Transaccion> getAnterior(){
        return this.anterior;
    }

    public Nodo<Transaccion> getSiguiente(){
        return this.siguiente;
    }

}
