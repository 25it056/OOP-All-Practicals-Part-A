public class Driver {
    public static void main(String[] args) {
        Card[] input = {
            new Card("Ace", "Spades"), new Card("King", "Hearts"),
            new Card("Queen", "Diamonds"), new Card("Ace", "Spades")
        };
        Card[] added = new Card[input.length];
        int size = 0;
        for (Card card : input) {
            boolean duplicate = false;
            for (int i = 0; i < size; i++) {
                if (card.equals(added[i])) { duplicate = true; break; }
            }
            if (duplicate) {
                System.out.println("Duplicate found: " + card);
                break;
            }
            added[size++] = card;
        }
    }
}
