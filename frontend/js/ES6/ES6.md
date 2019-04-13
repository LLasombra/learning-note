- [ECMAScript core knowledge](#ecmascript-core-knowledge)
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

# ECMAScript core knowledge

## 语法

### 1. 词法结构

#### a. 大小写敏感

- JavaScript 区分大小写，(X)HTML 不区分大小写

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
now.getDate(); //从1开始，表示几号
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

    全局对象的属性是全局定义的符号，JavaScript可以直接使用，JavaScript解释器会创建一个新的全局变量，并给它一组定义的初始属性
    全局属性：NaN、undefined ···
    全局函数：eval()、isFinite()、isNaN() ···
    全局对象：Math、json ···
    构造函数：Date()

##### 6. 拆箱与装箱问题

> 由于字符串、数字和布尔值的属性都是只读模式的，并且不能给他们定义新的属性

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
// 两个单独的对象一定不相等，只有引用同一对象才想等。
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
a.toExpontial(5); // '1.25300e+1' 使用指数计数法，小数前只有一位  Number2string
a.topresicion(5); // '12.530' 有效数位  Number2string
parseInt('0.1'); //0
parseInt('.1'); //NaN
parseFloat('0.1'); //0.1
parseFloat('.1'); //0.1
parseFloat('$2'); //NaN
```

### 3. 表达式、运算符

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
    ()优先级高, 首先说明p是一个指针, 指向一个整型的一维数组, 这个一维数组的长度是n, 也可以说是p的步长。也就是说执行p+1时, p要跨过n个整型数据的长度。
    数组指针：int (*a)[5]  //行指针
    []优先级高, 先与p结合成为一个数组, 再由int*说明这是一个整型指针数组, 它有n个指针类型的数组元素。这里执行p+1时, 则p指向下一个数组元素, 这样赋值是错误的：p=a；因为p是个不可知的表示, 只存在p[0]、p[1]、p[2]...p[n-1],而且它们分别是指针变量可以用来存放变量地址。但可以这样 *p=a; 这里*p表示指针数组第一个元素的值, a的首地址的值。
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

    是一个在浏览器中注册的 JavaScript 函数, 当特定事件发生时, 调用此函数。定义事件处理函数的简单说方法, 给 HTML 的以 'on'为前缀的属性绑定一个回调函数。或者使用 addEventListener() 函数.

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
Symbol.iterator in trees; // 返回true (数组可迭代，只在ES2015+上有效)
'PI' in Math; // 返回true
//    如果你使用 delete 运算符删除了一个属性，则 in 运算符对所删除属性返回 false
var mycar = { make: 'Honda', model: 'Accord', year: 1998 };
delete mycar.make; // delete mycar['make']
'make' in mycar; // 返回false
//    如果你只是将一个属性的值赋值为undefined，而没有删除它，则 in 运算仍然会返回true
var trees = new Array('redwood', 'bay', 'cedar', 'oak', 'maple');
trees[3] = undefined;
3 in trees; // 返回true
// 如果一个属性是从原型链上继承来的，in 运算符也会返回 true
'toString' in {}; // 返回true

// 运算数 x 的类型
typeof x;
// 前者是否为后者的实例，换而言之，是判断后者的原型对象（prototype）是否在前者的原型链之上
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

    a) 读取不存在的属性和方法时，返回undefined  // delete a[2] 返回 true 后, a[2] 为 undefined
    b) return;  //返回undefined
    c) 'use strict'：调用的函数(不是方法)中的this返回undefined
    d) Object.getOwnPropertyDescriptor({}， 'x'/'tostring')  //undefined
    e) 未定义或定义未赋值的变量

### 13. 枚举

    对象的内置方法不可枚举; 自有的属性方法可枚举

    一些内置属性也不可枚举 prototype
    基本类型的原型属性不可枚举[如Number、Array];
    继承属性可枚举(内置的基本方法除 tostring 外)，不可删除
    方法属性的可枚举性

    只能删除自有的可配置属性，不能删除继承属性和var/function/prototype等

### 14. Object.prototype 的属性

> tostring
> valueof
> constructor
> hasOwnproperty
> isPrototypeof
> propertyIsEnumerable
> toLocalString

### 15. 可扩展性

    对象的可扩展性用以表示是否可以给对象添加新的属性，所有的内置对象和自定义对象都实现是可扩展的

### 16. delete

```js
// 删除一个属性后，他就不存在了. 读取一个不存在的属性返回 undefined; 可以通过 in检测是否删除
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

// delete：只能删除自有的可配置属性，不能删除继承属性，一些内置属性不可删。
```

### 17. 类

    类 = 使用构造函数 + 构造函数名.prototype

### 18. codingstyle

    JavaScript 中约定大写字母开头的变量是私有的; _开始的变量是局部变量
