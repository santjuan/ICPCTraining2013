import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;


public class Jupiter
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
	}
	
	static long[] M;
	static long[] potB;
	static long P;
	
	static long calculateValue(long izq, long der, int n) 
	{
		long izqR = (izq * potB[n]) % P;
		return (der + izqR) % P;
	}
	
	static void update(int node, int b, int e, int pos, long value)
	{
		//if the current interval doesn't intersect 
		//the query interval return -1
		if (pos > e || pos < b)
			return;

		//if the current interval is included in 
		//the query interval return M[node]
		if (b == e)
		{
			M[node] = value;
			return;
		}
		update(2 * node, b, (b + e) / 2, pos, value);
		update(2 * node + 1, (b + e) / 2 + 1, e, pos, value);

		M[node] = calculateValue(M[2 * node], M[2 * node + 1], e - ((b + e) / 2 + 1) + 1);
	}
	
	static long query(int node, int b, int e, int i, int j)
	{
		long p1, p2;
		
		//if the current interval doesn't intersect 
		//the query interval return -1
		if (i > e || j < b)
			return 0;

		if (b >= i && e <= j)
			return M[node];

		//compute the minimum position in the 
		//left and right part of the interval
		p1 = query(2 * node, b, (b + e) / 2, i, j);
		p2 = query(2 * node + 1, (b + e) / 2 + 1, e, i, j);
		long tmp = (p1 * potB[tamInterseccion((b + e) / 2 + 1, e, i, j)]) % P;
		return (tmp + p2) % P;
	}

	static int tamInterseccion(int a, int b, int i, int j) 
	{
		int maxI = Math.max(a, i);
		int minJ = Math.min(b, j);
		if(minJ < maxI)
			return 0;
		return minJ - maxI + 1;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
	    System.setOut(new PrintStream(new BufferedOutputStream(System.out), false));
		while(true)
		{
			long b = sc.nextInt();
			P = sc.nextInt();
			int l = sc.nextInt();
			int n = sc.nextInt();
			if(b == 0 && P == 0 && l == 0 && n == 0)
			{
				System.out.flush();
				return;
			}
			M = new long[l * 4 + 2];
			potB = new long[l + 1];
			potB[0] = 1;
			for(int i = 1; i <= l; i++)
				potB[i] = (potB[i - 1] * b) % P;
			for(int i = 0; i < n; i++)
			{
				String c = sc.next();
				if(c.equals("E"))
				{
					int pos = sc.nextInt() - 1;
					int val = sc.nextInt();
					update(1, 0, l - 1, pos, val);
				}
				else
				{
					int aa = sc.nextInt() - 1;
					int bb = sc.nextInt() - 1;
					System.out.println(query(1, 0, l - 1, aa, bb));
				}
			}
			System.out.println("-");
		}
	}
}