package advance.designpattern;

/**
 * description: 单例设计模式 - 双重检查
 *
 * @author : LIUTAO
 * create at : 2020/4/14 下午5:42
 **/
public class SingletonPattonDoubleCheck {

    volatile private static Demo demo ;

    public static Demo getInstance() {
        if (demo == null) {
            synchronized (Demo.class) {
                if (demo == null) {
                    demo = new Demo();
                }
            }
        }
        return demo;
    }

}
