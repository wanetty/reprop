import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



public class Cjt_documentos {
	private ArrayList<Documento> conjDocumento = new ArrayList<Documento>();//arraylist que guarda los documentos resultados de una busqueda
	private Map<Frase, ArrayList<Frase>> por_autor = new HashMap<Frase,ArrayList<Frase>>();
	private Map<Frase, ArrayList<Frase>> por_tema = new HashMap<Frase,ArrayList<Frase>>();
	private Map<Date, ArrayList<Frase>> por_fecha = new HashMap<Date,ArrayList<Frase>>();
	
	//Constructora
	public Cjt_documentos() {}
	
	public Cjt_documentos(int prueba) {}
	
	//Consultoras
	//devuelve la lista de documentos resultados de una busqueda
	public ArrayList<Documento> devolver_documentos() {
		return conjDocumento;
	}
	
	//Altas
	private void string_to_arraylist(ArrayList<Frase> c, String s) throws IOException {
		String delimitadores= "[.;?!]";//faltan los puntos suspensivos
		String[] frasesseparadas = s.split(delimitadores);
		Frase aux=new Frase(s);
		c.add(aux);
	}
	
	public void alta_convencional(String text) throws IOException {
		String del="\\n";
		String[] aux = text.split(del);//aux contiene parrafos
		Frase ti = new Frase(aux[0]);
		Frase a = new Frase(aux[1]);
		Frase te= new Frase(aux[2]);
		ArrayList<Frase> c= new ArrayList<Frase>();
		for (int i=3; i<aux.length; ++i) {//el for es porque en aux a partir de la posicion 3 tenemos el contenido separado por parrafos 
			string_to_arraylist(c,aux[i]);
		}
		Documento d=new Documento(ti,a,te,c);
		//inacabada
	}
	public void alta_sin_fichero(){}
	public void alta_multiple(){}
	
	//Modificaciones
	public void borrar_palabra(){}
	public void escribir_palabra(){}
	
	//Bajas
	public void baja_individual(){}
	public void baja_multiple(){}
	
	//Busquedas
	public void busqueda_por_titulo(){}
	public void busqueda_por_tema(){}
	public void busqueda_por_fecha(){}
	public void busqueda_por_parecido(){}
	public void busqueda_por_relevancia(){}
	public void busqueda_por_booleana(){}
	public void busqueda_por_prefijo(){}
	public void busqueda_recientes(){}
	
}
