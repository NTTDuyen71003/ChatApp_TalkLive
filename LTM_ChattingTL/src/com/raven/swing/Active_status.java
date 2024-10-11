/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author DD
 */
public class Active_status extends Component{
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }

    private boolean active;

    public Active_status() {
        setPreferredSize(new Dimension(8, 8));
    }

    @Override
    public void paint(Graphics grphcs) {
        if (active) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0,102,0));
            g2.fillOval(0, (getHeight() / 2) - 4, 8, 8);
        }
    }
}
