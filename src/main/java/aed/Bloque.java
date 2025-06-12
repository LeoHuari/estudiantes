package aed;

import ListaDE.ListaDE;

public class Bloque {
    private int id;
    private ListaDE<Transaccion> transacciones;

    public Bloque(int id, ListaDE<Transaccion> lista){
        this.id = id;
        this.transacciones = lista;
    }
}
