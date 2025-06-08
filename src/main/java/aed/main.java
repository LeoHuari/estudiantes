package aed;

import ListaDE.*;

public class main {
    public static void main(String[] args) {
        int n = 15;
        Nodo<Integer> nodo = new Nodo<Integer>(n);
        int a = 20;
        Nodo<Integer> nodo2 = new Nodo<Integer>(a);
        Nodo<Integer> nodo3 =null;
        System.out.println(nodo.getAnterior());
        System.out.println(nodo.getSiguiente());
        nodo.setAnterior(nodo2);
        System.out.println(nodo.getAnterior().valor());
        System.out.println(nodo3.valor());
    }
}
