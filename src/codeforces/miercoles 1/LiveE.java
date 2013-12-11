import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class LiveE
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
	
	static ArrayList<Character> fillArray(String val) 
	{
		ArrayList <Character> ans = new ArrayList <Character> ();
		for(char c : val.toCharArray())
			ans.add(c);
		return ans;
	}
	
	static int whatNumber(ArrayList <Character> current, boolean oneB)
	{
		int val = 0;
		int one = 1;
		for(int i = current.size() - 1; i >= 0; i--)
		{
			char c = current.get(i);
			if(oneB && c == 'B')
				val += one;
			else if(!oneB && c != 'B')
				val += one;
			one <<= 1;
		}
		return val;
	}
	
	static TreeSet <Integer> solve(String val)
	{
		TreeSet <Integer> ans = new TreeSet <Integer> ();
		ArrayList <Character> current = fillArray(val);
		int limite = val.length();
		limite += limite + 1;
		for(int i = 0; i < limite; i++)
		{
			ans.add(whatNumber(current, true));
			ans.add(whatNumber(current, false));
			Collections.rotate(current, -1);
		}
		return ans;
	}
	
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			String current = sc.next();
			if(current.equals("END"))
				return;
			TreeSet <Integer> a = solve(current);
			a.addAll(solve(new StringBuilder(current).reverse().toString()));
			System.out.println(a.first() + " " + a.last());
		}
	}

}
