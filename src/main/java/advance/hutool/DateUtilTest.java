package advance.hutool;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.FastDateFormat;
import lombok.extern.slf4j.Slf4j;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/6/11 下午5:57
 **/

public class DateUtilTest {
    public static void main(String[] args) {
        DateTime parse = DateUtil.parse(String.valueOf(20200607));
        DateTime offset = DateUtil.offsetDay(parse, -2);

        System.out.println(offset.toString(DatePattern.PURE_DATE_FORMAT));
    }
}
