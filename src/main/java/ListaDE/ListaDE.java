package ListaDE;

public class ListaDE<T> implements Secuencia<T>{

    private Nodo<T> primero;

    public ListaDE() {
        primero = null;
    }
    
    public int longitud() {
        int longitud = 0;
        Nodo<T> actual = primero;
        while (actual != null) {
            actual = actual.getSiguiente();
            longitud++;
        }
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo<T> nuevo = new Nodo<T>(elem);
        
        if (primero == null) {
            primero = nuevo;
        }else{
            this.primero.anterior = nuevo;
            nuevo.siguiente = this.primero;
            this.primero = nuevo;
        }
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        Nodo actual = this.primero;
        nuevo.siguiente = null;
        if (this.primero == null) {
            this.primero = nuevo;
            return;
        }
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        nuevo.anterior = actual;
        actual.siguiente = nuevo;
    }

    public Nodo obtenerNodo(int i){
        Nodo actual = this.primero;
        for(int j = 0;  j < i ; j++){
            actual = actual.siguiente;
        }
        return actual;
    }

    public T obtener(int i) {
        return this.obtenerNodo(i).valor;
    }

    public void eliminar(int i) {
        Nodo actual = this.obtenerNodo(i);

        if (this.longitud() == 1 && i == 0) {
            this.primero = null;
            return;
        }
        if (actual.siguiente == null) {
            actual.anterior.siguiente = null;
            return;
        }
        if (actual.anterior == null) {
            actual.siguiente.anterior = null;
            this.primero = actual.siguiente;
            return;
        }
        actual.anterior.siguiente = actual.siguiente;
        actual.siguiente.anterior = actual.anterior;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = obtenerNodo(indice);
        actual.valor = elem;
    }

    public ListaDE(ListaDE<T> lista) {
        Nodo actual = lista.primero;
        while (actual != null) {
            T v = actual.valor;
            this.agregarAtras(v);
            actual = actual.siguiente;
        }
    }
    
    @Override
    public String toString() {
        StringBuffer valor = new StringBuffer("["+primero.valor);
        Nodo actual = primero.siguiente;
        while (actual != null) {
            valor.append(", "+actual.valor);
            actual = actual.siguiente;
        }
        valor.append("]");
        return valor.toString();
    }

    private class ListaIterador implements Iterador<T> {
    	private int indice;

        public ListaIterador (){
            this.indice = 0;
        }

        public boolean haySiguiente() {
	        boolean existeSiguiente = obtenerNodo(this.indice) != null;
            return existeSiguiente;
        }
        
        public boolean hayAnterior() {
            if (longitud() == 0) {
                return false;
            }
            if (longitud() == indice) {
                return true;
            }
	        boolean existeAnterior = obtenerNodo(this.indice).anterior != null;
            return existeAnterior;
        }

        public T siguiente() {
            int i = this.indice;
            this.indice = this.indice + 1;
	        return obtener(i);
        }
        

        public T anterior() {
            this.indice =this.indice - 1;
            return obtener(this.indice);
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }
}


