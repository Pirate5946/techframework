### 讲一讲MySQL的索引结构,数据库索引为什么用b+树？
B+ 树

### 深入问了乐观锁，悲观锁及其实现。

### mysql执行计划

### 你设计的数据库遵循的范式？

### 如果你发现你的sql语句始终走另一个索引，但是你希望它走你想要的索引，怎么办？

### 你在做sql优化主要从哪几个方面做，用到哪些方法工具？


- [MYSQL中常用的强制性操作（例如强制索引）](https://www.jb51.net/article/49807.htm)


- [MySQL的replace方法](https://www.cnblogs.com/libin6505/p/10422910.html)
```text
用法1 
REPLACE INTO users (id,name,age) VALUES(123, ‘chao’, 50);

用法2
SELECT   REPLACE('www.mysql.com',   'w',   'Ww');

->   'WwWwWw.mysql.com'

用法3
UPDATE tb1 SET f1=REPLACE(f1, 'abc', 'def');

用法4
replace into titles_test
select 5, 10005, title, from_date, to_date
from titles_test
where id = 5;

```    


- [explain type字段，Extra字段，迅猛定位低效SQL？](https://mp.weixin.qq.com/s?__biz=MjM5ODYxMDA5OQ==&mid=2651962587&idx=1&sn=d197aea0090ce93b156e0774c6dc3019&chksm=bd2d09078a5a801138922fb5f2b9bb7fdaace7e594d55f45ce4b3fc25cbb973bbc9b2deb2c31&scene=21#wechat_redirect)
如果 Extra字段为 Using index condition ，可以通过建立联合索引，优化为 Using index

- [《简洁清晰》  同一个SQL语句，为啥性能差异咋就这么大呢？（1分钟系列）](https://mp.weixin.qq.com/s?__biz=MjM5ODYxMDA5OQ==&mid=2651962514&idx=1&sn=550c48c9395b52b7ec561741e86e5ce0&chksm=bd2d094e8a5a80589117a653a30d062b5760ec20f8ab9e2154a63ab782d3353d1b1da50b9bc2&scene=21#wechat_redirect)
type : 连接类型

type方式由快到慢：

system > const > eq_ref > ref > range > index > ALL



- [mysql覆盖索引与回表](https://www.jianshu.com/p/8991cbca3854)
InnoDB有两大类索引：

聚集索引(clustered index)

普通索引(secondary index)

### 回表
先定位主键值，再定位行记录，扫描两遍索引树，它的性能较扫一遍索引树更低。

##### 索引覆盖？
只需要在一棵索引树上就能获取SQL所需的所有列数据，无需回表，速度更快。

常见的方法是：将被查询的字段，建立到联合索引里去。
