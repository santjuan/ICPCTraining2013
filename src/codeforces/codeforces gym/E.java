import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class E {
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
		
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		a/=100.0;
		b/=100.0;
		System.out.println(((a*a)/(a+b)) + ((b*b)/(a+b)));
		
	}
}
