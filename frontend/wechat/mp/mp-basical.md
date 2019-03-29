## mp grammar
  + **1. app.json:**
    ```js
    pages:
    window:
    tabBar ----> list ---> pagePath/text/iconPath/selectedIconPath:
    wx.switchTab({
    url: '/pages/list/list',
    })
    ```

  + **2. index:**
     * bindgetuserinfo: 是一个回调函数，wx.getUserInfo成功获得数据传入做参数
        * 判断用户是否已经授权: wx.getSetting 方法中res.authSetting['scope.userInfo']属性是否为空
        * 没有授权的话，出现是否授权的按钮，判断用户点击的是什么[是/否](bindgetuserinfo='handleGetUserInfo'回调函数的  data.detail.rawData是否为空)，并获取用户信息；
        * 已经授权的话直接使用用户信息;
     * index 界面的思路:
        * 在 onload 函数中调用 '获取用户信息函数: 用以是否显示 <button> {获取用户登录信息} </button>;
        * 判断是否已经授权: 如果已经授权就不在显示 <button> {获取用户登录信息} </button>;
        * 否则显示 <button> {获取用户登录信息} </button> ，予用户点击**wx.getUserInfo**;
        * 判断用户点击的是否为允许按钮: 如果是，则获取用户信息;
        * 否则,什么都不做.

  + **3. list:**
     **list 界面的思路:**
      * 获取数据, 调用 onload 函数初始化 data : **let datas=require('../../data/list-data.js');**
      * 使用 ``` <swiper><swiper-item>...</swiper-item></swiper> ``` 结构做轮播图; 且在最外层设置click事件， 内部点击会向外面冒泡， 最后被捕捉处理[**事件委托**];
      * 使用 <template></template>模板进行显示: ***data-index 出入参数 index; wx.for 内置了index, item参数;<tempalte/>只需要 .wxml 和 .wxss***
        ```html
        <import src="/pages/template/list-template.wxml"></import>  || @import'/pages/template/list-template.wxss';
        ...
        <block wx:for="{{listArr}}" wx:key="{{index}}">
            <view catchtap='toDetail' data-index='{{index}}'>
            <template is="listTam" data="{{...item}}"/>
            </view>
        </block>
        ```

  + **4. detail:**
      * **收藏: 使用了 storage 记录了状态, 数据的存储:**
        ```js
        wx.setStorageSync("isCollected",{});  // 如果detailStorage 为 false，则先set一下
        wx.getStorage({
            key:"isCollected",  //否则这里会出现null
            success:(datas)=>{
                let obj=datas.data;
                obj[index]=isCollected;  //{"1":true}
                wx.setStorage({
                    key : "isCollected",
                    data : obj,
                })
            },
        })
        ```
      * **音乐播放: 使用了全局变量**
        * 音乐播放 :
          ```js
          const backgroundAudioManager = wx.getBackgroundAudioManager();
          ```
        * 两个控制方式+数据的一次存储
        * 界面上控制和后台控制 + 记录当前的页面播放状态[只需要记录播放的那个状态(pageIndex + isPlay)]: 因为进入其他页面都是没有播放的默认状态



