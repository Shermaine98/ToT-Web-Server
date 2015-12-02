package Controller;

import DAO.CommentsDAO;
import DAO.FoodDAO;
import Model.Comments;
import Model.Food;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shermainesy
 */
public class GetTopServlet extends HttpServlet {

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
            ArrayList<Food> Topfood = new ArrayList<>();
            ArrayList<Comments> Comments = new ArrayList<>();
            FoodDAO foodDAO = new FoodDAO();
            CommentsDAO CommentDAO = new CommentsDAO();
            
            Topfood = foodDAO.GetTopFood();
            
            //Get Top food id then find its comments then return the comments
           
            for(int i = 0; i < Topfood.size(); i++){
               ArrayList<Comments> temp = new ArrayList<>();
                temp = CommentDAO.GetTopFoodComments(Topfood.get(i).getFoodID());
                for(int j=0; j<temp.size();j++){
                 Comments.add(temp.get(j));
                }
            }
          // Convert your object or list to a JSON string
		Gson g = new Gson();
                Gson gs = new Gson();
		String topFoodJson = g.toJson(Topfood); // try printing this out to see the JSON string of studentList
		String topComments = g.toJson(Comments);
		// Send the JSON string to the client who requested it
		response.getWriter().print(topFoodJson);
                response.getWriter().printf(topComments);

               // System.out.print(topFoodJson);
                
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
