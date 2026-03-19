package util;

import model.Medicine;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private static final String FILE_NAME = "pharmacy_db.txt";

    public static void saveToFile(ArrayList<Medicine> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Medicine m : list) {
                bw.write(
                        m.getName() + "," +
                        m.getBatch() + "," +
                        m.getPrice() + "," +
                        m.getQuantity() + "," +
                        m.getExpiryDate() + "," +
                        m.getCategory() + "," +
                        m.getPriorityLevel() + "," +
                        m.getSupplier()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public static ArrayList<Medicine> loadFromFile() {
        ArrayList<Medicine> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Medicine m = new Medicine(
                        data[0], data[1],
                        Double.parseDouble(data[2]),
                        Integer.parseInt(data[3]),
                        data[4], data[5],
                        Integer.parseInt(data[6]),
                        data[7]
                );

                list.add(m);
            }

        } catch (IOException e) {
            System.out.println("No existing database found. Starting fresh.");
        }

        return list;
    }
}