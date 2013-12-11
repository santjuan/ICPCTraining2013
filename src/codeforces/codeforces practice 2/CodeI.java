import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CodeI 
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
		
		public int[] nextIntArray(int n)
		{
			int[] res = new int[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextInt();
			return res;
		}
		
		public long[] nextLongArray(int n)
		{
			long[] res = new long[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}
		
		public double[] nextDoubleArray(int n)
		{
			double[] res = new double[n];
			for(int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}
		public void sortIntArray(int[] array)
		{
			Integer[] vals = new Integer[array.length];
			for(int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for(int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}
		
		public void sortLongArray(long[] array)
		{
			Long[] vals = new Long[array.length];
			for(int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for(int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}
		
		public void sortDoubleArray(double[] array)
		{
			Double[] vals = new Double[array.length];
			for(int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for(int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}
	}
	
	static class Arista
	{
		Nodo otro;
		boolean lucky;
		
		Arista(Nodo n, long number)
		{
			String s = number + "";
			lucky = true;
			for(char c : s.toCharArray())
				if(c != '4' && c != '7')
					lucky = false;
			otro = n;
		}
	}
	static class Nodo
	{
		long nLucky;
		long nUnlucky;
		ArrayList <Arista> aristas = new ArrayList <Arista> ();

		void calculateLucky(Nodo parent)
		{
			nLucky = 0;
			nUnlucky = 1;
			for(Arista a : aristas)
			{
				if(a.otro == parent)
					continue;
				a.otro.calculateLucky(this);
				if(a.lucky)
					nLucky += a.otro.nLucky + a.otro.nUnlucky;
				else
				{
					nLucky += a.otro.nLucky;
					nUnlucky += a.otro.nUnlucky;
				}				
			}
		}
		
		long luckyUnlucky(Nodo otro)
		{
			return nLucky + nUnlucky - otro.nLucky - otro.nUnlucky;
		}
		
		long lucky(Arista a)
		{
			if(a.lucky)
				return nLucky - a.otro.nLucky - a.otro.nUnlucky;
			else
				return nLucky - a.otro.nLucky;
		}
		
		long calculateTriples(Nodo parent, long acumLucky, long acumUnlucky)
		{
			long ans = 0;
			for(Arista a : aristas)
			{
				Nodo actual = a.otro;
				if(actual == parent)
					continue;
				long acumLuckyNext = acumLucky;
				long acumUnluckyNext = acumUnlucky;
				if(a.lucky)
				{
					acumLuckyNext += luckyUnlucky(actual) + acumUnlucky;
					acumUnluckyNext = 0;
				}
				else
				{
					acumLuckyNext += lucky(a);
					acumUnluckyNext += nUnlucky - actual.nUnlucky;
				}
				ans += actual.calculateTriples(this, acumLuckyNext, acumUnluckyNext);
			}
			return (nLucky + acumLucky) * (nLucky + acumLucky - 1) + ans;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Nodo[] nodos = new Nodo[n];
		for(int i = 0; i < n; i++)
			nodos[i] = new Nodo();
		for(int i = 0; i < n - 1; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			long peso = sc.nextLong();
			nodos[a].aristas.add(new Arista(nodos[b], peso));
			nodos[b].aristas.add(new Arista(nodos[a], peso));
		}
		nodos[0].calculateLucky(null);
		System.out.println(nodos[0].calculateTriples(null, 0, 0));
	}
}