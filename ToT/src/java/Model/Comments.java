package Model;

/**
 *
 * @author shermaine sy
 * @author geraldine atayan
 *
 */
public class Comments extends Food {
    private int CommentsID;
    private String comments;
    private String IDUser;

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

    /**
     * @return the CommentsID
     */
    public int getCommentsID() {
        return CommentsID;
    }

    /**
     * @param CommentsID the CommentsID to set
     */
    public void setCommentsID(int CommentsID) {
        this.CommentsID = CommentsID;
    }

    /**
     * @return the IDUser
     */
    public String getIDUser() {
        return IDUser;
    }

    /**
     * @param IDUser the IDUser to set
     */
    public void setIDUser(String IDUser) {
        this.IDUser = IDUser;
    }

}
