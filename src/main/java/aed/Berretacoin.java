package aed;

import java.util.ArrayList;

import Heaps.*;
import ListaDE.*;

public class Berretacoin {
    private Heap<Usuario> heapUsuarios;
    private HandleHeap<Usuario>[] handlerUsers;
    private Heap<HandleListaDE<Transaccion>> heapTransacciones;
    private ListaDE<Transaccion> listaTransOrdenadas;
    private Double promedio;
    private ListaDE<Bloque> blockchain;

    public Berretacoin(int n_usuarios){
        Usuario[] lista = new Usuario[n_usuarios];
        this.handlerUsers = new HandleHeap[n_usuarios];
        
        for(int i = 0; i < n_usuarios; i++){
            Usuario user = new Usuario(i+1);
            HandleHeap<Usuario> hu = new HandleHeap<Usuario>(i, user);
            this.handlerUsers[i] = hu;
            lista[i] = user;
        }
        this.heapUsuarios = new Heap<>(lista);
    }

    public void agregarBloque(Transaccion[] transacciones){
        this.listaTransOrdenadas = new ListaDE<Transaccion>();
        int id_comprador = 0;
        int id_vendedor = 0;
        int monto = 0;
        for(int i = 0; i<transacciones.length; i++){
            listaTransOrdenadas.agregarAtras(transacciones[i]);
            monto = transacciones[i].monto();
            if (transacciones[i].id_comprador() != 0) {
                id_comprador = transacciones[i].id_comprador();
                handlerUsers[id_comprador-1].getValor().comprar(monto);
                //heapUsuarios.actualizar(handlerUsers[id_comprador]);
            }
            id_vendedor = transacciones[i].id_vendedor();
            handlerUsers[id_vendedor-1].getValor().vender(monto);
            //heapUsuarios.actualizar(handlerUsers[id_vendedor]);
        }

        Iterador<Transaccion> iterador = listaTransOrdenadas.iterador();

        ArrayList<HandleListaDE<Transaccion>> lista = new ArrayList<>(transacciones.length);

        while (iterador.haySiguiente()) {
            Nodo<Transaccion> nt = iterador.sigNodo();
            HandleListaDE<Transaccion> ht = new HandleListaDE<>(nt.getAnterior(), nt.getSiguiente(), nt);
            lista.add(ht);
        }

        this.heapTransacciones = new Heap<>(lista);

        Bloque b = new Bloque(this.blockchain.longitud(), this.listaTransOrdenadas);
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

    //Metodos auxiliares

    public ArrayList<Usuario> getHeapUsuarios(){
        return this.heapUsuarios.getHeap();
    }

    public ArrayList<HandleListaDE<Transaccion>> getHeapTransacciones(){
        return this.heapTransacciones.getHeap();
    }
}
