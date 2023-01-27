package com.test.developertest.service;

import com.test.developertest.models.Purchase;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

@Service
@XmlRootElement
public class PurchaseConverter {

    private final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    public PurchaseConverter() {
    }

    protected Purchase readInternal(File file) throws JAXBException, SAXException {
            JAXBContext jaxbContext = JAXBContext.newInstance(Purchase.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Schema schema = schemaFactory.newSchema(new StreamSource(PurchaseConverter.class.getResourceAsStream("/purchases.xsd")));
            jaxbUnmarshaller.setSchema(schema);
            return (Purchase) jaxbUnmarshaller.unmarshal(file);
    }

    protected void writeInternal(Purchase purchase) throws JAXBException {
            JAXBContext jaxbContext = JAXBContext.newInstance(Purchase.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(purchase, new File("output.xml"));
    }
}
