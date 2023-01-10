package Controller;

import Model.Player;
import Model.Stack;
import View.ConsoleView;

public class Game {


    //Use a Stack class to pass my reference into the view
    private Stack stack1 = new Stack();
    private Stack stack2 = new Stack();

    private boolean isGameOver = false;
    private Player player1 = new Player();
    private Player player2 = new Player();


    private ConsoleView view = new ConsoleView();

    public void start()
    {
        boolean menu = true;
        while (menu) {
            int option = view.menu();
            switch (option) {
                case 1:
                    setup();
                    break;
                case 2:
                    AISetup();
                    break;
                case 3:
                    view.printRules();
                    break;
                case 4:
                    menu = false;
                    break;
            }
            menu = view.playAgain();
        }
    }

    private void setup() {
        isGameOver = false;
        stack1.setSticks(view.setStackCount("Stack 1"));
        stack2.setSticks(view.setStackCount("Stack 2"));
        player1.setName(view.askForName("Player 1"));
        player2.setName(view.askForName("Player 2"));
        setRandomPlayerTurn();
        game();
    }

    private void AISetup() {
        isGameOver = false;
        stack1.setSticks(view.setStackCount("Stack 1"));
        stack2.setSticks(view.setStackCount("Stack 2"));
        player1.setName(view.askForName("Player 1"));
        player2.setName("AI");
        setRandomPlayerTurn();
        AIGame();
    }

    private void AIGame() {
        while (!isGameOver) {
            if (player1.isTurn()) {
                view.turn(player1, stack1, stack2);
                if(checkForWin())
                {
                    view.printWinner(player2.getName());
                    isGameOver = true;
                }
                player1.setTurn(false);
                player2.setTurn(true);
            } else {
                view.AITurn(stack1, stack2);
                if(checkForWin())
                {
                    view.printWinner(player1.getName());
                    isGameOver = true;
                }
                player1.setTurn(true);
                player2.setTurn(false);
            }
        }
    }

    private void game()
    {
        while (!isGameOver)
        {
            if (player1.isTurn())
            {
                view.turn(player1, stack1, stack2);
                if(checkForWin())
                {
                    view.printWinner(player2.getName());
                    isGameOver = true;
                }
                player1.setTurn(false);
                player2.setTurn(true);
            }
            else
            {
                view.turn(player2, stack1, stack2);
                if(checkForWin())
                {
                    view.printWinner(player2.getName());
                    isGameOver = true;
                }
                player1.setTurn(true);
                player2.setTurn(false);
            }
        }

    }

    private boolean checkForWin(){
        if (stack1.getSticks() == 0 && stack2.getSticks() == 0){
            return true;
        }
        return false;
    }


    private void setRandomPlayerTurn()
    {
        int random = (int) (Math.random() * 2);
        if (random == 0)
        {
            player1.setTurn(true);
            player2.setTurn(false);
        }
        else
        {
            player1.setTurn(false);
            player2.setTurn(true);
        }
    }
    //This is a comment to show that we can push to Github 

}
