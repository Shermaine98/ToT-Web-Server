/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Database.DBConnectionFactory;
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
public class RatingDAO {

    public boolean addRating(int userID, double rating, int foodID) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into Rating (foodID,rating,userID) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, foodID);
            pstmt.setDouble(2, rating);
            pstmt.setInt(3, userID);

            int rows = pstmt.executeUpdate();
            conn.close();
            return rows == 1;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Double> GetAllRating(int FoodID) throws SQLException {
        ArrayList<Double> ratings = new ArrayList<>();
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();

        String query = "SELECT * from Rating where FoodID = ?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, FoodID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            double i = 0.0;
            i = rs.getInt("rating");
            ratings.add(i);
        }
        conn.close();
        rs.close();
        return ratings;
    }

    public double SolveRating(int FoodID) throws SQLException {
        double newRating = 0.0;
        ArrayList<Double> ratings = new ArrayList<>();
        ratings = GetAllRating(FoodID);
        int count5 = 0;
        int count4 = 0;
        int count3 = 0;
        int count2 = 0;
        int count1 = 0;
        int count0 = 0;
        for (int i = 0; i < ratings.size(); i++) {
            if (ratings.get(i) ==5) {
                count5++;
            } else if (ratings.get(i) == 4.0) {
                count4++;
            } else if (ratings.get(i) == 3.0) {
                count3++;
            } else if (ratings.get(i)==2.0) {
                count2++;
            } else if (ratings.get(i)==1.0) {
                count1++;
            } else if (ratings.get(i)==0.0) {
                count0++;
            }
        }
        newRating = (5 * count5 + 4 * count4 + 3 * count3 + 2 * count2 + 1 * count1) / (count5 + count4 + count3 + count2 + count1);
        return newRating;
    }
}
