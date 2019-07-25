package tictactoe;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static String cells;
    public static String[][] ticTacToe = new String[3][3];

    public static void main(String[] args) {
        do{
            cells = scanner.nextLine();
        }while (cells.length() == 0);

        matrizGame();

        //printGame();
        moveGame();
        //StateGame();
    }

    public static void moveGame(){
        boolean ok = true;
        System.out.print("Enter the coordinates: ");

        //that coordinates start with 1 and can be 1, 2 or 3
        String firstDigito = scanner.next();
        String secondDigito = scanner.next();
        System.out.println();

        System.out.println("d1 = "+firstDigito+"  d2 = "+secondDigito);

        if (Character.isDigit(firstDigito.charAt(0)) && (Character.isDigit(secondDigito.charAt(0)))) {
            if (Integer.parseInt(firstDigito) <= 3 && Integer.parseInt(secondDigito) <= 3) {
                int column = Integer.parseInt(firstDigito); //first coordinate goes from left to right
                int line = Integer.parseInt(secondDigito);//second coodrinate goes from bottom to top.

                if (" ".equals(ticTacToe[3 - line][column - 1])) {
                    ticTacToe[3 - line][column - 1] = "X";
                }else{
                    System.out.println("This cell is occupied! Choose another one!");
                    ok = false;
                }
                if (ok) {
                    cells = "\"";
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            cells += ticTacToe[i][j];
                        }
                    }
                    cells += "\"";
                }
            }else{
                System.out.println("Coordinates should be from 1 to 3!");
                ok = false;
            }
        }else{
            System.out.println("You should enter numbers!");
            ok = false;
        }
        if (ok) {
            printGame();
        }else{
            moveGame();
        }

    }




    public static void matrizGame(){
        int cell = 0;

        for (int i=0;i < cells.length();i++){
            if (cells.charAt(i) == 'X' ||cells.charAt(i) == 'O' ||cells.charAt(i) == ' ') {
                cell++;
                switch (cell){
                    case 1:
                        ticTacToe[0][0] = cells.charAt(i)+"";
                        break;
                    case 2:
                        ticTacToe[0][1] = cells.charAt(i)+"";
                        break;
                    case 3:
                        ticTacToe[0][2] = cells.charAt(i)+"";
                        break;
                    case 4:
                        ticTacToe[1][0] = cells.charAt(i)+"";
                        break;
                    case 5:
                        ticTacToe[1][1] = cells.charAt(i)+"";
                        break;
                    case 6:
                        ticTacToe[1][2] = cells.charAt(i)+"";
                        break;
                    case 7:
                        ticTacToe[2][0] = cells.charAt(i)+"";
                        break;
                    case 8:
                        ticTacToe[2][1] = cells.charAt(i)+"";
                        break;
                    case 9:
                        ticTacToe[2][2] = cells.charAt(i)+"";
                        break;
                }
            }
        }
        /*for (int i=0;i < 3;i++){
            for (int j=0;j < 3;j++){
                if (ticTacToe[i][j] == null){
                    ticTacToe[i][j] = " ";
                }
            }
        }
        cells = "";
        for (int i=0;i < 3;i++){
            for (int j=0;j < 3;j++){
                cells += ticTacToe[i][j];
            }
        }*/
        printGame();
    }

    public static void printGame(){

        System.out.println("---------");
        int column = 0;
        if (cells.length() == 11) {

            for (int i = 0; i < cells.length(); i++) {
                if (i == 0) {
                    System.out.print("| ");
                }
                if (cells.charAt(i) == 'X' || cells.charAt(i) == 'O' || cells.charAt(i) == ' ') {
                    System.out.print(cells.charAt(i) + " ");
                    column++;
                    if ((column) % 3 == 0) {
                        System.out.println("|");
                        if (column < 9) System.out.print("| ");
                    }
                }
            }
        }
        System.out.println("---------");
    }

    public static void StateGame(){
        String[][] ticTacToe = new String[3][3];
        int position = 1;
        int contWhite = 0;
        int contX = 0;
        int contO = 0;
        for (int i=0;i < 3;i++){
            for (int j=0;j < 3;j++){
                ticTacToe[i][j] = cells.charAt(position++)+"";
                if (" ".equals(ticTacToe[i][j])){
                    contWhite++;
                }
                if ("X".equals(ticTacToe[i][j])){
                    contX++;
                }else if ("O".equals(ticTacToe[i][j])){
                    contO++;
                }
            }
        }
        String jogador = "";
        if (ticTacToe[0][0].equals(ticTacToe[0][1]) && ticTacToe[0][1].equals(ticTacToe[0][2])){ //linha 1
            jogador += ticTacToe[0][0];
        }
        if (ticTacToe[1][0].equals(ticTacToe[1][1]) && ticTacToe[1][1].equals(ticTacToe[1][2])){ //linha 2
            jogador += ticTacToe[1][0];
        }
        if (ticTacToe[2][0].equals(ticTacToe[2][1]) && ticTacToe[2][1].equals(ticTacToe[2][2])){ //linha 3
            jogador += ticTacToe[2][0];
        }
        if (ticTacToe[0][0].equals(ticTacToe[1][1]) && ticTacToe[1][1].equals(ticTacToe[2][2])){ //diagonal 1
            jogador += ticTacToe[0][0];
        }
        if (ticTacToe[0][2].equals(ticTacToe[1][1]) && ticTacToe[1][1].equals(ticTacToe[2][0])){ //diagonal 2
            jogador += ticTacToe[0][2];
        }
        if (ticTacToe[0][0].equals(ticTacToe[1][0]) && ticTacToe[0][0].equals(ticTacToe[2][0])){ //coluna 1
            jogador += ticTacToe[0][0];
        }
        if (ticTacToe[0][1].equals(ticTacToe[1][1]) && ticTacToe[1][1].equals(ticTacToe[2][1])){ //coluna 2
            jogador += ticTacToe[0][1];
        }
        if (ticTacToe[0][2].equals(ticTacToe[1][2]) && ticTacToe[0][2].equals(ticTacToe[2][2])){ //coluna 3
            jogador += ticTacToe[0][2];
        };

        if (jogador.length() == 1){
            System.out.println(jogador + " wins");
        }else{
            if (jogador.length() > 1 || contO - contX >= 2 || contX - contO >= 2){
                System.out.println("Impossible");
            }else if (contWhite == 0){
                System.out.println("Draw");
            }else
                System.out.println("Game not finished");
        }

    }
}