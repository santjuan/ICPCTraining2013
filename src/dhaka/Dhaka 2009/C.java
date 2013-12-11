import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class C 
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
	
	static int N, S, W;
	static BitSet[] keywords;
	static Integer[] dp;
	
	static int dp(int current)
	{
		if(current >= N)
			return 0;
		if(dp[current] != null)
			return dp[current];
		BitSet actual = new BitSet(100);
		int best = Integer.MAX_VALUE;
		for(int i = current; i < N; i++)
		{
			int size = i - current + 1;
			actual.or(keywords[i]);
			int footNotes = actual.cardinality();
			if(size + footNotes > S)
				break;
			int sig = dp(i + 1);
			if(sig != Integer.MAX_VALUE)
				best = Math.min(best, footNotes + sig);
		}
		return dp[current] = best;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int T = sc.nextInt();
		for(int caso = 1; caso <= T; caso++)
		{
			N = sc.nextInt();
			S = sc.nextInt();
			W = sc.nextInt();
			dp = new Integer[N];
			keywords = new BitSet[N];
			for(int i = 0; i < keywords.length; i++)
				keywords[i] = new BitSet(100);
			for(int i = 0; i < W; i++)
			{
				int frec = sc.nextInt();
				for(int j = 0; j < frec; j++)
					keywords[sc.nextInt() - 1].set(i);
			}
			int ans = dp(0);
			if(ans == Integer.MAX_VALUE)
				ans = -1;
			System.out.println("Case " + caso + ": " + ans);
		}
	}
}