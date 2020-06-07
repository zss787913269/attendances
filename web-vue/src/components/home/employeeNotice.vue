<template>
  <div class="main">
   

    <!-- 标题栏 -->
    <div class="title">
      <div class="left">
        <span>公告信息</span>
        <span>Notice</span>
      </div>
    </div>
 

      <div class="dialog">
     <el-dialog    title="公告详情" :visible.sync="dialogVisible" width="80%">
        <div class="dialog-msg">
          
          <div class="msg">
              <h3>
                  {{EmployeeMsg.title}}
              </h3>
              <div>
                  <span>
                      {{EmployeeMsg.msg}}
                  </span>
              </div>

              <div class="time-name">
                 <div>
                  <span>
                    发布人：{{EmployeeMsg.name}}
                  </span>
              </div>
              <div>
                  <span>
                    创建时间：  {{EmployeeMsg.time}}
                  </span>
              </div>
              </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
   

    <div class="search-box">
      <!-- 搜素 -->

      <!-- 操作 -->
      <div class="tablemain">
        
        <!-- 表格 -->
        <div class="tablebox">
          <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" stripe style="width: 100%" class="tableDatadisplay">
            <el-table-column label="操作" width="180">
                <template slot-scope="scope">
                <el-button
                  type="primary"
                  size="mini"
                  @click="showEmployeeDialog(scope.$index, scope.row)"
                >查看</el-button>
              </template>
            </el-table-column>
            
             <el-table-column prop="title" label="标题" align="center"></el-table-column>

            <el-table-column  :show-overflow-tooltip='true' prop="msg" label="内容" align="center"></el-table-column>

                 <el-table-column prop="time" label="创建时间" align="center"></el-table-column>
                 
                 <el-table-column prop="name" label="发布人" align="center"></el-table-column>

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

.time-name{
  
  position: relative;
  // height: 200px;
  right:50px;
  top: 100px;
  margin-top: 50px;
  font-size: 16px;
}
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
  h3 {
    text-align: center;
    margin-bottom: 30px;
  }
  .msg {
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      font-size: 26px;
    .msg-left {
      p {
        display: flex;
        margin-top: 20px;
        font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
        color: #000;
        border-bottom: 1px solid #cccccc;
        span {
          font-size: 24px;
          display: block;
        }
        span:first-child {
          width: 200px;
        }
        span:last-child {
          width: 300px;
        }
      }
    }
    .msg-right {
      p {
        display: flex;
        margin-top: 20px;
        font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
        color: #000;
        border-bottom: 1px solid #cccccc;
        span {
          font-size: 24px;
          display: block;
        }
        span:first-child {
          // width: 200px;
        }
        span:last-child {
          // width: 300px;
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
      //   background-color: #f1f1f1;
      display: flex;

    //   justify-content: flex-end;
      button {
        margin-right: 50px;
        margin-bottom: 30px;
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
      EmployeeMsg:{},
      //请假单
      timeoffForm: {
        title: "",
        time: "",
        msg:""
      },
      //请假类型
    
      tableData: [
        {
            title:"",
            time:"",
            msg:""
        }
      ]
    };
  },
  
  mounted() {
    this.getNoticeMsg();
  },

  methods: {

 

    showEmployeeDialog(index,row) {    
      
         this.EmployeeMsg = row
            this.dialogVisible = true
    },
    //获取全部的公告

    getNoticeMsg() {

        let that = this
        this.axios.get("notice/findAll").then((res)=>{



            that.tableData = res.data.reverse()
           
        })
    },
 
    handleClose() {
      this.dialogFormVisible = false;
    },
    handleTimeoff() {
      this.dialogFormVisible = true;
    },
    // 删除员工

    // 查看某个员工的详细信息

   handleCurrentChange(item) {

        this.currentPage = item;

        console.log('跳转到指定页');

    },

    //处理每页显示数量变化的函数

    handleSizeChange(item) {

        this.pagesize = item;

        console.log('每页的数量变化');

    },

    handleSelectionChange(val) {
      this.selectionVal = val;
    }
  }
};
</script>