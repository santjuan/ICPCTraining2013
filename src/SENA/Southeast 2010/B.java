import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class B 
{
	static class Scanner
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		public String nextLine()
		{
			try
			{
				return br.readLine();
			}
			catch(Exception e)
			{
				throw(new RuntimeException());
			}
		}
		
		public String next()
		{
			while(!st.hasMoreTokens())
			{
				String l = nextLine();
				if(l == null)
					return null;
				st = new StringTokenizer(l);
			}
			return st.nextToken();
		}
		
		public int nextInt()
		{
			return Integer.parseInt(next());
		}
		
		public long nextLong()
		{
			return Long.parseLong(next());
		}
		
		public double nextDouble()
		{
			return Double.parseDouble(next());
		}
	}
	
	static Integer[] dpK = new Integer[80];
	
	static int dpK(int number)
	{
		if(dpK[number] != null)
			return dpK[number];
		if(number == 1)
			return dpK[number] = 0;
		return dpK[number] = 1 + dpK(Integer.bitCount(number));
	}
	
	static char[] actual;
	static Long[][][] dp;
	static int k;
	
	
	static long countUpTo(long val, int cualK)
	{
		actual = Long.toBinaryString(val).toCharArray();
		dp = new Long[actual.length + 1][2][64];
		k = cualK;
		return dp(0, true, 0);
	}
	static long dp(int digit, boolean tope, int count)
	{
		if(dp[digit][tope ? 1 : 0][count] != null)
			return dp[digit][tope ? 1 : 0][count];
		if(digit == actual.length)
		{
			if(count == 0)
				return dp[digit][tope ? 1 : 0][count] = 0L;
			else
				return dp[digit][tope ? 1 : 0][count] = ((dpK(count) + 1) == k) ? 1L : 0L;
		}
		int limite = tope ? actual[digit] - '0' : 1;
		long total = 0;
		for(int i = 0; i <= limite; i++)
			total += dp(digit + 1, tope && i == limite, count + i);
		return dp[digit][tope ? 1 : 0][count] = total;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			long a = sc.nextLong();
			long b = sc.nextLong();
			int x = sc.nextInt();
			if(a == 0 && b == 0 && x == 0)
				return;
			long first = countUpTo(b, x);
			long second = a == 1 ? 0L : countUpTo(a - 1, x);
			long ans = first - second;
			if(x == 1 && 1 >= a && 1 <= b)
				ans--;
			else if(x == 0 && 1 >= a && 1 <= b)
				ans++;
			System.out.println(ans);
		}
	}
}