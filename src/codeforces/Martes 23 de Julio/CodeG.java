import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class CodeG 
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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		@SuppressWarnings("unchecked")
		LinkedList <Integer> [] acusadores = new LinkedList[n];
		@SuppressWarnings("unchecked")
		LinkedList <Integer> [] absuelven = new LinkedList[n];
		for(int i = 0; i < n; i++)
		{
			acusadores[i] = new LinkedList <Integer> ();
			absuelven[i] = new LinkedList <Integer> ();
		}
		HashSet <Integer> pendientesVerdadAc = new HashSet <Integer> ();
		LinkedList <Integer> pendientesMentiraAc = new LinkedList <Integer> ();
		LinkedList <Integer> pendientesVerdadAb = new LinkedList <Integer> ();
		boolean[] metidoMentiraAc = new boolean[n];
		boolean[] metidoVerdadAb = new boolean[n];
		HashSet <Integer> pendientesMentiraAb = new HashSet <Integer> ();
		int totalAbsuelven = 0;
		int totalAcusan = 0;
		boolean[] verdad = new boolean[n];
		boolean[] mentira = new boolean[n];
		for(int i = 0; i < n; i++)
		{
			String e = sc.next();
			int a = Integer.parseInt(e.substring(1)) - 1;
			if(e.charAt(0) == '+')
			{
				acusadores[a].add(i);
				pendientesVerdadAc.add(a);
				if(!metidoMentiraAc[a])
				{
					pendientesMentiraAc.add(a);
					metidoMentiraAc[a] = true;
				}
				totalAcusan++;
			}
			else
			{
				absuelven[a].add(i);
				if(!metidoVerdadAb[a])
				{
					pendientesVerdadAb.add(a);
					metidoVerdadAb[a] = true;
				}
				pendientesMentiraAb.add(a);
				totalAbsuelven++;
			}
		}
		for(int i = 0; i < n; i++)
		{
			int diciendoVerdad = acusadores[i].size() + (totalAbsuelven - absuelven[i].size());
			if(diciendoVerdad == m)
			{
				if(pendientesVerdadAc.contains(i))
				{
					pendientesVerdadAc.remove(i);
					for(int v : acusadores[i])
					{
						if(verdad[v]) break;
						verdad[v] = true;
					}
				}
				for(ListIterator <Integer> it = pendientesMentiraAc.listIterator(); it.hasNext();)
				{
					int z = it.next();
					if(z != i)
					{
						it.remove();
						for(int v : acusadores[z])
						{
							if(mentira[v]) break;
							mentira[v] = true;
						}
					}
				}
				if(pendientesMentiraAb.contains(i))
				{
					pendientesMentiraAb.remove(i);
					for(int v : absuelven[i])
					{
						if(mentira[v]) break;
						mentira[v] = true;
					}
				}
				for(ListIterator <Integer> it = pendientesVerdadAb.listIterator(); it.hasNext();)
				{
					int z = it.next();
					if(z != i)
					{
						it.remove();
						for(int v : absuelven[z])
						{
							if(verdad[v]) break;
							verdad[v] = true;
						}
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++)
		{
			if(verdad[i] && !mentira[i])
				sb.append("Truth\n");
			else if(!verdad[i] && mentira[i])
				sb.append("Lie\n");
			else
				sb.append("Not defined\n");
		}
		System.out.print(sb);
		System.out.flush();
	}
}