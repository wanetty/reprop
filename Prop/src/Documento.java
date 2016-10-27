import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;


public class Documento {

	private Frase titulo = new Frase();
	private Frase autor = new Frase();
	private Frase tema = new Frase();
	private java.util.Date fecha = new Date();
	private ArrayList<Frase> contenido = new ArrayList<Frase>();
	//vector
	//constructoras
	public Documento(){
	
	}
	
	public Documento(Frase ti, Frase a, Frase te, ArrayList<Frase> c){
		titulo = ti;
		autor = a;
		tema = te;
		contenido = c;
	}
	
	//modificadora
	
	public void set_titulo(Frase t){
		titulo = t;
	}
	
	public void set_autor(Frase a){
		autor = a;
	}
	
	public void set_tema(Frase t){
		tema = t;
	}
	
	//consultoras
	
	public Frase get_titulo() {
		return titulo;
	}
	
	public Frase get_autor() {
		return autor;
	}
	
	public Frase get_tema() {
		return tema;
	}
	public Date get_fecha(){
		return fecha;
	}
	public int get_num_frases(){
		return contenido.size();
	}
	
	public Frase get_frase(int i){
		return contenido.get(i);
	}
	
	//modificadoras
	
	public void borrar_frase(int i){
		if (i < contenido.size()) contenido.remove(i);
	}
	
	public void set_frase(int i, Frase f){
		if (i < contenido.size()) contenido.add(i, f);
		else if (i == contenido.size()) contenido.add(f);
	}
}
