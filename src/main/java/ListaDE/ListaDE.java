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
            this.primero.setAnterior(nuevo);
            nuevo.setSiguiente(this.primero);
            this.primero = nuevo;
        }
    }

    public void agregarAtras(T elem) {
        Nodo<T> nuevo = new Nodo<T>(elem);
        Nodo<T> actual = this.primero;
        nuevo.setSiguiente(null);
        if (this.primero == null) {
            this.primero = nuevo;
            return;
        }
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        nuevo.setAnterior(actual);
        actual.setSiguiente(nuevo);
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
            return;
        }
        if (actual.getSiguiente() == null) {
            actual.getAnterior().setSiguiente(null);
            return;
        }
        if (actual.getAnterior() == null) {
            actual.getSiguiente().setAnterior(null);
            this.primero = actual.getSiguiente();
            return;
        }
        actual.getAnterior().setSiguiente(actual.getSiguiente());
        actual.getSiguiente().setAnterior(actual.getAnterior());
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
	        boolean existeAnterior = obtenerNodo(this.indice).getAnterior() != null;
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


