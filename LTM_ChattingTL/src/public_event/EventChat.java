/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package public_event;

import model.Model_receive_message;
import model.Model_send_message;

/**
 *
 * @author DD
 */
public interface EventChat {

    public void sendMessage(Model_send_message data);

    public void receiveMessage(Model_receive_message data);
}
