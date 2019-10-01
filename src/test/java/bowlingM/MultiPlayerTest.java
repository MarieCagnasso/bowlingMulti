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
        
       /* @Test
        public void gameFinish() throws Exception{
            game.startNewGame(players);
            for (int t=0; t<10; t++){
                for(int b=0;b<4;b++){
                    game.lancer(2);
                }
            }
            System.out.println(game.displayMsg());
           
        }*/
}
