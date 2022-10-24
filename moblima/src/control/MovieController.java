package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entity.*;

public class MovieController {
    private static String FILE = "moblima/db/movie.txt";

    public static void addToDatabase(Movie movie) {
        try {
            ArrayList<Movie> data = new ArrayList<Movie>();
            // Append new data entry to existing database file
            if (new File(FILE).exists()) {
                data = readFromDatabase();
            }
            data.add(movie);

            FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    public static ArrayList<Movie> readFromDatabase() {
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Movie> data = (ArrayList<Movie>) ois.readObject();
            ois.close();
            return data;
        } catch (IOException | ClassNotFoundException e) {
        }
        return new ArrayList<Movie>();
    }

    public static void overwriteDatabase(ArrayList<Movie> data) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }
}
