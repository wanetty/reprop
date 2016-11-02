import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



public class Cjt_documentos {
	private ArrayList<Documento> conjDocumento = new ArrayList<Documento>();//arraylist que guarda todos los documentos
	private ArrayList<Documento> conjDocumento_res = new ArrayList<Documento>();//arraylist que guarda los documentos resultados de una busqueda
	private Map<Frase,ArrayList<Documento>> por_autor = new HashMap<Frase,ArrayList<Documento>>();
	private Map<Frase, ArrayList<Map<Frase,ArrayList<Documento>>>> por_titulo = new HashMap<Frase,ArrayList<Map<Frase,ArrayList<Documento>>>>();
	private Map<Frase, ArrayList<Documento>> por_tema = new HashMap<Frase,ArrayList<Documento>>();
	private Map<Calendar, ArrayList<Documento>> por_fecha = new HashMap<Calendar,ArrayList<Documento>>();
	
	//Constructora (Altas)
	public Cjt_documentos() {}
	
	private void string_to_arraylist(ArrayList<Frase> c, String s) throws IOException {
		String delimitadores= "[.;?!]";//faltan los puntos suspensivos
		String[] frasesseparadas = s.split(delimitadores);
		for (int i=0; i<frasesseparadas.length; ++i) {
			Frase aux=new Frase(frasesseparadas[i]);
			c.add(aux);
		}
	}
	
	private void alta(String text) throws IOException {
		//1.Crear los parametros para crear un documento
		String del="\\n";
		String[] aux = text.split(del);//aux contiene parrafos
		Frase ti = new Frase(aux[0]);
		Frase a = new Frase(aux[1]);
		Frase te= new Frase(aux[2]);
		ArrayList<Frase> c= new ArrayList<Frase>();
		for (int i=3; i<aux.length; ++i) {//el for es porque en aux a partir de la posicion 3 tenemos el contenido separado por parrafos 
			string_to_arraylist(c,aux[i]);
		}
		Calendar cal = null;
		Date f=cal.getTime();
		Documento d=new Documento(ti,a,te,c,f);
		conjDocumento.add(d);//anyado documento al conjunto
		
		//2.Rellenar las estructuras por_autor,por_titulo,por_tema,por_fecha
		if (!por_autor.containsKey(a)) {//si el autor es nuevo,anyade una lista nueva de documentos para el autor
			ArrayList<Documento> conjaux = new ArrayList<Documento>();
			conjaux.add(d);
			por_autor.put(a, conjaux);
		}
		else por_autor.get(a).add(d); //si autor ya existe,anyade un nuevo documento a la lista
		if (!por_titulo.containsKey(ti)){ //si titulo no exsite crear un titulo con el autor correspondiente
			ArrayList<Map<Frase,ArrayList<Documento>>> auxiliar = new ArrayList<Map<Frase,ArrayList<Documento>>>();
			auxiliar.add(por_autor);
			por_titulo.put(ti, auxiliar);
		}
		else {//si titulo ya existe anyadir el autor
			por_titulo.get(ti).add(por_autor);
		}
		if (!por_tema.containsKey(te)) {//si el tema es nuevo, anyade una lista nueva de documentos para este tema
			ArrayList<Documento> conjaux = new ArrayList<Documento>();
			conjaux.add(d);
			por_tema.put(te, conjaux);
		}
		else {//si el autor ya existia, anyade el documento a la lista
			por_tema.get(te).add(d);
		}
		//creo un nuevo calendario para guardar solo la fecha y no las horas
		int anyoaux=f.getYear();
		int mesaux=f.getMonth();
		int diaaux=f.getDate();
		Calendar nuevo = null;
		nuevo.set(anyoaux, mesaux, diaaux);
		if (!por_fecha.containsKey(nuevo)) {//si la fecha es nueva, anyade una lista nueva de documentos para este tema
			ArrayList<Documento> conjaux = new ArrayList<Documento>();
			conjaux.add(d);
			por_fecha.put(nuevo, conjaux);
		}
		else {//si el autor ya existia, anyade el documento a la lista
			por_fecha.get(nuevo).add(d);
		}
	}
	
	//creo que no hace falta porque es lo mismo para nosotros que el usuario importe un fichero o que escriba por si solo, hasta que no confirma(en la interficie) lo que escribe no guardamos lo que ha escrito 
	public void alta_sin_fichero(){}
	
	//Cuando sea una alta convencional n=0, cuando sea multiple n>0;
	public void alta_conv_mult(String text, int n) throws IOException{
		for (int i=0; i<n; ++i) alta(text);
	}
	
	//Modificaciones
	//de momento suponemos que desde el progama principal le pasara la posicion de la palabra en la frase
	//nota: hay que anyadir en el caso de usos que el usuario tendria que indicar el numero de la frase a la que pertenece la palabra que se quiere borrar y la palabra borrada.
	public void borrar_palabra(int numfras, Palabra pborr){
		
	}
	public void escribir_palabra(){}
	
	//Bajas
	//Da de baja un documento dado su autor y su titulo
	public void baja_individual(Palabra aut, Palabra tit){
		
	}
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
	
	//Consultoras
	//devuelve la lista de documentos resultados de una busqueda
	public ArrayList<Documento> devolver_documentos() {
		return conjDocumento_res;
	}
	
}
