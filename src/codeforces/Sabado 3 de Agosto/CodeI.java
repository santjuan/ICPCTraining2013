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

	
	static class Arista implements Comparable <Arista>
	{
		Nodo otro;
		int costoA;
		int costoB;
		
		public Arista(Nodo nodo)
		{
			otro = nodo;
		}
		
		int diferencia()
		{
			return costoA - costoB;
		}
		
		@Override
		public int compareTo(Arista o) 
		{
			return o.diferencia() - diferencia();
		}
	}
	
	static class Nodo
	{
		Integer dpA;
		Integer dpB;
		ArrayList <Arista> aristas = new ArrayList <Arista> ();
		
		int darMejor(Nodo padre, boolean puesto)
		{
			if(!puesto && dpA != null) return dpA;
			if(puesto && dpB != null) return dpB;
			for(Arista a : aristas)
				if(a.otro != padre)
				{
					a.costoA = a.otro.darMejor(this, false);					
					a.costoB = a.otro.darMejor(this, true);
				}
			int max = Integer.MAX_VALUE;
			int sMax = Integer.MAX_VALUE;
			int maxDiff = Integer.MIN_VALUE;
			for(int i = 0; i < aristas.size(); i++)
			{
				if(aristas.get(i).otro == padre) continue;
				if(aristas.get(i).diferencia() > maxDiff)
				{
					maxDiff = aristas.get(i).diferencia();
					max = i;
				}
			}
			maxDiff = Integer.MIN_VALUE;
			for(int i = 0; i < aristas.size(); i++)
			{
				if(aristas.get(i).otro == padre) continue;
				if(aristas.get(i).diferencia() > maxDiff && i != max)
				{
					maxDiff = aristas.get(i).diferencia();
					sMax = i;
				}
			}
			int totalA = 0;
			int totalB = 0;
			for(Arista a : aristas)
				if(a.otro != padre)
				{
					totalA += a.costoA;
					totalB += a.costoB;
				}
			int total = totalB + 500;
			if(puesto && aristas.size() == 0)
				total = 0;
			if(!puesto && aristas.size() == 0)
				total = 100;
			if(puesto)
				total = Math.min(total, totalA);
			if(puesto)
			{
				if(aristas.size() >= 3 - (padre == null ? 1 : 0))
				{
					int valor = aristas.get(max).costoB + aristas.get(sMax).costoB + totalA - aristas.get(max).costoA - aristas.get(sMax).costoA + 175;
					total = Math.min(total, valor);
				}
				if(aristas.size() >= 2 - (padre == null ? 1 : 0))
				{
					int valor = aristas.get(max).costoB + totalA - aristas.get(max).costoA + 100;
					total = Math.min(total, valor);
				}
			}
			else
			{
				if(aristas.size() >= 2 - (padre == null ? 1 : 0))
				{
					int valor = aristas.get(max).costoB + totalA - aristas.get(max).costoA + 175;
					total = Math.min(total, valor);
				}
				if(aristas.size() >= 1 - (padre == null ? 1 : 0))
				{
					int valor = totalA  + 100;
					total = Math.min(total, valor);
				}	
			}
			if(!puesto)
				dpA = total;
			else
				dpB = total;
			return total;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int i = 0; i < t; i++)
		{
			Nodo[] nodos = new Nodo[sc.nextInt()];
			for(int j = 0; j < nodos.length; j++)
				nodos[j] = new Nodo();
			for(int j = 0; j < nodos.length - 1; j++)
			{
				int a = sc.nextInt();
				int b = sc.nextInt();
				nodos[a].aristas.add(new Arista(nodos[b]));
				nodos[b].aristas.add(new Arista(nodos[a]));
			}
			System.out.println("$" + nodos[0].darMejor(null, true));
		}
	}
}