package com.wordpress.ilyaps;

import com.wordpress.ilyaps.dataset.Referee;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ilyap on 21.11.2015.
 */
public class MainBmstuDBLab9 {
    private static final String PROPERTIES_FILE = "cfg/db.properties";
    private static Scanner sc = new Scanner(System.in);;


    public static void func1(FootballDBService footballDBService) {
        System.out.print(footballDBService.printInfoOfConnection());
    }

    public static void func2(FootballDBService footballDBService) {
        System.out.print("Input city id: ");
        int id = new Integer(sc.nextLine());
        System.out.print("Input city new name: ");
        String cityName = sc.nextLine();

        footballDBService.updateCityName(id, cityName);
    }

    public static void func3(FootballDBService footballDBService) {
        System.out.print("Input referee id: ");
        int id = new Integer(sc.nextLine());

        System.out.print(footballDBService.readReferee(id));
    }

    public static void func4(FootballDBService footballDBService) {
        System.out.print("Input city id: ");
        int id = new Integer(sc.nextLine());

        System.out.print(footballDBService.readRefereeByCity(id));
    }

    public static void func5(FootballDBService footballDBService) {
        List<Referee> listReferee =  new ArrayList<>();
        int maxId = footballDBService.getMaxIdReferee();

        System.out.print("Input the number of referees: ");
        int num = new Integer(sc.nextLine());

        for (int i = 0; i < num; ++i) {
            System.out.print("Input firstname: ");
            String firstname = sc.nextLine();
            System.out.print("Input lastname: ");
            String lastname = sc.nextLine();
            System.out.print("Input middlename: ");
            String middlename = sc.nextLine();
            System.out.print("Input city id: ");
            int cityid = new Integer(sc.nextLine());
            listReferee.add(new Referee(maxId + i + 1, firstname, lastname, middlename, cityid));
        }

        System.out.println("insert = " + footballDBService.insertReferee(listReferee));
    }

    public static void main(String[] args) throws NoSuchMethodException {
        FootballDBService footballDBService = new FootballDBService(PROPERTIES_FILE);
        footballDBService.openConnection();


        List<String> listStrings = new ArrayList<>();
        listStrings.add("\t\t1 - Connection Info\n");
        listStrings.add("\t\t2 - updateCityName (call stored procedure)\n");
        listStrings.add("\t\t3 - readReferee (single statement)\n");
        listStrings.add("\t\t4 - readRefereeByCity (set string)\n");
        listStrings.add("\t\t5 - insertReferee (prepare statement)\n");

        List<Method> listMethods = new ArrayList<>();
        Class c = MainBmstuDBLab9.class;
        Class[] paramTypes = new Class[] { FootballDBService.class };
        Object[] args1 = new Object[] { footballDBService };
        listMethods.add(c.getMethod("func1", paramTypes));
        listMethods.add(c.getMethod("func2", paramTypes));
        listMethods.add(c.getMethod("func3", paramTypes));
        listMethods.add(c.getMethod("func4", paramTypes));
        listMethods.add(c.getMethod("func5", paramTypes));

        TerminalMenu menu = new TerminalMenu(listStrings, listMethods, c, args1);
        menu.run();

        footballDBService.closeConnection();
    }

}
