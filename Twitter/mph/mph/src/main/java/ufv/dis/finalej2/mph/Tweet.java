package ufv.dis.finalej2.mph;

public class Tweet {
	String autor;
	String mensaje;
	String fecha;
	
	public Tweet()
	{
		
	}
	public Tweet(String name, String type, String number) {
		this.autor= name;
		this.mensaje = type;
		this.fecha = number;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String name) {
		this.autor = name;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String type) {
		this.mensaje = type;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String number) {
		this.fecha = number;
	}
		
}
