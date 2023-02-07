package speedtest;

public class SpeedTestAList {
    public static void main(String[] args){
        AList L = new AList();
        int i = 0;
        while (i < 10000){
            L.addLast(i);
            i += 1;
        }
    }
}
