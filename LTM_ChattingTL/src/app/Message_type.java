/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author DD
 */
//enum: định nghĩa một tập hợp các hằng số có tên
public enum Message_type {

    TEXT(1), EMOJI(2), FILE(3), IMAGE(4);

    private final int value;

    public int getValue() {
        return value;
    }

    private Message_type(int value) {
        this.value = value;
    }

    public static Message_type toMessageType(int value) {
        if (value == 1) {
            return TEXT;
        } else if (value == 2) {
            return EMOJI;
        } else if (value == 3) {
            return FILE;
        } else {
            return IMAGE;
        }
    }
}
