/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjecturecreator;

import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author christopher1
 */
public class ConjectureCreator {
    //Collects the input from the user as a string. 
    //Needs to be converted into a Biginteger equation. 

    String inputEven;
    String inputOdd;
    static boolean running = true;
    static BigInteger n = new BigInteger("0");
    static Scanner sc = new Scanner(System.in);
    ArrayList<String> evenCalc = new ArrayList<>();
    ArrayList<String> oddCalc = new ArrayList<>();
    static BigInteger userOdd = new BigInteger("0");
    static BigInteger userEven = new BigInteger("0");
    static ArrayList<BigInteger> bigIntThousand = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //Loop through 1000 to test numbers
        for (long i = 2; i <= 1000; i++) {
            //biginteger named loop 
            BigInteger loop = BigInteger.ZERO;

            //setting loop equal to i
            loop = loop.valueOf(i);

            //adding i to arraylist called bigint
            bigIntThousand.add(loop.valueOf(i));
            //System.out.println(loop.valueOf(i));
        }

        System.out.println("Welcome to the Conjecture Creator!");

        try {
            System.out.println("In this program you will create two cases for which numbers will be calculated.");
            System.out.println("One for if the number is even and one for if it is odd.");
            System.out.println("The program will then calculate the number you enter and try and get to one. ");
            System.out.println("This will demonstrate the concept that the Colatz Conjecture states.");
            System.out.println("This is so that a given number will eventually get to one after being iterated \nusing arithmetic parameters for each of the subsequent even and odd cases. ");
            //run the program
            Run();

        } catch (Exception e) {
            System.out.println("That's not what we expected. Try again.");
            Run();

        }
    }

    public static void Run() {

        while (running == true) {

            System.out.println("Enter 1 to calculate another number using the Colatz Conjecture.");
            System.out.println("Enger 2 if you would like to enter your own parameters.");
            System.out.println("Or if you would like to exit the program enter 0.");
            long r = sc.nextInt();
            if (r == 0) {
                End();
            } else if (r == 1) {
                ColatzCalculate();
            } else if (r == 2) {
                CreatedCalculate();
            } else if (r < 2) {
                System.out.println("That's not an option. Please enter a valid response.");
                Run();

            }

        }

    }

    //Create another method so that anyone can input conditions into the program to see if it will get to one. 
    public static void EquationInputEven() {
        boolean isOdd = false;
        System.out.println("Please enter an equation for when the number is even:");
        String even = sc.next();
        EquationParser parserEven = new EquationParser(even, n, isOdd);

    }

    //Create method for input of odd equation calculation.
    public static void EquationInputOdd() {
        boolean isOdd = true;
        System.out.println("Please enter an equation for when the number is odd:");

        String odd = sc.next();
        EquationParser parserOdd = new EquationParser(odd, n, isOdd);

    }

    public static void ColatzCalculate() {
        boolean calculate = true;

        long counter = 0;
        try {
            while (calculate == true) {

                System.out.println("Please enter an integer of your choosing:");
                n = sc.nextBigInteger();
                BigInteger one = new BigInteger("1");
                BigInteger three;
                BigInteger two;
                BigInteger zero;
                zero = BigInteger.valueOf(0);
                two = BigInteger.valueOf(2);
                three = BigInteger.valueOf(3);
                while (n.compareTo(one) > 0) {

                    if (n.mod(two).equals(BigInteger.ONE)) {
                        BigInteger odd = new BigInteger("0");
                        odd = n.multiply(three).add(one);

                        n = odd;
                        System.out.println(odd);
                    } else if (n.mod(two).equals(BigInteger.ZERO)) {
                        BigInteger even = new BigInteger("0");
                        even = n.divide(two);

                        n = even;
                        System.out.println(even);
                    }
                    if (n.equals(BigInteger.ONE)) {
                        calculate = false;

                    }
                    counter++;
                }

            }
        } catch (Exception e) {
// Fix this part here.
            System.out.println("That's not a number. Try again.");

        }

        System.out.println("It took: " + counter + " steps to get to 1.");
        counter = 0;
    }

    public static void EquationTest() {
        for (int i = 0; i < bigIntThousand.size(); i++) {
            n = bigIntThousand.get(i);
            //TODO: Figure out how to tell if it will loop infinitely if the equations entered don't work. 
        }

    }

    public static void CreatedCalculate() {
        boolean calculate = true;

        long counter = 0;

        try {
            System.out.println("Please enter your parameters in a simple format.");
            EquationInputEven();
            EquationInputOdd();
            System.out.println("Would you like to test your parameters on the integers from 2 to 1000?");
            System.out.println("This will test them to make sure they work.");
            String check = sc.next();
            if (check == "Y" || check == "y" || check == "yes" || check == "Yes") {
                EquationTest();
            } else if (check == "N" || check == "n" || check == "no" || check == "No") {
                System.out.println("There's a chance it might calculate forever. \nType Ctrl + C to exit the program if it loops forever.");
            }

            while (calculate == true) {

                System.out.println("Please enter an integer of your choosing:");
                n = sc.nextBigInteger();
                BigInteger one = new BigInteger("1");
                BigInteger three;
                BigInteger two;
                BigInteger zero;
                zero = BigInteger.valueOf(0);
                two = BigInteger.valueOf(2);
                three = BigInteger.valueOf(3);
                while (n.compareTo(one) > 0) {

                    if (n.mod(two).equals(BigInteger.ONE)) {
                        BigInteger odd = new BigInteger("0");

                        odd = userOdd;
                        n = odd;
                        System.out.println(odd);
                    } else if (n.mod(two).equals(BigInteger.ZERO)) {
                        BigInteger even = new BigInteger("0");

                        even = userEven;
                        n = even;
                        System.out.println(even);
                    }
                    if (n.equals(BigInteger.ONE)) {
                        calculate = false;

                    }
                    counter++;
                }

            }
        } catch (Exception e) {
            System.out.println("That's not a number. Try again.");
            System.out.println(e);

        }

        System.out.println("It took: " + counter + " steps to get to 1.");
        counter = 0;
    }

    public static void End() {
        System.exit(0);
    }

    public String getInputEven() {
        return inputEven;
    }

    public void setInputEven(String inputEven) {
        this.inputEven = inputEven;
    }

    public String getInputOdd() {
        return inputOdd;
    }

    public void setInputOdd(String inputOdd) {
        this.inputOdd = inputOdd;
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        ConjectureCreator.running = running;
    }

    public static BigInteger getN() {
        return n;
    }

    public static void setN(BigInteger n) {
        ConjectureCreator.n = n;
    }

    public static Scanner getSc() {
        return sc;
    }

    public static void setSc(Scanner sc) {
        ConjectureCreator.sc = sc;
    }

    public ArrayList<String> getEvenCalc() {
        return evenCalc;
    }

    public void setEvenCalc(ArrayList<String> evenCalc) {
        this.evenCalc = evenCalc;
    }

    public ArrayList<String> getOddCalc() {
        return oddCalc;
    }

    public void setOddCalc(ArrayList<String> oddCalc) {
        this.oddCalc = oddCalc;
    }

    public BigInteger getUserOdd() {
        return userOdd;
    }

    public void setUserOdd(BigInteger userOdd) {
        this.userOdd = userOdd;
    }

    public BigInteger getUserEven() {
        return userEven;
    }

    public void setUserEven(BigInteger userEven) {
        this.userEven = userEven;
    }

}
