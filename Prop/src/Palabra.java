import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Palabra {

	private String palabra;	
	//constructoras
	public Palabra(){
		palabra = "";
	}
	
	public Palabra (String pal) throws IOException{
		palabra = pal;		
	}
	
	
	//modificadora 
	
	public void modificar (String pal) throws IOException{
		
		palabra = pal;		
	} 
	
	
	//consultoras
	
	
	public boolean son_iguales(Palabra pal){
		
		return palabra.contentEquals(pal.palabra);
		
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
		
		BufferedReader in = new BufferedReader(new FileReader("empty.eng"));
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
		
		
		
		
		
		
		
		
}
