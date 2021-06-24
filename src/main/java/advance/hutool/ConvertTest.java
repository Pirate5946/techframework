package advance.hutool;

import cn.hutool.core.convert.Convert;

import java.util.Date;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/6/11 下午5:47
 **/
public class ConvertTest {

    public static void main(String[] args) {
        String a = "2017-05-06";
        Date value = Convert.toDate(a);
        System.out.println(value.toString());


    }
}
