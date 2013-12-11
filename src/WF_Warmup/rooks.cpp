#include <cassert>
#include <cstdio>
#include <cmath>
#include <cstdio>
#include <algorithm>

using namespace std;

struct cpx
{
	cpx(){}
	cpx(double aa):a(aa){}
	cpx(double aa, double bb):a(aa),b(bb){}
	double a;
	double b;
	double modsq(void) const
	{
		return a * a + b * b;
	}
	cpx bar(void) const
	{
		return cpx(a, -b);
	}
};

cpx operator +(cpx a, cpx b)
{
	return cpx(a.a + b.a, a.b + b.b);
}

cpx operator -(cpx a, cpx b)
{
	return cpx(a.a - b.a, a.b - b.b);
}

cpx operator *(cpx a, cpx b)
{
	return cpx(a.a * b.a - a.b * b.b, a.a * b.b + a.b * b.a);
}

cpx operator /(cpx a, cpx b)
{
	cpx r = a * b.bar();
	return cpx(r.a / b.modsq(), r.b / b.modsq());
}

cpx EXP(double theta)
{
	return cpx(cos(theta),sin(theta));
}

const double two_pi = 4 * acos(0);


const double PI = 4.0*atan(1.0);

void fft(int n, double theta, cpx* a) {
	for (int m = n; m >= 2; m >>= 1) {
		int mh = m >> 1;
		for (int i = 0; i < mh; i++) {
			cpx w = EXP(i*theta);
			for (int j = i; j < n; j += m) {
				int k = j + mh;
				cpx x = a[j] - a[k];
				a[j] = a[j] + a[k];
				a[k] = w * x;
			}
		}
		theta *= 2;
	}
	int i = 0;
	for (int j = 1; j < n - 1; j++) {
		for (int k = n >> 1; k > (i ^= k); k >>= 1);
		if (j < i) swap(a[i], a[j]);
	}
}	

cpx a[300000];
cpx b[300000];
cpx c[300000];

long long convolucion(bool* filas, bool* columnas, int* diagonales, int nFilas, int nColumnas, int nDiagonales)
{
	int m = nFilas + nColumnas;
	int tam = 1;
	while(tam < m)
		tam <<= 1;
	for(int i = 0; i < nFilas; i++)
		a[i] = cpx(filas[i] ? 1 : 0, 0);
	for(int i = nFilas; i < tam; i++)
		a[i] = cpx(0, 0);
	for(int i = 0; i < nColumnas; i++)
		b[i] = cpx(columnas[nColumnas - i - 1] ? 1 : 0, 0);
	for(int i = nColumnas; i < tam; i++)
		b[i] = cpx(0, 0);
	fft(tam, two_pi / tam, a);
	fft(tam, two_pi / tam, b);
	for(int i = 0; i < tam; i++)
		c[i] = a[i] * b[i];
	fft(tam, -two_pi / tam, c);
	long long respuesta = 0;
	int limite = min(nDiagonales, tam);
	for(int i = 0; i < limite; i++)
		if(diagonales[i] == 1)
			respuesta += round(c[i].a / tam);
	return respuesta;
}


void generarIntegralImage(int* vals, int tamVals)
{
	for(int i = 1; i < tamVals; i++)
		vals[i] += vals[i - 1];
}

long long darSuma(int* vals, int a, int b)
{
	if(a == 0)
		return vals[b];
	return vals[b] - vals[a - 1];
}

bool filas[100100];
bool columnas[100100];
int diagonales[100100];

int main()
{
	int t;
	scanf("%d", &t);
	for(int caso = 1; caso <= t; caso++)
	{
		int rows;
		int cols;
		scanf("%d %d", &rows, &cols);
		for(int i = 0; i < rows; i++)
			filas[i] = false;
		for(int i = 0; i < cols; i++)
			columnas[i] = false;
		for(int i = 0; i < rows + cols; i++)
			diagonales[i] = 0;
		int n;
		scanf("%d", &n);
		for(int i = 0; i < n; i++)
		{
			int r;
			int c;
			scanf("%d %d", &r, &c);
			r--;
			c--;
			filas[r] = true;
			columnas[c] = true;
			diagonales[r + (cols - c - 1)] = 1;
		}
		long long colsFree = 0;
		for(int i = 0; i < cols; i++)
			if(!columnas[i])
				colsFree++;
		long long rowsFree = 0;
		for(int i = 0; i < rows; i++)
			if(!filas[i])
				rowsFree++;
		long long acum = (cols - colsFree) * rows + (rows - rowsFree) * cols;
		int nDiagonales = rows + cols;
		for(int i = 0; i < cols; i++)
			if(diagonales[i] == 1)
				acum += min(i + 1, rows);
		for(int i = cols; i < nDiagonales; i++)
			if(diagonales[i] == 1)
				acum += min(nDiagonales - (i + 1), cols);
		int diagonalesNormales[nDiagonales];
		for(int i = 0; i < nDiagonales; i++)
			diagonalesNormales[i] = diagonales[i];
		generarIntegralImage(diagonales, nDiagonales);
		for(int i = 0; i < rows; i++)
			if(filas[i])
				acum -= (cols - colsFree);
		for(int i = 0; i < rows; i++)
			if(filas[i])
				acum -= darSuma(diagonales, i, i + cols - 1);
		for(int i = 0; i < cols; i++)
			if(columnas[i])
			{
				int iReal = cols - i - 1;
				acum -= darSuma(diagonales, iReal, iReal + rows - 1);
			}
		acum += convolucion(filas, columnas, diagonalesNormales, rows, cols, nDiagonales);
		printf("Case %d: %lld\n", caso, (((((long long) rows) * cols) - acum)));
	}
}
