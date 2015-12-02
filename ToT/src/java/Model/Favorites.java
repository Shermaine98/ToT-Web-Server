package Model;

/**
 *
 * @author shermaine sy
 * @author geraldine atayan
 */
public class Favorites  {

  
  private String UserName;
  private int UseriD;
   private int foodID;
    private String foodName;
    private String foodDescription;
    private double price;
    private double rating;
    private int picture;

   
    /**
     * @return the UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the UseriD
     */
    public int getUseriD() {
        return UseriD;
    }

    /**
     * @param UseriD the UseriD to set
     */
    public void setUseriD(int UseriD) {
        this.UseriD = UseriD;
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
