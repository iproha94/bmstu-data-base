package com.wordpress.ilyaps;

import java.util.List;

/**
 * Created by ilyap on 20.11.2015.
 */
public class BDLab8p3 {
    public static void main(String[] args) {
        DBService dbService = new DBService();

        System.out.println("Status: " +  dbService.getLocalStatus());

//        dbService.save(new CityDataSet("Lol1"));
//        dbService.save(new CityDataSet("Lol2"));
//
//        CityDataSet dataSet = dbService.read(1);
//        System.out.println(dataSet);
//
//        dataSet = dbService.readByName("Moscow");
//        System.out.println(dataSet);
//
//        List<CityDataSet> dataSets = dbService.readAll();
//        for (CityDataSet cityDataSet : dataSets) {
//            System.out.println(cityDataSet);
//        }
        //dbService.update("Lol1", "Karaganda");
        //dbService.delete("tully23");

        List<RefereeDataSet> list = dbService.readCityByRefereeId("Karaganda");
        System.out.print(list);
//        dbService.callCityProc(3, "Korolev");
        dbService.shutdown();
    }
}
