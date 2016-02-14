package com.wordpress.ilyaps;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by ilyap on 19.11.2015.
 */
public class ExampleStaxOfPlayers extends StaxHelper {
    private static final QName attrNumName = new QName("Num");
    private static final QName attrPositionName = new QName("Position");
    private static final QName tagTeamName = new QName("Team");
    private static final QName tagPlayerName = new QName("Player");
    private static final QName tagNameName = new QName("Name");
    private static final QName tagAgeName = new QName("Age");

    @Override
    protected void processEndDocument() {
        System.out.println("End document");
    }
    @Override
    protected void processStartDocument() {
        System.out.println("Start document");
    }

    @Override
    protected void processEndElement(EndElement endElement) {
        System.out.println("/" + endElement.getName());
    }

    @Override
    protected void processCharacters(Characters characters) {
        if (!characters.isWhiteSpace()) {
            System.out.println("\t" + characters.getData());
        }
    }

    @Override
    protected void processStartElement(StartElement startElement) {
        System.out.println(startElement.getName());

        if (startElement.getName().equals(tagPlayerName)) {
            Attribute attrNum = startElement.getAttributeByName(attrNumName);
            Attribute attrPosition = startElement.getAttributeByName(attrPositionName);

            System.out.println("\t" + attrNum.getName() + " = " + attrNum.getValue());
            System.out.println("\t" + attrPosition.getName() + " = " + attrPosition.getValue());
        }
    }

    public void createNewFileOfPlayers(List<Player> players) {
        if (!loadWriterFile()) {
            return;
        }

        XMLEventFactory ef = XMLEventFactory.newInstance();
        try {
            writer.add(ef.createStartDocument("UTF-8", "1.0"));
            writer.add(ef.createStartElement("", "", tagTeamName.toString()));
            for (Player player : players) {
                Attribute attributeNum = ef.createAttribute(attrNumName, Integer.toString((new Random()).nextInt(100)));
                Attribute attributePosition = ef.createAttribute(attrPositionName, player.getPosition());
                List attributeList = Arrays.asList(attributeNum, attributePosition);
                List nsList = Arrays.asList();
                writer.add(ef.createStartElement("", "", tagPlayerName.toString(), attributeList.iterator(), nsList.iterator()));

                writer.add(ef.createStartElement("", "", tagNameName.toString()));
                writer.add(ef.createCharacters(player.getName()));
                writer.add(ef.createEndElement("", "", tagNameName.toString()));

                writer.add(ef.createStartElement("", "", tagAgeName.toString()));
                writer.add(ef.createCharacters(Integer.toString(player.getAge())));
                writer.add(ef.createEndElement("", "", tagAgeName.toString()));

                writer.add(ef.createEndElement("", "", tagPlayerName.toString()));
            }
            writer.add(ef.createEndElement("", "", tagTeamName.toString()));
            writer.add(ef.createEndDocument());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        unloadWriterFile();
    }

    public static void main(String[] args) {
        ExampleStaxOfPlayers ltx = new ExampleStaxOfPlayers();
        ltx.setFileReaderName("Team.xml");
        ltx.setFileWriterName("newTeam.xml");
        ltx.printXmlFile();
//        ltx.createCloneFile();

//        List<Player> players = new ArrayList<>();
//        players.add(new Player("Hulk", "Zenit", 29, Player.Position.FORWARD));
//        players.add(new Player("Kerzhakov", "Zenit", 32, Player.Position.FORWARD));
//        players.add(new Player("Lodygin", "Zenit", 25, Player.Position.GOALKEEPER));
//        players.add(new Player("Dzyuba", "Zenit", 27, Player.Position.FORWARD));
//        players.add(new Player("Lombaerts", "Zenit", 30, Player.Position.DEFENDER));
//        players.add(new Player("Danny", "Zenit", 32, Player.Position.FORWARD));
//        players.add(new Player("Kerzhakov", "Zenit", 27, Player.Position.GOALKEEPER));
//        players.add(new Player("Musa", "CSKA", 23, Player.Position.FORWARD));
//        players.add(new Player("Akinfeev", "CSKA", 29, Player.Position.GOALKEEPER));
//        ltx.createNewFileOfPlayers(players);
    }
}
