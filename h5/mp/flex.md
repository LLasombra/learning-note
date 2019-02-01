# Flex布局元素：

[参考](http://www.runoob.com/w3cnote/flex-grammar.html)

  1. 设为Flex布局以后，子元素的float、clear和vertical-align属性将失效。 ***display : flex***;
	2. 采用Flex布局的元素，称为Flex容器（flex container），简称”容器”。
		它的所有子元素自动成为容器成员，称为Flex项目（flex item）简称”项目”。
	3. **容器的属性**：
		__flex-direction__: row(横着的，默认的) | row-reverse | column（多条线） | column-reverse;  //表示主轴的方向(项目的排列方向)
		__flex-wrap__: nowrap | wrap | wrap-reverse;  //默认情况下，项目都排在一条线（又称”轴线”）上。
						flex-wrap属性定义，如果一条轴线排不下，如何换行。
		__flex-flow__: <flex-direction> <flex-wrap>;  //flex-flow属性是flex-direction属性和flex-wrap属性的简写形式，
						默认值为row nowrap。
		__justify-content__: flex-start | flex-end | center | space-between(两端对齐，项目之间的间隔都相等) |
						space-around (每个项目两侧的间隔相等。所以，项目之间的间隔比项目与边框的间隔大一倍);
						//justify-content属性定义了项目在主轴上的对齐方式。
		__align-items__: flex-start | flex-end | center | baseline | stretch;
						//align-items属性定义项目在交叉轴上如何对齐。
		__align-content__: flex-start | flex-end | center | space-between | space-around | stretch;

	4. **项目的属性**：
		__order__: <integer>; //order属性定义项目的排列顺序。数值越小，排列越靠前，默认为0。
		__flex-grow__: <number>; // flex-grow属性定义项目的放大比例，默认为0，即如果存在剩余空间，也不放大。如果所有项目的__flex-grow属性都为1，
						则它们将等分剩余空间（如果有的话）。如果一个项目的flex-grow属性为2，其他项目都为1，则前者占据的剩余空间将比其他项多一倍。
		__flex-shrink__: <number>; //flex-shrink属性定义了项目的缩小比例，默认为1，即如果空间不足，该项目将缩小。如果所有项目的__flex-shrink属性都为1，
						当空间不足时，都将等比例缩小。如果一个项目的flex-shrink属性为0，其他项目都为1，则空间不足时，前者不缩小。负值对该属性无效。
		__flex-basis__: <length> | auto; // flex-basis属性定义了在分配多余空间之前，项目占据的主轴空间（main size）。浏览器根据这个属性，计算主轴是否有多余空间。它的默认值为auto，即项目的本来大小。
		__flex__: none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]  //flex属性是flex-grow, flex-shrink 和flex-basis的简写，默认值为0 1 auto。后两个属性可选。该属性有两个快捷值：auto (1 1 auto) 和 none (0 0 auto)。
		__align-self__: auto | flex-start | flex-end | center | baseline | stretch;  //align-self属性允许单个项目有
						与其他项目不一样的对齐方式，可覆盖align-items属性。默认值为auto，表示继承父元素的align-items属性，
						如果没有父元素，则等同于stretch。




## 注：
  **justify-content属性： 在主轴上移动**
  **align-items属性 ：在交叉轴上移动**
  **align-content属性 ： 多轴**



	上面有一层flex
  `justify-content:center;` **左右移动**
  `align-items:center;` **使水平方向在一条线上，上下移动**