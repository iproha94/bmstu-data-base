package com.wordpress.ilyaps;

import com.wordpress.ilyaps.entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //создаем файл бд города
        ArrayList<String> listCity = readFile("txtdictionary\\eng_city.txt");
        ArrayList<String> listCityDB = generateCityDB(listCity);
        writeFile("txtrezult/city.csv", listCityDB, City.getHat());

        //создаем файл бд тренеров
        ArrayList<String> listCoachFirstname = readFile("txtdictionary\\coach_firstname.txt");
        ArrayList<String> listCoachSurname = readFile("txtdictionary\\coach_surname.txt");
        ArrayList<String> listCoachMiddlename = readFile("txtdictionary\\coach_middlename.txt");
        ArrayList<String> listCoachDB = generateCoachDB(listCoachFirstname, listCoachSurname, listCoachMiddlename);
        writeFile("txtrezult/coach.csv", listCoachDB, Coach.getHat());

        //создаем файл бд команд
        ArrayList<String> listTeam = readFile("txtdictionary\\team.txt");
        ArrayList<String> listTeamDB = generateTeamDB(listTeam, listCoachDB.size(), listCity);
        writeFile("txtrezult/team.csv", listTeamDB, Team.getHat());

        //создаем файл бд стадионов
        ArrayList<String> listStadium = readFile("txtdictionary\\stadium.txt");
        ArrayList<String> listStadiumDB = generateStadiumDB(listStadium, listCity);
        writeFile("txtrezult/stadium.csv", listStadiumDB, Stadium.getHat());

        //создаем файл бд судей
        ArrayList<String> listFirstname = readFile("txtdictionary\\eng_firstname.txt");
        ArrayList<String> listSurname = readFile("txtdictionary\\eng_surname.txt");
        ArrayList<String> listMiddlename = readFile("txtdictionary\\eng_middlename.txt");
        ArrayList<String> listRefereeDB = generateRefereeDB(listFirstname, listSurname, listMiddlename, listCity.size());
        writeFile("txtrezult/referee.csv", listRefereeDB, Referee.getHat());

        ArrayList<String> listMatchDB = generateMatchDB(listTeamDB.size(), listStadiumDB.size(), listRefereeDB.size());
        writeFile("txtrezult/match.csv", listMatchDB, Match.getHat());

    }

    public static void writeFile(String fileName, ArrayList<String> text, String hat) {
        //Определяем файл
        //File file = new File(fileName);
        //PrintWriter out = null;

        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(fileName), "Cp1251"));

            //проверяем, что если файл не существует то создаем его
//            if(!file.exists()){
//                file.createNewFile();
//            }

            //PrintWriter обеспечит возможности записи в файл
            //out = new PrintWriter(file);
            out.write(hat + "\n");
            for (String str: text) {
                out.write(str + "\n");
            }

        } catch(IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<String> readFile(String filename) {
        Scanner in = null;
        ArrayList<String> list = new ArrayList<>();

        try {
            in = new Scanner(new File(filename));
            while (in.hasNext()) {
                list.add(in.nextLine());
            }
            System.out.println("In the file " + filename  + " read " + list.size() + " elements");

        } catch (FileNotFoundException e) {
            System.out.println("File " + filename  + " not found");
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return list;
    }

    public static ArrayList<String> generateCityDB(ArrayList<String> listCity) {
        ArrayList<String> listCityDB = new ArrayList<>();

        for (int i = 0; i < listCity.size(); ++i) {
            City city = new City(i, listCity.get(i));
            listCityDB.add(city.toString());
        }

        return listCityDB;
    }

    private static ArrayList<String> generateCoachDB(ArrayList<String> listCoachFirstname, ArrayList<String> listCoachSurname, ArrayList<String> listCoachMiddlename) {
        ArrayList<String> listCoachDB = new ArrayList<>();

        int count = 1000;
        Random rand = new Random();

        for (int i = 0; i < count; ++i) {
            String first = listCoachFirstname.get(rand.nextInt(listCoachFirstname.size()));
            String sur = listCoachSurname.get(rand.nextInt(listCoachSurname.size()));
            String middle = listCoachMiddlename.get(rand.nextInt(listCoachMiddlename.size()));

            Coach coach = new Coach(i, first, sur, middle);
            listCoachDB.add(coach.toString());
        }

        return listCoachDB;
    }

    private static String randDate() {
        String date = "";
        Random rand = new Random();

        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int mon = rand.nextInt(12) + 1;

        int day = rand.nextInt(days[mon]) + 1;
        if (day < 10) {
            date = date + "0" + day;
        } else {
            date = date + day;
        }

        date += "-";
        if (mon < 10) {
            date = date + "0" + mon;
        } else {
            date = date + mon;
        }

        date += "-";
        date += (rand.nextInt(15) + 2000);

        return date;
    }

    private static ArrayList<String> generateMatchDB(int sizeTeam, int sizeStadium, int sizeReferee) {
        ArrayList<String> listMatchDB = new ArrayList<>();

        int count = 100000;
        Random rand = new Random();

        for (int i = 0; i < count; ++i) {
            int team1Id = rand.nextInt(sizeTeam);
            int team2Id = rand.nextInt(sizeTeam);
            while (team1Id == team2Id) {
                team2Id = rand.nextInt(sizeTeam);
            }

            Match match = new Match(i, team1Id, team2Id, rand.nextInt(sizeStadium), rand.nextInt(sizeReferee), randDate());
            listMatchDB.add(match.toString());
        }

        return listMatchDB;
    }

    private static ArrayList<String> generateRefereeDB(ArrayList<String> listFirstname, ArrayList<String> listSurname, ArrayList<String> listMiddlename, int sizeCity) {
        ArrayList<String> listRefereeDB = new ArrayList<>();

        int count = 1000;
        Random rand = new Random();

        for (int i = 0; i < count; ++i) {
            String first = listFirstname.get(rand.nextInt(listFirstname.size()));
            String sur = listSurname.get(rand.nextInt(listSurname.size()));
            String middle = listMiddlename.get(rand.nextInt(listMiddlename.size()));

            Referee referee = new Referee(i, first, sur, middle, rand.nextInt(sizeCity));
            listRefereeDB.add(referee.toString());
        }

        return listRefereeDB;
    }

    private static ArrayList<String> generateStadiumDB(ArrayList<String> listStadium, ArrayList<String> listCity) {
        ArrayList<String> listStadiumDB = new ArrayList<>();

        int count = 1000;
        Random rand = new Random();

        for (int i = 0; i < count; ++i) {
            int cityId = rand.nextInt(listCity.size());
            String name =  listCity.get(cityId) + "-" + listStadium.get(rand.nextInt(listStadium.size())) ;

            Stadium stadium = new Stadium(i, name,rand.nextInt(90000) + 5000, cityId);
            listStadiumDB.add(stadium.toString());
        }

        return listStadiumDB;
    }

    private static ArrayList<String> generateTeamDB(ArrayList<String> listTeam, int sizeCoach, ArrayList<String> listCity) {
        ArrayList<String> listTeamDB = new ArrayList<>();

        int count = 1000;
        Random rand = new Random();

        for (int i = 0; i < count; ++i) {
            int cityId = rand.nextInt(listCity.size());
            String name = listTeam.get(rand.nextInt(listTeam.size())) + "-" + listCity.get(cityId);

            Team team = new Team(i, name, rand.nextInt(sizeCoach), cityId);
            listTeamDB.add(team.toString());
        }

        return listTeamDB;
    }


}
