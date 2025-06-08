package aed;

public class Nodo<T> {
    Nodo anterior;
    T valor;
    Nodo siguiente;
    
    public Nodo(T valor){
        this.valor = valor;
    }
}
