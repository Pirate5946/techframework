package mybatis;

import cn.hutool.json.JSONUtil;
import entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: teset
 *
 * @author : LIUTAO
 * create at : 2020/3/29 下午4:16
 **/
public class test {
    public static void main(String[] args) {
        /*
         * 1.加载mybatis的配置文件，初始化mybatis，创建出SqlSessionFactory，是创建SqlSession的工厂
         * 这里只是为了演示的需要，SqlSessionFactory临时创建出来，在实际的使用中，SqlSessionFactory只需要创建一次，当作单例来使用
         */
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);

        //2. 从SqlSession工厂 SqlSessionFactory中创建一个SqlSession，进行数据库操作
        SqlSession sqlSession = factory.openSession();

        //3.使用SqlSession查询
        Map<String,Object> params = new HashMap<String,Object>();

        params.put("empNo",10001);

        List<Employee> result = sqlSession.selectList("mybatis.mapper.EmployeesMapper.selectByEmpNo",params);
        List<Employee> result1 = sqlSession.selectList("mybatis.mapper.EmployeesMapper.selectByEmpNo");
        System.out.println("查询结果：" + JSONUtil.toJsonStr(result));
        //~output :   查询到的数据总数：5
        System.out.println("所有员工数: "+result1.size());

    }
}
