import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class review 
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
	
	static class Paper
	{
		String university;
		boolean problem;
		ArrayList <Integer> reviews = new ArrayList <Integer> ();
		
		int count(int val)
		{
			int total = 0;
			for(int i : reviews)
				if(i == val)
					total++;
			return total;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int k = sc.nextInt();
			int n = sc.nextInt();
			if(k == 0 && n == 0) return;
			Paper[] papers = new Paper[n];
			for(int i = 0; i < n; i++)
				papers[i] = new Paper();
			for(int i = 0; i < n; i++)
			{
				papers[i].university = sc.next();
				for(int j = 0; j < k; j++)
					papers[sc.nextInt() - 1].reviews.add(i);
			}
			for(Paper p : papers)
			{
				if(p.reviews.size() != k)
					p.problem = true;
				else
				{
					for(int i : p.reviews)
					{
						if(p.count(i) != 1)
							p.problem = true;
						if(papers[i].university.equals(p.university))
							p.problem = true;
					}
				}
			}
			int problems = 0;
			for(Paper p : papers)
				if(p.problem)
					problems++;
			if(problems == 0)
				System.out.println("NO PROBLEMS FOUND");
			else if(problems == 1)
				System.out.println("1 PROBLEM FOUND");
			else
				System.out.println(problems + " PROBLEMS FOUND");
		}
	}

}
