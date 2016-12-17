/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class BAllFrame extends javax.swing.JFrame {

	Domain_controller estado = new Domain_controller();
	ArrayList<ArrayList<String>> todos = new ArrayList<ArrayList<String>>();
	ArrayList<String> actual;
	   
	public BAllFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }
	public BAllFrame(Domain_controller estado)
	{
		this.estado = estado;
        this.actual = new ArrayList<String>();
		initComponents();
		llenartodos();
        setLocationRelativeTo(null);
        
    }

	private void llenartodos(){
    	 DefaultListModel<String> lista = new DefaultListModel<String>();
    	 try {
			todos  = estado.ALL_DOCS();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 for (int i = 0; i < todos.size();++i){
    		 actual = todos.get(i);
    		 lista.addElement("Titulo: "+ actual.get(1)+"\n" +"      Autor: "+ actual.get(0)+"\n");
    	 }
    	 ListaDoc.setModel(lista);

	}
	
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaDoc = new javax.swing.JList<String>();
        jButton4 = new javax.swing.JButton();
        Consulta = new javax.swing.JButton();
        Modifica = new javax.swing.JButton();
        baja = new javax.swing.JButton();
        setTitle("Gestor de Documentos");
        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ListaDoc.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDoc.setToolTipText("");
        jScrollPane1.setViewportView(ListaDoc);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Consulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Modifica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(baja, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
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
			estado.BAJA_DOC(actual.get(1), actual.get(0));
			actual = null;
			ListaDoc.remove(ListaDoc.getLeadSelectionIndex());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"ERROR DESCONOCIDO", " Error", JOptionPane.ERROR_MESSAGE);
		}
    	}
    }                                    

                                       

    

    // Variables declaration - do not modify                     
    private javax.swing.JButton Consulta;
    private javax.swing.JList<String> ListaDoc;
    private javax.swing.JButton Modifica;
    private javax.swing.JButton baja;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
