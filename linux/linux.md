## 一、common

- **apachectl -v**: 查看 Apache 的 version
- **history |grep xxx**: 查看历史命令:
- **cp -r 目录 目录**
- **mv -i/f 目录 目录**
- **sudo service apache2 restart**: 重启服务
- **uname -a**: 查看 Linux 的内核
- **lsb_release -a**: 查看 linux 系统版本
- **lsof -i :80**: 查看端口的使用
- **ls | wc -w**: 查看文件个数
- **df -h**:查看磁盘使用情况
- **du sh**:查看文件夹大小

## 二、软件相关

- **dpkg --list | grep mysql**: 查看安装的软件
- **sudo apt-get --purge remove <programname>**: 卸载软件和配置
- **sudo apt-get remove <programname>**: 只卸载软件保留配置文件
- **apt-get -f install**: 安装依赖

## 三、卸载安装卸载

### 安装

- apt-get install git
- deb: sudo dpkg -i git.deb
- AppImage:
  ```shell
  chmod a+x git.AppImage
  ./git.AppImage
  ```

### 卸载

- apt-get remove git
- **dpkg --get-selections |grep firefox**
- **sudo apt-get purge firefox firefox-locale-en firefox-locale-zh-hans**

## 四、翻墙 VPN

> **_天坑 :_** **apt-get install 安装的 `shadowsocks`需要使用到 `distribute`, 但是这个 Python3.3 之后就不在维护, 因此不能使用之上的 python 版本作为默认的 python 环境**

```shell
# 1. 安装shadowsocks
sudo apt-get update
sudo apt-get install shadowsocks
# 2. 在/etc/shadowsocks/目录下, 创建 config.json:
{
  "server":"****",
  "server_port":18889,
  "local_address": "127.0.0.1",
  "local_port":1080,
  "password":"057510",
  "timeout":300,
  "method":"aes-256-cfb",
  "fast_open": false,
  "workers": 1,
  "prefer_ipv6": false
}
# 3. 创建 ss.sh 处理文件
#!/bin/sh
#sr.sh
sslocal -c /etc/shadowsocks/config.json
# 4. 使创建的 ss.sh 文件开机自动执行
# 4.1 建立 rc-local.service 文件
sudo vim /etc/systemd/system/rc-local.service
# 4.2 复制下列内容到 rc-local.service 文件中
[Unit]
Description=/etc/rc.local Compatibility
ConditionPathExists=/etc/rc.local

[Service]
Type=forking
ExecStart=/etc/rc.local start
TimeoutSec=0
StandardOutput=tty
RemainAfterExit=yes
SysVStartPriority=99

[Install]
WantedBy=multi-user.target
# 4.3 创建文件 rc.local
sudo vim /etc/rc.local
# 4.4 将下列内容复制进rc.local文件
#!/bin/sh -e
#
# rc.local
#
# This script is executed at the end of each multiuser runlevel.
# Make sure that the script will "exit 0" on success or any other
# value on error.
#
# In order to enable or disable this script just change the execution
# bits.
#
# By default this script does nothing.
nohup bash /etc/shadowsocks/ss.sh &
# 4.5 给rc.local加上权限
sudo chmod +x /etc/rc.local
# 4.6 将下列内容复制进rc.local文件
sudo systemctl enable rc-local
# 4.7 启动服务并检查状态
sudo systemctl start rc-local.service
sudo systemctl status rc-local.service

# 5. 最重要的一步是设置本地电脑的网络代理
# 在 socks host 中配置代理: 127.0.0.1 1080, 如图:
# 6. 不使用代理时可以选择 disabled; 使用是选择 manual, 为翻墙模式
```

![avatar](https://img-blog.csdnimg.cn/20190525121523221.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NzA0MzY0,size_16,color_FFFFFF,t_70)

## 五、磁盘相关

```bash
# 查看磁盘空间使用情况
df -lh
# 查看文件夹大小
du -h --max-depth=0 # 0表示当前目录
```

## 六、 优化 Ubuntu 的桌面

1. 安装 GNOME Tweaks

```shell
sudo apt-get install gnome-tweak-tool
sudo apt-get install gnome-shell-extensions
sudo apt-get install  gnome-shell-extension-dashtodock
```

2. 打开 tweak, 选择扩展, 打开 User themes 选项: **然后选择外观，如果 shell 上有感叹号，关闭 tweak, 按 Alt+F2, 输入 r, 执行后重新打开 tweak 就没有感叹号了**
3. 配置主题和图标, [商店网址](https://www.gnome-look.org/s/Gnome/browse/cat/135/)<br>, 完成下载， 移动到 _/usr/share/themes/_ 目录下 **reboot**.
   [具体的应用、图标、shell 资源链接](./gnome-resource)
   ![avatar](https://img-blog.csdnimg.cn/20190526144526575.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NzA0MzY0,size_16,color_FFFFFF,t_70)
4. 安装 _Dash to dock_，并进行设置 dock, 具体如图:
   ![avatar](https://img-blog.csdnimg.cn/20190526150622341.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NzA0MzY0,size_16,color_FFFFFF,t_70)
5. 更换锁屏的壁纸
6. 设置终端

```shell
# Zsh是替代Bash的终端，还可以设置多种主题，在终端中安装：
sudo apt-get install zsh
sh -c "$(curl -fsSL https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"
# 最后用Zsh替换Bash：
chsh -s `which zsh`
```

## 注 1: 搜狗输入法的安装:

- [referece](https://blog.csdn.net/neuroc/article/details/82992524)
- **sudo apt-get install fcitx-bin**
- **sudo dpkg -i 搜狗安装包的文件名**
- **sudo apt install -f # 如果出现依赖错误输入这行处理，执行完后，继续输入上面的命令 sudo apt --fix-broken install**
- **安装成功过后，进入设置 根据红色箭头进入语言安装界面，安装语言（会自动安装中文语言）**
- **注销或者重启**
- **Configure Current Input Method 调出搜狗，并置顶**

## 通过命令打开文件夹 opendir:

- 1. 在 _/usr/bin_ 下创建 opendir 文件
- 2. 给权限 755
  ```shell
  touch opendir
  sudo chmod 755 opendir
  ```
- 3. 填写 opendir 文件内容
  ```bash
  if [ -n "$1" ]; then
        nautilus "$1" > /dev/null 2>&1
  else
        nautilus "$(pwd)" > /dev/null 2>&1
  fi
  ```
