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
      String distanceMeasurement;
      
      // Explain program to user
      System.out.println("Hello. This program will convert Miles to Kilometers, or vice-versa.");
      
      System.out.print("To get started please enter a distance: ");
      distance = keyboard.nextDouble();
      System.out.println("You've entered " + distance);
      keyboard.nextLine();
      System.out.print("Did you submit Miles of Kilometers? \n" + "Type m for Miles, or k for Kilometers: ");
      distanceMeasurement = keyboard.nextLine();
      convertDistance(distance, distanceMeasurement);
   }
   
   public static void convertDistance (double distance, String distanceMeasurement) {
      if (distanceMeasurement.toLowerCase().equals("m") || distanceMeasurement.equals("Miles")) {
         System.out.println(distance + " miles is equal to " + distance * 1.60934 + " Kilometers");
         
      } else if (distanceMeasurement.toLowerCase().equals("k") || distanceMeasurement.equals("Kilometer")) {
         System.out.println(distance + " kilometers is equal to " + distance * 0.621371 + " Miles");
         
      } else { 
         System.out.println("An incorrect option was selected, please input k for Kilometer or m for Miles");
      }       
   } 
}    