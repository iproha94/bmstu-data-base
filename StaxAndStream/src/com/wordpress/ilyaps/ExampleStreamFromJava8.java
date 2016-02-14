package com.wordpress.ilyaps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyap on 18.11.2015.
 */
public class ExampleStreamFromJava8 {
    private List<Player> players = new ArrayList<>();

    public void autoFull() {
        players.add(new Player("Hulk", "Zenit", 29, Player.Position.FORWARD));
        players.add(new Player("Kerzhakov", "Zenit", 32, Player.Position.FORWARD));
        players.add(new Player("Lodygin", "Zenit", 25, Player.Position.GOALKEEPER));
        players.add(new Player("Dzyuba", "Zenit", 27, Player.Position.FORWARD));
        players.add(new Player("Lombaerts", "Zenit", 30, Player.Position.DEFENDER));
        players.add(new Player("Danny", "Zenit", 32, Player.Position.FORWARD));
        players.add(new Player("Kerzhakov", "Zenit", 27, Player.Position.GOALKEEPER));
        players.add(new Player("Musa", "CSKA", 23, Player.Position.FORWARD));
        players.add(new Player("Akinfeev", "CSKA", 29, Player.Position.GOALKEEPER));
    }

    public void printAll() {
        System.out.println("\nAll players");
        players
                .stream()
                .forEach(System.out::println);
    }

    public void printFilterName(String substr) {
        System.out.println("\nAll players, which starts with " + substr);
        players
                .stream()
                .filter((s) -> s.getName().startsWith(substr))
                .forEach(System.out::println);
    }

    public void printSorted() {
        System.out.println("\nSorted all players");
        players
                .stream()
                .sorted()
                .forEach(System.out::println);
    }

    public void printMapConvertTeam(String team) {
        System.out.println("\nConvert all players");
        players
                .stream()
                .map(player -> player.getCloneWithModifyTeam(team))
                .forEach(System.out::println);
    }

    public boolean isAnyInTeam(String team) {
        return players
                .stream()
                .anyMatch((s) -> s.getTeam().equals(team));
    }

    public long countPlayersInTeam(String team) {
        return players
                .stream()
                .filter((s) -> s.getTeam().equals(team))
                .count();
    }

    public static void main(String[] args) {
        ExampleStreamFromJava8 lto = new ExampleStreamFromJava8();
        lto.autoFull();
        lto.printAll();
        lto.printFilterName("Lo");
        lto.printSorted();
        lto.printMapConvertTeam("Krasnodar");

        System.out.println("\nIs team " + "CSKA" + "? " + lto.isAnyInTeam("CSKA"));
        System.out.println("In team " + "CSKA" + "= " + lto.countPlayersInTeam("CSKA"));

        System.out.println("\nIs team " + "Zenit" + "? " + lto.isAnyInTeam("Zenit"));
        System.out.println("In team " + "Zenit" + "= " + lto.countPlayersInTeam("Zenit"));

        System.out.println("\nIs team " + "Krasnodar" + "? " + lto.isAnyInTeam("Krasnodar"));
        System.out.println("In team " + "Krasnodar" + "= " + lto.countPlayersInTeam("Krasnodar"));
    }
}
