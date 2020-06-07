<template>
  <div class="bgc">
    <div class="login_box">
      <h2>员工考勤系统</h2>

        <div class="welcome">
            欢迎登录
        </div>
      <div class="name">
        <Icon style="margin-left:20px" size="20" type="person"></Icon>
        <input type="text" placeholder="请输入账号"  v-model="num" />
      </div>

      <div class="psw">
        <Icon style="margin-left:20px" type="locked" size="20"></Icon>
        <input type="password" placeholder="请输入密码"  v-model="password" />
      </div>
      <span class="wj" @click="wjmm">忘记密码？</span>

      <div class="btn" @click="login()">
        <el-button type="success" style="width:300px">登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
        password:"",
        num:"",
       
    };
  },
  updated() {
   
  },
  methods: {

    wjmm(){
       
        this.$message({
          message: '请找管理员/老板，重新设置密码',
          type: 'warning'
        });
    },
    
    login() {
      let _this = this
      let obj = {
          password:this.password,
          num:this.num
      }
        this.axios.post("/employee/login",this.qs.stringify(obj)).then((res)=>{
            
            if(res.data.code == 200){
                // 把id保存在本地 根据id来获取信息 如果首页没有保存有id 则跳转到登录页面
                // 退出登录时 删除本地id
                
                let id = res.data.id
                localStorage.setItem('id',id);
                this.$router.push({path:"myHome"})
            }else{
               this.$message.error('账号或者密码错误,请重新输入');
            }
        })  

    },
  
  }
};
</script>


<style scoped>
.wj{
  margin-top: 10px;

  cursor: pointer;
}
.scanf {
  position: absolute;
  right: 0;
}
.scanf-img {
  width: 52px;
  height: 52px;
}

.bgc {
  background-image: url("../assets/login.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.login_box {
  position: relative;
  height: 380px;
  width: 390px;
  border-radius: 12px;
  background-color: #fff;
}
.welcome{
    margin-left: 25px;
    font-size: 20px;
    margin-top: 10px;
}
.name,
.psw {
  margin-left: 35px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-top: 30px;
  height: 35px;
  width: 290px;
  display: flex;
  align-items: center;
  overflow: hidden;
}
h2 {
  margin-top: 20px;
  text-align: center;
  font-size: 24px;
}
.box {
  margin-left: 15px;
  margin-top: 25px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}
input {
  margin-left: 15px;
  border: 0;
  height: 30px;
  font-size: 14px;
  outline: none;
}
p {
  font-size: 16px;
}
span {
  font-size: 12px;
  margin-top: 5px;
  margin-right: 25px;
  display: flex;
  justify-content: flex-end;
}

.btn {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 40px;
}
</style>
