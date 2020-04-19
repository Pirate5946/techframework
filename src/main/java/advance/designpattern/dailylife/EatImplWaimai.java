package advance.designpattern.dailylife;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/4/18 下午12:30
 **/
public class EatImplWaimai implements Eat {
    @Override
    public void breakfast() {
        System.out.println("早餐点外卖");
    }

    @Override
    public void lunch() {
        System.out.println("午饭点外卖");
    }

    @Override
    public void dinner() {
        System.out.println("晚饭点外卖");
    }
}
