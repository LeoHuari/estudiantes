package aed;

import java.util.ArrayList;

import Heaps.*;
import ListaDE.*;

public class Berretacoin {
    private Heap<Usuario> heapUsuarios;
    private ArrayList<Heap<Usuario>.HandleHeap> handlerUsers;
    private Heap<HandleListaDE<Transaccion>> heapTransacciones;
    private ListaDE<Transaccion> listaTransOrdenadas;
    private int suma;
    private int cantTransacciones;
    private ListaDE<Bloque> blockchain;

    public Berretacoin(int n_usuarios){
        Usuario[] lista = new Usuario[n_usuarios];
        
        for(int i = 0; i < n_usuarios; i++){
            Usuario user = new Usuario(i+1);
            lista[i] = user;
        }
        
        this.heapUsuarios = new Heap<>(lista);

        this.handlerUsers = heapUsuarios.getHandles();
        
        this.blockchain = new ListaDE<Bloque>();

        this.suma = 0;
        this.cantTransacciones = 0;
    }

    public void agregarBloque(Transaccion[] transacciones){
        this.listaTransOrdenadas = new ListaDE<Transaccion>();
        int id_comprador = 0;
        int id_vendedor = 0;
        int monto = 0;
        this.cantTransacciones = 0;
        this.suma = 0;
        /*
        Se agregan las transacciones a listaTransOrdenadas(Lista de transacciones enlazada ordenada)
        Utilizando los handles se actualiza el dinero de los usuarios de acuerdo a la accion realizada
        y se actualiza su posicion en el heap
        Al mismo tiempo se lleva el conteo de transacciones y la suma de montos
        */
        for(int i = 0; i<transacciones.length; i++){

            listaTransOrdenadas.agregarAtras(transacciones[i]);
            monto = transacciones[i].monto();

            Heap<Usuario>.HandleHeap handle_user = null;
            if (transacciones[i].id_comprador() != 0) {

                id_comprador = transacciones[i].id_comprador();

                handle_user = this.handlerUsers.get(id_comprador-1);
                handle_user.getValor().comprar(monto);

                this.heapUsuarios.actualizar(handle_user);

                this.suma = this.suma + transacciones[i].monto();
                this.cantTransacciones++;
            }

            id_vendedor = transacciones[i].id_vendedor();

            handle_user = this.handlerUsers.get(id_vendedor-1);
            handle_user.getValor().vender(monto);

            this.heapUsuarios.actualizar(handle_user);

            
        }
        
        
        /*
        Utilizando un iterador de recorre la lista enlazada de transacciones creando handles para cada nodo
        guardando esos handles en un arrayList para luego ser convertidos en un heap (el heapTransacciones)
        */
        Iterador<Transaccion> iterador = listaTransOrdenadas.iterador();

        ArrayList<HandleListaDE<Transaccion>> lista = new ArrayList<>(transacciones.length);

        while (iterador.haySiguiente()) {
            Nodo<Transaccion> nodoTransaccion = iterador.sigNodo();
            HandleListaDE<Transaccion> handleTransaccion = new HandleListaDE<>(nodoTransaccion);
            lista.add(handleTransaccion);
        }

        this.heapTransacciones = new Heap<>(lista);

        //Por ultimo se crea un bloque con la lista enlazada de transacciones, un id que es la longitud de la blockchain y se lo agrega
        Bloque b = new Bloque(this.blockchain.longitud(), this.listaTransOrdenadas);
        this.blockchain.agregarAtras(b);
    }

    public Transaccion txMayorValorUltimoBloque(){
        return this.heapTransacciones.ver().getValor();
    }

    public Transaccion[] txUltimoBloque(){

        int longitud = this.listaTransOrdenadas.longitud();
        Transaccion[] res = new Transaccion[longitud];

        Iterador<Transaccion> it = this.listaTransOrdenadas.iterador();
        int i = 0;

        while (it.haySiguiente()) {
            res[i] = it.siguiente();
            i++;
        }

        return res;
    }

    public int maximoTenedor(){
        return this.heapUsuarios.ver().id();
    }

    public int montoMedioUltimoBloque(){
        int promedio = 0;
        if (cantTransacciones != 0) {
            promedio = suma / cantTransacciones;
        }
        return promedio;
    }

    public void hackearTx(){
        HandleListaDE<Transaccion> handle = this.heapTransacciones.extraer();

        Transaccion t = handle.getValor();
        int monto = t.monto();
        int id_comprador = t.id_comprador();
        int id_vendedor = t.id_vendedor();

        //Se devuelve el monto de la transaccion a los usuarios correspondientes
        if (id_comprador != 0) {
            Heap<Usuario>.HandleHeap handleComprador = this.handlerUsers.get(id_comprador-1);
            handleComprador.getValor().vender(monto);
            this.heapUsuarios.actualizar(handleComprador);
        }
        
        Heap<Usuario>.HandleHeap handleVendedor = this.handlerUsers.get(id_vendedor-1);
        handleVendedor.getValor().comprar(monto);
        this.heapUsuarios.actualizar(handleVendedor);

        if (cantTransacciones != 0) {
            this.suma = this.suma - monto;
            this.cantTransacciones--;
        }

        //Eliminacion de la transaccion utilizando los nodos guardados en el handle
        this.listaTransOrdenadas.eliminarNodo(handle.getNodo());
    }

    //Metodos auxiliares

    public ArrayList<Usuario> getHeapUsuarios(){
        return this.heapUsuarios.getHeap();
    }

    public Heap<Usuario> getHeap(){
        return this.heapUsuarios;
    }

    public ArrayList<HandleListaDE<Transaccion>> getHeapTransacciones(){
        return this.heapTransacciones.getHeap();
    }

    public ArrayList<Heap<Usuario>.HandleHeap> getHandleUsers(){
        return this.handlerUsers;
    }
}
