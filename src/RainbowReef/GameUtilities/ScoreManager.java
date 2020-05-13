package RainbowReef.GameUtilities;

public class ScoreManager {
    int playerScore;

    public ScoreManager(){
        playerScore = 0;
    }

    public void addtoScore(int points){playerScore += points;}
    public int getPlayerScore(){return playerScore;}
}
