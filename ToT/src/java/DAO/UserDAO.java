package DAO;

import Database.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shermainesy
 * 
 */
public class UserDAO {
   
    /**
     * Register UserDAO
     *
     * @param newUser
     * @return
     */

    public boolean register(Model.User newUser) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into user"
                    + "(userID,Email,userName,password) "
                    + "values (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, newUser.getUserID());
            pstmt.setString(2, newUser.getEmail());
            pstmt.setString(3, newUser.getUserName());
            pstmt.setString(4, newUser.getPassword());

            int rows = pstmt.executeUpdate();
            conn.close();
            return rows == 1;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
    /**
     * Authenticate
     *
     * @param User
     * @return
     */

    public boolean authenticate(Model.User User) {
        boolean valid = false;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String query = "select * from User where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, User.getUserName());
            pstmt.setString(2, User.getPassword());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                valid = true;
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }

    
    
}
