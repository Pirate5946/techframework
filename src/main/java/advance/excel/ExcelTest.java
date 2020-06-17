package advance.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ExcelTest
 * @Descrption TODO
 * @Author lt
 * @Date 2020/6/17 22:36
 * @Version 1.0
 * <a href="https://hutool.cn/docs/#/poi/Excel%E7%94%9F%E6%88%90-ExcelWriter">hutool excel 1</a>
 **/
public class ExcelTest {

    public static void main(String[] args) {
        writeMap2Excel();
    }


    public static void writeMap2Excel(){
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("姓名", "李四");
        row2.put("年龄", 33);
        row2.put("成绩", 59.50);
        row2.put("是否合格", false);
        row2.put("考试日期", DateUtil.date());

        ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1, row2);

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeMapTest.xlsx");
// 合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "一班成绩单");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
// 关闭writer，释放内存
        writer.close();

    }

}
