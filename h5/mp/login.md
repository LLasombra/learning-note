1. 获取用户信息：wx.getUserInfo
    判断用户是否已经授权: wx.getSetting 方法中res.authSetting['scope.userInfo']属性是否为空
        没有授权的话，出现是否授权的按钮，判断用户点击的是什么[是/否](bindgetuserinfo='handleGetUserInfo'回调函数的 data.detail.rawData是否为空)，并获取用户信息；
        已经授权的话
    直接使用用户信息;


2. template的使用：xxx.wxml[使用import标签引入]/xxx.wxss[使用 @import 引入]
    数据和样式的引入是在使用template的xxx.wxml和xxx.wxss中；
    template需要有一个name属性；
    wx:for中使用：默认会有index，每个元素为item [...item是把对象解析传入，template内部直接使用其属性]
        <block wx:for="{{listArr}}" wx:key="{{index}}">
            <view >
                <template is="listTam" data="{{...item}}"/>
            </view>
        </block>


3. 收藏功能:
    图片的转换
    数据的存储:
        ```java
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

4. 音乐播放 : const backgroundAudioManager = wx.getBackgroundAudioManager();
    两个控制方式+数据的一次存储
    界面上控制和后台控制 + 记录当前的页面播放状态[只需要记录播放的那个状态(pageIndex + isPlay)]: 因为进入其他页面都是没有播放的默认状态
