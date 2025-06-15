package Heaps;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

    private ArrayList<T> cola;
    private ArrayList<HandleHeap> colaHandle;

    public Heap(){
        this.cola = new ArrayList<>();
        this.colaHandle = new ArrayList<>();
    }

    public Heap(T[] array){
        int cantElems = array.length;
        this.cola = new ArrayList<>(cantElems);
        this.colaHandle = new ArrayList<>(cantElems);
        for (int i = 0; i < cantElems; i++) {
            HandleHeap handle = new HandleHeap(i, array[i]);
            this.colaHandle.add(handle);
            this.cola.add(array[i]);
        }
        for (int i = this.cola.size() / 2 - 1; i >= 0; i--) {
            this.bajar(i);
        }
    }

    public Heap(ArrayList<T> lista){
        int cantElems = lista.size();
        this.cola = new ArrayList<>(cantElems);
        this.colaHandle = new ArrayList<>(cantElems);
        for(int i = 0; i < cantElems; i++){
            HandleHeap handle = new HandleHeap(i, lista.get(i));
            this.colaHandle.add(handle);
            this.cola.add(lista.get(i));
        }
        for (int i = this.cola.size() / 2 - 1; i >= 0; i--) {
            this.bajar(i);
        }
    }

    public void agregar(T elem){
        HandleHeap handle = new HandleHeap(0, elem);
        if (this.cola.size()==0) {
            this.colaHandle.add(handle);
            this.cola.add(elem);
        }else{
            this.colaHandle.add(handle);
            handle.setIndice(this.colaHandle.size()-1);
            this.cola.add(elem);
            int indice = this.cola.size()-1;
            subir(indice);
        }
    }

    private void subir(int index){
        int indexPadre = (index - 1) / 2;
        T hijo = this.cola.get(index);
        T padre = this.cola.get(indexPadre);
        HandleHeap handleHijo = this.colaHandle.get(index);
        HandleHeap handlePadre = this.colaHandle.get(indexPadre);
        while (index > 0 && hijo.compareTo(padre) > 0) {
            this.colaHandle.set(index, handlePadre);
            this.colaHandle.set(indexPadre, handleHijo);
            this.cola.set(index, padre);
            this.cola.set(indexPadre, hijo);
            index = indexPadre;
            indexPadre = (index - 1) / 2;
            handlePadre = this.colaHandle.get(indexPadre);
            padre = this.cola.get(indexPadre);
        }
    }

    private void bajar(int index){
        int hijoIzq = 2 * index + 1;
        int hijoDer = 2 * index + 2;
        int mayor = index;

        if (hijoIzq < this.cola.size() && this.cola.get(hijoIzq).compareTo(this.cola.get(mayor)) > 0) {
            mayor = hijoIzq;
        }
        if (hijoDer < this.cola.size() && this.cola.get(hijoDer).compareTo(this.cola.get(mayor)) > 0) {
            mayor = hijoDer;
        }
    
        if (mayor != index) {
            T temp = this.cola.get(index);
            HandleHeap handleaux = this.colaHandle.get(index);
            //Se cambian los indices/punteros dentro de los handles que se van a intercambiar
            handleaux.setIndice(mayor);
            colaHandle.get(mayor).setIndice(index); 
            //Se intercambian los handles y elementos
            this.colaHandle.set(index, this.colaHandle.get(mayor));
            this.colaHandle.set(mayor, handleaux);
            this.cola.set(index, this.cola.get(mayor));
            this.cola.set(mayor, temp);
            bajar(mayor);
        }
    }

    public T extraer(){
        T res = this.cola.get(0);
        this.cola.set(0, this.cola.get(this.cola.size() - 1));
        this.cola.remove(this.cola.size() - 1);
        this.bajar(0);
        return res;
    }
    
    public T ver(){
        return this.cola.get(0);
    }

    public int cardinalidad(){
        return this.cola.size();
    }

    public boolean contiene(T elem){
        return this.cola.contains(elem);
    }

    public boolean esVacio(){
        return this.cola.isEmpty();
    }

    public void vaciar() {
        this.cola.clear();
    }

    public ArrayList<T> getHeap(){
        return this.cola;
    }

    public ArrayList<HandleHeap> getHandles(){
        int cantElems = this.colaHandle.size();
        ArrayList<HandleHeap> res = new ArrayList<>(cantElems);
        
        for(int i = 0; i < cantElems; i++){
            res.add(this.colaHandle.get(i));
        }

        return res;
    }

    public void actualizar(HandleHeap handle){
        int index = handle.getIndice();
        subir(index);
        bajar(index);
    }

    public class HandleHeap implements Handle{
        int indice;
        T valor;

        public HandleHeap (int indice, T valor){
            this.indice = indice;
            this.valor = valor;
        }

        public int getIndice(){
            return this.indice;
        }

        public T getValor(){
            return this.valor;
        }

        public void setIndice(int i){
            this.indice = i;
        }

        public void setValor(T v){
            this.valor = v;
        }

        @Override
        public String toString(){
            return "indice = "+indice+"- valor {"+valor+"}";
        }
    }

}
