package GestorCorreo;

public class Correo {

	private String origen;
	private String destino;
	private String asunto;
	private String mensaje;
	
	public Correo(String origen, String destino, String asunto, String mensaje) {
		this.origen = origen;
		this.destino = destino;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	
	public String elOrigen() {
		return origen;
	}

	@Override
	public String toString() {
		return "Origen: " + origen + "\nDestino: " + destino + "\nAsunto: " + asunto + "\nMensaje: " + mensaje;
	}
}
