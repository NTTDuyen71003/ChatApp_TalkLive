package com.raven.form;

import com.raven.component.People_display;
import com.raven.swing.JIMSendTextPane;
import net.miginfocom.swing.MigLayout;
import com.raven.swing.Scrollbar;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import model.Model_User_Account;
import public_event.EventMenuLeft;
import public_event.PublicEvent;

public class Menu_Left extends javax.swing.JPanel {

    private List<Model_User_Account> userAccount;

    public Menu_Left() {
        initComponents();
        init();
        sp.setVerticalScrollBar(new Scrollbar());

    }

    private void init() {
        menuList.setLayout(new MigLayout());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
        userAccount = new ArrayList<>();
        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {
            @Override
            public void newUser(List<Model_User_Account> users) {
                for (Model_User_Account d : users) {
                    userAccount.add(d);
                    menuList.add(new People_display(d), "wrap");
                    refreshMenuList();
                }
            }

            @Override
            public void userConnect(int userID) {
                for (Model_User_Account u : userAccount) {
                    if (u.getUserID() == userID) {
                        u.setStatus(true);
                        PublicEvent.getInstance().getEventMain().updateUser(u);
                        break;
                    }
                }
                if (menuchat.isSelected()) {
                    for (Component com : menuList.getComponents()) {
                        People_display item = (People_display) com;
                        if (item.getUser().getUserID() == userID) {
                            item.updateStatus();
                            break;
                        }
                    }
                }
            }

            @Override
            public void userDisconnect(int userID) {
                for (Model_User_Account u : userAccount) {
                    if (u.getUserID() == userID) {
                        u.setStatus(false);
                        PublicEvent.getInstance().getEventMain().updateUser(u);
                        break;
                    }
                }
                if (menuchat.isSelected()) {
                    for (Component com : menuList.getComponents()) {
                        People_display item = (People_display) com;
                        if (item.getUser().getUserID() == userID) {
                            item.updateStatus();
                            break;
                        }
                    }
                }
            }
        });
        showPeople();
    }

    private void showPeople() {
        menuList.removeAll();
        for (Model_User_Account d : userAccount) {
            menuList.add(new People_display(null), "wrap");
        }
        refreshMenuList();
    }

    private void showGroup() {
        //  test data
        menuList.removeAll();
        for (int i = 1; i < 20; i++) {
            menuList.add(new People_display(null), "wrap");
        }
        refreshMenuList();
    }

    private void showNotifi() {
        //  test data
        menuList.removeAll();
        for (int i = 1; i < 20; i++) {
            menuList.add(new People_display(null), "wrap");
        }
        refreshMenuList();
    }

    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuchat = new com.raven.component.Menubutton();
        menugroup = new com.raven.component.Menubutton();
        menunotifi = new com.raven.component.Menubutton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();
        txtsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        menu1 = new javax.swing.JLayeredPane();
        menuexit = new com.raven.component.Menubutton();

        menu.setBackground(new java.awt.Color(255, 255, 255));
        menu.setOpaque(true);
        menu.setLayout(new java.awt.GridLayout(1, 0));

        menuchat.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/chat.png"))); // NOI18N
        menuchat.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/icon/chat_before.png"))); // NOI18N
        menuchat.setSelected(true);
        menuchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuchatActionPerformed(evt);
            }
        });
        menu.add(menuchat);

        menugroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/group.png"))); // NOI18N
        menugroup.setEnabled(false);
        menugroup.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/message.png"))); // NOI18N
        menugroup.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/icon/group.png"))); // NOI18N
        menugroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menugroupActionPerformed(evt);
            }
        });
        menu.add(menugroup);

        menunotifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/notification-bell_before.png"))); // NOI18N
        menunotifi.setEnabled(false);
        menunotifi.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/notification-bell.png"))); // NOI18N
        menunotifi.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/icon/notification-bell_before.png"))); // NOI18N
        menunotifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menunotifiActionPerformed(evt);
            }
        });
        menu.add(menunotifi);

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        txtsearch.setText("Tìm kiếm");
        txtsearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtsearchFocusGained(evt);
            }
        });

        btnsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/circle.png"))); // NOI18N
        btnsearch.setContentAreaFilled(false);
        btnsearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        menu1.setBackground(new java.awt.Color(255, 255, 255));
        menu1.setOpaque(true);
        menu1.setLayout(new java.awt.GridLayout(1, 3));

        menuexit.setBackground(new java.awt.Color(255, 255, 255));
        menuexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        menuexit.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/chat.png"))); // NOI18N
        menuexit.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/icon/chat_before.png"))); // NOI18N
        menuexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuexitActionPerformed(evt);
            }
        });
        menu1.add(menuexit);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtsearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(menu1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtsearch))
                .addGap(18, 18, 18)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuchatActionPerformed
        // TODO add your handling code here:
        if (!menuchat.isSelected()) {
            menuchat.setSelected(true);
            menugroup.setSelected(false);
            menunotifi.setSelected(false);
            showPeople();
        }
    }//GEN-LAST:event_menuchatActionPerformed

    private void menugroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menugroupActionPerformed
        // TODO add your handling code here:
        if (!menugroup.isSelected()) {
            menuchat.setSelected(false);
            menugroup.setSelected(true);
            menunotifi.setSelected(false);
            showGroup();
        }
    }//GEN-LAST:event_menugroupActionPerformed

    private void menunotifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menunotifiActionPerformed
        // TODO add your handling code here:
        if (!menunotifi.isSelected()) {
            menuchat.setSelected(false);
            menugroup.setSelected(false);
            menunotifi.setSelected(true);
            showNotifi();
        }
    }//GEN-LAST:event_menunotifiActionPerformed

    private void menuexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuexitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuexitActionPerformed

    private void txtsearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsearchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchFocusGained

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsearch;
    private javax.swing.JLayeredPane menu;
    private javax.swing.JLayeredPane menu1;
    private javax.swing.JLayeredPane menuList;
    private com.raven.component.Menubutton menuchat;
    private com.raven.component.Menubutton menuexit;
    private com.raven.component.Menubutton menugroup;
    private com.raven.component.Menubutton menunotifi;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
