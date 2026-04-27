/**

        * File: Problem set unit 4

        * Author: Nithin.A

        * Date Created: April 24th, 2026

        * Date Last Modified: April 27th, 2026

        */
public class ProblemSet {
	public static void main(String args[]) {
        Random ran = new Random();
        Scanner s = new Scanner(System.in);
        int userGuess = 0;
        int roundCount = 0;
        boolean roundCountInvalid = true;
 
        System.out.print("Welcome to Modified Russian Roulette" + "\n" + 
                         "Input how many rounds would you like to play: ");
        
        // Checking for invalid input
        while (roundCountInvalid) {
            if (s.hasNextInt()) {
                roundCount = s.nextInt();
                if (roundCount > 0) {
                    roundCountInvalid = false;
                    s.nextLine();
                }
				else {
                    System.out.print("\n" + "That was an invalid input! " + "\n" + 
                                            "Input how many rounds would you like to play: ");
                    s.nextLine();
                }
            }
			else {
                System.out.print("\n" + "That was an invalid input! " + "\n" + 
                                        "Input how many rounds would you like to play: ");
                s.nextLine();
            }
        }
 
 
 
        int lowRange = 0;
        int highRange = 0;
        boolean rangeInvalid = true;
        System.out.print("What range would you like to play between (#-#)? ");
        
         //Range input validation
        while (rangeInvalid) {
            String userRangeInput = s.nextLine().trim();
            int separatorIndex = -1;
            boolean separatorFound = false;
            for (int i = 1; i < userRangeInput.length() && !separatorFound; i++) {
                if (userRangeInput.charAt(i) == '-' && (userRangeInput.charAt(i - 1) >= '0' && userRangeInput.charAt(i - 1) <= '9')) {
                    separatorIndex = i;
                    separatorFound = true;
                }
            }
            
            
            if (!separatorFound) {
                System.out.print("That was an invalid input!" + "\n" + "What Range would you like to play between (#-#)? ");
            }
            else {
                // Splitting the left and right parts of range input
                String left  = userRangeInput.substring(0, separatorIndex);
                String right = userRangeInput.substring(separatorIndex + 1);
 
                // Checking if left and right range inputs are valid numbers
                if (!isValidInt(left) || !isValidInt(right)) {
                    System.out.print("That was an invalid input!" + "\n" + "What Range would you like to play between (#-#)? ");
                }
                else {
                    // Parsing if invalid checks fail
                    lowRange  = Integer.parseInt(left);
                    highRange = Integer.parseInt(right);
                    
                    // Checking if range input is in order from low to high.
                    if (lowRange >= highRange) {
                        System.out.print("That was an invalid input! " + "\n" + "What Range would you like to play between (#-#)? ");
                    }
                    else if (highRange - lowRange < 2) {
                        System.out.print("That was an invalid input! " + "\n" + "What Range would you like to play between (#-#)? ");
                    }
                    else {
                        rangeInvalid = false;
                    }
                }
            }
        }
 
        //Calculating middle number(s)
        int middleLow; // 3
        int middleHigh; // 5
        if ((highRange - lowRange) % 2 == 0) {
            middleLow  = lowRange + (highRange - lowRange) / 2;
            middleHigh = middleLow;
            }
        else {
            middleLow  = lowRange + (highRange - lowRange) / 2;
            middleHigh = middleLow + 1;
        }
 
        //Creating the range and middle number(s) that will be shown on the guessing menu
        String middleMenu;
        String highRangeMenu;
        String lowRangeMenu;
        if (middleLow == middleHigh) {
            middleMenu = "(" + middleLow + ")";
        }
        else {
            middleMenu = middleLow + " or " + middleHigh;
        }
        if (middleHigh + 1 == highRange){
            highRangeMenu = "(" + highRange + ")";
        }
        else {
            highRangeMenu = "(" + (middleHigh + 1) + " to " + highRange + ")";
        }
        if (middleLow - 1 == lowRange){
            lowRangeMenu = "(" + lowRange + ")";
        }
        else {
            lowRangeMenu = "(" + lowRange + " to " + (middleLow - 1) + ")";
        }
        
        int score = 0;
        int i;
        for (i = 1; i <= roundCount; i++) {
            System.out.println("\n" + "Round " + i + ":"+ "\n" + 
                               "High, Low or Even?:"+ "\n" + 
                               "1. High " + highRangeMenu + "\n" + 
                               "2. Low " + lowRangeMenu + "\n" + 
                               "3. Even " + middleMenu  + "\n");
 
 
            boolean userGuessInvalid = true;
            //Checking user guess invalidity
            while (userGuessInvalid) {
                if (s.hasNextInt()) {
                    userGuess = s.nextInt();
                    if (userGuess >= 1 && userGuess <= 3) {
                        userGuessInvalid = false;
                        s.nextLine();
                    }
                    else {
                        System.out.print("\n" + "That was an invalid input! " + "\n" + 
                                                "High, Low or Even?: ");
                        s.nextLine();
                    }
                }
                else {
                    System.out.print("\n" + "That was an invalid input! " + "\n" + 
                                            "High, Low or Even?: ");
                    s.nextLine();
                }
            }
 
            //Getting random number
            int answer = ran.nextInt(highRange - lowRange + 1) + lowRange;
 
            //Checking if user guess is correct
            String userGuessResult = guessChecker(userGuess, answer, middleLow, middleHigh);
 
            if (userGuessResult.equals("correct")) {
                score = score + 1;
            }
            System.out.println("\n" + "The number was " + answer + ", You are " + userGuessResult + ".");
            System.out.println("Current Score: " + score + "\n");
        }
 
        //Showing total score at the end of all rounds
        System.out.println("Total Score: " + score);
        if (score >= roundCount / 2.0) {
            System.out.println("Congratulations! You got " + score + " out of " + roundCount + " rounds right!");
        }
        else {
            System.out.println("You got " + score + " out of " + roundCount + " rounds right..."
                    + "\n" + "Better luck next time...");
        }
    }




    //Guess result checker
    public static String guessChecker(int userGuess, int answer, int middleLow, int middleHigh) {
        if (userGuess == 1 && answer > middleHigh) {
            return "correct";
        }
        else if (userGuess == 2 && answer < middleLow) {
            return "correct";
        }
        else if (userGuess == 3 && answer >= middleLow && answer <= middleHigh) {
            return "correct";
        }
        else {
            return "incorrect";
        }
    }
    
    //Function for checking if left and right range inputs are valid
    public static boolean isValidInt(String str) {
        //Checking if str is empty
        if (str.length() == 0) {
            return false;
        }
 
        int startIndex = 0;
        // Checking if str has negative
        if (str.charAt(0) == '-') {
            startIndex = 1;
            if (str.length() == 1) {
                return false;
            }
        }
 
        // Checks if remaining characters are a digit using ascii comparsisons
        boolean allDigits = true;
        for (int i = startIndex; i < str.length() && allDigits; i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                allDigits = false;
            }
        }
 
        return allDigits;
    }
}
