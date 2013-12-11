import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class Lunes30I 
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
            ArrayList <Integer> ans = new ArrayList <Integer> ();
            for(int i : as)
                ans.add(i);
            return ans;
        }
        
        public int[] unwrap(Collection <Integer> collection)
        {
            int[] vals = new int[collection.size()];
            int index = 0;
            for(int i : collection) vals[index++] = i;
            return vals;
        }
    }
    
    static int[][] createNext(char[] input)
    {
        int[][] ans = new int[input.length][26];
        for(int i = 0; i < 26; i++)
        {
            ans[0][i] = input[0] == 'a' + i ? 0 : Integer.MAX_VALUE;
            for(int j = 1; j < input.length; j++)
            {
                if(input[j] == 'a' + i)
                    ans[j][i] = j;
                else
                    ans[j][i] = ans[j - 1][i];
            }
        }
        return ans;
    }
    
    static class Answer
    {
        char letra;
        int left;
        int right;
        Answer next;
        
        Answer(char le, int lef, int rig, Answer n)
        {
            letra = le;
            left = lef;
            right = rig;
            next = n;
        }
    }
    
    static Answer best(Answer a, Answer b)
    {
        if(a == null) return b;
        else if(b == null) return a;
        else if(a.right > b.right) return a;
        else if(a.right < b.right) return b;
        else if(a.left < b.left) return a;
        else if(b.left < a.left) return b;
        else return a;
    }

    static Answer bestSpread(Answer a, Answer b) 
    {
        if(a == null) return b;
        else if(b == null) return a;
        int spreadA = a.right - a.left + 1;
        int spreadB = b.right - b.left + 1;
        if(spreadA > spreadB) return a;
        else if(spreadB >  spreadA) return b;
        else return a;
    }
    
    static void generateNormal(Answer answer, StringBuilder sb)
    {
        if(answer.left == -1) return;
        sb.append(answer.letra);
        generateNormal(answer.next, sb);
    }
    
    static String generateString(Answer answer, char middle)
    {
        StringBuilder sb = new StringBuilder();
        generateNormal(answer, sb);
        if(middle == 0)
            return new StringBuilder(sb).reverse().append(sb).toString();
        else
            return new StringBuilder(sb).reverse().append(middle).append(sb).toString();
    }
    
    static String solve(char[] input, int[][] next)
    {   
        Answer[] bestSpread = new Answer[51];
        Answer[] before = new Answer[51];
        Answer[] current = new Answer[51];
        before[0] = new Answer('1', -1, input.length, null);
        for(int i = 0; i < input.length; i++)
        {
            int currentLetter = input[i] - 'a';
            current[0] = before[0];
            for(int j = 1; j < 51; j++)
            {
                if(before[j - 1] == null)
                {
                    current[j] = before[j];
                    continue;
                }
                int rightBefore = before[j - 1].right;
                if(rightBefore == 0)
                {
                    current[j] = before[j];
                    continue;
                }
                int possibleRight = next[rightBefore - 1][currentLetter];
                if(possibleRight < input.length && possibleRight > i)
                {
                    Answer possibleAnswer = new Answer(input[i], i, possibleRight, before[j - 1]);
                    current[j] = best(possibleAnswer, before[j]);
                }
                else
                {
                    current[j] = before[j];
                    continue;
                }
            }
            for(int j = 0; j < 51; j++)
                bestSpread[j] = bestSpread(bestSpread[j], current[j]);
            Answer[] tmp = before;
            before = current;
            current = tmp;
        }
        if(before[50] != null)
            return generateString(before[50], (char) 0);
        else
        {
            for(int i = 49; i >= 1; i--)
            {
                if(bestSpread[i] != null)
                {
                    if(bestSpread[i].left + 1 != bestSpread[i].right)
                        return generateString(bestSpread[i], input[bestSpread[i].left + 1]);
                    else
                        return generateString(bestSpread[i], (char) 0);
                }
            }
            return input[0] + "";
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner();
        char[] input = sc.next().toCharArray();
        System.out.println(solve(input, createNext(input)));
    }
}