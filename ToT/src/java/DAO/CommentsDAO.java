package DAO;

import Database.DBConnectionFactory;
import Model.Comments;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class CommentsDAO {

    public ArrayList<Comments> GetComments(int FoodID) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            ArrayList<Comments> Comments = new ArrayList<>();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(""
                    + "SELECT C.CommentID, C.FoodID, C.Comments, U.username "
                    + "FROM COMMENTS C JOIN USER U ON C.idUser = U.idUser\n"
                    + "WHERE FOODID = ?;");
            pstmt.setInt(1, FoodID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comments temp = new Comments();
                temp.setCommentsID(rs.getInt("CommentID"));
                temp.setComments(rs.getString("Comments"));
                temp.setIDUser(rs.getString("userName"));
                Comments.add(temp);
            }

            pstmt.close();
            conn.close();
            return Comments;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public boolean addComments(int userID, int foodID, String Comments) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into comments (commentID,FoodID,idUser,Comments)"
                    + " values (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            int last = getCommentsLastNumber();
            pstmt.setInt(1, last);
            pstmt.setInt(2, foodID);
             pstmt.setInt(3, userID);
            pstmt.setString(4, Comments);
            int rows = pstmt.executeUpdate();
            conn.close();
            return rows == 1;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
      /**
     * getHistoryLastNumber
     *
     * @return
     * @throws SQLException
     */
    public Integer getCommentsLastNumber() throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        Integer i = 0;
        String query = "SELECT MAX(commentID) from Comments";
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getInt("MAX(commentID)");
        }
        if (i == 0) {
            i = 0;
        } else {
            i += 1;
        }
        conn.close();
        rs.close();
        return i;
    }

}
