package collections.compare.demo.cards;

import java.util.Random;
import java.util.Set;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.primitive.IntInterval;

public class EclipseCollectionsDeckOfCardsAsReadableList
{
    private ListIterable<Card> cards;
    private ListMultimap<Suit, Card> cardsBySuit;

    public EclipseCollectionsDeckOfCardsAsReadableList()
    {
        this.cards = Lists.mutable.with(
                Card.streamCards()
                        .toArray(Card[]::new))
                .sortThis()
                .asUnmodifiable();
        this.cardsBySuit = this.cards.groupBy(Card::getSuit);
    }

    public MutableStack<Card> shuffle(Random random)
    {
        return this.cards
                .toList()
                .shuffleThis(random)
                .shuffleThis(random)
                .shuffleThis(random)
                .toStack();
    }

    public MutableSet<Card> deal(MutableStack<Card> stack, int count)
    {
        return stack.pop(count).toSet();
    }

    public Card dealOneCard(MutableStack<Card> stack)
    {
        return stack.pop();
    }

    public ListIterable<Set<Card>> shuffleAndDeal(Random random, int hands, int cardsPerHand)
    {
        MutableStack<Card> shuffled = this.shuffle(random);
        return this.dealHands(shuffled, hands, cardsPerHand);
    }

    public ListIterable<Set<Card>> dealHands(MutableStack<Card> shuffled, int hands, int cardsPerHand)
    {
        MutableList<Set<Card>> result = Lists.mutable.empty();
        IntInterval.oneTo(hands)
                .forEach(i -> result.add(this.deal(shuffled, cardsPerHand)));
        return result.asUnmodifiable();
    }

    public ListIterable<Card> diamonds()
    {
        return this.cardsBySuit.get(Suit.DIAMONDS);
    }

    public ListIterable<Card> hearts()
    {
        return this.cardsBySuit.get(Suit.HEARTS);
    }

    public ListIterable<Card> spades()
    {
        return this.cardsBySuit.get(Suit.SPADES);
    }

    public ListIterable<Card> clubs()
    {
        return this.cardsBySuit.get(Suit.CLUBS);
    }

    public Bag<Suit> countsBySuit()
    {
        return this.cards.asLazy().collect(Card::getSuit).toBag();
    }

    public Bag<Rank> countsByRank()
    {
        return this.cards.asLazy().collect(Card::getRank).toBag();
    }

    public ListIterable<Card> getCards()
    {
        return this.cards;
    }

    public ListMultimap<Suit, Card> getCardsBySuit()
    {
        return this.cardsBySuit;
    }
}
