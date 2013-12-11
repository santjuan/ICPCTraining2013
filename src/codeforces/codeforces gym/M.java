import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class M {
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
	static int []next;
	static int []last;
	static int []nums;
	static int []dp;
	static int n;
	
	public static void main(String[] args)
	{	
		Scanner sc = new Scanner();
		n = sc.nextInt();
		nums = new int [n];
		next = new int [n];
		dp = new int [n];
		last = new int [200001];
		Arrays.fill(next, (1<<30));
		Arrays.fill(last, (1<<30));
		for(int i=0;i<n;++i)
			nums[i] = sc.nextInt();
		
		for(int i=n-1;i>=0;i--){
			next[i] = last[nums[i]];
			last[nums[i]] = i;
		}
		Arrays.fill(dp, -1);
		System.out.println(solve(0));
	}
	
	static int solve(int pos){
		if(pos==n-1)return 0;
		if(dp[pos]!=-1) return dp[pos];
		if(next[pos]==(1<<30)) return dp[pos]=(solve(pos+1)+1);
		return dp[pos]=Math.min(solve(pos+1),solve(next[pos]))+1;	
	}
}
