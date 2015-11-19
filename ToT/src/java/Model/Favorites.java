package Model;

/**
 *
 * @author shermaine sy
 * @author geraldine atayan
 */
public class Favorites extends User {

    private String FoodID;

    public Favorites(int userID, String email, String userName, String password) {
        super(userID, email, userName, password);
    }

    public Favorites() {
    }

    public String getFoodID() {
        return FoodID;
    }

    public void setFoodID(String FoodID) {
        this.FoodID = FoodID;
    }

}
