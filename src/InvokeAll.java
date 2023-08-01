import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAll {
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

        // Dùng Future để lấy về danh sách các kết quả trả về từ mỗi Callable
        List<Future<String>> futures = null;
        try {
            futures = executorService.invokeAll(listCallable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(Future<String> future : futures) {
            try {
                System.out.println("Result: " + future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        // Phương thức này đã nói ở trên đây rồi
        executorService.shutdown();
    }
}
