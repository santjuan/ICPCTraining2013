import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
 
public class LiveE
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
 
                public String[] nextStringArray(int n)
                {      
                        String[] vals = new String[n];
                        for(int i = 0; i < n; i++)
                                vals[i] = next();
                        return vals;
                }
               
                Integer nextInteger()
                {
                        String s = next();
                        if(s == null)
                                return null;
                        return Integer.parseInt(s);
                }
               
                int[][] nextIntMatrix(int n, int m)
                {
                        int[][] ans = new int[n][];
                        for(int i = 0; i < n; i++)
                                ans[i] = nextIntArray(m);
                        return ans;
                }
        }
       
        static boolean simular(double xl, double xu, int buscada, int depth)
        {
                if(depth == 8)
                        return false;
                double xr = (xl + xu) / 2;
                double fxr = funcion(xr);
                double fxl = funcion(xl);
                double actual = fxr * fxl;
                if(darEntero(actual) == 0)
                        return darEntero(xr) == buscada;
                if(actual < 0)
                        return simular(xl, xr, buscada, depth + 1);
                else
                        return simular(xr, xu, buscada, depth + 1);
        }
       
        private static double funcion(double x)
        {
                double ans = 1;
                for(int r : raices)
                        ans *= (x - r);
                return ans;
        }
 
        static int[] raices;
        static boolean[] raicesMalas = new boolean[10000];
        static int[] actuales = new int[256];
        static int tamActuales = 0;
       
        static void intentarAgregar(int left, int right)
        {
                if(left >= 0 && left <= 10000)
                {
                	double eval = funcion(left) * funcion(right);
                	if(darEntero(eval) != 0 && eval < 0)
                        actuales[tamActuales++] = left;
                }
        }
       
 
        static double EPS = 0;
 
        static int darEntero(double possible)
        {
                int entero = (int) Math.round(possible);
                if(Math.abs(entero - possible) <= EPS)
                        return entero;
                return -1;
        }
       
        static class Funcion
        {
                double left;
                double right;
                int depth;
               
                Funcion(double l, double r)
                {
                        left = l;
                        right = r;
                        depth = 1;
                }
               
                void solve(int ri, int raiz)
                {
                        if(depth == 8)
                                return;
                        double possible = (raiz - right * ri) / left;  
                        int entero = darEntero(possible);
                        if(entero != -1 && entero < ri && entero >= 0)
                        {
                                if(simular(entero, ri, raiz, 1))
                                        intentarAgregar(entero, ri);
                        }
                        darLeft().solve(ri, raiz);
                        darRight().solve(ri, raiz);
                }
               
                Funcion darLeft()
                {
                        Funcion ans = new Funcion(left + 1, right);
                        ans.left /= 2;
                        ans.right /= 2;
                        ans.depth = depth + 1;
                        return ans;
                }
               
                Funcion darRight()
                {
                        Funcion ans = new Funcion(left, right + 1);
                        ans.left /= 2;
                        ans.right /= 2;
                        ans.depth = depth + 1;
                        return ans;
                }
        }
       
        static int total = 0;
       
        static void solve(int right, int raiz)
        {
                new Funcion(0.5, 0.5).solve(right, raiz);
        }
       
        static void countDifferent()
        {
                Arrays.sort(actuales, 0, tamActuales);
                int cuenta = Math.min(tamActuales, 1);
                for(int i = 1; i < tamActuales; i++)
                {
                        if(actuales[i] != actuales[i - 1])
                                cuenta++;
                }
                total += cuenta;
        }
       
        static int solve(int[] rs, int clave)
        {
                raices = rs;
                total = 0;
                for(int right = 0; right <= 10000; right++)
                {
                        tamActuales = 0;
                        solve(right, clave);
                        countDifferent();
                }
                return total;
        }
       
        static int solveNaive(int[] rs, int clave)
        {
                raices = rs;
                int cuenta = 0;
                for(int right = 0; right <= 10000; right++)
                {
                        for(int left = 0; left < right; left++)
                        {
                                if(simular(left, right, clave, 1))
                                        cuenta++;
                        }
                }
                return cuenta;
        }
       
        public static void main(String[] args)
        {
                Scanner sc = new Scanner();
                int casos = sc.nextInt();
                for(int caso = 1; caso <= casos; caso++)
                {
                        StringTokenizer st = new StringTokenizer(sc.nextLine(), "- ()=x");
                        int[] raices = new int[st.countTokens() - 1];
                        int indice = 0;
                        while(st.hasMoreTokens())
                        {
                                int r = Integer.parseInt(st.nextToken());
                                if(r != 0)
                                        raices[indice++] = r;
                        }
                        int buscada = sc.nextInt();
                        System.out.println(solve(raices, buscada) /* + " " + solveNaive(raices, buscada)*/);
                }
        }
 
}