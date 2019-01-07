## mp grammar
1. app.json:
    pages:
    window:
    tabBar ----> list ---> pagePath/text/iconPath/selectedIconPath:
        wx.switchTab({
          url: '/pages/list/list',
        })

2. index:
    bindgetuserinfo: 是一个回调函数，wx.getUserInfo成功获得数据传入做参数
    index 界面的思路:
        A) 在 onload 函数中调用 '获取用户信息函数: 用以是否显示 <button> {获取用户登录信息} </button>;
        B) 判断是否已经授权: 如果已经授权就不在显示 <button> {获取用户登录信息} </button>;
        C) 否则显示 <button> {获取用户登录信息} </button> ，予用户点击;
        D) 判断用户点击的是否为允许按钮: 如果是，则获取用户信息;
        E) 否则,什么都不做.

3. list:
    list 界面的思路:
        A) 获取数据, 调用 onload 函数初始化data: let datas=require('../../data/list-data.js');
        B) 使用 <swiper><swiper-item>...</swiper-item></swiper>结构做轮播图; 且在最外层设置click事件， 内部点击会向
            外面冒泡， 最后被捕捉处理[事件委托];
        C) 使用 <template></template>模板进行显示: data-index 出入参数 index; wx.for 内置了index, item参数;           <tempalte/>只需要 .wxml 和 .wxss
            <import src="/pages/template/list-template.wxml"></import>  || @import'/pages/template/list-template.wxss';
            ...
            <block wx:for="{{listArr}}" wx:key="{{index}}">
              <view catchtap='toDetail' data-index='{{index}}'>
                <template is="listTam" data="{{...item}}"/>
              </view>
            </block>

4. detail:
    detail 界面的思路: 两个功能: 音乐播放，收藏、
        收藏: 使用了 storage 记录了状态
        音乐播放: 使用了全局变量



