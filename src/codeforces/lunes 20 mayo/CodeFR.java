import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//import JaCoP.constraints.Sum;
//import JaCoP.constraints.XeqY;
//import JaCoP.core.IntVar;
//import JaCoP.core.Store;
//import JaCoP.search.DepthFirstSearch;
//import JaCoP.search.IndomainMin;
//import JaCoP.search.InputOrderSelect;
//import JaCoP.search.Search;
//import JaCoP.search.SelectChoicePoint;


public class CodeFR 
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
	
//	static void addEq(Store s, IntVar a, IntVar b, boolean equal)
//	{
//		if(a == b)
//			return;
//		if(equal)
//			s.impose(new XeqY(a, b));
//		else
//		{
//			IntVar p = new IntVar(s, 0, 1);
//			todas.add(p);
//			s.impose(new Sum(new IntVar[]{a, b}, p));
//		}
//	}
//	
//	static void intentarLink(Store s, IntVar a, IntVar[][] vars, int i, int j)
//	{
//		if(i < 0 || i >= vars.length || j < 0 || j >= vars.length)
//			return;
//		addEq(s, a, vars[i][j], false);
//	}
//	
//	static ArrayList <IntVar> todas;
//	
//	static boolean intentar(int size, int x)
//	{
//		Store store = new Store();
//		IntVar[][] variables = new IntVar[size][size];
//		todas = new ArrayList <IntVar> ();
//		for(int i = 0; i < size; i++)
//			for(int j = 0; j < size; j++)
//			{
//				variables[i][j] = new IntVar(store, 0, 1);
//				todas.add(variables[i][j]);
//			}
//		IntVar[] todasNormales = todas.toArray(new IntVar[0]);
//		for(int i = 0; i < size; i++)
//			for(int j = 0; j < size; j++)
//			{
//				intentarLink(store, variables[i][j], variables, i - 1, j);
//				intentarLink(store, variables[i][j], variables, i + 1, j);
//				intentarLink(store, variables[i][j], variables, i, j + 1);
//				intentarLink(store, variables[i][j], variables, i, j - 1);
//			}
//		for(int i = 0; i < size; i++)
//			for(int j = 0; j < size; j++)
//			{
//				addEq(store, variables[i][j], variables[size - i - 1][j], true);
//				addEq(store, variables[i][j], variables[i][size - j - 1], true);
//			}
//		IntVar suma = new IntVar(store, x, x);
//		todas.add(suma);
//		store.impose(new Sum(todasNormales, suma));
//		Search<IntVar> search = new DepthFirstSearch<IntVar>();
//		search.setPrintInfo(false);
//		SelectChoicePoint<IntVar> select =
//		new InputOrderSelect<IntVar>(store, todas.toArray(new IntVar[0]),
//		new IndomainMin<IntVar>());
//		return search.labeling(store, select);
//	}
	
	static int solve(int val)
	{
		if(val == 1)
			return 1;
		if(val == 2)
			return 3;
		if(val == 3)
			return 5;
		if(val < 6)
			return 3;
		if(val < 14)
			return 5;
		if(val < 26)
			return 7;
		if(val < 42)
			return 9;
		if(val < 62)
			return 11;
		if(val < 86)
			return 13;
		else
			return 15;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
//		int[] ans = new int[101];
//		for(int i = 1; i <= 100; i++)
//		{
//			for(int tam = 1; true; tam++)
//			{
//				if((tam & 1) == 0)
//					continue;
//				if(intentar(tam, i))
//				{
//					ans[i] = tam;
//					System.out.println(i + ": " + tam + " " + solve(i));
//					break;
//				}
//			}
//		}
		System.out.println(solve(sc.nextInt()));
	}

}
