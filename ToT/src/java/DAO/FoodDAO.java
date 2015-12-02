/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author shermainesy
 */
public class FoodDAO {

    /**
     * Get the Top 5 foods
     *
     * @return
     */
    public ArrayList<Food> GetTopFood() {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            ArrayList<Food> FoodTop = new ArrayList<>();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Food F\n"
                    + "WHERE F.RATING > 0\n"
                    + "ORDER BY F.RATING DESC LIMIT 5;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food temp = new Food();
                temp.setFoodID(rs.getInt("FoodID"));
                temp.setFoodName(rs.getString("FoodName"));
                temp.setFoodDescription(rs.getString("FoodDescription"));
                temp.setPicture(rs.getInt("Picture"));
                temp.setPrice(rs.getDouble("Price"));
                temp.setRating(rs.getInt("Rating"));
                FoodTop.add(temp);
            }
            
            pstmt.close();
            conn.close();
            return FoodTop;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
