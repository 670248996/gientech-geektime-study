## Redis 操作命令

#### 一. 基本数据类型

##### 1. 字符串（String）

使用场景：存储token、用户信息、点赞计数等

```lua
SET key value	存储一个字符串值。
GET key 检索字符串值。
DEL key 删除指定key。
EXPIRE key seconds 设置key的生存时间。

SETEX key seconds value 过期时间设置为 seconds 秒。（SET key value EX seconds） 
PSETEX key milliseconds value 将过期时间设置为 milliseconds 毫秒。（SET key value PX milliseconds）
SETNX key value 仅当键不存在时才存储字符串值。用于实现锁。（SET key value NX）
MGET key1 key2 检索多个字符串值。
INCR key 将key中存储的值增1（key不存在，则先初始化为0，再增1）。
DECR key 将key中存储的值减1（key不存在，则先初始化为0，再减1）。
INCRBY key num 指定增num
DECRBY key num 指定减num
```

##### 2. 列表（List）链表、有序、可重复

使用场景：数据字典等

```lua
LPUSH key value1 value2 value3 添加元素到列表的头部。
RPUSH key value4 value5 value6 添加元素到列表的尾部。
LPOP key 从列表的头部移除一个元素。
RPOP key 从列表的尾部移除一个元素。

# 根据count值移除列表key中与value相等的值。
# count = 0 移除所有与value相等的值。
# count > 0 从表头开始移除与value相等的值，数量为count。
# count < 0 从表尾开始移除与value相等的值，数量为count绝对值。
LREM key count value
LINDEX key index 返回列表key中，下标为index的元素
LLEN key 返回列表的长度。
LTRIM key start stop 将列表进行截取，保留下标从start到stop间的数据。
```

##### 3. 哈希（Hash）键值对结构

使用场景：购物车redis存储设计

```lua
HSET key name value 在key对应的hash中添加name-value键值对。
HGET key name 获取key对应的hash中name对应的值。

HMGET key name1 name2 批量获取hash中键对应的值。
HINCRBY 通过提供的整数增加给定字段的值。
HKEYS key 返回hash表中所有的键
HVALS key 返回hash表中所有的值
HDEL key name 删除hash中的name键。
```

##### 4. 集合（Set）无序、不重复

使用场景：文章关联用户点赞、浏览信息

```lua
SADD key value1 value2 value3 将元素添加到集合中。
SMEMBERS key 查看集合元素。
SREM key value 从集合中删除指定的元素。
SISMEMBER key value 判断集合集合中是否包含指定value。
SCARD 返回集合的大小（又名基数）。
```

##### 5. 有序集合（ZSet）降序排列

使用场景：排行榜

```lua
ZADD key score value 将value和关联分数score添加到key集合中。
ZRANGE key start stop 返回范围start到stop内排序的元素。
ZRANK key value 返回value元素的排名（score降序）。
ZREVRANK key value 返回value元素的排名（score升序）。
```

#### 二. 特殊数据类型

##### 6. BitMap 位存储 本质是String

使用场景：用户在线状态、统计活跃用户等

```lua
setbit key offset value 设置key对应的值中对应offset的bit值
getbit key offset 获取key对应的值中对应offset的bit值
bitcount key [start end] 统计指定范围内bit为1的个数
bitcount key 统计所有
```

##### 7. HyperLogLog 基数统计 本质是String

```lua
pfadd key element [element ...] 添加一个或多个元素，有变化返回1，无变化返回0
pfcount key 查看元素个数
pfmerge key1 key2 key3 合并key2，key3到key1中 
```

##### 8. GEO 地理信息位置 本质是ZSet

```lua
geoadd key 经度 纬度 member [经度 纬度 member ...] 添加一个或多个地理位置
geopos key member [member ...] 获取指定一个或多个member的经纬度
geodist key member1 member2 返回两个member之间的距离

# 以给定的经纬度为中心找出某一半径内的元素
# 其中单位有km千米,m米,mi英里,ft英尺
# withdist 显示经度
# withcoord 显示纬度
# count x显示x个
georadius key 经度 纬度 半径 [单位] [withdist] [withcoord] [count x]

# 根据集合中的某一个点找出指定范围内的元素
# 其中单位有km千米,m米,mi英里,ft英尺
# withdist 显示经度
# withcoord 显示纬度
# count x显示x个
georadiusbymember key member [单位] [withdist] [withcoord] [count x]

geohash key member [member ...] 返回一个或多个member的geohash字符串
```

