package Model;
/**
 *
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class Food   {

    private int foodID;
    private String foodName;
    private String foodDescription;
    private double price;
    private double rating;
    private int picture;
    private String restaurantName;
    private String address;
    public double longitude;
    public double latitude;

    
    
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

    /**
     * @return the foodID
     */
    public int getFoodID() {
        return foodID;
    }

    /**
     * @return the foodName
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * @return the foodDescription
     */
    public String getFoodDescription() {
        return foodDescription;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @return the picture
     */
    public int getPicture() {
        return picture;
    }

    /**
     * @return the restaurantName
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * @param restaurantName the restaurantName to set
     */
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
