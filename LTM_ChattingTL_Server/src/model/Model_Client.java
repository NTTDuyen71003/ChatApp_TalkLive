/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.corundumstudio.socketio.SocketIOClient;

/**
 *
 * @author DD
 */
public class Model_Client {

    public SocketIOClient getClient() {
        return client;
    }

    public void setClient(SocketIOClient client) {
        this.client = client;
    }

    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

    public Model_Client(SocketIOClient client, Model_User_Account user) {
        this.client = client;
        this.user = user;
    }

    public Model_Client() {
    }

    private SocketIOClient client;
    private Model_User_Account user;
}
