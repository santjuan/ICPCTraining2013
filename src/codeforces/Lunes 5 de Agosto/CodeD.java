import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;


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

	static class DisjointSet {
		int[] p, rank;
		int sets;

		public DisjointSet(int size) {
			rank = new int[size];
			p = new int[size];
			clear();
		}
		
		public void clear() {
			for (int i = 0; i < p.length; i++)
				make_set(i);
			sets = p.length;
		}

		public void make_set(int x) {
			p[x] = x;
			rank[x] = 0;
		}

		public void merge(int x, int y) {
			int a = find_set(x);
			int b = find_set(y);
			link(a, b);
			if(a != b) sets--;
		}

		public void link(int x, int y) {
			if (rank[x] > rank[y])
				p[y] = x;
			else {
				p[x] = y;
				if (rank[x] == rank[y])
					rank[y] += 1;
			}
		}

		public int find_set(int x) {
			if (x != p[x])
				p[x] = find_set(p[x]);
			return p[x];
		}

		public int countSets(int n) 
		{
			HashSet <Integer> sets = new HashSet <Integer> ();
			for(int i = 0; i < n; i++)
				sets.add(find_set(i));
			return sets.size();
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] cables = sc.nextIntMatrix(m, 2);
		for(int[] i : cables)
		{
			i[0]--;
			i[1]--;
		}
		DisjointSet ds = new DisjointSet(n);
		ds.clear();
		ArrayList <Integer> importantesID = new ArrayList <Integer> ();
		ArrayList <Integer> importantesDI = new ArrayList <Integer> ();
		for(int i = 0; i < cables.length; i++)
		{
			if(ds.find_set(cables[i][0]) != ds.find_set(cables[i][1]))
			{
				ds.merge(cables[i][0], cables[i][1]);
				importantesID.add(i);
			}
		}
		ds.clear();
		for(int i = cables.length - 1; i >= 0; i--)
		{
			if(ds.find_set(cables[i][0]) != ds.find_set(cables[i][1]))
			{
				ds.merge(cables[i][0], cables[i][1]);
				importantesDI.add(i);
			}
		}
		int k = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int c = 0; c < k; c++)
		{
			ds.clear();
			int l = sc.nextInt() - 1;
			int r = sc.nextInt() - 1;
			for(int i : importantesID)
			{
				if(i >= l) break;
				ds.merge(cables[i][0], cables[i][1]);
			}
			for(int i : importantesDI)
			{
				if(i <= r) break;
				ds.merge(cables[i][0], cables[i][1]);
			}
			sb.append(ds.sets);
			sb.append('\n');
		}
		System.out.print(sb.toString());
		System.out.flush();
	}
}