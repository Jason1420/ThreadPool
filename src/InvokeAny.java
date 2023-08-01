import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvokeAny {
    public static void main(String[] args) {
        // Khai báo một Thread Pool thông qua newSingleThreadExecutor() của Executors
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Callable<String>> listCallable = new ArrayList<>(); // Khởi tạo danh sách các Callable

        for (int i = 1; i <= 5; i++) {
            final int _i = i;
            // Khởi tạo từng Callable
            listCallable.add(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    // Trả về kết quả ở mỗi Callable
                    return "Callable " + _i;
                }
            });
        }

        // Callable nào kết thúc ở đây cũng sẽ dừng luôn Thread Pool
        String result = null;
        try {
            result = executorService.invokeAny(listCallable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Result: " + result);

        // Phương thức này đã nói ở trên đây rồi
        executorService.shutdown();
    }
}
