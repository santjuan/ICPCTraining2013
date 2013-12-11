	#include <iostream>
	#include <cstdio>
	
	using namespace std;
	
	int maximo;
	float izquierda;
	float derecha;
	float sinCambio;
	int n;
	float dp[2002][1002][2];
	
	float dp1(int actual, int pasos, bool llego)
	{
		if(!llego && (actual + pasos < maximo))
			return 0.0f;
		if(dp[actual + n][pasos][llego ? 1 : 0] != -1)
			return dp[actual + n][pasos][llego ? 1 : 0];
		if(pasos == 0)
			return dp[actual + n][pasos][llego ? 1 : 0] = ((llego) || (actual == maximo)) ? 1.0f : 0.0f;
		if(actual == maximo)
			return dp[actual + n][pasos][llego ? 1 : 0] = izquierda * dp1(actual - 1, pasos - 1, true) + sinCambio * dp1(actual, pasos - 1, true);
		else
			return dp[actual + n][pasos][llego ? 1 : 0] = izquierda * dp1(actual - 1, pasos - 1, llego) + sinCambio * dp1(actual, pasos - 1, llego) + derecha * dp1(actual + 1, pasos - 1, llego);
	}
	
	void limpiar()
	{
		int lim = (n << 1) + 2;
		int lim1 = n + 2;
		for(int i = 0; i < lim; i++)
			for(int j = 0; j < lim1; j++)
				for(int k = 0; k < 2; k++)
					dp[i][j][k] = -1;
	}
	
	float resolver()
	{
		float ans = 0; 
		for(int i = 0; i <= n; i++)
		{
			limpiar();
			maximo = i;
			ans += dp1(0, n, false) * i;
		}
		return ans;
	}
	
	int main()
	{
		int t;
		cin >> t;
		for(int i = 0; i < t; i++)
		{
			int id;
			cin >> id;
			cin >> n;
			cin >> izquierda;
			cin >> derecha;
			sinCambio = 1.0 - izquierda - derecha;
			printf("%d %.4f\n", id, resolver());
		}
	}