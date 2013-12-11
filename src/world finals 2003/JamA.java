import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class JamA 
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
	
	
	static double solve(long budget, ArrayList <Long> vals)
	{
		while(vals.size() != 37)
			vals.add(0L);
		Collections.sort(vals);
		double best = 0;
		for(int i = 0; i < 36; i++)
		{
			long spent = 0;
			long minVal = vals.get(i);
			int lim = i;
			while(lim < 36 && vals.get(lim).longValue() == minVal)
				lim++;
			lim--;
			for(int j = 0; j <= lim; j++)
				spent +=  minVal - vals.get(j);
			if(spent > budget)
				continue;
			long extra = budget - spent;
			long extraPossible = vals.get(lim + 1) - 1 - minVal;
			long nBalls = lim + 1;
			spent += Math.min(extraPossible * nBalls, (extra / nBalls) * nBalls);
			double curAns = 1 / ((double) nBalls);
			curAns *= spent;
			curAns *= 36;
			curAns -= spent;
			best = Math.max(best, curAns);
		}
		return best;
	}
	
	static double solveBrute(long capital, ArrayList <Long> vals)
	{
		while(vals.size() != 37)
			vals.add(0L);
		double best = 0;
		for(int min = 0; min <= 1000; min++)
		{
			long[] betAmount = new long[37];
			long betTotal = 0;
			boolean posible = false;
			for(long v : vals)
				if(v <= min)
					posible = true;
			if(posible)
			{
				double howManyMin = 0;
				for(int i = 0; i < 37; i++)
				{
					if(vals.get(i) < min)
					{
						int diff = (int) (min - vals.get(i));
						betAmount[i] = diff;
						betTotal += diff;
					}
					if(vals.get(i) + betAmount[i] == min)
						howManyMin += 1;
				}
				if(betTotal <= capital)
				{
					double profit = 0;
					for(long l : betAmount)
						profit += (1 / howManyMin) * 36 * l;
					best = Math.max(best, profit - betTotal);
				}
			}
		}
		return best;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
	//	System.setOut(new PrintStream("A1.out"));
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 1; caso <= t; caso++)
		{
			long b = sc.nextLong();
			int n = sc.nextInt();
			ArrayList <Long> vals = new ArrayList <Long> ();
			for(int i = 0; i < n; i++)
				vals.add(sc.nextLong());
			System.out.println("Case #" + caso + ": " + solveBrute(b, vals));
		}
	}

}
