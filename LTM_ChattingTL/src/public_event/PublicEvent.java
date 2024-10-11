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
public class PublicEvent {

    private static PublicEvent instance;
    private EventImageView eventImageView;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventMain eventMain;
    private EventMenuLeft eventMenuLeft;
    
    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }

    public void addEventImageView(EventImageView event) {
        this.eventImageView = event;
    }

    public void addEventChat(EventChat event) {
        this.eventChat = event;
    }
    
    public void addEventLogin(EventLogin event) {
        this.eventLogin = event;
    }
    
    public void addEventMain(EventMain event) {
        this.eventMain = event;
    }
    
    public void addEventMenuLeft(EventMenuLeft event) {
        this.eventMenuLeft = event;
    }

    public EventImageView getEventImageView() {
        return eventImageView;
    }

    public EventChat getEventChat() {
        return eventChat;
    }
    
    public EventLogin getEventLogin() {
        return eventLogin;
    }
    
    public EventMain getEventMain() {
        return eventMain;
    }
    
    public EventMenuLeft getEventMenuLeft() {
        return eventMenuLeft;
    }
}
