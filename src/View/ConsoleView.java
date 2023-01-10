package View;

import Model.Player;
import Model.Stack;

import java.util.Random;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);
    private Random rand = new Random();

    
    public int menu(){
        System.out.println("Welcome to Nim!");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs AI");
        System.out.println("3. Print Rules");
        System.out.println("4. Exit");
        System.out.println("Please enter your choice: ");
        int choice = readInt(1,4);
        return choice;
    }

    public void printRules(){
        System.out.println("The rules of Nim are simple. \n There are two stacks of sticks. Each player takes turns removing sticks from the stacks. \n The player who removes the last stick between both piles loses wins.");
    }

    public String askForName(String player) {
        String name = "";
        System.out.println(player + ", what is your name?");
        name = scanner.next();
        if (name.length() < 1) {
            name = player;
        }
        return name;
    }

    public int setStackCount(String name) {
        System.out.println("Please Enter Stack Count for " + name + " (1-100)");
        return readInt(1, 100);
    }

    public void turn(Player player, Stack stack1, Stack stack2){
        System.out.println(player.getName() + "'s turn");
        System.out.printf("Stack 1: %d Stack 2: %d \n", stack1.getSticks(), stack2.getSticks());
        int choice;
        if(stack1.getSticks() == 0)
        {
            System.out.println("Stack 1 is empty, you must take from stack 2");
            choice = 2;

        }
        else if(stack2.getSticks() == 0)
        {
            System.out.println("Stack 2 is empty, you must take from stack 1");
            choice = 1;
        }
        else
        {
            System.out.println("Would you like to take from stack 1 or stack 2 (1/2)?");
            choice = readInt(1, 2);
        }
        if (choice == 1) {
            System.out.println("How many sticks would you like to take from stack 1? (1-" + stack1.getSticks() + ")");
            int sticks = readInt(1, stack1.getSticks());
            stack1.setSticks(stack1.getSticks() - sticks);
        } else {
            System.out.println("How many sticks would you like to take from stack 2? (1-" + stack2.getSticks() + ")");
            int sticks = readInt(1, stack2.getSticks());
            stack2.setSticks(stack2.getSticks() - sticks);
        }
    }

    public void AITurn(Stack stack1, Stack stack2){
        int stack;
        if(stack1.getSticks() == 0) {
            stack = 2;
        }
        else if(stack2.getSticks() == 0) {
            stack = 1;
        }
        else {
            rand = new Random();
            stack = rand.nextInt(2) + 1;
        }
        if (stack == 1) {
            rand = new Random();
            int sticks = rand.nextInt(stack1.getSticks()) + 1;
            System.out.printf("AI took %d sticks from stack 1\n", sticks);
            stack1.setSticks(stack1.getSticks() - sticks);

        } else {
            rand = new Random();
            int sticks = rand.nextInt(stack2.getSticks()) + 1;
            System.out.printf("AI took %d sticks from stack 2\n", sticks);
            stack2.setSticks(stack2.getSticks() - sticks);

        }
    }



    public int readInt(int min, int max) {
        int input = 0;
        boolean valid = false;
        while (!valid) {
            try {
                input = Integer.parseInt(scanner.next());
                if (input >= min && input <= max) {
                    valid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        }
        return input;
    }

    public void printWinner(String name){
        System.out.println(name + " wins!");
    }

    public boolean playAgain(){
        System.out.println("Do you want to play again? (y/n)");
        String input = scanner.next();
        if (input.equals("y")){
            System.out.println("Starting new game...");
            return true;
        }
        else{
            System.out.println("Goodbye!");
            return false;
        }
    }




}
