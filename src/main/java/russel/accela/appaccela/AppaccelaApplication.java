package russel.accela.appaccela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import russel.accela.appaccela.exception.ResourceNotFoundException;
import russel.accela.appaccela.model.Address;
import russel.accela.appaccela.model.Person;
import russel.accela.appaccela.service.AddressService;
import russel.accela.appaccela.service.PersonService;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class AppaccelaApplication {

    private static PersonService personService;
    private static AddressService addressService;

    @Autowired
    public AppaccelaApplication(PersonService personService, AddressService addressService) {
        this.personService = personService;
        this.addressService = addressService;
    }

    public static void main(String[] args) {

        SpringApplication.run(AppaccelaApplication.class, args);

        // declare a variable that will store the user input
        String userInput;

        //declare a scanner object to read the command line input by user
        Scanner scanner = new Scanner(System.in);

        //loop the utility in loop until the user makes the choice to exit
        while(true){
            System.out.println();
            System.out.println("*****Select your choice:*****");
            System.out.println();
            //Print the options for the user to choose from
            System.out.println("* Press 1 to Add Person (id, firstName, lastName)");
            System.out.println("* Press 2 to Edit Person (firstName, lastName)");
            System.out.println("* Press 3 to Delete Person (id)");
            System.out.println("* Press 4 to Add Address to person [multiple possible] (id, street, city, state, " +
                    "postalCode)");
            System.out.println("* Press 5 to Edit Address (street, city, state, postalCode)");
            System.out.println("* Press 6 to Delete Address (id)");
            System.out.println("* Press 7 to Count Number of Persons");
            System.out.println("* Press 8 to List Persons");
            System.out.println("* Press 0 to exit");
            System.out.println();
            // Prompt the use to make a choice
            System.out.println("Enter your choice:");

            //Capture the user input in scanner object and store it in a pre-declared variable
            userInput = scanner.next();

            //Check the user input
            switch(userInput){
                case "1":
                    //do the job number 1
                    System.out.println();
                    System.out.println("* 1. Add Person : ");
                    System.out.println();
                    System.out.println("Enter First Name - ");
                    String firstName = scanner.next();
                    System.out.println("Enter Last Name - ");
                    String lastName = scanner.next();
                    personService.addPerson(new Person(firstName, lastName));
                    System.out.println();
                    System.out.println("A new Person has been added!");
                    break;
                case "2":
                    //do the job number 2
                    System.out.println();
                    System.out.println("* 1. Edit Person : ");
                    System.out.println();
                    System.out.println("We need person id * - ");

                    long personId = scanner.nextLong();

                    System.out.println("Edit First Name ( * Enter na to keep same )- ");
                    String newFirstName = scanner.next();
                    System.out.println("Edit Last Name ( * Enter na to keep same ) - ");
                    String newLastName = scanner.next();

                    try{
                        personService.editPerson(personId, new Person(newFirstName,newLastName));
                        System.out.println();
                        System.out.println("A Person has been edited!");
                    }catch (ResourceNotFoundException resourceNotFound){
                        System.out.println();
                        System.out.println("ResourceNotFoundException: " +resourceNotFound.getMessage());
                    }
                    break;
                case "3":
                    //do the job number 3
                    System.out.println();
                    System.out.println("* 3. Delete Person : ");
                    System.out.println();
                    System.out.println("We need person id to Delete - ");
                    long personIdToDelete = scanner.nextLong();
                    try{
                        personService.deletePerson(personIdToDelete);
                        System.out.println();
                        System.out.println("A Person is successfully Deleted!");
                    }catch (ResourceNotFoundException resourceNotFound){
                        System.out.println();
                        System.out.println("Exception found: " + resourceNotFound.getMessage());
                    }
                    break;
                case "4":
                    //do the job number 4
                    System.out.println();
                    System.out.println("* 4. Add Address to Person : ");
                    System.out.println();
                    System.out.println("We need a person id * - ");
                    long personIdToAddress = scanner.nextLong();
                    System.out.println("Enter Street Name - ");
                    String street = scanner.next();
                    System.out.println("Enter City Name - ");
                    String city = scanner.next();
                    System.out.println("Enter State Name - ");
                    String state = scanner.next();
                    System.out.println("Enter Postal Code - ");
                    String postalCode = scanner.next();
                    try{
                        addressService.addAddress(personIdToAddress,
                                new Address(street, city, state, postalCode));
                        System.out.println();
                        System.out.println("An address has been added to a Person!");
                    }catch (ResourceNotFoundException resourceNotFound){
                        System.out.println();
                        System.out.println("Address Add Exception: " + resourceNotFound.getMessage());
                    }

                    break;
                case "5":
                    //do the job number 5
                    System.out.println();
                    System.out.println("* 5. Edit Address : ");
                    System.out.println();
                    System.out.println("We need a person id * - ");
                    long personIdToAddressEdit = scanner.nextLong();
                    System.out.println("Please provide the address id * - ");
                    long addressIdToEdit = scanner.nextLong();
                    System.out.println("Edit Street Name - ");
                    String newStreetName = scanner.next();
                    System.out.println("Edit City Name - ");
                    String newCityName = scanner.next();
                    System.out.println("Edit State Name - ");
                    String newStateName = scanner.next();
                    System.out.println("Edit Postal Code - ");
                    String newPostalCode = scanner.next();
                    try{
                        addressService.editAddress(personIdToAddressEdit, addressIdToEdit,
                                new Address(newStreetName, newCityName, newStateName, newPostalCode));
                        System.out.println();
                        System.out.println("An Address has been updated!");
                    }catch (ResourceNotFoundException resourceNotFound){
                        System.out.println();
                        System.out.println("Address Edit Exception: " +resourceNotFound.getMessage());
                    }

                    break;
                case "6":
                    //do the job number 6
                    System.out.println();
                    System.out.println("* 6. Delete Address : ");
                    System.out.println();
                    System.out.println("We need a person id * - ");
                    long personIdToDeleteAddress = scanner.nextLong();
                    System.out.println("Please provide the Address Id to Delete - ");
                    long addressIdToDelete = scanner.nextLong();

                    try{
                        addressService.deleteAddress(personIdToDeleteAddress, addressIdToDelete);
                        System.out.println();
                        System.out.println("An Address is successfully Deleted!");
                    }catch (ResourceNotFoundException resourceNotFound){
                        System.out.println();
                        System.out.println("Address Delete Exception: " + resourceNotFound.getMessage());
                    }

                    break;
                case "7":
                    //do the job number 7
                    System.out.println();
                    System.out.println("* 7. Total Number of Persons are : ");
                    System.out.println(personService.getTotalNumberOfPersons());
                    System.out.println();
                    System.out.println("Now you know the total number of person.");
                    break;
                case "8":
                    //do the job number 8
                    System.out.println();
                    System.out.println("* List of Person : ");
                    System.out.println();
                    System.out.println(Arrays.toString(personService.getListOfPersons().toArray()));
                    System.out.println();
                    System.out.println("Persons List has been retrieved!");
                    break;
                case "0":
                    //exit from the program
                    System.out.println("Thanks for using our Accela Console Application!");
                    System.out.println("Now exiting...!");

                    System.exit(0);
                default:
                    //inform user in case of invalid choice.
                    System.out.println("Invalid choice. Read the options carefully...");
            }
        }
    }
}
