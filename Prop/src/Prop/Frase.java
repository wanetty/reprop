package Prop;
//Creador: Henry Qiu

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Frase implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7359658011779350346L;
	private ArrayList<Palabra> frase = new ArrayList<Palabra>();
	
	//constructoras
	public Frase(){
		frase = null;
			
	}
	//crea una frase a partir de un string de frase acabado en punto o algun signo
	public Frase (String fra) throws IOException{
		String delimitadores= "\\s";
		String[] pseparadas = fra.split(delimitadores);
		for(int i=0; i<pseparadas.length; ++i) {
			String saux= pseparadas[i];
			Palabra paux = new Palabra();
			if(saux.length() > 1 && !(saux.charAt(0)>='a' && saux.charAt(0)<='z') && !(saux.charAt(0)>='A' && saux.charAt(0)<='Z')){
				paux.setAdelante(saux.substring(0,1));
			}
			if(saux.length() > 1 && !(saux.charAt(saux.length()-1)>='a' && saux.charAt(saux.length()-1)<='z') && !(saux.charAt(saux.length()-1)>='A' && saux.charAt(saux.length()-1)<='Z')){
				paux.setDetras(saux.substring(saux.length()-1,saux.length()));
			}
			if(paux.getAdelante().equals("-1") && paux.getDetras().equals("-1"))paux.modificar(saux);
			else if(!paux.getAdelante().equals("-1") && paux.getDetras().equals("-1"))paux.modificar(saux.substring(1));
			else if(paux.getAdelante().equals("-1") && !paux.getDetras().equals("-1") && saux.length() != 1)paux.modificar(saux.substring(0,saux.length()-1));
			else if(!paux.getAdelante().equals("-1") && !paux.getDetras().equals("-1") && saux.length() != 1)paux.modificar(saux.substring(1,saux.length()-1));
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
	
	
	//devuelve el numero de palabras que conntiene la frase
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
	public String toString() {///transforma una frase en string en minuscula
		String res = "";
		if (!frase.isEmpty()){
			for(int i=0; i<frase.size(); ++i) {
				Palabra p=frase.get(i);
				res+=p.palabra();
				if (i != frase.size()-1) res+=(" ");
			}
			res=res.toLowerCase();
			return res;
		}
		else return "-1";
	}
	
	public String toString_consigno() {///transforma una frase en string
		String res = null;
		if (!frase.isEmpty()){
			for(int i=0; i<frase.size(); ++i) {
				Palabra p=frase.get(i);
				if (res == null) {
					if(p.getAdelante().equals("-1"))res=p.palabra();
					else {
						res = p.getAdelante();
						res += p.palabra();
					}
				}
				else if(!p.getAdelante().equals("-1")){
					res+=p.getAdelante();
					res += p.palabra();
				}
				else res+=p.palabra();
				if(!p.getDetras().equals("-1"))res+=p.getDetras();
				if (i != frase.size()-1) res+=(' ');
				//else res += (".\n");
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
	public void escribirfrase() {
		String res = null;
		for(int i=0; i<frase.size(); ++i) {
			Palabra p=frase.get(i);
			if (res == null) {
				if(p.getAdelante().equals("-1"))res=p.palabra();
				else {
					res = p.getAdelante();
					res += p.palabra();
				}
			}
			else if(!p.getAdelante().equals("-1")){
				res+=p.getAdelante();
				res += p.palabra();
			}
			else res+=p.palabra();
			if(!p.getDetras().equals("-1"))res+=p.getDetras();
			if (i != frase.size()-1) res+=(" ");
		}
		System.out.println(res);
	}
}
