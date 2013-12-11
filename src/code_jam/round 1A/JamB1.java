import java.io.FileNotFoundException;
import java.util.Scanner;


public class JamB1 
{
	
	static long[] vs;
	static Integer[][] dp;
	static long E;
	static long R;
	
	public static void main(String[] args) throws FileNotFoundException
	{
//		System.setOut(new PrintStream("b-testquick.out"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int caso = 1; caso <= T; caso++)
		{
			E = sc.nextLong();
			R = sc.nextLong();
			int n = sc.nextInt();
			vs = new long[n];
			for(int i = 0; i < n; i++)
				vs[i] = sc.nextLong();
			int actual = 0;
			long cuenta = 0;
			long energia = E;
			while(actual < n)
			{
				energia = Math.min(energia, E);
				if(cuenta < 0)
					System.out.println("paila");
				int siguiente = n;
				long este = vs[actual];
				for(int i = actual + 1; i < n; i++)
					if(vs[i] >= este)
					{
						siguiente = i;
						break;
					}
				if(siguiente == n)
				{
					cuenta += energia * vs[actual];
					energia = R;
					actual++;
					continue;
				}
				long dist = siguiente - actual;
				if(energia + dist * R < E)
				{
					actual = siguiente;
					energia = energia + dist * R;
					energia = Math.min(energia, E);
				}
				else
				{
					long energiaC = 0;
					int siguienteV = siguiente;
					for(int i = actual + 1; i <= siguiente; i++)
					{
						energiaC += R;
						if(energiaC >= E)
						{
							siguienteV = i;
							break;
						}
					}	
					long gasto = Math.min((energia + (siguienteV - actual) * R - E), E);
					gasto = Math.min(gasto, energia);
					if(gasto <= 0)
					{
						actual = actual + 1;
						energia += R;
						energia = Math.min(energia, E);
					}
					else
					{
						cuenta += gasto * vs[actual];
						energia = energia - gasto;
						energia += R;
						energia = Math.min(energia, E);
						actual = actual + 1;
					}
					
				}
			}
			System.out.println("Case #" + caso + ": " + cuenta);
		}
		sc.close();
	}
}
