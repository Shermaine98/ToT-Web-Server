package Controller;

import DAO.UserDAO;
import Model.User;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
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
       
        User user = new User();
        User user2 = new User();
        UserDAO DAO = new UserDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        user.setUserName(username);
        user.setPassword(password);
        user2 = DAO.authenticate(user);
       
        // String userID = "";
        Gson g = new Gson();
        //userID = g.toJson(userid);
        
        String u = "";
      //  User usersearch = DAO.getUser(userid);
        u = g.toJson(user2);

        
        if(!u.isEmpty()){
            response.getWriter().print(u);
        }
        else{
            response.getWriter().print("error");
        }
        
        //SESSION
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