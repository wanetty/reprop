import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class frecuencias_driver {
	public static void main (String[] args) throws IOException{
		frecuencias_globales f= new frecuencias_globales();
		Cjt_documentos cjt= new Cjt_documentos();
		cjt.alta_doc("DocumentoAlta6.txt");
		f=cjt.frec_glob_tot();
		//System.out.println(f.valor_global("muerete"));
		Documento d=cjt.busqueda_por_auttit("henry","gilipollas");
		/*
		Map<String,ArrayList<String>> m=f.frecdocumentos("cagada");
		for (String clave : m.keySet()){
			//System.out.println(m.get(clave).size());
			for (int i=0; i<m.get(clave).size(); ++i) {
				//System.out.println(m.get(clave).size());
				System.out.println(clave+" "+m.get(clave).get(i));
			}
		}
		System.out.println(f.valor_documento("joder",d));
		System.out.println(f.apariencias_doc_palabra("muerete"));
		*/
		
		cjt.alta_doc("DocumentoAlta7.txt");
		f=cjt.frec_glob_tot();
		//System.out.println(f.valor_global("caca"));
		d=cjt.busqueda_por_auttit("henry","tonto");
		/*
		m=f.frecdocumentos("cagada");
		for (String clave : m.keySet()){
			//System.out.println(m.get(clave).size());
			for (int i=0; i<m.get(clave).size(); ++i) {
				//System.out.println(m.get(clave).size());
				System.out.println(clave+" "+m.get(clave).get(i));
			}
		}
		System.out.println(f.valor_documento("joder",d));
		System.out.println(f.apariencias_doc_palabra("muerete"));
		*/
		
		
		cjt.alta_doc("DocumentoAlta8.txt");
		f=cjt.frec_glob_tot();
		//System.out.println(f.valor_global("joder"));
		d=cjt.busqueda_por_auttit("vanesa","tonto");
		/*
		m=f.frecdocumentos("cagada");
		for (String clave : m.keySet()){
			//System.out.println(m.get(clave).size());
			for (int i=0; i<m.get(clave).size(); ++i) {
				//System.out.println(m.get(clave).size());
				System.out.println(clave+" "+m.get(clave).get(i));
			}
		}
		
		System.out.println(f.valor_documento("grande",d));
		d=cjt.busqueda_por_auttit("henry","tonto");
		System.out.println(f.valor_documento("grande",d));
		System.out.println(f.valor_global("grande"));
		System.out.println(f.apariencias_doc_palabra("muerete"));
		f.borrar_frecuencias(d);
		 */
		f.borrar_frecuencias("cagada",d);
		System.out.println(f.valor_global("joder"));
		System.out.println(f.valor_global("cagada"));
		d=cjt.busqueda_por_auttit("henry","gilipollas");
		System.out.println(f.valor_documento("cagada",d));
		
		System.out.println(cjt.get_cjt_size());
	}
}
