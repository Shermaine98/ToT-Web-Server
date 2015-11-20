package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
 
public class LoginServlet extends HttpServlet {
 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        JSONObject json = new JSONObject();
         
 
        //ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
        Enumeration paramNames = request.getParameterNames();
        String params[] = new String[2];
        int i = 0;
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
 
            System.out.println(paramName);
            String[] paramValues = request.getParameterValues(paramName);
            params[i] = paramValues[0];
 
            System.out.println(params[i]);
            i++;
 
        }

        UserDAO DAO = new UserDAO();
        User user = new User();
        user.setUserName(params[0]);
        user.setPassword(params[1]);
        try {
           json = DAO.authenticate(user);
        } catch (JSONException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
        System.out.println(json.toString());
    }
 
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}