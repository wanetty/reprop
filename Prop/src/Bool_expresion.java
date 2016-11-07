import java.io.IOException;



public class Bool_expresion {
	//creadoras

	private Nodo estrucexp;
	private int cant;
	private int altura;

	//Inicializadora de variable poniendo valores por defecto
	public Bool_expresion() throws IOException{
		estrucexp = new Nodo();
	}

	//inicializadora de variable contruyendo el arbol segun la expresion que nos pasan
	public Bool_expresion(String expresion) throws IOException{
		if (!expresion.equals(""))this.estrucexp = analiza(estrucexp,expresion);	
	}
	//consultoras
	//pre : cierto
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
	public void PostOrden(){
		if(estrucexp != null) orden(estrucexp);
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
		System.out.println(actual.getValor());
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
					estrucexp = new Nodo();
					return null;
				}

				actual.setValor(expresion.substring(i,i+1));
				/*System.out.println(actual.getValor());
				System.out.println(actual.getNodoIz());
				System.out.println(actual.getNodoDer());*/
				actual.setNodoIzq(analiza(actual.getNodoIz(),expresion.substring(0, i-1)));
				actual.setNodoDer(analiza(actual.getNodoDer(),expresion.substring(i+2, (expresion.length()))));
				/*System.out.println(actual.getNodoIz().getValor());
				System.out.println(actual.getNodoDer().getValor());*/
				return actual;
			}
		}


	}
	private void cantidad(Nodo reco) {
		if (reco != null) {
			cant++;
			cantidad(reco.getNodoIz());
			cantidad(reco.getNodoDer());
		}
	}

	/*
	private boolean comprobar_conjunto(Frase frase, String expresion) throws IOException{

		//revisar
		expresion = expresion.substring(1,expresion.length()-1);
		Frase  aux = new Frase(expresion);
		int k = aux.midafrase();
		boolean[] compro= new boolean[k];
		//System.out.println(k);
		//aux.escribirfrase();
		//System.out.println(frase.midafrase());
		for(int i = 0;i < aux.midafrase();++i){
			for(int j = 0; j < frase.midafrase();++j){
				//System.out.print("auxiliar "+aux.getfrase(i).palabra());
				//System.out.println(" frase " +frase.getfrase(j).palabra()+" numero "+k );

				if(!compro[i] && aux.getfrase(i).son_iguales(frase.getfrase(j))){--k; compro[i] = true;};

				if (k == 0) return true;

			}
			//System.out.println(k);


		}
		return false;


	}
	private boolean comprobar_sequencia(Frase frase, String expresion) throws IOException{
		expresion = expresion.substring(1,expresion.length()-1);
		Frase aux = new Frase(expresion);
		boolean seguido = false;;
		int j = 0;
		int i = 0;
		//frase.escribirfrase();
		//ystem.out.println(k);
		//System.out.println(frase.midafrase());
		while(i < aux.midafrase() && j < frase.midafrase())
		{
			if (aux.getfrase(i).son_iguales(frase.getfrase(j))){
				++i;
				seguido = true;
			}
			else {
				seguido = false;
				i = 0;
			}
			++j;
		}
		return seguido;


	}
	private boolean comprobar_palabra(Frase frase, String expresion) throws IOException{
		Palabra pal = new Palabra(expresion);

		for(int i = 0;i < frase.midafrase();++i){
			if (frase.getfrase(i).son_iguales(pal))return true;
		}
		return false;
	}
	private boolean comprobar_negacion(Frase frase, String expresion) throws IOException{
		//System.out.println(expresion);
		expresion = expresion.substring(1,expresion.length());
		if(cuenta_operadores(expresion) < 1){
			if(expresion.charAt(0) == '{') return !comprobar_conjunto(frase, expresion);
			else if (expresion.charAt(0) == '"') return !comprobar_sequencia(frase, expresion);
			else  return !comprobar_palabra(frase,expresion);

		}else {

			return !analiza(actual,expresion);

		}


	}	*/
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
