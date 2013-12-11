import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Choosing 
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
	
	static int[] vs;
	static int[] cs;
	static int[] next;
	static long a, b;
	
	static void precalculate()
	{
		next = new int[cs.length];
		int[] last = new int[cs.length + 1];
		Arrays.fill(last, -1);
		for(int i = cs.length - 1; i >= 0; i--)
		{
			next[i] = last[cs[i]];
			last[cs[i]] = i;
		}
	}
	
	static long[] todasATemp = new long[100001];
	static long[] todasBTemp = new long[100001];
	
	static long solve()
	{
		long[] todasA = todasATemp;
		long[] todasB = todasBTemp;
		for(int i = 1; i < cs.length + 1; i++)
			todasB[i] = todasA[i] = 0; 
		long mejorTodas = 0;
		int mejorT = 1;
		int segundaMejorT = todasB.length < 3 ? 1 : 2;
		for(int i = cs.length - 1; i >= 0; i--)
		{
			long posibilidadA = todasA[cs[i]];
			long posibilidadB = 0;
			if(mejorT == cs[i])
				posibilidadB = mejorT == segundaMejorT ? Long.MIN_VALUE : todasB[segundaMejorT];
			else
				posibilidadB = todasB[mejorT];
			long mejor = Math.max(posibilidadA, posibilidadB);
			long solucionA = vs[i] * a + mejor;
			long solucionB = vs[i] * b + mejor;
			mejorTodas = Math.max(mejorTodas, solucionB);
			int considerada = cs[i];
			if(solucionA > todasA[considerada])
				todasA[considerada] = solucionA;
			if(solucionB > todasB[considerada])
			{
				todasB[considerada] = solucionB;
				if(considerada == mejorT || considerada == segundaMejorT)
				{
					if(todasB[mejorT] < todasB[segundaMejorT])
					{
						int temp = mejorT;
						mejorT = segundaMejorT;
						segundaMejorT = temp;
					}
				}
				else
				{
					if(solucionB > todasB[segundaMejorT])
					{
						segundaMejorT = considerada;
						if(todasB[mejorT] < todasB[segundaMejorT])
						{
							int temp = mejorT;
							mejorT = segundaMejorT;
							segundaMejorT = temp;
						}
					}
				}
			}
		}
		return mejorTodas;
 	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int q = sc.nextInt();
		vs = new int[n];
		cs = new int[n];
		for(int i = 0; i < n; i++)
			vs[i] = sc.nextInt();
		for(int i = 0; i < n; i++)
			cs[i] = sc.nextInt();
		precalculate();
		for(int i = 0; i < q; i++)
		{
			a = sc.nextInt();
			b = sc.nextInt();
			System.out.println(solve());
		}
	}
}