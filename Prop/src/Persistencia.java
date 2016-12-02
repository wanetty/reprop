

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Persistencia  {
	
	String ruta;
	
	public Persistencia(){
		ruta = null;
	}
	
	
	
	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public void guardar(Cjt_documentos conj) throws FileNotFoundException, IOException{
		
		ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(ruta));
	      salida.writeObject(conj);
	      salida.close();
	}
	
	public Cjt_documentos recuperar () throws ClassNotFoundException{
	  try { 
	ObjectInputStream entrada =new ObjectInputStream(new FileInputStream(ruta));
     Cjt_documentos conj = (Cjt_documentos) entrada.readObject();
     entrada.close();
   return conj;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	


	}




