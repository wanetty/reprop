package Prop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Domain_controller {
	
	private Persistencia PER = new Persistencia();
	private Cjt_documentos CJT = new Cjt_documentos();
	private Busquedas BUS = new Busquedas();
	
	
	
	public Domain_controller(){
	}
	

	public void Crear_manual(String titulo, String autor, String tema, String contenido) throws Custom_exception, IOException{
		try {
			if (CJT.existe_combinacion(autor, titulo)) throw new Custom_exception("Combinacion de autor y titulo ya existente");
			Documento Doc = new Documento();
			Date fecha = new Date();
			Doc.setTitulo(new Frase(titulo));
			Doc.setAutor(new Frase(autor));
			Doc.setTema(new Frase(tema));
			Doc.setFecha(fecha);
			Doc.setContorg(contenido);
			ArrayList<Frase> c = new ArrayList<Frase>();
			String delimitadores= "[.;?!]";//faltan los puntos suspensivos
			String[] frasesseparadas = contenido.split(delimitadores);
			for (int i=0; i<frasesseparadas.length; ++i) {
				Frase aux=new Frase(frasesseparadas[i]);
				c.add(aux);
			}
			Doc.setContenido(c);
			Doc.construirPesos();
			CJT.alta_doc(Doc);
		}catch (Custom_exception e) {
			throw e;
		}
	}
	
	/*
	public Documento Crear_raiz(String raiz){
		try {
			ArrayList<Frase> contenido = new ArrayList<Frase>();
			Documento Doc = new Documento();
			BufferedReader in = new BufferedReader(new FileReader(raiz));
			String funcional = in.readLine();
			if (funcional.isEmpty()) {
				throw new Documento_exception("Formato de documento incorrecto: autor no puede ser vacio");
			}
			else {
				Doc.setAutor(new Frase(funcional));
				funcional=in.readLine();
				if (funcional.isEmpty()) {
					throw new Documento_exception("Formato de documento incorrecto: titulo no puede ser vacio");
				}
				else {
					Doc.setTitulo(new Frase(funcional));
					funcional=in.readLine();
					Doc.setTema(new Frase(funcional));
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
					Doc.setContenido(contenido);
					Doc.construirPesos();
					return Doc;
				}
			}
		}catch (Exception e) {
			return null;
		}
	}
	*/
	
	public void Crear_raiz(String raiz) throws Custom_exception, IOException{
		try {
			File fi = new File(raiz);
			if (!fi.isFile()) throw new Custom_exception("El documento no existe");
			PER.setRuta(raiz);
			Documento d = new Documento();
			d = PER.alta_doc();
			if(CJT.existe_combinacion(d.getAutor().toString_consigno(), d.getTitulo().toString_consigno())) throw new Custom_exception("Combinación de titulo y autor ya existente");
			CJT.alta_doc(d);
		} catch(Custom_exception e) {
			throw e;
		}
	}
	
	public void BAJA_DOC(String titulo, String autor) throws IOException, Custom_exception {
		try {
			if (!CJT.existe_combinacion(autor, titulo)) throw new Custom_exception("El documento no existe");
			Documento d = new Documento();
			d = CJT.busqueda_por_auttit(autor, titulo);
			CJT.baja_individual_doc(d);
		}catch (Custom_exception e) {
			throw e;
		}
	}
	
	
	//BUSQUEDA
	
	public ArrayList<String> Doc_to_string(Documento d) throws IOException {
		//0 autor
		//1 titulo
		//2 tema
		//3 contenido
		ArrayList<String> res = new ArrayList<String>();
		res.add(0, d.getAutor().toString_consigno());
		res.add(1, d.getTitulo().toString_consigno());
		if(!(d.getTema().midafrase()== 0)) {
			res.add(2, d.getTema().toString_consigno());
		}
		String aux = d.contenido_toString();
		res.add(3, aux);
		return res;
		
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_TITULO(String titulo) throws Custom_exception, IOException{
		try {
			if (!CJT.existe_titulo(titulo)) throw new Custom_exception("No existe ningun documento con este titulo");
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_titulo(CJT, titulo));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		} catch (Custom_exception e){
			throw e;
		}
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_TEMA(String tema) throws Custom_exception, IOException {
		try {
			if(! CJT.existe_tema(tema)) throw new Custom_exception("No existe ningun documento con este tema");
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_tema(CJT, tema));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
		
			return res_string;
		}catch (Custom_exception e) {
			 throw e;
		}
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_AUTOR(String autor) throws IOException{
		//try {
			Set<String> autores = new HashSet<String>();
			autores = BUS.por_prefijo(CJT, autor);
			//if(autores.isEmpty()) throw new Custom_exception("No existe ningun documento con este autor");
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>();
			ArrayList<Documento> aux = new ArrayList<Documento>();
			for(String clave : autores) {
				aux = BUS.por_autor(CJT, clave);
				for (int k = 0; k < aux.size(); ++k) {
					res.add(aux.get(k));
				}
			}
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		//}catch (Custom_exception e) {
			//throw e;
		//}
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_FECHA(String fecha) throws IOException/*, Custom_exception*/ {
		//try {
			//if(!CJT.existe_fecha(fecha)) throw new Custom_exception("No existe ningun documento con esta fecha");
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_fecha(CJT, fecha));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		/*} catch (Custom_exception e) {
			throw e;
		}*/
	}
	public ArrayList<String> BUSQUEDA_auttit(String autor, String titulo) throws Custom_exception, IOException {
		try {
			if (!CJT.existe_combinacion(autor, titulo)) throw new Custom_exception("No existe ningun documento con esta combinacion de autor y titulo");
			return BUS.por_auttit(CJT, autor, titulo).Doc_to_string();
		} catch (Custom_exception e) {
			throw e;
		}
		//return null;
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_PARECIDO(String titulo, String autor, int k, int metodo) throws Custom_exception, IOException{
		try {
			if (!CJT.existe_combinacion(autor, titulo)) throw new Custom_exception("No existe ningun documento con esta combinacion de autor y titulo");
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_similitud(CJT, autor, titulo, k, metodo));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		}
		catch (Custom_exception e){
			throw e;
		}
		
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_BOOLEANA(String expresion) throws IOException/*, Custom_exception*/ {
		//try {
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_booleano(CJT, expresion));
			//if (res.isEmpty()) throw new Custom_exception("No existe ningun documento que cumpla la expresión");
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		//}catch (Custom_exception e) {
			//throw e;
		//}
	}
	
	public ArrayList<ArrayList<String>> ALL_DOCS() throws IOException {
		ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();  
		Documento d = new Documento();
		for(String clave1 : CJT.get_por_titulo().keySet()) {
			for (String clave2 : CJT.get_por_titulo().get(clave1).keySet()) {
				d = CJT.get_por_titulo().get(clave1).get(clave2);
				res_string.add(d.Doc_to_string());
			}
		}
		return res_string;
	}
	
	public void GUARDAR(String ruta) {
		try {
			PER.setRuta(ruta);
			PER.guardar(CJT);
		}catch (Exception e){}
	}

	public void RECUPERAR(String ruta) {
		try {
			PER.setRuta(ruta);
			CJT = PER.recuperar();
		}catch (Exception e){}
	}
	
}