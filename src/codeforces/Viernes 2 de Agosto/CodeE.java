import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
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
	
	public static class Fenwick
	{
		long[] fenwickTree;
		int tam;
		
		public Fenwick(int t)
		{
			fenwickTree = new long[t];
			tam = t;
		}
		
		long query(int a, int b) 
		{
		    if (a == 0)
		    {
		        long sum = 0;
		        for (; b >= 0; b = (b & (b + 1)) - 1)
		          sum += fenwickTree[b];
		        return sum;
		    } 
		    else 
		    {
		        return (query(0, b) - query(0, a - 1));
		    }
		}
	
		void increase(int k, long inc)
		{
		    for (; k < tam; k |= k + 1)
		        fenwickTree[k] += inc;
		}
		
		void increaseRange(int a, int b, long val)
		{
			increase(a, val);
			increase(b + 1, -val);
		}
		
		long queryRangePoint(int a)
		{
			return query(0, a);
		}
	}

	
	static long darSuma(int izq, int der, int[] trees, Fenwick f)
	{
		long total = 0;
		total += trees[izq];
		total += trees[der];
		izq++;
		der--;
		if(izq > der) return total;
		total += f.query(izq, der);
		return total;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] trees = sc.nextIntArray(n);
		Fenwick f = new Fenwick(n);
		for(int i = 0; i < trees.length; i++)
			f.increase(i, Math.max(0, trees[i]));
		TreeMap < Integer, ArrayList <Integer> > values = new TreeMap < Integer, ArrayList <Integer> > ();
		for(int i = 0; i < trees.length; i++)
		{
			if(values.get(trees[i]) == null)
			{
				ArrayList <Integer> a = new ArrayList <Integer> ();
				values.put(trees[i], a);
			}
			values.get(trees[i]).add(i);
		}
		int izq = 0;
		int der = 0;
		long best = Long.MIN_VALUE;
		for(Map.Entry < Integer, ArrayList <Integer> > e : values.entrySet())
		{
			ArrayList <Integer> todos = e.getValue();
			int idPrimero = todos.get(0);
			for(int i = 1; i < todos.size(); i++)
			{
				int idSegundo = todos.get(i);
				long suma = darSuma(idPrimero, idSegundo, trees, f);
				if(suma > best)
				{
					best = suma;
					izq = idPrimero;
					der = idSegundo;
				}
			}
		}
		ArrayList <Integer> aBorrar = new ArrayList <Integer> ();
		for(int i = 0; i < trees.length; i++)
		{
			if(i == izq || i == der) continue;
			if(i < izq || i > der) aBorrar.add(i);
			else if(trees[i] < 0) aBorrar.add(i);
		}
		System.out.println(best + " " + aBorrar.size());
		boolean empezo = false;
		for(int i : aBorrar)
		{
			if(empezo) System.out.print(" ");
			empezo = true;
			System.out.print(i + 1);
		} 
		System.out.println();
	}
}