import java.util.Random;

class WeightedQuickUnion
{
    private int[] parent;
    private int[] size;
    
    public WeightedQuickUnion(int n)
    {
        size = new int[n*n+2];
        parent = new int[n * n + 2];
        for (int i = 0; i < n + 1; i++)
        {
            parent[i] = 0;
            size[i] = 1;
        }
        for (int i = n*n-n+1; i < n*n+2; i++)
        {
            parent[i] = n * n + 1;
            size[i] = 1;
        }
        for (int i = n+1; i < n*n-n+1; i++)
        {
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    public int root(int p)
    {
        while (p != parent[p])
        {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }
    
    public boolean connectedToTop(int p)
    {
        int top = parent[0];
        return connected(p, top);
    }
    
    public boolean percolates()
    {
        int top = parent[0];
        int bottom = parent[parent.length-1];
        return connected(top, bottom);
    }
    
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (size[i] < size[j])
        {
            parent[i] = j;
            size[j] += size[i];
        }
        else 
        {
            parent[j] = parent[i];
            size[i] += size[j];
        }
    }
}

class Percolation
{
    private int N;
    private boolean[] openSites;
    private int openSitesCounter;
    WeightedQuickUnion w;
    
    public Percolation(int n)
    {
        openSitesCounter = 0;
        N = n;
        w = new WeightedQuickUnion(n);
        openSites = new boolean[n*n+1];
        for (int i = 0; i < n*n; i++)
        {
            openSites[i] = false;
        }
    }
    
    public void joinAdjacents(int row, int col)
    {
        int site = rowcolToIndex(row, col);
        int up = row + 1;
        int down = row - 1;
        int left = col - 1;
        int right = col + 1;
        if (isOpen(up, col))
        {
            int upSite = rowcolToIndex(up, col);
            w.union(upSite, site);
        }
        if (isOpen(down, col))
        {
            int downSite = rowcolToIndex(down, col);
            w.union(downSite, site);
        }
        if (isOpen(row, left))
        {
            int leftSite = rowcolToIndex(row, left);
            w.union(leftSite, site);
        }
        if (isOpen(row, right))
        {
            int rightSite = rowcolToIndex(row, right);
            w.union(rightSite, site);
        }
    }
    
    public void open(int row, int col)
    {
        int indx = rowcolToIndex(row, col);
        if (!openSites[indx])
        {
            openSites[indx] = true;
            openSitesCounter += 1;
            joinAdjacents(row, col);
        }
    }
    
    public boolean isOpen(int row, int col)
    {
        int indx = rowcolToIndex(row, col);
        return openSites[indx];
    }
    
    public boolean isFull(int row, int col)
    {
        int indx = rowcolToIndex(row, col);
        return w.connectedToTop(indx);
    }
    
    public int numberOfOpenSites()
    {
        return openSitesCounter;
    }
    
    public int totalSites()
    {
        return N * N;
    }
    
    public boolean percolates()
    {
        return w.percolates();
    }
    
     public int rowcolToIndex(int row, int col)
     {
         if ((row < 1 || row > N) || (col < 1 || col > N))
         {
             return 0;
         }
         else
         {
             return N * (row - 1) + col;
         }
     }
}

public class JavaClient {
    public static void main(String[] args)
    {
        int N = 100;
        Percolation p = new Percolation(N);
        Random rand = new Random();
        
        while (!p.percolates())
        {
            int row = rand.nextInt(N) + 1;
            int col = rand.nextInt(N) + 1;
            p.open(row, col);
        }
        int sites = p.numberOfOpenSites();
        int total = p.totalSites();
        double prop = ((double) sites) / total;
        
        System.out.println(p.percolates());
        System.out.println(prop);
    }
}
