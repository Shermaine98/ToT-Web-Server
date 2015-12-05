package DAO;

import Database.DBConnectionFactory;
import Model.Comments;
import Model.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shermainesy
 */
public class CommentsDAO {

    public ArrayList<Comments> GetTopFoodComments(int FoodID) {
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

    public ArrayList<Comments> GetHistoryComments(int userID) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            ArrayList<Comments> Comments = new ArrayList<>();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT C.COMMENTID, C.FOODID, "
                    + "C.COMMENTS, C.IDUSER, U.USERNAME\n"
                    + "FROM COMMENTS C JOIN HISTORY H ON C.FOODID = H.FOODID \n"
                    + "JOIN USER U ON C.IDUSER = U.IDUSER\n"
                    + "WHERE H.IDUSER = ?");
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comments temp = new Comments();
                temp.setCommentsID(rs.getInt("CommentID"));
                temp.setComments(rs.getString("Comments"));
                temp.setIDUser(rs.getString("username"));
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

}
