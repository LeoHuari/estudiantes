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
    /*
     * Creacion de un array de n usuarios O(n), y luego hacer heapify con el algoritmo de floyd mantiene O(n)
     */
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

    /*
     * Por cada transaccion(t) se la agrega a una lista doblemente enlazada O(1) y se actualiza los montos de los usuarios O(log n)
     * Ademas por cada transaccion se suman los montos y se cuentan la cantidad de transacciones O(1)
     * Luego utilizando la lista enlazada de transacciones se crean handles de los nodos y se los añade a un ArrayList O(t)
     * Utilizando este array se crea un heap de handles de transacciones, costando O(t) por el algoritmo de floyd
     * Esto da O(t*log n) + O(t) + O(t) que es lo mismo que O(t*log n)
     */
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

    /*
     * Utilizando un maxheap de transacciones ordenadas por montos se cosigue O(1)
     */
    public Transaccion txMayorValorUltimoBloque(){
        return this.heapTransacciones.ver().getValor();
    }

    /*
     * Teniendo ya la lista enlazada ordenada de transacciones solo se necesita crear una copia
     * Lo cual cuesta O(t)
     */
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

    /*
     * Utilizando un maxheap de usuarios ordenados por monto se consigue O(1)
     */
    public int maximoTenedor(){
        return this.heapUsuarios.ver().id();
    }

    /*
     * Habiendo llevado el conteo de transacciones y sus montos previamente se logra O(1) haciendo una simple operacion matematica
     */
    public int montoMedioUltimoBloque(){
        int promedio = 0;
        if (cantTransacciones != 0) {
            promedio = suma / cantTransacciones;
        }
        return promedio;
    }

    /*
     * Al haber utilizado un heap de handles de transacciones podemos extraer la mayor transacciones en O(log t)
     * Al tener los handles de los usuarios podemos actualizar sus montos en O(log n)
     * Ademas al haber utilizando handles de listas enlazadas que contienen nodos de transacciones
     * Se puede utilizar el nodo para realizar una eliminacion en O(1)
     * Dando como resultado O(log t) + O(log n) + O(1) que es lo mismo que O(log t + log n)
     */
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


}
