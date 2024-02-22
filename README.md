# Restaurant Simulation Project
This Java Object-Oriented Programming project simulates restaurant experience using JavaFX and Gradle. The application is designed to imitate a visit to a restaurant, with all interactions in Estonian. 

## Features
1. **Menu File Reading**:  The application reads the restaurant menu from a file, allowing for easy updates and modifications. **Note: the associated image displaying the menu remains static and will not reflect changes**
2. **Waitress Assignment**: The application randomly assigns one of the four waitresses to serve you.
3. **Age Verification**: If an alcoholic beverage (or any item that has "18+" mark) is ordered, the application prompts the user to enter their Estonian identification code (11 char). 
4. **Ordering System**: Users can order multiple items from the menu.
5. **Billing System**: After completing the order, users can request bill, and the application displays the bill and total amount spent. 
6. **Bill Logging**: The application writes the bill details, including the items ordered and the total amount, into a file.

## How to Use
To run the project, simply start the Kasutajaliides.java class located in src/main/java/com/resto/.
1. Run the 'Kasutajaliides.java' class
   OR
   Run this command
   ```
   ./gradlew run
   ```
3. You can make changes to menu in the "src/main/resources/com/resto/menu.txt" file.
4. You can see the bill in the "src/main/resources/com/resto/tšekk.txt" file.

### Notes

- The application is in Estonian, including prompts and messages.
- The identification code for age verification is also Estonian and is expected to be 11 characters long.

### Project Contributors
This project was developed by Kadri-Ketter Kont and Roberta Solom.
