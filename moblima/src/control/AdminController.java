package control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import entity.*;

public class AdminController {
    public static String FILE = "moblima/db/admin.txt";

    public static void create(Admin admin) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

        } catch (IOException e) {
        }
    }
}
