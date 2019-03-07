package designpatterns.singleton;

/**
 * 基本恶汉单例
 * @author furui
 * @date 2019/3/5 0005
 */
public class SingletonBase {
    /**
     * 1.私有构造器
     */
    private SingletonBase(){}

    /**
     * 2.私有静态实例
     */
    private static final SingletonBase INSTANCE = new SingletonBase();

    /**
     * 3.公有方法
     * @return
     */
    public static SingletonBase getIntance(){
        return INSTANCE;
    }
}
