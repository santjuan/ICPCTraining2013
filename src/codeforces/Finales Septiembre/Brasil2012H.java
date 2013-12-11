import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Brasil2012H 
{
	static class Scanner
    {
            char[] buffer;
            InputStreamReader isr;
            int p;
            int start;
            
            public Scanner()
            {
                    buffer = new char[15000000];
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
            
            boolean finished = false;
            public void readNext()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] <= ' ')
                            pos++;
                    if(pos == tam)
                    {
                    	   finished = true;
                           return;
                    }
                    start = pos;
                    while(pos < tam && buffer[pos] > ' ')
                            pos++;
                    if(pos == tam)
                    {
                            System.arraycopy(buffer, start, buffer, 0, buffer.length - start);
                            read(buffer.length - start);
                            p = start;
                            readNext();
                            return;
                    }
                    p = pos;
            }
            
            public String next()
            {
                    readNext();
                    return new String(buffer, start, p - start);
            }
            
            public Integer nextInteger()
            {
            	readNext();
            	if(finished) return null;
            	int d = start;
            	int pos = p;
            	long r = 0;
            	boolean n = buffer[d] == '-';
            	if(n)
            		d++;
            	r -= buffer[d++] - '0';
            	while (d < pos && (r = (r << 1) + (r << 3)) <= 0)
            		r -= buffer[d++] - '0';
            	return (int) (n ? r : -r);
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
    }
	
	static class FastArrayDeque
	{
		final Node[] queue;
		int head, tail;
		
		FastArrayDeque(int size)
		{
			queue = new Node[size];
			clear();
		}
		
		
		void add(Node val)
		{
			queue[++tail] = val;
		}
		
		Node poll()
		{
			return queue[head++];
		}
		
		void clear()
		{
			head = 0; tail = -1;
		}
		
		boolean isEmpty()
		{
			return head > tail;
		}
		
		int size()
		{
			if(isEmpty()) return 0;
			return tail - head + 1;
		}
	}
	
	static class Entry
	{
		final int[] sons;
		Integer hash;
		
		Entry(Node n)
		{
			int sonCount = n.edges.size();
			if(n.currentFather != null)
				sonCount--;
			sons = new int[sonCount];
			int currentId = 0;
			for(Node x : n.edges)
				if(x == n.currentFather)
					continue;
				else
					sons[currentId++] = x.uniqueId;
			Arrays.sort(sons);
		}
		
		@Override
		public int hashCode()
		{
			if(hash == null)
				hash = Arrays.hashCode(sons);
			return hash;
		}
		
		@Override
		public boolean equals(Object o) 
		{
			return Arrays.equals(sons, ((Entry) o).sons);
		}
	}
	
	static int currentId;
	static final HashMap <Entry, Integer> hash = new HashMap <Entry, Integer> ();
	
	static int getId(Node node)
	{
		Entry e = new Entry(node);
		Integer possible = hash.get(e);
		if(possible == null)
		{
			possible = currentId++;
			hash.put(e, possible);
		}
		return possible;
	}
	
	static class Node
	{
		final int id;
		int aliveEdges;
		int uniqueId;
		Node currentFather;
		final ArrayList <Node> edges = new ArrayList <Node> ();
		
		public Node(int i)
		{
			id = i;
		}

		public void defineCurrentId() 
		{
			uniqueId = getId(this);
		}
	}
	
	static class Tree
	{
		final Node[] nodes;
		final FastArrayDeque queue;
		final Node[] inOrder;
		
		Tree(Scanner sc, final int n)
		{
			nodes = new Node[n];
			for(int i = 0; i < n; i++)
				nodes[i] = new Node(i);
			queue = new FastArrayDeque(n);
			inOrder = new Node[n];
			final int n1 = n - 1;
			for(int i = 0; i < n1; i++)
			{
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				nodes[a].edges.add(nodes[b]);
				nodes[b].edges.add(nodes[a]);
			}
		}
		
		int bfsAssign(int root)
		{
			for(Node n : nodes)
			{
				n.currentFather = null;
				n.uniqueId = -1;
			}
			queue.clear();
			queue.add(nodes[root]);
			int inOrderId = nodes.length - 1;
			while(!queue.isEmpty())
			{
				Node n = queue.poll();
				inOrder[inOrderId--] = n;
				for(Node s : n.edges)
				{
					if(s != n.currentFather)
					{
						s.currentFather = n;
						queue.add(s);
					}
				}
			}
			for(Node n : inOrder)
				n.defineCurrentId();
			return nodes[root].uniqueId;
		}
		
		int[] getRoots()
		{
			int currentNodeCount = nodes.length;
			queue.clear();
			for(Node n : nodes)
			{
				n.aliveEdges = n.edges.size();
				if(n.aliveEdges == 1)
					queue.add(n);
			}
			while(currentNodeCount > 2)
			{
				Node n = queue.poll();
				for(Node x : n.edges)
				{
					x.aliveEdges--;
					if(x.aliveEdges == 1)
						queue.add(x);
				}
				n.aliveEdges = 0;
				currentNodeCount--;
			}
			int[] roots = new int[queue.size()];
			int id = 0;
			while(!queue.isEmpty())
				roots[id++] = queue.poll().id;
			return roots;
		}
	}

	static PrintStream originalOut = System.out;
	static long lastEntry, total = 0;
	
	static void startCount()
	{
		lastEntry = System.currentTimeMillis();
	}
	
	static void endCount()
	{
		total += System.currentTimeMillis() - lastEntry;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
//		System.setIn(new FileInputStream("cancer.in"));
//		System.setOut(new PrintStream("cancer.out"));
		Scanner sc = new Scanner();
		// startCount();
		while(true)
		{
			Integer nTmp = sc.nextInteger();
			if(nTmp == null)
			{
				// endCount();
				// originalOut.println(total);
				return;
			}
			int n = nTmp;
			Tree a = new Tree(sc, n);
			Tree b = new Tree(sc, n);
		//	currentId = 0;
	//		hash.clear();
			int[] aRoots = a.getRoots();
			int[] bRoots = b.getRoots();
			boolean ok = false;
			outer:
			for(int rootA : aRoots)
			{
				int idA = a.bfsAssign(rootA);
				for(int rootB : bRoots)
				{
					if(b.bfsAssign(rootB) == idA)
					{
						ok = true;
						break outer;
					}
				}
			}
			System.out.println(ok ? "S" : "N");
		}
	}
}