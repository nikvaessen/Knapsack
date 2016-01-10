/**
 * Created by baxie on 10-1-16.
 */
public class Tester
{
    public static void main(String[] argv)
    {
        int spaces = 4;
        for(int i = 0; i < 5; i++)
        {
            double money = Math.pow(10 , i);
            int spacer = 0;
            int size = (int)money;
            while(size > 0)
            {
                size = size / 10;
                spacer++;
            }
            System.out.printf("I have %*f euros :)\n", spacer, money);
        }
    }

}
