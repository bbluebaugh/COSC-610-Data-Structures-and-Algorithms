package Matrix;

import java.util.ArrayList;
import javax.swing.JOptionPane;


 
public class MatrixCal {

    int matrix1[][];
    int matrix1row, matrix2column, matrix2row, matrix1column;
    int matrix2[][];
    int sum1[][];
    boolean flag = true;    
	public void assignFirst(int row, int column, ArrayList<Integer> arr) {
        int counter = 0;
        int first[][] = new int[row][column];
        int c, d;
        for (c = 0; c < row; c++) {
            for (d = 0; d < column; d++) {

                first[c][d] = arr.get(counter);
                if (counter == arr.size() - 1) {//Moving to the next row
                    break;
                }
                counter++;

            }
        }
        if (flag) {//Checking whether first or second matrix
            matrix1 = first;
            matrix1row = row;
            matrix1column = column;
            flag = false;
        } else {
            matrix2 = first;
            matrix2column = column;
            matrix2row = row;
        }

    }

    public boolean calculateMatrix() { //Method to calculate matrix
        if(matrix1column != matrix2row){// Checking if matrix cannot be multiplied            
            return false;
        }
        int multiply[][] = new int[matrix1row][matrix2column];
        int sum = 0, c, d, k;
        for (c = 0; c < matrix1row; c++) {
            for (d = 0; d < matrix2column; d++) {
                for (k = 0; k < matrix2row; k++) {
                    sum = sum + matrix1[c][k] * matrix2[k][d];
                }

                multiply[c][d] = sum;
                sum = 0;
            }
        }
        sum1 = multiply;
        for (c = 0; c < matrix1row; c++) {
            for (d = 0; d < matrix2column; d++) {
                System.out.print(multiply[c][d]);
                System.out.print(",\t");
            }
            System.out.print("\n");
        }
        return true;
    }

}