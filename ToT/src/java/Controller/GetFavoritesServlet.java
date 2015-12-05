package Controller;

import DAO.CommentsDAO;
import DAO.FavoritesDAO;
import Model.Comments;
import Model.Favorites;
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
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class GetFavoritesServlet extends HttpServlet {

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
            ArrayList<Favorites> FavoritesList = new ArrayList<Favorites>();

            CommentsDAO cDao = new CommentsDAO();
            FavoritesDAO dao = new FavoritesDAO();
            int paraUserId = Integer.parseInt(request.getParameter("userID"));
            FavoritesList = dao.GetFavorites(paraUserId);

            JSONArray favorites = new JSONArray();
            JSONArray arrayComment = new JSONArray();

            for (int i = 0; i < FavoritesList.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("foodID", FavoritesList.get(i).getFoodID());
                obj.put("foodName", FavoritesList.get(i).getFoodName());
                obj.put("foodDescription", FavoritesList.get(i).getFoodDescription());
                obj.put("price", FavoritesList.get(i).getPrice());
                obj.put("rating", FavoritesList.get(i).getRating());
                obj.put("picture", FavoritesList.get(i).getPicture());

                favorites.put(obj);
                ArrayList<Comments> temp = new ArrayList<>();
                temp = cDao.GetFavoriteComments(FavoritesList.get(i).getFoodID());

                for (int j = 0; j < temp.size(); j++) {
                    JSONObject comment = new JSONObject();
                    comment.put("CommentsID", temp.get(j).getCommentsID());
                    comment.put("comments", temp.get(j).getComments());
                    comment.put("IDUser", temp.get(j).getIDUser());
                    comment.put("foodID", FavoritesList.get(i).getFoodID());
                    arrayComment.put(comment);
                }
            }

            JSONObject main = new JSONObject();
            main.put("Favorites", favorites);
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
            Logger.getLogger(GetFavoritesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetFavoritesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
