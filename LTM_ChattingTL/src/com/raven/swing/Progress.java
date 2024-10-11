/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.swing;

import javax.swing.JProgressBar;

/**
 *
 * @author DD
 */
public class Progress extends JProgressBar {

    public ProgressType getProgressType() {
        return progressType;
    }

    public void setProgressType(ProgressType progressType) {
        this.progressType = progressType;
        repaint();
    }

    private ProgressType progressType = ProgressType.NONE;
    
    public static enum ProgressType {
        NONE, DOWN_FILE, CANCEL, FILE
    }
    
    public Progress() {
        setOpaque(false);
        setUI(new ProgressCircleUI(this));
    }
}
