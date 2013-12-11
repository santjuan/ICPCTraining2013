import java.math.BigInteger;
import java.util.Scanner;


public class E
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		BigInteger[] fibo = new BigInteger[10001];
		fibo[0] = BigInteger.ONE;
		fibo[1] = BigInteger.ONE;
		for(int i = 2; i < 10001; i++)
			fibo[i] = fibo[i - 1].add(fibo[i - 2]);
		while(sc.hasNextInt())
		{
			int n = sc.nextInt();
			System.out.println(fibo[n].add(fibo[n - 2]));
		}
	}

}
