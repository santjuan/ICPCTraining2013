import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class L 
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
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		long anterior = 0;
		long actual = sc.nextLong();
		for(int i = 0; i < n; i++)
		{
			if(i != 0)
				System.out.print(" ");
			System.out.print(actual - anterior);
			if(i == n - 1)
				break;
			anterior = actual;
			actual = sc.nextLong();
		}
		System.out.println();
	}
}