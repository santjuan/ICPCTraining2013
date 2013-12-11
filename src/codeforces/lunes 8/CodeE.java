import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class CodeE
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
		
		public String[] nextStringArray(int n)
		{
			String[] res = new String[n];
			for(int i = 0; i < res.length; i++)
				res[i] = next();
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

	static class Statue
	{
		int x;
		int y;
		
		public Statue(int xx, int yy) 
		{
			x = xx;
			y = yy;
		}

		Statue getPosition(int turn)
		{
			int nextX = x + (turn + 1) / 2;
			if(nextX > 7)
				return null;
			return new Statue(nextX, y);
		}
	}
	
	static class Entrada
	{
		int x;
		int y;
		int turno;
		
		public Entrada(int x, int y, int turno) 
		{
			this.x = x;
			this.y = y;
			this.turno = turno;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		ArrayList <Statue> statues = new ArrayList <Statue> ();
		char[][] board = new char[8][];
		for(int i = 0; i < 8; i++)
			board[i] = sc.next().toCharArray();
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
			{
				if(board[i][j] == 'S')
					statues.add(new Statue(i, j));
			} 
		boolean[][][] visitado = new boolean[8][8][200];
		LinkedList <Entrada> cola = new LinkedList <Entrada> ();
		visitado[7][0][0] = true;
		cola.add(new Entrada(7, 0, 0));
		outer:
		while(!cola.isEmpty())
		{
			Entrada actual = cola.poll();
			for(Statue s : statues)
			{
				Statue pA = s.getPosition(actual.turno);
				if(pA != null && pA.x == actual.x && pA.y == actual.y)
					continue outer;
			}
			if(actual.x == 0 && actual.y == 7)
			{
				System.out.println("WIN");
				return;
			}
			boolean unico = (actual.turno & 1) == 1;
			for(int i = unico ? 0 : -1; i < (unico ? 1 : 2); i++)
				for(int j = unico ? 0 : -1; j < (unico ? 1 : 2); j++)
				{
					int xSig = actual.x + i;
					int ySig = actual.y + j;
					if(xSig < 0 || xSig > 7 || ySig < 0 || ySig > 7)
						continue;
					if(visitado[xSig][ySig][actual.turno + 1])
						continue;
					boolean paila = false;
					for(Statue s : statues)
					{
						Statue pA = s.getPosition(actual.turno);
						if(pA != null && pA.x == xSig && pA.y == ySig)
							paila = true;
					}
					if(paila)
						continue;
					visitado[xSig][ySig][actual.turno + 1] = true;
					cola.add(new Entrada(xSig, ySig, actual.turno + 1));
				}
		}
		System.out.println("LOSE");
	}

}
