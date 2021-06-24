import cn.hutool.core.date.DateUtil;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/6/19 下午4:52
 **/
public class Test {
    public static void main(String[] args) throws ParseException {
//        testDateTime();

//        String s = new String(("数据明细-用户列表" + DateUtil.date() + ".xlsx").getBytes(), StandardCharsets.ISO_8859_1);
//        System.out.println(s);


    }

    private static void testDateTime() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-22 00:00:00"));
        long timeInMillis = calendar.getTimeInMillis();
        System.out.println(timeInMillis);


        Calendar calendar2 = Calendar.getInstance();
        String dateTime = "2020-06-22 23:55:56";
        calendar2.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime));

        long timeInMillis1 = calendar2.getTimeInMillis();

        System.out.println("parselong: "+ timeInMillis1);

        if (timeInMillis > timeInMillis1) {
            System.out.println("不消费消息");
        } else {
            System.out.println("消费消息");
        }
    }
}
