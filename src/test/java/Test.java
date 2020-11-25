import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Test
 * @Author: sanwu
 * @Date: 2020/11/24 13:11
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("wake up");
                } catch (InterruptedException e) {
                    System.out.println("wake up by interrupt");
                    e.printStackTrace();
//                    Thread.currentThread().interrupt();
                }
            }
        });

        thread.start();
        thread.interrupt();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }


}
