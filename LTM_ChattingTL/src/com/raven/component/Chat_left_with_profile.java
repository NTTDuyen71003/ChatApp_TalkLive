package com.raven.component;

import java.awt.Color;
import javax.swing.Icon;

public class Chat_left_with_profile extends javax.swing.JLayeredPane {

    public Chat_left_with_profile() {
        initComponents();
        txt.setBackground(new Color(255, 255, 255));
    }

    public void Settext(String text) {
        if (text.equals("")) {
            txt.Hidetext();
        } else {
            txt.Settext(text);
        }
    }
    
    public void Setuserprofile(String user) {
        txt.Setuserprofile(user);
    }
    
    public void Setimageprofile(Icon image) {
        Imimage.setImage(image);
    }
    
    public void Setimage(Icon... image) {
        //txt.Setimage(false, image);
        //Comming soon!
    }
    
    public void Setimage(String... image) {
//        txt.Setimage(false, image);
    }

    public void Settime() {
        txt.Settime("10:30 PM");    //  Testing
    }
    
    public void SetFile(String fileName, String fileSize) {
        txt.SetFile(fileName, fileSize);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        Imimage = new com.raven.swing.Avatar_image();
        txt = new com.raven.component.Chat_item();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        Imimage.setBorderSize(0);
        Imimage.setImage(new javax.swing.ImageIcon(getClass().getResource("/img_test/jiyan.jpg"))); // NOI18N
        Imimage.setMaximumSize(new java.awt.Dimension(35, 35));
        Imimage.setMinimumSize(new java.awt.Dimension(35, 35));
        Imimage.setPreferredSize(new java.awt.Dimension(35, 35));

        jLayeredPane1.setLayer(Imimage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(Imimage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(Imimage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jLayeredPane1);
        add(txt);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Avatar_image Imimage;
    private javax.swing.JLayeredPane jLayeredPane1;
    private com.raven.component.Chat_item txt;
    // End of variables declaration//GEN-END:variables
}
