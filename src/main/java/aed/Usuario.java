package aed;

public class Usuario implements Comparable<Usuario>{
    private int id;
    private int dinero;
    
    public Usuario(int id){
        this.id = id;
        this.dinero = 0;
    }

    public int id(){
        return this.id;
    }

    public int dinero(){
        return this.dinero;
    }

    public void setDinero(int n){
        this.dinero = n;
    }

    public void comprar(int n){
        this.dinero = this.dinero - n;
    }

    public void vender(int n){
        this.dinero = this.dinero + n;
    }

    @Override
    public int compareTo(Usuario o) {
        int res = 0;
        boolean mismoDinero = this.dinero - o.dinero == 0;

        if (mismoDinero){
            res = -(this.id - o.id);
        }else{
            res = this.dinero - o.dinero;
        }
        
        return res;
    }

}
