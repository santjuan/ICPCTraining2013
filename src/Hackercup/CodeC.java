import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeC 
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

	static class PushCommand implements Comparable <PushCommand>
	{
		int number;
		String order = null;
		
		
		PushCommand(int n)
		{
			number = n;
		}

		@Override
		public int compareTo(PushCommand o)
		{
			return o.number - number;
		}
	}
	
	static class PollCommand
	{
		ArrayList <String> orders = new ArrayList <String> ();
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Object[] commands = new Object[n];
		int index = 0;
		for(int i : sc.nextIntArray(n))
		{
			if(i == 0)
				commands[index++] = new PollCommand();
			else
				commands[index++] = new PushCommand(i);
		}
		PriorityQueue <PushCommand> pq = new PriorityQueue <PushCommand> ();
		for(Object o : commands)
		{
			if(o instanceof PollCommand)
			{
				PollCommand command = (PollCommand) o;
				if(!pq.isEmpty())
				{
					pq.poll().order = "pushStack";
					command.orders.add("popStack");
				}
				if(!pq.isEmpty())
				{
					pq.poll().order = "pushFront";
					command.orders.add("popFront");
				}
				if(!pq.isEmpty())
				{
					pq.poll().order = "pushQueue";
					command.orders.add("popQueue");
				}
				pq.clear();
			}
			else
			{
				PushCommand command = (PushCommand) o;
				pq.add(command);
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int stackSize = 0;
		index = 0;
		for(Object o : commands)
		{
			index++;
			if(o instanceof PollCommand)
			{
				PollCommand command = (PollCommand) o;
				String ans = command.orders.size() + "";
				for(String s : command.orders)
				{
					ans += " " + s;
					if(s.equals("popStack"))
						stackSize--;
					if(stackSize < 0)
						throw new RuntimeException((index - 1) + "");
				}
				bw.write(ans);
				bw.write('\n');
			}
			else
			{
				PushCommand command = (PushCommand) o;
				if(command.order == null)
					command.order = "pushBack";
				if(command.order.equals("pushStack"))
					stackSize++;
				bw.write(command.order);
				bw.write('\n');
			}
		}
		bw.flush();
		bw.close();
	}
}