/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package public_event;

/**
 *
 * @author DD
 */
public interface EventImageSender {
    
    public void onSending(double percentage);

    public void onStartSending();

    public void onFinish();
}
