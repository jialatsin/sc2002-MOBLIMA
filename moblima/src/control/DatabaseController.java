package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DatabaseController<DataType> {
    private String filePath;

    public DatabaseController(String filePath) {
        this.filePath = filePath;
    }

    public void addToDatabase(DataType data) {
        try {
            ArrayList<DataType> dataList = new ArrayList<DataType>();
            // Append new dataList entry to existing database file
            if (new File(filePath).exists()) {
                dataList = readFromDatabase();
            }

            dataList.add(data);

            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dataList);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<DataType> readFromDatabase() {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<DataType> dataList = (ArrayList<DataType>) ois.readObject();
            ois.close();
            return dataList;
        } catch (IOException | ClassNotFoundException e) {
        }
        return new ArrayList<DataType>();
    }

    public void overwriteDatabase(ArrayList<DataType> dataList) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dataList);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }
}
