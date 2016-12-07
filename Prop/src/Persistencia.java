

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Persistencia  {
	
	String ruta;
	
	public Persistencia(){
		ruta = null;
	}

	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	// Ahora se da de alta los documentos llamando a la capa de persistencia.
	public Documento alta_doc(){
		try {
			ArrayList<Frase> contenido = new ArrayList<Frase>();
			Documento Doc = new Documento();
			BufferedReader in = new BufferedReader(new FileReader(ruta));
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

	public void guardar(Cjt_documentos conj) throws FileNotFoundException, IOException{
		
		ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(ruta));
	      salida.writeObject(conj);
	      salida.close();
	}
	
	public Cjt_documentos recuperar () throws ClassNotFoundException{
	  try { 
	ObjectInputStream entrada =new ObjectInputStream(new FileInputStream(ruta));
     Cjt_documentos conj = (Cjt_documentos) entrada.readObject();
     entrada.close();
   return conj;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	


	}




