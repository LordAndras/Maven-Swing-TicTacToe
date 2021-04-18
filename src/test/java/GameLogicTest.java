/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andras Sarro <>
 */
public class GameLogicTest {

    @Test
    public void gameLogicConstructorTest() {
        JFrame testFrame = new JFrame();
        JButton[] testButtons = {new JButton(), new JButton(), new JButton()};
        try {
            new GameLogic(testButtons, testFrame);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testTableInit() {
        GameLogic gamelogic = new GameLogic();
        gamelogic.tableInit();

        for (String[] strings : gamelogic.getSignBoard()) {
            for (String string : strings) {
                assertEquals(" ", string);
            }
        }
    }

    @Test
    public void testSwitchPlayer() {
        GameLogic gamelogic = new GameLogic();
        gamelogic.setActualPlayer("X");
        gamelogic.switchPlayer();

        assertEquals("O", gamelogic.getActualPlayer());

        gamelogic.setActualPlayer("O");
        gamelogic.switchPlayer();

        assertEquals("X", gamelogic.getActualPlayer());
    }

    @Test
    public void testNextMove() {
        JButton button = new JButton();
        button.setName(7 + "");
        GameLogic gamelogic = new GameLogic();
        gamelogic.setActualPlayer("X");
        gamelogic.tableInit();
        gamelogic.nextMove(button);
        assertEquals("X", gamelogic.getSignBoard()[2][1]);
    }

    @Test
    public void testGameWon() {
        GameLogic gamelogic = new GameLogic();
        gamelogic.tableInit();

        assertEquals(false, gamelogic.gameWon());

        gamelogic.getSignBoard()[0][0] = "X";
        gamelogic.getSignBoard()[0][1] = "X";
        gamelogic.getSignBoard()[0][2] = "X";

        assertEquals(true, gamelogic.gameWon());

        gamelogic.tableInit();

        gamelogic.getSignBoard()[1][2] = "O";
        gamelogic.getSignBoard()[2][2] = "O";
        gamelogic.getSignBoard()[0][2] = "O";

        assertEquals(true, gamelogic.gameWon());

        gamelogic.tableInit();
        gamelogic.getSignBoard()[0][1] = "X";
        gamelogic.getSignBoard()[0][1] = "O";
        gamelogic.getSignBoard()[0][1] = "X";

        assertEquals(false, gamelogic.gameWon());

        gamelogic.tableInit();
        gamelogic.getSignBoard()[1][2] = "O";
        gamelogic.getSignBoard()[2][2] = "O";
        gamelogic.getSignBoard()[0][2] = "O";

        assertEquals(true, gamelogic.gameWon());
    }

    @Test
    public void testGameEnd() {
        GameLogic gamelogic = new GameLogic();
        gamelogic.tableInit();

        assertEquals(false, gamelogic.gameEnd());

        gamelogic.setRoundIndex(9);
        assertEquals(true, gamelogic.gameEnd());

        gamelogic.setRoundIndex(6);
        gamelogic.getSignBoard()[1][2] = "O";
        gamelogic.getSignBoard()[2][2] = "O";
        gamelogic.getSignBoard()[0][2] = "O";

        assertEquals(true, gamelogic.gameEnd());

        gamelogic.tableInit();
        gamelogic.getSignBoard()[1][1] = "X";
        gamelogic.getSignBoard()[1][2] = "X";
        gamelogic.getSignBoard()[1][0] = "X";

        assertEquals(true, gamelogic.gameEnd());

    }

}
