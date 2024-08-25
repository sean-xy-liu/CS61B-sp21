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

  public static int upperBound = 32000;
  public static void timeGetLast() {
    // TODO: YOUR CODE HERE
    Stopwatch sw = new Stopwatch();
    SLList<Integer> ints = new SLList<>();
    AList<Integer> Ns = new AList<>();
    AList<Integer> ops = new AList<>();
    AList<Double> times = new AList<>();
    double time;
    int N = 1000;
    int op = 10000;
    while (N <= upperBound){
      for (int i=0; i < N; i++){
        ints.addLast(6);
      }
      sw.elapsedTime();
      for (int i=0; i < op; i++){
        ints.getLast();
      }
      time = sw.elapsedTime();
      N = ints.size();
      Ns.addLast(N);
      times.addLast(time);
      ops.addLast(op);
    }
    printTimingTable(Ns, times, ops);
  }

}
