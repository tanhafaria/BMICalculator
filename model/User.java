/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bmicalculatorapp.model;

/**
 *
 * @author Admin
 */
public class User {
    private int id;
    private String name;
    private double height;
    private double weight;
    private double bmi;
    
    public User(int id, String name, double height, double weight){
        this.id  = id;
        this.name= name;
        this.height = height;
        this.weight = weight;
        calculateBMI();
    }
    
    public int getId() {
        return id;
        }
    
    public String getName() {
        return name;
        }
    
    public double getHeight() {
        return height;
        }
    
    public double getWeight() {
        return weight;
        }
    
    public double getBMI(){
        return bmi;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setHeight(double height){
        this.height = height;
        calculateBMI();
    }
    
    public void setWeight(double weight){
        this.weight = weight;
        calculateBMI();
    }
    
    private void calculateBMI(){
        this.bmi = weight / (height * height);
    }
    
    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", bmi=" + bmi +
                '}';
    }
    
}
