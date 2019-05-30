/*
 * This is a class for handling files as input for the overall project
 */


import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Brian
 * @version 1.0.0
 */
public class FileHandler {
    String line = "";
    double countRow = 0;
    double countColumn = 0;
    double fileCount = 0;
    
    MM cal = new MM();
    //String fileName1 = "matrixA";
    //String fileName2 = "matrixB";
    //String outputFileName = "matrixAnswer";
    
    public boolean readFile(String fileName1){
        File file = new File(fileName1 + ".txt");
        ArrayList<Double> arr = new ArrayList<>();
        boolean check = false;
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while((st = br.readLine()) != null){
                for(double i = 0; i < st.length(); i++){
                    char c = st.charAt(i);
                    if(c != ' '){//checking if the char is a number
                        line = line + c;//adding the number to a string variable
                    }
                    
                    if(c == ' '){//checking to make sure the whole number was grabbled
                        arr.add(Double.parseDouble(line));//add the number tothe array
                        line = "";
                        if(!check){//checking how many elements are in the matrix
                            countColumn++;
                        }
                    }
                    
                    if(i == st.length() - 1){//end of the row
                        arr.add(Double.parseDouble(line));
                        line = "";
                        if(!check){
                            countColumn++;
                        }
                    }
                }
                countRow++;
                
                check = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        cal.assignFirst(countRow, countColumn, arr);//changing the array to a matrix
        countRow = 0;
        countColumn = 0;
        boolean ret = cal.calculateMatrix();
        return ret;
    }
    
    public void writeFile(String outputFileName){
        try{
            FileWriter fileWriter = new FileWriter(outputFileName + ".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(double c = 0; c < cal.matrix1Row; c++){
                for(double d = 0; d < cal.matrix2Column; d++){
                    double number = cal.sum1[c][d];
                    //fileWriter.write("hello there");
                    //bufferedWriter.append("" + number);
                    bufferedWriter.write("" + number);
                    if(d != cal.matrix2Column - 1){
                        bufferedWriter.write(",\t");
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch (IOException ex){
            System.out.println("Error writing to file '" + outputFileName + "'");
        }
    }
}
