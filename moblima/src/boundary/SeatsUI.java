package boundary;


import java.util.ArrayList;

//import control.seatController;
//import entity.Admin;

public class SeatsUI {

    public static void main() {
        // login();

        int selection;
        do {
            System.out.println("\n===== ADMIN =====\n"
                    + "1. Platinum Suites\n"
                    + "2. Gold Suites\n"
                    + "3. Standard Suites\n"
                    + "0. Previous page\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    platinumSuites();
                    break;
                case 2:
                    goldSuites();
                    break;
                case 3:
                    standardSuites();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    public static void platinumSuites() {
        int rows = 6;
        int columns = 8;
        int[][] matrix = new int[rows][columns];
        //Need get seat number from Database and populate matrix first!
        System.out.println("\n===== Platinum Suites Seats =====\n");
        for (int r = 0; r < rows; r++) {
            if(r == rows - 1){
                System.out.print("  ");
                for (int c = 0; c < columns - 2; c++) {
                    if(c == columns/3 || c == columns/3 + columns/3){
                        System.out.print("  " + (char)('A' + c) + " ");
                    }else{
                        System.out.print((char)('A' + c) + " ");
                    }
                        
                }
            }else{
                System.out.print(r + 1 + " ");
                for (int c = 0; c < columns; c++) {
                    if(c == columns/3 || c == columns/3 + columns/3 + 1){
                        System.out.print("  ");
                    }else{
                        if(r == rows - 1){
                            System.out.print((char)('A' + c) + " ");
                        }else if(matrix[r][c] != 0){
                            System.out.print(c + " ");
                        }else{
                            System.out.print("X ");
                        }
                    }
                    
                    
                }
            }
			
            System.out.println("");
		}
    }

    public static void goldSuites() {
        int rows = 10;
        int columns = 14;
        int[][] matrix = new int[rows][columns];
        //Need get seat number from Database and populate matrix first!
        System.out.println("\n===== Gold Suites Seats =====\n");
        for (int r = 0; r < rows; r++) {
            if(r == rows - 1){
                System.out.print("  ");
                for (int c = 0; c < columns - 2; c++) {
                    if(c == columns/3 || c == columns/3 + columns/3){
                        System.out.print("  " + (char)('A' + c) + " ");
                    }else{
                        System.out.print((char)('A' + c) + " ");
                    }
                        
                }
            }else if(r > rows - 5){
                System.out.print(r + 1 + " ");
                for (int c = 0; c < columns; c++) {
                    if(c == columns/3 || c == columns/3 + columns/3 + 1){
                        System.out.print("  ");
                    }else{
                        if(matrix[r][c] != 0){
                            System.out.print(c + " ");
                        }else{
                            if(c > 4 && c < 9){
                                if((c+1)%2 == 0){
                                    System.out.print((char)('[') + " ");
                                }else{
                                    System.out.print((char)(']') + " ");
                                }
                            }else{
                                if(c%2 == 0){
                                    System.out.print((char)('[') + " ");
                                }else{
                                    System.out.print((char)(']') + " ");
                                }
                            }
                            
                            
                        }
                    }
                }
            }else{
                System.out.print(r + 1 + " ");
                for (int c = 0; c < columns; c++) {
                    if(c == columns/3 || c == columns/3 + columns/3 + 1){
                        System.out.print("  ");
                    }else{
                        if(matrix[r][c] != 0){
                            System.out.print(c + " ");
                        }else{
                            System.out.print("X ");
                        }
                    }
                }
            }
			
            System.out.println("");
		}
    }

    public static void standardSuites() {
        int rows = 10;
        int columns = 14;
        int[][] matrix = new int[rows][columns];
        //Need get seat number from Database and populate matrix first!
        System.out.println("\n===== Standard Suites Seats =====\n");
        for (int r = 0; r < rows; r++) {
            if(r == rows - 1){
                System.out.print("  ");
                for (char c = 'A'; c < (char)(columns + 'A' - 2); c++) {
                    if(c == (char)(columns/3 + 'A' ) || c == (char)(columns/3 + columns/3 + 'A')){
                        System.out.print("  " + c + " ");
                    }else{
                        System.out.print(c + " "); 
                    }
                        
                }
            }else{
                System.out.print(r + 1 + " ");
                for (int c = 0; c < columns; c++) {
                    if(c == columns/3 || c == columns/3 + columns/3 + 1){
                        System.out.print("  ");
                    }else{
                        if(matrix[r][c] != 0){
                            System.out.print(c + " ");
                        }else{
                            System.out.print("X ");
                        }
                    }
                    
                    
                }
            }
			
            System.out.println("");
		}
    }
}
