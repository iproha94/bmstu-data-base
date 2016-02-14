package com.wordpress.ilyaps;

/**
 * Created by ilyap on 26.11.2015.
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;

public class TerminalMenu {
    private Scanner sc;
    private PrintStream ps;
    private int choice;
    private List<Method> listMethods;
    private Class clazz;
    private Object[] args;
    private List<String> listStrings;

    public TerminalMenu(List<String> listStrings, List<Method> listMethods, Class c, Object[] args) {
        this.sc  = new Scanner(System.in);
        this.ps = System.out;
        this.choice = -1;
        this.listMethods = listMethods;
        this.clazz = c;
        this.args = args;
        this.listStrings = listStrings;
    }

    public boolean isFinish() {
        return choice == 0;
    }

    public int scanChoice() {
        ps.print("->> ");
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            choice = -1;
        }
        return choice;
    }

    public void run() {
        printMenu();
        scanChoice();

        while (!isFinish()) {
            try {
                if (choice <= listMethods.size()) {
                    listMethods.get(choice - 1).invoke(clazz, args);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            printMenu();
            scanChoice();
        }
        ps.print("The end.");
    }


    public void printMenu() {
        ps.println("\n\n\t\t===Menu:===");
        ps.println("\t\t0 - Exit");
        ps.print(listStrings);
    }

}
