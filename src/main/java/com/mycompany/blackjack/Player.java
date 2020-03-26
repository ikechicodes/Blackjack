/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack;

/**
 *
 * @author Ikechi Iwuagwu
 */
public class Player
{
    private Hand hand;          // To reference an object of type Hand.
    private int playerNumber;   // The player number, the dealer is Player 0.
    private double bet;         // To represent the cash value of a player's 
                                // bet.
    private double pot;         // The total amount of money the player has won.
    private boolean busted;     // Boolean that signifies whether a player is
                                // out of the game.
    private boolean blackjack;  // Boolean that signifies whether a player has
                                // a 21 on first draw.
    
    /**
     * This constructor accepts no arguments.
     */
    
    public Player(int playerNumber)
    {
        hand = new Hand();
        this.playerNumber = playerNumber;
        busted = false;
    }
    
    /**
     * This method models adding a card to your hand.
     */
    
    public void addToHand(Card card)
    {
        hand.insertCard(card);
    }
    
    /**
     * This method retrieves the object stored in the hand field.
     */
    
    public Hand getHand()
    {
        return hand;
    }
    
    /**
     * This method retrieves the player number.
     */
    
    public int getPlayerNumber()
    {
        return playerNumber;
    }
    
    /**
     * This method models making a bet.
     * @param double The cash value of a bet.
     */
    
    public void setBet(double bet)
    {
        this.bet = bet;
    }
    
    /**
     * This method retrieves value stored in the bet field.
     * @return This player's bet.
     */
    
    public double getBet()
    {
        return bet;
    }
    
    /**
     * This method models hitting (asking for another card in an attempt to
     * get closer to a total value of 21 or actually reach 21.) When this
     * method is called, the dealer should draw a card and
     * insert it into the players hand.
     */
    
    public void hit()
    {
        //
    }
    
    /**
     * This method models adding money to the pot.
     * @param value The money value to be added to this player's pot.
     */
    
    public void addToPot(double value)
    {
        pot = pot + value;
    }
    
    /**
     * This method stores a value in the pot field.
     * @param pot The pot.
     */
    
    public void setPot(double pot)
    {
        this.pot = pot;
    }
    
    /**
     * This method returns the value stored in the pot field.
     * @return This player's pot.
     */
    
    public double getPot()
    {
        return pot;
    }
    
    /**
     * This method stores a value in the busted field.
     */
    
    public void setBusted()
    {
        busted = true;
    }
    
    /**
     * This method retrieves the value stored in the busted field.
     * @return A Boolean value signifying whether a player is still in the game
     *  or out (busted).
     */
    
    public boolean getBustStatus()
    {
        return busted;
    }
    
    /**
     * This method stores a value in the blackjack field.
     */
    
    public void setBlackjack()
    {
        blackjack = true;
    }
    
    /**
     * This method retrieves the value stored in the blackjack field.
     * @return A Boolean value signifying whether a player has a blackjack
     * (a 21 in first two cards).
     */
    
    public boolean getBlackjack()
    {
        return blackjack;
    }
    
    
}
