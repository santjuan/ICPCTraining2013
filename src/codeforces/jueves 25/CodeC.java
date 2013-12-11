import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class CodeC 
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
	
	static int numeroDoces(int n)
	{
		int base = 2;
		int ans = 0;
		while((n / base) != 0)
		{
			ans += (n / base);
			base *= 2;
		}
		return ans;
	}
	
	static HashMap <Integer, Integer> hash = new HashMap <Integer, Integer> ();
	static TreeMap <Integer, Integer> mapa = new TreeMap <Integer, Integer> ();
	
	static void poner(int num)
	{
		Integer val = hash.get(num);
		if(val == null)
			val = 0;
		else
		{
			int veces = mapa.get(val);
			veces--;
			if(veces == 0)
				mapa.remove(val);
			else
				mapa.put(val, veces);
		}
		val++;
		hash.put(num, val);
		Integer veces = mapa.get(val);
		if(veces == null)
			veces = 0;
		veces++;
		mapa.put(val, veces);
	}
	
	static void quitar(int num)
	{
		Integer val = hash.get(num);
		if(val == null)
			val = 0;
		else
		{
			int veces = mapa.get(val);
			veces--;
			if(veces == 0)
				mapa.remove(val);
			else
				mapa.put(val, veces);
		}
		val--;
		hash.put(num, val);
		Integer veces = mapa.get(val);
		if(veces == null)
			veces = 0;
		veces++;
		mapa.put(val, veces);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] arreglo = sc.nextIntArray(n);
		int i = 0;
		int j = 0;
		poner(arreglo[0]);
		long total = 0;
		while(i < n)
		{
			while(j != n - 1 && mapa.lastKey() < k)
				poner(arreglo[++j]);
			if(mapa.lastKey() >= k)
				total += n - j;
			quitar(arreglo[i++]);
		}
		System.out.println(total);
	}

}
