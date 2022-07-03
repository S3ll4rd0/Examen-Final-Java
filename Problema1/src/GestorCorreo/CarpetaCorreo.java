package GestorCorreo;

public class CarpetaCorreo {

	private Correo [] listaCorreos = new Correo[40];
	private int numCorreos = 0;
	
	public CarpetaCorreo() {
		this.listaCorreos = new Correo[40];
		this.numCorreos = 0;
	}
	
	public void añadirC(Correo c) {
		Correo cor = c;
		listaCorreos[numCorreos] = cor;
		numCorreos++;
	}
	
	public Correo[] getListaCorreos() {
		return listaCorreos;
	}
	
	public Correo borrarC(int i) {
		Correo correoBorrado = this.listaCorreos[i];
		Correo [] nuevaLista = new Correo[40];

        for (int j = 0; j < listaCorreos.length; j++) {
        	if (listaCorreos[j] != listaCorreos[i]) {
        		nuevaLista[j] = listaCorreos[j];
        	} else {
        		correoBorrado = this.listaCorreos[i];
        	}
        }
        
        for (int q = 0; q < nuevaLista.length; q++) {
        	this.listaCorreos[q] = nuevaLista[q];
        }
		return correoBorrado;
	}
	
	public Correo buscarC(String origen) {
		Correo correoEncontrado = new Correo("", "", "", "");
		for (Correo c : listaCorreos) {
			if (c.elOrigen() == origen) {
				correoEncontrado = c;
			} else {
				correoEncontrado = null;
			}
		}
		return correoEncontrado;
	}

	@Override
	public String toString() {
		String text = "";
		for (int i = 0; i < listaCorreos.length; i++) {
			text += "Correo nº: " + i + "\n" + listaCorreos[i].toString() + "\n\n";
		}
		return text;
	}
	
	
}
