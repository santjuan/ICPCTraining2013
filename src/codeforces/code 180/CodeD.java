import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class CodeD 
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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextInt();
		HashMap <Integer, Integer> alice = new HashMap <Integer, Integer> ();
		TreeSet <Integer> numeros = new TreeSet <Integer> ();
		for(int i = 0; i < n; i++)
		{
			int este = sc.nextInt();
			if(!alice.containsKey(este))
				alice.put(este, 0);
			alice.put(este, alice.get(este) + 1);
			numeros.add(este);
		}
		HashMap <Integer, Integer> bob = new HashMap <Integer, Integer> ();
		for(int i = 0; i < m; i++)
		{
			int este = sc.nextInt();
			if(!bob.containsKey(este))
				bob.put(este, 0);
			bob.put(este, bob.get(este) + 1);
			numeros.add(este);
		}
		int cuenta = 0;
		boolean posible = false;
		while(!numeros.isEmpty())
		{
			int val = numeros.pollLast();
			int nAlice = alice.containsKey(val) ? alice.get(val) : 0;
			int nBob = bob.containsKey(val) ? bob.get(val) : 0;
			cuenta += nAlice - nBob;
			if(cuenta > 0)
				posible = true;
		}
		System.out.println(posible ? "YES" : "NO");
	}

}
