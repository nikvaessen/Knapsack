/**
 * Created by Stefan K on 18.01.2016.
 */
public class PerfectPento
{

    int[][][] container = new int[33][5][8] ;
    int i , j , k = 0 ;

    public static void main(String[] args)
    {
        PerfectPento p = new PerfectPento() ;
        p.perfectSolution() ;

        int x ;
        int y = 0;
        int z ;

        for(z = 0 ; z < p.container[0][0].length ; z++)
        {
            for(x = 0 ; x < p.container.length ; x++)
            {
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y = 0 ;
                x++ ;
                System.out.println() ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y = 0 ;
                x++ ;
                System.out.println() ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y++ ;
                System.out.print(p.container[x][y][z]) ;
                y = 0 ;
                System.out.println();
            }
            System.out.println() ;
            System.out.println("next layer") ;
            System.out.println() ;
        }

        System.out.println("Finished print!") ;
    }

   public void perfectSolution()
   {
       for(k = 0 ; k < container[0][0].length ; k++)
       {
           for(i = 0; i < container.length; i++)
           {
               container[i][j][k] = 99 ;
               j++ ;
               container[i][j][k] = 98 ;
               j++ ;
               container[i][j][k] = 98 ;
               j++ ;
               container[i][j][k] = 98 ;
               j++ ;
               container[i][j][k] = 99 ;
               j = 0 ;
               i++ ;
               container[i][j][k] = 99 ;
               j++ ;
               container[i][j][k] = 99 ;
               j++ ;
               container[i][j][k] = 98 ;
               j++ ;
               container[i][j][k] = 99 ;
               j++ ;
               container[i][j][k] = 99 ;
               j = 0 ;
               i++ ;
               container[i][j][k] = 99 ;
               j++ ;
               container[i][j][k] = 99 ;
               j++ ;
               container[i][j][k] = 98 ;
               j++ ;
               container[i][j][k] = 99 ;
               j++ ;
               container[i][j][k] = 99 ;
               j = 0 ;
           }

       }
   }
}
