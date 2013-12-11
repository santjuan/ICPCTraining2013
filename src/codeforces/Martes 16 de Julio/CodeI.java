import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class CodeI 
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
	
	static class Operator
	{
		
	}
	
	static LinkedList <String> entrada = new LinkedList <String> ();
	
	
	static Operator readOperator()
	{
		if(entrada.peek().trim().startsWith("try"))
			return new Try();
		else
			return new Throw();
	}
	
	static class Try extends Operator
	{
		Catch c;
		ArrayList <Operator> internal = new ArrayList <Operator> (); 
		
		Try()
		{
			entrada.poll();
			while(!entrada.peek().trim().startsWith("catch"))
				internal.add(readOperator());
			c = new Catch();
		}
		
		public Try(boolean b)
		{
			while(!entrada.isEmpty() && !entrada.peek().trim().startsWith("catch"))
				internal.add(readOperator());
			c = new Catch(true);
		}

		Answer execute()
		{
			for(Operator o : internal)
			{
				if(o instanceof Throw)
				{
					Throw t = (Throw) o;
					return c.attemptCatch(new Answer(true, t.exceptionType));
				}
				else
				{
					Try t = (Try) o;
					Answer a = t.execute();
					if(a != null)
					{
						if(a.isException)
							return c.attemptCatch(a);
						else
							return a;
					}
				}
			}
			return null;
		}
	}
	
	static class Catch extends Operator
	{
		String exceptionType;
		String message;
		
		Catch()
		{
			String s = entrada.poll().trim();
			s = s.substring(5);
			s = s.trim();
			s = s.substring(1);
			s = s.trim();
			exceptionType = s.substring(0, s.indexOf(','));
			s = s.substring(s.indexOf(',') + 1);
			s = s.trim();
			s = s.substring(1);
			message = s.substring(0, s.indexOf('"'));
		}
		
		public Catch(boolean b)
		{
			exceptionType = "";
			message = "";
		}
		
		public Answer attemptCatch(Answer answer) 
		{
			if(!answer.isException)
				return answer;
			if(answer.name.trim().equals(exceptionType.trim()))
				return new Answer(false, message);
			else
				return answer;
		}
	}
	
	static class Throw extends Operator
	{
		String exceptionType;
		
		Throw()
		{
			String s = entrada.poll().trim();
			s = s.substring(5);
			s = s.trim();
			s = s.substring(1);
			s = s.trim();
			exceptionType = s.substring(0, s.indexOf(')'));
		}
	}
	
	static class Answer
	{
		boolean isException;
		String name;
		
		public Answer(boolean i, String n) 
		{
			isException = i;
			name = n;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		for(int i = 0; i < n; i++)
		{
			String linea = sc.nextLine().trim();
			if(!linea.isEmpty())
				entrada.add(linea);
		}
		Try t = new Try(true);
		Answer a = t.execute();
		if(a.isException)
			System.out.println("Unhandled Exception");
		else
			System.out.println(a.name);
	}
}