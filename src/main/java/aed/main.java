package aed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Heaps.Heap;
import ListaDE.*;

public class main {
    public static void main(String[] args) {
        Integer[] n = {1,5,7,1,3,5,4};
        Heap<Integer> h = new Heap<>(n);

        System.out.println(h.getHeap());
        System.out.println(h.ver());
        h.extraer();
        System.out.println(h.ver());
        System.out.println(h.getHeap());
        Integer num = 9;
        h.agregar(num);
        System.out.println(h.getHeap());
    }
}
