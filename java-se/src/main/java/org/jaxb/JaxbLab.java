package org.jaxb;

import org.apache.commons.io.FileUtils;
import org.jaxb.gpx.v0.Gpx;
import org.jaxb.gpx.v0.ObjectFactory;
import org.jaxb.gpx.v1.GpxType;
import org.jaxb.gpx.v1.RteType;
import org.jaxb.gpx.v1.WptType;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by remipassmoilesel on 01/02/17.
 */
public class JaxbLab {

    /*

    Step 1: download XSD files and XML sample:
        $ wget http://www.topografix.com/gpx/1/1/gpx.xsd
        $ wget https://raw.githubusercontent.com/AndyA/Geo--Gpx/master/doc/gpx-1.0.xsd
        $ wget http://www.openrunner.com/kml/exportImportGPX.php?rttype=0&id=3853828 -O track.gpx

    Step 2: create java classes:
        $ xjc *.xsd


    */

    public static void main(String[] args) throws JAXBException, IOException {

        //parseV1_0();

        createV1_1();

    }

    public static void createV1_1() throws JAXBException, IOException {

        // create a context
        JAXBContext jaxbContext = JAXBContext.newInstance("org.jaxb.gpx.v1");

        // create a marshaller
        Marshaller marshaller = jaxbContext.createMarshaller();

        org.jaxb.gpx.v1.ObjectFactory factory = new org.jaxb.gpx.v1.ObjectFactory();
        GpxType gpxtype = factory.createGpxType();

        for (int j = 0; j < 3; j++) {
            RteType rtetype = factory.createRteType();
            for (int i = 0; i < 30; i++) {

                WptType pte = factory.createWptType();
                pte.setLat(new BigDecimal(i));
                pte.setLon(new BigDecimal(i + 1));
                pte.setEle(new BigDecimal(i + 2));

                rtetype.getRtept().add(pte);
            }
            gpxtype.getRte().add(rtetype);
        }

        gpxtype.setCreator("Abc-map !");
        gpxtype.setVersion("1.1");
        JAXBElement<GpxType> gpx = factory.createGpx(gpxtype);

        Path dest = Paths.get("gpxsample1_1.gpx");
        Files.deleteIfExists(dest);

        try (OutputStream output = Files.newOutputStream(dest)) {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(gpx, output);
        }

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement element = (JAXBElement) unmarshaller.unmarshal(dest.toFile());
        System.out.println(element.getValue());
    }

    public static void parseV1_0() throws JAXBException {

        File gpxSource = new File("src/main/java/org/jaxb/track-1.0.gpx");

        // create a context
        JAXBContext jaxbContext = JAXBContext.newInstance("org.jaxb.gpx.v0");

        // create an object to unmarshall xml
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // parse document
        Gpx gpxDocument = (Gpx) unmarshaller.unmarshal(gpxSource);

        // display informations
        System.out.println("element");
        System.out.println(gpxDocument);
        System.out.println(gpxDocument.getBounds());
        System.out.println(gpxDocument.getAny());
        System.out.println(gpxDocument.getAuthor());
        System.out.println(gpxDocument.getEmail());
        System.out.println(gpxDocument.getRte());
        System.out.println(gpxDocument.getTrk());
        System.out.println(gpxDocument.getUrl());
        System.out.println(gpxDocument.getUrlname());

        // add informations to document

        // create a helper to create XML objects
        ObjectFactory factory = new ObjectFactory();

        // create a route and some points
        Gpx.Rte rte = factory.createGpxRte();

        for (int i = 0; i < 20; i++) {
            Gpx.Rte.Rtept rtept = factory.createGpxRteRtept();
            rtept.setLat(new BigDecimal(i));
            rtept.setLon(new BigDecimal(i));
            rte.getRtept().add(rtept);
        }

        gpxDocument.getRte().add(rte);

        // create a marshaller
        Marshaller marshaller = jaxbContext.createMarshaller();

        // marshall java classes to XML
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //marshaller.marshal(gpxDocument, System.out);

    }
}
