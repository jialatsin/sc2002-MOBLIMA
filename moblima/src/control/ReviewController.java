package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entity.*;

public class ReviewController {
    private static String FILE = "moblima/db/review.txt";

    public static void addToDatabase(Review review) {
        try {
            ArrayList<Review> data = new ArrayList<Review>();
            // Append new data entry to existing database file
            if (new File(FILE).exists()) {
                data = readFromDatabase();
            }
            data.add(review);

            FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    public static ArrayList<Review> readFromDatabase() {
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Review> data = (ArrayList<Review>) ois.readObject();
            ois.close();
            return data;
        } catch (IOException | ClassNotFoundException e) {
        }
        return new ArrayList<Review>();
    }

    public static void overwriteDatabase(ArrayList<Review> data) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    public static void addReview(String title) {

    }
}
