/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bmicalculatorapp.controller;

import com.mycompany.bmicalculatorapp.model.User;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class UserController {
    private ArrayList<User> users = new ArrayList<>();
    private int currentId = 1;
    
    //CRUD - Craete
    public void addUser(String name, double height, double weight){
        User user = new User(currentId++, name, height, weight);
        users.add(user);
    }
    
    //CRUD - Read
    public ArrayList<User> getUsers(){
        return users;
    }
    
    //CRUD - Update 
    public boolean updateUser(int id, String name, double height, double weigth){
        for(User user : users){
            if(user.getId() == id){
                user.setName(name);
                user.setHeight(height);
                user.setWeight(height);
                return true;
            }
        }
        return false;
    }
    
    //CRUD - Delete
    public boolean deleteUser(int id){
        return users.removeIf(user -> user.getId() == id);
    }
}
