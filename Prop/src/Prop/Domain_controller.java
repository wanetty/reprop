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
			String delimitadores= "[.;?!]";
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
	
	//public void BAJA_DOC(Documento d)
	
	//BUSQUEDA
	
	public ArrayList<Documento> BUSQUEDA_PARECIDO(String titulo, String autor, int k, int metodo) {
		try {
			return BUS.por_similitud(CJT, autor, titulo, k, metodo);
		}
		catch (Exception e){
			return null;
		}
		
	}
	
	/*public ArrayList<Documento> BUSQUEDA_BOOLEANA(String expresion){
	}
	*/
	
	public ArrayList<Documento> BUSQUEDA_TITULO(String titulo){
		try {
			return BUS.por_titulo(CJT, titulo);
		} catch (Exception e){
			return null;
		}
	}
	
	public ArrayList<Documento> BUSQUEDA_TEMA(String tema) {
		try {
			return BUS.por_tema(CJT, tema);
		}catch (Exception e) {
			return null;
		}
	}
	
	public ArrayList<Documento> BUSQUEDA_AUTOR(String autor) {
		try {
			return BUS.por_autor(CJT, autor);
		}catch (Exception e) {
			return null;
		}
	}
	
	public ArrayList<Documento> BUSQUEDA_FECHA(String fecha) {
		try {
			return BUS.por_fecha(CJT, fecha);
		} catch (Exception e) {
			return null;
		}
	}
	public Documento BUSQUEDA_auttit(String autor, String titulo) {
		try {
			return BUS.por_auttit(CJT, autor, titulo);
		} catch (Exception e) {
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
			PER.recuperar();
		}catch (Exception e){}
	}
}