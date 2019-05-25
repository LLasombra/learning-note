## JQuery 笔记

### 注意点

1. `text() 可以获取除带 value 属性外的**所有**东西`; .val() `只可以`获取带有 value 属性的东西
   > .val( function(index, oldvalue) ) return 设置文本内容的一个函数。index 可选。接收选择器的 下标 位置；oldvalue 可选。接收选择器的当前内容; **.val()是用来读取表单元素的"value"值**
   > .html(): 获取集合中第一个匹配元素的 HTML 内容. 这个函数不能用于 XML 文档。但可以用于 XHTML 文档; **.html()是用来读取元素的 html 内容（包括 html 标签）**
   > .text(): 获取匹配元素集合中每个元素的合并文本内容, 包括他们的后代。这个方法对 HTML 和 XML 文档都有效; **.text()用来读取元素的纯文本内容, 包括其后代元素**
   > .val(): 获取匹配的元素集合中的第一个元素的当前值
   > .html( htmlString ): 设置每一个匹配元素的 html 内容.
   > .text( textString ): 设置匹配元素内容的文本; 　
   > .val(value): 设置匹配的元素集合中的每个元素的值
   ```js
   // 即便是为一组 radio 赋值,val 参数中也应该使用数组.使用一个值不起作用。
   // val 只能能直接获取 checkbox 被选择的第一个值值
   // val() 可以直接获取 select 的被选择的值.
   ```
2. 使用 `is()` 方法, 来判断某个给定的 jQuery 对象是否符合指定的 `选择器`.

   ```js
   var flag = $('.content').is(':hidden');
   ```

3. 以下代码出现的是剪切效果
   ```js
   var div1 = document.getElementById("div1");
   var ul1 = document.getElementById("ulid");
   div1.appendChild(ul1);
   // 3.1创建元素$():
   $(html); $('<p/>') 或 $('<p></p>')
   // 3.2 插入元素(7):
   $("<li id='atguigu'>尚硅谷</li>").appendTo($("#city"));
   $( "#city").append("<li id='atguigu'>[尚硅谷]</li>");
   // 放在最前
   $("<li id='atguigu'>尚硅谷</li>").prependTo($("#city"));
   $("#city").prepend("<li id='atguigu'>[尚硅谷]</li>");
   // 插入指定节点的后面
   $("<li id='atguigu'>尚硅谷</li>").insertAfter($("#bj"));
   $("#bj").after("<li id='atguigu'>[尚硅谷]</li>");
   // 插入指定节点的前面
   $("<li id='atguigu'>尚硅谷</li>").insertBefore($("#bj"));
   // 3.3删除元素(2):
   remove():
   empty():  // 清空节点 – 清空元素中的所有后代节点(不包含属性节点).
   // 3.4元素的复制:
   clone() // 克隆返回值为克隆后的副本. 但此时复制的新节点不具有任何行为.
   clone(true) // 复制元素的同时也复制元素中的的事件
   // 3.5元素的替换: 剪切的效果; 注意: 若在替换之前, 已经在元素上绑定了事件, 替换后原先绑定的事件会与原先的元素一起消失
   replaceWith()// 将所有匹配的元素都替换为指定的 HTML 或 DOM 元素
   replaceAll() // 颠倒了的 replaceWith() 方法.
   // 3.6包裹节点:
   wrap() // 将指定节点用其他标记包裹起来. 该方法对于需要在文档中插入额外的结构化标记非常有用, 而且不会破坏原始文档的语义.
   wrapAll() // 将所有匹配的元素用一个元素来包裹. 而 wrap() 方法是将所有的元素进行单独包裹.
   wrapInner() // 将每一个匹配的元素的子内容(包括文本节点)用其他结构化标记包裹起来.
       //包装 li 本身
       $("#game li").wrap("<font color='red'></font>");
       //包装所有的 li
       $("#city li").wrapAll("<font color='red'></font>");
       //包装 li 里边的文字.
       $("#language li").wrapInner("<font color='red'></font>");
   // 3.7属性操作:
   attr() attr(), html(), text(), val(), height(), width(), css()
   removeAttr() //删除指定元素的指定属性
   // 3.8DOM属性:
   opacity() // 属性表示透明度
   offset() // 当前视窗中的相对位移
   ```

### 选择器

1.  Id 选择器: \$('#id')
2.  标签选择器: \$('p')
3.  类选择器: \$('.class')
4.  `标签 + 类选择器`: \$('p.class')
5.  组合选择器: \$('选择器 1,选择器 2,选择器 3~~~')
6.  层次选择器:

    - 后代元素选择器: 使用 `` 开个隔开
      ```js
      `$("p").find("span:first")``$('选择器 1 选择器 2'): 表示选取选择器 1 下的所有选择器 2 所有元素`;
      ```
    - 子元素选择器: 使用 `>` 隔开

      ```js
      $("ul li:nth-child(2)")
      $("div").children(".selected")
      $("form > input")//不应是第一个子元素, 取第一个 出现的
      $('选择器 1>选择器 2'):表示选取满足选择器 2 的子元素
      ```

    - 相邻元素选择器: 使用 `+` 隔开

      ```js
      $('选择器1+选择器2'): 【next('选择器2')】 表示选择器1选取到的元素的后一个元素, 且满足选择器2
      $('选择器 1~选择器 2'): 【.nextAll('选择器 2')】 表示选择器 1 选取的元素后面的所有满足选择器 2 的兄弟元素
      ```

    - 方法:
      > nextAll()
      > prev()
      > prevAll()
      > siblings()

7.  与 DOM 相关的操作:

    ```
    1. 文本节点的操作: text()
    2. 属性节点的操作: attr()
    3. 创建新的节点: 函数 $(): $(html);
    4. 可以使用 $('<p/>') 或 $('<p></p>'): 只有这两种
    5. 节点的插入:
    ```

    - 节点的创建插入

      ```js
      2.1.插进来作为子节点: appendTo 和 append: 主语和宾语的位置不同:
      // 放在最后
      $("<li id='atguigu'>尚硅谷</li>").appendTo($("#city"));
      $("#city").append("<li id='atguigu'>[尚硅谷]</li>");
      // 放在最前
      $("<li id='atguigu'>尚硅谷</li>").prependTo($("#city"));
      $("#city").prepend("<li id='atguigu'>[尚硅谷]</li>");

      2.2插入指定的位置:
      //插入指定节点的后面
      $("<li id='atguigu'>尚硅谷 </li>").insertAfter($("#bj"));
      ("#bj").after("<li id='atguigu'>[尚硅谷]</li>");
      //插入指定节点的后面
      //$("<li id='atguigu'>尚硅谷</li>").insertBefore($("#bj"));
      ```

    3. \$("#bj").remove(); //移除对象
    4. jQuery 对象的 empty() 方法: 清空 jQuery 对象对应的

### 与链式编程相关:

```js
1.有些方法是会破坏链式编程中的返回对象的:
next(), nextAll(), prev(), prevAll(), Siblings(), text(), val(), html()
2.但是这些都可以通过.end()方法修复链
```

### Jquery 对象的常用方法:

```js
0. $("p").find("span") //$('p span')
1. $('p').css({'key':'vlaue', 'key':'vlaue'))
2. $('p').text/val('value') //innerText/textContent:
    //注: 直接操作 value 属性值可以使用更便捷的 val() [这里文本输入只能使用val]方法.
        text中有值为设置, 无值为读取
3.  $('p').attr('key','value'): 有value为设置；没有为读取
    $('p').prop('key','value'): 有value为设置；没有为读取
4.  $.map()
    $.each()
    $.trim()

5. $('p').hasClass('CLASSNAME')	//判断是否有这个class的样式
    $('p').removeClass('CLASSNAME')//移除class的样式
    $('p').addClass('CLASSNAME')//添加Class的样式
    $('p').toggleClass('CLASSNAME')//有就移除没有就添加

6. 基本过滤选择器:
    first
    last //$('p:last')
    lt(5) //索引小于5
    gt(5) //索引大于5 //$('p:gt(5)')
    eq(5)
    header //h1……h6
    not (选择器1)//除…所有元素 //$('p:not(#id)')  //$('p :not(#id1)')//与上一个不一样
    even //索引为奇数 $('p:even')
    odd //索引为偶数 $('p:odd')

7. 内容过滤选择器:
    contains(text)  // 选取文本内容为 text 的元素
        $("div:contains('John')")
    empty //选取不包含子元素或文本的空元素
        $("td:empty")
    has(selector) //选取含有选择器所匹配的元素的元素
        $("div:has(p)").给所有包含 p 元素的 div 元素
    parent //选取含有子元素或文本的元素
        $("td:parent")	//查找所有含有子元素或者文本的 td 元素
    选取有name属性且name值为TXT1的input元素:
        $('input[name=txt1]')
    选取body下的所有带有name属性的元素:
        $('body *[name]')
    选取body下的所有带有name属性的且以a开头的元素:
        $('body *[name^=a]')
    选取body下的所有带有name属性的且以a结尾的元素:
        $('body *[name$=a]')
    选取body下的所有带有name属性的且包含a的元素:
        $('body *[name*=a]')
    选取body下的所有带有name属性的且name值为空的元素:
        $('body *[name=]')
    选取body下的所有带有name属性的且不等于zhang的元素:
        $('body *[name!=zhang]')
    同时包含具有多个属性的元素
        $('body *[name*a][id][value]')
    选取div下未被选中的元素:
        $('input:disabled')/$('input:enable')//(非)禁用的input
        $('div :not(:checked)')
    获取被选中的checkedbox元素:
        $('input[type=checkbox]:not(:checked)')
    获取文本框中的值
        $("input").val();
    将不可见的div, 可见
        $("div:hidden").show(2000).css("background", "#ffbbaa");

9. 合成事件bind、unbind/ hover、toggle、one() {function(){}}//只执行一次
    $(".head").bind("click", function(){}) // 等价于 $(".head").click(function(){})
    //mouseover: 鼠标移上去
    //mouseout: 鼠标移出.
    $(".head").mouseover(function(){
        $(".content").show();
    }).mouseout(function(){
        $(".content").hide();
    });

    //合成事件:hover: 鼠标移上去执行第一个函数, 移出执行第二个函数.
    $(".head").hover(function(){
        $(".content").show();
    }, function(){
        $(".content").hide();
    });

    // 合成事件: toggle: 第一次点击执行第一个函数, 第二次点击执行第二个
    //函数 ... 循环执行.
    $(".head").toggle(function(){
        $(".content").show();
    }, function(){
        $(".content").hide();
    });

10. Jquery中的动画:
    //会同时减少(增大)内容的高度, 宽度和不透明度.
    show(1000)
    hide(200)//时间

    //只改变元素的透明度
    fadeOut():降低元素的不透明度, 直到元素完全消失
    fadeIn()

    //只会改变元素的高度
    slideDown()
    slideUp():元素由下至上缩短隐藏

    //其他
    toggle():切换元素的可见状态:
    slideToggle(): 通过高度变化来切换匹配元素的可见性.
    fadeTo(): 把不透明度以渐近的方式调整到指定的值(0 – 1 之间).
```

### DOM 中 Node 节点的常用方法:

|             method             |    return    |                                 desc                                  |
| :----------------------------: | :----------: | :-------------------------------------------------------------------: |
|            nodeName            |    String    |                   节点的名字；根据节点的类型而定义                    |
|           nodeValue            |    String    |                    节点的值；根据节点的类型而定义                     |
|            nodeType            |    Number    |                         节点的类型常量值之一                          |
|         ownerDocument          |   Document   |                        指向这个节点所属的文档                         |
|           firstChild           |     Node     |                 指向在 childNodes 列表中的第一个节点                  |
|           lastChild            |     Node     |                指向在 childNodes 列表中的最后一个节点                 |
|           childNodes           |   NodeList   |                           所有子节点的列表                            |
|           parentNode           |     Node     |                       返回一个给定节点的父节点                        |
|        previousSibling         |     Node     |  指向前一个兄弟节点；如果这个节点就是第一个兄弟节点, 那么该值为 null  |
|          nextSibling           |     Node     | 指向后一个兄弟节点；如果这个节点就是最后一个兄弟节点, 那么该值为 null |
|        hasChildNodes()         |   Boolean    |              当 childNodes 包含一个或多个节点时, 返回真               |
|        attributes(node)        | NamedNodeMap |       包含了代表一个元素的特性的 Attr 对象；仅用于 Element 节点       |
|          appendChild           |     Node     |                   将 node 添加到 childNodes 的末尾                    |
|       removeChild(node)        |     Node     |                       从 childNodes 中删除 node                       |
| replaceChild (newnode,oldnode) |     Node     |               将 childNodes 中的 oldnode 替换成 newnode               |
| insertBefore(newnode,refnode)  |     Node     |              在 childNodes 中的 refnode 之前插入 newnode              |
