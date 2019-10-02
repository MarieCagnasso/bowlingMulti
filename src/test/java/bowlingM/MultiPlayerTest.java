/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowlingM;

import bowling.SinglePlayerGame;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author marie
 */
public class MultiPlayerTest {
    private MultiPlayer game;
    private String[]players={"John", "Paul"};
      
	@Before
	public void setUp() {
            game = new MultiPlayer();
	}
        
        @Test
        public void gameStartMsg () throws Exception{
            assertEquals("Prochain tir : joueur John, tour n° 1, boule n° 1",game.startNewGame(players)); 
        }
        
        @Test (expected = Exception.class)
        public void neddOnePlayer() throws Exception{
            String[] s={};
            game.startNewGame(s);
        }
        
        @Test 
        public void lancerStrike () throws Exception{
            game.startNewGame(players);
            assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1",game.lancer(10)); 
        }
        
        @Test
        public void lancerSpareScore() throws Exception{
            game.startNewGame(players);
            game.lancer(5);
            game.lancer(5);
            game.lancer(5);
            game.lancer(3);
            game.lancer(5);
            game.lancer(1);
            assertEquals(21,game.scoreFor("John"));      
        }
        
        @Test
        public void gameFinish() throws Exception{
            game.startNewGame(players);
            for (int t=0; t<10; t++){
                for(int b=0;b<4;b++){
                    game.lancer(2);
                }
            }
            assertEquals("Partie terminée",game.displayMsg());
        }
        
        @Test
        public void winners () throws Exception{
            game.startNewGame(players);
           for (int t=0; t<24; t++){
                game.lancer(10);
           }
            assertEquals("Partie terminée",game.displayMsg());
            assertEquals(300,game.scoreFor("Paul"));
            assertEquals(300,game.scoreFor("John"));
            assertEquals("Partie terminée",game.displayMsg());
        }
        
        @Test
        public void looser () throws Exception{
           game.startNewGame(players);
           for (int t=0; t<40; t++){
                game.lancer(0);
           }
            assertEquals("Partie terminée",game.displayMsg());
            assertEquals(0,game.scoreFor("Paul"));
            assertEquals(0,game.scoreFor("John"));
        }
        
        @Test (expected = Exception.class)
        public void lancerGameEnd () throws Exception{
            game.startNewGame(players);
           for (int t=0; t<24; t++){
                game.lancer(10);
           }
           game.lancer(1);
        }
        
        @Test (expected = Exception.class)
        public void playerUnknow () throws Exception{
            game.startNewGame(players);
            game.scoreFor("Marie");
        }
}
