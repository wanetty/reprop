/*Eduard Gonzalez Moreno*/

package Interfaz;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Prop.Custom_exception;
import Prop.Domain_controller;

/**
 *
 * @author Papilomavirus
 */
public class BBoolFrame extends javax.swing.JFrame {

	Domain_controller estado = new Domain_controller();
	ArrayList<String> actual = new ArrayList<String>();
	ArrayList<ArrayList<String>> todos = new ArrayList<ArrayList<String>>();
	DefaultListModel<String> lista = new DefaultListModel<String>();
	   
	public BBoolFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }
	public BBoolFrame(Domain_controller estado) {
        initComponents();
        setLocationRelativeTo(null);
        this.estado = estado;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jLabel2 = new javax.swing.JLabel();
        bool = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaDoc = new javax.swing.JList<String>();
        buscar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Consulta = new javax.swing.JButton();
        Modifica = new javax.swing.JButton();
        baja = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");
        setTitle("Gestor de Documentos");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Expresion a tratar: ");

        bool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	boolActionPerformed(evt);
            }
        });

        jLabel3.setText("Introduce la expresion booleana para buscar:");

        ListaDoc.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDoc.setToolTipText("");
        jScrollPane1.setViewportView(ListaDoc);

        buscar.setText("BUSCAR");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					buscarActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Custom_exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
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

        jLabel4.setText("Documentos que cumplen con la b�squeda:");

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
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bool, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(buscar, javax.swing.GroupLayout.Alignment.TRAILING)))))))
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
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(bool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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

    private void boolActionPerformed(java.awt.event.ActionEvent evt) {                                      

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

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) throws Exception {                                       
   	 if (bool.getText().isEmpty())JOptionPane.showMessageDialog(null,"Campo de expresion vacio", " Error", JOptionPane.ERROR_MESSAGE);
        else{
       	 /*0 autor
       	 1Titulo
       	 2 tema
       	 3 contenido*/
        try {
	       	 todos  = estado.BUSQUEDA_BOOLEANA(bool.getText());
	       	if(todos.isEmpty()){
       		 JOptionPane.showMessageDialog(null,"No hay documentos que cumplan esta expresion.", " Error", JOptionPane.ERROR_MESSAGE);
       		 lista.clear();
       		 ListaDoc.setModel(lista);
       	 }
       	 else { 
	       		 lista.clear();
	       	 for (int i = 0; i < todos.size();++i){
	       		 actual = todos.get(i);
	
	       		 lista.addElement("Titulo: "+ actual.get(1)+"\n" +"      Autor: "+ actual.get(0)+"\n");
	       	 }
       	 ListaDoc.setModel(lista);
        }	
        }catch(Custom_exception e) {
        	JOptionPane.showMessageDialog(null,e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e) {
        	JOptionPane.showMessageDialog(null,"Expresi�n incorrecta", " Error", JOptionPane.ERROR_MESSAGE);
        }
        }
   }    
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton Consulta;
    private javax.swing.JList<String> ListaDoc;
    private javax.swing.JButton Modifica;
    private javax.swing.JTextField bool;
    private javax.swing.JButton baja;
    private javax.swing.JButton buscar;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
