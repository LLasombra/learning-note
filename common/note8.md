# 使用npm管理包

标签（空格分隔）： 技术总结

---

```
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




