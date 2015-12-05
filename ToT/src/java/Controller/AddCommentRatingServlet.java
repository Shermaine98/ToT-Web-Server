/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CommentsDAO;
import DAO.FavoritesDAO;
import DAO.FoodDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class AddCommentRatingServlet extends HttpServlet {

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
           double newRating = Double.parseDouble(request.getParameter("rating"));
           int userID = Integer.parseInt(request.getParameter("userID"));
           int FoodID = Integer.parseInt(request.getParameter("foodID"));
           String comment = request.getParameter("Comment");
           double currRating;
           CommentsDAO CommentsDAO = new CommentsDAO();
           FoodDAO FoodDAO = new FoodDAO();
           
            if (comment.equalsIgnoreCase("null%") && newRating == 0.0) {
                response.getWriter().print(false);
            } else if (!comment.equalsIgnoreCase("null%") && newRating != 0.0) {
                currRating = FoodDAO.getRating(FoodID);
                //(5*252 + 4*124 + 3*40 + 2*29 + 1*33)
                double AddRating =  (currRating + newRating) / 2;
                boolean result = FoodDAO.UpdateRating(AddRating, FoodID);
                boolean result2 = CommentsDAO.addComments(userID, FoodID, comment);
                if (result == true && result2 == true) {
                    response.getWriter().print(true);
                }
            } else if (newRating != 0.0) {
                currRating = FoodDAO.getRating(FoodID);
                double AddRating = (currRating + newRating) / 2;
                boolean result = FoodDAO.UpdateRating(AddRating, FoodID);
                response.getWriter().print(result);
            } else if (!comment.equalsIgnoreCase("null%")) {
                boolean result2 = CommentsDAO.addComments(userID, FoodID, comment);
                response.getWriter().print(result2);
            }
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
