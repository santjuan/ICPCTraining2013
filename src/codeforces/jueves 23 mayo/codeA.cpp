#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>

using namespace std;

int n;
int m;

struct FordFulkerson
{
	unsigned char capacity[102][102];
	bool visited[102];
	int source;
	int sink;
	int primeraPersona;
	int ultimaPersona;
	int primerProyecto;
	int ultimoProyecto;
	
	int augment(int x, int min)
	{
		if(x == sink)
			return min;
		visited[x] = true;
		if(x == source)
		{
			for(int i = primeraPersona; i <= ultimaPersona; i++)
			{
				if(!visited[i] && capacity[x][i])
				{
					int ret = augment(i, 1);
					if(ret)
					{
						capacity[x][i] -= ret;
						capacity[i][x] += ret;
						return ret;
					}
				}
			}
			return 0;
		}
		else if(x >= primeraPersona && x <= ultimaPersona)
		{
			for(int i = primerProyecto; i <= ultimoProyecto; i++)
			{
				if(!visited[i] && capacity[x][i])
				{
					int ret = augment(i, 1);
					if(ret)
					{
						capacity[x][i] -= ret;
						capacity[i][x] += ret;
						return ret;
					}
				}
			}
			return 0;
		}
		else
		{
			for(int i = sink; i <= sink; i++)
			{
				if(!visited[i] && capacity[x][i])
				{
					int ret = augment(i, 1);
					if(ret)
					{
						capacity[x][i] -= ret;
						capacity[i][x] += ret;
						return ret;
					}
				}
			}
			for(int i = primeraPersona; i <= ultimaPersona; i++)
			{

				if(!visited[i] && capacity[x][i])
				{
					int ret = augment(i, 1);
					if(ret)
					{
						capacity[x][i] -= ret;
						capacity[i][x] += ret;
						return ret;
					}
				}
			}
			return 0;
		}
	}
	
	int maxFlow()
	{
		int ret = 0;
		while(true)
		{
			memset(visited, false, sizeof(visited));
			int flow = augment(source, 1);
			if(!flow)
				return ret;
			ret += flow;
		}
	}
	
	void clear() 
	{
		memset(capacity, 0, sizeof(capacity));
	}
	
};

vector <int> preferencias [50];

int maximo(int* escogidos, int* proyectos, int cual, int limite, bool validar, FordFulkerson & ff)
{
	int source = 0;
	int sink = 1;
	int primeraPersona = 2;
	int primerProyecto = 2 + n;
	ff.clear();
	for(int i = 0; i < n; i++)
		if(escogidos[i] == -1)
			ff.capacity[source][primeraPersona + i] = 1;
	for(int i = 0; i < m; i++)
		if(proyectos[i] > 0)
			ff.capacity[primerProyecto + i][sink] = proyectos[i];
	for(int i = 0; i < n; i++)
		if(escogidos[i] == -1)
		{
			int indice = 0;
			for(int indice = 0; indice < preferencias[i].size(); indice++)
			{
				if(i == cual && indice > limite)
					break;
				int j = preferencias[i][indice];
				if(proyectos[j] > 0)
					ff.capacity[primeraPersona + i][primerProyecto + j] = 1;		
			}
		}
	int ans = ff.maxFlow();
	if(validar && ff.capacity[primeraPersona + cual][source] == 0)
		return -1;
	else
		return ans;
}

int busquedaBinaria(int a, int b, int* escogidos, int* proyectos, int cual, int buscado, FordFulkerson & ff)
{
	if(a == b)
		return maximo(escogidos, proyectos, cual, a, true, ff) == buscado ? a : -1;
	int mid = (a + b) >> 1;
	if(maximo(escogidos, proyectos, cual, mid, true, ff) == buscado)
		return busquedaBinaria(a, mid, escogidos, proyectos, cual, buscado, ff);
	else
		return busquedaBinaria(mid + 1, b, escogidos, proyectos, cual, buscado, ff);
}

int main()
{
	int casos;
	scanf("%d", &casos);
	for(int caso = 1; caso <= casos; caso++)
	{
		scanf("%d %d", &n, &m);
		FordFulkerson ff;
		ff.source = 0;
		ff.sink = 1;
		ff.primeraPersona = 2;
		ff.primerProyecto = 2 + n;
		ff.ultimaPersona = ff.primerProyecto - 1;
		ff.ultimoProyecto = n + m + 1;
		int proyectos[m];
		for(int i = 0; i < m; i++)
		{
			int k;
			scanf("%d", &k);
			proyectos[i] = k;
		}
		for(int i = 0; i < n; i++)
		{
			preferencias[i].clear();
			int k;
			scanf("%d", &k);
			for(int j = 0; j < k; j++)
			{
				int t;
				scanf("%d", &t);
				preferencias[i].push_back(t - 1);
			}
		}
		int escogidos[n];
		for(int i = 0; i < n; i++)
			escogidos[i] = -1;
		int maxFlow = maximo(escogidos, proyectos, 0, preferencias[0].size(), false, ff);
		int buscado = maxFlow;
		for(int i = 0; i < n; i++, ff.primeraPersona++)
		{
			if(buscado == 0)
				break;
			if(preferencias[i].size() == 0)
				continue;
			int cual = busquedaBinaria(0, preferencias[i].size() - 1, escogidos, proyectos, i, buscado, ff);
			if(cual != -1)
			{
				escogidos[i] = preferencias[i][cual];
				proyectos[preferencias[i][cual]]--;
				buscado--;
			}
		}
		printf("Case #%d:\n", caso);
		printf("%d applicant(s) can be hired.\n", maxFlow);
		for(int i = 0; i < n; i++)
			if(escogidos[i] != -1)
				printf("%d %d\n", (i + 1), (escogidos[i] + 1));
	}
}