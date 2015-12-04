/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FavoritesDAO;
import Model.Favorites;
import com.google.gson.Gson;
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
            FavoritesDAO DAO = new FavoritesDAO();
            int Session = 0;
            FavoritesList = DAO.GetFavorites(Session);

            JSONArray favorites = new JSONArray();

            for (int i = 0; i < FavoritesList.size(); i++) {
                JSONObject obj = new JSONObject();
                JSONObject mainObj = new JSONObject();
                obj.put("username", FavoritesList.get(i).getUserName());
                obj.put("userID", FavoritesList.get(i).getUseriD());
                obj.put("foodID", FavoritesList.get(i).getFoodID());
                obj.put("foodName", FavoritesList.get(i).getFoodName());
                obj.put("foodDescription", FavoritesList.get(i).getFoodDescription());
                obj.put("price", FavoritesList.get(i).getPrice());
                obj.put("rating", FavoritesList.get(i).getRating());
                obj.put("picture", FavoritesList.get(i).getPicture());

                mainObj.put("Favorites", obj);
                favorites.put(mainObj);
            }

            Gson g = new Gson();
            for (int i = 0; i < favorites.length(); i++) {
                String topFoodJson = g.toJson(favorites.get(i).toString());
                //response.getWriter().print(topFoodJson);
                response.getWriter().print(favorites.get(i).toString());
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
