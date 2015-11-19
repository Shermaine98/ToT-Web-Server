package Model;

/**
 *
 * @author shermaine sy
 * @author geraldine atayan
 */
public class Food extends Restaurant {

    private int foodID;
    private String foodName;
    private String foodDescription;
    private double price;
    private double rating;
    private int picture;

    public Food(int foodID, String foodName, String foodDescription, double price, double rating, int picture, String restaurantName, String address, double longitude, double latitude) {
        super(restaurantName, address, longitude, latitude);
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.price = price;
        this.rating = rating;
        this.picture = picture;
    }

    
    public Food() {

    }

    /**
     * @param foodID the foodID to set
     */
    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    /**
     * @param foodName the foodName to set
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    /**
     * @param foodDescription the foodDescription to set
     */
    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(int picture) {
        this.picture = picture;
    }

}
