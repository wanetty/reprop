package Prop;
//Clase realizada por Eduard Gonzalez Moreno
//Clase que transforma una expresion booleana en una estructura de tipo Nodo.


import java.io.IOException;
import java.util.ArrayList;



public class Bool_expresion implements java.io.Serializable  {
	//creadoras

	private Nodo estrucexp;
	private int cant;
	private int altura;
	private boolean correcto = true;
	private ArrayList <String> postorden = new ArrayList<String>();

	//Inicializadora de variable poniendo valores por defecto
	public Bool_expresion() throws IOException{
		estrucexp = new Nodo();
	}

	//inicializadora de variable contruyendo el arbol segun la expresion que nos pasan
	public Bool_expresion(String expresion) throws IOException{
		Nodo aux = null;
		if(comprueba(expresion))aux = analiza(estrucexp,expresion);	
		if (correcto) this.estrucexp = aux;
		else estrucexp = null;
	}
	//consultoras
	//pre : cierto&h
	//post: devuelve el valor de la raiz. Si es nula retorna -1
	public String valor_raiz(){
		if(estrucexp != null) return estrucexp.getValor();
		else return "-1";
	}
	//pre: cierto
	//post: Nos devuleve nodo de la raiz.
	public Nodo getraiz(){
		return estrucexp;
	}
	//pre: arbol no vacio
	//devuelve por pantalla el arbol en postorden.
	public ArrayList<String> PostOrden(){
		if(estrucexp != null) orden(estrucexp);
		return postorden;
	}
	//pre: cierto
	//post: devuelve la cantidad de nodos de un arbol.
	public int cantidad() {
		cant = 0;
		cantidad(estrucexp);
		return cant;
	}
	//pre: cierto
	//post: devuleve la altura del arbol
	public int altura() {
		altura = 0;
		retornarAltura(estrucexp, 1);
		return altura;
	}
	//pre: cierto
	//post: devuleve cierto si el arbol es nulo, y falso si contiene algun valor.
	public boolean isnull()
	{
		if (estrucexp != null) return false;
		else return true;
	}


	//PRIVADAS
	private void retornarAltura(Nodo reco, int nivel) {
		if (reco != null) {
			retornarAltura(reco.getNodoIz(), nivel + 1);
			if (nivel > altura) {
				altura = nivel;
			}
			retornarAltura(reco.getNodoDer(), nivel + 1);
		}
	}
	private void orden(Nodo actual){
		if(actual != null & actual.getValor() == null)return;
		if(actual.getNodoIz()!= null) orden(actual.getNodoIz());
		if(actual.getNodoDer() != null)orden(actual.getNodoDer());
		postorden.add(actual.getValor());}
	private boolean comprueba(String exp) throws IOException{
		boolean comillas = false;
		int claves = 0;
		if (exp.isEmpty())return false;
		
		//>Implementar que operador en medio de espacios no pete.
		if(exp.length() == 1 && (exp.charAt(0) == '&' || exp.charAt(0) == '|' ||  exp.charAt(0) == '!' ))return false;
		if(cuenta_parentesis(exp)%2 != 0) return false;
		
		if(cuenta_operadores(exp) == 0 && (exp.charAt(0) != '{' || exp.charAt(exp.length()-1) !='}')&& (exp.charAt(0) != '"' || exp.charAt(exp.length()-1) !='"')){
			Frase f = new Frase(exp);
			if(f.midafrase() != 1) return false;
		}
		if (cuenta_operadores(exp) == 0 && cuenta_parentesis(exp) > 0) return false;
		if(exp.charAt(0) == '&' || exp.charAt(0) == '|' || exp.charAt(exp.length()-1) == '&' || exp.charAt(exp.length()-1) == '|') return false;
		for (int i = 0; i < exp.length();++i){
			if(exp.charAt(i) == '"'){
				if (!comillas)comillas = true;
				else comillas = false;
			}
			if(exp.charAt(i) == '{' ){
				++claves;
			}
			else if (exp.charAt(i) == '}' ){ 
				if(claves == 0)return false;
				--claves;
			}
			if (i != 0 && i != exp.length()-1 && (exp.charAt(i) != '&' && exp.charAt(i) != '|') && (exp.charAt(i-1) == ' ' && exp.charAt(i+1) == ' ') && !comillas) return false;
			if (exp.charAt(i) == '(' && exp.charAt(i+1) == ')' && !comillas) return false;
			if((exp.charAt(i) == '&' || exp.charAt(i) == '|') && (exp.charAt(i+1) != ' ' || exp.charAt(i-1) != ' ' )&& !comillas) return false;
			if(i != 0 &&exp.charAt(i) == '!' && (exp.charAt(i+1) == ' ' || exp.charAt(i-1) != ' ' )&& !comillas) return false;
			if(exp.charAt(i) == '{' && exp.charAt(i+1) == '}' && !comillas) return false;
			if(i != exp.length()-1 &&  exp.charAt(i) == '"' && exp.charAt(i+1) == '"') return false;

		}

		return true;


	}
	private Nodo analiza(Nodo actual, String expresion) throws IOException{			
		actual = new Nodo();
		if(cuenta_operadores(expresion) < 1){
			if(expresion.charAt(0) == '!'){
				if(actual.getValor() == null){
					actual.setValor(expresion.substring(0,1));
					actual.setNodoIzq(analiza(actual.getNodoIz(),expresion.substring(1,expresion.length())));
				}
			}
			else if(expresion.charAt(0) == '{' && expresion.charAt(expresion.length()-1) == '}'){
				return separador(actual,expresion.substring(1,expresion.length()-1));
			}
			else if(actual.getValor() == null){
				actual.setValor(expresion);
			}
			return actual;

		}
		else {
			if(expresion.charAt(0) == '!' &&  cuenta_conj(expresion) == 1 && expresion.charAt(expresion.length()-1) == ')' && expresion.charAt(1) == '(' ){
				actual.setValor("!");
				actual.setNodoIzq(analiza(actual.getNodoIz(), expresion.substring(1, expresion.length())));
				return actual;
			} else {

				int i = 0;
				int parentesis = 0;
				//comprobar expresion
				if (cuenta_parentesis(expresion) == 3){
					if (expresion.charAt(0) == '(' && expresion.charAt(1) == '(' ) expresion = expresion.substring(1,expresion.length());
					else if (expresion.charAt(expresion.length()-1) == ')' && expresion.charAt(expresion.length()-2) == ')' ) expresion = expresion.substring(1,expresion.length()-1);
				}
				if(parentesis_internos(expresion) && cuenta_conj(expresion) == 1 && expresion.charAt(expresion.length()-1) == ')' && expresion.charAt(0) == '(' ){
					expresion = expresion.substring(1,expresion.length()-1);

				}
				if (expresion.charAt(0) == '(' && expresion.charAt(expresion.length()-1) == ')' && (cuenta_parentesis(expresion) == 2)){
					expresion = expresion.substring(1,expresion.length()-1);}

				boolean b = false;
				while (!b && i < expresion.length()){
					if(expresion.charAt(i) == '(') ++parentesis;
					else if (expresion.charAt(i) == ')')--parentesis ;
					if ((expresion.charAt(i) == '&' || expresion.charAt(i) == '|') && parentesis == 0) b = true;
					else ++i;
				}
				if(i == expresion.length()){
					correcto = false;
					return null;
				}

				actual.setValor(expresion.substring(i,i+1));
				actual.setNodoIzq(analiza(actual.getNodoIz(),expresion.substring(0, i-1)));
				actual.setNodoDer(analiza(actual.getNodoDer(),expresion.substring(i+2, (expresion.length()))));
				return actual;
			}
		}


	}
	private Nodo separador (Nodo actual, String expresion) throws IOException {
		String delimitadores= "[ .,;?!\'\"\\[\\]]+";
		String[] pseparadas = expresion.split(delimitadores);
		Palabra auxp = new Palabra("&");
		ArrayList<String> aux = new ArrayList<String>();
		int j = 0;
		for(int i = 0; i < pseparadas.length;++i ){
			//if(pseparadas[i].charAt(pseparadas[i].length()-1) == ',')
			aux.add(pseparadas[i]);
			
			if(!aux.get(j).equals(auxp.palabra()) && i != pseparadas.length -1){
				aux.add(auxp.palabra());
				++j;
			}
			++j;
		}
		String ret = "";
		for(int i = 0; i <aux.size();++i){
		
			if(aux.size() >= 1 && i == 0 ) ret = aux.get(i) + " ";
			else if(i == 0) ret = aux.get(i);
			else if(i < aux.size()-1)ret += aux.get(i) + " ";
			else ret += aux.get(i);
		}
		System.out.println(ret);
		return analiza(actual,ret);

	}
	private void cantidad(Nodo reco) {
		if (reco != null) {
			cant++;
			cantidad(reco.getNodoIz());
			cantidad(reco.getNodoDer());
		}
	}
	private int cuenta_operadores(String expresion){
		int contador = 0;
		for(int i = 0; i < expresion.length();++i){
			if (expresion.charAt(i) == '&' || expresion.charAt(i) == '|') ++contador;
		}
		return contador;
	}
	private int cuenta_parentesis(String expresion){
		int contador = 0;
		for(int i = 0; i < expresion.length();++i){
			if (expresion.charAt(i) == '(' || expresion.charAt(i) == ')') ++contador;
		}
		return contador;
	}
	private boolean parentesis_internos(String expresion){
		int abierto = 0;
		for(int i = 0; i < expresion.length();++i){
			if (expresion.charAt(i) == '('){
				++abierto;
				if (abierto > 1) return true;
			} 
			else if(expresion.charAt(i) == ')'){
				--abierto;
			}
		}
		return false;


	}
	private int cuenta_conj(String expresion){
		boolean interno = false;
		int contadorint = 0;
		int contadorext = 0;
		for(int i = 0; i < expresion.length();++i){
			if (expresion.charAt(i) == '(' ){
				if(!interno)interno = true;
				else ++contadorint;
			}
			else if (expresion.charAt(i) == ')' && interno){
				if(contadorint == 0){
					++contadorext;
					interno = false;
				}
				else --contadorint;
			}
		}
		return contadorext;
	}
}
