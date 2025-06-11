package Heaps;

import java.util.ArrayList;
import java.util.Arrays;

public class Heap<T extends Comparable<T>> {

    private ArrayList<T> cola;

    public Heap(){
        this.cola = new ArrayList<>();
    }

    public Heap(T[] array){
        int cantElems = array.length;
        this.cola = new ArrayList<>(cantElems);
        for (int i = 0; i < cantElems; i++) {
            this.cola.add(array[i]);
            System.out.println(array[i]);
        }
        System.out.println(this.cola);
        for (int i = this.cola.size() / 2 - 1; i >= 0; i--) {
            this.bajar(i);
        }
    }

    public void agregar(T elem){
        if (this.cola.size()==0) {
            this.cola.add(elem);
        }else{
            this.cola.add(elem);
            int indice = this.cola.size()-1;
            subir(indice);
        }
    }

    private void subir(int index){
        int indexPadre = (index - 1) / 2;
        T hijo = this.cola.get(index);
        T padre = this.cola.get(indexPadre);
        while (index > 0 && hijo.compareTo(padre) > 0) {
            this.cola.set(index, padre);
            this.cola.set(indexPadre, hijo);
            index = indexPadre;
            indexPadre = (index - 1) / 2;
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
}
