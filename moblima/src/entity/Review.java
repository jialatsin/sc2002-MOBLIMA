package entity;

import java.io.Serializable;

/** Represents a Review added by a moviegoer to a movie. */
public class Review implements Serializable {
    /** Rating given for this review. */
    private int rating;
    /** Comment given for this review. */
    private String reviewText;

    /**
     * Creates a Review with the given attributes.
     * 
     * @param rating     The rating for this review.
     * @param reviewText The comment for this review.
     */
    public Review(int rating, String reviewText) {
        this.rating = rating;
        this.reviewText = reviewText;
    }

    /**
     * Gets the rating for this review.
     * 
     * @return Rating for this review.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Gets the comment for this review.
     * 
     * @return Comment for this review.
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Changes the rating for this review.
     * 
     * @param rating The input rating.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Changes the comment for this review.
     * 
     * @param reviewText The input comment.
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    /**
     * Returns a string containing this review's rating and comment.
     * 
     * @return String containing this review's rating and comment.
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
     * Compares 2 Review instances to check if they are identical.
     * 
     * @return Returns true if all attributes of both reviews are equal, else
     *         false.
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
