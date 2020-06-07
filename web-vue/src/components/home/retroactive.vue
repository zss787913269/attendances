<template>
  <div class="main">

<!-- 补签详情 -->
       <el-dialog title="查看补签详情单" :visible.sync="dialogVisible" width="80%">
        <div class="dialog-msg">
          <h1>补签详情单</h1>
          <div class="msg">
            <div class="msg-left">
              <p>
                <span>补签时间：</span><span>{{EmployeeMsg.timeStart}}</span>
              </p>
               <p><span>补签原因：</span><span>{{EmployeeMsg.reason}}</span>
              </p>
               <p>
                <span>状态：</span><span>{{EmployeeMsg.status}}</span>
              </p>
              
               <p>
                <span>提交时间：</span><span>{{EmployeeMsg.createtime}}</span>
              </p>

            </div>
          
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
  
       <!-- 补签弹框 -->
    <el-dialog title="补签详情单" :visible.sync="dialogFormVisible" >
      <el-form :model="timeoffForm" label-position="right" label-width="140px">
        <el-form-item label="补签日期">
          <el-date-picker
            v-model="timeoffForm.timeoff_date"
            type="date"
            align="right"
            start-placeholder="选择日期"
            size="small"
          ></el-date-picker>
        </el-form-item>
       
      

        <el-form-item label="补签原因">
          <el-input
            type="textarea"
            :rows="5"
            v-model="timeoffForm.reason"
            size="small"
            class="textareaDecs"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleClose" size="small">取 消</el-button>
        <el-button type="primary" @click="ensure" size="small">确 定</el-button>
      </div>
    </el-dialog>

   


    <!-- 标题栏 -->
    <div class="title">
      <div class="left">
        <span>考勤补签</span>
        <span>Attendance retroactive</span>
      </div>

     
    </div>
    <!-- 查看员工档案 -->
   
    <div class="search-box">
      <!-- 搜素 -->
      
      <!-- 操作 -->
      <div class="tablemain">
        <div class="crudbox">
          <el-button type="danger" icon="el-icon-plus"  @click="open">添加</el-button>
        </div>

        <!-- 表格 -->
        <div class="tablebox">
          <el-table
            ref="multipleTable"
            :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            height="600"
           
            id="out-table"
            @selection-change="handleSelectionChange"
          >
            <el-table-column label="序号" type="index" width="55"></el-table-column>
            <el-table-column style="width:20px;height:20px" type="selection" width="60"></el-table-column>
            <el-table-column label="操作" width="180">
                <template slot-scope="scope">
                <el-button
                  type="primary"
                  size="mini"
                  @click="showView(scope.$index, scope.row)"
                >查看</el-button>
              </template>
            </el-table-column>
          <el-table-column prop="createtime" label="提交时间"></el-table-column>
          <el-table-column prop="timeStart" label="补签日期" ></el-table-column>
            <el-table-column prop="reason" label="事由" ></el-table-column>
            <el-table-column prop="status" label="审批状态" >

               <template slot-scope="scope">
                <div
                  :class="{red: (scope.row.status === '不同意'), green: (scope.row.status === '同意'),blue: (scope.row.status === '审批中') }"
                >
                  <span>{{scope.row.status}}</span>
                </div>
              </template>

            </el-table-column>
           
          </el-table>
          <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="currentPage"
    :page-sizes="[10, 20, 30, 40]"
    :page-size="10"
    layout="total, sizes, prev, pager, next, jumper"
    :total="tableData.length">
</el-pagination>
          
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped  lang="less">
.green {
  color: #67c23a;
}
.red {
  color: red;
}
.blue {
  color: #409eff;
}
.main {
  width: 95%;
  margin-left: 40px;
  margin-top: 95px;
}
.title {
  display: flex;
  align-items: center;
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
    margin-right: 20px;
    display: flex;
    align-items: center;
    position: absolute;
    right: 5%;
  }
}
// 员工信息添加样式
.dialog-add {
  h1 {
    text-align: center;
    margin-bottom: 30px;
  }
  .s-msg {
    display: flex;
    justify-content: space-around;
  }
}
// 员工档案
.dialog-msg {
  h1 {
    text-align: center;
    margin-bottom: 30px;
  }
  .msg {
    display: flex;
    justify-content: space-around;
    .msg-left {
      p{
        display: flex;
        margin-top: 20px;
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        color: #000;
        border-bottom: 1px solid #cccccc;
        span{
          font-size: 24px;
          display: block;
       
        }
        span:first-child{
          // width: 200px;
          
          
        }
        span:last-child{
          //  width: 300px;
        
        }
      }
    }
    .msg-right {
       p{
        display: flex;
        margin-top: 20px;
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        color: #000;
        border-bottom: 1px solid #cccccc;
        span{
          font-size: 24px;
          display: block;
       
        }
        span:first-child{
          // width: 200px;
        
          
        }
        span:last-child{
          //  width: 300px;
       
        }
      }
    }
  }
}
.dialog-content {
  .name {
    display: flex;
    justify-content: space-around;
  }
}
.search-box {
  width: 100%;
  margin-top: 20px;
  font-size: 20px;
  .inputbox {
    input {
      margin-left: 10px;
      height: 38px;
    }
    button {
      margin-left: 20px;
    }
  }
  .tablemain {
    margin-top: 20px;
    widows: 80%;
    .crudbox {
      width: 100%;
      background-color: #f1f1f1;
      button {
        margin: 10px 10px 10px 22px;
      }
    }
  }
}
</style>

<script>
import FileSaver from "file-saver";
export default {
  data() {
    return {
      currentPage:1, //初始页
pagesize:10,    //    每页的数据
  
      dialogVisible:false,
      dialogFormVisible: false,
       timeoffForm: {
        timeoff_date: "",
        reason: "",
      },
      EmployeeMsg:{
        timeStart:"2020-2-2",
        reason:"网络问题",
        status:"审批中"
      },
     
      tableData: [
        {
      
          uid:"", //名字
          reason:"", //理由
          createtime:"", //创建时间
          timeStart:"", //补签时间
          status:"" ,//审批状态 
        }
      ]
    };
  },
  mounted() {
   this.getMsg()
  },
  // 监听输入框是否为空
  watch: {
    
  },
  methods: {
   
   handleClose(){
     this.dialogFormVisible = false
   },
   ensure(){

 

      let that = this

      if(this.timeoffForm.timeoff_date == "" || this.timeoffForm.reason == ""){
        this.$message('表单未填写完整');
      }else{
          let startTime = this.timeoffForm.timeoff_date
          const startDate = startTime.getFullYear() + '-' + (startTime.getMonth() + 1) + '-' + startTime.getDate()
          let reason = this.timeoffForm.reason
          let uid = localStorage.getItem("id")
          let createtime = this.getTime()

          let obj = {
            createtime,uid,reason,timeStart:startDate,status:0
          }

          this.axios.post("/sign/create",this.qs.stringify(obj)).then((res)=>{
                console.log(res)
                 if(res.data.code == 200){
                    that.getMsg()
                    that.dialogFormVisible = false
                 }
          })

      }

     

   },
    getTime(){
        let t= new Date();
        let y = t.getFullYear();
        let m = t.getMonth()+1;
        let d = t.getDate()
        return y+"-"+m+"-"+d
    },

   getMsg(){

     let id = localStorage.getItem("id"),obj = {id},that = this
      this.axios.post("/sign/getSignMsg",this.qs.stringify(obj)).then((res)=>{

          that.tableData = res.data.data
          for(let i of that.tableData){
            if(i.status == "0"){
              i.status = "审批中"
            }else if(i.status == '1'){
              i.status = "同意"
            }else if(i.status == 2){
              i.status = "不同意"
            }
          }
          console.log(that.tableData);
      })
   },
   
   
  // 查看补签详细信息
   showView(index,row)
   {

    this.EmployeeMsg = row
     this.dialogVisible = true
   },
   //打开补签弹窗
    open(){

    
      this.dialogFormVisible = true
    },
    // 查看某个员工的详细信息
    showEmployeeDialog(index, row) {
    
    },
  handleCurrentChange(item) {
    this.currentPage = item;
    console.log('跳转到指定页');
   },
handleSizeChange(item) {
this.pagesize = item;
console.log('每页的数量变化');
    },

    handleSelectionChange(val) {
      this.selectionVal = val;
    },
    
   
  }
};
</script>