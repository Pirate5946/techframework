[Redis BigKey介绍](https://blog.csdn.net/weixin_47531845/article/details/108821372)

一、什么是bigkey
在Redis中，一个字符串最大512MB，一个二级数据结构（例如hash、list、set、zset）可以存储大约40亿个(2^32-1)个元素，但实际上中如果下面两种情况，我就会认为它是bigkey。

字符串类型：它的big体现在单个value值很大，一般认为超过10KB就是bigkey。
非字符串类型：哈希、列表、集合、有序集合，它们的big体现在元素个数太多。
二、危害
bigkey可以说就是Redis的老鼠屎，具体表现在：

1.内存空间不均匀
这样会不利于集群对内存的统一管理，存在丢失数据的隐患。

2.超时阻塞
由于Redis单线程的特性，操作bigkey的通常比较耗时，也就意味着阻塞Redis可能性越大，这样会造成客户端阻塞或者引起故障切换，它们通常出现在慢查询中。

例如，在Redis发现了这样的key，你就等着DBA找你吧。

127.0.0.1:6379> hlen big:hash(integer) 
2000000127.0.0.1:6379> hgetall big:hash
1) "a"
2) "1"
3.网络拥塞
bigkey也就意味着每次获取要产生的网络流量较大，假设一个bigkey为1MB，客户端每秒访问量为1000，那么每秒产生1000MB的流量，对于普通的千兆网卡(按照字节算是128MB/s)的服务器来说简直是灭顶之灾，而且一般服务器会采用单机多实例的方式来部署，也就是说一个bigkey可能会对其他实例造成影响，其后果不堪设想。

4.过期删除
有个bigkey，它安分守己（只执行简单的命令，例如hget、lpop、zscore等），但它设置了过期时间，当它过期后，会被删除，如果没有使用Redis 4.0的过期异步删除(lazyfree-lazy-expire yes)，就会存在阻塞Redis的可能性，而且这个过期删除不会从主节点的慢查询发现（因为这个删除不是客户端产生的，是内部循环事件，可以从latency命令中获取或者从slave节点慢查询发现）。

5.迁移困难
当需要对bigkey进行迁移（例如Redis cluster的迁移slot），实际上是通过migrate命令来完成的，migrate实际上是通过dump + restore + del三个命令组合成原子命令完成，如果是bigkey，可能会使迁移失败，而且较慢的migrate会阻塞Redis。

