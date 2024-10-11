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
public class Model_receive_message {

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Model_image_receive getDataImage() {
        return dataImage;
    }

    public void setDataImage(Model_image_receive dataImage) {
        this.dataImage = dataImage;
    }

    public Model_receive_message(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            messageType = Message_type.toMessageType(obj.getInt("messageType"));
            fromUserID = obj.getInt("fromUserID");
            text = obj.getString("text");
            if (!obj.isNull("dataImage")) {
                dataImage = new Model_image_receive(obj.get("dataImage"));
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
    }

    private Message_type messageType;
    private int fromUserID;
    private String text;
    private Model_image_receive dataImage;

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("messageType", messageType.getValue());
            json.put("fromUserID", fromUserID);
            json.put("text", text);
            if (dataImage != null) {
                json.put("dataImage", dataImage.toJsonObject());
            }
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
