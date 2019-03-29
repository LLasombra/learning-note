## nodejs environment install

### 安装

- 傻瓜式安装

  - Node.js : http://nodejs.cn/
  - 查看安装成功:

    ```shell
    npm --version #出现安装信息
    ```

### 配置环境变量

- 1. 指定安装全局安装的 modules 地址
  ```shell
  npm config set prefix "install path\node_global"
  npm config set cache "install path\node_cache"
  ```
- 2. 添加系统环境变量 NODE_PATH: **install path\node_global\node_modules**, 将安装的全局 modules 指定位置

### 安装 cnpm

```shell
npm install -g cnpm --registry=https://registry.npm.taobao.org
# 查看
npm config list
# 将 cnmp 添加到环境变量:  **install path\node_global\node_modules**
```

### 卸载安装的 modules

```shell
npm uninstall modules_name
```
