- [SpringBoot + Mybatis plus 实现多数据源整合，默认bootstrap.properties配置不生效，在配置类配置mybatis-plus 全局配置](https://blog.csdn.net/u012075383/article/details/79304178)


#### 关键逻辑
数据源是作为map的value ，名称作为value

重写 AbstractRoutingDataSource#determineCurrentLookupKey 方法，==通过切面动态修改获取数据源的key==
```java
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		DataSourceType.DataBaseType dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
	}

}
```