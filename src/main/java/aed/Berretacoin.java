package aed;

import java.util.ArrayList;

import Heaps.*;
import ListaDE.ListaDE;

public class Berretacoin {
    private Heap<Usuario> heapUsuarios;
    private ArrayList<HandleHeap<Usuario>> handlesUsers;
    private Heap<HandleListaDE<Transaccion>> heapTransacciones;
    private ListaDE<Transaccion> listaTransOrdenadas;
    private Double promedio;
    private ListaDE<Bloque> blockchain;

    public Berretacoin(int n_usuarios){
        throw new UnsupportedOperationException("Implementar!");
    }

    public void agregarBloque(Transaccion[] transacciones){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion txMayorValorUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion[] txUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int maximoTenedor(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int montoMedioUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }
}
