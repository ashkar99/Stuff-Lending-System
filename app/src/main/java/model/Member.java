package model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String email;
    private String password;
    private int phoneNum;
    private int memberID;
    private int creationDate;
    private int credit;
    private List<Item> items = new ArrayList<>();
    

    
    
    public Member(String name, String email, int phoneNum, String password) {
        setName(name);
        setEmail(email);
        setPhoneNum(phoneNum);
        setPassword(password);
    }
    public Member(){}
    
    private void setPassword(String password) {
        this.password = password;
       
    }
    public String getPassword(){
        return password;
    }
    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    private void setEmail(String email) {
        this.email = email;
    }
    public int getPhoneNum() {
        return phoneNum;
    }
    private void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
    public int getMemberID() {
        return memberID;
    }
    private void setMemberID(int memberID) {
        this.memberID = memberID;
    }
    public int getCreationDate() {
        return creationDate;
    }
    private void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }
    public int getCredit() {
        return credit;
    }
    private void setCredit(int credit) {
        this.credit = credit;
    }
    public List<Item> getItems() {
        return items;
    }
    private void addItem(Item item) {
        this.items.add(item);
    }
    
}
