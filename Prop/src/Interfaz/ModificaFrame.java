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
 * @author Papilomavirus
 */
public class ModificaFrame extends javax.swing.JFrame {

	Domain_controller estado = new Domain_controller();

	public ModificaFrame() {
		initComponents();
		setLocationRelativeTo(null);
	}
	public ModificaFrame(Domain_controller estado) {
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
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		titulo = new javax.swing.JTextField();
		autor = new javax.swing.JTextField();
		tema = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		contenido = new javax.swing.JEditorPane();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("ALTA MANUAL");

		jButton4.setText("Atras");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jLabel1.setText("Titulo");

		jLabel2.setText("Autor");

		jLabel3.setText("Tema");

		jLabel4.setText("Contenido");

		titulo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tituloActionPerformed(evt);
			}
		});

		autor.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				autorActionPerformed(evt);
			}
		});

		tema.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				temaActionPerformed(evt);
			}
		});

		jScrollPane1.setViewportView(contenido);

		jButton1.setText("Guardar");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(layout.createSequentialGroup()
														.addComponent(jLabel1)
														.addGap(18, 18, 18)
														.addComponent(titulo))
												.addGroup(layout.createSequentialGroup()
														.addComponent(jLabel2)
														.addGap(18, 18, 18)
														.addComponent(autor))
												.addGroup(layout.createSequentialGroup()
														.addComponent(jLabel3)
														.addGap(18, 18, 18)
														.addComponent(tema, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addComponent(jLabel4)
														.addGap(296, 296, 296))
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addContainerGap())
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(183, 183, 183)
														.addComponent(jButton4)
														.addGap(33, 33, 33))))))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(35, 35, 35)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1)
								.addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2)
								.addComponent(autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3)
								.addComponent(tema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(35, 35, 35)
						.addComponent(jLabel4)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
										.addComponent(jButton4)
										.addContainerGap())
								.addGroup(layout.createSequentialGroup()
										.addGap(18, 18, 18)
										.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				);

		pack();
	}// </editor-fold>                        

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Object [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(rootPane,"Esta seguro que desea salir sin guardar?","Mensaje de Confirmacion",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
		if (eleccion == JOptionPane.YES_OPTION)
		{
			StartWindow ven = new StartWindow(estado);
			ven.setVisible(true);
			this.dispose();
			
		}else{
			
		}

		}                                        

		private void tituloActionPerformed(java.awt.event.ActionEvent evt) {                                       
			// TODO add your handling code here:
		}                                      

		private void autorActionPerformed(java.awt.event.ActionEvent evt) {                                      
			// TODO add your handling code here:
		}                                     

		private void temaActionPerformed(java.awt.event.ActionEvent evt) {                                     
			// TODO add your handling code here:
		}                                    




		// Variables declaration - do not modify                     
		private javax.swing.JTextField autor;
		private javax.swing.JEditorPane contenido;
		private javax.swing.JButton jButton1;
		private javax.swing.JButton jButton4;
		private javax.swing.JLabel jLabel1;
		private javax.swing.JLabel jLabel2;
		private javax.swing.JLabel jLabel3;
		private javax.swing.JLabel jLabel4;
		private javax.swing.JScrollPane jScrollPane1;
		private javax.swing.JTextField tema;
		private javax.swing.JTextField titulo;
		// End of variables declaration                   
	}
