package com.raven.component;

import java.awt.Color;
import javax.swing.Icon;
import model.Model_file_sender;
import model.Model_image_sender;

public class Chat_right extends javax.swing.JLayeredPane {

    public Chat_right() {
        initComponents();
        txt.setBackground(new Color(168, 213, 186));
    }

    public void Settext(String text) {
        if (text.equals("")) {
            txt.Hidetext();
        } else {
            txt.Settext(text);
        }
        txt.Seen();
    }

    public void Setimage(Model_image_sender imageSender) {
        txt.Setimage(true, imageSender);
    }
    
    public void Setimage(String... image) {
//        txt.Setimage(true, image);
    }

    public void Settime() {
        txt.Settime("10:30 PM");    //  Testing
    }
    
//    public void SetFile(Model_file_sender fileSender) {
//        txt.SetFile(true, fileSender);
//    }
    
    public void SetFile(String fileName, String fileSize) {
        txt.SetFile(fileName, fileSize);
    }
    
    public void setEmoji(Icon icon) {
        txt.Hidetext();
        txt.setEmoji(true, icon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.raven.component.Chat_item();

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.Chat_item txt;
    // End of variables declaration//GEN-END:variables
}
