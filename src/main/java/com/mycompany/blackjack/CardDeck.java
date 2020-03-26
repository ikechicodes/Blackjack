/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class models a standard 52-card playing deck.
 *
 * @author Ikechi Iwuagwu
 */
public class CardDeck
{
    private final ArrayList<Card> cardDeck = new ArrayList<Card>();
    
    /**
     * This constructor accepts no arguments.
     */
    
    public CardDeck()
    {
        // Add the standard 52 playing cards to the card deck from file.
        try
        {
            File file = new File("52CardDeck.txt");
            Scanner inputFile = new Scanner(file);
            
            while (inputFile.hasNext())
            {
                // Create a Card object and add to cardDeck ArrayList.
                String[] values = inputFile.nextLine().split(" ");
                Card card = new Card(values[0], values[values.length - 1]);
                cardDeck.add(card);
            }
            
            // Shuffle the card deck.
            Collections.shuffle(cardDeck);
         
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
     * This method models drawing a card from the card deck.
     * @return The card that was drawn.
     */
    
    public Card drawCard()
    {
        Card drawnCard = cardDeck.get(0);
        
        // Remove the card from the card deck and return the card.
        cardDeck.remove(0);
        
        return drawnCard;
    }
    
    /**
     * This method models shuffling the card deck,
     */
    
    public void shuffle()
    {
        Collections.shuffle(cardDeck);
    }
    
    
}
