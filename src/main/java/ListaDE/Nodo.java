package ListaDE;

public class Nodo<T> {
    private Nodo<T> anterior;
    private T valor;
    private Nodo<T> siguiente;
    
    public Nodo(T valor){
        this.valor = valor;
    }

    public T valor(){
        return this.valor;
    }

    public Nodo<T> getAnterior(){
        return this.anterior;
    }

    public Nodo<T> getSiguiente(){
        return this.siguiente;
    }

    public void setAnterior(Nodo<T> nodo){
        this.anterior = nodo;
    }

    public void setSiguiente(Nodo<T> nodo){
        this.siguiente = nodo;
    }
}
