package stax;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * 
 * a) Gerar uma tabela HTML com o nome do produto, a linha, a empresa produtora, a
 * quantidade em estoque, e o preço, ordenado pelo nome do produto.
 * 
 */

public class StAX_1 {
    
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlif = XMLInputFactory.newFactory();
        Reader reader = new FileReader("web/products.xml");
        XMLStreamReader xmlsr = xmlif.createXMLStreamReader(reader);
        
        boolean bName = false;
        boolean bLine = false;
        String name = null;
        String line = null;
        
        while (xmlsr.hasNext()) {
            switch (xmlsr.next()){
                case XMLStreamReader.START_ELEMENT:
                    String nome = xmlsr.getLocalName();
//                    System.out.println(nome);
                    if (nome.equals("name")){
                        bName = true;
                    }
                    if (nome.equals("line")){
                        bLine = true;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    nome = xmlsr.getLocalName();
//                    System.out.println(nome);
                    if (nome.equals("product")){
                        System.out.println(name);  
                        System.out.println(line);
                        name = null;
                    }                                        
                    break;
                case XMLStreamReader.CHARACTERS:
                    if (bName) {
                        name = xmlsr.getText();
//                        System.out.println(name);
                    }
                    if (bLine){
                        line = xmlsr.getText();
                    }
                    bLine = false;
                    bName = false;
                    break;                
            }
        }
        xmlsr.close();
    }
}


/*
 * A montagem da saída das questões implementadas com SAX ou StAX deve utilizar como
 * base a classe XMLStreamWriter .
 * 
 */



/*

run:
1969 Harley Davidson Ultimate Chopper
Motorcycles
1952 Alpine Renault 1300
Classic Cars
1996 Moto Guzzi 1100i
Motorcycles
2003 Harley-Davidson Eagle Drag Bike
Motorcycles
1972 Alfa Romeo GTA
Classic Cars
1962 LanciaA Delta 16V
Classic Cars
1968 Ford Mustang
Classic Cars
2001 Ferrari Enzo
Classic Cars
1958 Setra Bus
Trucks and Buses
2002 Suzuki XREO
Motorcycles
1969 Corvair Monza
Classic Cars
1968 Dodge Charger
Classic Cars
1969 Ford Falcon
Classic Cars
1970 Plymouth Hemi Cuda
Classic Cars
1957 Chevy Pickup
Trucks and Buses
1969 Dodge Charger
Classic Cars
1940 Ford Pickup Truck
Trucks and Buses
1993 Mazda RX-7
Classic Cars
1937 Lincoln Berline
Vintage Cars
1936 Mercedes-Benz 500K Special Roadster
Vintage Cars
1965 Aston Martin DB5
Classic Cars
1980s Black Hawk Helicopter
Planes
1917 Grand Touring Sedan
Vintage Cars
1948 Porsche 356-A Roadster
Classic Cars
1995 Honda Civic
Classic Cars
1998 Chrysler Plymouth Prowler
Classic Cars
1911 Ford Town Car
Vintage Cars
1964 Mercedes Tour Bus
Trucks and Buses
1932 Model A Ford J-Coupe
Vintage Cars
1926 Ford Fire Engine
Trucks and Buses
P-51-D Mustang
Planes
1936 Harley Davidson El Knucklehead
Motorcycles
1928 Mercedes-Benz SSK
Vintage Cars
1999 Indy 500 Monte Carlo SS
Classic Cars
1913 Ford Model T Speedster
Vintage Cars
1934 Ford V8 Coupe
Vintage Cars
1999 Yamaha Speed Boat
Ships
18th Century Vintage Horse Carriage
Vintage Cars
1903 Ford Model A
Vintage Cars
1992 Ferrari 360 Spider red
Classic Cars
1985 Toyota Supra
Classic Cars
Collectable Wooden Train
Trains
1969 Dodge Super Bee
Classic Cars
1917 Maxwell Touring Car
Vintage Cars
1976 Ford Gran Torino
Classic Cars
1948 Porsche Type 356 Roadster
Classic Cars
1957 Vespa GS150
Motorcycles
1941 Chevrolet Special Deluxe Cabriolet
Vintage Cars
1970 Triumph Spitfire
Classic Cars
1932 Alfa Romeo 8C2300 Spider Sport
Vintage Cars
1904 Buick Runabout
Vintage Cars
1940s Ford truck
Trucks and Buses
1939 Cadillac Limousine
Vintage Cars
1957 Corvette Convertible
Classic Cars
1957 Ford Thunderbird
Classic Cars
1970 Chevy Chevelle SS 454
Classic Cars
1970 Dodge Coronet
Classic Cars
1997 BMW R 1100 S
Motorcycles
1966 Shelby Cobra 427 S/C
Classic Cars
1928 British Royal Navy Airplane
Planes
1939 Chevrolet Deluxe Coupe
Vintage Cars
1960 BSA Gold Star DBD34
Motorcycles
18th century schooner
Ships
1938 Cadillac V-16 Presidential Limousine
Vintage Cars
1962 Volkswagen Microbus
Trucks and Buses
1982 Ducati 900 Monster
Motorcycles
1949 Jaguar XK 120
Classic Cars
1958 Chevy Corvette Limited Edition
Classic Cars
1900s Vintage Bi-Plane
Planes
1952 Citroen-15CV
Classic Cars
1982 Lamborghini Diablo
Classic Cars
1912 Ford Model T Delivery Wagon
Vintage Cars
1969 Chevrolet Camaro Z28
Classic Cars
1971 Alpine Renault 1600s
Classic Cars
1937 Horch 930V Limousine
Vintage Cars
2002 Chevy Corvette
Classic Cars
1940 Ford Delivery Sedan
Vintage Cars
1956 Porsche 356A Coupe
Classic Cars
Corsair F4U (Bird Cage)
Planes
1936 Mercedes Benz 500k Roadster
Vintage Cars
1992 Porsche Cayenne Turbo Silver
Classic Cars
1936 Chrysler Airflow
Vintage Cars
1900s Vintage Tri-Plane
Planes
1961 Chevrolet Impala
Classic Cars
1980’s GM Manhattan Express
Trucks and Buses
1997 BMW F650 ST
Motorcycles
1982 Ducati 996 R
Motorcycles
1954 Greyhound Scenicruiser
Trucks and Buses
1950\'s Chicago Surface Lines Streetcar
Trains
1996 Peterbilt 379 Stake Bed with Outrigger
Trucks and Buses
1928 Ford Phaeton Deluxe
Vintage Cars
1974 Ducati 350 Mk3 Desmo
Motorcycles
1930 Buick Marquette Phaeton
Vintage Cars
Diamond T620 Semi-Skirted Tanker
Trucks and Buses
1962 City of Detroit Streetcar
Trains
2002 Yamaha YZR M1
Motorcycles
The Schooner Bluenose
Ships
American Airlines: B767-300
Planes
The Mayflower
Ships
HMS Bounty
Ships
America West Airlines B757-200
Planes
The USS Constitution Ship
Ships
1982 Camaro Z28
Classic Cars
ATA: B757-300
Planes
F/A 18 Hornet 1/72
Planes
The Titanic
Ships
The Queen Mary
Ships
American Airlines: MD-11S
Planes
Boeing X-32A JSF
Planes
Pont Yacht
Ships
BUILD SUCCESSFUL (total time: 0 seconds)


*/