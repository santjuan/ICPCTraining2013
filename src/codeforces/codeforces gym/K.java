import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class K 
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
		String s = sc.nextLine();
		int cuenta = 0;
		boolean paila = false;
		for(char c : s.toCharArray())
		{
			cuenta += c == '(' ? 1 : -1;
			if(cuenta < 0)
				paila = true;
		}
		if(paila)
			System.out.println("IMPOSSIBLE");
		else
		{
			StringBuilder sb = new StringBuilder(s);
			while(cuenta > 0)
			{
				sb.append(')');
				cuenta--;
			}
			System.out.println(sb.toString());
		}
	}
}