/**
 * Name: Roy van Schaijk
 * Student ID: 1314599
 */

import java.util.*;

/**
 * ApeIntelligence should prevent the world of becoming really
 * f*cked up, if its not too late already (hint: it probably is)
 */
public class ApeIntelligence {
    //Scanner that reads the input
    Scanner sc = new Scanner(System.in);

    //String holding the text that will be printed in the end
    String decodedOrder = "";

    String[] commandTranslations = {
        "Attack with",
        "Search",
        "Retreat to",
    };

    String[] resourceTypeTranslations = {
        "",
        " orangutans",
        " chimps",
        " gorillas",
        " mounted chimps",
        " mounted orangutans",
    };

    String[] searchPlaceTranslations = {
        " hills",
        " marshes",
        " caves",
        " woods"
    };

    String[] directionTranslations = {
        "", //0 not used
        " North",
        " East",
        " West",
        " South",
        " South-East",
        " South-West",
        " North-East",
        " North-West",
    };

    String[] searchedObjectsTranslations = {
        " and look for humans",
        " and look for human males",
        " and look for human females",
        " and look for human children",
        " and look for mutated humans",
    };

    String[] retreatPlacesTranslations = {
        " Ape City",
        " Forbidden Zone",
        " Rocky Mountains",
    };

    String[] typeTranslations = {
        " wooden",
        " steel",
        " stone",
        " cotton",
    };

    String[] itemTranslations = {
        " cage",
        " net",
        " helmet",
        " shield",
    };


    /**
     * Main function to choose between parts
     */
    void run() {
        //Ask for which part the translation/decryption is needed
        String part = sc.next() + " " + sc.next();

        switch (part) {
            case "Part 1":
                partOne();
                break;
            case "Part 2":
                partTwo();
                break;
            case "Part 3":
                partThree();
                break;
        }

        //Add a period to finish an English sentence
        decodedOrder += ".";

        //Print the decrypted super secret monkey commands
        System.out.println(decodedOrder);
    }

    /**
     * Execute program according to Part 1
     * Battle commands
     */
    void partOne() {
        int command = translateInput(commandTranslations);
        //switch on command because of different approach
        switch (command) {
            case 0: //Attack, so amount + type
                int amountAttack = sc.nextInt();
                if (amountAttack == 0) {
                    decodedOrder += " all your";
                } else {
                    decodedOrder += " " + amountAttack;
                }

                translateInput(resourceTypeTranslations);
                break;
            case 1: //Search, so place/direction + (optional) searched objects
                int startingNumber = sc.nextInt();
                if (startingNumber == 0) { //place
                    //ask for amount
                    int amountPlaces = sc.nextInt();
                    if (amountPlaces == 0) {
                        decodedOrder += " all";
                    } else {
                        decodedOrder += " " + amountPlaces;
                    }
                    //get type of place
                    translateInput(searchPlaceTranslations);
                } else { //direction
                    //get direction
                    translateInput(directionTranslations, startingNumber);
                }
                //get optional searched objects
                translateInput(searchedObjectsTranslations);
                break;
            case 2:
                //get place to retreat to
                translateInput(retreatPlacesTranslations);

                int optionalAmountResource = sc.nextInt();
                if (optionalAmountResource != -1) {
                    decodedOrder += " and move";
                    if (optionalAmountResource == 0) {
                        decodedOrder += " all your";
                    } else {
                        decodedOrder += " " + optionalAmountResource;
                    }
                    translateInput(resourceTypeTranslations);
                    decodedOrder += " to";
                    translateInput(directionTranslations);
                }
                break;
        }
    }

    /**
     * Execute program according to Part 2
     */
    void partTwo() {
        decodedOrder += "Pack a";
        getContainersSpecifications(sc.nextInt());
        getOptionalDirections();
    }

    /**
     * Get the container specifications recursively
     *
     * @param inputValue the current int read from terminal
     */
    void getContainersSpecifications(int inputValue) {
        translateInput(typeTranslations, inputValue);
        translateInput(itemTranslations);
        int continueInt = sc.nextInt();
        if (continueInt == 5) { //add two more containers
            decodedOrder += " containing a";
            getContainersSpecifications(sc.nextInt());
            decodedOrder += " and a";
            getContainersSpecifications(sc.nextInt());
        } else if (continueInt != 4) { //add only one container
            decodedOrder += " containing a";
            getContainersSpecifications(continueInt);
        }
        //stop if continueInt == 4
    }

    /**
     * Get the directions recursively
     */
    void getOptionalDirections() {
        int direction = sc.nextInt();
        if (direction != -1) {
            decodedOrder += " and send one to";
            translateInput(directionTranslations, direction);
            getOptionalDirections();
        }
    }

    /**
     * Execute program according to Part 3
     */
    void partThree() {
        decodedOrder += "Build a concrete base";
        //get the boxes recursively
        getBoxes(new ArrayList<>());
    }

    /**
     * Ask for box type indexes and return the boxes
     *
     * @param boxes the current boxes
     */
    void getBoxes(ArrayList<String> boxes) {
        int input = sc.nextInt();
        if (input == 4) { //so this are all the boxes
            //ask for paintings, which decides the amount of 1x1x1 boxes
            ArrayList<String> paintings = getPaintings();
            if (boxes.size() > 0) {
                echoBoxes(boxes, paintings.size());
            }
            if (paintings.size() > 0) {
                echoPaintings(paintings);
            }
        } else {
            boxes.add(typeTranslations[input] + " cube");
            getBoxes(boxes);
        }
    }

    /**
     * Ask for paining indexes and return them
     *
     * @return array with the paintings
     */
    ArrayList<String> getPaintings() {
        int input = sc.nextInt();
        if (input == -1) {
            //return empty list
            return new ArrayList<>();
        } else {
            //get more paintings
            ArrayList<String> currentPaintings = getPaintings();
            //add the painting in the beginning
            currentPaintings.add(0, resourceTypeTranslations[input]);
            //return the current paintings
            return currentPaintings;
        }
    }

    /**
     * Prints the boxes to the terminal
     *
     * @param boxes            the boxes array
     * @param amountOfOneByOne amount of 1x1x1's on top
     */
    void echoBoxes(ArrayList<String> boxes, int amountOfOneByOne) {
        decodedOrder += " on top of which is a";
        if (boxes.size() > amountOfOneByOne) {
            decodedOrder += " 2x2x2";
        } else {
            decodedOrder += " 1x1x1";
        }
        decodedOrder += boxes.remove(0);
        if (boxes.size() > 0) {
            echoBoxes(boxes, amountOfOneByOne);
        }
    }

    /**
     * Prints the paintings to the terminal
     *
     * @param paintings the array with the paintings wanted
     */
    void echoPaintings(ArrayList<String> paintings) {
        decodedOrder += " with";
        decodedOrder += paintings.remove(0);
        decodedOrder += " painted on the sides";
        if (paintings.size() > 0) {
            echoPaintings(paintings);
        }
    }

    /**
     * Ask for input and convert to code
     *
     * @param translations the translations from
     * @return translation
     */
    int translateInput(String[] translations) {
        return translateInput(translations, sc.nextInt());
    }

    /**
     * Use input and convert to code
     *
     * @param translations the translations from
     * @param code the index of the translation to get
     * @return translation
     */
    int translateInput(String[] translations, int code) {
        if (code == -1 || code >= translations.length) { //stop the program
            return -1;
        }
        decodedOrder += translations[code];
        return code;
    }

    /**
     * Initialization method
     *
     * @param args the arguments given to the class constructor
     */
    public static void main(String[] args) {
        ApeIntelligence instance = new ApeIntelligence();
        instance.run();
    }
}
