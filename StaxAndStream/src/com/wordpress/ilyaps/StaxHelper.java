package com.wordpress.ilyaps;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ilyap on 20.11.2015.
 */
public abstract class StaxHelper {
    protected XMLEventReader reader;
    protected FileReader fr;
    protected String fileReaderName;


    protected XMLEventWriter writer;
    protected FileWriter fw;
    protected String fileWriterName;

    public StaxHelper() {
        fileReaderName = null;
        fr = null;
        fileWriterName = null;
        fw = null;
    }

    public void setFileReaderName(String fileReaderName) {
        this.fileReaderName = fileReaderName;
    }

    public String getFileReaderName() {
        return fileReaderName;
    }

    public String getFileWriterName() {
        return fileWriterName;
    }

    public void setFileWriterName(String fileWriterName) {
        this.fileWriterName = fileWriterName;
    }

    protected boolean loadReaderFile() {
        XMLInputFactory factory = XMLInputFactory.newInstance();

        try {
            fr = new FileReader(fileReaderName);
            reader = factory.createXMLEventReader(fr);
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected boolean loadWriterFile() {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {
            fw = new FileWriter(fileWriterName);
            writer = factory.createXMLEventWriter(fw);
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected void unloadReaderFile() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        try {
            if (fr != null) {
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void unloadWriterFile() {
        try {
            if (writer != null) {
                writer.flush();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        try {
            if (writer != null) {
                writer.close();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        try {
            if (fw != null) {
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    protected void printXmlFile() {
        if (!loadReaderFile()) {
            return;
        }

        XMLEvent xmlEvent = null;
        while (reader.hasNext()) {
            try {
                xmlEvent = reader.nextEvent();
            } catch (XMLStreamException e) {
                e.printStackTrace();
                return;
            }
            switch (xmlEvent.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT:
                    processStartDocument();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    processStartElement(xmlEvent.asStartElement());
                    break;
                case XMLStreamConstants.CHARACTERS:
                    processCharacters(xmlEvent.asCharacters());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    processEndElement(xmlEvent.asEndElement());
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    processEndDocument();
                    break;
            }
        }

        unloadReaderFile();
    }

    protected void createCloneFile() {
        if (!loadReaderFile()) {
            return;
        }
        if (!loadWriterFile()) {
            return;
        }

        XMLEvent xmlEvent = null;
        while (reader.hasNext()) {
            try {
                xmlEvent = reader.nextEvent();
                writer.add(xmlEvent);

            } catch (XMLStreamException e) {
                e.printStackTrace();
                return;
            }
        }
        unloadWriterFile();
        unloadReaderFile();
    }

    protected abstract void processEndDocument();

    protected abstract void processEndElement(EndElement endElement);

    protected abstract void processCharacters(Characters characters);

    protected abstract void processStartElement(StartElement startElement);

    protected abstract void processStartDocument();

}
