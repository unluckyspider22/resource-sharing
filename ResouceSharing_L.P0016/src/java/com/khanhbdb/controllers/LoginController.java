/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.controllers;

import com.khanhbdb.utils.ReCaptchaUtil;
import com.khanhdbd.daos.AccountDAO;
import com.khanhdbd.dtos.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"}, loadOnStartup = 0)
public class LoginController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    private final String SUCCESS = "index.jsp";
    private final String ERROR = "login.jsp";

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
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String url = ERROR;
        try {
            boolean valid = true;
            String errorMessage = null;
            //check login
            AccountDAO dao = new AccountDAO();
            AccountDTO dto = new AccountDTO();
            dto = dao.checkLogin(email, password);
            if (dto == null) {
                //login failed
                errorMessage = "Invalid email or password";
                request.setAttribute("ERROR", errorMessage);
            } else {
                String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
                System.out.println(gRecaptchaResponse);
                valid = ReCaptchaUtil.verify(gRecaptchaResponse);
                if (!valid) {
                    //input wrong
                    errorMessage = "Captcha invalid";
                    request.setAttribute("ERROR", errorMessage);
                }else{
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
