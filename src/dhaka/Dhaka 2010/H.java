import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class H 
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
	
	static double bestAnswer;
	static Double[][][][][][] dp;
	static int[] limit;
	static double[][] probabilities;
	static boolean[] depends;
	static int nQuestions;
	
	static double dp(int[] tams, boolean lastCorrect, int questionNumber)
	{
		if(questionNumber == nQuestions)
			return 0;
		if(dp[tams[0]][tams[1]][tams[2]][tams[3]][tams[4]][lastCorrect ? 1 : 0] != null)
			return dp[tams[0]][tams[1]][tams[2]][tams[3]][tams[4]][lastCorrect ? 1 : 0];
		double best = Double.NEGATIVE_INFINITY;
		for(int i = 0; i < tams.length; i++)
		{
			if(tams[i] == limit[i])
				continue;
			if(depends[questionNumber] && !lastCorrect)
			{
				tams[i]++;
				best = Math.max(best, dp(tams, false, questionNumber + 1));
				tams[i]--;
			}
			else
			{
				tams[i]++;
				double res = probabilities[i][questionNumber] + probabilities[i][questionNumber] * dp(tams, true, questionNumber + 1);
				res += (1 - probabilities[i][questionNumber]) * dp(tams, false, questionNumber + 1);
				best = Math.max(best, res);
				tams[i]--;
			}
		}
		return dp[tams[0]][tams[1]][tams[2]][tams[3]][tams[4]][lastCorrect ? 1 : 0] = best;
	}
	
	static void tryAll(int[] current, int n, int i, int left)
	{
		if(i == current.length)
		{
			if(left != 0)
				return;
			int[] tams = new int[5];
			for(int a = 0; a < current.length; a++)
				tams[a] = current[a];
			dp = new Double[tams[0] + 1][tams[1] + 1][tams[2] + 1][tams[3] + 1][tams[4] + 1][2];
			limit = tams;
			bestAnswer = Math.max(bestAnswer, dp(new int[5], true, 0));
		}
		else
		{
			current[i] = n;
			tryAll(current, n, i + 1, left);
			if(left != 0)
			{
				current[i] = n + 1;
				tryAll(current, n, i + 1, left - 1);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 1; caso <= t; caso++)
		{
			int n = sc.nextInt();
			nQuestions = sc.nextInt();
			int k = sc.nextInt();
			depends = new boolean[nQuestions];
			for(int i = 0; i < k; i++)
				depends[sc.nextInt() - 1] = true;
			probabilities = new double[n][nQuestions];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < nQuestions; j++)
					probabilities[i][j] = sc.nextDouble();
			bestAnswer = Double.NEGATIVE_INFINITY;
			tryAll(new int[n], nQuestions / n, 0, nQuestions % n);
			System.out.println("Case " + caso + ": " + String.format("%.4f", bestAnswer).replace(",", "."));
		}
	}
}