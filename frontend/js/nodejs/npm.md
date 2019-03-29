## npm

### install npm

```shell
# 全局下载webpack
npm install webpack -g
# 下载webpack为开发依赖
npm install webpack --save-dev
```

### common instructions

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
