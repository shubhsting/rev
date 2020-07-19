import java.util.*;

public class L001 {
    public static void main(String[] args) {

    }

    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int n = 7;
    static ArrayList<Edge>[] graph = new ArrayList[7];

    public static void addEdge(ArrayList<Edge>[] gp, int u, int v, int w) {
        gp[u].add(new Edge(v, w));
        gp[v].add(new Edge(u, w));
    }

    public static void display(ArrayList<Edge>[] gp) {
        for (int i = 0; i < gp.length; i++) {
            System.out.print(i + " -> ");
            for (Edge e : gp[i])
                System.out.print(" (" + e.v + "," + e.w + ") " + " ");
            System.out.println();
        }
    }

    public static int findEdge(int v1, int v2) {
        int vtx = -1;
        for (int i = 0; i < graph[v1].size(); i++) {
            if (graph[v1].get(i).v == v2) {
                vtx = i;
                break;
            }
        }
        return vtx;
    }

    public static void removeEdge(int u, int v) {
        int idx1 = findEdge(u, v);
        int idx2 = findEdge(v, u);
        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int vtx) {
        for (int i = graph[vtx].size() - 1; i >= 0; i--) {
            removeEdge(graph[vtx].get(i).v, vtx);
        }
    }

    public static boolean hasPath(int start, int end, boolean[] vis) {
        if (start == end)
            return true;
        vis[start] = true;
        boolean res = false;
        for (Edge e : graph[start])
            if (!vis[e.v])
                res = res || hasPath(e.v, end, vis);
        return res;
    }

    public static int allPath(int start, int end, boolean[] vis, int weight, String ans) {
        if (start == end) {
            System.out.println(ans + " " + weight);
            return 1;
        }
        vis[start] = true;
        int count = 0;
        for (Edge e : graph[start]) {
            if (!vis[e.v])
                count += allPath(e.v, end, vis, weight + e.w, ans + " " + e.v);
        }
        vis[start] = false;
        return count;
    }

    public class allPair {
        int lightWeight = (int) 1e7;
        int heavyWeight = 0;
        int ceil = (int) 1e7;
        int floor = 0;

    }

    public static void allsol(int start, int end, boolean[] vis, int weight, allPair pair, int val) {
        if (start == end) {
            pair.lightWeight = Math.min(pair.lightWeight, weight);
            pair.heavyWeight = Math.max(pair.heavyWeight, weight);
            if (weight > val)
                pair.ceil = Math.min(pair.ceil, weight);
            if (weight < val)
                pair.floor = Math.max(pair.floor, weight);
            return;
        }
        vis[start] = true;
        for (Edge e : graph[start]) {
            if (!vis[e.v])
                allsol(e.v, end, vis, weight + e.w, pair, val);
        }
        vis[start] = false;
    }

    public static void hamitonian_Path(int src, int osrc, boolean[] vis, int count, String ans) {
        if (count == graph.length - 1) {
            int idx = findEdge(src, osrc);
            if (idx == -1)
                System.out.println(" Path " + ans);
            else
                System.out.println(" Cycle " + ans);
            return;
        }
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                hamitonian_Path(e.v, osrc, vis, count + 1, ans + " " + e.v + " ");
        }
        vis[src] = false;
    }

    public static int dfs_GCC(int src, boolean[] vis) {
        vis[src] = true;
        int count = 0;
        for (Edge e : graph[src])
            if (!vis[e.v])
                count += dfs_GCC(e.v, vis);

        return count + 1;
    }

    public static int GCC() {
        boolean[] vis = new boolean[n];
        int noOfCompo = 0;
        int greatest = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                noOfCompo++;
                greatest = Math.max(greatest, dfs_GCC(i, vis));
            }
        }
        System.out.println(greatest);
        return noOfCompo;
    }

    public static void bfs(int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        int dest = 6;
        int level = 0;
        int cycle = 0;
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int vtx = que.removeFirst();
                if (vis[vtx]) {
                    cycle++;
                    System.out.println("Cycle" + vtx + " " + level);
                    continue;
                }
                if (vtx == dest)
                    System.out.println("Destination occured" + level);
                vis[vtx] = true;
                for (Edge e : graph[vtx]) {
                    if (!vis[e.v])
                        que.addLast(e.v);
                }
            }
            level++;
        }
    }

    public static void bfs_02(int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        int dest = 6;
        int level = 0;
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int vtx = que.removeFirst();
                if (vtx == dest)
                    System.out.println("Destination occured" + level);
                for (Edge e : graph[vtx]) {
                    if (!vis[e.v]) {
                        que.addLast(e.v);
                        vis[e.v] = true;
                    }
                }
            }
            level++;
        }
    }

    // ==========================leetcode 785=============================
    public boolean isBipartite(int[][] graph) {
        ArrayList<Integer>[] gp = new ArrayList[graph.length];
        for (int i = 0; i < graph.length; i++)
            gp[i] = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                gp[i].add(graph[i][j]);

            }
        }
        for (int i = 0; i < graph.length; i++)
            System.out.println(gp[i]);
        int[] vis = new int[graph.length];
        Arrays.fill(vis, -1);
        boolean res = true;
        for (int i = 0; i < vis.length; i++) {
            if (vis[i] == -1)
                res = res && isbipartite(gp, i, vis);
        }
        return res;
    }

    public class bipar {
        int src = 0;
        int color = 0;

        bipar(int src, int color) {

            this.src = src;
            this.color = color;
        }
    }

    public boolean isbipartite(ArrayList<Integer>[] graph, int src, int[] vis) {
        LinkedList<bipar> que = new LinkedList<>();
        que.addLast(new bipar(src, 0));
        int cycle = 0;
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                bipar temp = que.removeFirst();
                if (vis[temp.src] != -1) {
                    cycle++;
                    if (vis[temp.src] != temp.color)
                        return false;
                }
                vis[temp.src] = temp.color;
                for (int e : graph[temp.src]) {
                    if (vis[e] == -1)
                        que.addLast(new bipar(e, (temp.color + 1) % 2));
                }
            }
        }
        return true;
    }
}