package p1;

import java.net.*;
import java.io.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class window extends javax.swing.JFrame {

    //declaracion nombres variables
    MulticastSocket s = null;
    InetAddress group = null;
    
    //constructor
    public window() {
        initComponents();
    }

    //start
    public void start(){
        
        try{
            
            //connect
            this.group = InetAddress.getByName("224.0.0.100");
            this.s = new MulticastSocket(6703);
            this.s.joinGroup(group);
            
            //declare variable names
            byte[] buffer = new byte[1000];
            DatagramPacket messageIn = null;
            
            //loop
            while(true){
                messageIn = new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                
                // imprimir mensaje
                chat.append( new String(messageIn.getData(), 0, messageIn.getLength()) + "\n");
            }
            
        }catch(SocketException e){ System.out.println("Socket: " + e.getMessage());
        }catch(IOException e){ System.out.println("IO: " + e.getMessage());
        }finally{ if(s != null) s.close(); }
        
    }
    
    //iniciar componentes graficos
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatLabel = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat = new javax.swing.JTextArea();
        message = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatLabel.setText("CHAT");

        username.setPreferredSize(new java.awt.Dimension(75, 30));
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
        });

        usernameLabel.setText("USERNAME");

        chat.setEditable(false);
        chat.setColumns(20);
        chat.setRows(5);
        jScrollPane1.setViewportView(chat);

        message.setPreferredSize(new java.awt.Dimension(75, 30));
        message.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chatLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(usernameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel)
                    .addComponent(chatLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //EVENTOS -----------------------------
    
    //evento enter message box
    private void messageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageKeyPressed
        
        //enter
        if(evt.getKeyCode() == 10){
            
            //comprobamos contenido
            if(!username.getText().isEmpty()){
                try{
            
                //conseguir mensaje de textbox
                String msg = username.getText() + "> " + message.getText();
                byte [] m = msg.getBytes();
            
                //mandamos mensaje
                DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6703);
                this.s.send(messageOut);
            
                //borramos textbox
                message.setText("");
            
                }catch(SocketException e){ System.out.println("Socket: " + e.getMessage());
                }catch(IOException e){ System.out.println("IO: " + e.getMessage());}
           
            }
            else{
                //mensaje error
                showMessageDialog(null, "Introduce Username");
            }
        }
        
    }//GEN-LAST:event_messageKeyPressed

    //evento enter username box
    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        
        //detectamos enter
        if(evt.getKeyCode() == 10){
            
            //comprobamos contenido
            if(!username.getText().isEmpty()){
                
                //mensaje de conexion
                chat.append( "- " + username.getText() + " has connected -\n");
                username.setEditable(false);
                message.requestFocus();
            }
            else{
                //mensaje de error
                showMessageDialog(null, "Invalid Username");
            }
        }
        
    }//GEN-LAST:event_usernameKeyPressed

    
    //MAIN --------------------------------
    
    //main
    public static void main(String args[]) {
        
        //configuracion de la ventana
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
            java.util.logging.Logger.getLogger(window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        window win = new window();
        
        //crear ventana
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                win.setVisible(true);
            }
        });
        
        win.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chat;
    private javax.swing.JLabel chatLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField message;
    private javax.swing.JTextField username;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
