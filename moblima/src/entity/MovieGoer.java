package entity;

import java.io.Serializable;

/**
 * Represents a MovieGoer.
 * Details of the moviegoer are captured by the application upon booking of
 * tickets. The moviegoer can only read database records and create certain
 * records (eg. reviews).
 */
public class MovieGoer implements Serializable {
    /** Name of this moviegoer. */
    private String name;
    /** Mobile number of this moviegoer. */
    private String mobile;
    /** Email address of this moviegoer. */
    private String email;

    /**
     * Creates a MovieGoer with the given attributes.
     * 
     * @param name   The moviegoer's name.
     * @param mobile The moviegoer's mobile number.
     * @param email  The moviegoer's email address.
     */
    public MovieGoer(String name, String mobile, String email) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    /**
     * Gets the name of this moviegoer.
     * 
     * @return String containing the name of this moviegoer.
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of this moviegoer.
     * 
     * @param name The input name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the mobile number of this moviegoer.
     * 
     * @return String containing the mobile number of this moviegoer.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Changes the mobile number of this moviegoer.
     * 
     * @param mobile The input mobile number.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets the email address of this moviegoer.
     * 
     * @return String containing the email address of this moviegoer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Changes the email address of this moviegoer.
     * 
     * @param email The input email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string containing this moviegoer's name, mobile number and email
     * address.
     * 
     * @return String containing this moviegoer's name, mobile number and email
     *         address.
     */
    @Override
    public String toString() {
        return "MovieGoer [name=" + name + ", mobile=" + mobile + ", email=" + email + "]";
    }

}
