package ListaDE;

public class ListaDE<T> implements Secuencia<T>{

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int longitud;

    public ListaDE() {
        primero = null;
        ultimo = null;
        longitud = 0;
    }
    
    public int longitud() {
        return this.longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo<T> nuevo = new Nodo<T>(elem);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        }else{
            this.primero.setAnterior(nuevo);
            nuevo.setSiguiente(this.primero);
            this.primero = nuevo;
        }
        this.longitud++;
    }

    public void agregarAtras(T elem) {
        Nodo<T> nuevo = new Nodo<T>(elem);
        
        if (this.primero == null) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        }else{
            this.ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(this.ultimo);
            this.ultimo = nuevo;
        }
        this.longitud++;
    }

    public Nodo<T> obtenerNodo(int i){
        Nodo<T> actual = this.primero;
        for(int j = 0;  j < i ; j++){
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public T obtener(int i) {
        return this.obtenerNodo(i).valor();
    }

    public void eliminar(int i) {
        Nodo<T> actual = this.obtenerNodo(i);

        if (this.longitud() == 1 && i == 0) {
            this.primero = null;
            this.longitud--;
            return;
        }
        if (actual.getSiguiente() == null) {
            actual.getAnterior().setSiguiente(null);
            this.longitud--;
            return;
        }
        if (actual.getAnterior() == null) {
            actual.getSiguiente().setAnterior(null);
            this.primero = actual.getSiguiente();
            this.longitud--;
            return;
        }
        actual.getAnterior().setSiguiente(actual.getSiguiente());
        actual.getSiguiente().setAnterior(actual.getAnterior());

        this.longitud--;
    }
    //Notar que si el nodo no pertece a la lista se rompe la longitud
    public void eliminarNodo(Nodo<T> nodo){
        Nodo<T> anterior = nodo.getAnterior();
        Nodo<T> siguiente = nodo.getSiguiente();
        if (longitud == 1) {
            this.primero = null;
            this.ultimo = null;
            this.longitud--;
            return;
        }
        if (anterior != null) {
            anterior.setSiguiente(siguiente);
        }
        if (siguiente != null) {
            siguiente.setAnterior(anterior);
        }
        this.longitud--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo<T> actual = obtenerNodo(indice);
        actual.setValor(elem);
    }

    public ListaDE(ListaDE<T> lista) {
        Nodo<T> actual = lista.primero;
        while (actual != null) {
            T v = actual.valor();
            this.agregarAtras(v);
            actual = actual.getSiguiente();
        }
        this.longitud = lista.longitud();
    }
    
    @Override
    public String toString() {
        StringBuffer valor = new StringBuffer("["+primero.valor());
        Nodo<T> actual = primero.getSiguiente();
        while (actual != null) {
            valor.append(", "+actual.valor());
            actual = actual.getSiguiente();
        }
        valor.append("]");
        return valor.toString();
    }

    private class ListaIterador implements Iterador<T> {
    	private int indice;
        private Nodo<T> actual;

        public ListaIterador (){
            this.indice = 0;
            this.actual = primero;
        }

        public boolean haySiguiente() {
	        boolean existeSiguiente = indice < longitud;
            return existeSiguiente;
        }
        
        public boolean hayAnterior() {
            if (longitud == 0) {
                return false;
            }
            if (longitud == indice) {
                return true;
            }
	        boolean existeAnterior = indice > 0 && indice <= longitud ;
            return existeAnterior;
        }

        public T siguiente() {
	        return sigNodo().valor();
        }

        public Nodo<T> sigNodo(){
            Nodo<T> res = actual;
            if (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            this.indice++;
            return res;
        }
        
        public T anterior() {
            Nodo<T> res = null;
            if (indice == longitud) {
                res = actual;
            }else{
                res = actual.getAnterior();
                actual = actual.getAnterior();
            }
            this.indice--;
            return res.valor();
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }
    
}


