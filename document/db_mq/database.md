# 数据库
* **MySQL 索引使用的注意事项**
   ``` 
     SELECT `sname` FROM `stu` WHERE `age`+10=30;-- 不会使用索引,因为所有索引列参与了计算
     
     SELECT `sname` FROM `stu` WHERE LEFT(`date`,4) <1990; -- 不会使用索引,因为使用了函数运算,原理与上面相同
     
     SELECT * FROM `houdunwang` WHERE `uname` LIKE'后盾%' -- 走索引
     
     SELECT * FROM `houdunwang` WHERE `uname` LIKE "%后盾%" -- 不走索引
     
     -- 正则表达式不使用索引,这应该很好理解,所以为什么在SQL中很难看到regexp关键字的原因
     
     -- 字符串与数字比较不使用索引;
     CREATE TABLE `a` (`a` char(10));
     EXPLAIN SELECT * FROM `a` WHERE `a`="1" -- 走索引
     EXPLAIN SELECT * FROM `a` WHERE `a`=1 -- 不走索引
     
     select * from dept where dname='xxx' or loc='xx' or deptno=45 --如果条件中有or,即使其中有条件带索引也不会使用。换言之,就是要求使用的所有字段,都必须建立索引, 我们建议大家尽量避免使用or 关键字
     
     -- 如果mysql估计使用全表扫描要比使用索引快,则不使用索引
   ```
   1. 索引不会包含有NULL的列
      * 只要列中包含有NULL值，都将不会被包含在索引中，复合索引中只要有一列含有NULL值，那么这一列对于此符合索引就是无效的。
   2. 使用短索引
       * 对串列进行索引，如果可以就应该指定一个前缀长度。例如，如果有一个char（255）的列，如果在前10个或20个字符内，多数值是唯一的，那么就不要对整个列进行索引。短索引不仅可以提高查询速度而且可以节省磁盘空间和I/O操作。
   3. 索引列排序
       * mysql查询只使用一个索引，因此如果where子句中已经使用了索引的话，那么order by中的列是不会使用索引的。因此数据库默认排序可以符合要求的情况下不要使用排序操作，尽量不要包含多个列的排序，如果需要最好给这些列建复合索引。
   4. like语句操作
       * 一般情况下不鼓励使用like操作，如果非使用不可，注意正确的使用方式。like ‘%aaa%’不会使用索引，而like ‘aaa%’可以使用索引。
   5. 不要在列上进行运算
   6. 不使用NOT IN 、<>、！=操作，但<,<=，=，>,>=,BETWEEN,IN是可以用到索引的
   7. 索引要建立在经常进行select操作的字段上。
       * 这是因为，如果这些列很少用到，那么有无索引并不能明显改变查询速度。相反，由于增加了索引，反而降低了系统的维护速度和增大了空间需求。
   8. 索引要建立在值比较唯一的字段上。
   9. 对于那些定义为text、image和bit数据类型的列不应该增加索引。因为这些列的数据量要么相当大，要么取值很少。
   10. 在where和join中出现的列需要建立索引。
   11. where的查询条件里有不等号(where column != …),mysql将无法使用索引。
   12. 如果where字句的查询条件里使用了函数(如：where DAY(column)=…),mysql将无法使用索引。
   13. 在join操作中(需要从多个数据表提取数据时)，mysql只有在主键和外键的数据类型相同时才能使用索引，否则及时建立了索引也不会使用。
   
* **MySQL 联合索引**
   * 命名规则：表名_字段名
      1. 需要加索引的字段，要在where条件中
      2. 数据量少的字段不需要加索引
      3. 如果where条件中是OR关系，加索引不起作用
      4. 符合最左原则
         * Mysql从左到右的使用索引中的字段，一个查询可以只使用索引中的一部份，但只能是最左侧部分。例如索引是key index (a,b,c). 可以支持a | a,b| a,b,c 3种组合进行查找，但不支持 b,c进行查找 .当最左侧字段是常量引用时，索引就十分有效(条件顺序不影响索引命中)
   * 注意事项
      1. 比如有一条语句是这样的：select * from users where area=’beijing’ and age=22;
         * 如果我们是在area和age上分别创建单个索引的话，由于mysql查询每次只能使用一个索引，所以虽然这样已经相对不做索引时全表扫描提高了很多效
         率，但是如果在area、age两列上创建复合索引的话将带来更高的效率。如果我们创建了(area, age,salary)的复合索引，那么其实相当于创建了(area,age,salary)、(area,age)、(area)三个索引，这被称为最佳左前缀特性。
         因此我们在创建复合索引时应该将最常用作限制条件的列放在最左边，依次递减。
         
* **DDL、DML、DCL分别指什么**
   * DDL（data definition language）数据库定义语言：其实就是我们在创建表的时候用到的一些sql，比如说：CREATE、ALTER、DROP等。DDL主要是用在定义或改变表的结构，数据类型，表之间的链接和约束等初始化工作上
   * DML（data manipulation language）数据操纵语言：就是我们最经常用到的 SELECT、UPDATE、INSERT、DELETE。 主要用来对数据库的数据进行一些操作
   * DCL（Data Control Language）数据库控制语言：是用来设置或更改数据库用户或角色权限的语句，包括（grant,deny,revoke等）语句。这个比较少用到。
   
* **explain命令**
   * https://www.cnblogs.com/gomysql/p/3720123.html
   * EXPLAIN命令（执行计划）是查看优化器如何决定执行查询的主要方法
   * id  | select_type | table  | type | possible_keys | key | key_len | ref  | rows | Extra |
     :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | 
      1. id
         * 包含一组数字，表示查询中执行select子句或操作表的顺序
         * id如果相同，可以认为是一组，从上往下顺序执行；在所有组中，id值越大，优先级越高，越先执行
      2. select_type
         * 查询中每个select子句的类型（简单OR复杂）
            1. SIMPLE：查询中不包含子查询或者UNION
            2. 查询中若包含任何复杂的子部分，最外层查询则被标记为：PRIMARY
            3. 在SELECT或WHERE列表中包含了子查询，该子查询被标记为：SUBQUERY
            4. 在FROM列表中包含的子查询被标记为：DERIVED（衍生）用来表示包含在from子句中的子查询的select，mysql会递归执行并将结果放到一个临时表中。服务器内部称为"派生表"，因为该临时表是从子查询中派生出来的
            5. 若第二个SELECT出现在UNION之后，则被标记为UNION；若UNION包含在FROM子句的子查询中，外层SELECT将被标记为：DERIVED
            6. 从UNION表获取结果的SELECT被标记为：UNION RESULT
      3. type                                       
         * 表示MySQL在表中找到所需行的方式，又称“访问类型”，常见类型如下:  
            1. ALL：Full Table Scan， MySQL将遍历全表以找到匹配的行
            2. index：Full Index Scan，index与ALL区别为index类型只遍历索引树
            3. range：索引范围扫描，对索引的扫描开始于某一点，返回匹配值域的行。显而易见的索引范围扫描是带有between或者where子句里带有<, >查询。当mysql使用索引去查找一系列值时，例如IN()和OR列表，也会显示range（范围扫描）,当然性能上面是有差异的
            4. ref：使用非唯一索引扫描或者唯一索引的前缀扫描，返回匹配某个单独值的记录行
            5. eq_ref：类似ref，区别就在使用的索引是唯一索引，对于每个索引键值，表中只有一条记录匹配，简单来说，就是多表连接中使用primary key或者 unique key作为关联条件
            6. const：system, 当MySQL对查询某部分进行优化，并转换为一个常量时，使用这些类型访问。如将主键置于where列表中，MySQL就能将该查询转换为一个常量(system是const类型的特例，当查询的表只有一行的情况下，使用system)
            7. NULL：MySQL在优化过程中分解语句，执行时甚至不用访问表或索引，例如从一个索引列里选取最小值可以通过单独索引查找完成
            * 从上到下，性能从最差到最好
            
* **left join，right join，inner join**
   1. left join（左联接）：返回左表中的所有记录以及和右表中的联接字段相等的记录。
   2. right join（右联接）：返回右表中的所有记录以及和左表中的联接字段相等的记录。
   3. inner join（等值联接）：只返回两个表中联接字段相等的记录
   
* **数据库事物ACID（原子性、一致性、隔离性、持久性）**
   1. 原子性（Atomicity）：指事务操作要么全部成功，要么失败回滚。
   2. 一致性 （Consistency）：一个事务执行之前和执行之后都必须处于一致性状态。
   3. 隔离性（Isolation）：隔离性是当A、B 2个用户同时（并发）访问数据库操作同一张表时要相互隔离。不能同时操作即隔离。官方是这么说的：即要达到这么一种效果：对于任意两个并发的事务T1和T2，在事务T1看来，T2要么在T1开始之前就已经结束，要么在T1结束之后才开始，这样每个事务都感觉不到有其他事务在并发地执行
   4. 持久性（Durability）：持久性是指一个事务一旦被提交了，那么对数据库中的数据的改变就是永久性的。
   
* **事物的隔离级别（读未提交、读以提交、可重复读、可序列化读）**
   1. 读未提交
      * 就是事务A可以读取事务B还未提交的数据，即发生了脏读。
   2. 读已提交
      * 就是事务A读取某条数据，事务B接着更改了这条数据并且提交了，然后事务A再次读取这条数据发现数据发生了改变。在这个级别下，B无法读取A未提交的事务，避免了脏读，但未解决不可重复读，因为可能出现一个事务范围内相同的查询返回不同的数据。大多数数据库默认级别就是这个，比如Oracle
   3. 重复读
      * 就是事务A在读取某一条数据时，事务B就不能对这一条数据进行修改，这解决了不可重复读的情况，但还可能出现幻读，幻读和不可重复的区别就是，幻读对应的是insert操作，不可重复读是update操作。mySql默认级别就是重复读。
   4. 串行化
      * 这是事务的最高级别，可以避免脏读、不可重复读和幻读，但需要牺牲较大的性能，效率低，很少使用

* **脏读、幻读、不可重复读**
   * 读已提交、重复读、串行化解决了“脏读”，“不可重复读”，“幻读”这三种问题
   
* **数据库的几大范式**
   * 在关系型数据库中的一些规范称为范式
   * 三大范式：
      1. 第一范式：当关系模式R的所有属性都不能在分解为更基本的数据单位时，称R是满足第一范式的，简记为1NF。满足第一范式是关系模式规范化的最低要
         求，否则，将有很多基本操作在这样的关系模式中实现不了。
         * 每一列属性都是不可再分的属性值，确保每一列的原子性
         * 两列的属性相近或相似或一样，尽量合并属性一样的列，确保不产生冗余数据。
      2. 第二范式：如果关系模式R满足第一范式，并且R得所有非主属性都完全依赖于R的每一个候选关键属性，称R满足第二范式，简记为2NF。
         * 每一行的数据只能与其中一列相关，即一行数据只做一件事。只要数据列中出现数据重复，就要把表拆分开来
      3. 第三范式：设R是一个满足第一范式条件的关系模式，X是R的任意属性集，如果X非传递依赖于R的任意一个候选关键字，称R满足第三范式，简记为3NF
         * 
* **数据库常见的命令**
   * select、insert、update、delete、from、where、 order by （desc 、asc）、like、in、between等
   
* **说说分库与分表设计**
   * 数据切分根据其切分类型，可以分为两种方式：垂直（纵向）切分和水平（横向）切分
      * 垂直（纵向）切分
         1. 垂直分库
            * 垂直分库就是根据业务耦合性，将关联度低的不同表存储在不同的数据库。做法与大系统拆分为多个小系统类似，按业务分类进行独立划分。与"微服务治理"的做法相似，每个微服务使用单独的一个数据库
         2. 垂直分表
            * 垂直分表是基于数据库中的"列"进行，某个表字段较多，可以新建一张扩展表，将不经常用或字段长度较大的字段拆分出去到扩展表中.
            * 在字段很多的情况下（例如一个大表有100多个字段），通过"大表拆小表"，更便于开发与维护，也能避免跨页问题，MySQL底层是通过数据页存储的，一条记录占用空间过大会导致跨页，造成额外的性能开销。另外数据库以行为单位将数据加载到内存中，这样表中字段长度较短且访问频率较高，内存能加载更多的数据，命中率更高，减少了磁盘IO，从而提升了数据库性能。
         * 优点
            1. 解决业务系统层面的耦合，业务清晰
            2. 与微服务的治理类似，也能对不同业务的数据进行分级管理、维护、监控、扩展等
            3. 高并发场景下，垂直切分一定程度的提升IO、数据库连接数、单机硬件资源的瓶颈
         * 缺点
            1. 部分表无法join，只能通过接口聚合方式解决，提升了开发的复杂度
            2. 分布式事务处理复杂
            3. 依然存在单表数据量过大的问题（需要水平切分）
      * 水平（横向）切分
         * 当一个应用难以再细粒度的垂直切分，或切分后数据量行数巨大，存在单库读写、存储性能瓶颈，这时候就需要进行水平切分了。是根据表内数据内在的逻辑关系，将同一个表按不同的条件分散到多个数据库或多个表中，每个表中只包含一部分数据，从而使得单个表的数据量变小，达到分布式的效果
         1. 库内分表
         2. 分库分表
         * 优点
            1. 不存在单库数据量过大、高并发的性能瓶颈，提升系统稳定性和负载能力
            2. 应用端改造较小，不需要拆分业务模块
         * 缺点
            1. 跨分片的事务一致性难以保证
            2. 跨库的join关联查询性能较差
            3. 数据多次扩展难度和维护量极大
         * 几种典型的数据分片规则：
            1. 按照时间区间或ID区间来切分
               * 如：按日期将不同月甚至是日的数据分散到不同的库中；将userId为1~9999的记录分到第一个库，10000~20000的分到第二个库，以此类推
               * 优点：
                 1. 单表大小可控
                 2. 天然便于水平扩展，后期如果想对整个分片集群扩容时，只需要添加节点即可，无需对其他分片的数据进行迁移
                 3. 使用分片字段进行范围查找时，连续分片可快速定位分片进行快速查询，有效避免跨分片查询的问题。
               * 缺点：
                 1. 热点数据成为性能瓶颈。连续分片可能存在数据热点，例如按时间字段分片，有些分片存储最近时间段内的数据，可能会被频繁的读写，而有些分片存储的历史数据，则很少被查询
            2. 根据数值取模
               * 一般采用hash取模mod的切分方式，例如：将 Customer 表根据 cusno 字段切分到4个库中，余数为0的放到第一个库，余数为1的放到第二个库，以此类推。这样同一个用户的数据会分散到同一个库中，如果查询条件带有cusno字段，则可明确定位到相应库去查询。
               * 优点：
                 1. 数据分片相对比较均匀，不容易出现热点和并发访问的瓶颈
               * 缺点：
                 1. 后期分片集群扩容时，需要迁移旧的数据（使用一致性hash算法能较好的避免这个问题）
                 2. 容易面临跨分片查询的复杂问题。比如上例中，如果频繁用到的查询条件中不带cusno时，将会导致无法定位数据库，从而需要同时向4个库发起查询，再在内存中合并数据，取最小集返回给应用，分库反而成为拖累。
                 
* **分库与分表带来的分布式困境与应对之策（如何解决分布式下的分库分表，全局表？）**
   1. 事务一致性问题
      * 方案一：使用分布式事务
        1. 优点：交由数据库管理，简单有效
        2. 缺点：性能代价高，特别是shard越来越多时
      * 方案二：由应用程序和数据库共同控制
        * 原理：将一个跨多个数据库的分布式事务分拆成多个仅处于单个数据库上面的小事务，并通过应用程序来总控各个小事务。
        1. 优点：性能上有优势
        2. 缺点：需要应用程序在事务控制上做灵活设计。如果使用了spring的事务管理，改动起来会面临一定的困难
   2. 跨节点关联查询 join 问题
      * 切分之前，系统中很多列表和详情页所需的数据可以通过sql join来完成。而切分之后，数据可能分布在不同的节点上，此时join带来的问题就比较麻烦了，考虑到性能，尽量避免使用join查询
      * 解决这个问题的一些方法：
         1. 全局表
            * 全局表，也可看做是"数据字典表"，就是系统中所有模块都可能依赖的一些表，为了避免跨库join查询，可以将这类表在每个数据库中都保存一份。这些数据通常很少会进行修改，所以也不担心一致性的问题。
         2. 字段冗余
            * 一种典型的反范式设计，利用空间换时间，为了性能而避免join查询。例如：订单表保存userId时候，也将userName冗余保存一份，这样查询订单详情时就不需要再去查询"买家user表"了。但这种方法适用场景也有限，比较适用于依赖字段比较少的情况。而冗余字段的数据一致性也较难保证，就像上面订单表的例子，买家修改了userName后，是否需要在历史订单中同步更新呢？这也要结合实际业务场景进行考虑。
         3. 数据组装
            * 在系统层面，分两次查询，第一次查询的结果集中找出关联数据id，然后根据id发起第二次请求得到关联数据。最后将获得到的数据进行字段拼装。
         4. ER分片
            * 关系型数据库中，如果可以先确定表之间的关联关系，并将那些存在关联关系的表记录存放在同一个分片上，那么就能较好的避免跨分片join问题。在1:1或1:n的情况下，通常按照主表的ID主键切分。
   3. 跨节点分页、排序、函数问题
      * 跨节点多库进行查询时，会出现limit分页、order by排序等问题。分页需要按照指定字段进行排序，当排序字段就是分片字段时，通过分片规则就比较容易定位到指定的分片；当排序字段非分片字段时，就变得比较复杂了。需要先在不同的分片节点中将数据进行排序并返回，然后将不同分片返回的结果集进行汇总和再次排序，最终返回给用户
      * 在使用Max、Min、Sum、Count之类的函数进行计算的时候，也需要先在每个分片上执行相应的函数，然后将各个分片的结果集进行汇总和再次计算，最终将结果返回
   4. 全局主键避重问题
      * 在分库分表环境中，由于表中数据同时存在不同数据库中，主键值平时使用的自增长将无用武之地，某个分区数据库自生成的ID无法保证全局唯一。因此需要单独设计全局主键，以避免跨库主键重复问题。有一些常见的主键生成策略：
         1. UUID
            * UUID标准形式包含32个16进制数字，分为5段，形式为8-4-4-4-12的36个字符，例如：550e8400-e29b-41d4-a716-446655440000
            * UUID是主键是最简单的方案，本地生成，性能高，没有网络耗时。但缺点也很明显，由于UUID非常长，会占用大量的存储空间；另外，作为主键建立索引和基于索引进行查询时都会存在性能问题，在InnoDB下，UUID的无序性会引起数据位置频繁变动，导致分页。
         2. 结合数据库维护主键ID表
         3. Snowflake分布式自增ID算法
            * 64位的Long型数字
               1. 第一位未使用
               2. 接下来41位是毫秒级时间，41位的长度可以表示69年的时间
               3. 5位datacenterId，5位workerId。10位的长度最多支持部署1024个节点
               4. 最后12位是毫秒内的计数，12位的计数顺序号支持每个节点每毫秒产生4096个ID序列
               
   5. 数据迁移、扩容问题
      * 当业务高速发展，面临性能和存储的瓶颈时，才会考虑分片设计，此时就不可避免的需要考虑历史数据迁移的问题。一般做法是先读出历史数据，然后按指定的分片规则再将数据写入到各个分片节点中。此外还需要根据当前的数据量和QPS，以及业务发展的速度，进行容量规划，推算出大概需要多少分片（一般建议单个分片上的单表数据量不超过1000W）
      * 如果采用数值范围分片，只需要添加节点就可以进行扩容了，不需要对分片数据迁移。如果采用的是数值取模分片，则考虑后期的扩容问题就相对比较麻烦。
      
* **说说 SQL 优化之道**
   * 常见优化规则
      * 表连接数
          1. 连接的表越多，性能越差
          2. 可能的话，将连接拆分成若干个过程逐一执行
          3. 优先执行可显著减少数据量的连接，既降低了复杂度，也能够容易按照预期执行
          4. 如果不可避免多表连接，很可能是设计缺陷
          5. 外链接效果差，因为必须对左右表进行表扫描
          6. 尽量使用inner join查询
          7. 如果不可避免，可以考虑使用临时表或表变量存放中间结果。
          8. 少用子查询
          9. 视图嵌套不要过深,一般不要超过2个为宜
      * SQL编写注意事项
         1. NULL列：Null列使用索引没有意义，任何包含null值的列都不会被包含在索引中。因此where语句中的is null或is not null的语句优化器是不允许使用索引的。
         2. concat或||：concat或||是mysql和oracle的字符串连接操作，如果对列进行该函数操作，那么也开会忽略索引的使用
         3. like：通配符出现在首位，无法使用索引，反之可以（'t%'）
         4. order by：order by子句中不要使用非索引列或嵌套表达式，这样都会导致性能降低。
         5. Not运算：not运算无法使用索引，可以改成其他能够使用索引的操作。
         6. where与having：select .. from .. on .. where .. group by .. having .. order by .. limit ..，以上是sql语句的语法结构，其中on、where和having是有过滤行为的，过滤行为越能提前完成就越可以减少传递给下一个阶段的数据量，因此如果在having中的过滤行为能够在where中完成，则应该优先考虑where来实现
         7. exists替代in：not in是最低效的，因为要对子查询的表进行全表扫描。可以考虑使用外链接或not exists。
         8. 索引：索引的好处可以实现折半查找，时间复杂度是O(log2n)
         9. 使用">="代替">"
         10. union代替or：在索引列上，可以使用union替换or操作。索引列上的or操作会造成全表扫描
         11. union & union all：union具有去重的操作，增加了计算时间。union all不需要去重，但会包含相同记录。同样功能下，首选union all操作。
         
* **MySQL遇到的死锁问题、如何排查与解决**
* **存储引擎的 InnoDB与MyISAM区别，优缺点，使用场景**
   * 主要区别：
     1. MyISAM是非事务安全型的，而InnoDB是事务安全型的。
     2. MyISAM锁的粒度是表级，而InnoDB支持行级锁定。
     3. MyISAM支持全文类型索引，而InnoDB不支持全文索引。
     4. MyISAM相对简单，所以在效率上要优于InnoDB，小型应用可以考虑使用MyISAM。
     5. MyISAM表是保存成文件的形式，在跨平台的数据转移中使用MyISAM存储会省去不少的麻烦。
     6. InnoDB表比MyISAM表更安全，可以在保证数据不会丢失的情况下，切换非事务表到事务表（alter table tablename type=innodb）。
   * 应用场景：
     1. MyISAM管理非事务表。它提供高速存储和检索，以及全文搜索能力。如果应用中需要执行大量的SELECT查询，那么MyISAM是更好的选择。
     2. InnoDB用于事务处理应用程序，具有众多特性，包括ACID事务支持。如果应用中需要执行大量的INSERT或UPDATE操作，则应该使用InnoDB，这样可以提高多用户并发操作的性能。
     
* **索引类别（B+树索引、全文索引、哈希索引）、索引的原理**
   * Mysql目前主要有以下几种索引类型：FULLTEXT，HASH，BTREE
   1. FULLTEXT
      * 即为全文索引，目前只有MyISAM引擎支持。其可以在CREATE TABLE ，ALTER TABLE ，CREATE INDEX 使用，不过目前只有 CHAR、VARCHAR ，TEXT 列上可以创建全文索引。值得一提的是，在数据量较大时候，现将数据放入一个没有全局索引的表中，然后再用CREATE INDEX创建FULLTEXT索引，要比先为一张表建立FULLTEXT然后再将数据写入的速度快很多。
   2. HASH
      * hash就是一种（key=>value）形式的键值对
         1. Hash 索引仅仅能满足"=","IN"和"<=>"查询，不能使用范围查询
         2. Hash 索引不能利用部分索引键查询
            * 对于组合索引，Hash 索引在计算 Hash 值的时候是组合索引键合并后再一起计算 Hash 值，而不是单独计算 Hash 值，所以通过组合索引的前面一个或几个索引键进行查询的时候，Hash 索引也无法被利用。
         3. Hash 索引在任何时候都不能避免表扫描
            * 前面已经知道，Hash 索引是将索引键通过 Hash 运算之后，将 Hash运算结果的 Hash 值和所对应的行指针信息存放于一个 Hash 表中，由于不同索引键存在相同 Hash 值，所以即使取满足某个 Hash 键值的数据的记录条数，也无法从 Hash 索引中直接完成查询，还是要通过访问表中的实际数据进行相应的比较，并得到相应的结果。 
         4. Hash 索引遇到大量Hash值相等的情况后性能并不一定就会比B-Tree索引高
   3. BTREE索引就是一种将索引值按一定的算法，存入一个树形的数据结构中
      * BTREE在MyISAM里的形式和Innodb稍有不同
        1. 在 Innodb里，有两种形态：一是primary key形态，其leaf node里存放的是数据，而且不仅存放了索引键的数据，还存放了其他字段的数据。二是secondary index（辅助索引），其leaf node和普通的BTREE差不多，只是还存放了指向主键的信息.
        2. 在MyISAM里，主键和其他的并没有太大区别。不过和Innodb不太一样的地方是在MyISAM里，leaf node里存放的不是主键的信息，而是指向数据文件里的对应数据行的信息.
        
* **什么是自适应哈希索引（AHI）**
* **为什么要用 B+tree作为MySQL索引的数据结构**
* **聚集索引与非聚集索引的区别**
* **遇到过索引失效的情况没，什么时候可能会出现，如何解决**
* **limit 20000 加载很慢怎么解决**
* **如何选择合适的分布式主键方案**
* **选择合适的数据存储方案**
* **常见的几种分布式ID的设计方案**
* **常见的数据库优化方案，在你的项目中数据库如何进行优化的**