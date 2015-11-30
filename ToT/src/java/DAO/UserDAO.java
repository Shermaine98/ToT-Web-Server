package DAO;

import Database.DBConnectionFactory;
import Model.User;
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
    public User authenticate(Model.User User) {

       User user = new User();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String query = "select * from User where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            System.out.println("dao" + User.getUserName());

            pstmt.setString(1, User.getUserName());
            pstmt.setString(2, User.getPassword());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("Email"));
            }
            conn.close();
            return User;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
