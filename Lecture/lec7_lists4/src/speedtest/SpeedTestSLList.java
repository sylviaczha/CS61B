package speedtest;

public class SpeedTestSLList {
    public static void main(String[] args){
        SLList<Integer> L = new SLList<>();
        int i = 0;
        while (i < 10000){
            L.addLast(i);
            i += 1;
        }
    }
}
