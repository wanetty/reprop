package Interfaz;

import Prop.Domain_controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class SelecBusqueda extends javax.swing.JFrame {

	Domain_controller estado = new Domain_controller();
	   
	public SelecBusqueda() {
        initComponents();
        setLocationRelativeTo(null);
    }
	public SelecBusqueda(Domain_controller estado) {
        initComponents();
        setLocationRelativeTo(null);
        this.estado = estado;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        atras = new javax.swing.JButton();
        Autor = new javax.swing.JButton();
        Titulo = new javax.swing.JButton();
        tema = new javax.swing.JButton();
        fecha = new javax.swing.JButton();
        similitud = new javax.swing.JButton();
        booleana = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TitAut = new javax.swing.JButton();
        booleana1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor de Documentos");
        setResizable(false);

        atras.setText("Atras");
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });

        Autor.setText("Autor");
        Autor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutorActionPerformed(evt);
            }
        });

        Titulo.setText("Título");
        Titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TituloActionPerformed(evt);
            }
        });

        tema.setText("Tema");
        tema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temaActionPerformed(evt);
            }
        });

        fecha.setText("Fecha");
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });

        similitud.setText("Similitud");
        similitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                similitudActionPerformed(evt);
            }
        });

        booleana.setText("Booleana");
        booleana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booleanaActionPerformed(evt);
            }
        });

        jLabel1.setText("ESCOGE UNA TIPO DE BÚSQUEDA:");

        TitAut.setText("Titulo y Autor");
        TitAut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TitAutActionPerformed(evt);
            }
        });

        booleana1.setText("Todo el contenido");
        booleana1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booleana1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(atras, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tema, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(Autor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(booleana1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(similitud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(booleana, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TitAut, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Autor, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(similitud, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(booleana, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tema, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(booleana1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TitAut, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(atras)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }/// </editor-fold>                        

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {                                      
        StartWindow ven = new StartWindow(estado);
        ven.setVisible(true);
        this.dispose();
    }                                     

    private void AutorActionPerformed(java.awt.event.ActionEvent evt) {                                      
    	BAutFrame ven = new BAutFrame(estado);
        ven.setVisible(true);
        setVisible(false);
    }                                     

    private void TituloActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	BTitFrame ven = new BTitFrame(estado);
        ven.setVisible(true);
        setVisible(false);
    }                                      

    private void booleanaActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	BBoolFrame ven = new BBoolFrame(estado);
        ven.setVisible(true);
        setVisible(false);
    }                                        

    private void similitudActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	BSimFrame ven = new BSimFrame(estado);
        ven.setVisible(true);
        setVisible(false);
    }                                         

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {                                      
    	BFechFrame ven = new BFechFrame(estado);
        ven.setVisible(true);
        setVisible(false);
    }                                     

    private void temaActionPerformed(java.awt.event.ActionEvent evt) {                                     
    	BTemFrame ven = new BTemFrame(estado);
        ven.setVisible(true);
        setVisible(false);
    }              
    private void TitAutActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	BTitAutFrame ven = new BTitAutFrame(estado);
        ven.setVisible(true);
        setVisible(false);
    }                                      
    private void booleana1ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	BAllFrame ven = new BAllFrame(estado);
        ven.setVisible(true);
        setVisible(false);
    }                        
       

    // Variables declaration - do not modify                     
    private javax.swing.JButton Autor;
    private javax.swing.JButton Titulo;
    private javax.swing.JButton atras;
    private javax.swing.JButton booleana;
    private javax.swing.JButton TitAut;
    private javax.swing.JButton fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton similitud;
    private javax.swing.JButton tema;
    private javax.swing.JButton booleana1;
    // End of variables declaration                   
}
