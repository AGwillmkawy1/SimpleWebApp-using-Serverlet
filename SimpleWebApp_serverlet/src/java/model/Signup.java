package model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author omega
 */
@Entity 
public class Signup implements Serializable{
    
   @Id
  
   private String studentid;
   private String email;
   private String password;

    public Signup() {
    }

    public Signup(String studentid, String email, String password) {
        this.studentid = studentid;
        this.email = email;
        this.password = password;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    
  }
