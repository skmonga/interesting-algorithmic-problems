package bot;

import java.util.Scanner;

public class SavePrincess2 {

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
    
static void nextMove(int n, int r, int c, String [] grid){
	Position bot = new Position();
    bot.x = r;
    bot.y = c;
    Position princess = findPosition(n, grid, 'p');
    int xdiff = Math.abs(bot.x - princess.x);
    if(xdiff != 0) {
    	System.out.println((bot.x > princess.x)?"UP":"DOWN");
    } else if(Math.abs(bot.y - princess.y) != 0){
    	System.out.println((bot.y > princess.y)?"LEFT":"RIGHT");
    }
  }
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n,r,c;
        n = in.nextInt();
        r = in.nextInt();
        c = in.nextInt();
        in.useDelimiter("\n");
        String grid[] = new String[n];


        for(int i = 0; i < n; i++) {
            grid[i] = in.next();
        }

    nextMove(n,r,c,grid);
    in.close();
    }

}

