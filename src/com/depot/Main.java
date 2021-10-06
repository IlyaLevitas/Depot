package com.depot;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) {
        try {
            Train train = new Train();

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("Train.xml");

            document.getDocumentElement().normalize();

            NodeList cars = document.getElementsByTagName("Car");

            for(int i = 0; i < cars.getLength(); i++) {
                Element e = (Element) cars.item(i);
                if (e.getAttribute("type").equals("passenger")) {
                    PassengerCar car = new PassengerCar(e.getElementsByTagName("Type").item(0).getTextContent(),
                                Integer.parseInt(e.getElementsByTagName("seatsAmount").item(0).getTextContent()));
                    train.addCar(car);
                }
                else if(e.getAttribute("type").equals("freight")) {
                    FreightCar car = new FreightCar(e.getElementsByTagName("Type").item(0).getTextContent(),
                            Integer.parseInt(e.getElementsByTagName("liftingCapacity").item(0).getTextContent()));
                    train.addCar(car);
                }
            }

            document = documentBuilder.newDocument();

            Element root = document.createElement("Train");
            document.appendChild(root);

            Element info = document.createElement("Info");
            root.appendChild(info);

            Element seats_total = document.createElement("Seats");
            seats_total.appendChild(document.createTextNode(train.getSeats_total().toString()));
            info.appendChild(seats_total);

            Element total_lifting_capacity = document.createElement("liftingCapacity");
            total_lifting_capacity.appendChild(document.createTextNode(train.getTotal_lifting_capacity().toString()));
            info.appendChild(total_lifting_capacity);

            Element cars_count = document.createElement("CarsCount");
            cars_count.appendChild(document.createTextNode(Car.getCars_count().toString()));
            info.appendChild(cars_count);

            Element freight_cars_count = document.createElement("freightCarsCount");
            freight_cars_count.appendChild(document.createTextNode(FreightCar.getFreight_cars_count().toString()));
            info.appendChild(freight_cars_count);

            Element passenger_cars_count = document.createElement("passengerCarsCount");
            passenger_cars_count.appendChild(document.createTextNode(PassengerCar.getPassenger_cars_count().toString()));
            info.appendChild(passenger_cars_count);

            Element carsTypes = document.createElement("CarsTypes");

            root.appendChild(carsTypes);

            for (Car car: train.cars) {
                Element carType = document.createElement("carType");
                carType.appendChild(document.createTextNode(car.getCarType()));
                carsTypes.appendChild(carType);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("Output.xml"));

            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } catch (TransformerException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
