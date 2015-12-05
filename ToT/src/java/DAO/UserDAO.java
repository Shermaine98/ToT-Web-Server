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
                    + "(idUser,email,username,password) "
                    + "values (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, lastId()+1);
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
    
    public int lastId(){
        int last = -1;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String query = "SELECT idUser FROM user ORDER BY idUser DESC LIMIT 1";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                last = rs.getInt("idUser");
            }
            conn.close();
            return last;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return last;
    }

    /**
     * Authenticate
     *
     * @param User
     * @return
     */
    public User authenticate(User User) {

        User user = null;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String query = "select * from user where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, User.getUserName());
            pstmt.setString(2, User.getPassword());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("idUser"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     * Checks if username is taken
     *
     * @param User
     * @return true if taken
     * @return false if not
     */
    public boolean checkUsername(String username) {

        boolean exists = false;

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String query = "SELECT EXISTS(SELECT 1 FROM user WHERE username = ?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = rs.getBoolean(1);
            }
            conn.close();
            return exists;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;

    }

    /**
     * Checks if email is taken
     *
     * @param User
     * @return true if taken
     * @return false if not
     */
    public boolean checkEmail(String email) {

        boolean exists = false;
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String query = "SELECT EXISTS(SELECT 1 FROM user WHERE email = ?);";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = rs.getBoolean(1);
            }
            conn.close();
            return exists;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }
}
