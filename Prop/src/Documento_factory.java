import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Documento_factory {
	
	public Documento Crear_manual(Frase titulo, Frase autor, Frase tema, ArrayList<Frase> contenido){
		try {
			Documento Doc = new Documento();
			Date fecha = new Date();
			Doc.setTitulo(titulo);
			Doc.setAutor(autor);
			Doc.setTema(tema);
			Doc.setFecha(fecha);
			Doc.setContenido(contenido);
			Doc.construirPesos();
			return Doc;
		}catch (Exception e) {
			return null;
		}
	}
	
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
}