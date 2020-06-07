<template>
  <div class="main">
    <div class="title">
      <div class="left">
        <span>系统管理</span>
        <span>system management</span>
      </div>
    
    </div>

    <div class="content-top">
      <div class="box">
       
        <div class="box-content">
              <h3>修改密码</h3>
              <div class="center-box">
                <div class="msg">
                    <div class="input-box">
                      <span>原密码：</span><input  v-model="password" type="password" name="" id="">
                    </div>
                    <div class="input-box">
                      <span>新密码：</span><input  v-model="newpassword" type="password">
                    </div>
                    <div class="input-box">
                     <span> 确认密码：</span><input  v-model="enpassword" type="password">
                    </div>
                    <p>提示：密码必须6位以上，且包含数字与字母，字母区分大小写</p>
                    <div class="bottom-box">
                      <div class="quxiao" @click="cancel()">
                       
                          取消
                      </div>
                      <div @click="ensure()" class="queding">
                       
                         提交
                      </div>
                    </div>
                  </div> 
              </div>
        </div>
        
      </div>
  
    </div>
    
  </div>
</template>
<style scoped  lang="less">
.main {

  width: 98%;
  margin-left: 40px;
  margin-top: 95px;
}
.title {
  display: flex;
  justify-content: space-between;
  color: #000000;
  .left {
    :first-child {
      font-size: 20px;
    }
    :last-child {
      margin-left: 12px;
      font-size: 14px;
    }
  }
  .right {
    font-size: 18px;
    margin-right: 20px;
    display: flex;
    align-items: center;
  }
}
.content-top {
  width:90%;
  margin-top: 15px;
  display: flex;
  justify-content: space-between;
  //  box-shadow:  #888888;
  .box {
    // margin-left: 10px;
    height: 400px;
    width: 1000px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px 0 rgba(156, 148, 148, 0.2), 0 6px 20px 0 rgba(150, 144, 144, 0.19);
    .box-content {
      margin-left: 30px;
      h3{
        margin-left: 20px;
        margin-top: 30px;
        font-size: 20px;
        color: blue;
      }
      .center-box{
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 70px;
         .msg{
           p{
             margin-top: 15px;
             color: red;
           }
           .input-box{
             margin-top: 10px;
             font-size: 14px;
             color:black;
             display: flex;
            
             input{
               margin-left: 10px;
             }
             span{
               width: 80px;
               display: block;
               font-size: 16px;
               color:#657180;
             }
           }
           .bottom-box{
             display: flex;
             margin-top: 20px;
             margin-left: 50px;
             .quxiao{
               cursor: pointer;
               margin-left: 10px;
               width: 60px;
               height: 30px;
               border: 1px solid #ccc;
               text-align: center;
               line-height: 30px;
               border-radius: 5px;
             }
             .queding{
               cursor: pointer;
               width: 60px;
               margin-left: 20px;
               height: 30px;
               border: 1px solid #ccc;
               text-align: center;
               line-height: 30px;
               color: #fff;
               background-color: blue;
               border-radius: 5px;
             }
           }
         }
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
}

</style>

<script>
export default {
  data() {
    return {
      value1: "2020-02-14",
      list:{},
      password:"",
      newpassword:"",
      enpassword:""
    };
  },
  watch:{
   
  },
  mounted(){
    this.getMsg()
  },
  methods:{
    getMsg(){
       let  that= this
       let id = localStorage.getItem("id")
        let obj = {
            id
        }
          this.axios.post("/employee/getMsg",this.qs.stringify(obj)).then((res)=>{
              
              that.list = res.data
          })
    },
    cancel(){
        this.password = ""
        this.newpassword = ""
        this.enpassword = ""
    },
    ensure(){
       let  that= this
      
        if(this.password == this.list.password){
            if(that.newpassword == that.enpassword){
              that.list.password = that.newpassword
               that.axios.post("/employee/editOrAddMsg",that.qs.stringify(that.list)).then((res)=>{
                  if(res.data.code == 200){
                     
                     this.$message({
                        message: '修改成功，请重新登录',
                        type: 'success'
                      });
                      that.password = ""
                      that.newpassword = ""
                      that.enpassword = ""

                      that.$router.push("/login")
                  }
                })
            }else{
              
                this.$message({
                        message: '两次密码输入不一致，请重新输入',
                        type: 'error',
                        offset:100
                      });
            }
        }else{
       
            this.$message({
                        message: '两次密码输入不一致，请重新输入',
                        type: 'error',
                        offset:100
                      });
        }

       
    }
  }
};
</script>