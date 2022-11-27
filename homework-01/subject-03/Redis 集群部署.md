## Redis 集群部署

#### 1. 创建 Redis 集群的要求

##### 要创建集群，首先需要有几个空的 Redis 实例以集群模式运行

在`redis.conf`文件中设置以下指令

```yaml
#端口
port 7000
#开启集群部署
cluster-enabled yes
#节点配置的文件路径
cluster-config-file nodes_7000.conf
#节点超时时长（毫秒）
cluster-node-timeout 5000
#后台运行
appendonly yes
```

##### 最小集群必须至少包含三个主节点，强烈建议使用具有三个主节点和三个副本的六节点集群

在每个目录中创建一个`redis.conf`文件，从 7000 到 7005

```shell
mkdir cluster-test
cd cluster-test
mkdir 7000 7001 7002 7003 7004 7005
```

按如下方式启动每个实例

```shell
redis-server ./7000/redis.conf
redis-server ./7001/redis.conf
redis-server ./7002/redis.conf
redis-server ./7003/redis.conf
redis-server ./7004/redis.conf
redis-server ./7005/redis.conf
```

#### 2. 创建 Redis 集群

这里使用的命令是**create**，创建一个新集群。

选项`--cluster-replicas 1`为每个创建的母版创建一个副本

```lua
redis-cli --cluster create 127.0.0.1:7000 127.0.0.1:7001 \
127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 \
--cluster-replicas 1
```

#### 3. 查看集群状态

使用`redis-cli`进入一个Redis 集群实例

```csharp
redis-cli -c -p 7000
127.0.0.1:7000> cluster nodes
```
