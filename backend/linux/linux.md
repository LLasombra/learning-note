## 一、common
  * **apachectl -v**: 查看Apache的version
  *	**history |grep xxx**: 查看历史命令:
  * **cp -r 目录 目录**
  * **mv -i/f 目录 目录**
  * **sudo service apache2 restart**: 重启服务
  * **uname -a**: 查看Linux的内核
  * **lsb_release -a**: 查看linux系统版本
  * **lsof -i :80**: 查看端口的使用
  * **ls | wc -w**: 查看文件个数
  * **df -h**:查看磁盘使用情况
  * **du sh**:查看文件夹大小

## 二、软件相关
  * **dpkg --list | grep mysql**: 查看安装的软件
  * **sudo apt-get --purge remove <programname>**: 卸载软件和配置
  * **sudo apt-get remove <programname>**: 只卸载软件保留配置文件
  * **apt-get -f install**: 安装依赖











































## 注1: 搜狗输入法的安装:
  * **sudo apt-get install fcitx-bin**
  * **sudo dpkg -i 搜狗安装包的文件名**
  * **sudo apt --fix-broken install**
  * **安装成功过后，进入设置 根据红色箭头进入语言安装界面，安装语言（会自动安装中文语言）**
  * **注销或者重启**
  * **Configure Current Input Method 调出搜狗，并置顶**