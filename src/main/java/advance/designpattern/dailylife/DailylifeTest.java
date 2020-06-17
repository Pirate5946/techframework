package advance.designpattern.dailylife;

/**
 * description: 测试类
 *
 * @author : LIUTAO
 * create at : 2020/4/18 下午12:31
 **/
public class DailylifeTest {
    public static void main(String[] args) {
        Eat eat = new EatImplLiutao();
        DailyLifeLiuTao dailyLife = new DailyLifeLiuTao();
        dailyLife.setEat(eat);
        dailyLife.noon();
    }
}
