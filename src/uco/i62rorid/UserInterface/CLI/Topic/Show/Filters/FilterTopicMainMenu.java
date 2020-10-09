package uco.i62rorid.UserInterface.CLI.Topic.Show.Filters;

import uco.i62rorid.Utils.UserInput;

public class FilterTopicMainMenu {
    public static void init(){
        String menu = "\t1) Name\n"+
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            UserInput.clear();
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1:
                    FilterTopicByName.init();
                    break;
                default:
                    System.out.print("Not a valid option");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
