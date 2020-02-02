package com.example.demo.kindergarden.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class KindergardenModel {

    private @Id @GeneratedValue Long id;
    private String name;
    private String address;
    
    @ElementCollection(targetClass = ChildrenModel.class)
    @OneToMany( cascade = {CascadeType.ALL}, targetEntity=ChildrenModel.class)
    private List<ChildrenModel> childrenQueue;
    public KindergardenModel(){}
    public KindergardenModel(String name, String address, List<ChildrenModel> childrenQueue){
        this.name = name;
        this.address = address;
        this.childrenQueue = childrenQueue;
    }
    public void addChildren(ChildrenModel child){
        this.childrenQueue.add(child);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    
    public void setChildrenQueue(List<ChildrenModel> childrenQueue){
        this.childrenQueue = childrenQueue;
    }
    public List<ChildrenModel> getChildrenQueue(){
        Collections.sort(this.childrenQueue);
        return this.childrenQueue;
    }
    public Long getId(){
        return this.id;
    }
}