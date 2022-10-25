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
            for (int s=0; s<5; s++) {
                if (solidStars-->0) stars.insert(s, "★");
                else stars.insert(s, "☆");
            }
        return " [rating=" + stars.toString() + ", reviewText=" + reviewText + "]";
    }
}
