/*Eduard Gonzalez Moreno*/
package Interfaz;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import Prop.Domain_controller;
import Prop.Custom_exception;
import Prop.Documento;

/**
 *
 * @author Papilomavirus
 */
public class BSimFrame extends javax.swing.JFrame {

	Domain_controller estado = new Domain_controller();
	ArrayList<String> actual;
	ArrayList<ArrayList<String>> todos = new ArrayList<ArrayList<String>>();
	DefaultListModel<String> lista = new DefaultListModel<String>();
	   
	public BSimFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }
	public BSimFrame(Domain_controller estado)
	{
        this.actual = new ArrayList<String>();
		initComponents();
        setLocationRelativeTo(null);
        this.estado = estado;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	grupo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        titulo = new javax.swing.JTextField();
        autor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaDoc = new javax.swing.JList<String>();
        buscar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Consulta = new javax.swing.JButton();
        Modifica = new javax.swing.JButton();
        baja = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Kdoc = new javax.swing.JFormattedTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

      

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Titulo ");

        jLabel2.setText("Autor");
        setTitle("Gestor de Documentos");
        autor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autorActionPerformed(evt);
            }
        });

        jLabel3.setText("Introduce el titulo y autor deseado para buscar:");

        ListaDoc.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDoc.setToolTipText("");
        jScrollPane1.setViewportView(ListaDoc);

        buscar.setText("BUSCAR");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					buscarActionPerformed(evt);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jButton4.setText("Atras");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Consulta.setText("Consultar");
        Consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaActionPerformed(evt);
            }
        });

        Modifica.setText("Modificar");
        Modifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificaActionPerformed(evt);
            }
        });

        baja.setText("Baja");
        baja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					bajaActionPerformed(evt);
				} catch (Custom_exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jLabel6.setText("Metodo:");

        jLabel4.setText("Documentos que cumplen con la b�squeda:");

        jLabel5.setText("K documentos parecidos: ");
        jRadioButton1.setText("IDF smooth");
        jRadioButton1.setSelected(true);
        jRadioButton2.setText("IDF Probabilistico");
        
        grupo.add(jRadioButton1);
        grupo.add(jRadioButton2);

        Kdoc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(autor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                                            .addComponent(titulo, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buscar)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(Kdoc, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButton1)
                                            .addComponent(jRadioButton2))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Consulta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Modifica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(baja, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Kdoc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Consulta)
                    .addComponent(Modifica)
                    .addComponent(baja))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void autorActionPerformed(java.awt.event.ActionEvent evt) {                                      
    		
    }                                     
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	SelecBusqueda ven = new SelecBusqueda(estado);
        ven.setVisible(true);
        this.dispose();
    }   
    private void ConsultaActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if(ListaDoc.isSelectionEmpty())JOptionPane.showMessageDialog(null,"Ningun documento seleccionado", " Error", JOptionPane.ERROR_MESSAGE);
    	else {
    	ConsultaFrame ven = new ConsultaFrame(estado,todos.get(ListaDoc.getLeadSelectionIndex()));
        ven.setVisible(true); 
    	}
    }                                        

    private void ModificaActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if(ListaDoc.isSelectionEmpty())JOptionPane.showMessageDialog(null,"Ningun documento seleccionado", " Error", JOptionPane.ERROR_MESSAGE);
    	else {
    	ModificaFrame ven = new ModificaFrame(estado,todos.get(ListaDoc.getLeadSelectionIndex()));
        ven.setVisible(true); 
        this.setVisible(false);
    	}
    }                                        

    private void bajaActionPerformed(java.awt.event.ActionEvent evt) throws Custom_exception {                                     
    	if(ListaDoc.isSelectionEmpty())JOptionPane.showMessageDialog(null,"Ningun documento seleccionado", " Error", JOptionPane.ERROR_MESSAGE);
    	else{
    	try {
    		actual = todos.get(ListaDoc.getLeadSelectionIndex());
    	    estado.BAJA_DOC(actual.get(1), actual.get(0));
    	    todos.remove(ListaDoc.getLeadSelectionIndex());
    		lista.remove(ListaDoc.getLeadSelectionIndex());
    		ListaDoc.setModel(lista);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"ERROR DESCONOCIDO", " Error", JOptionPane.ERROR_MESSAGE);
		}
    	}
    }                                    

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) throws NumberFormatException, IOException {                                       
   	 if (titulo.getText().isEmpty())JOptionPane.showMessageDialog(null,"Campo Titulo vacio", " Error", JOptionPane.ERROR_MESSAGE);
        else if (autor.getText().isEmpty())JOptionPane.showMessageDialog(null,"Campo Autor vacio", " Error", JOptionPane.ERROR_MESSAGE);
        else if (Kdoc.getText().isEmpty())JOptionPane.showMessageDialog(null,"Campo \"K Documentos parecidos\" vacio", " Error", JOptionPane.ERROR_MESSAGE);
        else{
       	 /*0 autor
       	 1Titulo
       	 2 tema
       	 3 contenido*/
       	 int metodo;
       	 if(jRadioButton1.isSelected()) metodo = 1;
       	 else metodo = 2;
       	 try {
       		
       		 todos  = estado.BUSQUEDA_PARECIDO(titulo.getText(), autor.getText(), Integer.parseInt(Kdoc.getText()), metodo);

  	       		 lista.clear();
       		 for (int i = 0; i < todos.size();++i){
       		 actual = todos.get(i);
       		 lista.addElement("Titulo: "+ actual.get(1)+"\n" +"      Autor: "+ actual.get(0)+"\n");
       		 }
       		 ListaDoc.setModel(lista);
       	 }catch(Custom_exception e) {
       		 JOptionPane.showMessageDialog(null,e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
       	 }
        }
   }                                           

    

    // Variables declaration - do not modify                     
    private javax.swing.JButton Consulta;
    private javax.swing.JList<String> ListaDoc;
    private javax.swing.JButton Modifica;
    private javax.swing.JTextField autor;
    private javax.swing.JButton baja;
    private javax.swing.JButton buscar;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField titulo;
    private javax.swing.JFormattedTextField Kdoc;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration                   
}
