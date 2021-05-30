package com.example.netty.enjoy;

import org.msgpack.annotation.Message;

/**
 * <pre>
 *    描述：TODO
 * </pre>
 *
 * @author HDY
 * @version v1.0
 * @date 2020/9/9 22:05
 */
@Message
public class User {
    private String id;
    private String name;
    private int age;
    private UserContact userContact;
    private String newFeild;
    //开发新功能
    public User() {
    }

    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserContact getUserContact() {
        return userContact;
    }

    public void setUserContact(UserContact userContact) {
        this.userContact = userContact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNewFeild() {
        return newFeild;
    }

    public void setNewFeild(String newFeild) {
        this.newFeild = newFeild;
    }
}
