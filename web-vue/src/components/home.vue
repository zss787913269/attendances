<template>
  <div class="home-box">
      <div class="msg">
         <h1 style="margin-left:60px">考勤系统</h1>
       <div class="head">
        <!-- <img class="msg-img" src="../assets/tx.jpg" alt /> -->
        <p>{{name}}</p>
        <p v-if="!showboss">{{department}}</p>
        <p v-else>经理</p>
        <p>{{getTime()}}</p>
       </div>
        <div class="system" @click="loginOut()">
          <i class="el-icon-location"></i>
          <span >退出系统</span>
        </div>
      </div>
    <div class="menu">
      <el-row class="tac">
        <el-col :span="24">
          
          <el-menu
         
            style="background-color: #ccc;font-size:20px;font-family:PingFang SC;"
            :default-active="this.$route.path"
            class="el-menu-vertical-demo"
            @open="handleOpen" 
            @close="handleClose"
            router
             background-color="#545c64"
             text-color="#fff"
              active-text-color="#ffd04b"
            
          >
            <el-menu-item index="/myHome">
              <i class="el-icon-menu"></i>
              <span slot="title"  style="font-size:20px;font-family:PingFang SC" >首页</span>
            </el-menu-item>

            <el-menu-item index="/hr" v-if="showboss">
              <i class="el-icon-document-copy"></i>
              <span slot="title"  style="font-size:20px;font-family:PingFang SC"   >人事管理</span>
            </el-menu-item>
            <el-menu-item index="/employeeNotice" v-if="!showboss">
              <i class="el-icon-document-copy"></i>
              <span slot="title" style="font-size:20px;font-family:PingFang SC" >公告</span>
            </el-menu-item>

             <el-submenu index="9" v-if="showboss">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span style="font-size:20px;font-family:PingFang SC">公告管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/notice" style="font-size:18px;font-family:Hiragino Sans GB">我发布的公告</el-menu-item>
                <el-menu-item index="/employeeNotice" style="font-size:18px;font-family:Hiragino Sans GB">全部公告</el-menu-item>
              </el-menu-item-group>
            </el-submenu>


             <el-menu-item index="/trip" v-if="showboss" >
              <i class="el-icon-menu"></i>
              <span slot="title"  style="font-size:20px;font-family:PingFang SC" >员工出差信息</span>
            </el-menu-item>

              <el-menu-item index="/myleave" v-if="!showboss" >
              <i class="el-icon-menu"></i>
              <span slot="title"  style="font-size:20px;font-family:PingFang SC" >请假管理</span>
            </el-menu-item >

                     <el-menu-item index="/bussiness" v-if="!showboss">
              <i class="el-icon-menu"></i>
              <span slot="title"  style="font-size:20px;font-family:PingFang SC"  >出差管理</span>
            </el-menu-item>

            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span style="font-size:20px;font-family:PingFang SC">考勤系统</span>
                <!-- 缺一个系统通知页面 -->
              </template>
              <el-menu-item-group >
                <el-menu-item index="/attendance" v-if="!showboss"  style="font-size:18px;font-family:PingFang SC" >个人考勤</el-menu-item>
                <el-menu-item index="/teamAttendance" v-if="showboss" style="font-size:18px;font-family:PingFang SC" >团队考勤</el-menu-item>
                <el-menu-item index="/retroactive"  v-if="!showboss"  style="font-size:18px;font-family:PingFang SC" >考勤补签</el-menu-item>
                <el-menu-item index="/approval"  v-if="showboss" style="font-size:18px;font-family:PingFang SC" >员工请假审批</el-menu-item>
                 <el-menu-item index="/permissionSign"  v-if="showboss" style="font-size:18px;font-family:PingFang SC" >员工考勤审批</el-menu-item>
             
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="8">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span style="font-size:20px;font-family:PingFang SC">通讯录管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/contacts" style="font-size:18px;font-family:Hiragino Sans GB">单位通讯录</el-menu-item>
                <el-menu-item index="/pcontacts" style="font-size:18px;font-family:Hiragino Sans GB">个人通讯录</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
         
            <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span style="font-size:20px;font-family:PingFang SC">系统管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/information" style="font-size:18px;font-family:Hiragino Sans GB">个人信息管理</el-menu-item>
                <el-menu-item index="/repassword" style="font-size:18px;font-family:Hiragino Sans GB">修改密码</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-col>
      </el-row>
    
    
    </div>

    <div class="mianRight">
      <router-view />
    </div>
  </div>
</template>
<script>

export default {
  data(){
    return {
        id:"",
        msg:"",
        name:"",
        department:"",
        showboss:false,
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      // console.log(key, keyPath);
    },
    loginOut(){
      this.$router.push("login")
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
    },
    getTime(){
        let t= new Date();
        let y = t.getFullYear();
        let m = t.getMonth()+1;
        let d = t.getDate()
        return y+"年"+m+"月"+d+"日"
    },
    getMsg(){
        
      let  _this= this
        let obj = {
            id : this.id
        }
          this.axios.post("/employee/getMsg",this.qs.stringify(obj)).then((res)=>{

         

          // 老板是1 员工是0
                  if(res.data.role == 1){
                    _this.showboss  = true
                  }


            _this.name = res.data.username
            localStorage.setItem("name",res.data.username)
            _this.department = res.data.department
            
          })
    },
    
  },
  created(){
       this.id = localStorage.getItem('id');
       if(this.id == null){
         this.$router.push({path:"login"})
       }else{
           this.getMsg();
       }
       
      
      
  },
 
  


};
</script>

<style scoped>
.mianRight{
  
  width: 78%;
}
.tac{
  
  overflow: hidden;
    /* height: 100%; */
   
}
.home-box {
  display: flex;
  height: 100%;
  
}
.menu {
  margin-top: 60px;
  /* width: 250px; */
  width: 20%;
  background-color: #545c64;
  position: relative;
  /* height: 10000px; */
 

}
.msg {
  position: absolute;
  font-size: 16px;
  text-align: center;
  width: 100%;
  height: 60px;
  background-color: rgba(19, 17, 17, 0.521);
  display: flex;
  z-index: 99999;
  align-items: center;
  color: #ffffff;
  justify-items: center;

  
}
.head{
  display: flex;
  align-items: center;
  margin-left: 60px;
  position: absolute;
  right: 250px;
}
p{
  padding: 20px;
  font-size: 20px;
}
.msg-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.system {
  position: absolute;
  right: 30px;
  line-height: 60px;
  font-size: 30px;
  cursor: pointer;
  color: #000;
}

</style>

