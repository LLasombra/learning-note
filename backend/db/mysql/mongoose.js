var mongoose = require('mongoose');
// 1. 指定数据库名称、链接
mongoose.connect('mongodb://127.0.0.1:27017/mongoose_test', {
  useNewUrlParser: true
});

// 2. 数据库连接的监听事件
mongoose.connection.once('open', function() {
  console.log('数据库连接成功~~~');
});
mongoose.connection.once('close', function() {
  console.log('数据库连接已经断开~~~');
});
mongoose.disconnect();
