/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import app.Message_type;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DD
 */
public class Model_send_message {

    public Message_type getMessageType() {
        return messageType;
    }

    public void setMessageType(Message_type messageType) {
        this.messageType = messageType;
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Model_image_sender getFile() {
        return file;
    }

    public void setFile(Model_image_sender file) {
        this.file = file;
    }
    
    public Model_file_sender getFileFolder() {
        return folder;
    }

    public void setFileFolder(Model_file_sender file) {
        this.folder = folder;
    }

    public Model_send_message(Message_type messageType, int fromUserID, int toUserID, String text) {
        this.messageType = messageType;
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.text = text;
        
    }

    public Model_send_message() {
    }

    private Message_type messageType;
    private int fromUserID;
    private int toUserID;
    private String text;
    //ảnh
    private Model_image_sender file;
    //file
    private Model_file_sender folder;

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("messageType", messageType.getValue());
            json.put("fromUserID", fromUserID);
            json.put("toUserID", toUserID);
            //ảnh
            if (messageType == Message_type.FILE || messageType == Message_type.IMAGE) {
                json.put("text", file.getFileExtensions());
            } else if(messageType == Message_type.FILE){
                json.put("text", folder.getFileFolderExtensions());}
            else {
                json.put("text", text);
            }
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
