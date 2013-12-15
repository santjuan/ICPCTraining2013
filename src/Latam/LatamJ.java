import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class LatamJ 
{
	static class Scanner
    {
            char[] buffer;
            InputStreamReader isr;
            int p;
            int start;
            
            public Scanner()
            {
                    buffer = new char[50000001];
                    isr = new InputStreamReader(System.in);         
                    read(0);
            }
            
            void read(int init)
            {
                    try
                    {
                            int tam;
                            if((tam = isr.read(buffer, init, buffer.length - init)) < buffer.length - init)
                            {
                                    if(tam < 0)
                                            tam = 0;
                                    buffer[tam] = '\0';
                            }
                            else
                            	throw(new RuntimeException());
                    }
                    catch(Exception e)
                    {
                            throw(new RuntimeException());
                    }
            }
            
            public void readNext()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] <= ' ')
                            pos++;
                    if(pos == tam)
                            throw new RuntimeException();
                    start = pos;
                    while(pos < tam && buffer[pos] > ' ')
                            pos++;
                    if(pos == tam)
                        throw new RuntimeException();
                    p = pos;
            }
            
            public String next()
            {
                    readNext();
                    return new String(buffer, start, p - start);
            }
            
            
            public long nextLong()
            {
            	readNext();
            	int d = start;
            	int pos = p;
            	long r = 0;
            	boolean n = buffer[d] == '-';
            	if(n)
            		d++;
            	r -= buffer[d++] - '0';
            	while (d < pos && (r = (r << 1) + (r << 3)) <= 0)
            		r -= buffer[d++] - '0';
            	return n ? r : -r;
            }
            
            public int nextInt()
            {
                    return (int) nextLong();
            }
            
            public double nextDouble()
            {
                    return Double.parseDouble(next());
            }
            
            public String nextLine()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] != '\n')
                            pos++;
                    if(pos == tam)
                    {
                            read(0);
                            return nextLine();
                    }
                    start = ++pos;
                    while(pos < tam && buffer[pos] != '\n')
                            pos++;
                    if(pos >= tam)
                    {
                            System.arraycopy(buffer, start, buffer, 0, buffer.length - start);
                            read(buffer.length - start);
                            return nextLine();
                    }
                    p = pos;
                    return new String(buffer, start, p - start);
            }
            
            public boolean EOF()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] <= ' ')
                            if(buffer[pos++] == '\0')
                                    return true;
                    pos++;
                    if(pos >= tam)
                    {
                            if(p == 0)
                                    return false;
                            System.arraycopy(buffer, p, buffer, 0, buffer.length - p);
                            read(buffer.length - p);
                            p = 0;
                            return EOF();
                    }
                    return false;
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
    			try
    			{
    				int n = nextInt();
    				return n;
    			}
    			catch(Exception e)
    			{
    				return null;
    			}
    		}
    		
    		public Long nextLLong()
    		{
    			try
    			{
    				long n = nextLong();
    				return n;
    			}
    			catch(Exception e)
    			{
    				return null;
    			}
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
	
	
	static class Entry
	{
		Node father;
		int idInList;
	}
	
	static class Answer implements Comparable <Answer>
	{
		int worst;
		Node node;
		
		public Answer(int w, Node n) 
		{
			worst = w;
			node = n;
		}

		@Override
		public int compareTo(Answer o) 
		{
			if(worst != o.worst)
				return o.worst - worst;
			return o.node.id - node.id;
		}
	}
	
	static final Node[] inOrder = new Node[40000];
	static int inOrderSize = 0;
	
	static class Node
	{
		int id;
		ArrayList <Node> nodes = new ArrayList <Node> ();
		Node father = null;
		Answer worstAnswer;
		Answer secondWorstAnswer;
		int firstPassAnswer;
		int secondPassParameter;
		int myWorst;
		
		public Node(Scanner sc, int i)
		{
			id = i;
		}
		
		void firstPassA()
		{
			for(Node n : nodes)
				if(n != father)
				{
					n.father = this;
					inOrder[inOrderSize++] = n;
				}
		}

		
		private void trySetAnswer(Answer answer) 
		{
			if(worstAnswer == null)
				worstAnswer = answer;
			else if(answer.worst > worstAnswer.worst)
			{
				secondWorstAnswer = worstAnswer;
				worstAnswer = answer;
			}
			else if(secondWorstAnswer == null || answer.worst > secondWorstAnswer.worst)
				secondWorstAnswer = answer;
		}
		
		void firstPassB()
		{
			for(Node n : nodes)
			{
				if(n != father)
				{
					Answer answer = new Answer(n.firstPassAnswer, n);
					trySetAnswer(answer);
				}
			}
			if(worstAnswer == null)
				firstPassAnswer = 1;
			else
				firstPassAnswer = worstAnswer.worst + 1;
		}

		int getWorstWithout(Node node)
		{
			if(worstAnswer == null)
				return 0;
			else if(worstAnswer.node == node)
			{
				if(secondWorstAnswer == null)
					return 0;
				int sol = secondWorstAnswer.worst;
				return sol;
			}
			else
				return worstAnswer.worst;
		}
		
		void secondPass()
		{
			if(father == null)
			{
				for(Node n : nodes)
					n.secondPassParameter = getWorstWithout(n) + 1;
				if(worstAnswer == null)
					myWorst = 0;
				else
					myWorst = worstAnswer.worst;
			}
			else
			{
				for(Node n : nodes)
				{
					if(n == father)
						continue;
					n.secondPassParameter = Math.max(secondPassParameter + 1, getWorstWithout(n) + 1);
				}
				myWorst = Math.max(secondPassParameter, worstAnswer == null ? 0 : worstAnswer.worst);
			}
		}
	}
	
	
	public static Node[] readGraph(Scanner sc, int n)
	{
		Node[] nodesN = new Node[n];
		for(int i = 0; i < n; i++)
			nodesN[i] = new Node(sc, i);
		for(int i = 0; i < n - 1; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			nodesN[a].nodes.add(nodesN[b]);
			nodesN[b].nodes.add(nodesN[a]);
		}
		inOrderSize = 0;
		inOrder[inOrderSize++] = nodesN[0];
		for(int i = 0; i < inOrderSize; i++)
			inOrder[i].firstPassA();
		for(int i = inOrderSize - 1; i >= 0; i--)
			inOrder[i].firstPassB();
		for(int i = 0; i < inOrderSize; i++)
			inOrder[i].secondPass();
		return nodesN;
	}
	


	private static void generateIntegral(int[] countN)
	{
		for(int i = 1; i < countN.length; i++)
			countN[i] += countN[i - 1];
	}
	
	private static long[] generateIntegeralDot(int[] countQ) 
	{
		long[] ans = new long[countQ.length];
		ans[0] = 0;
		for(int i = 1; i < ans.length; i++)
			ans[i] = ans[i - 1] + countQ[i] * ((long) i);
		return ans;
	}

	private static long integralBetween(int[] integralVector, int i, int j)
	{
		if(i < 0 || j < 0)
			return 0;
		if(i == 0)
			return integralVector[j];
		else
			return integralVector[j] - integralVector[i - 1];
	}


	private static long integralBetween(long[] integralVector, int i, int j)
	{
		if(i < 0 || j < 0)
			return 0;
		if(i == 0)
			return integralVector[j];
		else
			return integralVector[j] - integralVector[i - 1];
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
//		System.setIn(new FileInputStream("f.in"));
		Scanner sc = new Scanner();
		while(true)
		{
			try
			{
				Integer nT = sc.nextInteger();
				if(nT == null)
					return;
				int n = nT;
				int q = sc.nextInt();
				Node[] nodesN;
				Node[] nodesQ;
				try
				{
					nodesN = readGraph(sc, n);
					nodesQ = readGraph(sc, q);
				}
				catch(Exception e)
				{
					return;
				}
				int[] countN = new int[Math.max(n + 1, q + 1) + 1];
				int[] countQ = new int[Math.max(n + 1, q + 1) + 1];
				int worstN = 0;
				int worstQ = 0;
				for(Node x : nodesN)
				{
					countN[x.myWorst]++;
					worstN = Math.max(worstN, x.myWorst);
				}
				for(Node x : nodesQ)
				{
					countQ[x.myWorst]++;
					worstQ = Math.max(worstQ, x.myWorst);
				}
				int[] countQBefore = countQ.clone();
				generateIntegral(countN);
				generateIntegral(countQ);
				long[] dotP = generateIntegeralDot(countQBefore);
				long expected = 0;
				int worstBoth = Math.max(worstN,  worstQ);
				try
				{
					for(Node x : nodesN)
					{
						long expectedThis = 0;
						int y = x.myWorst;
						long countLess = integralBetween(countQ, 0, worstBoth - 1 - y);
						int starting = worstBoth - y;
						int to = countQ.length - 1;
						expectedThis += countLess * worstBoth;
						long countMore = q - countLess;
						expectedThis += countMore * (y + 1);
						expectedThis += integralBetween(dotP, starting, to);
						expected += expectedThis;
					}
				}
				catch(Throwable e)
				{
					return;
				}
				double expectedAns = expected;
				expectedAns /= n;
				expectedAns /= q;
				String ans = String.format("%.3f", expectedAns).replace(",", ".");
				System.out.println(ans);
			}
			catch(Throwable t)
			{
				return;
			}
		}
	}
}