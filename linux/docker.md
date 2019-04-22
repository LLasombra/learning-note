## 安装:
  * uname -a  ：查看内核版本
  *	**step 1: 安装必要的一些系统工具:**
	```shell
	sudo apt-get update
	sudo apt-get -y install apt-transport-https ca-certificates curl software-properties-common
	```
  *	**step 2: 安装GPG证书，并查看证书:**
	```shell
	curl -fsSL http://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
	sudo apt-key fingerprint 0EBFCD88
	```
  *	**Step 3: 查看Ubuntu版本，写入软件源信息:**
	```shell
	sudo lsb_release -cs
	# 根据CPU类型选择添加哪种源
	amd64: $ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
	armhf: $ sudo add-apt-repository "deb [arch=armhf] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
	s390x: $ sudo add-apt-repository "deb [arch=s390x] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
	```
  * **Step 4: 更新apt包索引，并安装 Docker-CE:**
	```shell
	sudo apt-get -y update
	sudo apt-get -y install docker-ce
	```
  * **Step 5: 检验安装是否成功:**
	```shell
	sudo docker version
	```


## 配置文件： 例子： mysql [/etc/mysql/mysql.conf.d# vim mysqld.cnf ]
  *	1. 配置镜像加速器
		针对Docker客户端版本大于 1.10.0 的用户

		您可以通过修改daemon配置文件/etc/docker/daemon.json来使用加速器
		```shell
		sudo mkdir -p /etc/docker
		sudo tee /etc/docker/daemon.json <<-'EOF'
		{
		  "registry-mirrors": ["https://wfjvo9ge.mirror.aliyuncs.com"]
		}
		EOF
		sudo systemctl daemon-reload
		sudo systemctl restart docker

		# 设置开机启动redis
		sudo docker run --restart=always redis
		```

## 常用的docker指令：
	```shell
	service docker start/stop
	docker version
	docker info
	docker --help
	# 查看所有本地镜像
	docker images
	# 删除指定的本地镜像
	docker rmi image-id
	# 我们经常去docker hub上检索镜像的详细信息，如镜像的TAG.
	# eg：docker search redis
	docker search 关键字
	# :tag是可选的，tag表示标签，多为软件的版本，默认是latest
	docker pull 镜像名:tag
	# [‐d：后台运行  ‐p: 将主机的端口映射到容器的一个端口    主机端口:容器内部的端口]
	docker run ‐d ‐p 8888:8080 tomcat
	# [查看容器的日志]
	docker logs container‐name/container‐id
	# 查看防火墙状态
	service firewalld status 
	# 关闭防火墙
	service firewalld stop
	# [查看运行中的容器]
	docker ps
	# [查看所有的容器]
	docker ps ‐a
	# [启动容器]
	docker start 容器id
	# [停止运行中的容器]
	docker stop  容器的id
	# [删除一个容器]
	docker rm 容器id
	```