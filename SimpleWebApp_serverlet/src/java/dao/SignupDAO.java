/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Signup;
import org.hibernate.*;


/**
 *
 * @author omega
 */
public class SignupDAO {
   

    public void createSignup(Signup signup) {
        
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.save(signup);
        tx.commit();
        session.close();
        
    }

    public void updateSignup(Signup signup) {
        
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.update(signup);
        tx.commit();
        session.close();
        
    }
    
     /*public Signup findSignup(Signup signup){
         Session ss = NewHibernateUtil.getSessionFactory().openSession();
         Signup stud =(Signup) ss.get(Signup.class, signup.getPassword());
         ss.close();
         return stud;
    }*/
    public Signup findSignupByEmailAndPassword(String email, String password) {
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    Signup foundSignup = null;
    try {
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Signup where email = :email and password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        foundSignup = (Signup) query.uniqueResult();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }
    return foundSignup;

}

    
}

    
