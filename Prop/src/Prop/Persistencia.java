/*Eduard González Moreno*/
package Prop;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Persistencia  {
	
	private String ruta;
	
	public Persistencia(){
		ruta = "";
	}

	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	// Ahora se da de alta los documentos llamando a la capa de persistencia.
	public Documento alta_doc() throws Custom_exception, IOException{
		try {
			ArrayList<Frase> contenido = new ArrayList<Frase>();
			Documento Doc = new Documento();
			BufferedReader in = new BufferedReader(new FileReader(ruta));
			String funcional = in.readLine();
			if (funcional.isEmpty()) {
				throw new Custom_exception("Formato de documento incorrecto: autor no puede ser vacio");
			}
			else {
				Doc.setAutor(new Frase(funcional));
				funcional=in.readLine();
				if (funcional.isEmpty()) {
					throw new Custom_exception("Formato de documento incorrecto: titulo no puede ser vacio");
				}
				else {
					Doc.setTitulo(new Frase(funcional));
					funcional=in.readLine();
					Doc.setTema(new Frase(funcional));
					funcional=in.readLine();
					String delimitadores= "[.;?!:]";
					String original=funcional+'\n';
					while (funcional != null){
						String[] frasesseparadas = funcional.split(delimitadores);
						for (int i=0; i<frasesseparadas.length; ++i) {
							funcional=frasesseparadas[i];
							contenido.add(new Frase(funcional));
						}
						funcional = in.readLine();
						if (funcional != null) original+=funcional+'\n';
					}
					Doc.setContenido(contenido);
					Doc.setContorg(original);
					Doc.construirPesos();
					return Doc;
				}
			}
		}catch (Custom_exception e) {
			throw e;
		}
	}

	/*private static final long serialVersionUID = 1527375506289726741L;
	private Map<String, Map<String,Documento>> por_titulo = new HashMap<String,Map<String,Documento>>();//guarda todos los documentos por titulo y los autores que lo tienen y su documento
	private Map<String, Map<String,Documento>> por_autor = new HashMap<String,Map<String,Documento>>();//guarda todos 	los documentos de un autor
	private Map<String, Map<String, Map<String,Documento>>> por_tema = new HashMap<String, Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un tema concreto
	private Map<String, Map<String, Map<String,Documento>>> por_fecha = new HashMap<String,Map<String, Map<String,Documento>>>();//guarda todos los documentos por autor y titulo de un dia concreto
	private frecuencias_globales frecuencias= new frecuencias_globales();
	private int cjt_size;*/
	
	public void guardar(Cjt_documentos conj) throws FileNotFoundException, IOException{
		FileOutputStream fet = new  FileOutputStream(ruta);
		ObjectOutputStream salida=new ObjectOutputStream(fet);
	     
	      salida.writeObject(conj);
	      
	      salida.close();
	      fet.close();
	}
	
	public Cjt_documentos recuperar () throws ClassNotFoundException{
	  try { 
		  Cjt_documentos conj  = new Cjt_documentos();
	ObjectInputStream entrada =new ObjectInputStream(new FileInputStream(ruta));
	conj =(Cjt_documentos)entrada.readObject();
    entrada.close();
   return conj;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	


	}




