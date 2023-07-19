package BookSearchAutomation;

import java.util.*;

public class Automation_test extends DriverSetup{
	
	public static void main(String[] args) {
		
        Scanner scanner = new Scanner(System.in);

        int choice;
        boolean isValidChoice = false;
        
        System.out.println("Choose one of the Driver(Chrome/Edge):");
        driver_choice=scanner.nextInt();
        
        System.out.println("Choose an option:");
        System.out.println("1. Listing Category and Count of Books");
        System.out.println("2. Check whether Tamil books are available more than one thousand");
        System.out.println("3. Browsing the books with respective to Subject. Listing Category and Hits for each subcategory");

        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Direct_Listing_Category_Count.main(args);
                    isValidChoice = true;
                    break;

                case 2:
                	Tamil_Language_Books.main(args);
                	Listing_Category_count.main(args);
                    isValidChoice = true;
                    break;

                case 3:
                	Listing_Category_count.main(args);
                    isValidChoice = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (!isValidChoice);

        scanner.close();
        
    }

}
