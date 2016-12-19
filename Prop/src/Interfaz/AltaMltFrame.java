/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Prop.Custom_exception;
import Prop.Domain_controller;


public class AltaMltFrame extends javax.swing.JFrame {

	private Domain_controller estado = new Domain_controller();

	public AltaMltFrame() {
		initComponents();
		setLocationRelativeTo(null);
	}
	public AltaMltFrame(Domain_controller estado) {
		initComponents();
		setLocationRelativeTo(null);
		this.estado = estado;
	}
    @SuppressWarnings("unchecked")                         
    private void initComponents() {
    	setTitle("Gestor de Documentos");

        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Explorar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
               " Documentos a dar de Alta"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jButton4.setText("Atras");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Dar de Alta!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }                      

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       JFileChooser selector=new JFileChooser();
				selector.setMultiSelectionEnabled(true);
				int r=selector.showOpenDialog(null);
				if(r==JFileChooser.APPROVE_OPTION){
					File archivos[]=selector.getSelectedFiles();
					int i=0;
					boolean max = false;
					for(File f:archivos){
						if(!max)tabla.setValueAt(f.getPath(), i, 0);
						else {}
						if(i > 100){
							JOptionPane.showMessageDialog(null, "Maximos documentos seleccionados.", "Error",JOptionPane.ERROR_MESSAGE);
							max = true;
							break;
						}
						i++;
					}
					i=0;
				}
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        AltaDocumentoFrame ven = new AltaDocumentoFrame(estado);
        ven.setVisible(true);
        this.dispose();
    }                                    

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
      int i= 0; 
      int fail = 0; 
    	while (tabla.getValueAt(i, 0) != null){
    		try {
				estado.Crear_raiz(tabla.getValueAt(i, 0).toString());
			}catch(Custom_exception e) {
				JOptionPane.showMessageDialog(null,e.getMessage() +"\n"+ tabla.getValueAt(i, 0).toString() , " Error", JOptionPane.ERROR_MESSAGE);
				++fail;
			} catch (IOException e) {
				e.printStackTrace();
			}
    		tabla.setValueAt(null, i, 0);
    		++i;
       }
    	int tot = i - fail;
    	JOptionPane.showMessageDialog(null, "Se ha dado de alta " + tot +  " archivos de los " + i + " propuestos.", "Correcto",JOptionPane.INFORMATION_MESSAGE);
    }                                        

  

                   
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;                 
}
