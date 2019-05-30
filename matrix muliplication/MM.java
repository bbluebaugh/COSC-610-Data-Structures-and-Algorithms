/*
 *This project is a matrix multiplier
 */


import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Brian
 */
public class MM {
    
    int matrix1[][];
    int matrix1Row, matrix2Column, matrix2Row, matrix1Column;
    int matrix2[][];
    int sum1[][];
    boolean flag = true;
    
    public void assignFirst(int row, int column, ArrayList<Integer> arr){
        int counter = 0;
        int first[][] = new int[row][column];
        int c, d;
        for(c = 0; c < row; c++){
            for(d = 0; d < column; d++){
                first[c][d] = arr.get(counter);
                if(counter == arr.size() - 1){//moving to the next row
                    break;
                }
                counter++;
            }
        }
        if(flag){//checking whether it is the first or second matrix
            matrix1 = first;
            matrix1Row = row;
            matrix1Column = column;
            flag = false;
        }else{
            matrix2 = first;
            matrix2Row = row;
            matrix2Column = column;
        }
        
    }
    
    public boolean calculateMatrix(){//calculate the matrix
        if(matrix1Column != matrix2Row){//check if the matrices match in size
            return false;//if not break because cannot perform calculation
        }
        int multiply[][] = new int[matrix1Row][matrix2Column];//make a new matrix for the multiplication based on
        //size of the original 2 matrices
        int sum = 0, c, d, k;
        for(c = 0; c < matrix1Row; c++){
            for(d = 0; d < matrix2Column; d++){
                for(k = 0; k < matrix2Row; k++){
                    sum = sum + matrix1[c][k] *matrix2[k][d];
                }
                multiply[c][d] = sum;
                sum = 0;
            }
        }
        sum1 = multiply;
        for(c = 0; c < matrix1Row; c++){
            for(d = 0; d < matrix2Column; d++){
                System.out.print(multiply[c][d]);
                System.out.println(",\t");
            }
            System.out.println("\n");
        }
        return true;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MM m = new MM();
        FileHandler file = new FileHandler();
        
        file.readFile("matrixA");
        file.readFile("matrixB");
        file.writeFile("matixAnswer");
        //m.assignFirst(file.countRow, file.countColumn, file.arr);
        //m.calculateMatrix();
        
    }
    
}
