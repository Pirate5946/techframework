package advance.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author : LIUTAO
 * create at : 2020/6/11 下午9:29
 **/
public class ExcelTest {
    public static void main(String[] args) {
//        List<?> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd", DateUtil.date(), 3.22676575765);
//        List<?> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1", DateUtil.date(), 250.7676);
//        List<?> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2", DateUtil.date(), 0.111);
//        List<?> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3", DateUtil.date(), 35);
//        List<?> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4", DateUtil.date(), 28.00);
//
//        List<List<?>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("name", "张三");
        row1.put("age", 23);
        row1.put("date", 23);
//        row1.put("成绩", 88.32);
//        row1.put("是否合格", true);
//        row1.put("考试日期", DateUtil.date());

//        Map<String, Object> row2 = new LinkedHashMap<>();
//        row2.put("姓名", "李四");
//        row2.put("年龄", 33);
//        row2.put("成绩", 59.50);
//        row2.put("是否合格", false);
//        row2.put("考试日期", DateUtil.date());

        ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1);


        BigExcelWriter writer= ExcelUtil.getBigWriter("xxx.xlsx");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("date", "日期");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("sex", "性别");
// 一次性写出内容，使用默认样式
        writer.write(rows);
// 关闭writer，释放内存
        writer.close();
    }
}
