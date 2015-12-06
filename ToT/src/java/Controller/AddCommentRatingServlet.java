/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CommentsDAO;
import DAO.FoodDAO;
import DAO.RatingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           double newRating = Double.parseDouble(request.getParameter("rating"));
           int userID = Integer.parseInt(request.getParameter("userID"));
           int FoodID = Integer.parseInt(request.getParameter("foodID"));
           String comment = request.getParameter("Comment");
           double currRating;
           CommentsDAO CommentsDAO = new CommentsDAO();
           FoodDAO FoodDAO = new FoodDAO();
           RatingDAO ratingDAO = new RatingDAO();
          // String comment="hello";
         //  double newRating=1.0;
          // int FoodID=59;
         //  int userID=0;
            if (comment.equalsIgnoreCase("null%") && newRating == 0.0) {
                response.getWriter().print(false);
            } else if (!comment.equalsIgnoreCase("null%") && newRating != 0.0) {
                ratingDAO.addRating(userID, newRating, FoodID);
                currRating = ratingDAO.SolveRating(FoodID);
                boolean result = FoodDAO.UpdateRating(currRating, FoodID);
                boolean result2 = CommentsDAO.addComments(userID, FoodID, comment);
                if (result == true && result2 == true) {
                    response.getWriter().print(true);
                }
            } else if (newRating != 0.0) {
                ratingDAO.addRating(userID, newRating, FoodID);
                currRating = ratingDAO.SolveRating(FoodID);
                boolean result = FoodDAO.UpdateRating(currRating, FoodID);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddCommentRatingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddCommentRatingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
