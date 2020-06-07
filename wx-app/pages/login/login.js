//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userName: '',
    userPwd:''
  },
  //事件处理函数
  
  onLoad: function () {
    
  },
  userNameInput:function(e)
  {
    this.setData({
      userName: e.detail.value
    })
  },
  passWdInput:function(e)
  {
    this.setData({
      userPwd: e.detail.value
    })
  },
  //获取用户输入的密码
  loginBtnClick: function (e) {
    let that = this
    console.log("用户名："+this.data.userName+" 密码：" +this.data.userPwd);

    
    wx.request({
      url: 'http://192.168.95.1:8080/employee/login', //仅为示例，并非真实的接口地址
      method:"POST",
      data: {
        num: that.data.userName,
        password: that.data.userPwd
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded' 
      },
      success (res) {
        console.log(res.data)
        if(res.data.code == 200){
          wx.setStorage({
            key:"uid",
            data:res.data.id
          })
          wx.navigateTo({
            url: '/pages/signIn/signIn?name='+res.data.name+"&department="+res.data.department,
            
          })
        }
      },
    })

  }
  
})
