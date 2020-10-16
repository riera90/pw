package uco.i62rorid.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * The type User input.
 */
public class UserInput {
    /**
     * Get menu option from user integer.
     *
     * @return the integer
     */
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

    /**
     * Get id from user integer.
     *
     * @return the integer
     */
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

    /**
     * Get string from user string.
     *
     * @return the string
     */
    public static String getStringFromUser(){
        Scanner scanner = new Scanner(System.in);
        String input;
        do{
            input = scanner.nextLine();
        }while(input.isEmpty());
        return input;
    }

    /**
     * Get date from user date.
     *
     * @return the date
     */
    public static Date getDateFromUser(){
        Scanner scanner = new Scanner(System.in);
        String input;
        do{
            System.out.print("[dd/mm/yyyy-HH:mm] ");
            input = scanner.nextLine();
            if (!input.isEmpty()){
                try {
                    return new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(input);
                }catch (ParseException e){
                    System.out.print("[dd/mm/yyyy-HH:mm] ");
                }
            }
        }while(true);
    }

    /**
     * Pause.
     */
    public static void pause(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tPress [Enter] to continue");
        scanner.nextLine();
    }

    /**
     * Clear.
     */
    public static void clear(){
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
