package Interfaz;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Prop.Domain_controller;
import Prop.Exception_test;




public class AltaTxtFrame extends javax.swing.JFrame {
	Domain_controller estado = new Domain_controller();
	   
	public AltaTxtFrame() {
        initComponents();
        setLocationRelativeTo(null);
        this.estado = null;
    }
	public AltaTxtFrame(Domain_controller estado) {
        initComponents();
        setLocationRelativeTo(null);
        this.estado = estado;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ruta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton4.setText("Atras");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Ruta de archivo:");

        jButton1.setText("Dar de Alta!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                                .addComponent(jButton1)
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
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        ruta.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        StartWindow ven = new StartWindow(estado);
        ven.setVisible(true);
        this.dispose();
    }  
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	try {
        if(!ruta.getText().isEmpty()){	
        	try {
				estado.Crear_raiz(ruta.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	JOptionPane.showMessageDialog(null, "Se ha dado de alta el archivo.", "Correcto",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
         JOptionPane.showMessageDialog(null, "Campo Vacio", "Error",JOptionPane.ERROR_MESSAGE);
        }
    	}catch (Exception_test e) {
    		JOptionPane.showMessageDialog(null, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
    		
    	}
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

    


             
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField ruta;
    private javax.swing.JButton jButton2;
                      
}
