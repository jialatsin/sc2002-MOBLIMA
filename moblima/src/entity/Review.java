package entity;

import java.io.Serializable;
/**
 * It Represents a Review added by MovieGoer to a Movie
 * @author OOP SSP1 Lab Group 4
 * @version 7/11/2022
 */
public class Review implements Serializable {
    /**
     * The rating of the movie
     */
	private int rating;
	/**
	 * The review of the movie
	 */
    private String reviewText;
    /**
     * Creates a Review with these given attributes
     * Review information consisting of a a rating and review text
     * @param rating      The rating of the movie
     * @param reviewText  The review of the movie
     */
    public Review(int rating, String reviewText) {
        this.rating = rating;
        this.reviewText = reviewText;
    }
    /**
     * Get the rating of the movie
     * @return rating The rating of the movie
     */
    public int getRating() {
        return rating;
    }	
    /**
     * The review text of the movie
     * @return review The review text of the movie
     */
    public String getReviewText() {
        return reviewText;
    }
    /**
     * The rating of the movie
     * @param rating The rating of the movie
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    /**
     * The review text of the movie
     * @param reviewText The review text of the movie
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    /**
     * String displaying the rating and the review text of the movie.
     * @return string displaying the rating and the review text of the movie.
     */
    @Override
    public String toString() {
        StringBuffer stars = new StringBuffer();
        int solidStars = rating;
        for (int i = 0; i < solidStars; i++) {
            stars.insert(i, "*");
        }
        return " [rating = " + stars.toString() + ", reviewText = \"" + reviewText + "\"]";
    }
    /**
     * Compare 2 Review Instances to check if they are identical
     * @return boolean      Return true if both reviews are identical based on their rating and review text, else false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Review) {
            Review other = (Review) obj;
            return this.rating == other.getRating()
                    && this.reviewText.equals(other.getReviewText());
        }
        return false;
    }
}

