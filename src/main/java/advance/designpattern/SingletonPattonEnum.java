package advance.designpattern;

/**
 * description: 单例设计模式
 *
 * @author : LIUTAO
 * create at : 2020/4/14 下午5:42
 **/
public class SingletonPattonEnum {

    public enum MyEnumSingleton{
        demoFactory;

        private Demo demo;

        MyEnumSingleton() {
            this.demo = new Demo();
        }

        public Demo getDemo() {
            return demo;
        }
    }

    public static Demo getDemo() {
        return MyEnumSingleton.demoFactory.getDemo();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(SingletonPattonEnum.getDemo().hashCode());
        }
    }


}
