// Allen Bao
// 11/12/21
// Takes in the user's input, a measurement of distance, in either miles or kilometers, and converts it to the other unit.

import java.util.Scanner;

public class TravelToGermany {
   public static void main (String[] arg) {
   
      // Create scanner for input
      Scanner keyboard = new Scanner(System.in);
      
      // Declare Variables
      double distance;
      String unit;
      
      // Explain program to user
      System.out.println("Hello. This program will convert Miles to Kilometers, or vice-versa.");
      
      // Gets user input
      // Get the distance
      System.out.print("To get started please enter a distance: ");
      distance = keyboard.nextDouble();
      System.out.println("You've entered " + distance);
      keyboard.nextLine();
      // Get the units
      System.out.print("Did you submit Miles or Kilometers? \n" + "Type m for Miles, or k for Kilometers: ");
      unit = keyboard.nextLine();
      
      // Call the function to convert the distance
      convertDistance(distance, unit);
   }
   
   public static void convertDistance (double distance, String unit) {
      // if the user entered miles then convert to kilometers
      if (unit.toLowerCase().equals("m") || unit.equals("Miles")) {
         System.out.println(distance + " miles is equal to " + distance * 1.60934 + " Kilometers");
         
      } 
      // If the user entered kilometers then convert to miles
      else if (unit.toLowerCase().equals("k") || unit.equals("Kilometer")) {
         System.out.println(distance + " kilometers is equal to " + distance * 0.621371 + " Miles");
         
      } 
      // if the user gave an incorrect input then print an error message
      else { 
         System.out.println("An incorrect option was selected, please input k for Kilometer or m for Miles");
      }       
   } 
}    