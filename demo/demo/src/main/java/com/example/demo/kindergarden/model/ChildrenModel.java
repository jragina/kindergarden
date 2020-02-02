package com.example.demo.kindergarden.model;

import lombok.Data;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.BadAttributeValueExpException;
import javax.persistence.Entity;
import javax.persistence.Id;

//this from vs code will create attribute for pattern, so we set manually getters and setters
//@Data
@Entity
public class ChildrenModel implements Comparable<ChildrenModel>{
    private String firstName;
    private String lastName;
    private @Id String identityCode;
    private boolean sibling;
    private Date entryDate;
    private final Pattern identityCodePattern = Pattern.compile("^[0-9]{6}-[0-9]{5}$");
    public ChildrenModel(){}
    public ChildrenModel(String firstName, String lastName, String identityCode, boolean sibling, Date entryDate)
            throws BadAttributeValueExpException {
        if(!validateIndentityCode(identityCode)){
            throw new BadAttributeValueExpException(identityCode);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityCode = identityCode;
        this.sibling = sibling;
        this.entryDate = entryDate;
    }
    public boolean validateIndentityCode(String identityCode){
        Matcher identityCodeMatcher = identityCodePattern.matcher(identityCode);
        return identityCodeMatcher.matches();
    }

    @Override
    public int compareTo(ChildrenModel child) {
        if (getEntryDate() == null || child.getEntryDate() == null){
        return 0;
        }
      int compareByDate =  getEntryDate().compareTo(child.getEntryDate());
      if (compareByDate == 0){
          return Boolean.compare(!getSibling(), !child.getSibling());//so trues are first in list
      }
      return compareByDate;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setIdentityCode(String identityCode){
        this.identityCode = identityCode;
    }
    public void setSibling(boolean sibling){
        this.sibling = sibling;
    }
    public void setEntryDate(Date entryDate){
        this.entryDate = entryDate;
    }

    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getIdentityCode(){
        return this.identityCode ;
    }
    public boolean getSibling(){
        return this.sibling;
    }
    public Date getEntryDate(){
        return this.entryDate;
    }
    
}