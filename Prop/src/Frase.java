//Creador: Henry Qiu

import java.io.IOException;
import java.util.ArrayList;

public class Frase {
	private ArrayList<Palabra> frase = new ArrayList<Palabra>();
	
	//constructoras
	public Frase(){
		frase = null;
			
	}
	//crea una frase a partir de un string de frase acabado en punto o algun signo
	public Frase (String fra) throws IOException{
		String delimitadores= "[ .,;?!\'\"\\[\\]]+";
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
	
	//borra una palabra de la frase, pos es la posicion de la palabra que se quiere borrar
	//0<=pos<=numerodepalabrasdelafrase
	public void borrarpalabra (int pos) throws IOException{
		frase.remove(pos);
		if(frase.size() == 0) frase = null;
	} 
	
	//consultora
	
	
	//devuelve el numero de palabras que ocntiene la frase
	public int midafrase() throws IOException{
		return frase.size();
	}
	
	public int midafrase_significativa() throws IOException{
		int res = 0;
		for(int i = 0; i < frase.size(); ++i){
			if (!frase.get(i).esfuncional()) ++res;
		}
		return res;
	}
	public String toString() {///transforma una frase en string
		String res = null;
		if (!frase.isEmpty()){
		for(int i=0; i<frase.size(); ++i) {
			Palabra p=frase.get(i);
			if (res == null) res=p.palabra();
			else res+=p.palabra();
			if (i != frase.size()-1) res+=(" ");
		}
		return res;
		}
		else return "-1";
	}
	//devuelve la posicion en la que se encuentra la palabra pal en la frase
	//devuelve -1 en caso de que no se encuentre en ella
	public int posfrase(Palabra pal) throws IOException {
		for(int i = 0; i < frase.size();++i){
			if(frase.get(i).palabra().equals(pal.palabra()))return i;
		}
		return -1;
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
		String res = null;
		for(int i=0; i<frase.size(); ++i) {
			Palabra p=frase.get(i);
			if (res == null) res=p.palabra();
			else res+=p.palabra();
			if (i != frase.size()-1) res+=(" ");
		}
		System.out.println(res);
	}
}
