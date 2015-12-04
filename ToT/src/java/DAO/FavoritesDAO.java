/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Database.DBConnectionFactory;
import Model.Comments;
import Model.Favorites;
import Model.Food;
import Model.User;
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
public class FavoritesDAO {

    public ArrayList<Favorites> GetFavorites(int UseriD) {

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            ArrayList<Favorites> Favorites = new ArrayList<>();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(""
                    + "SELECT F.FoodID, F.FoodName, F.FoodDescription, F.Picture, U.username, U.idUser, F.price "
                    + "FROM Food F JOIN FAVORITE FV ON F.FoodID = FV.FoodID JOIN USER U ON FV.idUser = U.idUser\n"
                    + "WHERE FV.idUser = ?;");
            pstmt.setInt(1, UseriD);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Favorites temp = new Favorites();
                temp.setPrice(rs.getDouble("price"));
                temp.setUseriD(rs.getInt("idUser"));
                temp.setUserName(rs.getString("userName"));
                temp.setFoodID(rs.getInt("foodID"));
                temp.setFoodName(rs.getString("FoodName"));
                temp.setFoodDescription(rs.getString("FoodDescription"));
                temp.setPicture(rs.getInt("Picture"));
                Favorites.add(temp);
            }

            pstmt.close();
            conn.close();
            return Favorites;
        } catch (SQLException ex) {
            Logger.getLogger(FavoritesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean removeFavorite(int userID, int foodID){
        
        boolean result = false;
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            ArrayList<Favorites> Favorites = new ArrayList<>();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(""
                    + "delete from favorite where idUser=? and FOODID=?");
            pstmt.setInt(1, userID);
            pstmt.setInt(2, foodID);
            int count = pstmt.executeUpdate();
            result = (count > 0);

            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FavoritesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
}
