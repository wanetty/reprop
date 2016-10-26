import java.util.ArrayList;
import java.util.Date;


public class Documento {

	private Frase titulo;
	private Frase autor;
	private Frase tema;
	private java.util.Date fecha;
	private ArrayList<Frase> contenido;
	
	//constructoras
	public Documento(){
		titulo = new Frase();
		autor = new Frase();
		tema = new Frase();
		fecha = new Date();
		contenido = new ArrayList<Frase>();
	}
	/*
	public Documento(Frase ti, Frase a, Frase te, Date f, ArrayList<Frase> c){
		titulo = ti;
		autor = a;
		tema = te;
		fecha = f;
		contenido = c;
	}
	//modificadora
	
	public void añadir_titulo(Frase t){
		titulo = t;
	}
	
	public void añadir_autor(Frase a){
		autor = a;
	}
	
	public void añadir_tema(Frase t){
		tema = t;
	}
	*/
	
	//consultoras
	
	public Frase consulta_titulo() {
		return titulo;
	}
	
	public Frase consulta_autor() {
		return autor;
	}
	
	public Frase consulta_tema() {
		return tema;
	}
	public Date consulta_fecha(){
		return fecha;
	}
	public int consulta_num_frases(){
		return contenido.size();
	}
	
	public Frase consulta_frase(int i){
		if (i < contenido.size()) return contenido.get(i);
	}
	
	//modificadoras
	
	public void borrar_frase(int i){
		if (i < contenido.size()) contenido.remove(i);
	}
	
	public void añadir_frase(int i, Frase f){
		if (i < contenido.size()) contenido.add(i, f);
		else if (i == contenido.size()) contenido.add(f);
	}
}
