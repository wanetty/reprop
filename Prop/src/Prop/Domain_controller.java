package Prop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Domain_controller {
	
	private Persistencia PER = new Persistencia();
	private Cjt_documentos CJT = new Cjt_documentos();
	private Busquedas BUS = new Busquedas();
	
	
	
	public Domain_controller(){
	}
	

	public void Crear_manual(String titulo, String autor, String tema, String contenido){
		try {
			Documento Doc = new Documento();
			Date fecha = new Date();
			Doc.setTitulo(new Frase(titulo));
			Doc.setAutor(new Frase(autor));
			Doc.setTema(new Frase(tema));
			Doc.setFecha(fecha);
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
		}catch (Exception e) {
			e.printStackTrace();
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
	
	public void Crear_raiz(String raiz) throws Exception_test, IOException{
		File fi = new File(raiz);
		if (!fi.isFile()) throw new Exception_test("El documento no existe");
		PER.setRuta(raiz);
		CJT.alta_doc(PER.alta_doc());
	}
	//no se si hace falta...
	public void ALTA_DOC(Documento d) {
		try {
			CJT.alta_doc(d);
		}catch (Exception e){}
	}
	
	public void BAJA_DOC(String titulo, String autor) throws IOException {
		Documento d = new Documento();
		d = CJT.busqueda_por_auttit(autor, titulo);
		CJT.baja_individual_doc(d);
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
	
	public ArrayList<ArrayList<String>> BUSQUEDA_TITULO(String titulo){
		try {
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_titulo(CJT, titulo));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		} catch (Exception e){
			return null;
		}
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_TEMA(String tema) {
		try {
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_tema(CJT, tema));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				System.out.println(d.get_autor());
				res_string.add(i, d.Doc_to_string());
			}
		
			return res_string;
		}catch (Exception e) {
			return null;
		}
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_AUTOR(String autor) {
		try {
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_autor(CJT, autor));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		}catch (Exception e) {
			return null;
		}
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_FECHA(String fecha) {
		try {
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_fecha(CJT, fecha));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		} catch (Exception e) {
			return null;
		}
	}
	public ArrayList<String> BUSQUEDA_auttit(String autor, String titulo) {
		try {
			return BUS.por_auttit(CJT, autor, titulo).Doc_to_string();
		} catch (Exception e) {
			return null;
		}
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_PARECIDO(String titulo, String autor, int k, int metodo) {
		try {
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_similitud(CJT, autor, titulo, k, metodo));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		}
		catch (Exception e){
			return null;
		}
		
	}
	
	public ArrayList<ArrayList<String>> BUSQUEDA_BOOLEANA(String expresion) {
		try {
			ArrayList<ArrayList<String>> res_string = new ArrayList<ArrayList<String>>();
			ArrayList<Documento> res = new ArrayList<Documento>(BUS.por_booleano(CJT, expresion));
			Documento d = new Documento();
			for (int i = 0; i < res.size(); ++i) {
				d = res.get(i);
				res_string.add(i, d.Doc_to_string());
			}
			return res_string;
		}catch (Exception e) {
			return null;
		}
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