package service;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import model.Model_User_Account;
import model.Model_file_sender;
import model.Model_image_receiver;
import model.Model_image_sender;
import model.Model_receive_message;
import model.Model_send_message;
import public_event.EventImageReceiver;
import public_event.PublicEvent;

public class Service {

    private static Service instance;
    private Socket client;
    private final int PORT_NUMBER = 1234;
    //code kết nối máy chủ
    private final String IP = "192.168.31.140"; 
//    private final String IP = "localhost";
    private Model_User_Account user;
    //ảnh
    private List<Model_image_sender> imageSender;
    private List<Model_image_receiver> imageReceiver;
    //file
    private List<Model_file_sender> fileSender;
    

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    private Service() {
        imageSender = new ArrayList<>();
        imageReceiver = new ArrayList<>();
        fileSender = new ArrayList<>();      
    }

    //ảnh
    public Model_image_sender addFile(File file, Model_send_message message) throws IOException {
        Model_image_sender data = new Model_image_sender(file, client, message);
        message.setFile(data);
        imageSender.add(data);
        //  Gửi từng file
        if (imageSender.size() == 1) {
            data.initSend();
        }
        return data;
    }

    //file
    public Model_file_sender addFileFolder(File folder, Model_send_message message) throws IOException {
        Model_file_sender data = new Model_file_sender(folder, client, message);
        message.setFileFolder(data);
        fileSender.add(data);
        //  Gửi từng file
        if (imageSender.size() == 1) {
            data.initSendFile();
        }
        return data;
    }

    public void fileSendFinish(Model_image_sender data) throws IOException {
        imageSender.remove(data);
        if (!imageSender.isEmpty()) {
            // Gửi file mới ghi file trc đã dc gửi đi
            imageSender.get(0).initSend();
        }
    }
    
    public void filefolderSendFinish(Model_file_sender data) throws IOException {
        fileSender.remove(data);
        if (!fileSender.isEmpty()) {
            // Gửi file mới ghi file trc đã dc gửi đi
            fileSender.get(0).initSendFile();
        }
    }

    public void startServer() {
        try {
            client = IO.socket("http://" + IP + ":" + PORT_NUMBER);
            client.on("list_user", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    //  list user
                    List<Model_User_Account> users = new ArrayList<>();
                    for (Object o : os) {
                        Model_User_Account u = new Model_User_Account(o);
                        if (u.getUserID() != user.getUserID()) {
                            users.add(u);
                        }
                    }
                    PublicEvent.getInstance().getEventMenuLeft().newUser(users);
                }
            });
            client.on("user_status", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    int userID = (Integer) os[0];
                    boolean status = (Boolean) os[1];
                    if (status) {
                        //  kết nối
                        PublicEvent.getInstance().getEventMenuLeft().userConnect(userID);
                    } else {
                        //  hủy kết nối
                        PublicEvent.getInstance().getEventMenuLeft().userDisconnect(userID);
                    }
                }
            });
            client.on("receive_ms", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    Model_receive_message message = new Model_receive_message(os[0]);
                    PublicEvent.getInstance().getEventChat().receiveMessage(message);
                }
            });
            client.open();
        } catch (URISyntaxException e) {
            error(e);
        }
    }

    public Socket getClient() {
        return client;
    }

    private void error(Exception e) {
        System.err.println(e);
    }

    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }
    
    public void fileReceiveFinish(Model_image_receiver data) throws IOException {
        imageReceiver.remove(data);
        if (!imageReceiver.isEmpty()) {
            imageReceiver.get(0).initReceive();
        }
    }

    public void addFileReceiver(int fileID, EventImageReceiver event) throws IOException {
        Model_image_receiver data = new Model_image_receiver(fileID, client, event);
        imageReceiver.add(data);
        if (imageReceiver.size() == 1) {
            data.initReceive();
        }
    }
}
