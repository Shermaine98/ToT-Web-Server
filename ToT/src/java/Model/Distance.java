/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.FoodDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class Distance {

    ArrayList<Food> foodFiltered;
    Random generator;

    private float distance(double lat1, double lon1, double lat2, double lon2) {
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

    public Food getFilteredLocation(double lat2, double lon2, double distanceC) {
        FoodDAO foodDAO = new FoodDAO();
        ArrayList<Food> foodList = new ArrayList<>();
        foodFiltered = new ArrayList<>();
        generator = new Random();
        Food result = new Food();

        foodList = foodDAO.GetAllByLocation();

        for (int i = 0; i < foodList.size(); i++) {
            double distanceR = distance(foodList.get(i).getLatitude(), foodList.get(i).getLongitude(), lat2, lon2);
            if (distanceC >= distanceR) {
                foodFiltered.add(foodList.get(i));
            }
        }
        System.out.print(foodFiltered.size());
        if (foodFiltered.isEmpty()) {
            result = getNearest(foodList, lat2, lon2);
            return result;
        } else {
            int index = generator.nextInt(foodFiltered.size());
            result = foodFiltered.get(index);
            return result;
        }

    }

    public Food getFilteredBoth(double lat2, double lon2, double distanceC, double price) {
        FoodDAO foodDAO = new FoodDAO();
        ArrayList<Food> foodList = new ArrayList<>();
        foodFiltered = new ArrayList<>();
        generator = new Random();
        Food result = new Food();
        foodList = foodDAO.GetAllByBoth(price);
        
        for (int i = 0; i < foodList.size(); i++) {
            float distanceR = distance(foodList.get(i).getLatitude(), foodList.get(i).getLongitude(), lat2, lon2);
            if (distanceC >= distanceR) {
                foodFiltered.add(foodList.get(i));
            }
        }

        if (foodFiltered.isEmpty()) {
            result = getNearest(foodList, lat2, lon2);
            return result;
        } else {
            int index = generator.nextInt(foodFiltered.size());
            result = foodFiltered.get(index);
            return result;
        }

    }

    public Food getNearest(ArrayList<Food> foodList, double lat2, double lon2) {
        float min = 0;
        int index = 0;

        Collections.shuffle(foodList);
        for (int i = 0; i < foodList.size(); i++) {
            float distanceR = distance(foodList.get(i).getLatitude(), foodList.get(i).getLongitude(), lat2, lon2);
            if (i == 0) {
                min = distanceR;
            } else {
                if (min >= distanceR) {
                    min = distanceR;
                    index = i;
                }
            }
        }
        return foodList.get(index);
    }
    
    public  static void main(String args[]){
            Distance Distance = new Distance();
        Food filteredLocation = Distance.getFilteredBoth(14.601655500,  120.97737, 0.0, 500.00);
           System.out.println(filteredLocation.getFoodName());
    }

}
