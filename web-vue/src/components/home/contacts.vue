<template>
  <div class="main">
    <!-- 添加员工信息 -->
  

    <!-- 修改员工信息 -->
   


    <!-- 标题栏 -->
    <div class="title">
      <div class="left">
        <span>单位通讯录</span>
        <span>Company Contact</span>
      </div>

     
    </div>
   <div class="dialog">
     <el-dialog title="查看员工档案" :visible.sync="dialogVisible" width="80%">
        <div class="dialog-msg">
          <h1>查看员工档案</h1>
          <div class="msg">
            <div class="msg-left">
              <p>
                <span>姓名：</span><span>{{EmployeeMsg.username}}</span>
              </p>
               <p><span>性别：</span><span>{{EmployeeMsg.gender}}</span>
              </p>
               <p>
                <span>部门：</span><span>{{EmployeeMsg.department}}</span>
              </p>
               <p>
                <span>职位：</span><span>{{EmployeeMsg.position}}</span>
              </p>
               
              

            </div>
            <div class="msg-right">
              <p>
                <span>年龄：</span><span>{{EmployeeMsg.age}}</span>
              </p>
               <p>
                <span>手机号码：</span><span>{{EmployeeMsg.phone}}</span>
              </p>
               
               <p>
                <span>电子邮件：</span><span>{{EmployeeMsg.email}}</span>
              </p>
               <p>
                <span>籍贯：</span><span>{{EmployeeMsg.address}}</span>
              </p>
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
          <el-table
            ref="multipleTable"
            :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
           
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
                  @click="showEmployeeDialog(scope.$index, scope.row)"
                >查看</el-button>
               
              </template>
               
              
            </el-table-column>
            <el-table-column prop="username" label="姓名" width="140"></el-table-column>
            <el-table-column prop="department" label="部门" width="140"></el-table-column>
            <el-table-column prop="position" label="职位" width="140"></el-table-column>
            <el-table-column prop="phone" label="手机" width="140"></el-table-column>
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
     dialogVisible:false,
     EmployeeMsg:{},
     currentPage:1, //初始页
      pagesize:10,
      tableData: [
        {
    
          username:"张英铭", //名字
          department:"销售部", //理由
          position:"前端", //创建时间
          phone:"15977602749", //补签时间
      
        }
      ]
    };
  },
  mounted() {
   this.getEmployee()
  },
 
 
  methods: {

      
        // 展示所有员工信息 
        getEmployee(){
            let that = this;
            this.axios.get("/employee/showEmployee").then(res => {
                that.tableData = res.data
                
            });
        },
    
    // 查看某个员工的详细信息
    showEmployeeDialog(index,row) {    
        
      
        this.EmployeeMsg = row
      if(row.gender == 1){
          this.EmployeeMsg.gender = "男"
      }else{
          this.EmployeeMsg.gender = "女"
      }
      
            this.dialogVisible = true
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
        console.log(val)
      this.selectionVal = val;
    },
    
   
  }
};
</script>