package uco.i62rorid.Utils;

import java.util.Scanner;

public class UserInput {
    public static Integer getMenuOptionFromUser(){
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if (option.equals("")) return -10;
        try{
            return Integer.parseInt(option);
        }catch (NumberFormatException e){
            return -11;
        }
    }

    public static Integer getIdFromUser(){
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if (option.equals("")) return -1;
        try{
            return Integer.parseInt(option);
        }catch (NumberFormatException e){
            return -2;
        }
    }

    public static String getStringFromUser(){
        Scanner scanner = new Scanner(System.in);
        String input;
        do{
            input = scanner.nextLine();
        }while(input.isEmpty());
        return input;
    }

    public static void pause(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tPress [Enter] to continue");
        scanner.nextLine();
    }

    public static void clear(){
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
