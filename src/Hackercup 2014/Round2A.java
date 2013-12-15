import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Round2A
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

        <T> T[] nextObjectArray(Class <T> clazz, int size)
        {
            @SuppressWarnings("unchecked")
            T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
            for(int c = 0; c < 3; c++)
            {
                Constructor <T> constructor;
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

        public Collection <Integer> wrap(int[] as)
        {
            ArrayList <Integer> ans = new ArrayList <Integer>();
            for(int i : as)
                ans.add(i);
            return ans;
        }

        public int[] unwrap(Collection <Integer> collection)
        {
            int[] vals = new int[collection.size()];
            int index = 0;
            for(int i : collection)
                vals[index++] = i;
            return vals;
        }
    }

    static int[][] readBoards(Scanner sc, int n, int m)
    {
        long x1 = sc.nextLong();
        long a1 = sc.nextLong();
        long b1 = sc.nextLong();
        long c1 = sc.nextLong();
        long r1 = sc.nextLong();
        long x2 = sc.nextLong();
        long a2 = sc.nextLong();
        long b2 = sc.nextLong();
        long c2 = sc.nextLong();
        long r2 = sc.nextLong();
        int[] board1 = new int[n];
        int[] board2 = new int[m];
        board1[0] = (int) x1;
        board2[0] = (int) x2;
        int max = Math.max(n, m);
        for(int i = 1; i < max; i++)
        {
            if(i < n)
                board1[i] = (int) ((a1 * board1[(i - 1) % n] + b1 * board2[(i - 1) % m] + c1) % r1);
            if(i < m)
                board2[i] = (int) ((a2 * board1[(i - 1) % n] + b2 * board2[(i - 1) % m] + c2) % r2);
        }
        return new int[][] { board1, board2 };
    }

    static long solve(int[] board1, int[] board2, int n, int m)
    {
        int lastPut1 = -1;
        int lastPut2 = -1;
        TreeSet <Integer> needed1 = new TreeSet <Integer>();
        TreeSet <Integer> needed2 = new TreeSet <Integer>();
        HashSet <Integer> in1 = new HashSet <Integer>();
        HashSet <Integer> in2 = new HashSet <Integer>();
        long total = 0;
        while(true)
        {
            if(needed1.isEmpty() && needed2.isEmpty() && lastPut1 != -1)
            {
                long count1 = 1;
                while(lastPut1 != n - 1)
                {
                    if(!in1.contains(board1[lastPut1 + 1]))
                        break;
                    lastPut1++;
                    count1++;
                }
                long count2 = 1;
                while(lastPut2 != m - 1)
                {
                    if(!in2.contains(board2[lastPut2 + 1]))
                        break;
                    lastPut2++;
                    count2++;
                }
                total += count1 * count2;
            }
            if(!needed1.isEmpty())
            {
                if(lastPut1 == n - 1)
                    break;
                in1.add(board1[++lastPut1]);
                needed1.remove(board1[lastPut1]);
                if(!in2.contains(board1[lastPut1]))
                    needed2.add(board1[lastPut1]);
            }
            else
            {
                if(lastPut2 == m - 1)
                    break;
                in2.add(board2[++lastPut2]);
                needed2.remove(board2[lastPut2]);
                if(!in1.contains(board2[lastPut2]))
                    needed1.add(board2[lastPut2]);
            }
        }
        return total;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner();
        int t = sc.nextInt();
        for(int c = 1; c <= t; c++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] both = readBoards(sc, n, m);
            int[] board1 = both[0];
            int[] board2 = both[1];
            System.out.println("Case #" + c + ": " + solve(board1, board2, n, m));
        }
    }
}