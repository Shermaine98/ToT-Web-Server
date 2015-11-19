package Model;

/**
 *
 * @author shermaine sy
 * @author geraldine atayan
 */
public class History extends User {

    private int foodID;

    public History(int userID, String email, String userName, String password) {
        super(userID, email, userName, password);
    }

    public History() {
    }

    /**
     * @return the foodID
     */
    public int getFoodID() {
        return foodID;
    }

    /**
     * @param foodID the foodID to set
     */
    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

}
