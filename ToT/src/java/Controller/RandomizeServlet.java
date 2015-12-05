package Controller;

import DAO.FoodDAO;
import Model.Distance;
import Model.Food;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shermainesy
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String filter = request.getParameter("filterBy");
            Distance calucation = new Distance();
            Random generator = new Random();
            Food result = new Food();
            FoodDAO dao = new FoodDAO();
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

             Gson g = new Gson();
             String resultString = g.toJson(result);
             response.getWriter().print(resultString);
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
