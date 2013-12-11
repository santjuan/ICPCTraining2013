import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

	static int problema;
	static class Student
	{
		int id;
		Boolean color;
		boolean existe = true;
		ArrayList <Student> enemigos = new ArrayList <Student> ();
	
		Student(int i)
		{
			id = i;
		}
		
		boolean dfs(boolean c)
		{
			if(!existe) return false;
			if(color != null && color.booleanValue() != c)
			{
				problema = id;
				return true;
			}
			if(color != null) return false;
			color = c;
			for(Student e : enemigos)
				if(e.dfs(!c))
					return true;
			return false;
		}
	}
	
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		Student[] students = new Student[n];
		for(int i = 0; i < students.length; i++) students[i] = new Student(i);
		for(int i = 0; i < m; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			students[a].enemigos.add(students[b]);
			students[b].enemigos.add(students[a]);
		}
		int cuenta = 0;
		outer:
		while(true)
		{
			for(Student s : students) s.color = null;
			for(Student s : students)
			{
				if(s.color == null && s.existe)
				{
					if(s.dfs(true))
					{
						cuenta++;
						students[problema].existe = false;
						continue outer;
					}
				}
			}
			break;
		}
		int total = n - cuenta;
		if((total & 1) == 1)
			cuenta++;
		System.out.println(cuenta);
	}
}