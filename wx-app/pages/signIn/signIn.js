// pages/signIn/signIn.js
var bmap = require('../../libs/bmap-wx.min.js');
var wxMarkerData = [];

Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:"",
    department:"",
    show: true,
    showdown: false,
    showdown2: false,
    down: false,
    down2: false,
    m: "",
    h: "",
    s: "",
    officeTime: "1",
    downTime: "",
    longitude: '', //经度  
    latitude: '', //纬度  
    address: '', //地址  
    ak: "8gd9Zrt20E78sw64ybBibkCXqtNAGCvL",
    id:""

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    this.setData({
      name:options.name,
      department:options.department
    })

     this.startTime()
  },

  startTime() {

    var that = this

    var today = new Date()
    var h = today.getHours()
    var m = today.getMinutes()
    var s = today.getSeconds()
    // add a zero in front of numbers<10
    m = checkTime(m)
    s = checkTime(s)
    setTimeout(function () {

      that.startTime();

    }, 1000);

    this.setData({
      m,
      s,
      h
    })

    function checkTime(i) {
      if (i < 10) {
        i = "0" + i
      }
      return i
    }
  },

  // 获取地理位置信息
  getAddress() {
    var that = this;
    /* 获取定位地理位置 */
    // 新建bmap对象   
    var BMap = new bmap.BMapWX({
      ak: that.data.ak
    });
    var success = function (data) {
      // console.log(data)
      that.setData({
        address: data.wxMarkerData[0].address +" "+ data.wxMarkerData[0].desc
      })
    }
    // 发起regeocoding检索请求   
    BMap.regeocoding({
      success: success
    });


  },
  
  // 上班打卡

  showM() {
    var that = this
    wx.showToast({
      title: '上班打卡成功',
      icon: 'success',
      duration: 2000
    })
    this.setData({
      show: false,
      down: true,
      down2: true
    })
    let timeStr = this.data.h + ":" + this.data.m
    this.setData({
      officeTime: timeStr
    })

    var BMap = new bmap.BMapWX({
      ak: that.data.ak
    });
    var success = function (data) {
      // console.log(data)
      that.setData({
        address: data.wxMarkerData[0].address +" "+ data.wxMarkerData[0].desc
      })


      wx.getStorage({
        key: 'uid',
        success (res) {
          console.log(that.data.address)
          wx.request({
            url: 'http://192.168.95.1:8080/attendance/insert',
            method:"POST",
            header: {
              'content-type': 'application/x-www-form-urlencoded' // 默认值
            },
            data:{
              eid:res.data,
              workup:that.data.officeTime,
              workdown:"",
              upaddress:that.data.address,
              downaddress:"",
              worktime:that.getTime(),
              status:2
            },
            success(obj){
             
              that.setData({
                id:obj.data.id
              })
            }
          })
        }
      })

    }
    // 发起regeocoding检索请求   
    BMap.regeocoding({
      success: success
    });
   

  

  },

  getTime(){
    let t= new Date();
    let y = t.getFullYear();
    let m = t.getMonth()+1;
    let d = t.getDate()
    return y+"-"+m+"-"+d
  },

  // 下班打卡
  showD() {
    let that = this

    wx.showToast({
      title: '下班打卡成功',
      icon: 'success',
      duration: 2000
    })
    let timeStr = this.data.h + ":" + this.data.m
    this.setData({
      showdown: true,
      down2: false,
      downTime: timeStr
    })

    // this.getAddress()
    var BMap = new bmap.BMapWX({
      ak: that.data.ak
    });
    var success = function (data) {
      // console.log(data)
      that.setData({
        address: data.wxMarkerData[0].address +" "+ data.wxMarkerData[0].desc
      })
     
      wx.getStorage({
        key: 'uid',
        success (res) {
          
          wx.request({
            url: 'http://192.168.95.1:8080/attendance/insert',
            method:"POST",
            header: {
              'content-type': 'application/x-www-form-urlencoded' // 默认值
            },
            data:{
              id:that.data.id,
              eid:res.data,
              workup:that.data.officeTime,
              workdown:that.data.officeTime,
              upaddress:that.data.address,
              downaddress:that.data.address,
              worktime:that.getTime(),
              status:0
            },
            success(obj){
                console.log(obj)
            }
          })
        }
      })
    }
    // 发起regeocoding检索请求   
    BMap.regeocoding({
      success: success
    });
 

  }


})