package advance.math;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/6/3 下午3:59
 **/
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal value = new BigDecimal("123.333");
        String valueOf = String.valueOf(value.setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println(valueOf);
    }
}
