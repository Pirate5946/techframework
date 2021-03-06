
# 第四章 Schema与数据类型优化

## 4.1 选择优化的数据类型

几个简单的原则

- 更小的通常更好
    
    tinyint unsigned  VS  int

- 简单就好

    整型  VS  字符型
    
    使用 内建类型 存储日期/时间 而不是使用字符串    
    
- 尽量避免 NULL


### 4.1.1 整数类型
存储值 的范围   -2的N-1次方  到 2的N-1次方减一 ， N是存储空间的位数

| TINYINT  | 8位 |
| :--------- | :--: | -----------: |
| SMALLINT | 16位 |
| INT |       32位 |
| BIGINT |       64位 |
 
 
 整数类型有可选的 UNSIGNED 属性，表示不允许负值
 
 有符号和无符号 类型使用相同的存储空间
 
MYSQL 可以为整数类型制定宽度， 例如 INT(11), 但是其实没有什么意义 ， 

这并不会限制值得合法范围  ， 只是规定了 一些客户端工具 用来显示字符的个数，

对于存储和计算来说， INT(1) 和 INT(28) 是一样的

### 4.1.2 实数类型
FLOAT 使用4字节存储

DOUBLE 使用8字节存储


可以用 decimal 存储 比 BIGINT 还大的整数

DECIMAL 类型 用于存储精确的小数

FLOAT、DOUBLE、DECIMAL 都可以指定精度， 每4个字节存储9个数字， 

例如 DECIMAL(18,9) 小数点两边各存储9个数字，占8个字节，小数点占一个字节，一共占9个字节

MYSQL5.0和更高版本中，DECIMAL 最多允许65个数字

### 4.1.3 字符串类型
避免使用一个很长的字符串作为主键

#### VARCHAR 和 CHAR 类型

VARCHAR 用于存储可变长字符串， 需要1 / 2个额外字节记录字符串的长度

如果列的最大长度小于或者等于 255字节，需要额外的一个字节，否则使用2个字节


CHAR 类型是定长的，适合存储很短的字符串，


与 CHAR 和 VARCHAR 类似的类型还有 BINARY 和 VARBINARY,
它们存储的是二进制字符串

> 最好的策略是 只分配真正需要的空间

#### BLOB 和 TEXT 类型
BLOB 和 TEXT 用于存储很大的数据

BLOB是二进制类型， TEXT是字符类型

InnoDB会使用专门的 "外部" 存储区域来进行存储，每个值在行内需要 1-4个字节存储一个指针


#### 使用枚举（ENUM）代替字符串类型


### 4.1.4 日期和时间类型

#### DATETIME
可以保存 1001年到9999年，精度为秒， 与时区无关，使用8个字节的存储空间

#### TIMESTAMP
和UNIX时间戳一样， 保存了从 1970年1月1号0点（格林尼治时间）到当前时间的秒数

可以保存 1970年到2038年，使用4个字节的存储空间， 跟时区有关

### 4.1.5 位数据类型
#### BIT

#### SET


### 4.1.6 选择标识符
在满足值 的范围要求，并且预留未来增长空间的前提下，==尽量选择最小的数据类型==

使用 无符号整型 存储IP地址


## 4.2 MySQL schema 设计中的陷阱

#### 太多的列

#### 太多的表关联

#### 过度使用 ENUM 、 SET

#### 避免存储null

## 4.3 范式与反范式
在范式化的数据库中，每个事实数据会出现 并且只出现一次

在反范式化的数据库中，信息是冗余的，可能会存储在多个地方

### 4.3.1 范式的优点和缺点

#### 优点
范式化的更新操作更快

范式化的数据只需要修改更少的数据

范式化的表更小

#### 缺点
范式化的数据需要更多的关联， 可能会使一些索引失效

### 4.3.2 反范式的优点和缺点

#### 优点
所有的数据都在一张表中，可以很好的避免关联， 可以使用更有效的索引策略


### 4.3.3 混用范式化 和反范式化
实际应用中 需要经常混用

## 4.4 缓存表和汇总表
维度表

按一定维度聚合后的结果，例如 日报、周报、月报、年报

临时表：在重建汇总表、维度表时，
先建立结构相同的表，然后操作完成后，修改表名

### 4.4.1 物化视图
Oracle 和 SQL Server 都提供了 视图的功能，

Mysql并不原生支持 物化视图

### 4.4.2 计数器表
分日期、分桶 统计计数

## 4.5 加快 ALTER TABLE 操作的速度
两种常见技巧
- 一种是在一台不提供服务的机器上执行 ALTER TABLE 操作，然后和提供服务的主库进行切换
- 影子拷贝，用要求的表结构创建一张和原表无关的新表，然后通过重命名、删表交换两张表'
    - 影子拷贝工具： online schema change、 openark.toolkit

ALTER TABLE 允许使用 alter column、 modify column、 change column，这三种操作都是不一样的

所有的 modify column 操作都将导致表重建

列的默认值实际上存储在表的 .frm文件中

修改字段的默认值可以使用 alter column， 这个语句会直接修改 .frm文件而不涉及表数据，速度很快

### 4.5.1 只修改 .frm文件

### 4.5.2 快速创建 MyISAM索引

## 4.6 总结
使用小而简单的合适数据类型

尽量避免使用 null 值

尽量使用整型定义标识列（0，1，2）

避免使用 MYSQL 已经遗弃的特性，例如制定浮点数的精度，或者整数的显示宽度
















