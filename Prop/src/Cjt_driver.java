import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Cjt_driver {


	public static void main (String[] args) throws IOException{
		 /*
		Frase f = new Frase("An essay is essentially a collection of paragraphs, and as has already been said, a pargraph is essentially a mini-essay.");
		Frase g = new Frase("An essay contains an introduction, a body, and a conclusion.");
		Frase aut = new Frase("Nacho vidal");
		Frase tit = new Frase("Don Quijote");
		Frase tem = new Frase("Poesia Clasica");
		ArrayList<Frase> y = new ArrayList<Frase>();
		y.add(g);
		y.add(f);
		Calendar c = null;
		Date d=c.getTime();
		Documento d1 = new Documento(aut,tit,tem,y,d);
		/*
		System.out.println(test.calculaSimilitud(d1, d3));
		*/
		String text = "Kiko Rivera"
				+ "Don Quijote"
				+ "Posia Profunda"
				+ "An essay is essentially a collection of paragraphs, and as has already been said, a pargraph is essentially a mini-essay. "
				+ "An essay contains an introduction, a body, and a conclusion.";
		Cjt_documentos cjt= new Cjt_documentos();
		//cjt.alta_multiple(text, 1);

	}

}
