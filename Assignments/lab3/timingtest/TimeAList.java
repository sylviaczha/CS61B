package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> counter = new AList<Integer>();
        AList<Integer> temp = new AList<Integer>();
        AList<Double> timer = new AList<Double>();
        Stopwatch sw = new Stopwatch();

        for (int i = 1; i <= Math.pow(2, 7); i *= 2){
            counter.addLast(i * 1000);
        }

        for (int j = 0; j < counter.size(); j++){
            for(int k = 0; k <= counter.get(j); k++){
                temp.addLast(0);
            }
            timer.addLast(sw.elapsedTime());
        }

        printTimingTable(counter, timer, counter);
    }


}
