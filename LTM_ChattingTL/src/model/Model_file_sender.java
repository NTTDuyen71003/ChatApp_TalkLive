/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import service.Service;
import io.socket.client.Ack;
import io.socket.client.Socket;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author DD
 */
public class Model_file_sender {

    public Model_send_message getMessage() {
        return message;
    }

    public void setMessage(Model_send_message message) {
        this.message = message;
    }

    public int getFileID() {
        return fileFolderID;
    }

    public void setFileID(int fileID) {
        this.fileFolderID = fileID;
    }

    public String getFileFolderExtensions() {
        return fileFolderExtensions;
    }

    public void setFileFolderExtensions(String fileExtensions) {
        this.fileFolderExtensions = fileExtensions;
    }

    public File getFileFolder() {
        return fileFolder;
    }

    public void setFileFolder(File file) {
        this.fileFolder = file;
    }

    public long getFileSize() {
        return fileFolderSize;
    }

    public void setFileSize(long fileSize) {
        this.fileFolderSize = fileSize;
    }

    public RandomAccessFile getAccFile() {
        return accFolderFile;
    }

    public void setAccFile(RandomAccessFile accFile) {
        this.accFolderFile = accFile;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Model_file_sender(File file, Socket socket, Model_send_message message) throws IOException {
        accFolderFile = new RandomAccessFile(file, "r");
        this.fileFolderSize = file.length();
        this.socket = socket;
        this.message = message;
        fileFolderExtensions = getExtensions(file.getName());
        fileFolderSize = accFolderFile.length();
    }

    public Model_file_sender() {
    }
    

    private Model_send_message message;
    private int fileFolderID;
    private String fileFolderExtensions;
    private File fileFolder;
    private long fileFolderSize;
    private RandomAccessFile accFolderFile;
    private Socket socket;

    public synchronized byte[] readFile() throws IOException {
        long filepointer = accFolderFile.getFilePointer();
        if (filepointer != fileFolderSize) {
            int max = 2000;
            long length = filepointer + max >= fileFolderSize ? fileFolderSize - filepointer : max;
            byte[] data = new byte[(int) length];
            accFolderFile.read(data);
            return data;
        } else {
            return null;
        }
    }

    public void initSendFile() throws IOException {
        System.out.println("Đợi phản hồi...");
        socket.emit("send_to_user", message.toJsonObject(), new Ack() {
            @Override
            public void call(Object... os) {
                if (os.length > 0) {
                    int fileID = (int) os[0];
                    try {
                        startSend(fileID);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void startSend(int fileID) throws IOException {
        this.fileFolderID = fileID;
        sendingFile();
    }

    private void sendingFile() throws IOException {
        Model_package_file_sender data = new Model_package_file_sender();
        data.setFileID(fileFolderID);
        byte[] bytes = readFile();
        if (bytes != null) {
            data.setData(bytes);
            data.setFinish(false);
        } else {
            data.setFinish(true);
            close();
        }
        socket.emit("send_file", data.toJsonObject(), new Ack() {
            @Override
            public void call(Object... os) {
                if (os.length > 0) {
                    boolean act = (boolean) os[0];
                    if (act) {
                        try {
                            if (!data.isFinish()) {
                                sendingFile();
                            } else {
                                //  File send finish
                                Service.getInstance().filefolderSendFinish(Model_file_sender.this);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public double getPercentage() throws IOException {
        double percentage;
        long filePointer = accFolderFile.getFilePointer();
        percentage = filePointer * 100 / fileFolderSize;
        return percentage;
    }

    public void close() throws IOException {
        accFolderFile.close();
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }
}
