<template>
  <div class="main">
    <div class="title">
      <div class="left">
        <span>考勤管理</span>
        <span>Personnel</span>
      </div>
      <div class="right">
        <span>日历：</span>
        <el-date-picker
          v-model="value4"
          type="date"
          placeholder="选择日期"
          format="yyyy 年 MM 月 dd 日"
          style="width:190px"
        ></el-date-picker>
      </div>
    </div>

    <div class="content-top">
      <div class="box">
        <div class="box-content">
          <h3>请假申请</h3>
          <div class="absence-box">
            <div class="line">
              <div class="left">
                <span class="left-span">申请人：</span>
                <span class="name-box"><h2>小何</h2></span>
              </div>
              <div class="right">
                <span class="left-span">请假事由：</span>
                  <el-select v-model="value" placeholder="请选择"
                   style="width:120px">
                    <el-option
                   
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
              </div>
            </div>
            <div class="line">
              <div class="left"> 
                <span class="left-span">开始时间：</span>
                <el-date-picker v-model="value1" type="datetime" placeholder="选择日期时间"></el-date-picker>
              </div>
              <div class="right">
                <span  class="left-span">结束时间：</span>
                <el-date-picker v-model="value2" type="datetime" placeholder="选择日期时间"></el-date-picker>
              </div>
            </div>
             <div class="line">
              <div class="left"> 
                <span  class="left-span">总时长：</span><span>8小时</span>
              </div>
              <div class="right">
                <span  class="left-span">申请时间：</span>
                <el-date-picker v-model="value3" type="datetime" placeholder="选择日期时间"></el-date-picker>
              </div>
            </div>
             <div class="textarea-box">
              <div class="left"> 
                <span  class="left-span">申请理由：</span>
              </div>
              <div class="right">
                    <textarea name="" id="" cols="90" rows="7"></textarea>                    
              </div>
            </div>
            <div class="upload-box">
                <span>上传附件:</span>
                 <input type="file">
            </div>
            <div class="bottom-right">
                    <el-button type="info">取消</el-button>
                    <el-button  type="info">保存</el-button>
                    <el-button type="primary">提交</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped  lang="less">
.main {
  width: 100%;
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
  width: 100%;
  margin-top: 15px;
  display: flex;
  justify-content: space-between;
  //  box-shadow:  #888888;
  .box {
    // margin-left: 10px;
    height: 650px;
    width: 1100px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px 0 rgba(156, 148, 148, 0.2),
      0 6px 20px 0 rgba(150, 144, 144, 0.19);

    .box-content {
      h3 {
        text-align: center;
        margin-top: 20px;
        color: blue;
        font-size: 20px;
      }
      .absence-box {
        font-size: 16px;
        .bottom-right{
            margin-top: 50px;
            margin-right: 60px;
            display: flex;
            justify-content: flex-end;
        }
        .upload-box{
          margin-top: 20px;
         margin-left: 100px;
         input{
             margin-left: 30px;
             
         }
        }
        .textarea-box{
            display: flex;
            margin-top: 20px;
            .left{ 
              margin-left: 100px;
            }
            .right{
                margin-left: 30px;
            }
        }
        .line {
            margin-top: 20px;
            display: flex;
            align-items: center;
          .left{
              .name-box{
                  display: block;
                  line-height: 30px;
                  background-color: #E2E2E2;
                  color: #010101;
                  width: 100px;
                  height: 30px;
                  border-radius: 3px;
                  h2{
                      margin-left: 10px;
                      font-size: 16px;
                  }
              }
              display: flex;
              width: 400px;
              align-items: center;
              margin-left: 100px;
              .left-span{
                  display: block;
                  width: 100px;
                  
              }
              
          }
          .right{
              margin-left: 100px;
              display: flex;
              align-items: center;
              .left-span{
                  display: block;
                  width: 100px;
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
      value1: "",
      value2: "",
      value3: "",
      value4: "",
          options: [{
          value: '选项1',
          label: '年假'
        }, {
          value: '选项2',
          label: '事假'
        }, {
          value: '选项3',
          label: '产假'
        }, {
          value: '选项4',
          label: '例假'
        }, {
          value: '选项5',
          label: '病假'
        }],
          value: ''
    };
  }
};
</script>