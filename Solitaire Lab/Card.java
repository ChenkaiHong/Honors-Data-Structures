/**
 * Card class contains basic methods for Card objects
 *
 * @author Chenkai Hong
 * @version 12/18/2023
 */
public class Card
{
    //instance variables
    private final int rank;
    private final String suit;
    private boolean isFaceUp;

    /**
     * Constructor for the card class.
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(int rank, String suit)
    {
        this.rank = rank;
        this.suit = suit;
        isFaceUp = false;
    }

    /**
     * Gets rank of card.
     * @return rank
     */
    public int getRank()
    {
        return rank;
    }

    /**
     * Gets suit of card.
     * @return suit
     */
    public String getSuit()
    {
        return suit;
    }

    /**
     * Determines if the card is red.
     * @return true if the card is red; otherwise, false
     */
    public boolean isRed()
    {
        return this.suit.equals("d") || this.suit.equals("h");
    }

    /**
     * Determines if the card is facing up.
     * @return true if card is facing up; otherwise, false
     */
    public boolean isFaceUp()
    {
        return this.isFaceUp;
    }

    /**
     * Flips the card up.
     */
    public void turnUp()
    {
        this.isFaceUp = true;
    }

    /**
     * Flips the card down.
     */
    public void turnDown()
    {
        this.isFaceUp = false;
    }

    /**
     * Gets the name of the file containing the card.
     * @return the name of the file containing the card
     */
    public String getFileName()
    {
        if(!isFaceUp)
            return "cards/back.gif";
        String rankString;
        switch(rank)
        {
            case 1:
                rankString = "a";
                break;
            case 10:
                rankString = "t";
                break;
            case 11:
                rankString = "j";
                break;
            case 12:
                rankString = "q";
                break;
            case 13:
                rankString = "k";
                break;
            default:
                rankString = Integer.toString(rank);
        }
        return "cards/" + rankString + suit + ".gif";
    }
}
