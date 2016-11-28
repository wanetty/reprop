import java.io.BufferedWriter;
import java.io.File;
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

	public void guardar (Cjt_documentos prog){
		if(prog.get_cjt_size() != 0){
			Map<String, Map<String,Documento>> aux = prog.get_por_titulo();
			File arch = new File(archivo);
			System.out.println("te lo guardo : "+ archivo);
			BufferedWriter bw;
			try {
					bw = new BufferedWriter(new FileWriter(arch));
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
						bw.close();
					}	

				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


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
			ArrayList<Frase> cont = d.get_contenido();
			for (int i=0; i<cont.size(); ++i) {
				bw.write(cont.get(i).toString_consigno() + ".");
				bw.newLine();
			}
			bw.write("<findocumento>");
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}


	}






}
