package aed;

public class Bloque {
    private int id;
    private Transaccion[] transacciones;

    public Bloque(int id, Transaccion[] lista){
        this.id = id;
        this.transacciones = lista;
    }
}
