package com.example.netty.enjoy;

import org.msgpack.annotation.Message;

/**
 * <pre>
 *    描述：TODO
 * </pre>
 *
 * @author HDY
 * @version v1.0
 * @date 2020/9/9 22:04
 */
@Message
public class UserContact {
    private String mail;
    private String phone;
    private String diercitijiao;
    //修改一个bug
    public UserContact() {
    }

    public UserContact(String mail, String phone) {
        this.mail = mail;
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiercitijiao() {
        return diercitijiao;
    }

    public void setDiercitijiao(String diercitijiao) {
        this.diercitijiao = diercitijiao;
    }
}
