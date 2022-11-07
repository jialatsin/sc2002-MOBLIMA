package entity;

import java.io.Serializable;

/**
 * It Represents a MovieGoer and their details are captured by the application upon booking of tickets. The moviegoer can can only read database records and create certain records (eg. reviews)
 * @author OOP SSP1 Lab Group 4
 * @version 07/11/2022
 */
public class MovieGoer implements Serializable {
    /**
     * name of the movie-goer
     */
	private String name;
	/**
	 * mobile number of the movie-goer
	 */
    private String mobile;
    /**
     * email of the movie-goer
     */
    private String email;
    /**
     * Creates Information of the MovieGoer using these given attributes
     * @param name   The name of the movie-goer
     * @param mobile The number of the movie-goer
     * @param email  The email id of the movie-goer
     */
    public MovieGoer(String name, String mobile, String email) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }
    /**
     * Get the name of the movie-goer
     * @return name The name of the movie-goer
     */
    public String getName() {
        return name;
    }
    /**
     * The name of the movie-goer
     * @param name The name of the movie-goer
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * The mobile number of the movie-goer
     * @return mobile The mobile number of the movie-goer
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * The mobile number of the movie-goer
     * @param mobile The mobile number of the movie-goer
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * Get the email id of the movie goer
     * @return email The email id of the movie goer
     */
    public String getEmail() {
        return email;
    }
    /**
     * The email id of the movie goer
     * @param email The email id of the movie goer
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * String to display the movie-goers name, mobile number and email id
     * @return string displaying the movie-goers name, mobile number and email id
     */
    @Override
    public String toString() {
        return "MovieGoer [name=" + name + ", mobile=" + mobile + ", email=" + email + "]";
    }

}
