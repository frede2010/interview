# Redis
* **Redis 有哪些数据类型**
   * https://mp.weixin.qq.com/s?__biz=MzI1NDQ3MjQxNA==&mid=2247483987&idx=1&sn=5c5e4cd5bc73a7e6f84e5d6adfab0935&chksm=e9c5fbe2deb272f4b5b75bd2ac92bb27950452623ec83c0e1add7e30c773160421fab1571680&scene=21#wechat_redirect
   1. String 字符串类型
      * 常用命令：get、set、del、INCR 自增、DECR 自减、INCRBY 增加整数值、DECRBY 减去整数值、INCRBYFLOAT 增加浮点值
      * 应用场景：String是最常用的一种数据类型，普通的key/value存储都可以归为此类，value其实不仅是String， 也可以是数字：比如想知道什么时候封锁一个IP地址(访问超过几次)。INCRBY命令让这些变得很容易，通过原子递增保持计数
   2. List 列表类型
      * 常用命令：RPUSH 推入列表右端，LPUSH，PPOP，LPOP，LRANGE 获取列表在给定范围上的所有元素，LINDEX 获取列表在指定位置上的元素
      * 使用场景：微博 TimeLine，消息队列
   3. Set 集合类型
      * 常用命令：SADD 添加给定元素、SMEMBERS返回集合包含的所有元素、 SISMEMBER 检查指定元素是否存在、SREM 移除指定元素
      * 使用场景：
        1. 共同好友、二度好友
        2. 利用唯一性，可以统计访问网站的所有独立 IP
        3. 好友推荐的时候，根据 tag 求交集，大于某个 threshold 就可以推荐
        
   4. Hash 散列类型
   
   5. ZSet 有序集合类型
      * 使用场景：twitter 的public timeline可以以发表时间作为score来存储，这样获取时就是自动按时间排好序的
   * 比较
     结构类型 | 结构存储的值 | 结构的读写能力 
     :-: | :-: | :-: | 
     String 字符串类型 | 可以使字符串、整数、浮点数 | 对字符串或者字符串的一部分执行操作；对整数和浮点数执行自增和自减操作 | 
     List 列表类型 | 一个链表，链表上的每一个节点都包含一个字符串| 从链表的两端进行push或pop操作；根据偏移量对链表进行修剪；读取单个或多个元素；根据值查找或者删除元素 | 
     Set 集合类型 | 包含字符串的无序收集器，并且被包含的每个字符串都是唯一的 | 添加、获取、删除单个元素；检查一个元素是否存在于一个集合中；计算交集、并集、差集；从集合里面最忌获取元素 | 
     Hash 散列类型 | 包含键值对的无序散列表 | 添加、获取、删除单个元素；获取所有键值对元素 | 
     ZSet 有序集合类型 | 字符串成员与浮点数分值之间的有序映射，元素的排列顺序由分值的大小决定 | 添加、获取、删除单个元素；根据分值范围或者成员来获取元素 |
      
* **hset,mset,hmset区别**
   1. hset
      * Redis Hset 命令用于为哈希表中的字段赋值。如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。如果字段已经存在于哈希表中，旧值将被覆盖。
      ```
      redis 127.0.0.1:6379> HSET KEY_NAME FIELD VALUE 
      ``` 
   2. mset
      * Redis Mset 命令用于同时设置一个或多个 key-value 对。
      ```
      redis 127.0.0.1:6379> MSET key1 value1 key2 value2 .. keyN valueN   
      ``` 
   3. hmset
      * Redis Hmset 命令用于同时将多个 field-value (字段-值)对设置到哈希表中。此命令会覆盖哈希表中已存在的字段。如果哈希表不存在，会创建一个空哈希表，并执行 HMSET 操作
      ```
      redis 127.0.0.1:6379> HMSET KEY_NAME FIELD1 VALUE1 ...FIELDN VALUEN  
      ```
   * 区别
      1. mset函数插入效率最高，但是所耗内存较高
      2. hmset由于在实际应用中，每条数据对应的key不相同，需要维护一个本机的缓存容器Map<key,Map<field, value>>，因此插入速度要比mset函数慢一些，但是内存消耗较低
      3. hset函数插入效率较低，约为mset的1/20，内存消耗约为mset的2/3
      4. hset和hmset函数对于每个key所存储的<field, value>数目非常敏感。一旦超过限制，插入效率下降十分明显，同样，内存开销也会显著增加。在实际应用中，应尽量保证每个key下面的<field, value>的数目不超过限制(默认值为64)
* **Redis 使用场景**


* **Redis 使用场景**
   1. 配合关系型数据库做高速缓存
   2. 缓存高频次访问的数据，降低数据库io
   3. 分布式架构，做session共享
   4. 可以持久化特定数据。
   5. 利用zset类型可以存储排行榜
   6. 利用list的自然时间排序存储最新n个数据
   
* **Redis 持久化机制**
   * 概述：
      1. 快照（RDB）：就是我们俗称的备份，他可以在定期内对数据进行备份，将Redis服务器中的数据持久化到硬盘中；
         * 客户端通过向Redis发送BGSAVE 命令来创建快照
         * 客户端通过向Redis发送SAVE 命令来创建快照。接收到SAVE命令的Redis服务器在快照创建完毕之前将不再响应任何其他命令的请求
      2. 只追加文件（AOF）：他会在执行写命令的时候，将执行的写命令复制到硬盘里面，后期恢复的时候，只需要重新执行一下这个写命令就可以了。类似于我们的MySQL数据库在进行主从复制的时候，使用的是binlog二进制文件，同样的是执行一遍写命令
         * 打开AOF持久化选项：appendonly yes
         * 通过如下命令来配置AOF文件的同步频率： appendfsync everysec/always/no
         * appendfsync同步频率的区别
          选项 | 同步频率 
          :-: | :-: | 
          always | 每个redis写命令都会同步写入硬盘，会严重影响redis性能 | 
          everysec | 每秒同步一次，显示的将多个写命令写入硬盘 | 
          no | 让操作系统决定何时同步 | 
   * 注意：这两种持久化的方式既可以单独的使用，也可以同时使用，具体选择哪种方式需要根据具体的情况进行选择
   
* **Redis 集群方案与实现**
   * redis主从方案
   * redis cluster方案
      * Redis Cluster是一种服务器Sharding技术，3.0版本开始正式提供
      * 关键字：槽，hash，master，失效推举

* **Redis 为什么是单线程的**
   * 单线程（single-threaded）。Redis的性能瓶颈不在于CPU资源，而在于内存访问和网络IO。而采用单线程的设计带来的好处是，极大简化了数据结构和算法的实现。相反，Redis通过异步IO和pipelining等机制来实现高速的并发访问。显然，单线程的设计，对于单个请求的快速响应时间也提出了更高的要求
   * pipeline
      
* **缓存雪崩、缓存穿透、缓存预热、缓存更新、缓存降级**
   * 缓存雪崩：由于原有缓存失效，新缓存未到期间(例如：我们设置缓存时采用了相同的过期时间，在同一时刻出现大面积的缓存过期)，所有原本应该访问缓存的请求都去查询数据库了，而对数据库CPU和内存造成巨大压力，严重的会造成数据库宕机。从而形成一系列连锁反应，造成整个系统崩溃
      * 缓存雪崩解决方案：使用锁或队列、为key设置不同的缓存失效时间
   * 缓存穿透：指用户查询数据，在数据库没有，自然在缓存中也不会有。这样就导致用户查询的时候，在缓存中找不到，每次都要去数据库再查询一遍，然后返回空（相当于进行了两次无用的查询）。这样请求就绕过缓存直接查数据库，这也是经常提的缓存命中率问题
      *  缓存穿透解决方案：
         1. 采用布隆过滤器，将所有可能存在的数据哈希到一个足够大的bitmap中，一个一定不存在的数据会被这个bitmap拦截掉，从而避免了对底层存储系统的查询压力。
         2. 如果一个查询返回的数据为空（不管是数据不存在，还是系统故障），我们仍然把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟。通过这个直接设置的默认值存放到缓存，这样第二次到缓存中获取就有值了，而不会继续访问数据库，这种办法最简单粗暴！
   * 缓存预热：系统上线后，提前将相关的缓存数据直接加载到缓存系统。避免在用户请求的时候，先查询数据库，然后再将数据缓存的问题！用户直接查询事先被预热的缓存数据！
   * 缓存更新：除了缓存服务器自带的缓存失效策略之外（Redis默认的有6中策略可供选择），我们还可以根据具体的业务需求进行自定义的缓存淘汰，常见的策略有两种：
      1. 定时去清理过期的缓存；
      2. 当有用户请求过来时，再判断这个请求所用到的缓存是否过期，过期的话就去底层系统得到新数据并更新缓存。
      
* **使用缓存的合理性问题**

* **Redis常见的回收策略**

* **Redis分布式锁**
   * 分布式环境下，传统的jdk自带的同步锁无法满足需求，所以需要其他机制来做同步
   * 分布式锁需满足：
      1. 互斥性。在任意时刻，只有一个客户端能持有锁。
      2. 不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
      3. 具有容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
      4. 只有自己可以解锁自己。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。
   * 使用命令介绍
      * 在使用Redis实现分布式锁的时候，主要就会使用到这三个命令。
         1. SETNX ```SETNX key val``` 当且仅当key不存在时，set一个key为val的字符串，返回1；若key存在，则什么都不做，返回0。
         2. expire ```expire key timeout``` 为key设置一个超时时间，单位为second，超过这个时间锁会自动释放，避免死锁。
         3. delete ```delete key``` 删除key。
   * jedis代码实现
   ```
       public class RedisTool {
       
           private static final String LOCK_SUCCESS = "OK";
           private static final String SET_IF_NOT_EXIST = "NX";
           private static final String SET_WITH_EXPIRE_TIME = "PX";
           private static final Long RELEASE_SUCCESS = 1L;
       
           /**
            * 尝试获取分布式锁
            * @param jedis Redis客户端
            * @param lockKey 锁
            * @param requestId 请求标识
            * @param expireTime 超期时间
            * @return 是否获取成功
            */
           public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
       
               String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
       
               if (LOCK_SUCCESS.equals(result)) {
                   return true;
               }
               return false;
       
           }
           
           /**
            * 释放分布式锁
            * @param jedis Redis客户端
            * @param lockKey 锁
            * @param requestId 请求标识
            * @return 是否释放成功
            */
           public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
       
               String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
               Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
       
               if (RELEASE_SUCCESS.equals(result)) {
                   return true;
               }
               return false;
       
           }
       
       }
   ```
