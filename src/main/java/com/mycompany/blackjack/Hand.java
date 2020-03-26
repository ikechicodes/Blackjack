/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack;

import java.util.ArrayList;

/**
 * This class models the hand of a player or dealer.
 * The dealer and each player should have a hand.
 *
 * @author Ikechi Iwuagwu
 */
public class Hand
{
    private ArrayList<Card> hand; // A hand of cards.
    // private int total, total2;    // The totals values of hand.
    
    /**
     * This constructor accepts no arguments.
     */
    
    public Hand()
    {
        hand = new ArrayList<Card>();
        
        
    }
    
    /**
     * This method models adding a card to the hand.
     * @param card An object of type card.
     */
    
    public void insertCard(Card card)
    {
        hand.add(card);
    }
    
    /**
     * This method retrieves a card from the Hand.
     * @param index The index of the card.
     * @return The card at the given index.
     */
    
    public Card getCard(int index)
    {
        return hand.get(index);
    }
    
    /**
     * This method retrieves the total value of a player's hand.
     */
    
    public int[] getValue() throws Exception
    {
       // For each card in the ArrayList of Cards, add all of the values.
        int total = 0;
        int total2 = 0;
        
        for (Card card: hand)
        {
            switch (card.getRank())
            {
                case "2":
                    total = total + 2;
                    total2 = total2 + 2;
                    break;
                case "3":
                    total = total + 3;
                    total2 = total2 + 3;
                    break;
                case "4":
                    total = total + 4;
                    total2 = total2 + 4;
                    break;
                case "5":
                    total = total + 5;
                    total2 = total2 + 5;
                    break;
                case "6":
                    total = total + 6;
                    total2 = total2 + 6;
                    break;
                case "7":
                    total = total + 7;
                    total2 = total2 + 7;
                    break;
                case "8":
                    total = total + 8;
                    total2 = total2 + 8;
                    break;
                case "9":
                    total = total + 9;
                    total2 = total2 + 9;
                    break;
                case "10":
                case "JACK":
                case "QUEEN":
                case "KING":
                    total = total + 10;
                    total2 = total2 + 10;
                    break;
                case "ACE":
                    total = total + 1;
                    total2 = total2 + 11;
                    break;
                default:
                    throw new Exception("ERROR ENCOUNTERED WHILE RETRIEVING " +
                            "HAND VALUE");
 
            }
        }
        
        // Return a one argument array if both totals are the same value or if
        // total2 is over 21, otherwise return both totals.
        if (total == total2 || total2 > 21)
        {
            int[] handValue = {total};
            return handValue;
        }
        else
        {
            int[] handValues = {total, total2};
            return handValues;
        }
    }
    
    /**
     * This method returns the ArrayList of cards in a player's hand.
     * @return The player's hand of cards.
     */
    
    public ArrayList<Card> getAllCards()
    {
        return hand;
    }
    
    
    
}
