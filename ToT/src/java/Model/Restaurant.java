package Model;

/**
 *
 * @author shermaine sy
 * @author geraldine atayan
 */
public class Restaurant {

    private String restaurantName;
    private String address;
    private double longitude;
    private double latitude;

    public Restaurant(String restaurantName, String address, double longitude, double latitude) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Restaurant() {

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
    public void setLongitude (double longitude) {
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
