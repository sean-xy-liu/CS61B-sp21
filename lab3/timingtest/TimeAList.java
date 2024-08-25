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

  public static int upperBound = 128000;

  public static void timeAListConstruction() {
    // TODO: YOUR CODE HERE
    AList<Integer> ops = new AList<>();
    AList<Integer> Ns = new AList<>();
    AList<Double> times = new AList<>();
    AList<Integer> ints;
    double time;
    int op = 1000;
    Stopwatch sw = new Stopwatch();
    while (op <= upperBound){
      sw.elapsedTime();
      ints = new AList<>();
      for (int i=0; i < op; i++){
        ints.addLast(6);
      }
      time = sw.elapsedTime();
      ops.addLast(op);
      times.addLast(time);
      Ns.addLast(ints.size());
      op *= 2;
    }
    printTimingTable(ops, times, ops);
  }
}
