/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import dao.AdmissionformDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Admissionform;
import java.util.Date;
/*import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author omega
 */
@WebServlet(name = "AdmissionformServlet", urlPatterns = {"/AdmissionformServlet"})
@MultipartConfig(maxFileSize = 16177215) // 16 MB
public class AdmissionformServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdmissionformServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdmissionformServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
           Admissionform admission= new Admissionform();
            admission.setFirstname(request.getParameter("firstname"));
            admission.setLastname(request.getParameter("lastname"));
            

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dob = null;
        try {
            dob = dateFormat.parse(request.getParameter("dob"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
        admission.setDob(sqlDate);
    
            admission.setGender(request.getParameter("gender"));
            admission.setPhonenumber(request.getParameter("phonenumber"));
            admission.setId(request.getParameter("id"));
            admission.setEmail(request.getParameter("email"));
           
            admission.setAddress(request.getParameter("address"));
            
            
            // handle image file upload
        Part imagePart = request.getPart("image");
        if (imagePart != null && imagePart.getSize() > 0) {
            InputStream imageInputStream = imagePart.getInputStream();
            byte[] imageBytes = new byte[(int) imagePart.getSize()];
            imageInputStream.read(imageBytes);
            admission.setImage(imageBytes);
        }
        
        // handle PDF file upload
        Part pdfPart = request.getPart("pdf");
        if (pdfPart != null && pdfPart.getSize() > 0) {
            InputStream pdfInputStream = pdfPart.getInputStream();
            byte[] pdfBytes = new byte[(int) pdfPart.getSize()];
            pdfInputStream.read(pdfBytes);
            admission.setPdf(pdfBytes);
        }
            
        AdmissionformDAO dao = new AdmissionformDAO();
        dao.createAdmissionform(admission);
        
        response.sendRedirect("Admission_success.html");
            
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
