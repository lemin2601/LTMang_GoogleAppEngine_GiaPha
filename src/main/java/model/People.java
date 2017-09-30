/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class People {

    long id;
    String firstname;
    String lastname;
    String alias;
    Date birth;
    Date dead;
    int sex;
    String address;
    long id_genealogy;
    InputStream img;

    public People(long id, String firstname, String lastname, String alias, Date birth, Date dead, int sex, String address, long id_genealogy, String img) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.alias = alias;
        this.birth = birth;
        this.dead = dead;
        this.sex = sex;
        this.address = address;
        this.id_genealogy = id_genealogy;
    }
    public People(long id, String firstname, String lastname, Date birth, Date dead, int sex, String address) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birth = birth;
		this.dead = dead;
		this.sex = sex;
		this.address = address;
	}

    
    @Override
    public String toString() {
        return "People{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", alias=" + alias + ", birth=" + birth + ", dead=" + dead + ", sex=" + sex + ", address=" + address + ", id_genealogy=" + id_genealogy + ", img=" + img + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSex() {
		return sex;
	}


	public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getDead() {
        return dead;
    }

    public void setDead(Date dead) {
        this.dead = dead;
    }

    public int isSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId_genealogy() {
        return id_genealogy;
    }

    public void setId_genealogy(long id_genealogy) {
        this.id_genealogy = id_genealogy;
    }

    public InputStream getImg() {
        return img;
    }

    public void setImg(InputStream img) {
        this.img = img;
    }

    public People() {
    }
}
