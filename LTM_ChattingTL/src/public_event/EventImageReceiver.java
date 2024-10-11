/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package public_event;

import java.io.File;

/**
 *
 * @author DD
 */
public interface EventImageReceiver {

    public void onReceiving(double percentage);

    public void onStartReceiving();

    public void onFinish(File file);
}
