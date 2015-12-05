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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * "
                    + "FROM Food F JOIN RESTAURANTS R on F.RestaurantName = R.RestaurantName\n"
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
                temp.setAddress(rs.getString("Address"));
                temp.setRestaurantName(rs.getString("RestaurantName"));
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

    /**
     * Get All Food
     *
     * @return
     */
    public ArrayList<Food> GetAll() {

        ArrayList<Food> food = new ArrayList<>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Food ORDER BY FoodID");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food temp = new Food();
                temp.setFoodID(rs.getInt("FoodID"));
                temp.setRestaurantName(rs.getString("RestaurantName"));
                temp.setFoodName(rs.getString("FoodName"));
                temp.setFoodDescription(rs.getString("FoodDescription"));
                temp.setPrice(rs.getDouble("Price"));
                temp.setRating(rs.getInt("Rating"));
                temp.setPicture(rs.getInt("Picture"));
                food.add(temp);
            }

            pstmt.close();
            conn.close();
            return food;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Get food by price
     * @param price
     * @return 
     */
    public ArrayList<Food> GetAllByPrice(int price) {

        ArrayList<Food> food = new ArrayList<>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Food WHERE Price < ?  ORDER BY FoodID");
            pstmt.setInt(1, price);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food temp = new Food();
                temp.setFoodID(rs.getInt("FoodID"));
                temp.setRestaurantName(rs.getString("RestaurantName"));
                temp.setFoodName(rs.getString("FoodName"));
                temp.setFoodDescription(rs.getString("FoodDescription"));
                temp.setPrice(rs.getDouble("Price"));
                temp.setRating(rs.getInt("Rating"));
                temp.setPicture(rs.getInt("Picture"));
                food.add(temp);
            }

            pstmt.close();
            conn.close();
            return food;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    /**
     * TODO: LOCATION
     * Get Food By Location
     * @return 
     */
    public ArrayList<Food> GetAllByLocation(){
        
        ArrayList<Food> food = new ArrayList<>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT "
                    + "F.foodId, R.restaurantName, F.foodName, F.foodDescription, "
                    + "F.price, F.Rating, F.picture, R.Address, R.longitude, R.latitude "
                    + "FROM Food F JOIN RESTAURANTS R on F.RestaurantName = R.RestaurantName; ");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food temp = new Food();
                temp.setFoodID(rs.getInt("FoodID"));
                temp.setRestaurantName(rs.getString("RestaurantName"));
                temp.setFoodName(rs.getString("FoodName"));
                temp.setFoodDescription(rs.getString("FoodDescription"));
                temp.setPrice(rs.getDouble("Price"));
                temp.setRating(rs.getInt("Rating"));
                temp.setPicture(rs.getInt("Picture"));
                temp.setAddress(rs.getString("address"));
                temp.setLongitude(rs.getFloat("longitude"));
                temp.setLatitude(rs.getFloat("latitude"));
                food.add(temp);
            }

            pstmt.close();
            conn.close();
            return food;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Get food by price and location
     * @param price
     * @return 
     */
    public ArrayList<Food> GetAllByBoth(double price) {

        ArrayList<Food> food = new ArrayList<>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT "
                    + "F.foodId, R.restaurantName, F.foodName, F.foodDescription, "
                    + "F.price, F.Rating, F.picture, R.Address, R.longitude, R.latitude "
                    + "FROM Food F JOIN RESTAURANTS R on F.RestaurantName = R.RestaurantName "
                    + "WHERE F.price <= ? ;");
            pstmt.setDouble(1, price);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food temp = new Food();
                temp.setFoodID(rs.getInt("FoodID"));
                temp.setRestaurantName(rs.getString("RestaurantName"));
                temp.setFoodName(rs.getString("FoodName"));
                temp.setFoodDescription(rs.getString("FoodDescription"));
                temp.setPrice(rs.getDouble("Price"));
                temp.setRating(rs.getInt("Rating"));
                temp.setPicture(rs.getInt("Picture"));
                temp.setAddress(rs.getString("address"));
                temp.setLongitude(rs.getDouble("longitude"));
                temp.setLatitude(rs.getDouble("latitude"));
                food.add(temp);
            }

            pstmt.close();
            conn.close();
            return food;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    /**
     * Get food by price and location
     * @param price
     * @return 
     */
    public ArrayList<Food> GetNereastBoth(double price) {

        ArrayList<Food> food = new ArrayList<>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT"
                    + "f.foodId, R.restaurantName, F.foodName, F.foodDescription, "
                    + "F.price, F.Rating, F.picture, R.Address, R.longitude, R.latitude "
                    + "FROM Food F JOIN RESTAURANTS R on F.RestaurantName = R.RestaurantName "
                    + "WHERE F.price <= ? ");
            pstmt.setDouble(1, price);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Food temp = new Food();
                temp.setFoodID(rs.getInt("FoodID"));
                temp.setRestaurantName(rs.getString("RestaurantName"));
                temp.setFoodName(rs.getString("FoodName"));
                temp.setFoodDescription(rs.getString("FoodDescription"));
                temp.setPrice(rs.getDouble("Price"));
                temp.setRating(rs.getInt("Rating"));
                temp.setPicture(rs.getInt("Picture"));
                temp.setAddress(rs.getString("address"));
                temp.setLongitude(rs.getDouble("longitude"));
                temp.setLatitude(rs.getDouble("latitude"));
                food.add(temp);
            }

            pstmt.close();
            conn.close();
            return food;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
