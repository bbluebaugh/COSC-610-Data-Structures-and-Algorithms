package Matrix;

import java.io.*;
import java.util.ArrayList;


public class FileHandler {

    String line = "";
    int countrow = 0;
    int countcolumn = 0;
    int filecount = 0;

    MatrixCal cal = new MatrixCal();

    public boolean readFile(String filename1){
        File file = new File(filename1 + ".txt");
        ArrayList<Integer> arr = new ArrayList<>();
        boolean check = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {

                for (int i = 0; i < st.length(); i++) {
                    char c = st.charAt(i);
                    if (c != ',') { //Checking if the char is a number
                        line = line + c; // Adding the number to a string variable
                    }

                    if (c == ',') { // Checking if the whole number has been read
                        arr.add(Integer.parseInt(line)); // Adding the number to the Array
                        line = "";
                        if (!check) { // Checking how many elements are in the Matrix
                            countcolumn++;
                        }

                    }
                    if (i == st.length() - 1) { // End of the row
                        arr.add(Integer.parseInt(line));
                        line = "";
                        if (!check) {
                            countcolumn++;
                        }
                    }

                }
                countrow++;

                check = true;

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        cal.assignFirst(countrow, countcolumn, arr); //Changing the Array to a matrix
        countrow = 0;
        countcolumn = 0;
        boolean ret = cal.calculateMatrix();
        return ret;

    }

    public void writeFile(String outputFilename) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilename + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int c = 0; c < cal.matrix1row; c++) {
                for (int d = 0; d < cal.matrix2column; d++) {
                    int number = cal.sum1[c][d];
                    bufferedWriter.write(""+number);
                    if(d != cal.matrix2column-1){
                        bufferedWriter.write(",\t");
                    }
                    
                }
                bufferedWriter.newLine();//Moving to new line
            }
            bufferedWriter.close();// Closing File
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '" + outputFilename + "'");
        }
    }
}