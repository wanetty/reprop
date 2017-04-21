/*Eduard González Moreno*/
package Prop;

public class Nodo implements java.io.Serializable  {
	
		 
	    /**
	 * 
	 */
	private static final long serialVersionUID = -1820110035747029470L;
	/* Declaraciones de variables */
	 private String valor;
	 private Nodo izq;
	 private Nodo der;
	 
	 
	
	 public Nodo(){
		 valor = null;
		
	 }
	 public Nodo(String valor){
		 this.valor = valor;
		 
		 
	 }
	 
	 public Nodo getNodoIz(){
		 if(izq != null)return izq; 
		 else return null;
		 
	 }
	 public Nodo getNodoDer(){
		 if(der != null)return der; 
		 else return null;
	 }	
	 public String getValor(){
		if(valor != null) return valor;
		return valor; 
	
	 }
	 public void setValor(String valor){
		 this.valor = valor;
		 
	 } 
	 
	 public void setNodoDer(Nodo nodo){
		  der = nodo; 
	 }	 
	 public void setNodoIzq(Nodo nodo){
		 izq = nodo;
		 
	 }
	
	
	 
	}

