package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        SchemaFactory schemaFactory = SchemaFactory.newDefaultInstance();
        StreamSource xsd = new StreamSource(".idea/XSD.xsd");
        Schema schema = schemaFactory.newSchema(xsd);
        SAXParserFactory spf = SAXParserFactory.newDefaultInstance();
        spf.setSchema(schema);
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        List<Boek> boeken = new ArrayList<>();
        saxParser.parse(".idea/XML_BOEK.xml", new MyContentHandler(boeken));
        System.out.println("De boeken:");
        boeken.forEach(System.out::println);
    }
}

class MyContentHandler extends DefaultHandler {
    private StringBuilder tekstBuilder = new StringBuilder();
    private List<Boek> boeken = new ArrayList<>();
    private Boek boek;

    public MyContentHandler(List<Boek> boeken) {
        this.boeken = boeken;
    }

    @Override
    public void startDocument() throws SAXException {
        //System.out.println("Begin van het document");
    }

    @Override
    public void endDocument() throws SAXException {
        //System.out.println("Einde van het document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("boek")) {
            boek = new Boek();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tekstBuilder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("titel")) {
            boek.setTitel(tekstBuilder.toString());
        }
        if (localName.equals("auteur")) {
            boek.setAuteur(tekstBuilder.toString());
        }
        if (localName.equals("beoordeling")) {
            boek.setBeoordeling("beoordeling");
        } else if (localName.equals("boek")) {
            boeken.add(boek);
        }
        tekstBuilder.setLength(0); // terug leegmaken
    }

}

