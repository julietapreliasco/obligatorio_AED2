package tads.cola;

public class Cola<T> implements ICola<T>{

	private Nodo<T> inicio;
	private Nodo<T> fin;
	private int largo;

	public Cola() {
	}

	public void encolar(T dato) {
		if (esVacia()) {
			inicio = new Nodo<T>(dato);
			fin = inicio;
		} else {
			fin.setSig(new Nodo<T>(dato));
			fin = fin.getSig();
		}
		this.largo++;
	}

	// Pre: !esVacia()
	public T desencolar() {
		T dato = this.inicio.getDato();
		inicio = inicio.getSig();
		this.largo--;
		if(this.inicio == null) {
			fin = null;
		}
		return dato;
	}

	@Override
	public T peek() {
		return inicio.getDato();
	}

	public boolean esVacia() {
		return inicio == null;
	}

	@Override
	public String serializar(String separador) {
		//#TODO completar luego.
		return "";
	}

	protected static class Nodo<T> {
		private T dato;
		private Nodo<T> sig;

		public Nodo(T dato, Nodo<T> sig) {
			this.dato = dato;
			this.sig = sig;
		}

		public Nodo(T dato) {
			this.dato = dato;
		}

		public T getDato() {
			return dato;
		}

		public void setDato(T dato) {
			this.dato = dato;
		}

		public Nodo<T> getSig() {
			return sig;
		}

		public void setSig(Nodo<T> sig) {
			this.sig = sig;
		}

		@Override
		public String toString() {
			return dato + "";
		}

	}

}
