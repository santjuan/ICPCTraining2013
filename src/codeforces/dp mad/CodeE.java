import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


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

		public String[] nextStringArray(int n) 
		{	
			String[] vals = new String[n];
			for(int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}
	}
	
	static int k;
	static long answer;
	
	static class Nodo
	{
		ArrayList <Nodo> aristas = new ArrayList <Nodo> ();

		int[] solve(Nodo padre)
		{
			int[] dpK = new int[k + 1];
			dpK[0] = 1;
			long[] dpK2 = new long[k + 1];
			long ans = 0;
			for(Nodo n : aristas)
				if(n != padre)
				{
					int[] dpKS = n.solve(this);
					for(int depth = 1; depth <= k; depth++)
					{
						dpK[depth] += dpKS[depth - 1];
						if(k - depth >= depth)
							dpK2[depth] += dpKS[depth - 1] * dpKS[(k - depth) - 1];
					}
				}
			ans += dpK[k];
			for(int i = 1; k - i >= i; i++)
			{
				int ki = k - i;
				long ans1 = dpK[i] * ((long) dpK[ki]) - dpK2[i];
				if(i == ki)
					ans1 >>= 1;
				ans += ans1;
			}
			answer += ans;
			return dpK;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		k = sc.nextInt();
		Nodo[] nodos = new Nodo[n];
		for(int i = 0; i < n; i++)
			nodos[i] = new Nodo();
		for(int i = 0; i < n - 1; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			nodos[a].aristas.add(nodos[b]);
			nodos[b].aristas.add(nodos[a]);
		}
		nodos[0].solve(null);
		System.out.println(answer);
	}

}
