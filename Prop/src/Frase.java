
import java.io.IOException;
import java.util.ArrayList;

public class Frase {
	private ArrayList<Palabra> frase = new ArrayList<Palabra>();
	
	//constructoras
	public Frase(){
			
	}
	//crea una frase a partir de un string de frase acabado en punto o algun signo
	public Frase (String fra) throws IOException{
		String delimitadores= "[ .,;?!¡¿\'\"\\[\\]]+";
		String[] pseparadas = fra.split(delimitadores);
		for(int i=0; i<pseparadas.length; ++i) {
			String saux= pseparadas[i];
			Palabra paux = new Palabra(saux);
			frase.add(paux);
		}
	}
	
	
	//modificadora 

	//anyade una palabra a la frase, pal es la palabra a la que se quiere anyadir
	//pos indica la posicion a la que se quiere anyadir. 0<=pos<=numerodepalabrasdelafrase+1
	public void anyadirpalabra (Palabra pal, int pos) throws IOException{
		frase.add(pos,pal);
	} 
	
	//borra una palabra de la frase, pal es la palabra a la que se quiere borrar
	public void borrarpalabra (Palabra pal) throws IOException{
		frase.remove(pal);
	} 
	
	//borra una palabra de la frase, pos es la posicion de la palabra que se quiere borrar
	//0<=pos<=numerodepalabrasdelafrase
	public void borrarpalabra (int pos) throws IOException{
		frase.remove(pos);
	} 
	
	//consultora
	
	//devuelve el numero de palabras que ocntiene la frase
	public int midafrase() throws IOException{
		return frase.size();
	}

	//devuelve la posicion en la que se encuentra la palabra pal en la frase
	//devuelve -1 en caso de que no se encuentre en ella
	public int posfrase(Palabra pal) throws IOException {
		return frase.indexOf(pal);
	}
	
	//devuelve el elemento que hay en la posicion pos
	//0<=pos<=numerodepalabrasdelafrase
	public Palabra posfrase(int pos) throws IOException {
		return frase.get(pos);
	}
	
	//devuelve la frase
	public ArrayList<Palabra> frase () throws IOException {
		return frase;
	}
	
	//escribe la frase por pantalla
	/*!!PROBLEMA, SIGNOS DE PUNTUACION SE PIERDEN*/
	public void escribirfrase() {
		for (int i=0; i<frase.size(); ++i) {
			frase.get(i).escribir();
		}
	}
}
