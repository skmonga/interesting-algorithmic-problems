package bot;


/**
 * Refer to: https://www.hackerrank.com/challenges/saveprincess
 *
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SavePrincess {
    
    static class Position {
        int x;
        int y;
        public Position() {
            
        }
    }
    
  static Position findPosition(int n, String [] grid,char ch) {
      Position pos = new Position();
      for(int i = 0; i < n; i++) {
          for(int j = 0; j < n; j++) {
              if(grid[i].charAt(j) == ch) {
            	  pos.x = i;
            	  pos.y = j;
            	  return pos;
              }
          }
      }
      return pos;
  }
    
static void displayPathtoPrincess(int n, String [] grid){
    Position bot = findPosition(n,grid,'m');
    Position princess = findPosition(n,grid,'p');
    int xdiff = 0,ydiff = 0;
    String xMove = "UP",yMove="LEFT";
    xdiff = bot.x - princess.x;
    ydiff = bot.y - princess.y;
    if(xdiff > 0) {
    	xMove = "UP";
    } else if(xdiff < 0){
    	xMove = "DOWN";
    	xdiff = -xdiff;
    }
    if(ydiff > 0) {
    	yMove = "LEFT";
    } else if(ydiff < 0){
    	yMove = "RIGHT";
    	ydiff = -ydiff;
    }
    
    for(int i = 1; i <= xdiff; i++)
    	System.out.println(xMove);
    for(int i = 1; i <= ydiff; i++)
    	System.out.println(yMove);
  }
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m;
        m = in.nextInt();
        String grid[] = new String[m];
        for(int i = 0; i < m; i++) {
            grid[i] = in.next();
        }

    displayPathtoPrincess(m,grid);
    }
}
