### Redis 集群部署(Docker)

#### 1. 创建redis网卡

```shell
# 拉取redis镜像到本地
docker pull redis
# 查看本地镜像
docker images
# 创建redis网卡
docker network create redis --subnet 172.38.0.0/16
# 查看网卡信息
docker network ls
```

#### 2. 创建redis集群服务脚本

```shell
vim create_redis_script.sh

####################################################
for port in $(seq 1 6); \
do \
mkdir -p /home/docker/redis/cluster/node-${port}/conf
touch /home/docker/redis/cluster/node-${port}/conf/redis.conf
cat << EOF >/home/docker/redis/cluster/node-${port}/conf/redis.conf
port 6379
bind 0.0.0.0
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip 172.38.0.1${port}
cluster-announce-port 6379
cluster-announce-bus-port 16379
appendonly yes
#daemonize yes
EOF
done
####################################################

# 执行脚本
sh create_redis_script.sh
```

#### 3. 创建reids集群启动脚本

```shell
vim start_redis_script.sh

####################################################
for port in $(seq 1 6); \
do \
echo ${port}
docker run -p 637${port}:6379 -p 1637${port}:16379 --name redis-${port} \
-v /home/docker/redis/cluster/node-${port}/data:/data \
-v /home/docker/redis/cluster/node-${port}/conf/redis.conf:/etc/redis/redis.conf \
-d --net redis --ip 172.38.0.1${port} redis:latest redis-server /etc/redis/redis.conf; \
done
####################################################

# 执行脚本
sh start_redis_script.sh
```

#### 4. 创建redis集群

```shell
# 进入redis-1容器
docker exec -it redis-1 /bin/sh

# 创建集群主节点 
# --cluster-replicas 参数为数字，1表示每个主节点需要1个从节点。
redis-cli --cluster create 172.38.0.11:6379 172.38.0.12:6379 172.38.0.13:6379 172.38.0.14:6379 172.38.0.15:6379 172.38.0.16:6379 --cluster-replicas 1

# 连接集群 -c
redis-cli -c
# 查看集群信息
cluster info
# 查看节点信息
cluster nodes
```

