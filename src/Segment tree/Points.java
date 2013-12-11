import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class Points 
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
	}
	
	static int[] M;
	static int[] A;
	
	static void update(int node, int b, int e, int pos, int val)
	{
		//if the current interval doesn't intersect 
		//the query interval return -1
		if (pos > e || pos < b)
			return;

		//if the current interval is included in 
		//the query interval return M[node]
		if (b == e)
		{
			A[pos] = val;
			M[node] = pos;
			return;
		}

		//compute the minimum position in the 
		//left and right part of the interval
		update(2 * node, b, (b + e) / 2, pos, val);
		update(2 * node + 1, (b + e) / 2 + 1, e, pos, val);
		if ((M[2 * node] == -1 ? Integer.MIN_VALUE : A[M[2 * node]]) >= (M[2 * node + 1] == -1 ? Integer.MIN_VALUE : A[M[2 * node + 1]]))
			M[node] = M[2 * node];
		else
			M[node] = M[2 * node + 1];
	}
	 
	static int query(int node, int b, int e, int i, int j, int value)
	{
		//if the current interval doesn't intersect 
		//the query interval return -1
		 if (i > e || j < b)
	          return -1;

		//if the current interval is included in 
		//the query interval return M[node]
		if(b == e)
			return M[node] == -1 ? -1 : A[M[node]] <= value ? -1 : M[node];
		if (b >= i && e <= j)
		{
			if(M[node] == -1 || A[M[node]] <= value)
				return -1;
			if(M[2 * node] != -1 && A[M[2 * node]] > value)
				return query(2 * node, b, (b + e) / 2, i, j, value);
			return query(2 * node + 1, (b + e) / 2 + 1, e, i, j, value);
		}

		//compute the minimum position in the 
		//left and right part of the interval
		int p1 = query(2 * node, b, (b + e) / 2, i, j, value);
		if(p1 != -1)
			return p1;
		return query(2 * node + 1, (b + e) / 2 + 1, e, i, j, value);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
	    //System.setOut(new PrintStream(new BufferedOutputStream(System.out), false));
		while(true)
		{
			int n = sc.nextInt();
			int[][] queries = new int[n][3];
			TreeSet <Integer> valores = new TreeSet <Integer> ();
			for(int i = 0; i < n; i++)
			{
				String cual = sc.next();
				int a = sc.nextInt();
				int b = sc.nextInt();
				queries[i][0] = cual.equals("add") ? 0 : cual.equals("remove") ? 1 : 2;
				queries[i][1] = a;
				queries[i][2] = b;
				if(queries[i][0] == 0)
					valores.add(a);
			}
			int index = 0;
			TreeMap <Integer,Integer> map = new TreeMap <Integer,Integer> ();
			TreeMap <Integer,Integer> mapI = new TreeMap <Integer,Integer> ();
			for(int e: valores)
			{
				map.put(e, index);
				mapI.put(index++, e);
			}
			@SuppressWarnings("unchecked")
			TreeSet <Integer> [] mapas = new TreeSet[map.size()];
			for(int i = 0; i < mapas.length; i++)
				mapas[i] = new TreeSet <Integer> ();
			M = new int[mapas.length * 4 + 4];
			A = new int[mapas.length];
			Arrays.fill(M, -1);
			Arrays.fill(A, -1);
			for(int i = 0; i < n; i++)
			{
				if(queries[i][0] == 0)
				{
					int indiceReal = map.get(queries[i][1]);
					int y = queries[i][2];
					mapas[indiceReal].add(y);
					update(1, 0, A.length - 1, indiceReal, mapas[indiceReal].last());
				}
				else if(queries[i][0] == 1)
				{
					int indiceReal = map.get(queries[i][1]);
					int y = queries[i][2];
					mapas[indiceReal].remove(y);
					update(1, 0, A.length - 1, indiceReal, mapas[indiceReal].isEmpty() ? -1 : mapas[indiceReal].last());
				}
				else
				{
					int indiceReal = map.higherEntry(queries[i][1]) == null ? -1 : map.higherEntry(queries[i][1]).getValue();
					int y = queries[i][2];
					int res = indiceReal == -1 ? -1 : query(1, 0, A.length - 1, indiceReal, A.length - 1, y);
					if(res == -1)
						System.out.println("-1");
					else
						System.out.println(mapI.get(res) + " " + mapas[res].higher(y));
				}
			}
			System.out.flush();
			return;
		}
	}
}
