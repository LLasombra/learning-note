# Docker 应用总结

标签（空格分隔）： 技术总结

---

参考 - [Docker 入门教程 - 阮一峰](http://www.ruanyifeng.com/blog/2018/02/docker-tutorial.html)
#通过启动nginx 学习docker的使用
## docker 操作命令
```
# 在docker 仓库中搜索指定的镜像
docker search nginx

# 根据名称拉取镜像到本地
docker pull nginx

# 列出本地所有下载的镜像
docker images

# 列出正在运行的容器
docker ps
# 使用 docker ps -a 命令即可列出所有（包括已停止的）的容器
docker ps -a

# 删除 image 文件
$ docker image rm [imageName]

#从 image 文件，生成一个正在运行的容器实例。
docker container run

# 列出所有的容器
docker container ls
# 列出本机所有容器，包括终止运行的容器
docker container ls --all

# 终止容器运行
docker container kill

# 删除容器
docker container rm [containerID]

# run命令是新建容器，每运行一次，就会新建一个容器。同样的命令运行两次，就会生成两个一模一样的容器文件。如果希望重复使用容器，就要使用命令，它用来启动已经生成、已经停止运行的容器文件。
docker container start

# 前面的docker container kill命令终止容器运行，相当于向容器里面的主进程发出 SIGKILL 信号。而docker container stop命令也是用来终止容器运行，相当于向容器里面的主进程发出 SIGTERM 信号，然后过一段时间再发出 SIGKILL 信号。
docker container stop

# docker container logs命令用来查看 docker 容器的输出，即容器里面 Shell 的标准输出。如果docker run命令运行容器的时候，没有使用-it参数，就要用这个命令查看输出
docker container logs
```
##启动一个nginx容器,并映射本机目录到docker
```
# /var/www/html 为本机的路径
# /usr/share/nginx/html 为docker容器内nginx发布的路径
docker run --name mynginx -d -p 7777:80 -v /var/www/html:/usr/share/nginx/html  nginx
```




