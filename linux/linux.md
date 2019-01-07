## 一、common
  * 1. **apachectl -v**: 查看Apache的version
  *	2. **history |grep xxx**: 查看历史命令:
  * 3. **cp -r 目录 目录**
  * 4. **mv -i/f 目录 目录**
  * 5. **sudo service apache2 restart**: 重启服务
  * 6. **uname -a**: 查看Linux的内核
  * 7. **lsb_release -a**: 查看linux系统版本
  * 8. **lsof -i :80**: 查看端口的使用
  * 9. **ls | wc -w**: 查看文件个数
  * 10. **df -h**:查看磁盘使用情况
  * 11. **du sh**:查看文件夹大小

## 二、软件相关
  * 1. **dpkg --list | grep mysql**: 查看安装的软件
  * 2. **sudo apt-get --purge remove <programname>**: 卸载软件和配置
  * 3. **sudo apt-get remove <programname>**: 只卸载软件保留配置文件
  * 4. **apt-get -f install**: 安装依赖











































## 注1: 搜狗输入法的安装:
  * 1. **sudo apt-get install fcitx-bin**
  * 2. **sudo dpkg -i 搜狗安装包的文件名**
  * 3. **sudo apt --fix-broken install**
  * 4. **安装成功过后，进入设置 根据红色箭头进入语言安装界面，安装语言（会自动安装中文语言）**
  * 5. **注销或者重启**
  * 6. **Configure Current Input Method 调出搜狗，并置顶**
