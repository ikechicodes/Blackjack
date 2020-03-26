
import com.mycompany.blackjack.Game;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ikechi Iwuagwu
 */
public class GameRunner
{
    /**
     * The main method.
     */
    
    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        char response = 'N';
        do
        {
            Game game = new Game();
            System.out.print("\nPlay again[Y/N]: ");
            response = Character.valueOf(in.nextLine().toUpperCase().charAt(0));
            
        }
        while (response == 'Y');
        
    }
    
}
