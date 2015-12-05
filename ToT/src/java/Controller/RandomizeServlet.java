package Controller;

import DAO.CommentsDAO;
import DAO.FoodDAO;
import Model.Comments;
import Model.Distance;
import Model.Food;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
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
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class RandomizeServlet extends HttpServlet {

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
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String filter = request.getParameter("filterBy");
            Distance calucation = new Distance();
            Random generator = new Random();
            Food result = new Food();
            FoodDAO dao = new FoodDAO();
            CommentsDAO commentDAO = new CommentsDAO();
            ArrayList<Comments> comments = new ArrayList<>();
            ArrayList<Food> list = new ArrayList<>();
            if (filter.equalsIgnoreCase("None")) {
                list = dao.GetAll();
                int index = generator.nextInt(list.size());
                result = list.get(index);
            } else if (filter.equalsIgnoreCase("Price")) {
                int price = Integer.parseInt(request.getParameter("price"));
                list = dao.GetAllByPrice(price);
                int index = generator.nextInt(list.size());
                result = list.get(index);
            } else if (filter.equalsIgnoreCase("Location")) {
                float longtitidue = Float.parseFloat(request.getParameter("longitude"));
                double latitude = Double.parseDouble(request.getParameter("latitude"));
                double distance = Double.parseDouble(request.getParameter("distance"));
                result = calucation.getFilteredLocation(latitude, longtitidue, distance);
            } else if (filter.equalsIgnoreCase("Both")) {
                double price = Double.parseDouble(request.getParameter("price"));
                double longtitidue = Double.parseDouble(request.getParameter("longitude"));
                double latitude = Double.parseDouble(request.getParameter("latitude"));
                double distance = Double.parseDouble(request.getParameter("distance"));
                result = calucation.getFilteredBoth(latitude, longtitidue, distance, price);
            }
            //Converting to JSOn 
            JSONArray arrayComment = new JSONArray();
            JSONObject obj = new JSONObject();
            obj.put("foodID", result.getFoodID());
            obj.put("foodName", result.getFoodName());
            obj.put("foodDescription", result.getFoodDescription());
            obj.put("price", result.getPrice());
            obj.put("rating", result.getRating());
            obj.put("picture", result.getPicture());
            obj.put("RestaurantName", result.getRestaurantName());
            obj.put("address", result.getAddress());
           
            comments = commentDAO.GetComments(result.getFoodID());
            for (int j = 0; j < comments.size(); j++) {
                JSONObject comment = new JSONObject();
                comment.put("CommentsID", comments.get(j).getCommentsID());
                comment.put("comments", comments.get(j).getComments());
                comment.put("IDUser", comments.get(j).getIDUser());
                comment.put("foodID", result.getFoodID());
                arrayComment.put(comment);
            }
            JSONObject main = new JSONObject();
            main.put("Result", obj);
            main.put("Comments", arrayComment);
            response.getWriter().print(main.toString());
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
            Logger.getLogger(RandomizeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RandomizeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
