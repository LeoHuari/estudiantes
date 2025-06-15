package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    @Override
    public int compareTo(Transaccion otro) {
        int res = 0;
        boolean mismoMonto = this.monto - otro.monto == 0;

        if (mismoMonto) {
            res = this.id - otro.id;
        }else{
            res = this.monto - otro.monto;
        }

        return res;
    }

    @Override
    public boolean equals(Object otro){
        boolean esTransaccion = otro.getClass() == this.getClass();
        boolean igualdad = false;
        if (esTransaccion) {
            Transaccion oTrans = (Transaccion)otro;
            igualdad = this.id == oTrans.id()
                && this.id_comprador == oTrans.id_comprador() 
                && this.id_vendedor == oTrans.id_vendedor()
                && this.monto == oTrans.monto();
        }

        return igualdad;
    }

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }

    public int id(){
        return id;
    }
    
}