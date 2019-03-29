# 小程序生成二维码 并通过小程序自带扫码扫描

标签（空格分隔）： 问题总结

---

小程序生成的二维码一共有三个接口 [官方文档](https://developers.weixin.qq.com/miniprogram/dev/api/qrcode.html?search-key=%E4%BA%8C%E7%BB%B4%E7%A0%81)

因为接口B的限制较少，所以采用了这个。
跳坑记录，接口B生成小程序码时需要填写的page参数（页面路径），但是这个页面路径需要是已经发布版本的页面路径。也就是需要先发布再测试。逼si强迫症！～(对于未发布的小程序可以参考下面的数据格式，亲测成功。)
调用方式。
![调用](http://p7x92ioyq.bkt.clouddn.com/apiBGet.png)

>###通过wx.scancode 扫描接口B生成的二维码返回的结果如下图：

![返回结果](http://p7x92ioyq.bkt.clouddn.com/111111.png)

拿到了返回结果，可以看到我们需要的路径，以及所带的scene参数就在path参数里。这就可以接着写逻辑操作

```
#调用小程序自带的扫码api 扫上面通过接口B生成的二维码，res.path就是我们需要的参数
  scanCode: function () {
    wx.scanCode({
      onlyFromCamera: true,
      success: function (res) {
        console.log('扫码成功', res)
        if (res.path) {
          wx.navigateTo({
            url: '/' + res.path
          })
        }
      },
      fail: function (res) {
          wx.showToast({
            title: '暂不支持该二维码',
            icon: 'none',
            duration: 2000
          })
       },
      complete: function (res) { },
    })
  }
```

>###在扫码跳转的成功页，也就是我的detail页面，通过下面的方式获取到参数。
```
  onLoad: function (options) {
    if (options.scene) {
      let sku = decodeURIComponent(options.scene);
  }
```


