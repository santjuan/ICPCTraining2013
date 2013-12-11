import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class D 
{
	static class Scanner
	{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 StringTokenizer st = new StringTokenizer("");
		 
		 String nextLine()
		 {
			 try 
			 {
				 return br.readLine();
			 }
			 catch (IOException e) 
			 {
				 throw new RuntimeException(e);
			 }
		 }
		 
		 String next()
		 {
			 while(!st.hasMoreTokens())
			 {
				 String line = nextLine();
				 if(line == null) return null;
				 st = new StringTokenizer(line);
			 }
			 return st.nextToken();
		 }
		 
		 Long nextLongC()
		 {
			 String next = next();
			 if(next == null) return null;
			 return Long.parseLong(next);
		 }
	}
	
	static long[] primes = new long[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61};
	static BigInteger[] primesB = new BigInteger[primes.length];

	static BigInteger[] factorials = new BigInteger[63];
	static int[][] factoredFactorials = new int[63][primes.length];
	
	static long bestAns = Long.MAX_VALUE;
	
	static boolean isDivisible(int[] a, int[] b)
	{
		for(int i = 0; i < a.length; i++)
			if(b[i] > a[i]) return false;
		return true;
	}
	
	static void divide(int[] a, int[] b)
	{
		for(int i = 0; i < a.length; i++)
			a[i] -= b[i];
	}
	
	static void multiply(int[] a, int[] b)
	{
		for(int i = 0; i < a.length; i++)
			a[i] += b[i];
	}
	
	static void tryImprove(long number)
	{
		bestAns = Math.min(bestAns, number);
	}
	
	static long pow(long a, int b)
	{
		long acum = 1;
		for(int i = 0; i < b; i++)
		{
			if(overflows(acum, a)) return -1;
			acum *= a;
 		}
		return acum;
	}
	
	static boolean overflows(long a, long b)
	{
		return a < 0 || b < 0 || BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).compareTo(BigInteger.valueOf(Long.MAX_VALUE)) >= 0;
	}
	
	static boolean findBest(int remainingC, int[] remainingN, long solution, int highest, int currentPrime)
	{
		if(solution <= 0 || solution >= bestAns)
			return false;
		if(remainingC == 0)
		{
			if(isOne(remainingN))
			{
				tryImprove(solution);
				return true;
			}
		}
		if(currentPrime >= primesB.length)
			return false;
		int highestPrime = -1;
		for(int i = 0; i < remainingN.length; i++)
			if(remainingN[i] > 0)
				highestPrime = i;
		int start = 0;
		int end = 0;
		if(highestPrime == -1)
			start = end = 1;
		else if(highestPrime == remainingN.length - 1)
		{
			start = factoredFactorials.length - 1;
			end = (int) primes[highestPrime];
		}
		else
		{
			start = (int) (primes[highestPrime + 1] - 1);
			end = (int) primes[highestPrime];
		}
		for(int i = start; i >= end; i--)
		{
			if(remainingC - i < 0)
				continue;
			if(isDivisible(remainingN, factoredFactorials[i]))
			{
				long pow = pow(primes[currentPrime], i);
				if(pow == -1 || overflows(solution, pow)) continue;
				divide(remainingN, factoredFactorials[i]);
				if(findBest(remainingC - i, remainingN, solution * pow, i, currentPrime + 1));
				multiply(remainingN, factoredFactorials[i]);
			}
		}
		return false;
	}

	static boolean isOne(int[] n) 
	{
		for(int i : n)
			if(i != 0) return false;
		return true;
	}
	
	static long solve(int[] n)
	{
		long best = Long.MAX_VALUE;
		for(int i = 1; i < factorials.length; i++)
		{
			if((1L << i) > best) break;
			int[] current = factoredFactorials[i].clone();
			if(isDivisible(current, n))
			{
				divide(current, n);
				bestAns = Long.MAX_VALUE;
				findBest(i, current, 1, i, 0);
				if(bestAns < best)
					best = bestAns;
			}
		}
		return best;
	}
	
	static int[] factorizar(long n)
	{
		int[] ans = new int[primes.length];
		for(int i = 0; n > 1 && i < primes.length; i++)
		{
			while(n > 1 && (n % primes[i] == 0))
			{
				ans[i]++;
				n /= primes[i];
			}
		}
		return ans;
	}
	
	public static void main(String[] args)
	{
		for(int i = 0; i < primes.length; i++)
			primesB[i] = BigInteger.valueOf(primes[i]);
		factorials[0] = BigInteger.ZERO;
		for(int i = 1; i < factorials.length; i++)
			for(int j = 1; j <= i; j++)
				multiply(factoredFactorials[i], factorizar(j));
		Scanner sc = new Scanner();
		while(true)
		{
			Long k = sc.nextLongC();
			if(k == null) return;
			long ans = 0;
			if(k == 0)
				throw(new RuntimeException());
			else
				ans = solve(factorizar(k));
			System.out.println(k + " " + ans);
		}
 	}
}
