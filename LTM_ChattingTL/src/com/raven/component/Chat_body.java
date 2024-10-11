/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.component;

import app.Message_type;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import com.raven.swing.Scrollbar;
import emoji.Emoji;
import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import model.Model_receive_message;
import model.Model_send_message;

/**
 *
 * @author DD
 */
public class Chat_body extends javax.swing.JPanel {

    /**
     * Creates new form Chat_body
     */
    public Chat_body() {
        initComponents();
        init();
    }

    private void init() {
        bodychat.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new Scrollbar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    //add emoji,ảnh
    public void Additemleft(Model_receive_message data) {
        if (data.getMessageType() == Message_type.TEXT) {
            Chat_left item = new Chat_left();
            item.Settext(data.getText());
            bodychat.add(item, "wrap, w 100::80%");
        } else if (data.getMessageType() == Message_type.EMOJI) {
            Chat_left item = new Chat_left();
            item.setEmoji(Emoji.getInstance().getImoji(Integer.valueOf(data.getText())).getIcon());
            bodychat.add(item, "wrap, w 100::80%");
        } else if (data.getMessageType() == Message_type.IMAGE) {
            Chat_left item = new Chat_left();
            item.Settext("");
            item.setImage(data.getDataImage());
            bodychat.add(item, "wrap, w 100::80%");
        }
        repaint();
        revalidate();
    }

    public void Additemleft(String text, String user, String[] image) {
        Chat_left_with_profile item = new Chat_left_with_profile();
        item.Settext(text);
        item.Setuserprofile(user);
        item.Setimage(image);
        item.Settime();
        bodychat.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        bodychat.repaint();
        bodychat.revalidate();
    }

    //add emoji
    public void Additemright(Model_send_message data) {
        if (data.getMessageType() == Message_type.TEXT) {
            Chat_right item = new Chat_right();
            item.Settext(data.getText());
            bodychat.add(item, "wrap, al right, w 100::80%");
        } else if (data.getMessageType() == Message_type.EMOJI) {
            Chat_right item = new Chat_right();
            item.setEmoji(Emoji.getInstance().getImoji(Integer.valueOf(data.getText())).getIcon());
            bodychat.add(item, "wrap, al right, w 100::80%");
        }
        else if (data.getMessageType() == Message_type.IMAGE) {
            Chat_right item = new Chat_right();
            item.Settext("");
            item.Setimage(data.getFile());
            bodychat.add(item, "wrap, al right, w 100::80%");
        }
//        else if (data.getMessageType() == Message_type.FILE) {
//            Chat_right item = new Chat_right();
//            item.Settext("");
//            item.SetFile(data.getFileFolder());
//            bodychat.add(item, "wrap, al right, w 100::80%");
//        }
        repaint();
        revalidate();
        ScrollToBottom();
    }

    public void AdditemFileright(String text, String fileName, String fileSize) {
        Chat_right item = new Chat_right();
        item.Settext(text);
//        item.Setimage(image);
        item.SetFile(fileName, fileSize);
        bodychat.add(item, "wrap, al right, w 100::80%");
        //  ::80% set max with 80%
        bodychat.repaint();
        bodychat.revalidate();
    }

    public void Adddate(String date) {
        Chat_datetime item = new Chat_datetime();
        item.setDate(date);
        bodychat.add(item, "wrap, al center");
        bodychat.repaint();
        bodychat.revalidate();
    }

    public void AddItemFile(String text, String user, String fileName, String fileSize) {
        Chat_left_with_profile item = new Chat_left_with_profile();
        item.Settext(text);
        item.SetFile(fileName, fileSize);
        item.Settime();
        item.Setuserprofile(user);
        bodychat.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        bodychat.repaint();
        bodychat.revalidate();
    }

    public void clearChat() {
        bodychat.removeAll();
        repaint();
        revalidate();
    }

    private void ScrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        bodychat = new javax.swing.JPanel();

        sp.setBackground(new java.awt.Color(242, 242, 242));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        bodychat.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout bodychatLayout = new javax.swing.GroupLayout(bodychat);
        bodychat.setLayout(bodychatLayout);
        bodychatLayout.setHorizontalGroup(
            bodychatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        bodychatLayout.setVerticalGroup(
            bodychatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        sp.setViewportView(bodychat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodychat;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
