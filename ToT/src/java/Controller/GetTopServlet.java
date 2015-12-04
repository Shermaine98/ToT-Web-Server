package Controller;

import DAO.CommentsDAO;
import DAO.FoodDAO;
import Model.Comments;
import Model.Food;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
     * @throws org.json.JSONException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<Food> Topfood = new ArrayList<>();
            ArrayList<Comments> Comments = new ArrayList<>();
            FoodDAO foodDAO = new FoodDAO();
            CommentsDAO CommentDAO = new CommentsDAO();

            Topfood = foodDAO.GetTopFood();

            //Get Top food id then find its comments then return the comments
            JSONArray arrayFood = new JSONArray();
            JSONArray arrayComment = new JSONArray();

              
            for (int i = 0; i < Topfood.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("foodID", Topfood.get(i).getFoodID());
                obj.put("foodName", Topfood.get(i).getFoodName());
                obj.put("foodDescription", Topfood.get(i).getFoodDescription());
                obj.put("price", Topfood.get(i).getPrice());
                obj.put("rating", Topfood.get(i).getRating());
                
                arrayFood.put(obj);
                ArrayList<Comments> temp = new ArrayList<>();
                temp = CommentDAO.GetTopFoodComments(Topfood.get(i).getFoodID());
                for (int j = 0; j < temp.size(); j++) {
                    JSONObject comment = new JSONObject();
                    
                    comment.put("CommentsID", temp.get(j).getCommentsID());
                    comment.put("comments", temp.get(j).getComments());
                    comment.put("IDUser", temp.get(j).getIDUser());
                    comment.put("foodID", Topfood.get(i).getFoodID());
                    arrayComment.put(comment);
                

                }
            }
            
            JSONObject mainObj = new JSONObject();
            JSONObject mainObjC = new JSONObject();
            mainObj.put("Food", arrayFood);
            mainObjC.put("Comments", arrayComment);
           
        // Convert your object or list to a JSON string
           
          
            response.getWriter().printf(mainObj.toString());
            response.getWriter().printf(mainObjC.toString());
            
         
            
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
        } catch (JSONException ex) {
            Logger.getLogger(GetTopServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (JSONException ex) {
            Logger.getLogger(GetTopServlet.class.getName()).log(Level.SEVERE, null, ex);
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
