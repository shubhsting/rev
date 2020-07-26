import java.util.*;

public class L004 {

    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {

        constructGraph();
        PrimsAlgo(1);
        DijestraAlgo(1);
    }

    static ArrayList<Edge>[] graph;

    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + "-> "); // u

            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ")"); // v
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void addEdge(ArrayList<Edge>[] gp, int u, int v, int w) {
        gp[u].add(new Edge(v, w));
        gp[v].add(new Edge(u, w));
    }

    public static void constructGraph() {
        graph = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            graph[i] = new ArrayList<>();
        }
        addEdge(graph, 0, 1, 2);
        addEdge(graph, 0, 3, 6);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 1, 3, 8);
        addEdge(graph, 1, 4, 5);
        addEdge(graph, 2, 4, 7);
        addEdge(graph, 3, 4, 9);
        // addEdge(graph, 5, 6, 8);
        // addEdge(graph, 2, 5, 2);

    }

    static int N = 5;
    static int[] par;
    static int[] setSize;

    public static int findPar(int vtx) {
        if (par[vtx] == vtx)
            return vtx;
        return par[vtx] = findPar(vtx);
    }

    public static void mergeSet(int l1, int l2) {
        if (setSize[l1] > setSize[l2]) {
            par[l2] = l1;
            setSize[l1] += setSize[l2];
        } else {
            par[l1] = l2;
            setSize[l2] += setSize[l1];
        }
    }

    // It is bisically application of union find
    public static void Kruskal_Algo(int[][] arr) {
        par = new int[N];
        setSize = new int[N];
        ArrayList<Edge>[] KGraph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            par[i] = i;
            setSize[i] = 1;
            KGraph[i] = new ArrayList<>();
        }
        Arrays.sort(arr, (int[] a, int[] b) -> {
            return a[2] - b[2];
        });
        for (int[] ar : arr) {
            int p1 = findPar(ar[0]);
            int p2 = findPar(ar[1]);
            if (p1 != p2) {
                mergeSet(p1, p2);
                addEdge(KGraph, ar[0], ar[1], ar[2]);
            }
        }
        display(KGraph);
    }

    static class pair_ {
        int src = 0;
        int par = 0;
        int wt = 0;
        int wsf = 0;

        pair_(int src, int par, int w, int wsf) {
            this.src = src;
            this.par = par;
            this.wt = w;
            this.wsf = wsf;
        }
    }

    public static void DijestraAlgo(int src) {
        ArrayList<Edge>[] DGraph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            DGraph[i] = new ArrayList<Edge>();
        }

        PriorityQueue<pair_> que = new PriorityQueue<>((pair_ a, pair_ b) -> {
            return a.wsf - b.wsf;
        });
        boolean[] vis = new boolean[N];
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                pair_ vtx = que.remove();
                if (vis[vtx.src])
                    continue;
                if (vtx.par != -1)
                    addEdge(DGraph, vtx.src, vtx.par, vtx.wt);
                vis[vtx.src] = true;
                for (Edge e : graph[vtx.src])
                    if (!vis[e.v])
                        que.add(new pair_(e.v, vtx.src, e.w, vtx.wsf + e.w));
            }
        }
        display(DGraph);
    }

    public static void PrimsAlgo(int src) {
        ArrayList<Edge>[] PGraph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            PGraph[i] = new ArrayList<Edge>();
        }

        PriorityQueue<pair_> que = new PriorityQueue<>((pair_ a, pair_ b) -> {
            return a.wt - b.wt;
        });
        boolean[] vis = new boolean[N];
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                pair_ vtx = que.remove();
                if (vis[vtx.src])
                    continue;
                if (vtx.par != -1)
                    addEdge(PGraph, vtx.src, vtx.par, vtx.wt);
                vis[vtx.src] = true;
                for (Edge e : graph[vtx.src])
                    if (!vis[e.v])
                        que.add(new pair_(e.v, vtx.src, e.w, vtx.wsf + e.w));
            }
        }
        display(PGraph);
    }
}