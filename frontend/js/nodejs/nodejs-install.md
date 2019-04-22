**Table of Contents**

- [nodejs environment install](#nodejs-environment-install)
  - [安装](#%E5%AE%89%E8%A3%85)
  - [配置环境变量](#%E9%85%8D%E7%BD%AE%E7%8E%AF%E5%A2%83%E5%8F%98%E9%87%8F)
  - [安装 cnpm](#%E5%AE%89%E8%A3%85-cnpm)
  - [卸载安装的 modules](#%E5%8D%B8%E8%BD%BD%E5%AE%89%E8%A3%85%E7%9A%84-modules)

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
- 2. 添加系统环境变量 NODE_PATH: **INSTALL_PATH\node_global\node_modules**, 将安装的全局 modules 指定位置
- 3. 将 node_global 路径添加到 Path 中.

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
