package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entity.*;

public class AdminController {
    private static String FILE = "moblima/db/admin.txt";

    public static void addToDatabase(Admin admin) {
        try {
            ArrayList<Admin> data = new ArrayList<Admin>();
            // Append new data entry to existing database file
            if (new File(FILE).exists()) {
                data = readFromDatabase();
            }
            data.add(admin);

            FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    public static ArrayList<Admin> readFromDatabase() {
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Admin> data = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return data;
        } catch (IOException | ClassNotFoundException e) {
        }
        return new ArrayList<Admin>();
    }

    public static void overwriteDatabase(ArrayList<Admin> data) {
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
