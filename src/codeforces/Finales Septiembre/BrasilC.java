import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BrasilC 
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
		
		public Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		public int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
		
		public char[][] nextGrid(int r) 
		{
			char[][] grid = new char[r][];
			for(int i = 0; i < r; i++)
				grid[i] = next().toCharArray();
			return grid;
		}
		
		public static <T> T fill(T arreglo, int val)
		{
			if(arreglo instanceof Object[])
			{
				Object[] a = (Object[]) arreglo;
				for(Object x : a)
					fill(x, val);
			}
			else if(arreglo instanceof int[])
				Arrays.fill((int[]) arreglo, val);
			else if(arreglo instanceof double[])
				Arrays.fill((double[]) arreglo, val);
			else if(arreglo instanceof long[])
				Arrays.fill((long[]) arreglo, val);
			return arreglo;
		}
		
		<T> T[] nextObjectArray(Class <T> clazz, int size)
		{
			@SuppressWarnings("unchecked")
			T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
			for(int c = 0; c < 3; c++)
			{
				Constructor <T> constructor;
				try 
				{
					if(c == 0)
						constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
					else if(c == 1)
						constructor = clazz.getDeclaredConstructor(Scanner.class);
					else
						constructor = clazz.getDeclaredConstructor();
				} 
				catch(Exception e)
				{
					continue;
				}
				try
				{
					for(int i = 0; i < result.length; i++)
					{
						if(c == 0)
							result[i] = constructor.newInstance(this, i);
						else if(c == 1)
							result[i] = constructor.newInstance(this);
						else
							result[i] = constructor.newInstance();	
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
				return result;
			}
			throw new RuntimeException("Constructor not found");
		}
		
		public void printLine(int... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(long... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(int prec, double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.printf("%." + prec + "f", vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.printf(" %." + prec + "f", vals[i]);
				System.out.println();
			}
		}
		
		public Collection <Integer> wrap(int[] as)
		{
			ArrayList <Integer> ans = new ArrayList <Integer> ();
			for(int i : as)
				ans.add(i);
			return ans;
		}
		
		public int[] unwrap(Collection <Integer> collection)
		{
			int[] vals = new int[collection.size()];
			int index = 0;
			for(int i : collection) vals[index++] = i;
			return vals;
		}
	}
	
	static Node[] allNodes;
	
	static class Node implements Comparable <Node>
	{
		int id;
		int age;
		ArrayList <Node> directEmployees = new ArrayList <Node> ();
		BitSet employees;
		TreeSet <Node> myBosses = new TreeSet <Node> ();
		
		public Node(Scanner sc, int i)
		{
			id = i;
		}
		
		BitSet employees()
		{
			if(employees != null)
				return employees;
			employees = new BitSet();
			for(Node n : directEmployees)
			{
				employees.set(n.id);
				employees.or(n.employees());
			}
			return employees;
		}
		
		void update(int newAge)
		{
			for(int i = employees.nextSetBit(0); i >= 0; i = employees.nextSetBit(i + 1))
				allNodes[i].myBosses.remove(this);
			age = newAge;
			for(int i = employees.nextSetBit(0); i >= 0; i = employees.nextSetBit(i + 1))
				allNodes[i].myBosses.add(this);
		}

		@Override
		public int compareTo(Node o)
		{
			if(age == o.age)
				return id - o.id;
			else
				return age - o.age;
		}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer nTemp = sc.nextInteger();
			if(nTemp == null) return;
			int n = nTemp;
			int m = sc.nextInt();
			int o = sc.nextInt();
			allNodes = sc.nextObjectArray(Node.class, n);
			int[] ages = sc.nextIntArray(n);
			for(int i = 0; i < m; i++)
			{
				int x = sc.nextInt() - 1;
				int y = sc.nextInt() - 1;
				allNodes[x].directEmployees.add(allNodes[y]);
			}
			for(int i = 0; i < n; i++)
				allNodes[i].employees();
			for(int i = 0; i < n; i++)
				allNodes[i].update(ages[i]);
			int[] realPosition = new int[n];
			for(int i = 0; i < n; i++)
				realPosition[i] = i;
			for(int i = 0; i < o; i++)
			{
				if(sc.next().equals("T"))
				{
					int a = sc.nextInt() - 1;
					int b = sc.nextInt() - 1;
					int ageA = allNodes[realPosition[a]].age;
					int ageB = allNodes[realPosition[b]].age;
					allNodes[realPosition[a]].update(ageB);
					allNodes[realPosition[b]].update(ageA);
					int tmp = realPosition[a];
					realPosition[a] = realPosition[b];
					realPosition[b] = tmp;
				}
				else
				{
					int e = sc.nextInt() - 1;
					Node youngestBoss = allNodes[realPosition[e]].myBosses.isEmpty() ? null : allNodes[realPosition[e]].myBosses.first();
					System.out.println(youngestBoss == null ? "*" : youngestBoss.age);
				}
			}
		}
	}
}