import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeB 
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
	
	
	static boolean esPosible(int[] vals, int[] segunda, int desde)
	{
		int[] current = vals.clone();
		int menorActual = 25;
		for(int i = desde; i < segunda.length; i++)
		{
			while(menorActual >= 0 && current[menorActual] == 0)
				menorActual--;
			if(menorActual < 0)
				return false;
			if(menorActual < segunda[i])
				return false;
			if(menorActual > segunda[i])
				return true;
			current[menorActual]--;
		}
		while(menorActual >= 0 && current[menorActual] == 0)
			menorActual--;
		return menorActual >= 0;
	}
	
	static void solve(int[] primera, int[] segunda, int n, boolean pusoDistinta, int[] ans)
	{
		if(n == ans.length)
			return;
		if(n == segunda.length)
			pusoDistinta = true;
		if(pusoDistinta)
		{
			for(int i = 0; i < 26; i++)
				if(primera[i] != 0)
				{
					ans[n] = i;
					primera[i]--;
					solve(primera, segunda, n + 1, pusoDistinta, ans);
					return;
				}
		}
		else
		{
			if(primera[segunda[n]] != 0)
			{
				primera[segunda[n]]--;
				if(esPosible(primera, segunda, n + 1))
				{
					ans[n] = segunda[n];
					solve(primera, segunda, n + 1, pusoDistinta, ans);
					return;
				}
				else
				{
					primera[segunda[n]]++;	
					for(int i = segunda[n] + 1; i < 26; i++)
						if(primera[i] != 0)
						{
							primera[i]--;
							ans[n] = i;
							solve(primera, segunda, n + 1, true, ans);
							return;
						}
				}
			}
			for(int i = segunda[n] + 1; i < 26; i++)
				if(primera[i] != 0)
				{
					primera[i]--;
					ans[n] = i;
					solve(primera, segunda, n + 1, true, ans);
					return;
				}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String primeraS = sc.next();
		String segundaS = sc.next();
		int[] segunda = new int[segundaS.length()];
		int[] primera = new int[26];
		for(int i = 0; i < segunda.length; i++)
			segunda[i] = segundaS.charAt(i) - 'a';
		for(char c : primeraS.toCharArray())
			primera[c - 'a']++;
		if(esPosible(primera, segunda, 0))
		{
			int[] ans = new int[primeraS.length()];
			solve(primera, segunda, 0, false, ans);
			char[] res = new char[ans.length];
			for(int i = 0; i < ans.length; i++)
				res[i] = (char) (ans[i] + 'a');
			System.out.println(new String(res));
		}
		else
			System.out.println("-1");
	}

}
