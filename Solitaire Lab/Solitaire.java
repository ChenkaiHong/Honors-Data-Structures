import java.util.*;

/**
 * Solitaire game code
 *
 * @author - Chenkai Hong
 * @version - 01/03/2024
 */

public class Solitaire
{
    /**
     * Runs a solitaire game.
     */
    public static void main(String[] args)
    {
        new Solitaire();
    }

    // instance variables
    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations;
    private Stack<Card>[] piles;
    private SolitaireDisplay display;

    /**
     * Constructor for the Solitaire class.
     */
    public Solitaire()
    {
        foundations = new Stack[4];
        for (int i = 0; i < 4; i++)
        {
            foundations[i] = new Stack();
        }
        piles = new Stack[7];
        for (int i = 0; i < 7; i++)
        {
            piles[i] = new Stack();
        }
        stock = new Stack();
        waste = new Stack();
        display = new SolitaireDisplay(this);
        createRiggedGame();
        while (true)
        {
            if (stock.isEmpty() && waste.isEmpty() && this.pilesEmpty())
            {
                display.setTitle("You Win!!!");
                System.out.println("You win!!! Good Job!!!");
                break;
            }
        }
    }

    /**
     * Deals the stock stack to piles.
     */
    public void deal()
    {
        for (int i = 0; i < piles.length; i++)
        {
            for (int j = 0; j < i + 1; j++)
            {
                piles[i].push(stock.pop());
                if (i == j)
                {
                    piles[i].peek().turnUp();
                }
            }
        }
    }

    /**
     * Determines if the piles are empty.
     * @return whether all the piles are empty
     */
    public boolean pilesEmpty()
    {
        boolean empty = true;
        for (int i = 0; i < 7; i++)
        {
            if (!piles[i].isEmpty())
            {
                empty = false;
                break;
            }
        }
        return empty;
    }

    /**
     * Returns the card on top of the stock, or null if the stock is empty.
     * @return the stock card
     */
    public Card getStockCard()
    {
        if (stock.isEmpty())
        {
            return null;
        }
        return stock.peek();
    }

    /**
     * Returns the card on top of the waste, or null if the waste is empty
     * @return the card on top of the waste pile
     */
    public Card getWasteCard()
    {
        if (waste.isEmpty())
        {
            return null;
        }
        return waste.peek();
    }

    /**
     * Gets the card on top of the foundation at index.
     * @precondition: 0 <= index < 4
     * @postcondition: returns the card on top of the given foundation,
     *                 or null if the foundation is empty
     * @param index Index of the foundation card being retrieved.
     * @return the foundation card
     */
    public Card getFoundationCard(int index)
    {
        if (foundations[index].isEmpty())
        {
            return null;
        }
        return foundations[index].peek();
    }

    /**
     * Gets the pile at the index.
     * @precondition: 0 <= index < 7
     * @postcondition: returns a reference to the given pile
     * @param index The Index of the pile being retrieved.
     * @return Pile at the index
     */
    public Stack<Card> getPile(int index)
    {
        return piles[index];
    }

    /**
     * Creates the stock stack with 52 randomly shuffled cards.
     */
    public void createStock()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 1; i <= 13; i++)
        {
            cards.add(new Card(i, "s"));
            cards.add(new Card(i, "d"));
            cards.add(new Card(i, "c"));
            cards.add(new Card(i, "h"));
        }
        for(int i = 52; i > 0; i--)
        {
            int random = (int) (Math.random() * i);
            stock.push(cards.remove(random));
        }
    }

    /**
     * Creates a rigged Solitaire game to test the additional feature
     * of an end sreen and message.
     */
    public void createRiggedGame()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 1; i <= 13; i++)
        {
            cards.add(new Card(i, "s"));
            cards.add(new Card(i, "d"));
            cards.add(new Card(i, "c"));
            cards.add(new Card(i, "h"));
        }

        for (int i = 51; i >= 0; i--)
        {
            stock.push(cards.remove(i));
        }
    }

    /**
     * Resets the stock by moving all cards from waste to stock.
     */
    public void resetStock()
    {
        while (!waste.isEmpty())
        {
            stock.push(waste.pop());
            stock.peek().turnDown();
        }
    }

    /**
     * Deals three cards from stock to waste.
     */
    public void dealThreeCards()
    {
        int i = 0;
        while (i < 3 && !stock.isEmpty())
        {
            waste.push(stock.pop());
            waste.peek().turnUp();
            i++;
        }
    }

    /**
     * Called when the stock is clicked
     */
    public void stockClicked()
    {
        System.out.println("stock clicked");
        if (display.isWasteSelected() || display.isPileSelected())
        {
            return;
        }
        if (stock.isEmpty())
        {
            resetStock();
        }
        else
        {
            dealThreeCards();
        }
        display.unselect();
    }

    /**
     * Called when the waste is clicked.
     */
    public void wasteClicked()
    {
        if (!display.isWasteSelected() && !display.isPileSelected() && !waste.isEmpty())
        {
            display.selectWaste();
        }
        else if (display.isWasteSelected())
        {
            display.unselect();
        }
        System.out.println("waste clicked");
    }

    /**
     * Called when the foundation at the given index is clicked.
     * @precondition: 0 <= index < 4
     * @param index the index of the foundation that is clicked
     */
    public void foundationClicked(int index)
    {
        if (display.isWasteSelected())
        {
            if (this.canAddToFoundation(waste.peek(), index))
            {
                foundations[index].push(waste.pop());
            }
        }
        else if (display.isPileSelected())
        {
            if (this.canAddToFoundation(piles[display.selectedPile()].peek(), index))
            {
                foundations[index].push(piles[display.selectedPile()].pop());
            }
            System.out.println("foundation #" + index + " clicked");
        }
        else if (!display.isFoundationSelected() && !foundations[index].isEmpty())
        {
            display.selectFoundation(index);
        }
        else if (display.isFoundationSelected())
        {
            display.unselect();
        }
    }

    /**
     * Called when the pile at the given index is clicked.
     * @precondition:  0 <= index < 7\
     * @param index the index of the pile that is clicked
     */
    public void pileClicked(int index)
    {
        System.out.println("pile #" + index + " clicked");
        Stack<Card> pile = getPile(index);

        if (display.isFoundationSelected())
        {
            int ind = display.selectedFoundation();
            if (foundations[ind].isEmpty())
            {
                display.selectPile(index);
                return;
            }
            if (canAddToPile(foundations[ind].peek(), index))
            {
                piles[index].push(foundations[ind].pop());
                display.unselect();
            }
        }
        else if (!pile.isEmpty() && pile.peek().isFaceUp())
        {
            if (!display.isWasteSelected() && !display.isPileSelected())
            {
                display.selectPile(index);
                return;
            }
        }
        else if (!display.isPileSelected() && !display.isWasteSelected() &&
                !pile.isEmpty() && !pile.peek().isFaceUp())
        {
            pile.peek().turnUp();
            return;
        }
        if (display.isPileSelected() && (display.selectedPile() == index))
        {
            display.unselect();
        }
        if(display.isPileSelected() && (display.selectedPile() != index))
        {
            Stack<Card> selected = removeFaceUpCards(display.selectedPile());
            if (canAddToPile(selected.peek(), index))
            {
                addToPile(selected, index);
                display.unselect();
            }
            else
            {
                addToPile(selected, display.selectedPile());
            }
        }
        if (display.isWasteSelected())
        {
            Card w = waste.peek();
            if (canAddToPile(w, index))
            {
                getPile(index).push(waste.pop());
                display.unselect();
            }
        }
    }

    /**
     * Checks if the card can be added to a pile.
     *
     * @param card  - the card to be checked
     * @param index - the pile to be checked
     * @return - whether the card can be added
     */
    public boolean canAddToPile(Card card, int index)
    {
        Stack<Card> pile = getPile(index);
        if (pile.isEmpty())
        {
            return card.getRank() == 13;
        }
        else if (!pile.peek().isFaceUp())
        {
            return false;
        }
        if ((pile.peek().isRed() && !card.isRed()) || (!pile.peek().isRed() && card.isRed()))
        {
            return card.getRank() == pile.peek().getRank() - 1;
        }
        return false;
    }

    /**
     * Removes all face-up cards on the top of a pile.
     * @param index - the index of the pile
     * @return - the stack of the removed cards
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card> pile = getPile(index);
        Stack<Card> temp = new Stack<Card>();
        while (!pile.isEmpty() && pile.peek().isFaceUp())
        {
            temp.push(pile.pop());
        }
        return temp;
    }

    /**
     * Adds a stack of cards to a given pile.
     * @param cards - the stack of cards to be added to the pile
     * @param index - the index of the pile
     */
    private void addToPile(Stack<Card> cards, int index)
    {
        while (!cards.isEmpty())
        {
            piles[index].push(cards.pop());
        }
    }

    /**
     * Checks if a card can be added to a foundation.
     * @param card  - the card to be checked
     * @param index - the index of the foundation to be checked
     * @return - whether the card can be added to the foundation
     */
    private boolean canAddToFoundation(Card card, int index)
    {
        Card foundation = getFoundationCard(index);
        if (foundation == null)
        {
            return card.getRank() == 1;
        }
        return foundation.getSuit().equals(card.getSuit())
                && (foundation.getRank() == card.getRank() - 1);
    }
}