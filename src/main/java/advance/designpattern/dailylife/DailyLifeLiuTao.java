package advance.designpattern.dailylife;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/4/18 下午12:17
 **/
public class DailyLifeLiuTao implements DailyLife,Study {

    private Eat eat;

    private Study study;

    public Eat getEat() {
        return eat;
    }

    public void setEat(Eat eat) {
        this.eat = eat;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    /**
     * empty constructor
     */
    public DailyLifeLiuTao(){ };

    public DailyLifeLiuTao(Eat eat, Study study){
        this.eat = eat;
        this.study = study;
    };

    @Override
    public void morning() {

    }

    @Override
    public void noon() {
        this.study();
        eat.lunch();
    }

    @Override
    public void afternoon() {

    }

    @Override
    public void evening() {

    }

    @Override
    public void study() {
        System.out.println("学习。。。");
    }
}
