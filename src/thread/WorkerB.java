package thread;

/**
 * 工人A
 * @author furui
 * @date 2019/3/5 0005
 */
public class WorkerB extends Worker{
    @Override
    void say(String voice) {
        System.out.println("我是B,我在" + voice);
    }
}
