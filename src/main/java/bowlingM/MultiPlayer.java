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
    
    private boolean start = false;
    
    MultiPlayer(){
        jeux = new LinkedHashMap<>();
    }
    
    @Override
    public String startNewGame(String[] players) throws Exception {
        if ((players == null) || players.length == 0) {
			throw new Exception("Need one player");
	}
        jeux.clear();
        
        for (String j : players){
            jeux.put(j, new SinglePlayerGame());
        }
        
        names = jeux.keySet().iterator();
        start=true;
        
        currentPlayerName = names.next();
        currentGame = jeux.get(currentPlayerName);

        return displayMsg();
    }

    @Override
    public String lancer(int nbrQ) throws Exception {
        if (!start){
            throw new Exception("Need game start");
        }
        currentGame.lancer(nbrQ);

        // si le tour est fini on change de joueur 
        if (currentGame.isFinished() || currentGame.hasCompletedFrame()) {
            if(!names.hasNext()){
                names = jeux.keySet().iterator();
            }
            currentPlayerName = names.next();
            currentGame = jeux.get(currentPlayerName);
        }
        
        // Si la partie est fini 
        if (currentGame.getFrameNumber() == 0){
            start = false;
        }
        return displayMsg();
    }

    @Override
    public int scoreFor(String name) throws Exception {

        if (!jeux.containsKey(name)){
            throw new Exception("Unknown player");
        }
        return jeux.get(name).score();
    }
    
    /*** Affiche l'état de la partie ***/
    public String displayMsg (){
        if (!start){
            return END;
        }
        else{
            return String.format(MSG, currentPlayerName, currentGame.getFrameNumber(), currentGame.getNextBallNumber());
        }
    }
    
}
