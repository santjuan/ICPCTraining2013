import java.util.Scanner;


public class Poker 
{
	static class Respuesta
	{
		double a;
		double b;
		int aEstado;
		int bEstado;
	}
	
	
	static Respuesta[][] dp = new Respuesta[301][301];
	
	public static Respuesta dp(int a, int b)
	{
		if(dp[a][b] != null)
			return dp[a][b];
		Respuesta tmp = new Respuesta();
		tmp.aEstado = a;
		tmp.bEstado = b;
		tmp.a = 0;
		tmp.b = 1;
		dp[a][b] = tmp;
		if(a == 0 || b == 0)
		{
			tmp.a = 0;
			tmp.b = 0;
			return tmp;
		}
		if(a == b)
		{
			tmp.a = 1;
			tmp.b = 0;
			return tmp;
		}
		Respuesta hijo;
		if(a > b)
			hijo = dp(a - b, 2 * b);
		else
			hijo = dp(2 * a, b - a);
		tmp.a = 0.5;
		tmp.b = 0;
		if(hijo.aEstado == a && hijo.bEstado == b && hijo.b != 0)
		{
			tmp.a += (hijo.a + 1) * 0.5;
			tmp.a /= (1 - (hijo.b * 0.5));
			tmp.b = 0;
			return tmp;
		}
		else
		{
			tmp.a += 0.5 * (hijo.a + 1);
			tmp.b += 0.5 * hijo.b;
			tmp.aEstado = hijo.aEstado;
			tmp.bEstado = hijo.bEstado;
			return tmp;
		}
	}
	

	static Respuesta[][] dp2 = new Respuesta[301][301];
	
	
	public static Respuesta dp2(int a, int b)
	{
		if(dp2[a][b] != null)
			return dp2[a][b];
		Respuesta tmp = new Respuesta();
		tmp.aEstado = a;
		tmp.bEstado = b;
		tmp.a = 0;
		tmp.b = 1;
		dp2[a][b] = tmp;
		if(a == 0 || b == 0)
		{
			tmp.a = b == 0 ? 1 : 0;
			tmp.b = 0;
			return tmp;
		}
		if(a == b)
		{
			tmp.a = 0.5;
			tmp.b = 0;
			return tmp;
		}
		Respuesta hijo;
		if(a > b)
			hijo = dp2(a - b, 2 * b);
		else
			hijo = dp2(2 * a, b - a);
		tmp.a = a > b ? 0.5 : 0;
		tmp.b = 0;
		if(hijo.aEstado == a && hijo.bEstado == b && hijo.b != 0)
		{
			tmp.a += hijo.a * 0.5;
			tmp.a /= (1 - (hijo.b * 0.5));
			tmp.b = 0;
			return tmp;
		}
		else
		{
			tmp.a += 0.5 * hijo.a;
			tmp.b += 0.5 * hijo.b;
			tmp.aEstado = hijo.aEstado;
			tmp.bEstado = hijo.bEstado;
			return tmp;
		}
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int i = 1; i <= tc; i++)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			dp = new Respuesta[301][301];
			dp2 = new Respuesta[301][301];
			System.out.println(String.format("Case %d: %.6f %.6f", i, dp(a, b).a, dp2(a, b).a).replace(",", "."));
		}
	}
}