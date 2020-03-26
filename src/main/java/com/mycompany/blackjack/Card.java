/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class models a standard playing card.
 *
 * @author Ikechi Iwuagwu
 */
public class Card
{
    private String rank;    // The value of the card.
    private String suit;    // The suit of the card.
    private final ArrayList<String> validSuits = new ArrayList<String>
        (Arrays.asList("SPADES", "HEARTS", "DIAMONDS", "CLUBS"));
    private final ArrayList<String> validRanks = new ArrayList<String> 
        (Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", 
                "JACK", "QUEEN", "KING", "ACE"));
    
    /**
     * This constructor accepts the suit and value of the card
     * as arguments and initializes the object's fields.
     * @param rank The value of the card.
     * @param suit The suit of the card.
     */
    
    public Card(String rank, String suit) throws IllegalArgumentException
    {
        // Throw an error if the constructor i passed an invalid rank.
        if (validRanks.contains(rank.trim().toUpperCase()))
            this.rank = rank.trim().toUpperCase();
        else
            throw new IllegalArgumentException("INVALID CARD RANK");
        
        // Throw an error if the constructor is passed an invalid suit.
        if (validSuits.contains(suit.trim().toUpperCase()))
            this.suit = suit.trim().toUpperCase();
        else
            throw new IllegalArgumentException("INVALID CARD SUIT");
    }
    
    /**
     * This method retrieves the value stored in the rank field.
     * @return The rank of the card.
     */
    
    public String getRank()
    {
        return rank;
    }
    
    /**
     * This method retrieves the value stored in the suit field.
     * @return The suit of the card.
     */
    
    public String getSuit()
    {
        return suit;
    }
    
    
}
