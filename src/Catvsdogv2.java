
import java.util.ArrayList;

public class Catvsdogv2 {

    static Kattio io;
    static ArrayList<ArrayList<Integer>> lista;
    static boolean[] d;
    static int[] m;

    public static void main(String[] args) {
        io = new Kattio(System.in, System.out);
        final int m = io.getInt();
        for (int i = 0; i < m; ++i) {
            Solve();
        }
        io.close();
    }

    public static void Solve() {
        io.getInt();//dont need that
        io.getInt();//nor that
        final int v;
        int l, h;
        lista = new ArrayList<ArrayList<Integer>>();
        v = io.getInt();
        String lstring, hstring;
        ArrayList<Voter> catlovers = new ArrayList<Voter>();
        ArrayList<Voter> doglovers = new ArrayList<Voter>();
        for (int i = 0; i < v; ++i) {
            lstring = io.getWord();
            hstring = io.getWord();
            l = Integer.parseInt(lstring.substring(1));
            h = Integer.parseInt(hstring.substring(1));
            Voter voter = new Voter(l, h);
            if ("C".equals(lstring.substring(0, 1))) {
                catlovers.add(voter);
            } else {
                doglovers.add(voter);
            }
        }


        for (Voter v2 : catlovers) {
            lista.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < catlovers.size(); ++i) {
            for (int j = 0; j < doglovers.size(); ++j) {
                if (catlovers.get(i).l == doglovers.get(j).h || catlovers.get(i).h == doglovers.get(j).l) {
                    lista.get(i).add(j);
                }
            }
        }
        m = new int[doglovers.size()];
        for (int j = 0; j < doglovers.size(); ++j) {
            m[j] = -1;
        }

        int ans = catlovers.size() + doglovers.size();
        for (int i = 0; i < catlovers.size(); ++i) {
            d = new boolean[catlovers.size()];
            if (increase(i)) {
                --ans;
            }
        }
        io.println(ans);
    }

    public static boolean increase(int at) {
        if (d[at]) {
            return false;
        } else {
            d[at] = true;
        }
        ArrayList<Integer> c = lista.get(at);
        for (int i = 0; i < c.size(); ++i) {
            int end = c.get(i);
            if (m[end] == -1 || increase(m[end])) {
                m[end] = at;
                return true;
            }
        }
        return false;
    }
}