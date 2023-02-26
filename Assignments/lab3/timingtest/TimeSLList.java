package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> counter = new AList<Integer>();
        SLList<Integer> temp = new SLList<Integer>();
        AList<Double> timer = new AList<Double>();
        AList<Integer> ops = new AList<Integer>();

        for (int i = 1; i <= Math.pow(2, 7); i *= 2){
            counter.addLast(i * 1000);
        }

        for (int j = 0; j <= 7; j++){
            ops.addLast(10000);
        }

        for (int k = 0; k < counter.size(); k++){
            for(int m = 0; m <= counter.get(k); m++){
                temp.addLast(0);
            }
            Stopwatch sw = new Stopwatch();
            for (int l = 0; l <= 10000; l++) {
                temp.getLast();
            }
            timer.addLast((sw.elapsedTime()));
        }

        printTimingTable(counter, timer, ops);
    }
}
