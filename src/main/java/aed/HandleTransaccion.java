package aed;

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
        return this.actual.valor;
    }
}
