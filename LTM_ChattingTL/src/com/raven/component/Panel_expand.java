/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.component;

import app.Message_type;
import com.raven.swing.Scrollbar;
import com.raven.swing.WrapLayout;
import emoji.Emoji;
import emoji.Model_Emoji;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
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
public class Panel_expand extends javax.swing.JPanel {

    /**
     * Creates new form Panel_emoji_expand
     */
    public Panel_expand() {
        initComponents();
        init();
    }

    public Model_User_Account getUser() {
        return user;
    }

    private Model_User_Account user;

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

    private JPanel panelHeader;
    private JPanel panelDetail;

    private void init() {
        setLayout(new MigLayout("fillx"));
        panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.LINE_AXIS));
        panelHeader.add(getButtonFile());
        panelHeader.add(getButtonImage());
        panelHeader.add(getEmojiStyle1());
        panelHeader.add(getEmojiStyle2());
        add(panelHeader, "w 100%, h 30!, wrap");
        panelDetail = new JPanel();
        panelDetail.setLayout(new WrapLayout(WrapLayout.LEFT));
        JScrollPane ch = new JScrollPane(panelDetail);
        ch.setBorder(null);
        ch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ch.setVerticalScrollBar(new Scrollbar());
        add(ch, "w 100%, h 100%");
    }

    private JButton getEmojiStyle1() {
        Button_expand btnemo = new Button_expand();
//        btnemo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/icon/emoji/1.png")).getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH)));
        btnemo.setIcon(Emoji.getInstance().getImoji(1).toSize(25, 25).getIcon());
        btnemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //tô đậm emoji đang chọn
                clearSelected();
                btnemo.setSelected(true);
                panelDetail.removeAll();
                for (Model_Emoji d : Emoji.getInstance().getStyle1()) {
                    panelDetail.add(getButton(d));
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }
        });
        return btnemo;
    }

    private JButton getEmojiStyle2() {
        Button_expand btnemo1 = new Button_expand();
        btnemo1.setIcon(Emoji.getInstance().getImoji(21).toSize(25, 25).getIcon());
//        btnemo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/icon/emoji/21.png")).getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH)));
        btnemo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearSelected();
                btnemo1.setSelected(true);
                panelDetail.removeAll();
                for (Model_Emoji d : Emoji.getInstance().getStyle2()) {
                    panelDetail.add(getButton(d));
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }
        });
        return btnemo1;
    }

    private void clearSelected() {
        for (Component c : panelHeader.getComponents()) {
            if (c instanceof Button_expand) {
                ((Button_expand) c).setSelected(false);
            }
        }
    }

    private JButton getButton(Model_Emoji data) {
        JButton btn = new JButton(data.getIcon());
        btn.setName(data.getId() + "");
        btn.setBorder(new EmptyBorder(3, 3, 3, 3));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setContentAreaFilled(false);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Model_send_message message = new Model_send_message(Message_type.EMOJI, Service.getInstance().getUser().getUserID(), user.getUserID(), data.getId() + "");
                sendMessage(message);
                PublicEvent.getInstance().getEventChat().sendMessage(message);
            }
        });
        return btn;
    }

    private void sendMessage(Model_send_message data) {
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }

    //nút folder
    private JButton getButtonFile() {
        Button_expand btnfile = new Button_expand();
        btnfile.setIcon(new ImageIcon(getClass().getResource("/icon/folder.png")));
        btnfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(true);
                ch.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isDirectory() || isDocumentFile(file);
                    }

                    @Override
                    public String getDescription() {
                        return "Document File";
                    }
                });
                int option = ch.showOpenDialog(Main.getFrames()[0]);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File files[] = ch.getSelectedFiles();
                    try {
                        for (File file : files) {
                            Model_send_message message = new Model_send_message(Message_type.FILE, Service.getInstance().getUser().getUserID(), user.getUserID(), "");
                            Service.getInstance().addFile(file, message);
                            PublicEvent.getInstance().getEventChat().sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return btnfile;
    }

    //nút ảnh
    private JButton getButtonImage() {
        Button_expand btnimage = new Button_expand();
        btnimage.setIcon(new ImageIcon(getClass().getResource("/icon/gallery.png")));
        btnimage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(true);
                ch.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isDirectory() || isImageFile(file);
                    }

                    @Override
                    public String getDescription() {
                        return "Image File";
                    }
                });
                int option = ch.showOpenDialog(Main.getFrames()[0]);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File files[] = ch.getSelectedFiles();
                    try {
                        for (File file : files) {
                            Model_send_message message = new Model_send_message(Message_type.IMAGE, Service.getInstance().getUser().getUserID(), user.getUserID(), "");
                            Service.getInstance().addFile(file, message);
                            PublicEvent.getInstance().getEventChat().sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return btnimage;
    }

    //kiểm tra định dạng ảnh
    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }

    //kiểm tra định dạng file
    private boolean isDocumentFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".rar") || name.endsWith(".doc") || name.endsWith(".pdf");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
