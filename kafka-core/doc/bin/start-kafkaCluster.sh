# ----- 信息 -----
# @filename start.sh
# @version 1.0
# @author qye.zheng
# @description 启动脚本

# 解决远程ssh执行脚本失败问题
source /etc/profile

# 进入脚本所在目录
cd `dirname $0`
nohup ./bin/kafka-server-start.sh config/server0.properties >/dev/null 2>&1 &
nohup ./bin/kafka-server-start.sh config/server1.properties >/dev/null 2>&1 &
nohup ./bin/kafka-server-start.sh config/server2.properties >/dev/null 2>&1 &



