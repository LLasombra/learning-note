**Table of Contents**

- [npm](#npm)
  - [install npm](#install-npm)
  - [common instructions](#common-instructions)
  - [generate content](#generate-content)
    - [说明](#%E8%AF%B4%E6%98%8E)
  - [常见的 npm 指令](#%E5%B8%B8%E8%A7%81%E7%9A%84-npm-%E6%8C%87%E4%BB%A4)
  - [npm 找 modules 的规则](#npm-%E6%89%BE-modules-%E7%9A%84%E8%A7%84%E5%88%99)

# npm

## install npm

```shell
# 全局下载webpack
npm install webpack@1.0.0 -g
# 下载webpack为开发依赖
npm install webpack --save-dev
```

## common instructions

```shell
# 安装到项目下的 node_modules; 不写入节点; npm install 初始化项目时不会下载模块.
npm install moduleName
# 卸载安装
npm uninstall moduleName
# 安装到 global 下的 node_modules; 不写入节点; npm install 初始化项目时不会下载模块.
npm install -g moduleName
# 安装到项目下的 node_modules; 只写入 dependencies[生产时需要用的]  节点; npm install --production 时才会下载模块.
npm install -save moduleName
# 安装到项目下的 node_modules; 只写入 devDependencies[开发时需要用的]  节点; npm install --production 时才不会下载模块.
npm install -save-dev moduleName
# bulid
npm run build
```

## generate content

```shell
# 安装依赖
cnpm install doctoc -g
# 切换到文件目录
doctoc DEMO.md
```

### 说明

- 这里只能生成 # 的目录, 因此多使用 # 来进行分层

## 常见的 npm 指令

![avatar](https://img-blog.csdn.net/2018050817305865)

```shell
# 查看 npm 服务器上的最新版本
npm view MODULE_NAME version
npm view MODULE_NAME versions
# 查看本地 module 版本
npm ls MODULE_NAME
# 查看本地全局安装的 module 版本
npm ls MODULE_NAME -g
```

## npm 找 modules 的规则

### 无 package.json 文件:

    1) 开始在程序文件同一目录下查找
    2) 判断是否是核心模块（http，URL，fs ，querystring，Path等)
    3) 当前目录下的node_modules目录下查找
    4) 尝试进入父目录
    5) 父目录存在，则重复3步骤，父目录不存在，尝试在由环境变量NODE_PATH指定的目录下查找

### 有 package.json 文件:
