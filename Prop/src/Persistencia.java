import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Persistencia  {
	private String archivo = new String();


	public Persistencia () {
		archivo = null;
	}

	public Persistencia (String archivo) {
		this.archivo = archivo;
	}


	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public void guardar (Cjt_documentos prog) throws IOException{
		if(prog.get_cjt_size() != 0){
			Map<String, Map<String,Documento>> aux = prog.get_por_titulo();
			File arch = new File(archivo);
			if(!arch.exists()){
				arch.createNewFile();
			}
			//System.out.println("te lo guardo : "+ archivo);
			FileWriter fw = new FileWriter(arch.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<ficheroprop>");
			bw.newLine();
			for (String clave1 : aux.keySet()){
				for (String clave2 : aux.get(clave1).keySet()){
					System.out.println("Entro para el documento " + clave1 + " " + clave2);
					bw.write("<nuevodocumento>");
					bw.newLine();
					Documento d = aux.get(clave1).get(clave2);
					EscribirDoc(d,bw);
				}
			}	
			bw.close();



		}


	}


	public ArrayList<Documento> recuperar(){
		String identificador = "<ficheroprop>";
		String nuevoDoc = "<nuevodocumento>";
		String cont = "<contenido>";
		String fincont = "<fincontenido>";
		String findoc = "<findocumento>";
		ArrayList<Frase> contenido = new ArrayList<Frase>();
		ArrayList<Documento> documentos = new ArrayList<Documento>();
		Frase actual = new Frase();
		Frase autor = new Frase();
		Frase titulo = new Frase();
		Frase tema = new Frase();
		boolean primero = true;
		try {
			BufferedReader in = new BufferedReader(new FileReader(archivo));
			String lectura = in.readLine();
			while(lectura != null){
				System.out.println("ahora vale" + lectura);
				if(primero){
					primero = false;
					if(lectura.equals(identificador)){
						lectura = in.readLine();
					}
					else {
						System.out.println("Fichero de salvado incorrecto");// implementar error.
						break;
					}// implementar error.
				}
				if(lectura.equals(nuevoDoc)){
					lectura = in.readLine();
					autor = new Frase(lectura);
					System.out.println("El autor es" + lectura);
					lectura = in.readLine();
					titulo = new Frase(lectura);
					System.out.println("El titulo es " + lectura);
					lectura = in.readLine();
					tema = new Frase(lectura);
					System.out.println("El tema es " + lectura);
					lectura = in.readLine();
				}
				if(lectura.equals(cont)){
					lectura = in.readLine();
					contenido = new ArrayList<Frase>();
					while(!lectura.equals(fincont)){
						actual = new Frase(lectura);
						System.out.println("la frase que leo es: " + lectura);
						contenido.add(actual);
						lectura = in.readLine();
					}
					lectura = in.readLine();
				}
				if(lectura.equals(findoc)){
					Documento d = new Documento(titulo,autor,tema,contenido);
					System.out.println("El autor es: " + d.get_autor().toString_consigno());
					System.out.println("El titulo es: " + d.get_titulo().toString_consigno());
					documentos.add(d);
					lectura = in.readLine();
				}
			}	

			in.close();
		}		catch (IOException e) {
			e.printStackTrace();
		}



		return documentos;
	}
	private void EscribirDoc(Documento d, BufferedWriter bw) {
		try {
			bw.write(d.get_autor().toString_consigno());
			bw.newLine();
			bw.write(d.get_titulo().toString_consigno());
			bw.newLine();
			if(d.get_tema().toString_consigno() != null){
				bw.write(d.get_tema().toString_consigno());
				bw.newLine();
			}
			bw.write("<contenido>");
			bw.newLine();
			ArrayList<Frase> cont = d.get_contenido();
			for (int i=0; i<cont.size(); ++i) {
				bw.write(cont.get(i).toString_consigno() + ".");
				bw.newLine();
			}
			bw.write("<fincontenido>");
			bw.newLine();
			bw.write("<findocumento>");
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}






}
