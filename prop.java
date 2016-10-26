
public class Palabra {
	
	private String palabra;
	private boolean funcional;
	
	//constructoras
	public Palabra(){
		palabra = "";
		funcional = false;	
	}
	
	public Palabra (String pal){
		palabra = pal;
		funcional = esfuncional();
		
	}
	
	
	//modificadora 
	
	public void modificar (String pal){
		
		palabra = pal;
		funcional = esfuncional();
		
	} 
	
	
	//consultoras
	
	
	public boolean son_iguales(Palabra pal){
		
		return palabra.contentEquals(pal.palabra);
		
	}
	
	public boolean esfuncional() {
		return funcional;
	}
	//retorna la palabra.
	public String palabra() {
		
		return palabra;
		
		
	}
	public void escribir(){
		
		System.out.print(palabra);
		
	}
	
	
	
	
	
	
}
