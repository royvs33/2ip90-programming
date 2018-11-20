package ChallengeProgram.ZooKeeper;

/**
 * Client program for CP4 assignment.
 * You can change it but
 * only sequences of Zoo interface calls are allowed.
 */
public class ZooKeeper {
    public static void main(String[] args) {
        Zoo myZoo = new MyZoo();
        System.out.println(myZoo.accomodateAnimal("Leopard", "Bagheera", "Cage", 21));
        System.out.println(myZoo.accomodateAnimal("Leopard", "Bagheera", "Cage", 11));
        System.out.println(myZoo.accomodateAnimal("Lion", "Tom", "Enclosure",2));
        System.out.println(myZoo.accomodateAnimal("Zebra", "Tom1", "Enclosure",2));
        System.out.println(myZoo.accomodateAnimal("Lion", "Tom2", "Cage",2));
        System.out.println(myZoo.accomodateAnimal("Lion", "Tom3", "Cage",2));
        System.out.println(myZoo.accomodateAnimal("Bear", "Tom3", "Cage",2));
        System.out.println(myZoo.accomodateAnimal("Lion", "Tom4", "Cage",2));
        System.out.println(myZoo.accomodateAnimal("Tiger", "King", "Cage",6));
        System.out.println(myZoo.accomodateAnimal("Tiger", "Kid", "Cage",6));
        System.out.println(myZoo.accomodateAnimal("Zebra", "Sid", "Cage",1));
        System.out.println(myZoo.accomodateAnimal("Leopard", "Bagheera", "Enclosure", 1));
        System.out.println(myZoo.accomodateAnimal("Zebra", "Sid", "Enclosure",1));
        System.out.println(myZoo.accomodateAnimal("Zebra", "Vera", "Enclosure",1));
        System.out.println(myZoo.accomodateAnimal("Leopard", "Bagheera", "Enclosure", 1));
        System.out.println(myZoo.accomodateAnimal("Tiger", "Karl", "Enclosure",1));
        System.out.println(myZoo.accomodateAnimal("Tiger", "Karl", "Cage",5));
        System.out.println(myZoo.accomodateAnimal("Wildebeest", "Vega", "Enclosure",1));
        System.out.println(myZoo.accomodateAnimal("Giraffe", "Donald", "Enclosure", 4));
        System.out.println(myZoo.accomodateAnimal("Lion", "King", "Enclosure",0));
        System.out.println(myZoo.accomodateAnimal("Lion", "Kid", "Enclosure",0));
        System.out.println(myZoo.feedAnimals("Enclosure", 0, 100, "Carrot"));
        System.out.println(myZoo.feedAnimals("Enclosure", 0, 200, "Meat"));
        System.out.println(myZoo.buyFood("Meat", -200));
        System.out.println(myZoo.feedAnimals("Enclosure",0, 100, "Meat"));
        System.out.println(myZoo.relocateAnimal("Bagheera", "Enclosure", 1));
        System.out.println(myZoo.relocateAnimal("Bagheera", "Enclosure", 2));
        System.out.println(myZoo.accomodateAnimal("Zebra", "Sid", "Enclosure",1));
        System.out.println(myZoo.removeAnimal("Bagheera"));
        System.out.println(myZoo.relocateAnimal("Bagheera", "Enclosure", 3));
        System.out.println(myZoo.accomodateAnimal("Tiger", "Tom", "Cage",1));
        System.out.println(myZoo.buyFood("Meat", 20));
        System.out.println(myZoo.buyFood("Carrot", 20));
        myZoo.listOfTenants();

    }
}