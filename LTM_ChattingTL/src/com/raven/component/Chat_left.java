package com.raven.component;

import java.awt.Color;
import javax.swing.Icon;
import model.Model_image_receive;
import model.Model_receive_message;


public class Chat_left extends javax.swing.JLayeredPane {

    public Chat_left() {
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

    //ảnh
    public void Setimage(Icon... image) {
        //txt.Setimage(false, image);
        //Comming soon
    }

    public void setImage(Model_image_receive dataImage) {
        txt.setImage(false, dataImage);
    }

    public void Settime() {
        txt.Settime("10:30 PM");    //  Testing
    }

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
