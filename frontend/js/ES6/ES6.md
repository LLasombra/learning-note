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

- 原始类型: 数字、字符串、布尔值、原始值[undefined{未声明或声明后未赋值}[少用]、null]

- 对象[属性的集合]类型: array、function etc.

##### 2. 按可变分类

- 可变类型: [对象(名值对)object、array、function.....]

-不可变类型: [null、undefined、number、bool、String]

#### b. 有 GC 机制

#### c. 相关的类

> Math、Date、String、array、function、date、regExp、Error

##### 1. Math

```js
Math.round(0.6);      //1,四舍五入
Math.ceil(0.6);      //1,向上取整
Math.floor(0.6);      //1,向下取整
Math.pow(2,53);      //二的五十三次方
Math.exp(3);         //E的三次方
Math.max/min(x,y,z);      //取大小
Math.log(512)/Math.ln2;

Infinity: 溢出(overflow)
-Infinity: 下溢(underflow)
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

##### 3. String

![avatar](https://img-blog.csdnimg.cn/20190413200601854.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NzA0MzY0,size_16,color_FFFFFF,t_70)

```js
var s = 'hello,world'; //s[0];   ===>'h'
s.charAt(s.length - 1); //d
s.subString(1, 4); //ell
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
    全局属性: NaN、undefined ···
    全局函数: eval()、isFinite()、isNaN() ···
    全局对象: Math、json ···
    构造函数: Date()

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
// 不可变的原始值: undefined、null、String、number、boolean
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
// Number2String
var a = 12.56;
a.toFixed(1); // '12.5' 指定小数点后位数  Number2String
a.toExpontial(5); // '1.25300e+1' 使用指数计数法, 小数前只有一位  Number2String
a.topresicion(5); // '12.530' 有效数位  Number2String
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
// C系列语言中, 定义与声明的区别: 定义: int =0: 声明: int a;
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
- `+` 如果其中一个是对象, 则要通过 toString()/valueof()转换为原始值: 原始值若是字符转, 则另一个值也要转换为字符转. 否则都转换为数字或 NaN 进行运算
  ```js
  1 + {}; //"1[object Object]"
  2 + null; //2
  2 + undefined; //NaN
  ```
- 一元运算符(+ - 、++、--): 作用于一个单独的操作数, 并产生一个新值(优先级很高)
- 位运算符(&、|、^、~、<<、>>、>>>(无符号的有右移,只是左边最高位要补零)
- `==、+ 转换为 String 后运算; -、<、> 转换为 Number 后运算`

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
- 一元运算符(+ - 、++、--): 作用于一个单独的操作数, 并产生一个新值(优先级很高)
- 位运算符(&、|、^、~、<<、>>、>>>(无符号的有右移,只是左边最高位要补零)

##### 3. 逻辑表达式

- 种类: &&、||、！

##### 4. 赋值表达式

- 种类: ?:、typeof、delete [delete: 只能删除自有的可配置属性, 不能删除继承属性]、void、,
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
  |String |'String'|
  |function |'function'|
  |非函数内置对象 |'object'|
  |object |'String'|

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
// 1)如果要将一个已经声明的变量用于解构赋值, 必须非常小心
let x;
{x} = {x: 1}; //  SyntaxError: syntax error {x}理解成一个代码块
({x} = {x: 1}); //ok

// 2)由于数组本质是特殊的对象, 因此可以对数组进行对象属性的解构
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

#### a. 表达式语句

1. 赋值语句: let 与 () 问题
2. 递增、递减:
3. delete:
4. 函数调用:

#### b. 复合语句和空语句

1. JavaScript 解释器执行语句时, 空语句不做任何操作.
2. 空语句可以通过循环初始化数组.

```js
  //初始化 a 数组
for(var i=0;i<a.length;a[i++]=0)
```

#### c. 声明语句

1. 声明语句可以声明变量或函数
2. function:声明与定义的区别:
   都创建了新的函数对象, 声明语句中的函数名是一个<font color = 'red'>变量</font>; 定义中函数名是一个<font color = 'red'>对象</font>.
3. JavaScript 中可以在声明一个函数之前就调用它.

#### d. 条件语句

1. if ... else if ...else
2. **<font color = 'red'>switch: case 的匹配表达式实际上是'==='恒等运算符比较, 比较是不会做任何转换</font>**

#### e. 循环语句

1. 种类:
   while 、do while 、for...in 、for...of

2. for/in: for(variable in object)

   - for/in 循环并不会遍历所有的属性, 只遍历可枚举的属性
   - JavaScript 内置方法不是可枚举的【如 toString、prototype】, 代码中的方法属性和继承而来的都可枚举. (存在方法可以使其不可枚举). 枚举按照先后的顺序

   ```js
   var o = { x: 1, y: 2, z: 3 };
   var a = [],
     i = 0;
   for (a[i++] in o);
   // 隐式的给 a 赋值
   for (a[i] in o) {
     i++;
   }

   for (x in o) {
     console.log(x);
   }
   ```

3. for...of

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

#### f. 跳转语句

1. 种类:
   label、break、continue[再循环体内]、return、throw、try/catch/finally
2. 标签语句:

   - 通过给语句定义标签, 就可以在程序的任何地方通过标签名引用这条语句

     ```js
     mianloop: while(token!=null){
     //program
     continue mainloop;     //跳转到下一次loop
     //program}
     // 1.可以使用同一标识符作为语句标签和作为变量名后函数名
     ```

   - 语句标签在起作用的语句(也可以是子句)内是要有定义的
   - 一个语句标签不能和他内部的语句标签重名, 但在两个代码段不相互嵌套情况下是可以出现的[类似于临时变量]
   - 任何语句都可以有多个标签.

3. break: 立即跳出最内层的循或 switch 语句[立即跳出循环或其他语句]

   ```
   a) break;
   b) break labelname;
   ```

4. continue:结束本次循环, 进入下一次循环

   ```
   a) continue;
   b) Continue labelname;
   c) 注意: while 中, 检测循环开始时的表达式
    dowhile 中, 检测 while 中的表达式
   for 中, 计算自增, 检测 test
   for/in 中, 开始遍历下一个属性名, 这个属性名赋给指定的变量
   ```

5. return:
   return; //返回 undefined

6. throw:

   ```js
   //  异常
   if (x < 0) throw new Error('x 不能是负数');
   //  Error 对象 = name 属性 + Message 属性
   ```

7. try/catch/finally:
   ```
   a) 终止 try 语句的方法
      正常终止,执行完了
      break、continue、return
      异常+捕获
      异常+不捕获
   b) 只要 try 语句中代码有一部分执行了, finally 一定会执行. finally 常用于清除工作
   ```

#### g. 其他语句

1.  kind
    - with、debugger、'use strict'
2.  with: 用于临时扩展作用域链, 但是影响函数的优化
    a) with object //将 object 添加到作用域链的头部,然后执行
    Statement //statement 语句,最后把作用域链恢复到开始状态
    b) **严格模式下, 禁用 with 语句**
    c) 在对象层次嵌套很深的时候, 通常会使用 with 语句来简化代码的编写

    ```js
    document.form[0].address.value; //若多次出现
    // 则使用:
    with (document.forms[0]) {
      name.value = '';
      address.value = '';
      email.value = '';
    }

    var f = document.forms[0];
    f.name.value = '';
    ```

    d) **with 语句提供了一种读写属性的快捷方式, 但不能创建属性**

    ```js
    with (o) {
      x = 1; //若 o 有 x 属性,则为 x 赋值为 1
      //若 o 没有 x 属性 ,则等价于 var x=1;
    }
    ```

3.  degubber: 用来产生一个断点, JavaScript 代码会停止在断点位置
4.  'use strict'
    a) 只能出现在脚本化代码的开始, 函数体的开始, 任何实体语句以前: 与 eval() 使用时算是局部的上下文环境[可改不可创]
    b) 在严格模式下, 禁用 with 语句, 所有变量都要声明
    c) 调用的函数(不是方法)中的 this 返回 undefined:
    ```js
    // 区分是否支持 ECMA5 严格模式:
    var hasStrictModel = (function() {
      'use strict';
      // 在严格时, this 返回 undefined; 在不严格时, this 返回全局对象
      return this === undefined;
    })();
    ```
    d) call()、apply()中的 this 是通过 call()、apply()传入的第一个参数:
    在不严格时,null/undefined 的值会被全局对象或转换为对象的非全局对象所替代
    e) ···

#### h. 总结

![avatar](https://img-blog.csdnimg.cn/20190415095132209.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3NzA0MzY0,size_16,color_FFFFFF,t_70)

### 5. 字符串

#### a. 字符串的遍历器接口

1. for...of 循环遍历

   ```js
   // 可以识别大于0xFFFF的码点, 传统的for循环无法识别这样的码点(汉字)
   let text = String.fromCodePoint(0x20bb7);

   for (let i = 0; i < text.length; i++) {
     console.log(text[i]);
   }
   // " "
   // " "

   for (let i of text) {
     console.log(i);
   }
   // "𠮷"
   ```

#### b. includes(), startsWith(), endsWith()

1. 一个字符串是否包含在另一个字符串中

```js
// includes(): 返回布尔值, 表示是否找到了参数字符串

// startsWith(): 返回布尔值, 表示参数字符串是否在原字符串的头部.
// endsWith(): 返回布尔值, 表示参数字符串是否在原字符串的尾部.
```

#### c. repeat()

```js
// repeat方法返回一个新字符串, 表示将原字符串重复n次: 参数大于等于0, 小数取整, NaN等同于 0
'na'.repeat(2.9); // "nana"
'na'.repeat(NaN); // ""
```

#### d. matchAll()

    matchAll 方法返回一个正则表达式在当前字符串的所有匹配

#### e. 模板字符串

```js
// 模板字符串 (template string)是增强版的字符串, 用反引号 (`)标识. 它可以当作普通字符串使用, 也可以用来定义多行字符串, 或者在字符串中嵌入变量
// 1. 普通字符串
`In JavaScript '\n' is a line-feed.`;

// 2. 字符串中嵌入变量, 需要将变量名写在 ${} 之中
let name = 'Bob',
  time = 'today';
`Hello ${name}, how are you ${time}?`;

// 3. 如果使用模板字符串表示多行字符串, 所有的空格和缩进都会被保留在输出之中, 比如<ul>标签前面会有一个换行.如果你不想要这个换行, 可以使用trim方法消除它
$('#list').html(
  `
  <ul>
    <li>first</li>
    <li>second</li>
  </ul>
  `.trim()
);

// 4. 大括号内部可以放入任意的 JavaScript 表达式, 可以进行运算, 以及引用对象属性
let x = 1;
let y = 2;
`${x} + ${y * 2} = ${x + y * 2}`; // "1 + 4 = 5"

let obj = { x: 1, y: 2 };
`${obj.x + obj.y}`; // "3"

// 5. 模板字符串之中还能调用函数
function fn() {
  return 'Hello World';
}

`foo ${fn()} bar`; // foo Hello World bar

// 6. 模板字符串中的变量没有声明, 将报错
let msg = `Hello, ${place}`; // Uncaught ReferenceError: place is not defined
```

### 6. 对象

#### a. 对象的扩展

##### 1. 属性的简洁表示法

```js
// 1. 可以写入变量和函数, 作为对象的属性和方法
const foo = 'bar';
const baz = { foo: foo };

function f(x, y) {
  // 返回一个对象 {x: x, y: y}
  return { x, y };
}

const o = {
  method() {
    return 'Hello!';
  }
};
o.method(); // 'Hello!'

// 2. 模块输出一组变量简写
module.exports = { getItem, setItem, clear };

// 3. 属性赋值器简写
const cart = {
  // JavaScript中约定大写字母开头的变量是私有的；_开始的变量是局部变量
  _wheels: 4,
  get wheels() {
    return this._wheels;
  },
  set wheels(value) {
    if (value < this._wheels) {
      throw new Error('数值太小了！');
    }
    this._wheels = value;
  }
};

// 4. 如果某个方法的值是一个 Generator 函数，前面需要加上星号
const obj = {
  *m() {
    yield 'hello world';
  }
};
```

##### 2. 属性名表达式

```js
// 1. 字面量定义对象时，用方法二 (表达式)作为对象的属性名，即把表达式放在方括号内
let propKey = 'foo';
let obj = {
  [propKey]: true,
  ['a' + 'bc']: 123
};

// 2. 表达式还可以用于定义方法名
let obj = {
  ['h' + 'ello']() {
    return 'hi';
  }
};
obj.hello(); // hi

// 3. 注意，属性名表达式如果是一个对象，默认情况下会自动将对象转为字符串[object Object]，这一点要特别小心.

const keyA = { a: 1 };
const keyB = { b: 2 };

const myObject = {
  [keyA]: 'valueA',
  [keyB]: 'valueB'
};

myObject; // Object {[object Object]: "valueB"}  // 属性同名覆盖吊一个
```

##### 3. 方法的 name 属性

```js
// 1. 函数的name属性，返回函数名. 对象方法也是函数，因此也有name属性
const person = {
  sayName() {
    console.log('hello!');
  },
  get foo() {}
};
person.sayName.name; // "sayName"
person.foo.name; // TypeError: Cannot read property 'name' of undefined
const descriptor = Object.getOwnPropertyDescriptor(obj, 'foo');
descriptor.get.name; // "get foo"
```

##### 4. 属性的可枚举性和遍历

- 可枚举性: <font color='red'>尽量不要用 for...in 循环，而用 Object.keys()代替</font>
  1. for...in 循环：只遍历对象自身的和继承的可枚举的属性.
  2. Object.keys()：返回对象自身的所有可枚举的属性的键名.
  3. JSON.stringify()：只串行化对象自身的可枚举的属性.
  4. Object.assign()： 忽略 enumerable 为 false 的属性，只拷贝对象自身的可枚举的属性.
- 属性的遍历

  - 遍历的规则
    首先遍历所有数值键，按照数值升序排列.
    其次遍历所有字符串键，按照加入时间升序排列.
    最后遍历所有 Symbol 键，按照加入时间升序排列

  ```
   (1)for...in
      for...in循环遍历对象自身的和继承的可枚举属性 (不含 Symbol 属性).

   (2)Object.keys(obj)
      Object.keys返回一个数组，包括对象自身的 (不含继承的)所有可枚举属性 (不含 Symbol 属性)的键名.

   (3)Object.getOwnPropertyNames(obj): 小所有
      Object.getOwnPropertyNames返回一个数组，包含对象自身的所有属性 (不含 Symbol 属性，但是包括不可枚举属性)的键名.

   (4)Object.getOwnPropertySymbols(obj): 大所有
      Object.getOwnPropertySymbols返回一个数组，包含对象自身的所有 Symbol 属性的键名.

   (5)Reflect.ownKeys(obj)： 大所有
      Reflect.ownKeys返回一个数组，包含对象自身的所有键名，不管键名是 Symbol 或字符串，也不管是否可枚举.
  ```

##### 5. super 关键字

- this 关键字总是指向函数所在的当前对象;
- super 指向 **<font color='red'>当前对象的原型对象, 且必须在当前对象的方法内使用</font>**

```js
const proto = {
  foo: 'hello'
};

const obj = {
  foo: 'world',
  find() {
    return super.foo;
  }
};
Object.setPrototypeOf(obj, proto);
obj.find(); // "hello"
// 对象obj.find()方法之中，通过super.foo引用了原型对象proto的foo属性.
// 注意，super关键字表示原型对象时，只能用在对象的方法之中，用在其他地方都会报错.
```

##### 6. 对象的扩展运算符 ...

- 对象的解构赋值: 解构赋值的拷贝是浅拷贝
- **<font color ='red'>扩展运算符的解构赋值，不能复制继承属性; 非扩展云闪付的解构赋值可以用读取继承属性</font>**

```js
// 1. ...z 必须是最后一个参数
let { x, y, ...z } = { x: 1, y: 2, a: 3, b: 4 };

// 2. 扩展运算符的解构赋值，不能复制继承属性; 非扩展云闪付的解构赋值可以用读取继承属性
let o1 = { a: 1 };
let o2 = { b: 2 };
o2.__proto__ = o1;
let { ...o3 } = o2;
o3; // { b: 2 }
o3.a; // undefined

const o = Object.create({ x: 1, y: 2 });
o.z = 3;
console.log(o); // {z: 3}
let { x, ...newObj } = o;
let { y, z } = newObj;
x; // 1
y; // undefined
z; // 3

// 3. 对象的扩展运算符 (...)用于取出参数对象的所有可遍历属性，拷贝到当前对象之中
let z = { a: 3, b: 4 };
let n = { ...z }; //n = { a: 3, b: 4 }
{...{}, a: 1} // { a: 1 }

//4. 扩展运算符后面不是对象，则会自动将其转为对象
// 等同于 {...Object(1)}; 扩展运算符后面是整数1，会自动转为数值的包装对象Number{1}. 由于该对象没有自身属性，所以返回一个空对象
{...1} // {}
{...true} // {}
{...undefined} // {}
{...null} // {}
{...'hello'} // {0: "h", 1: "e", 2: "l", 3: "l", 4: "o"}
// Object.assign() 方法用于将所有可枚举属性的值从一个或多个源对象复制到目标对象
// 5. 对象的扩展运算符等同于使用Object.assign()方法
let aClone = { ...a }; // let aClone = Object.assign({}, a);

// 6. 深克隆
const clone1 = {
  __proto__: Object.getPrototypeOf(obj),
  ...obj
};
// 写法二
const clone2 = Object.assign(
  Object.create(Object.getPrototypeOf(obj)),
  obj
);
// 写法三
const clone3 = Object.create(
  Object.getPrototypeOf(obj),
  Object.getOwnPropertyDescriptors(obj)
)

// 7. 扩展运算符可以用于合并两个对象
let ab = { ...a, ...b };  // let ab = Object.assign({}, a, b);

// 8. 如果用户自定义的属性，放在扩展运算符后面，则扩展运算符内部的同名属性会被覆盖掉
let aWithOverrides = { ...a, x: 1, y: 2 };
// 等同于
let aWithOverrides = { ...a, ...{ x: 1, y: 2 } };
// 等同于
let x = 1, y = 2, aWithOverrides = { ...a, x, y };
// 等同于
let aWithOverrides = Object.assign({}, a, { x: 1, y: 2 });

// 9. 修改现有对象部分的属性
let newVersion = {
  ...previousVersion,
  name: 'New Name' // Override the name property
};
   // 恢复为默认值
   let aWithDefaults = { x: 1, y: 2, ...a };

// 10. 与数组的扩展运算符一样，对象的扩展运算符后面可以跟表达式.
const obj = {
  ...(x > 1 ? {a: 1} : {}), // 会优先执行
  b: 2,
};
```

#### b. 对象的创建: 直接量、new、object.create()

1. 对象直接量:
   - 通过直接量创建的对象都有一个相同的原型对象, 并可以通过 JavaScript 代码 object.prototype 获取原型对象的引用

```js
var point = { x: 0, y: 0 }; //可以嵌套; 每次执行都会新建并初始化
// 对象直接量的原型: object.prototype   // f.prototype=p;
```

2. new: new + 构造函数:
   ```js
   var a = new Array();
   // new 创建的原型: 构造函数.prototype   // Array.prototype
   ```
3. **object.create()**:
   ```js
   var o1 = object.create({ x: 1, y: 2 });
   // 可以通过传入 null 来创建没有原型的新对象:
   var o2 = object.create(null); //不继承任何属性和方法, 包括 tostring
   // 创建普通空对象: {}、 object.create(object.prototype)、new object();
   var o3 = object.create(object.prototype);
   var o3 = {};
   var o3 = new object();
   ```
4. 通过原型继承创建一个新的对象
   ```js
   function inherit(p) {
     if (p == null) throw typeError();
     if (Object.create) return Object.create(p);
     var t = typeof p;
     if (t !== 'object' && t !== 'function') throw Type.Error();
     function f() {}
     f.prototype = p;
     return new f();
   }
   ```

注: 1.

#### c. 对象的特征 value、writable、enumerable、configurable

- a) ECMA3 中创建的属性都是可写、可枚举、可配置的, 且无法更改这些特征
- b) ECMA5 中可以通过 API 给原型对象添加方法; 也可以将对象定义为不可写或不可删除的属性
- c) <font color ='red'>**描述符对象**: 实现属性特征的查询和设置(不能读取继承属性和方法)</font>

      ```js
      Object.getOwnPropertyDescriptor({x:1}, 'x') //value:1;w:T;e:T;c:T
      Object.getOwnPropertyDescriptor({}, 'x'/'tostring') //undefined
      ```

- d) 设置属性特征或新建属性具有某种特征:

  - **defineproperty(修改对象, 属性名, 描述符对象)**
    ```js
    // 若新建, 描述符对象属性不全时默认false、undefined
    var o = {}; // 创建一个空对象
    // 添加一个不可枚举的数据属性x, 并赋值为1
    Object.defineProperty(o, 'x', {
      value: 1,
      writable: true,
      enumerable: false,
      configurable: true
    });
    // 属性是存在的, 但不可枚举 o.x;  // => 1
    Object.keys(o); // => []
    // 现在对属性x做修改, 让它变为只读
    Object.defineProperty(o, 'x', { writable: false });
    // 试图更改这个属性的值
    o.x = 2; // 操作失败但不报错, 而在严格模式中抛出类型错误异常
    o.x; // => 1
    // 属性依然是可配置的, 因此可以通过这种方式对它进行修改:
    Object.defineProperty(o, 'x', { value: 2 });
    o.x; // => 2
    // 现在将x从数据属性修改为存取器属性
    Object.defineProperty(o, 'x', {
      get: function() {
        return 0;
      }
    });
    o.x; // => 0
    ```
  - **同时修改或创建多个属性: object.defineproperties(object,map)**

    ```js
    var p = Object.defineProperties(
      {}, // 要修改的对象, 这里是 null
      {
        x: { value: 1, writable: true, enumerable: true, configurable: true },
        r: {
          get: function() {
            return Math.sqrt(this.x * this.x + this.y * this.y);
          },
          enumerable: true,
          configurable: true
        }
      }
    );
    ```

- e) object.defineproperty()和 object.defineproperties(object,map)的使用规则
  ```js
  [违反规则的使用都会抛出类型错误异常]:
  1. 如果对象是不可扩展的, 则可以编辑已有的自有属性, 但不能给它添加新属性.
  2.如果属性是不可配置的, 则不能修改它的可配置性和可枚举性.
  3.如果存取器属性是不可配置的, 则不能修改其getter和setter方法, 也不能将它转换为数据属性.
  4.如果数据属性是不可配置的, 则不能将它转换为存取器属性.
  5.如果数据属性是不可配置的, 则不能将它的可写性从false修改为true, 但可以从true修改为false.
  6.如果数据属性是不可配置且不可写的, 则不能修改它的值.然而可配置但不可写属性的值是可以修改的(实际上是先将它标记为可写的, 然后修改它的值, 最后转换为不可写的).
  ```
- f) **<font color='red'>可扩展与可配置的区别</font>**

  1. **<font color='red' >可扩展(Object.preventExtensions(o)): 对象; 可配置(defineProperty): 属性</font>**
  2. 可扩展与是否可以添加属性有关; 可配置与 delete 有关 [不可配置不可删]
  3. 不可扩展的可配置可以删, 某些内置对象属性不可删 [object.prototype/var/function 等]
  4. 继承的属性方法描述符对象不可以读; 继承对象的属性是可以枚举的

     ```js
     //给 object.prototype 添加一个新的不可枚举的 extend() 方法
     //复制参数对象的所有自有属性(包括不可枚举对象)
     //a.entend(o) 代码中的this是object.prototype
     Object.defineProperty(
       Object.prototype,
       'extend', // 定义Object.prototype.extend
       {
         writable: true,
         enumerable: false, // 将其定义为不可枚举的
         configurable: true,
         value: function(o) {
           // 值就是这个函数
           // 得到所有的自有属性,包括不可枚举属性
           var names = Object.getOwnPropertyNames(o);
           // 遍历它们
           for (var i = 0; i < names.length; i++) {
             // 如果属性已经存在,则跳过
             if (names[i] in this) continue;
             // 获得o中的属性的描述符
             var desc = Object.getOwnPropertyDescriptor(o, names[i]);
             // 用它给this创建一个属性
             Object.defineProperty(this, names[i], desc);
           }
         }
       }
     );
     ```

#### d. 对象的查询设置

- object.property 等价于 object['property'] 等价于 关联数组 [js 中对象都是]

  1. 使用 [] 运算符(其中 string 是动态的,在运行时可以更改), 所以全面[适用性强]
     .运算符之后的标识符是静态的, 必须写死在代码里)
  2. 继承中的赋值, 不会影响原型数据, 自改变自身 [只在查询是才能感觉到继承]

- 关联数组/字典/散列: JavaScript 中对象都是关联数组
  ```js
  var addr = '';
  // 可以改用for/in
  for (var i = 0; i < 4; i++) {
    // address+i是属性, 而非下标
    addr += customer['address' + i] + '\n';
  }
  ```
- 属性访问错误:
  1. 查询不存在的属性返回 undefined
  2. 删除[delete]不存在的属性: true
  3. 访问不存在对象的而属性会报错. [null/undefined 无属性]
  4. 给 null、undefined 设置属性会报错
- setter/getter: 有 setter、getter 定义的属性称为 '存取器属性', 可以继承
  ```js
  var p = {
    x: 1.0,
    y: 1.0,
    get r() {
      /*代码*/
    },
    set r(argument) {
      /*代码*/
    },
    get theta() {
      /*代码*/
    }
  };
  var q = inherit(p);
  (q.x = 1), (q.y = 1);
  consoale(q.theta); //ok
  ```

#### e. 对象的删除属性

- 删除属性: delete: 只能删除自有的可配置属性, 不能删除继承属性, 一些内置对象属性不可删

#### f. 对象的检测属性

- 检测属性: in、hasOwnProperty()、PropertyIsEnumberable()、属性检查

  ```js
  // a)  in: 可以检查继承属性 [instanceof 可以检查继承的方法]
  // In可以区分不存在属性和存在属性但值为undefined:
  var o = { x: undefined };
  'toString' in o; //true
  o.x !== undefined; //false
  'x' in o; //true
  delete o.x; //true    delete o;  /error
  'x' in o; //false

  // b)  hasownproperty():  //继承属性不可以
  o.hasownproperty('x'); //true
  o.hasownproperty('tostring'); //false

  // c)  propertyisenumerable():   //继承属性可枚举(tostring类似方法除外)
  var o = inherit({ y: 2 });
  o.x = 1; //ok
  o.propertyisenumerable('x'); //true
  o.propertyisenumerable('y'); //false

  // d)  属性查询:
  var o = { x: 1 };
  o.x !== undefined; //true    =====>o.x!=null
  o.toString !== undefined; //true
  if (o.x) {
    //x为undefined、null、false、''、+-0、NaN    [7]
    //x存在，且不能转换为false
  }
  ```

#### g. 对象的枚举属性

- 枚举属性: [获取所有属性: getOwnPropertyNames()]
  1. 有许多实用工具库给 Object.prototype 添加了很多的新的方法或属性 [可继承], 且这些方法是可枚举的(ECMA5 之前), 所以要过滤这些方法.
  ```js
  // 方法一：
  for (p in o) {
    if (!o.hasProperty(p)) continue; //过滤继承方法
  }
  //方法二：
  for (p in o) {
    if (typeof o[p] === 'function') continue;
  }
  ```
  2. 枚举对象的工具函数
  - 返回对象
    ```
    extend(o,p); //p——>o,覆盖相同的属性(p 基准)
    merge(o,p); //P——>o,不覆盖相同属性(o 基准)
    restrict(o,p); //删除 o 中与 p 不相同的属性(o 基准)
    Subtract(o,p); //删除 o 中与 P 相同属性(o 基准)【o-p】
    union(o,p); //同有 O 和 P 的所有属性(p 基准)【o+p】
    intersection(o,p); //op 共有的属性(o 基准)【∩】
    ```
  - 返回数组
    ```
    keys(o); //返回数组包含 o 中可枚举的自有属性
    ECMA5:
      Object.keys() //自有
      Object.getOwnPropertyNames() //获取所有自有属性
    ```

#### h. 对象的三个属性: 原型属性、类属性、可扩展性(可删减属性): 区别于可配置性(与 delete 有关)

1.  原型属性：
    ```
    直接量创建对象原型：Object.PPrototype==>constructor.prototype
    new 创建：构造函数名.prototype
    Object.prototype(o):参数 o 为原型
    ```
2.  查询原型: //查询原型，检测一个 Object 是否为另一个 Object 的原型

    ```js
    // ECMA5：Object.getPrototypeof()
    var p = { x: 1 };
    var o = Object.create(p);
    p.isprototypeof(o); //true
    Object.prototype.isPrototypeof(o / p); //true
    // ECMA3：
    o.constructor.prototype; //检查原型
    ```

3.  类属性：是一个字符串，用以表示对象信息 classof()

    ```js
    // 查询方法：
    toString(); //返回字符串 '[Object class]' [8，-1]
    //由于很多类的 toString 方法重写了，所以间接调用 [.call()]
    function classof(o) {
      if (o === null) return 'Null';
      if (o === undefined) return 'Undefined';
      return Object.prototype.toString.call(o).slice(8, -1);
    }
    ```

4.  可扩展性(Object.esExtensible()): **<font color='red' >可扩展(Object.preventExtensions(o)): 对象; 可配置(defineProperty): 属性</font>**

    ```
    对象的可扩展性用以表示是否可以给对象添加新的属性;
    可配置 () 是属性, 表示是否可以 delete
    所有的内置对象和自定义对象都实现是可扩展的;
    宿主对象的可扩展性由 JavaScript 定义. [除非把他们转换为不可扩展的]
        可扩展------->不可扩展： Object.preventExtensions(o);
    ```

#### i. 序列化对象

1. 将对象的状态转为字符串(stringify());也可将字符串转为对象(parese())
   ```js
   //ECMA5：
   JSON.stringify(); //只能序列化对象可枚举属性
   JSON.parse(); //string——>object
   o = { x: 1, y: { z: [false, null, ''] } }; // 定义一个测试对象
   s = JSON.stringify(o); // s是 '{"x":1,"y":{"z":[false,null,""]}}'
   p = JSON.parse(s); // p是o的深拷贝
   //【JSON语法：NaN、+-infinity------------>序列化为null】
   ```

#### j. 对象的方法

1. object.prototype 里的方法
   ```js
   tostring(); //object——>string
   toLocalString(); //返回表示这个对象的本地化字符串
   toJson(); //object.prototype里没有次方法；但是JSON.Stringify()会调用
   valueOf(); //object——>原始值
   ```

### 7. 数组

#### 相关认知

```
1.  JavaScript 中数组是无类型的：数组元素可以是任意类型，并且同一数组可以有不同元素类型.
2.  JavaScript 中数组是动态的，可增减，声明时无需大小
3.  数组再系统内部是经过优化的，比访问常规对象属性要快
4.  类数组对象也适应数组的方法
5.  JavaScript 访问不存在属性是不会报错，返回 undefined
```

#### a. 数组扩展

##### 1. 扩展运算符: 主要用于函数调用

```js
// 1. 初识
[...[], 1]; // [1]
console.log(...[1, 2, 3]); // 1 2 3
console.log(1, ...[2, 3, 4], 5); // 1 2 3 4 5

// 2. 运算符主要用于函数调用: 该运算符将一个数组，变为参数序列
function push(array, ...items) {
  array.push(...items);
}

function add(z, x, y) {
  return z + x + y;
}
const numbers = [4, 38];
add(1, ...numbers); // 42

// 3. 扩展运算符后面还可以放置表达式
var x = 5;
const arar = [...(x > 0 ? ['a'] : []), 'b'];

// 4. 扩展运算符如果放在括号中，JavaScript 引擎就会认为这是函数调用. 如果这时不是函数调用，就会报错
console.log((...[1, 2])) // Uncaught SyntaxError: Unexpected number
console.log(...[1, 2]) // 1 2

// 5. 复制数组: 浅拷贝
const a1 = [1, 2];
// 写法一
const a2 = [...a1];
// 写法二
const [...a2] = a1;

// 6. 合并数组: 浅拷贝
const arr1 = ['a', 'b'];
const arr2 = ['c'];
// ES5 的合并数组
arr1.concat(arr1, arr2);
// ES6 的合并数组
[...arr2, ...arr3]

// 7. 与解构赋值结合
// ES5
a = list[0], rest = list.slice(1)
// ES6
[a, ...rest] = list

const [first, ...rest] = [1, 2, 3, 4, 5];
const [first, ...rest] = ["foo"];

// 8. 扩展运算符还可以将字符串转为真正的数组
[...'hello'] // [ "h", "e", "l", "l", "o" ]

// 9. 任何定义了遍历器 (Iterator)接口的对象 (参阅 Iterator 一章)，都可以用扩展运算符转为真正的数组
let nodeList = document.querySelectorAll('div'); // 类数组对象
let array = [...nodeList]; // 转为 Array, 原因就在于NodeList对象实现了 Iterator

Number.prototype[Symbol.iterator] = function*() {
  let i = 0;
  let num = this.valueOf();
  while (i < num) {
    yield i++;
  }
}
console.log([...5]) // [0, 1, 2, 3, 4]

// 10.
let aClone = { ...a }; // let aClone = Object.assign({}, a);
const obj = {a: 1, b: 2};
let arr = [...obj]; // TypeError: Cannot spread non-iterable object
// 对象的扩展运算符等同于使用Object.assign()方法: Object.assign() 方法用于将所有可枚举属性的值从一个或多个源对象复制到目标对象
let arr = {}...obj}; // ["a", "b"]

// 11. 扩展运算符内部调用的是数据结构的 Iterator 接口，因此只要具有 Iterator 接口的对象，都可以使用扩展运算符: Map 和 Set 结构，Generator 函数
let map = new Map([
  [1, 'one'],
  [2, 'two'],
  [3, 'three'],
]);
let arr = [...map.keys()]; // [1, 2, 3]

const go = function*(){
  yield 1;
  yield 2;
  yield 3;
};
[...go()] // [1, 2, 3]
```

##### 2. Array.from()

- Array.from 的第一个参数指定了第二个参数运行的次数, 还可以接受第二个参数，作用类似于数组的 map 方法
- Array.from 方法用于将两类对象转为真正的数组: 类似数组的对象(array-like object) 和可遍历 (iterable)的对象 (包括 ES6 新增的数据结构 Set 和 Map)

  ```js
  let arrayLike = {
    '0': 'a',
    '1': 'b',
    '2': 'c',
    length: 3
  };
  // ES5的写法
  var arr1 = [].slice.call(arrayLike); // ['a', 'b', 'c']
  // ES6的写法
  let arr2 = Array.from(arrayLike); // ['a', 'b', 'c']

  // NodeList对象
  let ps = document.querySelectorAll('p');
  Array.from(ps).filter(p => {
    return p.textContent.length > 100;
  });

  // arguments对象
  function foo() {
    var args = Array.from(arguments);
    const args = [...arguments];
    // ...
  }

  Array.from('hello'); // ['h', 'e', 'l', 'l', 'o']
  let namesSet = new Set(['a', 'b']);
  Array.from(namesSet); // ['a', 'b']
  ```

- Array.from 还可以接受第二个参数，作用类似于数组的 map 方法，用来对每个元素进行处理，将处理后的值放入返回的数组

  ```js
  Array.from(arrayLike, x => x * x);
  // 等同于
  Array.from(arrayLike).map(x => x * x);

  Array.from([1, 2, 3], x => x * x);
  // [1, 4, 9]

  Array.from([1, , 2, , 3], n => n || 0); // [1, 0, 2, 0, 3]
  // Array.from的第一个参数指定了第二个参数运行的次数
  Array.from({ length: 2 }, () => 'jack');
  ```

##### 3. Array.of()

- Array.of 方法用于将一组值，转换为数组
  ```js
  Array.of(3, 11, 8); // [3,11,8]
  Array.of(3); // [3]
  Array.of(3).length; // 1
  ```

##### 4. 数组实例的 copyWithin()

- 数组实例的 copyWithin 方法，在当前数组内部，将指定位置的成员复制到其他位置（会覆盖原有成员），然后返回当前数组. 也就是说，**<font color='red'>使用这个方法，会修改当前数组</font>**

> Array.prototype.copyWithin(target, start = 0, end = this.length)

- target（必需）：开始替换的位置. 如果为负值，表示倒数.
- start（可选）：从该位置开始读取数据，默认为 0. 如果为负值，表示倒数.
- end（可选）：到该位置前停止读取数据，默认等于数组长度. 如果为负值，表示倒数.

  ```js
  [1, 2, 3, 4, 5].copyWithin(0, 3); // [4, 5, 3, 4, 5]
  ```

##### 5. 数组实例的 find(f) 和 findIndex(f): Array.filter(predicate[, o])

- 第一个符合条件的数组成员, 如果没有符合条件的成员，则返回 undefined
  ```js
  [1, 5, 10, 15].find(function(value[, index, arr]) {
    return value > 9;
  }); // 10
  ```

##### 6. 数组实例的 fill()

- fill 方法使用给定值，填充一个数组, 会覆盖之前的值
  ```js
  // fill(value[,start,end])
  ['a', 'b', 'c'].fill(7, 1, 2);
  ```

##### 7. 数组实例的 entries()，keys() 和 values()

- keys()是对键名的遍历、values()是对键值的遍历，entries()是对键值对的遍历

  ```js
  // 方式一
  var keys = Object.keys(o); // 获得o对象属性名组成的数组
  var values = []; // 在数组中存储匹配属性的值
  for (var i = 0; i < keys.length; i++) {
    // 对于数组中每个索引
    var key = keys[i]; // 获得索引处的键值
    values[i] = o[key]; // 在values数组中保存属性值
  }

  // 方式2
  var data = [1, 2, 3, 4, 5]; // 这是需要遍历的数组
  var sumOfSquares = 0; // 要得到数据的平方和
  data.forEach(function(x) {
    // 把每个元素传递给此函数
    sumOfSquares += x * x; // 平方相加
  });
  sumOfSquares; // =>55 : 1+4+9+16+25

  // 方式3
  for (let index of ['a', 'b'].keys()) {
  ...
  }
  for (let elem of ['a', 'b'].values()) {
    ...
  }
  for (let [index, elem] of ['a', 'b'].entries()) {
    ...
  }
  // 如果不使用for...of循环，可以手动调用遍历器对象的next方法，进行遍历
  let letter = ['a', 'b', 'c'];
  let entries = letter.entries();
  console.log(entries.next().value); // [0, 'a']
  ```

##### 8. 数组实例的 includes(string[, start])

- Array.prototype.includes 方法返回一个布尔值，表示某个数组是否包含给定的值，与字符串的 includes 方法类似
  ```js
  [(1, 2, 3)].includes(2); // true
  [(1, 2, 3)].includes(4); // false
  //   -2 -1   ***     0  1
  [1, 2, 3].includes(3, 3); // false
  [1, 2, 3].includes(3, -1); // true
  ```

##### 9. 数组实例的 flat()，flatMap()

- Array.prototype.flat()用于将嵌套的数组“拉平”，变成一维的数组. 该方法返回一个新数组，对原数据没有影响

  ```js
  a = [1, 2, [3, 4]]; // (3) [1, 2, Array(2)]
  b = a.flat(); // (4) [1, 2, 3, 4]
  a; // (3) [1, 2, Array(2)]

  // 2. flat()默认只会“拉平”一层，如果想要“拉平”多层的嵌套数组
  [1, 2, [3, [4, 5]]].flat(2) // [1, 2, 3, 4, 5]

  // 3.如果原数组有空位，flat()方法会跳过空位
  [1, 2, , 4, 5].flat()
  ```

- flatMap()方法对原数组的每个成员执行一个函数(相当于执行 Array.prototype.map())
  ```js
  [2, 3, 4].flatMap(x => [x, x * 2]);
  // flatMap 中不能使用嵌套数组
  [2, 3, 4, [5, 8]].flatMap(x => [x, x * 2]);
  ```

##### 10. 数组的空位

- 空位不是 undefined，一个位置的值等于 undefined，依然是有值的. 空位是没有任何值，in 运算符可以说明这一点
  ```js
  var a = Array(3); // [, , ,]
  0 in a; // false
  0 in [undefined, undefined, undefined]; // true
  ```
- ES5 对空位的处理，已经很不一致了，大多数情况下会忽略空位
  > forEach(), filter(), reduce(), every() 和 some()都会跳过空位.
  > map()会跳过空位，但会保留这个值
  > join()和 toString()会将空位视为 undefined，而 undefined 和 null 会被处理成空字符串.
- ES6 则是明确将空位转为 undefined
  > Array.from 方法会将数组的空位，转为 undefined，也就是说，这个方法不会忽略空位.
  > 扩展运算符（...）也会将空位转为 undefined
  > copyWithin()会连空位一起拷贝
  > fill()会将空位视为正常的数组位置
  > for...of 循环也会遍历空位
  > entries()、keys()、values()、find()和 findIndex()会将空位处理成 undefined
- **<font color='red'>由于空位的处理规则非常不统一，所以建议避免出现空位</font>**
#### b. 创建数组：var 或 new Array()

```js
// 创建数组
var empty = [,]     //无元素
var empty = [,,]    //有两个元素undefined
var a = new Array(length)  //length表示长度 Array(5,4,3,2, 'ok')也可以
var a[-1.23] = true;    //可以，等价于添加了一个属性
```

#### c. 数组读写

```js
o = []; //创建了空数组
o[1] = 'one'; //添加新属性来索引化
// 所有的索引都是属性名，但是只有0-2^32-2之间的属性名才是属性
```

#### d. 稀疏数组: 索引不连续的数组 [1,,2]

```js
// a) 创建
a = new Array(5);
a = [];
a[1000] = 0;
// b) delete可以删除数组元素
// c) 稀疏数组与省略元素(默认为undefined)的区别：
var a1 = [, , ,]; // 数组是[undefined, undefined, undefined]
var a2 = new Array(3); // 该数组根本没有元素
var a3 = [,]; //没有元素
0 in a1; // => true: a1在索引0处有一个元素
0 in a2; // => false: a2在索引0处没有元素
0 in a3; //=>false:a3在索引0处没有元素
```

#### e. 数组长度

1.  不同语言中的长度表示

    | first |                      s                       |
    | :---: | :------------------------------------------: |
    |  C#   |                   .Length                    |
    |  C++  |              vector<int>a(100)               |
    |  C++  |                   a.size()                   |
    |  C++  | String: strlen() //求实际长度，参数为 char[] |
    |  C++  |   Length: //实际长度 string=字符数组+'\0'    |
    |  C++  |                  Sizeof():                   |
    | java  |                length //数组                 |
    | java  |              Length() //string               |
    | java  |         size()方法是针对泛型集合说的         |
    | java  |                length //数组                 |

2.  在 JavaScript 中可以让 length 属性只读：
    ```js
    a = [1, 2, 3];
    Object.defineProperty(a, 'length', { writable: false });
    // 可以让数组元素不可配置：不能 delete
    Object.defineProperty(a, 'length', { configurable: false });
    ```

#### f. 数组元素的添加删除

```js
a = [];
a[0] = 'zero';
a = [];
a.push('zero'); //末尾加      // pop()  删除末尾元素

a = [1, 2, 3];
delete a[1];
a[1] in a; //fasle
a[1] = undefined; //true
a.length === 3; //true
//delete删除数组元素，但是不改变数组的length
```

#### g. 数组遍历

```js
// 方式一
var keys = Object.keys(o); // 获得o对象属性名组成的数组
var values = []; // 在数组中存储匹配属性的值
for (var i = 0; i < keys.length; i++) {
  // 对于数组中每个索引
  var key = keys[i]; // 获得索引处的键值
  values[i] = o[key]; // 在values数组中保存属性值
}

// 方式2
var data = [1, 2, 3, 4, 5]; // 这是需要遍历的数组
var sumOfSquares = 0; // 要得到数据的平方和
data.forEach(function(x) {
  // 把每个元素传递给此函数
  sumOfSquares += x * x; // 平方相加
});
sumOfSquares; // =>55 : 1+4+9+16+25
```

#### h. 多维数组

```js
// 创建一个多维数组
var table = new Array(10); // 表格有10行
for (var i = 0; i < table.length; i++) table[i] = new Array(10); // 每行有10列
// 初始化数组
for (var row = 0; row < table.length; row++) {
  for (col = 0; col < table[row].length; col++) {
    table[row][col] = row * col;
  }
}
// 使用多维数组来计算(查询)5*7
var product = table[5][7]; // 35
```

#### i. 数组类型

- 使用 instanceof 的问题是在 Web 浏览器中有可能有多个窗口或窗体(frame)存在. 每个窗口都有自己的 JavaScript 环境, 有自己的全局对象. 并且每个全局对象有自己的一组构造函数.
- **<font color='red'>因此一个窗体中的对象将不可能是另外窗体中的构造函数的实例. 窗体之间的混淆不常发生</font>**
- **但这个问题足已证明 instanceof 操作符不能视为一个可靠的数组检测方法.**

```js
var isArray =
  Function.isArray ||
  function(o) {
    return (
      typeof o === 'object' &&
      Object.prototype.toString.call(o) === '[object Array]'
    );
  };
```

#### k. 类数组对象

```js
数组特征:
  当添加或删除元素时, length自动更新
  设置length为一个较小的值将截断数组
  从Array.prototype中继承了一些有用的方法
  类属性是Array:
    classof<===>typeof+object.prototype.tostring.call(o).slice(8,-1)
```

#### l. 作为数组的字符串, 字符串是不可变的，用作数组是为只读

#### m. 数组方法

```js
var s = 'test';
a = s.charAt(0);
console.log(a); //t
console.log(s[1]); //e
s[1] = 5;
console.log(s); //test
```

### 8. 函数

### 9. 类、模板

### 10. 正则 Reg

### 11. JavaScript 子集与扩展

### 12. nodejs

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

### 2. 对象

```js
// 名值对的集合 map
var book = {
  topic: 'javascript',
  fat: true
};
//对象可以添加属性
book.author = '练顺';
book.topic; // 'javascript' 等价于 book['topic']
```

```js
1. 对象的分类:
    内置对象: 如数组、函数、Date()、RegExp()
    自定义对象: JavaScript 代码中定义的对象
    宿主对象: 有 JavaScript 解释器所嵌入的宿主环境(web浏览器定义的) [如HTMLElemnet]

2. 对象的创建: 直接量、new、object.create() [原型问题]

3. JavaScript 对象是动态的: 可以增加或删除属性; 除了String、number、true/fasle、null、undefined之外, JavaScript 中都是对象

4. 对象是可变的, 我们提供引用而非值来操作对象:
    x是某对象的引用; var y=x; 则y也是对对象的引用, 而非对象副本.

5. 属性的分类:
    自有属性 + 数据属性
    继承属性 + 存取器属性

6. 对象的属性:
  a) 特征(definePropeerty())
      可写性
      可枚举: 与遍历 for/in 有关, 只遍历可枚举的属性方法
      可配置: 与 delete 有关, 不可配置不可删
  b) 查询设置:  getter / setter [监视: 属性值改变时调用的回调函数]
  c) 删除: delete [可以删除自有的可配置属性, 继承属性不可删]
  d) 枚举:
  e) 继承:
  f) Error:
  g) 检测: in、hasOwnPreproty()、propertyIsEnumberable()、属性查询

7. 属性:
  a) 数据属性(自有/继承)
      名字 name
      特征
        value
        writable
        enumerable
        configurable

  b) 存取器属性
      名字 name
      特征
        value
        set [监视]
        enumerable
        configurable
  c) writable、Enumerable、configurable、writable为Boolean类型

8. 对象的三个属性:
  原型属性: 对象的原型指向另一个对象, 本对象的一些属性继承自原型属性
  类属性 classof(): 一个标识对象类型的字符串[tostring]
  可扩展性: ECMA5 中指明是否可以添加删除属性 Object.isExtensible()

9. 序列化对象: 对象与字符串的转换
// ECMA5
JSON.stringify() // toString
JSON.parse() // toJson

10. 对象的最基本方法: tostring()、toLocalString()、valueOf()、toJSON() [不是]

11. 对象的常见用法: create、set、query、delete、test、enumberable

12. 原型:
    每一个 JavaScript 对象(除 null )都和另一个对相关联;
    每一个 JavaScript 对象(除 object.prototype )都有原型

13. 描述符对象 object.getOwnPropertyDescriptor(): 实现属性特征的查询和设置.
    描述符对象的属性与他们所描述的属性特征同名: [value、writable、Enumerable、configurable]
    object.getOwnPropertyDescriptor(o,p) 读取已有的自有属性 [继承属性不可读]
```

### 3. 数组

```js
// C++ 中只有*不可以改; java 中 String 不可变, 但是改变后会出现一个新的 String.
var prime = [2, 3, 4, 5];
prime.length; //4

//数组也可以直接添加
prime[4] = 6;
// 清空数组:
prime.length = 0;

//初始化 a 数组
for(var i=0;i<a.length;a[i++]=0)
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
    数组指针: int (*a)[5]  //行指针
    []优先级高, 先与p结合成为一个数组, 再由int*说明这是一个整型指针数组, 它有n个指针类型的数组元素. 这里执行p+1时, 则p指向下一个数组元素, 这样赋值是错误的: p=a: 因为p是个不可知的表示, 只存在p[0]、p[1]、p[2]...p[n-1],而且它们分别是指针变量可以用来存放变量地址. 但可以这样 *p=a; 这里*p表示指针数组第一个元素的值, a的首地址的值.
    指针数组: int *a[5]

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

### 7. 事件处理函数:

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
// 前者是否为后者的实例, 换而言之, 是判断后者的原型对象 (prototype)是否在前者的原型链之上
function A() {}
var a = new A();
a instanceof A; // true
Object instanceof Function; // true
Function instanceof Object; // true
// 判断当前的原型对象 (prototype)是否在传入参数的原型链上面
function A() {}
var a = new A();
A.prototype.isPrototypeOf(a); // true
Object.prototype.isPrototypeOf(a); // true
Object.prototype.isPrototypeOf(A.prototype); // true
```

### 12. undefined 情景

    a) 读取不存在的属性和方法时, 返回undefined  // delete a[2] 返回 true 后, a[2] 为 undefined
    b) return;  //返回undefined
    c) 'use strict': 调用的函数(不是方法)中的this返回undefined
    d) Object.getOwnPropertyDescriptor({},  'x'/'toString')  //undefined
    e) 未定义或定义未赋值的变量

### 13. 枚举

    对象的内置[原型]方法不可枚举; 自有的属性方法可枚举

    继承属性可枚举(内置的基本方法除 toString 外), 不可删除
    方法属性的可枚举性
    一些内置属性也不可枚举 prototype
    基本类型的原型属性不可枚举[如Number、Array];

    只能删除自有的可配置属性, 不能删除继承属性和var/function/prototype等

### 14. Object.prototype 的属性

> toString
> valueof
> constructor
> hasOwnproperty
> isPrototypeof
> propertyIsEnumerable
> toLocalString

> 基本的方法
> tostring()
> toLocalString()
> valueOf()
> toJSON() [不是]

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

// 删除一个不存在的属性返回true:
delete a[20] // true

// delete 不能删除 var、function、不可配置的属性(如object.prototype)出来的对象
var a={x:1,y:1}: delete a; //false
var t = 12;
delete t //true

// delete 非左值【如1】
delete 1; //false

this.x=1;
delete this.x/x; //true,严格是会报错的

// delete: 只能删除自有的可配置属性, 不能删除继承属性, 一些内置属性不可删.
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

### 20. 'use strict'

```js
a) 只能出现在脚本化代码的开始, 函数体的开始, 任何实体语句以前: 与 eval() 使用时算是局部的上下文环境[可改不可创]
b) 在严格模式下, 禁用 with 语句, 所有变量都要声明
c) 调用的函数(不是方法)中的 this 返回 undefined:

  // 区分是否支持 ECMA5 严格模式:
  var hasStrictModel = (function() {
    'use strict';
    // 在严格时, this 返回 undefined; 在不严格时, this 返回全局对象
    return this === undefined;
  })();

d) call()、apply()中的 this 是通过 call()、apply()传入的第一个参数:
在不严格时,null/undefined 的值会被全局对象或转换为对象的非全局对象所替代
e) ···

```

```js
严格模式和非严格模式之间的区别如下(前三条尤为重要):
1. 在严格模式中禁止使用with语句.
2. 在严格模式中, 所有的变量都要先声明, 如果给一个未声明的变量、函数、函数参数、catch从句参数或全局对象的属性赋值, 将会抛出一个引用错误异常(在非严格模式中,隐式声明的全局变量的方法是给全局对象新添加一个新属性).
3. 在严格模式中, 调用的函数(不是方法)中的一个this值是undefined. (在非严格模式中, 调用的函数中的this值总是全局对象). 可以利用这种特性来判断JavaScript实现是否支持严格模式:
    var hasStrictMode =(function(){
       "use strict";
       return this===undefined}()
    );
4. 同样, 在严格模式中, 当通过call()或apply()来调用函数时, 其中的this值就是通过call()或apply()传入的第一个参数(在非严格模式中, null和undefined值被全局对象和转换为对象的非对象值所代替).
5. 在严格模式中, 给只读属性赋值和给不可扩展的对象创建新成员都将抛出一个类型错误异常(在非严格模式中, 这些操作只是简单地操作失败, 不会报错).
6. 在严格模式中, 传入eval()的代码不能在调用程序所在的上下文中声明变量或定义函数, 而在非严格模式中是可以这样做的. 相反, 变量和函数的定义是在eval()创建的新作用域中, 这个作用域在eval()返回时就弃用了.
7. 在严格模式中, 函数里的arguments对象(见8.3.2节)拥有 传入函数值的静态副本. 在非严格模式中, arguments对象具有'魔术般'的行为, arguments里的数组元素和函数参数都是指向同一个值的引用.
8. 在严格模式中, 当delete运算符后跟随非法的标识符(比如变量、函数、函数参数)时, 将会抛出一个语法错误异常(在非严格模式中, 这种delete表达式什么也没做, 并返回false). 在严格模式中, 试图删除一个不可配置的属性将抛出一个类型错误异常(在非严格模式中, delete表达式操作失败, 并返回false).
9. 在严格模式中, 在一个对象直接量中定义两个或多个同名属性将产生一个语法错误(在非严格模式中不会报错).
10. 在严格模式中, 函数声明中存在两个或多个同名的参数将产生一个语法错误(在非严格模式中不会报错).
11. 在严格模式中是不允许使用八进制整数直接量(以0为前缀, 而不是0x为前缀)的(在非严格模式中某些实现是允许八进制整数直接量的).
12. 在严格模式中, 标识符eval和arguments当做关键字, 它们的值是不能更改的. 不能给这些标识符赋值, 也不能把它们声明为变量、用做函数名、用做函数参数或用做catch块的标识符.
13. 在严格模式中限制了对调用栈的检测能力, 在严格模式的函数中, arguments. caller和arguments.callee都会抛出一个类型错误异常. 严格模式的函数同样具有caller和arguments属性, 当访问这两个属性时将抛出类型错误异常(有一些JavaScript的实现在非严格模式里定义了这些非标准的属性). 】
```

### 21. String

1. 确定一个字符串是否包含在另一个字符串中的 4 种方法

   ```js
   // indexOf(): 返回 number 小于 0, 表示不包含
   // includes(): 返回布尔值, 表示是否找到了参数字符串
   // startsWith(): 返回布尔值, 表示参数字符串是否在原字符串的头部
   // endsWith(): 返回布尔值, 表示参数字符串是否在原字符串的尾部
   let s = 'Hello world!';

   // 支持第二个参数, 表示开始搜索的位置
   let s = 'Hello world!';
   s.indexOf('e'); // 1
   s.startsWith('world', 6); // true
   s.endsWith('Hello', 5); // true
   s.includes('Hello', 6); // false
   ```

2. 字符串中嵌入变量, 需要将变量名写在 \${} 之中
   ```js
   let name = 'Bob',
     time = 'today';
   `Hello ${name}, how are you ${time}?`;
   ```

### 22. 遍历问题

#### a. Map 的遍历

-任何部署了 Iterator 接口的对象, 都可以用 for...of 循环遍历. Map 结构原生支持 Iterator 接口, 配合变量的解构赋值, 获取键名和键值就非常方便

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

#### b. Array 的遍历

```js
// 方式一
var keys = Object.keys(o); // 获得o对象属性名组成的数组
var values = []; // 在数组中存储匹配属性的值
for (var i = 0; i < keys.length; i++) {
  // 对于数组中每个索引
  var key = keys[i]; // 获得索引处的键值
  values[i] = o[key]; // 在values数组中保存属性值
}

// 方式2
var data = [1, 2, 3, 4, 5]; // 这是需要遍历的数组
var sumOfSquares = 0; // 要得到数据的平方和
data.forEach(function(x) {
  // 把每个元素传递给此函数
  sumOfSquares += x * x; // 平方相加
});
sumOfSquares; // =>55 : 1+4+9+16+25

// 方式3
for (let index of ['a', 'b'].keys()) {
 ...
}
for (let elem of ['a', 'b'].values()) {
  ...
}
for (let [index, elem] of ['a', 'b'].entries()) {
  ...
}
// 如果不使用for...of循环，可以手动调用遍历器对象的next方法，进行遍历
let letter = ['a', 'b', 'c'];
let entries = letter.entries();
console.log(entries.next().value); // [0, 'a']
```

## API

### String

#### 1. constructor

```js
  new String (s) // 构造函数, 返回的是对象
  function String (s) // 转换函数, 返回的是转换后的原始值
```

#### 2. property

```js
length; // 字符串中的字符数
```

#### 3. method

##### charAt(string) 字符串指定位置的字符

##### concat(string) 多个字符串拼接为字符串 Array.concat()

##### indexOf(string) 寻找子串

##### lastIndexOf(substring[, start]) [从指定位置开始]寻找最后一个子串

##### match(new RegExp(...)) 使用 Regex 进行匹配

```js
'1ds dsa dws dws 52 dw2 ds 45'.match(/\d+/g); // ["1", "52", "2", "45"]
```

##### replace(string/reg, string) 替换字符串, 可以使用 RegExp

##### search(string/reg) 查找字符串, 可以使用 RegExp

##### split(string/reg, limit) 使用字符串或 reg 将原字符串分割为数组 Array.join()

```js
'1:2:3:4'.split(':'); // ["1", "2", "3", "4"]
'hello'.split('', 3); // ["h", "e", "l"]
```

##### slice(start, end) 字符串的切片或子串[可以是负数] Array.slice()

##### substring(from, to) 字符串的切片或子串[可以是负数] Array.slice()

##### substr(start, length) 字符串的切片或子串[可以是负数] Array.slice()

##### toLowerCase() 将字符串转换为小写

##### toUpperCase() 将字符串转换为大写

##### trim() 去除字符串中的空格

##### toString() 返回原始的字符串值

##### valueOf() 返回原始的字符串值

##### charCodeAt() 字符串指定位置的字符的编码

##### localeCompare(string) 比较 <0>

### Array

#### 1. constructor

```js
new Array();
new Array(size);
new Array(el0, el1, el2);
```

#### 2. property

```js
length; // 数组长度
```

#### 3. method

##### every(predicate[, o]) 测试断言函数是否对每个数组元素都为真

```js
// 参数o: 调用 predicate 是可选择的 this 值
[5, 6, 7, 8].every(x => x > 2); // true

[5, 6, 7, 8].every(function(x) {
  return x > 3;
}); // true
```

##### filter(predicate[, o]) 返回满足断言函数的数组元素

```js
// 参数o: 调用 predicate 是可选择的 this 值
// predicate(array[i], i, array)
[5, 6, 7, 8].filter(x => x > 2); // [5, 6, 7, 8]
```

##### forEach(f[, o]) 为数组的每一个元素调用指定函数

```js
// f(array[i], i, array)
var a = [1, 2, 3];
a.forEach((x, i, a) => a[i]++);
```

##### some(predicate[, o]) 测试是否至少有一个数组元素能让断言函数为真

```js
[1, 2, 3].some(x => x > 2); // true
```

##### map(f[, o]) 从数组的元素中，计算出新的数组元素

```js
// a[i] = f(array[i], i, array)
[[1, 2], [3, 4]].map(([a, b]) => a + b); // [3, 7]
[[1, 2], [3, 4]].map(function([a, b]) {
  return a + b;
}); // [3, 7]
```

##### reduce(f[, initial]) 从数组的元素中，计算出一个值

```js
[1, 2, 3, 4].reduce((x, y) => x * y); // 24 => ((1*2)*3)*4
```

##### reduceRight(f[, initial]) 从右到左缩减数组

```
[1,2,3,4].reduceRight((x,y)=> x*y)  // 24 => ((4*3)*2)*1
```

##### indexOf(value[, start]) 在数组中查找匹配元素

##### lastIndexOf(value[, start]) 在数组中反向查找

##### pop() 移除并返回数组最后一个元素

##### push(value ...) 把元素添加到数组尾部并返回该元素

##### shift() 移除数组的第一个元素

##### unshift(value...) 在数组头部插入元素

##### join([separator]) 将数组的所有元素转化为字符串，并衔接起来

```js
a = new Array(1, 2, 3, 4, 'string');
s = a.join('-'); // "1-2-3-4-string"
```

##### concat(el, [el, el]) 把元素衔接到数组中

```js
var a = [1, 2, 3];
a.concat(4, [5, 6]); // [1, 2, 3, 4, 5, 6]
```

##### reverse() 在原数组中颠倒数组元素的顺序

##### sort(oderfunc) 在原数组中对数组元素进行排序

```js
[2, 5, 4, 9, 3].sort((x, y) => x - y); // [2, 3, 4, 5, 9]
```

##### slice(start[, end]) 返回数组的一部分, 可以为负数

```js
var a = [1, 2, 3, 4, 5, 6, 7, 8, 9];
a.slice(1); // [2, 3, 4, 5, 6, 7, 8, 9]
```

##### splice(start[, deleteCount, value ...]) 插入、删除或替换数组元素: 会修改原数组

```js
var a = [1, 2, 3, 4, 5, 6, 7, 8, 9];
a.splice(1, 2); // [2,3] a =>  [1, 4, 5, 6, 7, 8, 9]
a.splice(1, 0, 2, 3); // 从1号位置开始，去掉0个元素并添加[2,3] a =>  [1, 4, 5, 6, 7, 8, 9]
```

##### includes(string[, start]) Array.prototype.includes 方法返回一个布尔值，表示某个数组是否包含给定的值，与字符串的 includes 方法类似

##### toLocaleString() 将数组转化为本地化字符串

##### toString() 将数组转化为字符串
