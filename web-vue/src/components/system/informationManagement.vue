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
              <h3>个人信息</h3>
              <div class="center-box"> 
                  <div class="left">
                    

                  </div>
                  <div class="center">
                      <ul>
                          <li>姓名：{{list.username}}</li>
                          <li>性别：{{list.gender = 1 ? "男" : "女" }}</li>
                          <li>年龄：{{list.age}}</li>
                          <li>籍贯：{{list.address}}</li>
                    
                      </ul>
                  </div>
                  <div class="right">
                       <ul>
                          <li>部门：{{list.department}}</li>
                          <li>职位：{{list.position}}</li>
                          <li>入职时间：{{list.jointime}}</li>
                          <li>电话号码：{{list.phone}}</li>
                      </ul>
                  </div>
              </div>
        </div>
        
      </div>
  
    </div>
    
  </div>
</template>
<style scoped  lang="less">
.main {
  width: 95%;
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
    overflow: hidden;
    height: 400px;
    width: 900px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px 0 rgba(156, 148, 148, 0.2), 0 6px 20px 0 rgba(150, 144, 144, 0.19);
  
    .box-content {
      h3{
        margin-top: 20px;
        margin-left: 20px;
        color: blue;
        font-size: 20px;
      }
      margin-left: 30px;
      .center-box{
          display: flex;
          justify-content: space-between;
          .left{
            display: flex;
            flex-direction:column;  
            justify-content: center;
            align-items: center;
            img{
              width: 150px;
              height: 150px;
              border-radius:50%;
              margin-left: 20px;
              margin-top: 30px;
            }
            p{
              margin-top: 10px;
              color: blue;
              font-size: 16px;
              border-bottom: 1px solid 
            }
          }
          .center{
            font-size: 16px;
            margin-top: 30px;
            ul{
              li{
                margin-top: 23px;
              }
            }

          }
          .right{
            font-size: 16px;
            margin-top: 30px;
            margin-right: 200px;
             ul{
              li{
                margin-top: 23px;
              }
            }
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
      list:[]
    }
  },
  mounted(){
    this.getMsg()
  },
  methods:{
    getMsg(){
         let id = localStorage.getItem("id"),that = this
             this.axios.post("/employee/getMsg",this.qs.stringify({id})).then((res)=>{
               
              that.list = res.data
             })
    }
  }
};
</script>