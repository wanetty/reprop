/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Prop.Domain_controller;
import Prop.Exception_test;


public class CargaFrame extends javax.swing.JFrame {

	Domain_controller estado = new Domain_controller();

	public CargaFrame() {
		initComponents();
		setLocationRelativeTo(null);
	}
	public CargaFrame(Domain_controller estado) {
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

        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ruta = new javax.swing.JTextField();
        Recuperar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor de Documentos");
        jButton4.setText("Atras");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Ruta de archivo:");

        ruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rutaActionPerformed(evt);
            }
        });
        jButton2.setText("Explorar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton2ActionPerformed(evt);
				} catch (Exception_test e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        Recuperar.setText("Recuperar");
        Recuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecuperarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ruta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 183, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Recuperar)
                                .addGap(211, 211, 211))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(ruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(Recuperar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        ruta.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>                        

    private void RecuperarActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	if(!ruta.getText().isEmpty()){	
			estado.RECUPERAR(ruta.getText());
			JOptionPane.showMessageDialog(null, "Se ha dado de alta el archivo.", "Correcto",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
		 JOptionPane.showMessageDialog(null, "Campo Vacio", "Error",JOptionPane.ERROR_MESSAGE);
		}
    }                                         

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        StartWindow ven = new StartWindow(estado);
        ven.setVisible(true);
        this.dispose();
    }                                        

    private void rutaActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }      
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws Exception_test, IOException {     
    	JFileChooser explorador = new JFileChooser("/");
    	explorador.setDialogTitle("Abrir documento...");
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    	explorador.setFileFilter(filter);
    	int seleccion = explorador.showDialog(null, "Abrir!");
    	  
    	//analizamos la respuesta
    	switch(seleccion) {
    	case JFileChooser.APPROVE_OPTION:
    		ruta.setText(explorador.getSelectedFile().getPath());
        	break;

    	case JFileChooser.CANCEL_OPTION:
    	 break;

    	case JFileChooser.ERROR_OPTION:
    		JOptionPane.showMessageDialog(null, "Error desconocido", "Error",JOptionPane.ERROR_MESSAGE);
    	 break;
    	}
 
    	
    }  



    // Variables declaration - do not modify                     
    private javax.swing.JButton Recuperar;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField ruta;
    private javax.swing.JButton jButton2;
    // End of variables declaration                   
}
