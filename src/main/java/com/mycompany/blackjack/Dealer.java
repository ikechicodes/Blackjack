/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Ikechi Iwuagwu
 */
public class Dealer extends Player
{
    private CardDeck cardDeck;  // To reference an object of type CardDeck.
    
    /**
     * This constructor accepts no arguments.
     */
    
    public Dealer()
    {
        super(0);
        cardDeck = new CardDeck();
    }
    
    /**
     * This method models dealing cards to the players.
     * @param players An array of references to Player objects.
     * 
     */
    
    public void dealCards(ArrayList<Player> players) throws Exception
    {
        boolean playerBlackjack = false, dealerBlackjack = false;
        
        // For each player in the players array, deal each two cards.
        for (Player player: players)
        {
            Card card1, card2;
            
            // Remove two cards from the card deck and add them to the player's 
            // hand and reveal the card to standard output.
            card1 = cardDeck.drawCard();
            player.addToHand(card1);
            System.out.println("Player " + player.getPlayerNumber() + ": "
                    + card1.getRank() + " " + card1.getSuit());
            
            card2 = cardDeck.drawCard();
            player.addToHand(card2);
            System.out.println("Player " + player.getPlayerNumber() + ": " 
                    + card2.getRank() + " " + card2.getSuit());
          
        }
        
        // Add two cards to the dealer's hand, but only reveal the first.
        Card upcard = cardDeck.drawCard();
        this.addToHand(upcard);
        System.out.println("Dealer's Upcard: " + upcard.getRank() + " " +
                upcard.getSuit());
        
        // If any player has a blackjack (total of 21) in first two cards
        // and dealer does not, add one and a half times the player's bet
        // to their jackpot.
        for (Player player: players)
        {
            int length = player.getHand().getValue().length;
            
            if (player.getHand().getValue()[0] == 21 ||
                    player.getHand().getValue()[length - 1] == 21)
            {
                playerBlackjack = true;
                System.out.printf("Player %d has a blackjack!\n", 
                        player.getPlayerNumber());
            }
            
            // Check if the dealer has a blackjack.
            length = this.getHand().getValue().length;

            if (this.getHand().getValue()[0] == 21 ||
                    this.getHand().getValue()[length - 1] == 21)
            {
                dealerBlackjack = true;
                System.out.printf("The dealer has a blackjack!\n");
            }
            
            // If the player has a blackjack and the dealer does not, add 1.5
            // times their bet to their pot.
            if (playerBlackjack && !dealerBlackjack)
            {
                player.addToPot(player.getBet() * 1.5);
                System.out.printf("1.5 times Player %d's bet has been added" +
                        " to their pot.\n", player.getPlayerNumber());
                System.out.printf("Player %d's Current Pot: $%,.2f\n", 
                        player.getPlayerNumber(), player.getPot());
                
                // Set this player's blackjack field to true.
                player.setBlackjack();
            }
                
            
            // If the dealer has a blackjack and the player does not, add the
            // player's bet to the dealer's pot.
            if (dealerBlackjack && !playerBlackjack)
            {
                this.addToPot(player.getBet());
                System.out.printf("Player %d's bet has been added to the " +
                        "dealers's pot.\n", player.getPlayerNumber());
                System.out.printf("Dealer's Current Pot: $%,.2f\n", 
                        this.getPot());
            }
                
            
            // If the dealer and player both have a blackjack, it is a standoff
            // and nothing happens.
            if (dealerBlackjack && playerBlackjack)
                System.out.println("Standoff!");
        }
       
        
        Card holecard = cardDeck.drawCard();
        this.addToHand(holecard);
    }
    
    /**
     * This method models revealing the dealer's upcard.
     * @throws Exception
     */
    
    public void revealHolecard() throws Exception
    {
        // Display the first card of the dealer's hand to standard output.
        int length = this.getHand().getValue().length;
        Card holecard = this.getHand().getCard(length - 1);
        System.out.println("Dealer's Holecard: " + holecard.getRank() + " " +
                holecard.getSuit());
    }
    
    /**
     * This method models prompting players for a hit or stand.
     * @param player A player object.
     */
    
    public boolean promptForHitOrStand(Player player) throws Exception
    {
        String hit = "hit";
        String response = "";
        Scanner in = new Scanner(System.in);
        // ArrayList<Player> bustedPlayers = new ArrayList<Player>();
        // int length; // The amount of cards in the player's hand.
        // When I define length outside the while loop, I receive an error
        // for bad local variable, so I am defining and initializing in while
        // loop.
        
        
        System.out.print("Player " + player.getPlayerNumber() + ", ");
        System.out.print("Hit or stand? ");
        response = in.nextLine().trim();
      
        while (hit.contains(response) && player.getHand().getValue()[0] < 21)
        {
            // The amount of cards in the player's hand. This line
            // must be in the while loop because, it will continue
            // to increase when the player chooses to hit.
            // Wait..this won't continue to increase as this only
            // hold a two value array with both of the player's totals.
            int length = player.getHand().getValue().length;
            
            // Draw a card from the deck and give it to plater.
            Card card = cardDeck.drawCard();
            player.addToHand(card);
            
            // Display the card drawn to standard output.
            System.out.println("Player " + player.getPlayerNumber() + ": " 
                    + card.getRank() + " " + card.getSuit());
            
            
            // If the player goes over 21, they bust, and the dealer collects
            // their bet.
            if (player.getHand().getValue()[0] > 21 && 
                    player.getHand().getValue()[length - 1] > 21)
            {
                System.out.println("Player " + player.getPlayerNumber() + 
                        " busts!\n");
                
                player.setBusted();
                
                System.out.printf("Dealer will collect Player %d's bet ($%,.2f)" +
                        ".\n", player.getPlayerNumber(),
                        player.getBet());
                
                this.addToPot(player.getBet());
            }
           
            // Since a card has been added to their hand, update length.
            length = player.getHand().getValue().length;
            // Prompt the player again if they haven't busted and their total
            // is not 21.
            
            /*
            For Testing
            System.out.printf("Player %d Bust?: %b", player.getPlayerNumber(), player.getBustStatus());
            System.out.println("Hand Value 1: " + player.getHand().getValue()[0]);
            System.out.println("Hand Value 2: " + player.getHand().getValue()[length - 1]);
            System.out.println("The value of length should be 2 but length is " + length);
            System.out.println("Cards in hand: " + player.getHand().getAllCards().size());
            */
            if (!player.getBustStatus() && 
                    player.getHand().getValue()[0] != 21 && 
                    player.getHand().getValue()[length - 1] != 21)
            {
                System.out.print("Player " + player.getPlayerNumber() + ", ");
                System.out.print("Hit or stand? ");
                response = in.nextLine().trim(); 
            }
            else
                response = "stand";
        }
        
        int length = player.getHand().getValue().length;
        // If the player has 21 exactly, report to standard output.
        if (player.getHand().getValue()[0] == 21 ||
                player.getHand().getValue()[length - 1] == 21)
            {
                System.out.printf("Player %d has 21!\n",
                player.getPlayerNumber());
            }
        
        // Check if the player busted outside of the while loop.
        if (player.getHand().getValue()[0] > 21 && 
            player.getHand().getValue()[length - 1] > 21  && 
                !player.getBustStatus())
            {
                System.out.println("Player " + player.getPlayerNumber() + 
                   " busts!\n");
                
                player.setBusted(); 
                System.out.printf("Dealer will add Player %d's bet ($%,.2f) " +
                "to their pot.\n", player.getPlayerNumber(),
                        player.getBet());
                
                this.addToPot(player.getBet());
            }
        
        // return bustedPlayers;
        // Return bust status of this player.
        return player.getBustStatus();
        
    }
    
    /**
     * This method simulates the dealer continually drawing cards until their
     * total has reached at least 17.
     */
    
    public void continuousDraw() throws Exception
    {
        // While every card in the dealer's had is less than 17.
        int length = this.getHand().getValue().length;
        while (this.getHand().getValue()[0]  < 17 
                && this.getHand().getValue()[length - 1] < 17)
        {
            // Draw card and add to hand.
            this.addToHand(cardDeck.drawCard());
        }
        
        // If the dealer busts, all players get their bet.
        if (this.getHand().getValue()[0]  > 21
                && this.getHand().getValue()[length - 1] > 21)
        {
            System.out.println("The dealer busts! All remaining players " +
                    "win their bets.\n");
            
            this.setBusted();
        }
        
        // Return the dealer's 
    }
    
    
    
}
