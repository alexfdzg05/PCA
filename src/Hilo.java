import java.util.Collections;
import java.util.List;

public class Hilo extends Thread{
    public List<Integer> bucket;

    public Hilo(List<Integer> num){
        bucket = num;
    }
    public void run(List<Integer> numbers){
        Collections.sort(numbers);
    }
}
