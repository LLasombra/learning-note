- [ECMAScript core knowledge](#ecmascript-core-knowledge)
  - [相关简介](#%E7%9B%B8%E5%85%B3%E7%AE%80%E4%BB%8B)
    - [1. Babel 转码器](#1-babel-%E8%BD%AC%E7%A0%81%E5%99%A8)
    - [2. Traceur 转码器](#2-traceur-%E8%BD%AC%E7%A0%81%E5%99%A8)
  - [语法](#%E8%AF%AD%E6%B3%95)
    - [1. 词法结构](#1-%E8%AF%8D%E6%B3%95%E7%BB%93%E6%9E%84)
      - [a. 大小写敏感](#a-%E5%A4%A7%E5%B0%8F%E5%86%99%E6%95%8F%E6%84%9F)
      - [b. 标识符](#b-%E6%A0%87%E8%AF%86%E7%AC%A6)
      - [c. 关键字](#c-%E5%85%B3%E9%94%AE%E5%AD%97)
      - [d. 保留字](#d-%E4%BF%9D%E7%95%99%E5%AD%97)
    - [2. 类型、值、变量](#2-%E7%B1%BB%E5%9E%8B%E5%80%BC%E5%8F%98%E9%87%8F)
      - [a. 类型分类](#a-%E7%B1%BB%E5%9E%8B%E5%88%86%E7%B1%BB)
        - [1. 按数据类型分类](#1-%E6%8C%89%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B%E5%88%86%E7%B1%BB)
        - [2. 按可变分类](#2-%E6%8C%89%E5%8F%AF%E5%8F%98%E5%88%86%E7%B1%BB)
      - [b. 有 GC 机制](#b-%E6%9C%89-gc-%E6%9C%BA%E5%88%B6)
      - [c. 相关的类](#c-%E7%9B%B8%E5%85%B3%E7%9A%84%E7%B1%BB)
        - [1. Math](#1-math)
        - [2. Date](#2-date)
        - [3. string](#3-string)
        - [4. false](#4-false)
        - [5. 全局对象](#5-%E5%85%A8%E5%B1%80%E5%AF%B9%E8%B1%A1)
        - [6. 拆箱与装箱问题](#6-%E6%8B%86%E7%AE%B1%E4%B8%8E%E8%A3%85%E7%AE%B1%E9%97%AE%E9%A2%98)
        - [7. 不可变的原始值和可变的对象引用](#7-%E4%B8%8D%E5%8F%AF%E5%8F%98%E7%9A%84%E5%8E%9F%E5%A7%8B%E5%80%BC%E5%92%8C%E5%8F%AF%E5%8F%98%E7%9A%84%E5%AF%B9%E8%B1%A1%E5%BC%95%E7%94%A8)
        - [8. 类型转换](#8-%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2)
    - [3. 表达式、运算符](#3-%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%BF%90%E7%AE%97%E7%AC%A6)
      - [a. var 与 let 与 const](#a-var-%E4%B8%8E-let-%E4%B8%8E-const)
        - [ES6 声明变量的六种方法](#es6-%E5%A3%B0%E6%98%8E%E5%8F%98%E9%87%8F%E7%9A%84%E5%85%AD%E7%A7%8D%E6%96%B9%E6%B3%95)
        - [var](#var)
        - [let](#let)
        - [const](#const)
      - [b. 全局对象](#b-%E5%85%A8%E5%B1%80%E5%AF%B9%E8%B1%A1)
        - [顶层对象属性](#%E9%A1%B6%E5%B1%82%E5%AF%B9%E8%B1%A1%E5%B1%9E%E6%80%A7)
        - [global 对象](#global-%E5%AF%B9%E8%B1%A1)
          - [在所有情况下, 都取到顶层对象](#%E5%9C%A8%E6%89%80%E6%9C%89%E6%83%85%E5%86%B5%E4%B8%8B-%E9%83%BD%E5%8F%96%E5%88%B0%E9%A1%B6%E5%B1%82%E5%AF%B9%E8%B1%A1)
      - [c. 表达式](#c-%E8%A1%A8%E8%BE%BE%E5%BC%8F)
        - [1. 算术表达式](#1-%E7%AE%97%E6%9C%AF%E8%A1%A8%E8%BE%BE%E5%BC%8F)
        - [2. 关系表达式](#2-%E5%85%B3%E7%B3%BB%E8%A1%A8%E8%BE%BE%E5%BC%8F)
        - [3. 逻辑表达式](#3-%E9%80%BB%E8%BE%91%E8%A1%A8%E8%BE%BE%E5%BC%8F)
        - [4. 赋值表达式](#4-%E8%B5%8B%E5%80%BC%E8%A1%A8%E8%BE%BE%E5%BC%8F)
        - [5. 表达式计算[rarely]](#5-%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%AE%A1%E7%AE%97rarely)
        - [6. 其他表达式](#6-%E5%85%B6%E4%BB%96%E8%A1%A8%E8%BE%BE%E5%BC%8F)
      - [d. 变量的解构赋值](#d-%E5%8F%98%E9%87%8F%E7%9A%84%E8%A7%A3%E6%9E%84%E8%B5%8B%E5%80%BC)
        - [规则](#%E8%A7%84%E5%88%99)
        - [1. 数组的解构赋值](#1-%E6%95%B0%E7%BB%84%E7%9A%84%E8%A7%A3%E6%9E%84%E8%B5%8B%E5%80%BC)
        - [2. 对象的解构赋值](#2-%E5%AF%B9%E8%B1%A1%E7%9A%84%E8%A7%A3%E6%9E%84%E8%B5%8B%E5%80%BC)
        - [3. 字符串的解构赋值](#3-%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%9A%84%E8%A7%A3%E6%9E%84%E8%B5%8B%E5%80%BC)
        - [4. 数值和布尔值的解构赋值](#4-%E6%95%B0%E5%80%BC%E5%92%8C%E5%B8%83%E5%B0%94%E5%80%BC%E7%9A%84%E8%A7%A3%E6%9E%84%E8%B5%8B%E5%80%BC)
        - [5. 函数参数的解构赋值](#5-%E5%87%BD%E6%95%B0%E5%8F%82%E6%95%B0%E7%9A%84%E8%A7%A3%E6%9E%84%E8%B5%8B%E5%80%BC)
        - [6. 圆括号问题](#6-%E5%9C%86%E6%8B%AC%E5%8F%B7%E9%97%AE%E9%A2%98)
        - [7.用途](#7%E7%94%A8%E9%80%94)
          - [a. 交换变量的值](#a-%E4%BA%A4%E6%8D%A2%E5%8F%98%E9%87%8F%E7%9A%84%E5%80%BC)
          - [b. 从函数返回多个值](#b-%E4%BB%8E%E5%87%BD%E6%95%B0%E8%BF%94%E5%9B%9E%E5%A4%9A%E4%B8%AA%E5%80%BC)
          - [c. 函数参数的定义](#c-%E5%87%BD%E6%95%B0%E5%8F%82%E6%95%B0%E7%9A%84%E5%AE%9A%E4%B9%89)
          - [d. 提取 JSON 数据](#d-%E6%8F%90%E5%8F%96-json-%E6%95%B0%E6%8D%AE)
          - [e. 函数参数的默认值](#e-%E5%87%BD%E6%95%B0%E5%8F%82%E6%95%B0%E7%9A%84%E9%BB%98%E8%AE%A4%E5%80%BC)
          - [f. 遍历 Map 结构](#f-%E9%81%8D%E5%8E%86-map-%E7%BB%93%E6%9E%84)
          - [g. 输入模块的指定方法](#g-%E8%BE%93%E5%85%A5%E6%A8%A1%E5%9D%97%E7%9A%84%E6%8C%87%E5%AE%9A%E6%96%B9%E6%B3%95)
    - [4. 语句](#4-%E8%AF%AD%E5%8F%A5)
    - [5. 对象](#5-%E5%AF%B9%E8%B1%A1)
    - [6. 数组](#6-%E6%95%B0%E7%BB%84)
    - [7. 函数](#7-%E5%87%BD%E6%95%B0)
    - [8. 类、模板](#8-%E7%B1%BB%E6%A8%A1%E6%9D%BF)
    - [9. 正则 Reg](#9-%E6%AD%A3%E5%88%99-reg)
    - [10. JavaScript 子集与扩展](#10-javascript-%E5%AD%90%E9%9B%86%E4%B8%8E%E6%89%A9%E5%B1%95)
    - [11. nodejs](#11-nodejs)
  - [客户端](#%E5%AE%A2%E6%88%B7%E7%AB%AF)
    - [1. Web 中的 JavaScript](#1-web-%E4%B8%AD%E7%9A%84-javascript)
    - [2. Window 对象(default)](#2-window-%E5%AF%B9%E8%B1%A1default)
    - [3. 脚本化文档](#3-%E8%84%9A%E6%9C%AC%E5%8C%96%E6%96%87%E6%A1%A3)
    - [4. 脚本化 CSS](#4-%E8%84%9A%E6%9C%AC%E5%8C%96-css)
    - [5. 事件处理(8)](#5-%E4%BA%8B%E4%BB%B6%E5%A4%84%E7%90%868)
    - [6. 脚本化 HTTP](#6-%E8%84%9A%E6%9C%AC%E5%8C%96-http)
    - [7. JQuery 库操纵文档的内容、样式、行为](#7-jquery-%E5%BA%93%E6%93%8D%E7%BA%B5%E6%96%87%E6%A1%A3%E7%9A%84%E5%86%85%E5%AE%B9%E6%A0%B7%E5%BC%8F%E8%A1%8C%E4%B8%BA)
    - [8. 客户端 Cache](#8-%E5%AE%A2%E6%88%B7%E7%AB%AF-cache)
    - [9. 多媒体与图形编程](#9-%E5%A4%9A%E5%AA%92%E4%BD%93%E4%B8%8E%E5%9B%BE%E5%BD%A2%E7%BC%96%E7%A8%8B)
    - [10. HTML5](#10-html5)
  - [summary](#summary)
    - [1.查看变量值](#1%E6%9F%A5%E7%9C%8B%E5%8F%98%E9%87%8F%E5%80%BC)
    - [2. 对象 map](#2-%E5%AF%B9%E8%B1%A1-map)
    - [3. 数组](#3-%E6%95%B0%E7%BB%84)
    - [4.对象数组与对象数组](#4%E5%AF%B9%E8%B1%A1%E6%95%B0%E7%BB%84%E4%B8%8E%E5%AF%B9%E8%B1%A1%E6%95%B0%E7%BB%84)
    - [5. 优先级](#5-%E4%BC%98%E5%85%88%E7%BA%A7)
    - [6. math](#6-math)
    - [7. 事件处理函数：](#7-%E4%BA%8B%E4%BB%B6%E5%A4%84%E7%90%86%E5%87%BD%E6%95%B0)
    - [8. front 文件加载顺序](#8-front-%E6%96%87%E4%BB%B6%E5%8A%A0%E8%BD%BD%E9%A1%BA%E5%BA%8F)
    - [9. 两个单独的对象一定不相等, 只有引用同一对象才想等.](#9-%E4%B8%A4%E4%B8%AA%E5%8D%95%E7%8B%AC%E7%9A%84%E5%AF%B9%E8%B1%A1%E4%B8%80%E5%AE%9A%E4%B8%8D%E7%9B%B8%E7%AD%89-%E5%8F%AA%E6%9C%89%E5%BC%95%E7%94%A8%E5%90%8C%E4%B8%80%E5%AF%B9%E8%B1%A1%E6%89%8D%E6%83%B3%E7%AD%89)
    - [10. 基本类型转换](#10-%E5%9F%BA%E6%9C%AC%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2)
    - [11. in、instanceof、isprototypeof、typeof 区别](#11-ininstanceofisprototypeoftypeof-%E5%8C%BA%E5%88%AB)
    - [12. undefined 情景](#12-undefined-%E6%83%85%E6%99%AF)
    - [13. 枚举](#13-%E6%9E%9A%E4%B8%BE)
    - [14. Object.prototype 的属性](#14-objectprototype-%E7%9A%84%E5%B1%9E%E6%80%A7)
    - [15. 可扩展性](#15-%E5%8F%AF%E6%89%A9%E5%B1%95%E6%80%A7)
    - [16. delete](#16-delete)
    - [17. 类](#17-%E7%B1%BB)
    - [18. codingstyle](#18-codingstyle)
    - [19. 函数声明语句 与 函数表达式](#19-%E5%87%BD%E6%95%B0%E5%A3%B0%E6%98%8E%E8%AF%AD%E5%8F%A5-%E4%B8%8E-%E5%87%BD%E6%95%B0%E8%A1%A8%E8%BE%BE%E5%BC%8F)

# ECMAScript core knowledge

## 相关简介

### 1. Babel 转码器

Babel 是一个广泛使用的 ES6 转码器, 可以将 ES6 代码转为 ES5 代码, 从而在现有环境执行. 这意味着, 你可以用 ES6 的方式编写程序, 又不用担心现有环境是否支持. 下面是一个例子

```js
// 转码前
input.map(item => item + 1);

// 转码后
input.map(function(item) {
  return item + 1;
});
```

```shell
cnpm install --save-dev @babel/core
# 使用时需要配置在根目录下的 .babelrc 文件
# 最新转码规则
$ npm install --save-dev @babel/preset-env
# react 转码规则
$ npm install --save-dev @babel/preset-react
{
  "presets": [
    "@babel/env",
    "@babel/preset-react"
  ],
  "plugins": []
}
```

### 2. Traceur 转码器

Google 公司的 Traceur 转码器, 也可以将 ES6 代码转为 ES5 代码

## 语法

### 1. 词法结构

#### a. 大小写敏感

- JavaScript 区分大小写, (X)HTML 不区分大小写

#### b. 标识符

- 字母、\_、\$开始

#### c. 关键字

> break、delete、function、return、typeof、case、
> Switch、else、in、this、void、continue、false、instance
> Throw、while、debugger、finally、new、true、with、for
> default、null、try、do、if
> class、const、enum、export、extends、Import、super
> arguments、eval

#### d. 保留字

> abstract、double、goto、native、static
> Boolean、enum、 implement、Package、super
> byte、export、import、private、synchronized
> char、extends、int、protected、throws 、class
> final、interface、public、 trainsient
> const、float、long、short、volatile

> arguments、encodeURI、infinity、Number
> RegExp、Array、encodeURIComponent、isFinite
> object、 String、Boolean、Error、isNaN
> parseInt、SyntaxError、Date、eval、JSon
> parseFloat、TypeError、decodeURI、EvalError
> MAth、rangeError、undefined、 decodeURIComponent
> Function、NaN、ReferenceError、URIError

### 2. 类型、值、变量

#### a. 类型分类

##### 1. 按数据类型分类

- 原始类型：数字、字符串、布尔值、原始值[undefined{未声明或声明后未赋值}[少用]、null]

- 对象[属性的集合]类型：array、function etc.

##### 2. 按可变分类

- 可变类型：[对象(名值对)object、array、function.....]

-不可变类型：[null、undefined、number、bool、string]

#### b. 有 GC 机制

#### c. 相关的类

> Math、Date、string、array、function、date、regExp、Error

##### 1. Math

```js
Math.round(0.6);      //1,四舍五入
Math.ceil(0.6);      //1,向上取整
Math.floor(0.6);      //1,向下取整
Math.pow(2,53);      //二的五十三次方
Math.exp(3);         //E的三次方
Math.max/min(x,y,z);      //取大小
Math.log(512)/Math.ln2;

Infinity：溢出(overflow)
-Infinity：下溢(underflow)
```

##### 2. Date

```js
var now = new Date();
now.getFullYear();
now.getMonth(); //从0开始
now.getDate(); //从1开始, 表示几号
now.getDay(); //星期几
now.getHours();
Now.getUTCHours();
now.getMinutes();
now.getSeconds();
```

##### 3. string

![avatar](https://img-blog.csdnimg.cn/20190413200601854.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NzA0MzY0,size_16,color_FFFFFF,t_70)

```js
var s = 'hello,world'; //s[0];   ===>'h'
s.charAt(s.length - 1); //d
s.substring(1, 4); //ell
s.slice(1, 4); //ell
s.slice(-3); //rld
s.indexOf(''); //2,首次出现l的位置
s.indexOf('', 3); //3,在3之后出现的l的位置
s.lastIndexOf(''); //最后出现l的位置
s.split(','); //['hello','world']
s.repalce('h', 'H');
s.toUpperCase();
s.trim(); //去首尾的space
s.join();
```

##### 4. false

> undefined、null、0、 -1 、NaN、''

##### 5. 全局对象

    全局对象的属性是全局定义的符号, JavaScript可以直接使用, JavaScript解释器会创建一个新的全局变量, 并给它一组定义的初始属性
    全局属性：NaN、undefined ···
    全局函数：eval()、isFinite()、isNaN() ···
    全局对象：Math、json ···
    构造函数：Date()

##### 6. 拆箱与装箱问题

> 由于字符串、数字和布尔值的属性都是只读模式的, 并且不能给他们定义新的属性

```js
var s = 'test';
// 由于 s 不是对象
s.len = s.length; //临时变量
var t = s.len; //undefined

// 名值对的集合
var book = {
  topic: 'javascript',
  fat: true
};
//对象可以添加属性
book.author = '练顺';
book.topic; // 'javascript' 等价于 book['topic']

var o = { x: 1 }; //数组也可以
o.x = 2;
o.y = 3; //OK
```

##### 7. 不可变的原始值和可变的对象引用

```js
// 不可变的原始值：undefined、null、string、number、boolean
// 两个单独的对象一定不相等, 只有引用同一对象才想等.
var o = { x: 1 },
var p = { x: 1 };
o == p; //false
```

##### 8. 类型转换

    显示转换: 调用 Boolean()、Number()、String()、Object()......
    .toString()  // 大家都有, 除了null、undefined
    x + ""       // 等价于 String(x)
    +x           // Number(x)   <====>  x - 0
    !!x          // Boolean(x)
    ==,+         // 会转换为 String 后运算
    // '1' == 1  // true
    // '1' + 1   // '11'
    -、<、>      // 会转换为 Number 后运算

![avatar](https://img-blog.csdnimg.cn/20190413202039356.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NzA0MzY0,size_16,color_FFFFFF,t_70)

```js
// Number2string
var a = 12.56;
a.toFixed(1); // '12.5' 指定小数点后位数  Number2string
a.toExpontial(5); // '1.25300e+1' 使用指数计数法, 小数前只有一位  Number2string
a.topresicion(5); // '12.530' 有效数位  Number2string
parseInt('0.1'); //0
parseInt('.1'); //NaN
parseFloat('0.1'); //0.1
parseFloat('.1'); //0.1
parseFloat('$2'); //NaN
```

### 3. 表达式、运算符

#### a. var 与 let 与 const

##### ES6 声明变量的六种方法

> var
> function
> let
> const
> import
> class

##### var

```js
// C系列语言中, 定义与声明的区别：定义：int =0；声明：int a;
var a = [1, , , , , , , 6]; //之间会自动填充undefined
var a = { x: 1, y: { z: 3 } }; // a.x = a['x']
var a = [0, 1, 2, 3];
a[1] = a['1'] = 0;
```

##### let

- 基本用法: 类似于 var, 但是所声明的变量, 只在 let 命令所在的代码块内有效

  ```js
  {
    let a = 10;
    var b = 1;
  }
  a; // ReferenceError: a is not defined.
  b; // 1
  ```

- for 循环的计数器, 就很合适使用 let 命令

- 不存在变量提升: 不存在 [变量可以在声明之前使用, 值为 undefined]

  ```js
  // var 的情况
  console.log(foo); // 输出undefined
  var foo = 2;

  // let 的情况
  console.log(bar); // 报错ReferenceError
  let bar = 2;
  ```

- 暂时性死区: 在代码块内, 使用 let 命令声明变量之前, 该变量都是不可用的(无论在外面是否定义)

  ```js
  var tmp = 12;
  if (true) {
    // TDZ开始
    tmp = 'abc'; // ReferenceError
    console.log(tmp); // ReferenceError
    typeof tmp; // ReferenceError
    typeof zack; //undefined

    let tmp; // TDZ结束
    console.log(tmp); // undefined

    tmp = 123;
    console.log(tmp); // 123
  }
  ```

  ```js
  function bar(x = y, y = 2) {
    return [x, y];
  }
  // 因为参数x默认值等于另一个参数y, 而此时y还没有声明, 属于'死区'
  bar(); // 报错
  ```

- 不允许重复声明
  let 不允许在相同作用域内, 重复声明同一个变量

- let 实际上为 JavaScript 新增了块级作用域

  ```js
  function f1() {
    let n = 5;
    if (true) {
      let n = 10;
    }
    console.log(n); // 5
  }
  ```

- 块级作用域与函数声明[不要使用]
  ES6 引入了块级作用域, 明确允许在块级作用域之中声明函数. ES6 规定, 块级作用域之中, 函数声明语句的行为类似于 let, 在块级作用域之外不可引用.
  考虑到环境导致的行为差异太大, 应该避免在块级作用域内声明函数. 如果确实需要, 也应该写成函数表达式, 而不是函数声明语句

  ```js
  function f() {
    console.log('I am outside!');
  }

  (function() {
    //等价于 在这里定义f
    // var f = undefined;

    if (false) {
      // 重复声明一次函数f
      function f() {
        console.log('I am inside!');
      }
    }

    f();
  })();
  ```

  ```js
  // 函数声明语句
  {
    let a = 'secret';
    function f() {
      return a;
    }
  }

  // 函数表达式
  {
    let a = 'secret';
    let f = function() {
      return a;
    };
  }
  ```

##### const

- const 声明一个只读的常量. 一旦声明, 常量的值就不能改变, 需要即刻赋值
- 只在声明所在的块级作用域内有效
- const 也与 let 一样 命令声明的常量也是不提升, 同样存在暂时性死区, 只能在声明的位置后面使用
- const 声明的常量, 也与 let 一样不可重复声明
- 本质
  - 变量指向的那个内存地址所保存的数据[基本类型的值、对象指向实际数据的指针{指针指向的数据结构是不是可变的, 就完全不能控制了}]不得改动
  - 改变指针指向的数据结构的值:
  ```js
  //常量foo指向一个冻结的对象, 所以添加新属性不起作用, 严格模式时还会报错
  const foo = Object.freeze({});
  // 除了将对象本身冻结, 对象的属性也应该冻结. 下面是一个将对象彻底冻结的函数
  var constantize = obj => {
    Object.freeze(obj);
    Object.keys(obj).forEach((key, i) => {
      if (typeof obj[key] === 'object') {
        constantize(obj[key]);
      }
    });
  };
  ```

#### b. 全局对象

##### 顶层对象属性

- 顶层对象, 在浏览器环境指的是 window 对象, 在 Node 指的是 global 对象. ES5 之中, 顶层对象的属性与全局变量是等价的. 从 ES6 开始, 全局变量将逐步与顶层对象的属性脱钩.

  ```js
  var a = 1;
  // 如果在 Node 的 REPL 环境, 可以写成 global.a
  // 或者采用通用方法, 写成 this.a
  window.a; // 1

  let b = 1;
  window.b;
  ```

##### global 对象

###### 在所有情况下, 都取到顶层对象

```js
// 方法一
typeof window !== 'undefined'
  ? window
  : typeof process === 'object' &&
    typeof require === 'function' &&
    typeof global === 'object'
  ? global
  : this;

// 方法二
var getGlobal = function() {
  if (typeof self !== 'undefined') {
    return self;
  }
  if (typeof window !== 'undefined') {
    return window;
  }
  if (typeof global !== 'undefined') {
    return global;
  }
  throw new Error('unable to locate global object');
};
```

#### c. 表达式

##### 1. 算术表达式

- 种类
  `+、-、++、--、&、~、<<、>>、>>>(无符号的有右移)`
- `+` 如果其中一个是对象, 则要通过 tostring()/valueof()转换为原始值；原始值若是字符转, 则另一个值也要转换为字符转. 否则都转换为数字或 NaN 进行运算
  ```js
  1 + {}; //"1[object Object]"
  2 + null; //2
  2 + undefined; //NaN
  ```
- 一元运算符(+ - 、++、--)：作用于一个单独的操作数, 并产生一个新值(优先级很高)
- 位运算符(&、|、^、~、<<、>>、>>>(无符号的有右移,只是左边最高位要补零)
- `==、+ 转换为 string 后运算; -、<、> 转换为 Number 后运算`

##### 2. 关系表达式

- 种类
  `==、===、in、instanceof`
- `==` 相等运算符|转换后比较
- `===` 恒等运算符|不转换比较
  ```js
  1 + {}; //"1[object Object]"
  2 + null; //2
  2 + undefined; //NaN
  ```
- 一元运算符(+ - 、++、--)：作用于一个单独的操作数, 并产生一个新值(优先级很高)
- 位运算符(&、|、^、~、<<、>>、>>>(无符号的有右移,只是左边最高位要补零)

##### 3. 逻辑表达式

- 种类: &&、||、！

##### 4. 赋值表达式

- 种类: ?:、typeof、delete [delete：只能删除自有的可配置属性, 不能删除继承属性]、void、,
- ?:
  ```js
  x > 0 ? x : -x; //|x|
  ```
- typeof
  |x | typeof x|
  |:----:|:----:|
  |undefined |'undefined'|
  |null |'obect'|
  |true/false |'boolean'|
  |number/NaN |'number'|
  |string |'string'|
  |function |'function'|
  |非函数内置对象 |'object'|
  |object |'string'|

##### 5. 表达式计算[rarely]

- eval()直接调用时, 它使用了调用它的变量作用域环境(很少使用)
- 直接调用 eval()时, 它总是在调用它的上下文作用域内执行
  其他间接调用(别名)时, 则使用全局对象作为上下文作用域、且无法读写 定义局部变量和函数
- 在严格模式下或以'use strict'指令开始时, 这里的 eval()是上下文环境中的局 部 eval()【在严格模式下, eval 执行的代码段卡可以查询或更改局部变量, 但 不能定义局部变量和函数】(可改不可创)
- 在非严格模式下或以默认时, eval 执行的代码段卡可以查询或更改局部变 量, 也可以定义局部变量和函数(可改也可创)

```js
// 如果传入的参数不是字符串, 则返回原值
eval('function(){return x+1;}');

eval('x'); //返回局部变量的值
eval('x=2'); //改变局部变量的值
eval('var y=3'); //声明新的局部变量
```

##### 6. 其他表达式

#### d. 变量的解构赋值

##### 规则

    a. 解构赋值的规则是, 只要等号右边的值不是对象或数组, 就先将其转为对象. 由于 undefined 和 null 无法转为对象, 所以对它们进行解构赋值, 都会报错

##### 1. 数组的解构赋值

```js
// 1. 本质上, 这种写法属于'模式匹配'
let [a, b, c] = [1, 2, 3];

// 2. 带有 ...
let [head, ...tail] = [1, 2, 3, 4];
head; // 1
tail; // [2, 3, 4]

let [x, y, ...z] = ['a'];
x; // "a"
y; // undefined
z; // []

// 3. 复合的结垢赋值
let [a, [b], d] = [1, [2, 3], 4];
a; // 1
b; // 2
d; // 4

// 4. 报错 等号的右边不是数组或者严格地说, 不是可遍历的结构
let [foo] = 1;
let [foo] = false;
let [foo] = NaN;
let [foo] = undefined;
let [foo] = null;
let [foo] = {};

// 5. 对于 Set 结构, 也可以使用数组的解构赋值
let [x, y, z] = new Set(['a', 'b', 'c']);
```

```js
// 1. 默认值: 只有当一个数组成员严格等于undefined, 默认值才会生效
let [foo = true] = [];
foo; // true

let [x, y = 'b'] = ['a']; // x='a', y='b'
let [x, y = 'b'] = ['a', undefined]; // x='a', y='b'

// 2. 只有当一个数组成员严格等于undefined, 默认值才会生效
let [x = 1] = [undefined];
x; // 1
let [x = 1] = [null];
x; // null

// 3. 默认值是一个表达式, 那么这个表达式是惰性求值的, 即只有在用到的时候, 才会求值
function f() {
  console.log('aaa');
}
let [x = f()] = [1]; // x=1

// 4. 默认值可以引用解构赋值的其他变量, 但该变量必须已经声明
let [x = 1, y = x] = []; // x=1; y=1
let [x = 1, y = x] = [2]; // x=2; y=2
let [x = 1, y = x] = [1, 2]; // x=1; y=2
let [x = y, y = 1] = []; // ReferenceError: y is not defined
```

##### 2. 对象的解构赋值

```js
// 1. 对象的属性没有次序, 变量必须与属性同名, 才能取到正确的值
let { bar, foo } = { foo: 'aaa', bar: 'bbb' }; // 本质 let { foo: foo, bar: bar } = { foo: 'aaa', bar: 'bbb' };
foo; // "aaa"
bar; // "bbb"

// 2. 如果解构失败, 变量的值等于undefined
let { baz } = { foo: 'aaa', bar: 'bbb' };
baz; // undefined

// 3. 对象的解构赋值, 可以很方便地将现有对象的方法, 赋值到某个变量
let { log, sin, cos } = Math;
const { log } = console;
log('hello'); // hello

let obj = { first: 'hello', last: 'world' };
let { first: f, last: l } = obj;
obj.first === f; // 'hello'
first; // error

// 报错 4. 如果解构模式是嵌套的对象, 而且子对象所在的父属性不存在, 那么将会报错.
//上面代码中, 等号左边对象的foo属性, 对应一个子对象. 该子对象的bar属性, 解构时会报错. 原因很简单, 因为foo这时等于undefined, 再取子属性就会报错.
let {
  foo: { bar }
} = { baz: 'baz' };

// 5.注意, 对象的解构赋值可以取到继承的属性.
const obj1 = {};
const obj2 = { foo: 'bar' };
Object.setPrototypeOf(obj1, obj2);
const { foo } = obj1;
foo; // "bar"
```

```js
// 1. 默认值
var { x, y = 5 } = { x: 1 }; // x = 1, y = 5
var { x: y = 3 } = {}; // y = 3
var { sa } = { sa: 5 }; // x = 5
var { sa: sa } = { sa: 5 }; // x = 5
// 这里 saa 是 undefined sw 为 5
var { saa: sw = 2 } = { saa: 5 }; // x = 5
var { x: y = 3 } = { x: 5 }; // y = 5

// 2. 默认值生效的条件是, 对象的属性值严格等于undefined.
var { x = 3 } = { x: undefined }; // x = 3
var { x = 3 } = { x: null }; // x = null
```

```js
// 注意点
// 1）如果要将一个已经声明的变量用于解构赋值, 必须非常小心
let x;
{x} = {x: 1}; //  SyntaxError: syntax error {x}理解成一个代码块
({x} = {x: 1}); //ok

// 2）由于数组本质是特殊的对象, 因此可以对数组进行对象属性的解构
let arr = [1, 2, 3];
let {0 : first, [arr.length - 1] : last} = arr; //first = 1, last = 3
```

##### 3. 字符串的解构赋值

```js
const [a, b, c, d, e] = 'hello'; //a = "h", b = "e", c = "l", d = "l", e = "o"

// 类似数组的对象都有一个length属性, 因此还可以对这个属性解构赋值.
let { length: len } = 'hello';
len; // 5
```

##### 4. 数值和布尔值的解构赋值

```js
// 1. 解构赋值时, 如果等号右边是数值和布尔值, 则会先转为对象
let { toString: s } = 123;
s === Number.prototype.toString; // true

let { toString: s } = true;
s === Boolean.prototype.toString; // true

// 2. 解构赋值的规则是, 只要等号右边的值不是对象或数组, 就先将其转为对象. 由于undefined和null无法转为对象, 所以对它们进行解构赋值, 都会报错
let { prop: x } = undefined; // TypeError
let { prop: y } = null; // TypeError
```

##### 5. 函数参数的解构赋值

```js
// 遍历数组内的没一个元素, 为数组则相加并替换这个数组位置
[[1, 2], [3, 4]].map(([a, b]) => a + b); // [3, 7]
let arr = [[1, 2], [3, 4]];
for (let i = 0; i < arr.length; i++) {
  let f = function([a, b]) {
    return a + b;
  };
  arr[i] = f(arr[i]);
}

// 默认值 函数参数的解构也可以使用默认值.
function move({ x = 0, y = 0 }) {
  return [x, y];
}
move({ x: 3, y: 8 }); // [3, 8]
move({ x: 3 }); // [3, 0]
move({}); // [0, 0]
move(); // [0, 0]
```

##### 6. 圆括号问题

- 不能使用圆括号的情况

  1. 变量声明语句

  ```js
  // 全部报错
  let [(a)] = [1];

  let {x: (c)} = {};
  let ({x: c}) = {};
  let {(x: c)} = {};
  let {(x): c} = {};

  let { o: ({ p: p }) } = { o: { p: 2 } };
  ```

  2. 函数参数: 函数参数也属于变量声明, 因此不能带有圆括号.

  ```js
  // 报错
  function f([(z)]) { return z; }
  // 报错
  function f([z,(x)]) { return x; }
  ```

  3. 赋值语句的模式

  ```js
  // 全部报错
  ({ p: a }) = { p: 42 };
  ([a]) = [5];
  // 上面代码将整个模式放在圆括号之中, 导致报错.
  // 报错
  [({ p: a }), { x: c }] = [{}, {}];
  ```

````

```js
let x;
{x} = {x: 1}; // SyntaxError: syntax error {x}会被人为是代码块

// 正确的写法
({x} = {x: 1});
````

##### 7.用途

###### a. 交换变量的值

```js
let x = 1;
let y = 2;
// 交换变量x和y的值
[x, y] = [y, x];
```

###### b. 从函数返回多个值

- 函数只能返回一个值, 如果要返回多个值, 只能将它们放在数组或对象里返回. 有了解构赋值, 取出这些值就非常方便

```js
// 返回一个数组
function example() {
  return [1, 2, 3];
}
let [a, b, c] = example();

// 返回一个对象

function example() {
  return {
    foo: 1,
    bar: 2
  };
}
let { foo, bar } = example();
```

###### c. 函数参数的定义

- 解构赋值可以方便地将一组参数与变量名对应起来

```js
// 参数是一组有次序的值
function f([x, y, z]) { ... }
f([1, 2, 3]);

// 参数是一组无次序的值
function f({x, y, z}) { ... }
f({z: 3, y: 2, x: 1});
```

###### d. 提取 JSON 数据

- 解构赋值对提取 JSON 对象中的数据, 尤其有用

```js
let jsonData = {
  id: 42,
  status: 'OK',
  data: [867, 5309]
};

let { id, status, data: number } = jsonData;

console.log(id, status, number);
// 42, "OK", [867, 5309]
```

###### e. 函数参数的默认值

```js
jQuery.ajax = function(
  url,
  {
    async = true,
    beforeSend = function() {},
    cache = true,
    complete = function() {},
    crossDomain = false,
    global = true
    // ... more config
  } = {}
) {
  // ... do stuff
};
```

###### f. 遍历 Map 结构

- 任何部署了 Iterator 接口的对象, 都可以用 for...of 循环遍历. Map 结构原生支持 Iterator 接口, 配合变量的解构赋值, 获取键名和键值就非常方便

```js
const map = new Map();
map.set('first', 'hello');
map.set('second', 'world');

for (let [key, value] of map) {
  console.log(key + ' is ' + value);
}
// first is hello
// second is world

// 获取键名
for (let [key] of map) {
  // ...
}

// 获取键值
for (let [, value] of map) {
  // ...
}
```

###### g. 输入模块的指定方法

- 加载模块时, 往往需要指定输入哪些方法. 解构赋值使得输入语句非常清晰

```js
const { SourceMapConsumer, SourceNode } = require('source-map');
```

### 4. 语句

### 5. 对象

### 6. 数组

### 7. 函数

### 8. 类、模板

### 9. 正则 Reg

### 10. JavaScript 子集与扩展

### 11. nodejs

## 客户端

### 1. Web 中的 JavaScript

### 2. Window 对象(default)

### 3. 脚本化文档

### 4. 脚本化 CSS

### 5. 事件处理(8)

### 6. 脚本化 HTTP

### 7. JQuery 库操纵文档的内容、样式、行为

### 8. 客户端 Cache

### 9. 多媒体与图形编程

### 10. HTML5

## summary

### 1.查看变量值

```js
console.log();
alert();
```

### 2. 对象 map

```js
// 名值对的集合
var book = {
  topic: 'javascript',
  fat: true
};
//对象可以添加属性
book.author = '练顺';
book.topic; // 'javascript' 等价于 book['topic']
```

### 3. 数组

```js
// C++ 中只有*不可以改; java 中 string 不可变, 但是改变后会出现一个新的 string.
var prime = [2, 3, 4, 5];
prime.length; //4

//数组也可以直接添加
prime[4] = 6;
// 清空数组：
prime.length = 0;
```

### 4.对象数组与对象数组

```js
// 对象数组
var points = [
  {
    x: 0,
    y: 0
  },
  {
    x: 1,
    y: 1
  }
];
console.log(points[0].x); // 0

// 数组对象
var data = {
  trail: [[1, 2], [3, 4]],
  trial2: [[2, 3], [4, 5]]
};
console.log(data.trail[0][0]); // 1
```

### 5. 优先级

    () > [] > *
    ()优先级高, 首先说明p是一个指针, 指向一个整型的一维数组, 这个一维数组的长度是n, 也可以说是p的步长. 也就是说执行p+1时, p要跨过n个整型数据的长度.
    数组指针：int (*a)[5]  //行指针
    []优先级高, 先与p结合成为一个数组, 再由int*说明这是一个整型指针数组, 它有n个指针类型的数组元素. 这里执行p+1时, 则p指向下一个数组元素, 这样赋值是错误的：p=a；因为p是个不可知的表示, 只存在p[0]、p[1]、p[2]...p[n-1],而且它们分别是指针变量可以用来存放变量地址. 但可以这样 *p=a; 这里*p表示指针数组第一个元素的值, a的首地址的值.
    指针数组：int *a[5]

### 6. math

```js
// isNaN 原理: NaN与任何值都不相等, 包括自身都不等于自身
console.log(NaN == NaN); // false
console.log(null == undefined); // true
console.log(3 / 2); // 1.5
// 被零除不会报错, 但是结果为+-infinity
console.log(1 / 0); // infinity
console.log(0 / 0); // NaN
isFinite(a); // 有限的  //a!=NaN 且a！= infinty 时为true
```

### 7. 事件处理函数：

    是一个在浏览器中注册的 JavaScript 函数, 当特定事件发生时, 调用此函数. 定义事件处理函数的简单说方法, 给 HTML 的以 'on'为前缀的属性绑定一个回调函数. 或者使用 addEventListener() 函数.

### 8. front 文件加载顺序

> 文档加载完成 —— onload 事件 —— JavaScript 代码

### 9. 两个单独的对象一定不相等, 只有引用同一对象才想等.

```js
var points = [{ x: 0, y: 0 }, { x: 1, y: 1 }];
var points2 = [{ x: 0, y: 0 }, { x: 1, y: 1 }];
console.log(points == points2); // false
po = points;
po == points; //true
```

### 10. 基本类型转换

    显示转换: 调用 Boolean()、Number()、String()、Object()......
    .toString()  // 大家都有, 除了null、undefined
    x + ""       // 等价于 String(x)
    +x           // Number(x)   <====>  x - 0
    !!x          // Boolean(x)
    ==,+         // 会转换为 String 后运算
    // '1' == 1  // true
    // '1' + 1   // '11'
    -、<、>      // 会转换为 Number 后运算

### 11. in、instanceof、isprototypeof、typeof 区别

```js
// prop in object
// in 属性是否存在:
var trees = new Array('redwood', 'bay', 'cedar', 'oak', 'maple');
0 in trees; // 返回true
6 in trees; // 返回false
'bay' in trees; // 返回false (必须使用索引号,而不是数组元素的值)
'length' in trees; // 返回true (length是一个数组属性)
Symbol.iterator in trees; // 返回true (数组可迭代, 只在ES2015+上有效)
'PI' in Math; // 返回true
//    如果你使用 delete 运算符删除了一个属性, 则 in 运算符对所删除属性返回 false
var mycar = { make: 'Honda', model: 'Accord', year: 1998 };
delete mycar.make; // delete mycar['make']
'make' in mycar; // 返回false
//    如果你只是将一个属性的值赋值为undefined, 而没有删除它, 则 in 运算仍然会返回true
var trees = new Array('redwood', 'bay', 'cedar', 'oak', 'maple');
trees[3] = undefined;
3 in trees; // 返回true
// 如果一个属性是从原型链上继承来的, in 运算符也会返回 true
'toString' in {}; // 返回true

// 运算数 x 的类型
typeof x;
// 前者是否为后者的实例, 换而言之, 是判断后者的原型对象（prototype）是否在前者的原型链之上
function A() {}
var a = new A();
a instanceof A; // true
Object instanceof Function; // true
Function instanceof Object; // true
// 判断当前的原型对象（prototype）是否在传入参数的原型链上面
function A() {}
var a = new A();
A.prototype.isPrototypeOf(a); // true
Object.prototype.isPrototypeOf(a); // true
Object.prototype.isPrototypeOf(A.prototype); // true
```

### 12. undefined 情景

    a) 读取不存在的属性和方法时, 返回undefined  // delete a[2] 返回 true 后, a[2] 为 undefined
    b) return;  //返回undefined
    c) 'use strict'：调用的函数(不是方法)中的this返回undefined
    d) Object.getOwnPropertyDescriptor({},  'x'/'tostring')  //undefined
    e) 未定义或定义未赋值的变量

### 13. 枚举

    对象的内置方法不可枚举; 自有的属性方法可枚举

    一些内置属性也不可枚举 prototype
    基本类型的原型属性不可枚举[如Number、Array];
    继承属性可枚举(内置的基本方法除 tostring 外), 不可删除
    方法属性的可枚举性

    只能删除自有的可配置属性, 不能删除继承属性和var/function/prototype等

### 14. Object.prototype 的属性

> tostring
> valueof
> constructor
> hasOwnproperty
> isPrototypeof
> propertyIsEnumerable
> toLocalString

### 15. 可扩展性

    对象的可扩展性用以表示是否可以给对象添加新的属性, 所有的内置对象和自定义对象都实现是可扩展的

### 16. delete

```js
// 删除一个属性后, 他就不存在了. 读取一个不存在的属性返回 undefined; 可以通过 in检测是否删除
// delete可以删数组元素,但是不改变数组的length
var a = [1,2,3];
delete a[2]; //true
2 in a;  //fasle
a.length;   //3

// 删除一个不存在的属性返回true：
delete a[20] // true

// delete 不能删除 var、function、不可配置的属性(如object.prototype)出来的对象
var a={x:1,y:1}；delete a; //false
var t = 12;
delete t //true

// delete 非左值【如1】
delete 1; //false

this.x=1;
delete this.x/x; //true,严格是会报错的

// delete：只能删除自有的可配置属性, 不能删除继承属性, 一些内置属性不可删.
```

### 17. 类

    类 = 使用构造函数 + 构造函数名.prototype

### 18. codingstyle

    JavaScript 中约定大写字母开头的变量是私有的; _开始的变量是局部变量

### 19. 函数声明语句 与 函数表达式

```js
// 函数声明语句
{
  let a = 'secret';
  function f() {
    return a;
  }
}

// 函数表达式
{
  let a = 'secret';
  let f = function() {
    return a;
  };
}
```
