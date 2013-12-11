import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


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
		
		public String[] nextStringArray(int n)
		{
			String[] res = new String[n];
			for(int i = 0; i < res.length; i++)
				res[i] = next();
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
		@SuppressWarnings("unchecked")
		LinkedList <Integer> [] aQuien = new LinkedList[n + 1];
		for(int i = 1; i <= n; i++)
			aQuien[i] = new LinkedList <Integer> ();
		for(int i = 0; i < m; i++)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			aQuien[a].add(b);
			aQuien[b].add(a);
		}
		int grupos = 0;
		while(true)
		{
			LinkedList < LinkedList <Integer> > reprimanded = new LinkedList < LinkedList<Integer> > ();
			for(int i = 1; i <= n; i++)
				if(aQuien[i].size() == 1)
					reprimanded.add(aQuien[i]);
			if(reprimanded.size() == 0)
				break;
			grupos++;
			for(LinkedList <Integer> a : reprimanded)
			{
				int cual = 0;
				for(int i = 1; i <= n; i++)
					if(a == aQuien[i])
						cual = i;
				if(!a.isEmpty())
					aQuien[a.peek()].remove((Integer) cual);
				a.clear();
			}
		}
		System.out.println(grupos);
	}

}
