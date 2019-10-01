/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowlingM;

import bowling.Frame;
import bowling.SinglePlayerGame;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *
 * @author marie
 */
public class MultiPlayer implements bowling.MultiPlayerGame{

    private final static String MSG = "Prochain tir : joueur %s, tour n° %d, boule n° %d"; 
    private final static String END = "Partie terminée"; 

    private Map<String,SinglePlayerGame> jeux;
    private Iterator<String> names;

    private String currentPlayerName;
    private SinglePlayerGame currentGame;
    
    private boolean gameIsRunning = false;
    
    MultiPlayer(){
        jeux = new LinkedHashMap<>();
    }
    
    @Override
    public String startNewGame(String[] players) throws Exception {
        if ((players == null) || players.length == 0) {
			throw new Exception("Need at least one player");
	}
        jeux.clear();
        
        for (String j : players){
            jeux.put(j, new SinglePlayerGame());
        }
        names = jeux.keySet().iterator();
        gameIsRunning=true;
        
        return displayMsg();
    }

    @Override
    public String lancer(int nbrQ) throws Exception {
        
        currentGame.lancer(nbrQ);
        
        // si le tour est fini on change de joueur 
        if (currentGame.isFinished() || currentGame.hasCompletedFrame()) {
            currentPlayerName = names.next();
            currentGame = jeux.get(currentPlayerName);
        }
        return displayMsg();
    }

    @Override
    public int scoreFor(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String displayMsg (){
        if (!gameIsRunning){
            return END;
        }
        else{
            String jname = currentPlayerName;
            int tour = currentGame.getFrameNumber();
            int boul = currentGame.getNextBallNumber();
            
            return String.format(MSG, jname, tour, boul);
        }
    }
    
}
