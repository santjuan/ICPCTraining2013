import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class A 
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
	
	static class Team implements Comparable <Team>
	{
		int howMany;
		int fromA;
		int fromB;
		
		public Team(int howMany, int fromA, int fromB) 
		{
			this.howMany = howMany;
			this.fromA = fromA;
			this.fromB = fromB;
		}
		
		int difference()
		{
			return Math.max(fromA, fromB) - Math.min(fromA, fromB);
		}

		@Override
		public int compareTo(Team o)
		{
			return o.difference() - difference();
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			if(n == 0 && a == 0 && b == 0)
				return;
			Team[] teams = new Team[n];
			for(int i = 0; i < n; i++)
				teams[i] = new Team(sc.nextInt(), sc.nextInt(), sc.nextInt());
			Arrays.sort(teams);
			int cost = 0;
			for(Team t : teams)
			{
				while(t.howMany != 0)
				{
					if(b == 0 || (t.fromA <= t.fromB && a != 0))
					{
						cost += t.fromA;
						a--;
					}
					else
					{
						cost += t.fromB;
						b--;
					}
					t.howMany--;
				}
			}
			System.out.println(cost);
		}
	}

}
