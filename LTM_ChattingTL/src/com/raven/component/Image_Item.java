package com.raven.component;

import public_event.EventImageReceiver;
import public_event.EventImageSender;
import service.Service;
import Blur_hash.Blur_hash;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.Model_image_receive;
import model.Model_image_receiver;
import model.Model_image_sender;


public class Image_Item extends javax.swing.JLayeredPane {

    public Image_Item() {
        initComponents();
    }

    //ảnh
    public void setImage(Icon image, Model_image_sender imageSender) {
        imageSender.addtEvent(new EventImageSender() {
            @Override
            public void onSending(double percentage) {
                loading.setValue((int) percentage);
            }

            @Override
            public void onStartSending() {
            }

            //load xong -> ẩn hình load
            @Override
            public void onFinish() {
                loading.setVisible(false);
            }
        });
        pic.setImage(image);
    }
    
    //ảnh
    public void setImage(Icon image, Model_image_receiver imageReceiver) {
        imageReceiver.addEvent(new EventImageReceiver() {
            @Override
            public void onReceiving(double percentage) {
                loading.setValue((int) percentage);
            }

            @Override
            public void onStartReceiving() {
            }

            //load xong -> ẩn hình load
            public void onFinish() {
                loading.setVisible(false);
            }

            @Override
            public void onFinish(File file) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        pic.setImage(image);
    }

    //ảnh
    public void setImage(Model_image_receive dataImage) {
        int width = dataImage.getWidth();
        int height = dataImage.getHeight();
        int[] data = Blur_hash.decode(dataImage.getImage(), width, height, 1);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        img.setRGB(0, 0, width, height, data, 0, width);
        Icon icon = new ImageIcon(img);
        pic.setImage(icon);
        try {
            Service.getInstance().addFileReceiver(dataImage.getFileID(), new EventImageReceiver() {
                @Override
                public void onReceiving(double percentage) {
                    loading.setValue((int) percentage);
                }

                @Override
                public void onStartReceiving() {

                }

                @Override
                public void onFinish(File file) {
                    loading.setVisible(false);
                    pic.setImage(new ImageIcon(file.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pic = new com.raven.swing.PictureBox();
        loading = new com.raven.swing.Progress();

        loading.setBorder(null);
        loading.setForeground(new java.awt.Color(255, 255, 255));
        loading.setProgressType(com.raven.swing.Progress.ProgressType.CANCEL);

        pic.setLayer(loading, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout picLayout = new javax.swing.GroupLayout(pic);
        pic.setLayout(picLayout);
        picLayout.setHorizontalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        picLayout.setVerticalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        setLayer(pic, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Progress loading;
    private com.raven.swing.PictureBox pic;
    // End of variables declaration//GEN-END:variables
}
