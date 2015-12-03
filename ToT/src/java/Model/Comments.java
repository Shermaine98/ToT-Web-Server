package Model;

/**
 *
 * @author shermaine sy
 * @author geraldine atayan
 *
 */
public class Comments  {
    private int CommentsID;
    private String comments;
    private String IDUser;

  

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
