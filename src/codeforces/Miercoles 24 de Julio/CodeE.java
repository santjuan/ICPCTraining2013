import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class CodeE 
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
				res[i] = nextDouble();
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

		public String[] nextStringArray(int n) 
		{	
			String[] vals = new String[n];
			for(int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}
		
		Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
	}

	static class ValueIncreasingMap
	{
		TreeMap <Integer, Integer> mapa = new TreeMap <Integer, Integer> ();
	
		void insert(int k, int v)
		{
			if(mapa.containsKey(k))
			{
				if(mapa.get(k) >= v) return;
				else mapa.remove(k);
			}
			while(true)
			{
				Integer p = mapa.ceilingKey(k);
				if(p == null) break;
				if(mapa.get(p) > v) break;
				mapa.remove(p);
			}
			Integer p = mapa.floorKey(k);
			if(p != null && mapa.get(p) >= v) return;
			mapa.put(k, v);
		}
		
		int query(int k)
		{
			Integer p = mapa.floorKey(k);
			if(p == null) return 0;
			return mapa.get(p);
		}
	}
	
	public static ArrayList <Integer> generarFactores(int n)
	{
		ArrayList <Integer> factores = new ArrayList <Integer> ();
		int limite = Math.min(n, (int) (Math.ceil(Math.sqrt(n) + 1)));
		for(int i = 2; i <= limite; i++)
		{
			if(n % i == 0)
			{
				int cuenta = 0;
				while(n % i == 0)
					n /= i;
				factores.add(i);
			}
		}
		if(n != 1)
			factores.add(n);
		return factores;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		boolean[] primos = new boolean[100001];
		Arrays.fill(primos, true);
		primos[0] = primos[1] = false;
		for(int i = 2; i < primos.length; i++)
		{
			if(primos[i])
			{
				long ini = i;
				ini *= i;
				if(ini >= primos.length) break;
				for(int j = i * i; j < primos.length; j += i)
					primos[j] = false;
			}
		}
		ValueIncreasingMap[] mapas = new ValueIncreasingMap[primos.length];
		for(int i = 0; i < primos.length; i++)
			if(primos[i])
				mapas[i] = new ValueIncreasingMap();

		int n = sc.nextInt();
		int[] vals = sc.nextIntArray(n);
		int mayor = Math.min(1, n);
		for(int i = 0; i < vals.length; i++)
		{
			int max = 0;
			ArrayList <Integer> factores = generarFactores(vals[i]);
			for(int p : factores)
				max = Math.max(mapas[p].query(vals[i]) + 1, max);
			for(int p : factores)
				mapas[p].insert(vals[i], max);
			mayor = Math.max(max, mayor);
		}
		System.out.println(mayor);
	}
}