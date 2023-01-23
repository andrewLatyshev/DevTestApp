package com.test.developertest.service;

import com.test.developertest.models.Purchase;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

@Service
@XmlRootElement
public class PurchaseConverter extends AbstractHttpMessageConverter<Purchase> {

    private final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    public PurchaseConverter() {
        super(MediaType.APPLICATION_XML);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.equals(Purchase.class);
    }

    @Override
    protected Purchase readInternal(Class<? extends Purchase> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Purchase.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Schema schema = schemaFactory.newSchema(new StreamSource(PurchaseConverter.class.getResourceAsStream("/purchases.xsd")));
            Validator validator = schema.newValidator();
            jaxbUnmarshaller.setSchema(schema);

            return (Purchase) jaxbUnmarshaller.unmarshal(inputMessage.getBody());
        } catch (Exception e) {
            throw new HttpMessageNotReadableException(e.getMessage(), e, inputMessage);
        }

    }

    @Override
    protected void writeInternal(Purchase purchase, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Purchase.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(purchase, outputMessage.getBody());
        } catch (Exception e) {
            throw new HttpMessageNotWritableException(e.getMessage(), e);
        }
    }
}
