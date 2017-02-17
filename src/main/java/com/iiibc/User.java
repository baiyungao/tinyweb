package com.iiibc;

import java.io.Serializable;

/**
 * Created by gaob on 2/16/2017.
 */
public class User implements Serializable {

    private String userid;
    private String password;
    private int points;

    public User(){

    }

    public User(String id, String pwd){
        userid = id;
        password = pwd;
    }

    public User(String id, String pwd, int pts){
        userid = id;
        password = pwd;
        points = pts;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int addPoints(int adding){
        points += adding;
        return points;
    }
}
