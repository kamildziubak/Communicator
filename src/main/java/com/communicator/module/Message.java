package com.communicator.module;

import java.util.GregorianCalendar;

public class Message {
    Integer msg_id;
    String text;
    GregorianCalendar date;
    User send_by, send_to;
    Boolean isRead;

    public Message(Integer msg_id, String text, GregorianCalendar date, User send_by, User send_to, Boolean isRead) {
        this.msg_id = msg_id;
        this.text = text;
        this.date = date;
        this.send_by = send_by;
        this.send_to = send_to;
        this.isRead=isRead;
    }

    public Message(String text, User send_by, User send_to)
    {
        this.text = text;
        this.send_by = send_by;
        this.send_to = send_to;
    }

    public Integer getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public User getSend_by() {
        return send_by;
    }

    public void setSend_by(User send_by) {
        this.send_by = send_by;
    }

    public User getSend_to() {
        return send_to;
    }

    public void setSend_to(User send_to) {
        this.send_to = send_to;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void read() {
        isRead=true;
    }
}
