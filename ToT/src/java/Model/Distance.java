/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.FoodDAO;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author shermainesy
 */
public class Distance {
        ArrayList<Food> foodFiltered;
         Random generator;
         
    private float distance(float lat1, float lon1, float lat2, float lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1609.344;
        return (float) dist;
    }
    
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    
    public Food getNearest(float lat2, float lon2,float distanceC){
        FoodDAO foodDAO = new FoodDAO();
        ArrayList<Food> foodList = new ArrayList<>();
        foodFiltered = new ArrayList<>();
        generator = new Random();
        Food result = new Food();
        
        foodList = foodDAO.GetAllByLocation();
        
        for(int i =0; i<foodList.size();i++){
        float distanceR =  distance(foodList.get(i).getLatitude(),foodList.get(i).getLongitude(),lat2,lon2);
            if(distanceC >= distanceR){
                foodFiltered.add(foodList.get(i));
            }
        }

        int index = generator.nextInt(foodFiltered.size());
        result = foodFiltered.get(index);
        return result;
    
    }
   
    public Food getBoth(float lat2, float lon2,float distanceC, double price){
        FoodDAO foodDAO = new FoodDAO();
        ArrayList<Food> foodList = new ArrayList<>();
        foodFiltered = new ArrayList<>();
        generator = new Random();
        Food result = new Food();
        foodList = foodDAO.GetAllByBoth(price);
        for(int i =0; i<foodList.size();i++){
        float distanceR =  distance(foodList.get(i).getLatitude(),foodList.get(i).getLongitude(),lat2,lon2);
            if(distanceC >= distanceR){
                foodFiltered.add(foodList.get(i));
            }
        }

        int index = generator.nextInt(foodFiltered.size());
        result = foodFiltered.get(index);
        return result;
    
    }

}
