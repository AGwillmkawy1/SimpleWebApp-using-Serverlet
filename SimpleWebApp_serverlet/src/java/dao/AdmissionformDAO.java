/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Admissionform;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author omega
 */
public class AdmissionformDAO {
    
    public void createAdmissionform(Admissionform admission) {
        
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.save(admission);
        tx.commit();
        session.close();
        
    }

    public void updateAdmissionform(Admissionform admission) {
        
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.update(admission);
        tx.commit();
        session.close();
        
    }
    
     public Admissionform findSignup(Admissionform admission){
         Session ss = NewHibernateUtil.getSessionFactory().openSession();
     Admissionform  stud =(Admissionform) ss.get(Admissionform .class, admission.getEmail());
         ss.close();
         return stud;
    }
    
    
}
