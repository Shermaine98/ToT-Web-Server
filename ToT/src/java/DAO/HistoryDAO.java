package DAO;

import Database.DBConnectionFactory;
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
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class HistoryDAO {

    public ArrayList<Food> GetHistory(int userID) {

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            ArrayList<Food> history = new ArrayList<>();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT H.IDUSER, H.FOODID, F.RestaurantName, F.FoodName, "
                    + "F.FoodDescription, F.Price, F.Rating, F.Picture, R.address, R.longitude, R.LATITUDE\n"
                    + "FROM HISTORY H JOIN FOOD F ON H.FOODID = F.FOODID\n"
                    + "JOIN restaurants R ON F.RestaurantName = R.RestaurantName WHERE H.IDUSER = ?;");
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food temp = new Food();
                temp.setFoodID(rs.getInt("FoodID"));
                temp.setFoodName(rs.getString("FoodName"));
                temp.setFoodDescription(rs.getString("FoodDescription"));
                temp.setPicture(rs.getInt("Picture"));
                temp.setPrice(rs.getDouble("Price"));
                temp.setRating(rs.getDouble("Rating"));
                temp.setAddress(rs.getString("Address"));
                temp.setRestaurantName(rs.getString("RestaurantName"));
                history.add(temp);
            }

            pstmt.close();
            conn.close();
            return history;
        } catch (SQLException ex) {
            Logger.getLogger(FavoritesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * getHistoryLastNumber
     *
     * @return
     * @throws SQLException
     */
    public Integer getHistoryLastNumber() throws SQLException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        Integer i = 0;
        String query = "SELECT MAX(historyID) from history";
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            i = rs.getInt("MAX(historyID)");
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

    /**
     * Add History
     *
     *
     * @param userID
     * @param foodID
     * @return
     */
    public boolean addHistory(int userID, int foodID) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into history  (historyID,idUser,foodID) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            Integer x = getHistoryLastNumber();
            pstmt.setInt(1, x);
            pstmt.setInt(2, userID);
            pstmt.setInt(3, foodID);

            int rows = pstmt.executeUpdate();
            conn.close();
            return rows == 1;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Check if foodID already exists in history table
     *
     * @param userID
     * @param foodID
     * @return
     */
    public boolean checkHistory(int userID, int foodID) {

        boolean exists = false;

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT EXISTS(SELECT 1 FROM history WHERE iduser = ? and foodid = ?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, foodID);
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
