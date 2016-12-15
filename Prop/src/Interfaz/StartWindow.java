/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javax.swing.JOptionPane;

import Prop.Domain_controller;

/**
 *
 * @author Eduard
 */
public class StartWindow extends javax.swing.JFrame {


	private Domain_controller estado = new Domain_controller();

	public StartWindow() {
		initComponents();
		setLocationRelativeTo(null);
	}
	public StartWindow(Domain_controller estado) {
		initComponents();
		setLocationRelativeTo(null);
		this.estado = estado;
	}
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	 private void initComponents() {

        AltaDoc = new javax.swing.JButton();
        BusquedasDoc = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        GuardarSalir = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GESTOR DE DOCUMENTOS");

        AltaDoc.setText("ALTA DOCUMENTOS");
        AltaDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AltaDocActionPerformed(evt);
            }
        });

        BusquedasDoc.setText("BUSQUEDAS DE DOCUMENTOS");
        BusquedasDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusquedasDocActionPerformed(evt);
            }
        });

        Salir.setText("Salir sin guardar");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        GuardarSalir.setText("Guardar y salir");
        GuardarSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarSalirActionPerformed(evt);
            }
        });

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        jButton1.setText("Cargar archivo");
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
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AltaDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BusquedasDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(GuardarSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Salir)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AltaDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BusquedasDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(Guardar)
                    .addComponent(GuardarSalir)
                    .addComponent(Salir))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }

	private void AltaDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AltaDocActionPerformed
		AltaDocumentoFrame ven = new AltaDocumentoFrame(estado);
		ven.setVisible(true);
		this.setVisible(false);
	}

	private void BusquedasDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusquedasDocActionPerformed
		SelecBusqueda ven = new SelecBusqueda(estado);
		ven.setVisible(true);
		setVisible(false);
	}
	private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusquedasDocActionPerformed
		GuardarFrame ven = new GuardarFrame(estado);
		ven.setVisible(true);
	}
	private void GuardarSalirActionPerformed(java.awt.event.ActionEvent evt) {                                             
		GuardarySalirFrame ven = new GuardarySalirFrame(estado);
		ven.setVisible(true);
		this.dispose();
	}
	private void SalirActionPerformed(java.awt.event.ActionEvent evt) {                                      
		Object [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(rootPane,"Esta seguro que desea salir sin guardar?","Mensaje de Confirmacion",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
		if (eleccion == JOptionPane.YES_OPTION)
		{
			this.dispose();

		}else {
		}

	}  
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
		CargaFrame ven = new CargaFrame(estado);
		ven.setVisible(true);
		this.dispose();
	}       


	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StartWindow().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton AltaDoc;
	private javax.swing.JButton BusquedasDoc;
	private javax.swing.JButton Guardar;
	private javax.swing.JButton GuardarSalir;
	private javax.swing.JButton Salir;
	private javax.swing.JButton jButton1;
	// End of variables declaration//GEN-END:variables
}
