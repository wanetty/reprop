package Prop;
//Creador: Gerard Heredia
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Documento implements java.io.Serializable  {
	private Frase titulo = new Frase(); 
	public Frase getTitulo() {
		return titulo;
	}

	public void setTitulo(Frase titulo) {
		this.titulo = titulo;
	}

	public Frase getAutor() {
		return autor;
	}

	public void setAutor(Frase autor) {
		this.autor = autor;
	}

	public Frase getTema() {
		return tema;
	}

	public void setTema(Frase tema) {
		this.tema = tema;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Frase> getContenido() {
		return contenido;
	}

	public void setContenido(ArrayList<Frase> contenido) {
		this.contenido = contenido;
	}

	public Map<String, Double> getPesos() {
		return pesos;
	}

	public void setPesos(Map<String, Double> pesos) {
		this.pesos = pesos;
	}

	private Frase autor = new Frase();
	private Frase tema = new Frase();
	private Date fecha = new Date();
	private ArrayList<Frase> contenido = new ArrayList<Frase>();
	private Map<String, Double> pesos = new HashMap<String, Double>();//constructoras

	public Documento(){
		titulo=null;
		autor=null;
		tema=null;
	}

	//Da de alta el documento raiz
	//No se debe hacer
	public Documento(String raiz) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(raiz));
		String funcional = in.readLine();
		if (!funcional.isEmpty()) {
			autor=new Frase(funcional);
			funcional=in.readLine();
			if (!funcional.isEmpty()) {
				titulo=new Frase(funcional);
				funcional=in.readLine();
				tema=new Frase(funcional);
				funcional=in.readLine();
				String delimitadores= "[.;?!:]";
				while (funcional != null){
					String[] frasesseparadas = funcional.split(delimitadores);
					for (int i=0; i<frasesseparadas.length; ++i) {
						funcional=frasesseparadas[i];
						contenido.add(new Frase(funcional));
					}
					funcional = in.readLine();
				}
				construirPesos();
				System.out.println("Documento leido correctamente");
			}
			else System.out.println("Formato de documento incorrecto: autor no puede ser vacio");
		}
		else System.out.println("Formato de documento incorrecto: titulo no puede ser vacio");
		in.close();
	}

	public Documento(Frase ti,Frase a,Frase te,ArrayList<Frase> c) throws IOException{
		titulo = ti;
		autor = a;
		tema = te;
		contenido = c;
		construirPesos();
	}

	private void string_to_arraylist(ArrayList<Frase> c, String s) throws IOException {
		String delimitadores= "[.;?!]";
		String[] frasesseparadas = s.split(delimitadores);
		for (int i=0; i<frasesseparadas.length; ++i) {
			Frase aux=new Frase(frasesseparadas[i]);
			c.add(aux);
		}
	}

	public void guardar_documento(String text) throws IOException {
		String del="\\n";
		String[] aux = text.split(del);//aux contiene parrafos
		Frase ti = new Frase(aux[0]);
		Frase a = new Frase(aux[1]);
		Frase te= new Frase(aux[2]);
		ArrayList<Frase> c= new ArrayList<Frase>();
		string_to_arraylist(c,aux[3]);
		titulo = ti;
		autor = a;
		tema = te;
		contenido = c;
		construirPesos();
		System.out.println("Documento leido correctamente");
	}

	//Construye un mapa <string, double> que contiene palabras con su peso
	public void construirPesos() throws IOException{
		int sizeDoc = contenido.size();
		Frase fraseActual = new Frase();

		for (int i = 0; i < sizeDoc; ++i) {
			fraseActual = contenido.get(i);
			int sizeFrase = fraseActual.midafrase();
			for (int j = 0; j < sizeFrase; ++j){
				Palabra palActual = fraseActual.posfrase(j);
				String palKey = palActual.palabra();
				palKey = palKey.toLowerCase();
				//palabra repetida
				if (!palActual.esfuncional()){
					if (pesos.containsKey(palKey)) {
						double pes = pesos.get(palKey);
						pesos.remove(palKey);
						pes = pes + 1;
						pesos.put(palKey, pes);
					}
					else {
						pesos.put(palKey, (double) 1);
					}
				}
			}
		}
	}

	//modificadora

	//Modificaciones
	//de momento suponemos que desde el progama principal le pasara la posicion de la palabra en la frase
	//nota: hay que anyadir en el caso de usos que el usuario tendria que indicar el numero de la frase a la que pertenece la palabra que se quiere borrar y la palabra borrada.
	//0<=numfras<=numero de frases en total del documento
	public void borrar_palabra(int numfras, Palabra pborr) throws IOException{
		if(numfras >= 0 && numfras<contenido.size()) {
			int pos=contenido.get(numfras).posfrase(pborr);
			if (pos >= 0) {
				contenido.get(numfras).borrarpalabra(pos);
				if (!pborr.esfuncional()) {
					String termino = pborr.palabra();
					Double frec = pesos.get(termino);
					--frec;
					if (frec == 0) pesos.remove(termino);
					else {
						pesos.put(termino, frec);
					}
				}
			}
			else System.out.println("La palabra introducida no se encuentra en la frase");
		}
		else System.out.println("El numero de frase introducido no es correcto");

	}
	public void anyadir_palabra(int numfras, Palabra panyad) throws IOException{
		if(numfras >= 0 && numfras<contenido.size()) {
			contenido.get(numfras).anyadirpalabra(panyad, contenido.get(numfras).midafrase());
			if (!panyad.esfuncional()){
				String termino = panyad.palabra().toLowerCase();
				Double frec;
				if (pesos.containsKey(termino)) {
					frec = pesos.get(termino);
					++frec;
					pesos.put(termino, frec);
				}
				else pesos.put(termino, (double) 1);
			}
		}
		else System.out.println("El numero de frase introducido no es correcto");
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
	public String get_fecha(){
		int anyoaux=fecha.getYear();
		int mesaux=fecha.getMonth();
		int diaaux=fecha.getDate();
		String anyo = null, mes=null, dia=null;
		anyo=String.valueOf(anyoaux);
		mes=String.valueOf(mesaux);
		dia=String.valueOf(diaaux);
		String nuevo;
		if (mes.equals("12")) nuevo=dia+"/01/"+anyo.substring(1, 3);
		else nuevo=dia+'/'+mes.charAt(0)+(char)(mes.charAt(1)+1)+'/'+anyo.substring(1, 3);
		return nuevo;
	}

	public int get_num_frases(){
		return contenido.size();
	}
	public ArrayList<Frase> get_contenido(){
		return contenido;
	}

	public Frase get_frase(int i){
		if(i > contenido.size() || i < 0) return null; 
		return contenido.get(i);
	}

	public Map<String, Double> get_pesos(){
		return pesos;
	}

	public double get_total_words() throws IOException{
		double res = 0;
		Frase aux = new Frase();
		for (int i = 0; i < contenido.size(); ++i){
			aux = contenido.get(i);
			res = res + aux.midafrase_significativa();
		}
		return res;
	}

	//devuelve un array que contiene las frases en las que aparece la palabra p
	public Set<Integer> apariencia_num_frase(String p) {
		Set<Integer> res=new HashSet<Integer>();
		String fraseaux;
		for(int i=0; i<contenido.size(); ++i) {
			fraseaux=contenido.get(i).toString();
			if (fraseaux.contains(p)) res.add(i);
		}
		return res;
	}

	//modificadoras

	public void modificar_autordoc(String aut) throws IOException {
		Frase newaut=new Frase(aut);
		autor=newaut;
	}
	
	public void modificar_titulodoc(String tit) throws IOException {
		Frase newtit=new Frase(tit);
		autor=newtit;
	}
	
	public void borrar_frase(int i) throws IOException{
		if(i >= 0 && i<=contenido.size()) {
			if (i < contenido.size()) {/*contenido.remove(i);*/
				Frase f = new Frase();
				int n = f.midafrase();
				for (int j = 0; j < n; ++j) {
					Palabra actual = f.posfrase(j);
					borrar_palabra(i, actual);	
				}
			}
		}
		else System.out.println("El numero de frase introducido no es correcto");
	}

	public void set_frase(int i, Frase f) throws IOException{
		if(i >= 0 && i<=contenido.size()) {
			contenido.add(i, f);
			Palabra actual = new Palabra();
			for (int j = 0; j < f.midafrase(); ++j){
				actual = f.posfrase(j);
				String palKey = actual.palabra().toLowerCase();
				if (pesos.containsKey(palKey)) {
					Double frec = pesos.get(palKey);
					++frec;
					pesos.put(palKey, frec);
				}
				else pesos.put(palKey, (double) 1);	
			}
		}
		else System.out.println("El numero de frase introducido no es correcto");
	}

	public void pintar_documento() {
		System.out.println(titulo.toString_consigno());
		System.out.println(autor.toString_consigno());
		System.out.println(tema.toString_consigno());
		for (int i=0; i<contenido.size(); ++i) {
			System.out.println(contenido.get(i).toString_consigno());
			
		}
	}
	
	public String contenido_toString() {
		String ret="";
		String aux;
		char fl;
		for(int i=0; i<contenido.size(); ++i) {
			aux=contenido.get(i).toString_consigno();
			fl=(char) ('A' + (aux.charAt(0) - 'a'));
			ret+=fl+aux.substring(1, aux.length());
			if (i!=contenido.size()-1) ret+=' ';
		}
		return ret;
	}
	
	
	/*
	public boolean doc_iguales(Documento d) {
		return d.get_autor().toString_consigno().equals(autor.toString_consigno()) && d.get_titulo().toString_consigno().equals(titulo.toString_consigno());
	}*/
}