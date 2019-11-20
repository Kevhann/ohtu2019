package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            m_score1++;
        else
            m_score2++;
    }

    public String scoreSwitch(int point) {
        switch (point) {
        case 0:
            return "Love";
        case 1:
            return "Fifteen";
        case 2:
            return "Thirty";
        case 3:
            return "Forty";
        default:
            return "Deuce";
        }
    }

    public String advantage() {
        int minusResult = m_score1 - m_score2;
        if (minusResult == 1)
            return "Advantage player1";
        else if (minusResult == -1)
            return "Advantage player2";
        else if (minusResult >= 2)
            return "Win for player1";
        else
            return "Win for player2";

    }

    public String getScore() {

        if (m_score1 == m_score2) {

            if (m_score1 == 4) {
                return scoreSwitch(m_score1);
            } else {
                return scoreSwitch(m_score1) + "-All";
            }

        }
        if (m_score1 >= 4 || m_score2 >= 4) {
            return advantage();
        }

        return scoreSwitch(m_score1) + "-" + scoreSwitch(m_score2);

    }
}