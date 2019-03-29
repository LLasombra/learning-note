# webpack 快速入门

## 1. 目标

    1). 理解项目的模块化打包
    2). 学会webpack的基本使用
    3). 理解webpack的4个核心概念

## 2. 初始化项目

    创建空应用: demo1
    npm init -y

## 3. 下载 webpack

    npm install webpack -g   //全局下载webpack
    npm install webpack-cli -g //全局下载webpack-cli
    npm install webpack --save-dev  //下载webpack为开发依赖
    npm install webpack-cli --save-dev  //下载webpack-cli为开发依赖

## 4. 编码

    1. bar.js
      export default function bar() {
        console.log('bar()')
      }
    2. app.js
      import bar from './bar'
      bar()
      document.getElementById('app').innerHTML = 'Hello, webpack'
    3. page.html
      <html>
        <head>
          <title>Hello webpack</title>
        </head>
        <body>
          <div id="app"></div>
          <script src="bundle.js"></script>
        </body>
      </html>

## 5. 使用 webpack 打包项目

    1. webpack配置: webpack.config.js
      module.exports = {
        entry: './app.js',
        output: {
          filename: 'bundle.js'
        }
      }
    2. 编译打包
      webpack
    3. 浏览器打开page.html, 查看运行效果

## 6. 常见问题

    1. 'webpack-dev-server' 不是内部或外部命令,也不是可运行的程序
        npm uninstall webpack-dev-server
        npm install weback-dev-server@2.9.1
        在 package.json 中查看版本
