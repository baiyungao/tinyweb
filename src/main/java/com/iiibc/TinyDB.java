package com.iiibc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaob on 2/16/2017.
 */
public class TinyDB {

    final String db_file = "c:\\tinydb\\db.json";
    static private TinyDB instance = null;

    static public TinyDB getDefault() {
        if (instance == null){
            instance = new TinyDB();
        }
        return instance;
    }

    private void createDefaultUser(){
        User user = new User("Chloe","12345",1);
        userList.add(user);
        try {
            this.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TinyDB(){

        try {
            load();
        } catch (IOException e) {
           createDefaultUser();
            try {
                commit();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        //build map
        userList.stream().forEach(u ->{userMap.put(u.getUserid(),u);});

    }

    private List<User> userList = new ArrayList<>();
    private Map<String, User> userMap = new HashMap<>();


    public boolean load() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        TypeReference<List<User>> mapType = new TypeReference<List<User>>() {};
        userList = objectMapper.readValue(new File(db_file), mapType);

        return true;
    }


    //save to File System
    public boolean commit() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        objectMapper.writeValue(new File(db_file), userList);

        return true;
    }

    public List<User> getUserList(){
        return userList;
    }

    public void addUser(String id, String pwd) throws IOException {
        User user = userMap.get(id);

        if (user == null) {
            user = new User(id, pwd);
            userList.add(user);
            commit();
            userMap.put(user.getUserid(),user);
        }
    }

    public String getPassword(String id){
        User user = userMap.get(id);
        if (user != null)
        {
            return user.getPassword();
        }

        return "";
    }

    public int addPoints(String id, int addpoints) throws IOException {
        User user = userMap.get(id);
        if (user != null)
        {
            user.addPoints(addpoints);
            commit();
            return user.getPoints();
        }

        return 0;
    }

    public static void main(String[] args){

        try {

            //user = getObject();
            TinyDB db = TinyDB.getDefault();

            db.addUser("ben", "12345");
            db.addPoints("ben", 13);


            System.out.println("User List:" + db.getUserList());
        }catch (Exception e)
        {}

    }
}


