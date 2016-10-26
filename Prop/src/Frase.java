import java.io.IOException;
import java.util.ArrayList;

public class Frase {
	private ArrayList<Palabra> frase;
	
	//constructoras
	public Frase(){
		frase = ArrayList<Palabra>();	
	}
	
	public Palabra (String pal) throws IOException{
		palabra = pal;
		funcional = esfuncional();
		
	}
	
	
	//modificadora 
	
	public void modificar (String pal) throws IOException{
		
		palabra = pal;
		funcional = esfuncional();
		
	} 
}
