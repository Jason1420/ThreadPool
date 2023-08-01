import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Submit {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> listFuture = new ArrayList<>();

        for (int i = 0; i < 10; i++){
//            MyRunnable myRunnable = new MyRunnable("Runnable" + i);
            Callable myCallable = new MyCallable("Runnable" + i);
            Future future = executorService.submit(myCallable);
            listFuture.add(future);
        }

        for(Future fu:listFuture){
            try {
                System.out.println(fu.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();
    }
}
