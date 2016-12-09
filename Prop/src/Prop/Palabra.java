package Prop;
//Creador: Eduard González Moreno


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Palabra implements java.io.Serializable {

	private String palabra;
	private String adelante;
	private String detras;
	//constructoras
	public Palabra(){
		palabra = null;
		adelante = "-1";
		detras = "-1";
	}

	public Palabra (String pal){
		if (comprobar(pal))palabra = pal.toLowerCase();	
		else palabra = null;
	}


	//modificadora 

	public void modificar (String pal) {
		if (comprobar(pal))palabra = pal.toLowerCase();	
		else palabra = null;
	} 


	//consultoras
	
	

	public int midapalabra() throws IOException {
		return palabra.length();
	}


	public String getAdelante() {
		return adelante;
	}

	public void setAdelante(String adelante) {
		this.adelante = adelante;
	}

	public String getDetras() {
		return detras;
	}

	public void setDetras(String detras) {
		this.detras = detras;
	}

	public boolean son_iguales(Palabra pal){

		return palabra.contentEquals(pal.palabra);

	}
	public boolean son_iguales(String pal){

		return palabra.contentEquals(pal);

	}

	public boolean esfuncional() throws IOException {
		return comproba_funcional();
	}


	//retorna la palabra.
	public String palabra() {

		return palabra;


	}
	public void escribir(){

		System.out.print(palabra);

	}


	// Mira si la palabra que esta en la carpeta es funcional o no. 
	private boolean comproba_funcional() throws IOException{

		BufferedReader in = new BufferedReader(new FileReader("empty.sp"));
		String funcional = in.readLine();

		while (funcional != null){

			if(funcional.equals(palabra)){

				in.close();
				return true;
			} 
			funcional = in.readLine();

		}
		in.close();
		return false;

	}
	private boolean comprobar(String pal){
		for (int i = 0;i < pal.length();++i){
			if(pal.charAt(i)== ' ')return false;

		}

		return true;


	}	







}
