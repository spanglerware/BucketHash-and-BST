/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatatest;

/**
 * Data structure for address records containing name, email, and 
 * phone number. Implements comparable interface on nameKey values.
 * 
 * @author Scott
 */
public class DataAddress implements Comparable<String> {
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String phoneNumber;
    private String nameKey;
    
    public DataAddress() {
        //SDS default init
        this("John", "Doe", "jdoe@example.com", "555-1234");
    }
    
    public DataAddress(String firstName, String lastName, String email, 
            String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        emailAddress = email;
        phoneNumber = phone;
        updateKey();
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        updateKey();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        updateKey();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getNameKey() {
        return nameKey;
    }

    private void updateKey() {
        nameKey = (this.firstName + this.lastName).toUpperCase();
    }
  
    public int compareTo(String key) {
        return nameKey.compareTo(key);
    }
        
    @Override
    public String toString() {
        return nameKey;
    }
    
} //SDS end of DataAddress class
