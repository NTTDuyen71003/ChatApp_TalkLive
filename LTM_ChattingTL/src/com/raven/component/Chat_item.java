package com.raven.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;
import model.Model_file_sender;
import model.Model_image_receive;
import model.Model_image_receiver;
import model.Model_image_sender;


public class Chat_item extends javax.swing.JLayeredPane {

    public Chat_item() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
    }

    public void Settext(String text) {
        txt.setText(text);
    }

    private JLabel label;

    public void Settime(String time) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        layer.setBorder(new EmptyBorder(0, 5, 10, 5));
        label = new JLabel(time);
        label.setForeground(new Color(110, 110, 110));
        label.setHorizontalTextPosition(JLabel.LEFT);
        layer.add(label);
        add(layer);
    }

    public void Sendsuccess() {
        if (label != null) {
            label.setIcon(new ImageIcon(getClass().getResource("/icon/tick.png")));
        }
    }

    public void Seen() {
        if (label != null) {
            label.setIcon(new ImageIcon(getClass().getResource("/icon/double-check.png")));
        }
    }

    //ảnh
    public void Setimage(boolean right, Model_image_sender imageSender) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 0, 5));
        Chat_image chatImage = new Chat_image(right);
        chatImage.Addimage(imageSender);
        layer.add(chatImage);
        add(layer);
    }
    

    //event ảnh
    public void setImage(boolean right, Model_image_receive dataImage) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 0, 5));
        Chat_image chatImage = new Chat_image(right);
        chatImage.addImage(dataImage);
        layer.add(chatImage);
        add(layer);
    }

    public void Hidetext() {
        txt.setVisible(false);
    }

    //file
//    public void SetFile(boolean right, Model_file_sender fileSender) {
//        JLayeredPane layer = new JLayeredPane();
//        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
//        layer.setBorder(new EmptyBorder(0, 5, 0, 5));
//
//        Chat_file chatFile = new Chat_file(right);
//
//        // Gọi setFile với hai tham số String
//        chatFile.setFile(fileSender.getFileName(), fileSender.getFileSize());
//
//        layer.add(chatFile);
//        add(layer);
//    }

    public void SetFile(String fileName, String fileSize) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 0, 5));
        Chat_file chatFile = new Chat_file();
        chatFile.setFile(fileName, fileSize);
        layer.add(chatFile);
        add(layer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.raven.swing.JIMSendTextPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        txt.setSelectionColor(new java.awt.Color(92, 188, 255));
        add(txt);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        if (getBackground() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        }
        super.paintComponent(grphcs);
    }

    public void Setuserprofile(String user) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        layer.setBorder(new EmptyBorder(10, 10, 0, 10));
        JButton cmd = new JButton(user);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setFocusable(false);
        cmd.setForeground(new Color(235, 52, 73));
        cmd.setFont(new java.awt.Font("sansserif", 1, 13));
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        layer.add(cmd);
        add(layer, 0);
    }

    //add emoji
    public void setEmoji(boolean right, Icon icon) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 0, 5));
        layer.add(new JLabel(icon));
        add(layer);
        setBackground(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.JIMSendTextPane txt;
    // End of variables declaration//GEN-END:variables
}
