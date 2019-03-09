## 切换NPM源(淘宝源)

标签（空格分隔）： 问题总结

---


```
临时使用淘宝源
npm --registry https://registry.npm.taobao.org install node-red-contrib-composer@latest
```
```
全局配置切换到淘宝源
 npm config set registry https://registry.npm.taobao.org
```
```
全局配置切换到官方源
 npm config set registry http://www.npmjs.org
```
```
检测是否切换到了淘宝源
npm info underscore
输出
.......

gitHead: 'e4743ab712b8ab42ad4ccb48b155034d02394e4d',
  dist:
   { shasum: '4f3fb53b106e6097fcf9cb4109f2a5e9bdfa5022',
     size: 34172,
     noattachment: false,
    //　有　registry.npm.taobao.org　等字样　　说明切换成功
     tarball: 'http://registry.npm.taobao.org/underscore/download/underscore-1.8.3.tgz' },
  directories: {},
  publish_time: 1427988774520 }
```

## 使用npm管理包

标签（空格分隔）： 技术总结

---

```shell
#1. Go to directory of your project

mkdir TestProject
cd TestProject

#2. Make this directory a root of your project (this will create a default package.json file)

npm init --yes

#3. Install required npm module and save it as a project dependency (it will appear in package.json)

npm install request --save

#4. Create a test.js file in project directory with code from package example

var request = require('request');
request('http://www.google.com', function (error, response, body) {
  if (!error && response.statusCode == 200) {
    console.log(body); // Print the google web page.
  }
});

#5. Your project directory should look like this

TestProject/
- node_modules/
- package.json
- test.js

#6. Now just run node inside your project directory

node test.js

```
