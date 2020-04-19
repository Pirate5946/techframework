package advance.designpattern.dailylife;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/4/18 下午12:29
 **/
public class EatImplLiutao implements Eat {
    @Override
    public void breakfast() {
        System.out.println("liutao make breakfast");
    }

    @Override
    public void lunch() {
        System.out.println("自己做午饭自己吃");
    }

    @Override
    public void dinner() {

    }
}
