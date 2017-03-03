package collections.compare.demo.cards;

import java.util.Comparator;

public class Card implements Comparable<Card>
{
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank()
    {
        return rank;
    }

    public Suit getSuit()
    {
        return suit;
    }

    @Override
    public int compareTo(Card o)
    {
        return Comparator.comparing(Card::getSuit).thenComparing(Card::getRank).compare(this, o);
    }

    public boolean isDiamonds()
    {
        return this.suit == Suit.DIAMONDS;
    }

    public boolean isHearts()
    {
        return this.suit == Suit.HEARTS;
    }

    public boolean isSpades()
    {
        return this.suit == Suit.SPADES;
    }

    public boolean isClubs()
    {
        return this.suit == Suit.CLUBS;
    }

    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Card))
        {
            return false;
        }
        Card card = (Card) object;
        return rank == card.rank && suit == card.suit;
    }

    public int hashCode()
    {
        int result = 31 + rank.hashCode();
        result = 31 * result + suit.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return rank + " of " + suit;
    }
}
