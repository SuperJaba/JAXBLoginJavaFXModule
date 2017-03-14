package pl.losK.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by m.losK on 2017-03-13.
 */
public class XMLFactory<T> {

    private Class<T> type;

    public XMLFactory(Class<T> type) {
        this.type = type;
    }

    public XMLFactory() {
    }

    public T xmlToObject(String xml) {
        T object = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            XMLInputFactory xif = XMLInputFactory.newInstance();
            StringReader stringReader = new StringReader(xml);
            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            object = (T) unmarshaller.unmarshal(xsr);
            return object;

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return object;
    }

    public String objectToXml(T object) {
        String result = null;
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(type);
            StringWriter stringWriter = new StringWriter();
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(object, stringWriter);
            result = stringWriter.toString();
            return result;

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;


    }
}
