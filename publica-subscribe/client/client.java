package client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.text.BadLocationException;
import org.jsoup.nodes.Document;

public class client extends javax.swing.JFrame {

    private Connection connection = null;
    private Channel channel_in = null;
    private Channel channel_out= null;
    private String user = "";
    private client c = null;
                    
    
    //constructor
    public client(String user) throws Exception{
        initComponents();
        
        this.user = user;
        this.c = this;
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        this.connection = factory.newConnection();
        
        //conexion a server_out
        this.channel_in = connection.createChannel();
        this.channel_in.exchangeDeclare("server_out", "direct");
        this.channel_in.queueDeclare(user + "_in", false, false, false, null);
        this.channel_in.queueBind(user + "_in", "server_out", user);
        
        //channel_out
        this.channel_out = this.connection.createChannel();
        this.channel_out.queueDeclare("server_in", false, false, false, null);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaValores = new javax.swing.JTable();
        text_ValoresIbex35 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAlertas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        nuevaAlertaEmpresa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nuevaAlertaValor = new javax.swing.JTextField();
        checkNuevaAlertaCompra = new javax.swing.JCheckBox();
        checkNuevaAlertaVenta = new javax.swing.JCheckBox();
        create = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        alertaSeleccionada = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        notificaciones = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tablaValores.setModel(new ModeloTablaDatos());
        tablaValores.setToolTipText("");
        tablaValores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaValoresMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaValores);

        text_ValoresIbex35.setText("VALORES IBEX35");

        jLabel1.setText("ALERTAS ACTIVAS");

        jLabel2.setText("Usuario:");

        usuario.setEditable(false);
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        tablaAlertas.setModel(new ModeloTablaAlertas());
        jScrollPane2.setViewportView(tablaAlertas);

        jLabel3.setText("NUEVA ALERTA");

        jLabel4.setText("Empresa:");

        jLabel6.setText("Valor:");

        checkNuevaAlertaCompra.setText("Compra");
        checkNuevaAlertaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNuevaAlertaCompraActionPerformed(evt);
            }
        });

        checkNuevaAlertaVenta.setText("Venta");
        checkNuevaAlertaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNuevaAlertaVentaActionPerformed(evt);
            }
        });

        create.setText("Crear Alerta");
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });

        delete.setText("Borrar Alerta");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo de alerta:");

        jLabel7.setText("NOTIFICACIONES");

        notificaciones.setEditable(false);
        jScrollPane3.setViewportView(notificaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_ValoresIbex35)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(145, 145, 145)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuario))
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(alertaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(delete))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(nuevaAlertaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(nuevaAlertaValor, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(checkNuevaAlertaCompra)
                        .addGap(18, 18, 18)
                        .addComponent(checkNuevaAlertaVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(create))
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane3))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_ValoresIbex35)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(alertaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delete))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(nuevaAlertaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(nuevaAlertaValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(checkNuevaAlertaCompra)
                            .addComponent(checkNuevaAlertaVenta)
                            .addComponent(create))
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //BOTONES
    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioActionPerformed

    private void checkNuevaAlertaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNuevaAlertaCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkNuevaAlertaCompraActionPerformed

    private void checkNuevaAlertaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNuevaAlertaVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkNuevaAlertaVentaActionPerformed

    
    //CREATE AND DELETE ALERTAS
    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        //create new alert
        try {
            
            //tipo de alerta
            String type = "";
            if( checkNuevaAlertaCompra.isSelected() && !checkNuevaAlertaVenta.isSelected() )
                type = "compra";
            else 
            if( !checkNuevaAlertaCompra.isSelected() && checkNuevaAlertaVenta.isSelected() )
                type = "venta";
            else
                showMessageDialog(null, "Error en la seleccion del tipo de alerta.", "Error", ERROR_MESSAGE);
                
            String str = "add:" + this.usuario.getText() + "\n" +
                         nuevaAlertaEmpresa.getText() + "|" + type + "|" + nuevaAlertaValor.getText() + "\n";
            sendMsg(str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_createActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        //delete selected alert
        try {
            //create new alert
            String str = "delete:" + this.usuario.getText();
            sendMsg(str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_deleteActionPerformed

    
    //CLOSE CONNECTION
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            //notify closing to the server
            String str = "desconectar:" + this.user;
            this.channel_out.basicPublish("", "server_in", null, str.getBytes());
            
            //close connection
            this.channel_in.queueDelete(this.user + "_in");
            this.channel_in.close();
            this.connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    //SELECT VALUE
    private void tablaValoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaValoresMousePressed
        // TODO add your handling code here:
        this.nuevaAlertaEmpresa.setText((String) tablaValores.getValueAt( tablaValores.getSelectedRow() , 0));
    }//GEN-LAST:event_tablaValoresMousePressed

    
    //MAIN
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //create window
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { 
                    
                    //user
                    String name = JOptionPane.showInputDialog("Type your name please");
                    
                    //crear ventana
                    client c = new client(name);
                    c.setVisible(true); 
                    c.setUser(name);
                    
                    //recibimos datos
                    c.recvMsg();
                    
                    //mandar mensaje conexion al servidor
                    c.sendMsg("connection:" + name);
                    
                
                } catch(Exception ex) { }
            }
        });
    }
    
    //SERVER COMINICATION
    private void sendMsg( String msg ) throws Exception{
            // publicamos el mensaje en la cola
            this.channel_out.basicPublish("", "server_in", null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Enviado '" + msg + "'");       
    }

    private void recvMsg() throws Exception {
        
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                String message = new String(delivery.getBody(), "UTF-8");
                String header = message.split("\n", 2)[0];
                String action = header.split(":")[0];
                String data = "";
                
                if(message.split("\n",2).length > 1)
                    data   = message.split("\n", 2)[1];
                
                System.out.println(" [.] Recibido '" + message + "'");
                
                switch(action){
                    case "values":
                        ModeloTablaDatos md = (ModeloTablaDatos) tablaValores.getModel();
                        md.setData(data);
                        break;
                    case "notificacion":
                        javax.swing.text.Document doc = notificaciones.getDocument();
                        doc.insertString(doc.getLength(),">" + data + "\n", null);
                        break;
                        
                    case "alertas":
                        ModeloTablaAlertas ma = (ModeloTablaAlertas) tablaAlertas.getModel();
                        ma.setData(data);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
                       
        };
        this.channel_in.basicConsume(this.user + "_in", true, deliverCallback, consumerTag -> { });
    }
    
    //SETTERS GETTERS
    public void setUser(String user){
        this.usuario.setText(user);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alertaSeleccionada;
    private javax.swing.JCheckBox checkNuevaAlertaCompra;
    private javax.swing.JCheckBox checkNuevaAlertaVenta;
    private javax.swing.JButton create;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane notificaciones;
    private javax.swing.JTextField nuevaAlertaEmpresa;
    private javax.swing.JTextField nuevaAlertaValor;
    private javax.swing.JTable tablaAlertas;
    private javax.swing.JTable tablaValores;
    private javax.swing.JLabel text_ValoresIbex35;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
