import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(name + " đang thực thi...");

        // Giả lập thời gian chạy của Callable mất 2 giây
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Trả kết quả về là một kiểu String
        return name;
    }
}
