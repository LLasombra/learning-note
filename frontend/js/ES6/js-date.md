# Js获取当前日期

标签（空格分隔）： 技术总结

---
##获取格式为(yyyy-MM-dd)的日期
```js
// 获取当前日期
var date = new Date();
// 获取当前月份
var nowMonth = date.getMonth() + 1;
// 获取当前是几号
var strDate = date.getDate();
// 添加分隔符“-”
var seperator = "-";
// 对月份进行处理，1-9月在前面添加一个“0”
if (nowMonth >= 1 && nowMonth <= 9) {
   nowMonth = "0" + nowMonth;
}
// 对月份进行处理，1-9号在前面添加一个“0”
if (strDate >= 0 && strDate <= 9) {
   strDate = "0" + strDate;
}
// 最后拼接字符串，得到一个格式为(yyyy-MM-dd)的日期
var nowDate = date.getFullYear() + seperator + nowMonth + seperator + strDate;
```
## 获取时间戳
```php
#获取当前时间的时间戳(毫秒数)
new Date().getTime()
#获取指定时间的时间戳(两种格式都是一样的)
new Date("2018/12/02 12:12:12").getTime()
new Date("2018-12-02 12:12:12").getTime()
```

