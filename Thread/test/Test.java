import com.corerax.ThreadDeadLock;

/**
 * Created by chengchuan on 2015/12/25.
 */
public class Test {
    public static void main(String[] args) {
        Object o1 = new Object(); // 资源1
        Object o2 = new Object(); // 资 源2
        ThreadDeadLock t1 = new ThreadDeadLock(1,o1,o2,5000);
        ThreadDeadLock t2 = new ThreadDeadLock(2,o2,o1,500);
        new Thread(t1).start();
        new Thread(t2).start();
    }
}
