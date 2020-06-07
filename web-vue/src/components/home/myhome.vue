<template>
  <div class="main">
    <div class="title">
      <div class="left">
        <span>欢迎您！</span>
        <span style="font-size:22px">{{name}}</span>
        <span style="font-size:18px;color:#333">({{department}})</span>
      </div>
     
    </div>


    <div style="display:flex; justify-content: space-between;">
      
   <div>
      <div class="content-top"> 
      <div class="box">
        <div class="box-title">
          <span>个人信息</span>
          <span  style="font-size:14px;margin-right:20px" @click="goMyinfo()">more>></span>
        </div>
        <div class="box-content">
          <p>{{getMyTime()}}，{{name}}</p>
          <!-- <p>签到成功。</p> -->
           <p  v-if="!role"   class="dd"   :class="[desc == '打卡异常' ? 'red' : 'dd']" > 滴~{{desc}}</p>

            <p v-else class="dd"> 祝您拥有美好的一天 ^_^</p>

        </div>
        <!-- <div class="box-red">今天上午10点，您有一个会议要召开，不要忘记啦。</div> -->
      </div>
 
    </div>
     <div class="noticeBox">
        <div class="box-title">
          <span>公告栏</span>
          <span @click="goNotice()" class="more">more>></span>
        </div>
        <div class="box-content-gg">
              <ul v-for="item in noticeList" :key = item.id>
                <li>{{item.title}}</li>
              </ul>
        </div>
      </div>
   </div>

      <div style="width:700px;height:400px">
            <el-calendar v-model="value" >
  <template
    slot="dateCell"
    slot-scope="{date, data}">
    <p :class="data.isSelected ? 'is-selected' : ''">
      {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : ''}}
    </p>
  </template>
         </el-calendar>
      </div>
    </div>

  
  </div>
</template>
<style scoped  lang="less">



  .is-selected {
    color: #1989FA;
  }
.more{
  cursor: pointer;
}
.main {
  // width: 90%;
   overflow-y: hidden;
  
  margin-left: 40px;
  margin-top: 95px;
  position: relative;
 
}
.title {
  display: flex;
  right: 5%;
  // justify-content: space-between;
  color: #000000;
  .left {
    :first-child {
      font-size: 30px;
    }
    :last-child {
      margin-left: 12px;
      font-size: 24px;
    }
  }
  .right {
    font-size: 18px;
    display: flex;
    align-items: center;
    position: absolute;
    right: 5%;
  }
}
.noticeBox {
    // margin-left: 10px;
     overflow: hidden;
    height: 250px;
    width:350px;
    margin: 15px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px 0 rgba(156, 148, 148, 0.2), 0 6px 20px 0 rgba(150, 144, 144, 0.19);
    .box-title {
      margin-left: 30px;
      margin-top: 15px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      color: blue;
      :first-child {
        font-size: 18px;
      }
      :last-child {
        font-size: 14px;
        margin-right: 20px;
      }
    }
     .box-content-msg{
      margin-left: 30px;
      font-size: 16px;
      margin-top: 5px;
      color: #000000;
      ul{
        margin-left: 20px;
        li{
           list-style:disc;
            margin-right: 10px;
           margin-top: 2px;
        }
      }
    }
    .box-content-gg{
      margin-left: 30px;
      font-size: 14px;
      margin-top: 5px;
      color: #000000;
      ul{
       
        margin-left: 20px;
        li{
           list-style:disc;
           margin-top: 2px;
        }
      }
    }
    .box-content {
      margin-left: 30px;
      :first-child {
        font-size: 18px;
        margin-top: 10px;
      }
      :last-child {
        font-size:204px;
        margin-right: 20px;
      }
    }
    .box-red {
      margin-left: 30px;
      margin-top: 15px;
      color: red;
      font-size: 14px;
      width: 200px;
      font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif
    }
  }
.content-top {
  
  // margin-top: 15px;
  // display: flex;
  // width: 100%;
 
  .box {
    // margin-left: 10px;
     overflow: hidden;
    height: 250px;
    width:350px;
    margin: 15px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px 0 rgba(156, 148, 148, 0.2), 0 6px 20px 0 rgba(150, 144, 144, 0.19);
    .box-title {
      margin-left: 30px;
      margin-top: 15px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      color: blue;
      :first-child {
        font-size: 18px;
      }
      
     
    }
     .box-content-msg{
      margin-left: 30px;
      font-size: 16px;
      margin-top: 5px;
      color: #000000;
      ul{
        margin-left: 20px;
        li{
           list-style:disc;
            margin-right: 10px;
           margin-top: 2px;
        }
      }
    }
    .box-content-gg{
      margin-left: 30px;
      font-size: 14px;
      margin-top: 5px;
      color: #000000;
      ul{
       
        margin-left: 20px;
        li{
           list-style:disc;
           margin-top: 2px;
        }
      }
    }
    .box-content {
      margin-left: 30px;
      :first-child {
        font-size: 18px;
        margin-top: 10px;
      }
      .dd{
         margin-top: 10px;
        font-size: 20px;
        color: #333;
      }
      .red{
         margin-top: 10px;
         font-size: 20px;
         color: red;
      }
      .green{
         margin-top: 10px;
        font-size: 20px;
        color: green;
      }
      // :last-child {
      //   margin-top: 10px;
      //   font-size: 16px;
      //   width: 230px;
      //   color: #000000;
      // }
    }
    .box-red {
      margin-left: 30px;
      margin-top: 15px;
      color: red;
      font-size: 14px;
      width: 200px;
      font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif
    }
  }
}
.content-bottom{
  margin-top: 15px;
  display: flex;
  width: 100%;
  .lang-box{
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px 0 rgba(156, 148, 148, 0.2), 0 6px 20px 0 rgba(150, 144, 144, 0.19);
    width: 62%;
    height: 200px;  
    margin: 15px;
    h3{
      color: blue;
      margin-left: 30px;
      margin-top: 20px;
      font-size: 18px
    }
    div{
      margin-left: 40px;
      ul{
        li{
          list-style:disc;
            margin-right: 10px;
           margin-top: 10px;
           font-size: 16px;
        }
      }
    }
  }
  .short-box {
    // margin-left: 10px;
    height: 250px;
    width: 30%;
    border: 1px solid #ccc;
    border-radius: 10px;
    margin: 14px;
    box-shadow: 0 4px 8px 0 rgba(156, 148, 148, 0.2), 0 6px 20px 0 rgba(150, 144, 144, 0.19);
    .box-title {
      margin-left: 30px;
      margin-top: 15px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      color: blue;
     
    }
     .box-content-msg{
      margin-left: 30px;
      font-size: 16px;
      margin-top: 5px;
      color: #000000;
      ul{
        margin-left: 20px;
        li{
           list-style:disc;
            margin-right: 10px;
           margin-top: 2px;
        }
      }
    }

  }
}
</style>

<script>
export default {
  data() {
    return {
      value1: "2020-02-14",
      name:"",
      desc:"",
      noticeList:[],
      list:[],
      signList:[],
      department:"",
      role:"",
      value: new Date()
    };
  },
  mounted(){
      this.getEmployeeName() //获取员工名字
      this.getEmployeeStatus() // 打卡状态
      this.getNoticeMsg() //公告信息
      // this.getLeaveMsgById()
      this.getSign() //是否签到
  },
  methods:{
    // 去个人信息页面
    goMyinfo(){
        this.$router.push("/information")
    },
    //获取补签信息
    getSign(){
        let id = localStorage.getItem("id"),obj = {id},that = this
      this.axios.post("/sign/getSignMsg",this.qs.stringify(obj)).then((res)=>{

          that.signList = res.data.data
          for(let i of that.signList){
            if(i.status == "0"){
              i.status = "审批中"
            }else if(i.status == '1'){
              i.status = "同意"
            }else if(i.status == 2){
              i.status = "不同意"
            }
          }
         
      })
    },
    // 请假状态
    getStatus(status){
        if(status == 0){
          return "审核中"
        }else if(status == 1)
        {
          return "同意"
        }else if(status == 2){
          return "不同意"
        }
    },
    // 获取员工名字
    getEmployeeName(){
         

             let id = localStorage.getItem("id"),that = this
             this.axios.post("/employee/getMsg",this.qs.stringify({id})).then((res)=>{
               that.name = res.data.username
               that.department = res.data.department
               that.role = res.data.role
             })
    },
    // 判断员工登录的是什么时间段 
    getMyTime(){
          let time = new Date(),hours = time.getHours()
        
          if(hours < 12 && hours > 8 ){
            return "上午好"
          }else if(hours >= 12 && hours < 18 ){
            return "下午好"
          }else if(hours >= 18 ){
            return "晚上好"
          }else if(hours >= 0 && hours < 8){
            return "夜深了"
          }
    },
    // 获取现在的时间是多少
    getTime(){
        let t= new Date(),y = t.getFullYear(), m = t.getMonth()+1,d = t.getDate()
        return y+"-"+m+"-"+d
    },
    // 根据现在的时间跟员工id去查询员工考勤表的状态 0是正常 1是补签成功 2是异常 请假先不做判断
    getEmployeeStatus(){
      let nowTime = this.getTime(),id = localStorage.getItem("id"),that = this,obj={worktime:this.getTime(),eid:id}
      that.axios.post("attendance/emloyeeIfSign",that.qs.stringify(obj)).then((res)=>{
          let status = res.data.data

       
          if(status == null){
            that.desc = "今日未打卡"
          }else{
              if(status.status == 1){
                that.desc = "今日打卡成功"
              }else if(status.status == 2){
                that.desc = "打卡异常"
              }else if(status.status == 0){
                that.desc = "今日打卡成功"
              }
          }
      })
    },
    //获取全部公告 只显示最新公告的前十条,点击更多的时候跳转到通知页面查询更多公告
    getNoticeMsg(){
      let that = this
      this.axios.get("/notice/findAll").then((res)=>{
         

          let len = res.data.length

          that.noticeList = res.data.slice(-8,len).reverse()


        

          // let ss = res.data.
          // console.log(ss)
      })  
    },
    //根据员工id查找请假列表
    getLeaveMsgById() {
      let that = this,
        id = localStorage.getItem("id"),
        obj = { id };

      this.axios
        .post("/leave/getLeaveMsg", this.qs.stringify(obj))
        .then(res => {
          that.list = res.data.data
        });
    },
    //点击更多去往对应界面
    goNotice(){
        this.$router.push("/employeeNotice")
    }
  }

  
}
</script>