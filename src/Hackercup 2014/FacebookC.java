import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class FacebookC
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
                res[i] = nextDouble();
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

        public Integer nextInteger()
        {
            String s = next();
            if(s == null)
                return null;
            return Integer.parseInt(s);
        }

        public int[][] nextIntMatrix(int n, int m)
        {
            int[][] ans = new int[n][];
            for(int i = 0; i < n; i++)
                ans[i] = nextIntArray(m);
            return ans;
        }

        public char[][] nextGrid(int r)
        {
            char[][] grid = new char[r][];
            for(int i = 0; i < r; i++)
                grid[i] = next().toCharArray();
            return grid;
        }

        public static <T> T fill(T arreglo, int val)
        {
            if(arreglo instanceof Object[])
            {
                Object[] a = (Object[]) arreglo;
                for(Object x : a)
                    fill(x, val);
            }
            else if(arreglo instanceof int[])
                Arrays.fill((int[]) arreglo, val);
            else if(arreglo instanceof double[])
                Arrays.fill((double[]) arreglo, val);
            else if(arreglo instanceof long[])
                Arrays.fill((long[]) arreglo, val);
            return arreglo;
        }

        <T> T[] nextObjectArray(Class<T> clazz, int size)
        {
            @SuppressWarnings("unchecked")
            T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
            for(int c = 0; c < 3; c++)
            {
                Constructor<T> constructor;
                try
                {
                    if(c == 0)
                        constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
                    else if(c == 1)
                        constructor = clazz.getDeclaredConstructor(Scanner.class);
                    else
                        constructor = clazz.getDeclaredConstructor();
                }
                catch(Exception e)
                {
                    continue;
                }
                try
                {
                    for(int i = 0; i < result.length; i++)
                    {
                        if(c == 0)
                            result[i] = constructor.newInstance(this, i);
                        else if(c == 1)
                            result[i] = constructor.newInstance(this);
                        else
                            result[i] = constructor.newInstance();
                    }
                }
                catch(Exception e)
                {
                    throw new RuntimeException(e);
                }
                return result;
            }
            throw new RuntimeException("Constructor not found");
        }

        public void printLine(int... vals)
        {
            if(vals.length == 0)
                System.out.println();
            else
            {
                System.out.print(vals[0]);
                for(int i = 1; i < vals.length; i++)
                    System.out.print(" ".concat(String.valueOf(vals[i])));
                System.out.println();
            }
        }

        public void printLine(long... vals)
        {
            if(vals.length == 0)
                System.out.println();
            else
            {
                System.out.print(vals[0]);
                for(int i = 1; i < vals.length; i++)
                    System.out.print(" ".concat(String.valueOf(vals[i])));
                System.out.println();
            }
        }

        public void printLine(double... vals)
        {
            if(vals.length == 0)
                System.out.println();
            else
            {
                System.out.print(vals[0]);
                for(int i = 1; i < vals.length; i++)
                    System.out.print(" ".concat(String.valueOf(vals[i])));
                System.out.println();
            }
        }

        public void printLine(int prec, double... vals)
        {
            if(vals.length == 0)
                System.out.println();
            else
            {
                System.out.printf("%." + prec + "f", vals[0]);
                for(int i = 1; i < vals.length; i++)
                    System.out.printf(" %." + prec + "f", vals[i]);
                System.out.println();
            }
        }

        public Collection<Integer> wrap(int[] as)
        {
            ArrayList<Integer> ans = new ArrayList<Integer>();
            for(int i : as)
                ans.add(i);
            return ans;
        }

        public int[] unwrap(Collection<Integer> collection)
        {
            int[] vals = new int[collection.size()];
            int index = 0;
            for(int i : collection)
                vals[index++] = i;
            return vals;
        }
    }

    static int n, m;
    static boolean[][] grid;

    static int next(int fromI, int fromJ, int toI, int toJ, boolean used)
    {
        if(toI < 0 || toJ < 0 || toI >= n || toJ >= m || grid[toI][toJ])
            return 0;
        if(used)
            return dp(toI, toJ, false, false, true);
        else
            return dp(toI, toJ, fromI == toI, fromJ == toJ, false);
    }

    static int[][][][][] dp;

    static int dp(int i, int j, boolean sameRow, boolean sameColumn, boolean used)
    {
        if(grid[i][j])
            return 0;
        if(dp[i][j][sameRow ? 1 : 0][sameColumn ? 1 : 0][used ? 1 : 0] != -1)
            return dp[i][j][sameRow ? 1 : 0][sameColumn ? 1 : 0][used ? 1 : 0];
        int best = 1;
        best = Math.max(best, 1 + next(i, j, i + 1, j, used));
        best = Math.max(best, 1 + next(i, j, i, j + 1, used));
        if(!used)
        {
            if(!sameRow)
            {
                int count = 1;
                for(int k = j - 1; k >= 0; k--)
                {
                    if(grid[i][k])
                        break;
                    count++;
                    best = Math.max(best, count + next(i, j, i + 1, k, true));
                }
            }
            if(!sameColumn)
            {
                int count = 1;
                for(int k = i - 1; k >= 0; k--)
                {
                    if(grid[k][j])
                        break;
                    count++;
                    best = Math.max(best, count + next(i, j, k, j + 1, true));
                }
            }
        }
        return dp[i][j][sameRow ? 1 : 0][sameColumn ? 1 : 0][used ? 1 : 0] = best;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        System.setIn(new FileInputStream("C.txt"));
        System.setOut(new PrintStream("C.out"));
        Scanner sc = new Scanner();
        int t = sc.nextInt();
        for(int c = 1; c <= t; c++)
        {
            n = sc.nextInt();
            m = sc.nextInt();
            char[][] gridChar = sc.nextGrid(n);
            grid = new boolean[n][m];
            for(int i = 0; i < n; i++)
                for(int j = 0; j < m; j++)
                    grid[i][j] = gridChar[i][j] == '#';
            dp = Scanner.fill(new int[n][m][2][2][2], -1);
            System.out.println("Case #" + c + ": " + dp(0, 0, false, false, false));
        }
    }
}