package Heaps;

public class HandleHeap<T> implements Handle {
    private int indice;
    private T valor;

    public HandleHeap(int indice, T valor){
        this.indice = indice;
        this.valor = valor;
    }

    public int getIndice(){
        return this.indice;
    }

    public T getValor(){
        return this.valor;
    }

    public void setIndice(int indice){
        this.indice = indice;
    }

    public void setValor(T valor){
        this.valor = valor;
    }
}
