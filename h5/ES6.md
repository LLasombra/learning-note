## ES6
 ### Promise
  * [参考](https://www.jianshu.com/p/c613c0198430)
  * [await/async](https://www.jb51.net/article/123257.htm)
  * new Promise(function(resolve, reject) {...})
  * **let promises** = await Promise.all([promise1, promise2]) **结果在promises中**
  * await Promise.all(promises).then(result => { ... })

 ### 知识点
  * promise的3种状态，未完成（unfulfilled）、完成（fulfilled）、失败（failed
  * promise的状态只能从 未完成->完成, 未完成->失败 且状态不可逆转
  * **实例化的Promise内部会立即执行，then方法中的异步回调函数会在脚本中所有同步任务完成时才会执行**
  * resolve中可以接受另一个promise实例: promise实例内部的resolve也执行的是异步回调，所以不管resolve放的位置靠前还是靠后，都要等内部的同步函数执行完毕，才会执行resolve异步回调
  * promise.all()方法可以处理一个以promise实例为元素的数组:
  `promise 的状态由p1，p2，p3共同决定。当它们都为resolve状态时，promise状态为true，它们的返回值组成一个数组，传递给promise；它们只要有一个的状态为reject，就将该实例的返回值传递给promise`
  * promise.race()方法也可以处理一个promise实例数组
  `但它和promise.all()不同，从字面意思上理解就是竞速，那么理解起来上就简单多了，也就是说在数组中的元素实例那个率先改变状态，就向下传递谁的状态和异步结果`
  * 应用
  `由于两个或多个$.ajax()间是同步的，如果我们并排着写实现不了依赖关系，所以，我们往往使用嵌套，但是对于ajax这样复杂的结构，嵌套不是个好办法，我们需要先将代码抽象提取，做一下封装，`

 ### 示例
  **demo1**
  ```js
  var promise = new Promise(function(resolve, reject){
      console.log('............');
      resolve(); // 这是promise的一个机制，只有promise实例的状态变为resolved，才会会触发then回调函数
  });

  promise.then(function(){
      for(var i=0;i<100;i++) {
          console.log('c')
      }
  })
  .then(function(){
      console.log('b')
  })
  .then(function(){
      console.log('a')
  })
  ```

  **应用demo1**
   ***最初的样子***
  ```js
  /*
  url：地址
  data：数据，在函数内部会转化成json。如果没传，表示用GET方法；如果传了，表示用POST方法
  */
  function ajax(url, data, callback) {
      $.ajax({
        url: url,
        type: data == null ? 'GET' : 'POST',
        dataType: "json",
        data: data == null ? '' : JSON.stringify(data),
        async: true,
        contentType: "application/json",
        success: function (data) {
            callback(data);
        },
        error: function (XMLHttpRequest, textStatus) {
          if (XMLHttpRequest.status == "401") {
              window.parent.location = '...';
              self.location = '...';
          } else {
              alert(XMLHttpRequest.responseText);
          }
        }
      });
  }
  ```
  **====>抽象成一个方法+promise**
  ```js
  function ajax(url, data, callback) =>
      new Promise((resolve, reject) => {
          $.ajax({
              url: url,
              type: data == null ? 'GET' : 'POST',
              dataType: "json",
              data: data == null ? '' : JSON.stringify(data),
              async: true,
              contentType: "application/json",
              success: function (data) {
                  callback(data);
                  resolve();
              },
              error: function (XMLHttpRequest, textStatus) {
                  if (XMLHttpRequest.status == "401") {
                      window.parent.location = '...'
                      self.location = '...'
                  } else {
                      alert(XMLHttpRequest.responseText);
                  }
                  reject()
              }
          })
      })
  }
  ```
  **===>使用**
  ```js
  $.ajax({
      url:'./a'
  }).then(function(){
      return $.ajax({ url:'./b' });
  }).then(function(){
      return $.ajax({ url:'./c' });
  }).then(function(){
      return $.ajax({ url:'./d' });
  }).then(function(){
      //TODO here
  });
  ```


**应用demo2**
 ***小程序中的示例***
 ```js
 // 封装抽象方法
 const promisify = name => {
    return (param, ...args) => {
      return new Promise((resolve, reject) => {
        wx[name]({
          ...param,
          success: res => {
            resolve(res)
            param.success && param.success(res)
          },
          fail: res => {
            reject(res)
            param.fail && param.fail(res)
          }
        })
      }, ...args)
    }
  }

  // 网络路径转换为临时路径
  const netImgUrl2localImgUrl = async imgNetUrls => {
    let type = Object.prototype.toString.call(imgNetUrls)
    if (type === '[object Array]') {
      let promises = []
      let getImageInfo = promisify('getImageInfo')
      for (let i = 0; i < imgNetUrls.length; i++) {
        promises[i] = getImageInfo({
          src: imgNetUrls[i]
        })
      }
      let imgLocalUrls = []
      await Promise.all(promises).then(result => {
        for (let i = 0; i < result.length; i++) {
          imgLocalUrls.push(result[i].path)
        }
      })
      return imgLocalUrls
    }
    return []
  }

  // 使用
  async methodsxx () {
    // 临时路径
    let imgLocalUrls = await netImgUrl2localImgUrl(imgNetUrls)
  }
  ```


