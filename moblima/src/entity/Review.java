package entity;

import java.io.Serializable;

public class Review implements Serializable {
    private int rating;
    private String reviewText;

    public Review(int rating, String reviewText) {
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    @Override
    public String toString() {
            StringBuffer stars = new StringBuffer();
            int solidStars = rating;
            for (int i=0; i<solidStars; i++) {
                stars.insert(i, "*");
            }
        return " [rating = " + stars.toString() + ", reviewText = \"" + reviewText + "\"]";
    }
}
