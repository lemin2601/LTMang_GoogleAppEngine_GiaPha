/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Key {
    long id;
    long id_user;
    String key;

    public Key(long id, long id_user, String key) {
        this.id = id;
        this.id_user = id_user;
        this.key = key;
    }

    public Key() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Key{" + "id=" + id + ", id_user=" + id_user + ", key=" + key + '}';
    }
    
}
