import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class B 
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
	}
	
	static int[] numbers = new int[12];
	
	
	public static int recurseSolve(int[] aAsignar, int taken, int currentNumber)
	{
		if(currentNumber == 8)
		{
			int suma = aAsignar[10];
			int ocho = suma - aAsignar[1] - aAsignar[5] - aAsignar[7];
			int nueve = suma - aAsignar[4] - aAsignar[6] - aAsignar[7];
			if(ocho == nueve)
				return 0;
			int sumaOk = aAsignar[8] + aAsignar[9] + ocho + nueve;
			if(sumaOk != suma)
				return 0;
			boolean pailaOcho = true;
			boolean pailaNueve = true;
			for(int i = 0; i < 12; i++)
			{
				if(numbers[i] == ocho && ((taken & (1 << i)) == 0))
					pailaOcho = false;
				if(numbers[i] == nueve && ((taken & (1 << i)) == 0))
					pailaNueve = false;
			}
			return !(pailaOcho || pailaNueve) ? 1 : 0;
		}
		else
		{
			int count = 0;
			for(int i = 0; i < 12; i++)
				if((taken & (1 << i)) == 0)
				{
					int nextTaken = taken | (1 << i);
					aAsignar[currentNumber] = numbers[i];
					if(currentNumber == 5)
					{
						int suma = aAsignar[1] + aAsignar[3] + aAsignar[4] - aAsignar[0] - aAsignar[5];
						boolean paila = true;
						for(int j = 0; j < 12; j++)
						{
							if(numbers[j] == suma && ((nextTaken & (1 << j)) == 0) && j != i)
							{
								paila = false;
								nextTaken |= (1 << j);
								aAsignar[8] = suma;
								break;
							}
						}
						if(paila)
							continue;
					}
					if(currentNumber == 6)
					{
						int sumaTotal = aAsignar[1] + aAsignar[2] + aAsignar[3] + aAsignar[4];
						int suma = aAsignar[1] + aAsignar[2] + aAsignar[4] - aAsignar[0] - aAsignar[6];
						boolean paila = true;
						for(int j = 0; j < 12; j++)
						{
							if(numbers[j] == suma && ((nextTaken & (1 << j)) == 0) && j != i)
							{
								paila = false;
								nextTaken |= (1 << j);
								aAsignar[9] = suma;
								aAsignar[10] = sumaTotal;
								break;
							}
						}
						if(paila)
							continue;
					}
					count += recurseSolve(aAsignar, nextTaken, currentNumber + 1);
				}
			return count;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			boolean fin = true;
			for(int i = 0; i < 12; i++)
			{
				numbers[i] = sc.nextInt();
				fin &= numbers[i] == 0;
			}
			if(fin)
				return;
			int ans = recurseSolve(new int[11], 0, 0);
			System.out.println(ans / 12);
		}
	}
}