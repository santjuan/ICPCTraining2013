import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class I {
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
	}
	
	static class Exam implements Comparable <Exam>
	{
		int cost;
		static int ids = 0;
		int id = ids++;
		int whenTaken;

		public Exam(int cost2) {
			cost = cost2;
		}

		@Override
		public int compareTo(Exam o) 
		{
			if(o.cost == cost)
				return id - o.id;
			return o.cost - cost;
		}
		
		@Override
		public String toString()
		{
			return cost + "";
		}
	}
	
	static class ExamList implements Comparable <ExamList>
	{
		TreeSet <Exam> exams = new TreeSet <Exam> ();
		int time;
		int offset;
		
		@Override
		public int compareTo(ExamList o) 
		{
			return time - o.time;
		}

		public void insert(ExamList x) 
		{
			if(x.exams.size() > exams.size())
			{
				x.time = time;
				listas.put(x.time, x);
				x.insert(this);
			}
			else
			{
				for(Exam e : x.exams)
				{
					e.cost += x.offset - offset;
					exams.add(e);
				}
			}
		}
	}
	
	static TreeMap <Integer, ExamList> listas = new TreeMap <Integer, ExamList> ();
	static boolean[] used = new boolean[2000000];
	
	static void insert(ExamList x, int position)
	{
		if(used[position])
		{
			x.offset++;
			insert(x, position + 1);
			return;
		}
		if(listas.containsKey(position))
		{
			ExamList toInsert = listas.get(position);
			toInsert.insert(x);
		}
		else
		{
			x.time = position;
			listas.put(position, x);
		}
	}

	static void process(int position)
	{
		ExamList current = listas.remove(position);
		used[position] = true;
		current.exams.pollFirst().whenTaken = position;
		if(!current.exams.isEmpty())
			insert(current, position);
	}
	
	public static void main(String[] args)
	{	
		Scanner sc = new Scanner(); 
		int n = sc.nextInt();
		Exam[] inOrder = new Exam[n];
		for(int i = 0; i < n; i++)
		{
			int time = sc.nextInt();
			int cost = sc.nextInt();
			Exam current = inOrder[i] = new Exam(cost);
			if(listas.containsKey(time))
				listas.get(time).exams.add(current);
			else
			{
				ExamList e = new ExamList();
				e.offset = 0;
				e.time = time;
				e.exams.add(current);
				listas.put(time, e);
			}
		}
		while(!listas.isEmpty())
			process(listas.firstKey());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++)
		{
			if(i != 0)
				sb.append(" ");
			sb.append(inOrder[i].whenTaken);
		}
		System.out.println(sb.toString());
	}
}