package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Represents a DatabaseController which contains functions to read and write
 * data of a specified data type from a database file. Inherited by all
 * controller classes with database storage.
 */
public abstract class DatabaseController<DataType> {
    /** File path name of database file to access. */
    private String filePath;

    /**
     * Creates a DatabaseController with the given file path.
     * 
     * @param filePath File path of the database file to access.
     */
    public DatabaseController(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new data object of DataType and add to corresponding database file.
     * If database file exists, existing records are read and and new data object is
     * appended to end of file.
     * If database file does not exist, data object is written and saved to a new
     * database file.
     * 
     * @param data Data object to be added into database.
     */
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

    /**
     * Reads database file and returns a list of all data objects in the database.
     * 
     * @return Returns a list of data objects if database file is found, else empty
     *         list.
     */
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

    /**
     * Overwrites the existing database file with the given new list of data
     * objects.
     * 
     * @param dataList Returns the list of data objects to overwrite the database
     *                 with.
     */
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
