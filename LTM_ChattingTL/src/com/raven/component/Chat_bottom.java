/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.component;

import app.Message_type;
import com.raven.swing.JIMSendTextPane;
import com.raven.swing.Scrollbar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import main.Main;
import model.Model_User_Account;
import model.Model_send_message;
import net.miginfocom.swing.MigLayout;
import public_event.PublicEvent;
import service.Service;

/**
 *
 * @author DD
 */
public class Chat_bottom extends javax.swing.JPanel {

    /**
     * Creates new form Chat_title
     */
    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
        panelEmoji.setUser(user);
    }

    private Model_User_Account user;

    public Chat_bottom() {
        initComponents();
        init();
    }

    private void init() {
        mig = new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2[]0");
        setLayout(mig);
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
                if (ke.getKeyChar() == 10 && ke.isControlDown()) {
                    eventSend(txt);
                }
            }
        });
        txt.setBorder(new EmptyBorder(5, 5, 5, 5));
        txt.setHintText("Hãy viết tin nhắn vào đây...");
        scroll.setViewportView(txt);
        Scrollbar sb = new Scrollbar();
        sb.setBackground(new Color(229, 229, 229));
        sb.setPreferredSize(new Dimension(2, 10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]20[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.WHITE);
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/icon/sendmsg.png")));
        txt.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "sendMessage");
        txt.getActionMap().put("sendMessage", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventSend(txt); // Gọi hàm gửi tin nhắn
            }
        });
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eventSend(txt);
            }
        });
        JButton btnemo = new JButton();
        btnemo.setBorder(null);
        btnemo.setContentAreaFilled(false);
        btnemo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnemo.setIcon(new ImageIcon(getClass().getResource("/icon/application_color.png")));
        btnemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (panelEmoji.isVisible()) {
                    btnemo.setIcon(new ImageIcon(getClass().getResource("/icon/application_color.png")));
                    panelEmoji.setVisible(false);
                    mig.setComponentConstraints(panelEmoji, "dock south,h 0!");
                    revalidate();
                } else {
                    btnemo.setIcon(new ImageIcon(getClass().getResource("/icon/application.png")));
                    panelEmoji.setVisible(true);
                    mig.setComponentConstraints(panelEmoji, "dock south,h 170!");
                    revalidate();
                }
            }
        });
        //hiển thị icon file
//        JButton btnfile = new JButton();
//        btnfile.setBorder(null);
//        btnfile.setContentAreaFilled(false);
//        btnfile.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btnfile.setIcon(new ImageIcon(getClass().getResource("/icon/folder.png")));
//        btnfile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                JFileChooser ch = new JFileChooser();
//                ch.showOpenDialog(Main.getFrames()[0]);
//                // Comming soon!
//            }
//        });
        //hiển thị icon ảnh
//        JButton btnimage = new JButton();
//        btnimage.setBorder(null);
//        btnimage.setContentAreaFilled(false);
//        btnimage.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btnimage.setIcon(new ImageIcon(getClass().getResource("/icon/gallery.png")));
//        btnimage.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                JFileChooser ch = new JFileChooser();
//                ch.showOpenDialog(Main.getFrames()[0]);
//                // Comming soon!
//            }
//        });
//        panel.add(btnfile);
//        panel.add(btnimage);
        panel.add(btnemo);
        panel.add(cmd);
        add(panel, "wrap");
        panelEmoji = new Panel_expand();
        panelEmoji.setVisible(false);
        add(panelEmoji, "dock south,h 0!");
    }

    private void eventSend(JIMSendTextPane txt) {
        String text = txt.getText().trim();
        if (!text.equals("")) {
            Model_send_message message = new Model_send_message(Message_type.TEXT, Service.getInstance().getUser().getUserID(), user.getUserID(), text);
            send(message);
            PublicEvent.getInstance().getEventChat().sendMessage(message);
            txt.setText("");
            txt.grabFocus();
            refresh();
        } else {
            txt.grabFocus();
        }
    }

    private void send(Model_send_message data) {
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }

    private void refresh() {
        revalidate();
    }

    private MigLayout mig;
    private Panel_expand panelEmoji;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
