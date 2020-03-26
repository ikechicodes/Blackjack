/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ikechi Iwuagwu
 */
public class Game
{

    private int numberOfPlayers;           // The number of players.
    private ArrayList<Player> players;     // An ArrayList of player objects.

    /**
     * This constructor accepts no arguments.
     */
    public Game() throws Exception
    {
        players = new ArrayList<Player>();

        // Prompt for the number of players.
        System.out.print("Enter the number of players: ");

        // Scanner object to read keyboard input.
        Scanner in = new Scanner(System.in);
        
        numberOfPlayers = in.nextInt();

        while (numberOfPlayers < 2 || numberOfPlayers > 7)
        {
            System.out.println("Number of players must be between 2 and 7.");
            // Prompt for the number of players.
            System.out.print("Enter the number of players: ");
            numberOfPlayers = in.nextInt();
        }

        // Create Player objects, Dealer is player 0.
        Dealer dealer = new Dealer();

        for (int index = 0; index < numberOfPlayers; index++)
        {
            // The Player constructor accepts the player number, which starts
            // at 1 for everyone who is not the dealer.
            players.add(new Player(index + 1));
        }

        // Gather the bets from each player.
        for (Player player : players)
        {
            System.out.printf("Player %d, enter your bet: $",
                    player.getPlayerNumber());

            player.setBet(in.nextDouble());

        }

        // The dealer should distribute the cards to each player.
        dealer.dealCards(players);

        // The dealer should prompt each player whether they want to hit or
        // stand.
        for (Player player : players)
        {
            boolean busted = dealer.promptForHitOrStand(player);

        }

        // Remove all busted players from the game by removing them from 
        // ArrayList.
        for (int index = 0; index < players.size(); index++)
        {
            if (players.get(index).getBustStatus())
            {
                players.remove(index);
            }
        }

        // After, the dealer's hole card (second card) is revealed.
        dealer.revealHolecard();

        // If the total of the dealer's two cards is 17 or more, they must
        // stand. If the total is under 17, they must keep taking cards until
        // their total value is at least 17.
        dealer.continuousDraw();

        // If the dealer busts, all remaining players win their bets.
        if (dealer.getBustStatus())
        {
            for (Player player : players)
            {
                if (!player.getBustStatus() && !player.getBlackjack())
                {
                    player.addToPot(player.getBet());
                }
            }

        } else
        {
            // If the dealer does not bust, all active players with higher 
            // totals, win their bets.    
            int playerLength, dealerLength;

            for (Player player : players)
            {
                if (!player.getBustStatus())
                {

                    playerLength = player.getHand().getValue().length;
                    dealerLength = dealer.getHand().getValue().length;

                    int smallerPlayerTotal = player.getHand().getValue()[0];
                    int largerPlayerTotal = player.getHand().
                            getValue()[playerLength - 1];

                    int smallerDealerTotal = dealer.getHand().getValue()[0];
                    int largerDealerTotal = dealer.getHand().
                            getValue()[dealerLength - 1];

                    // If player's and dealer's larger total is under 21, 
                    // use it to compare.
                    if (largerPlayerTotal <= 21 && largerDealerTotal <= 21)
                    {
                        if (largerPlayerTotal > largerDealerTotal)
                        {
                            System.out.printf("Player %d's hand (%d) beats the "
                                    + "dealer's hand (%d).\n Player %d's bet ($%,.2f)"
                                    + " has been added to their pot.\n",
                                    player.getPlayerNumber(), largerPlayerTotal,
                                    largerDealerTotal, player.getPlayerNumber(),
                                    player.getBet());

                            player.addToPot(player.getBet());
                            // players.remove(player);
                        } else
                        {
                            System.out.printf("The dealer's hand (%d) beats "
                                    + "Player %d's hand(%d).\n Player %d's bet "
                                    + "($%,.2f) has been added to the dealer's pot.\n",
                                    largerDealerTotal, player.getPlayerNumber(),
                                    largerPlayerTotal, player.getPlayerNumber(),
                                    player.getBet());
                            dealer.addToPot(player.getBet());
                            // players.remove(player);
                        }
                    } else if (largerPlayerTotal > 21 && largerDealerTotal > 21)
                    {
                        if (smallerPlayerTotal > largerDealerTotal)
                        {
                            System.out.printf("Player %d's hand (%d) beats the "
                                    + "dealer's hand (%d).\n Player %d's bet ($%,.2f)"
                                    + " has been added to their pot.\n",
                                    player.getPlayerNumber(), smallerPlayerTotal,
                                    smallerDealerTotal, player.getPlayerNumber(),
                                    player.getBet());

                            player.addToPot(player.getBet());
                            // players.remove(player);
                        } else
                        {
                            System.out.printf("The dealer's hand (%d) beats "
                                    + "Player %d's hand(%d).\n Player %d's bet "
                                    + "($%,.2f) has been added to the dealer's pot.\n",
                                    smallerDealerTotal, player.getPlayerNumber(),
                                    smallerPlayerTotal, player.getPlayerNumber(),
                                    player.getBet());
                            dealer.addToPot(player.getBet());
                            // players.remove(player);
                        }
                    } else if (largerPlayerTotal <= 21 && largerDealerTotal > 21)
                    {
                        if (largerPlayerTotal > smallerDealerTotal)
                        {
                            System.out.printf("Player %d's hand (%d) beats the "
                                    + "dealer's hand (%d).\n Player %d's bet ($%,.2f)"
                                    + " has been added to their pot.\n",
                                    player.getPlayerNumber(), largerPlayerTotal,
                                    smallerDealerTotal, player.getPlayerNumber(),
                                    player.getBet());

                            player.addToPot(player.getBet());
                            //  players.remove(player);
                        } else
                        {
                            System.out.printf("The dealer's hand (%d) beats "
                                    + "Player %d's hand(%d).\n Player %d's bet "
                                    + "($%,.2f) has been added to the dealer's pot.\n",
                                    smallerDealerTotal, player.getPlayerNumber(),
                                    largerPlayerTotal, player.getPlayerNumber(),
                                    player.getBet());
                            dealer.addToPot(player.getBet());
                            // players.remove(player);
                        }
                    } else
                    {
                        if (smallerPlayerTotal > largerDealerTotal)
                        {
                            System.out.printf("Player %d's hand (%d) beats the "
                                    + "dealer's hand (%d).\n Player %d's bet ($%,.2f)"
                                    + "has been added to their pot.",
                                    player.getPlayerNumber(), smallerPlayerTotal,
                                    largerDealerTotal, player.getPlayerNumber(),
                                    player.getBet());

                            player.addToPot(player.getBet());
                            // players.remove(player);
                        } else
                        {
                            System.out.printf("The dealer's hand (%d) beats "
                                    + "Player %d's hand(%d).\n Player %d's bet "
                                    + "($%,.2f) has been added to the dealer's pot.",
                                    largerDealerTotal, player.getPlayerNumber(),
                                    smallerPlayerTotal, player.getPlayerNumber(),
                                    player.getBet());
                            dealer.addToPot(player.getBet());
                            // players.remove(player);
                        }

                    }

                }
            }
        }

    }

}
