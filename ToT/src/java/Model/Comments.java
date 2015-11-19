package Model;

/**
 *
 * @author shermaine sy
 * @author geraldine atayan
 *
 */
public class Comments extends Food {

    private String comments;

    public Comments(int foodID, String foodName, String foodDescription, double price, double rating, int picture, String restaurantName, String address, double longtitude, double latitiue) {
        super(foodID, foodName, foodDescription, price, rating, picture, restaurantName, address, longtitude, latitiue);
    }

    public Comments() {
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
