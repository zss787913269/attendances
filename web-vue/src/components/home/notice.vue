<template>
  <div class="main">
   

    <!-- 标题栏 -->
    <div class="title">
      <div class="left">
        <span>公告信息</span>
        <span>Notice</span>
      </div>
    </div>
    <!-- 发布公告弹框 -->
    <el-dialog title="公告登记" :visible.sync="dialogFormVisible" >
      <el-form :model="timeoffForm" label-position="right" label-width="140px">

        <el-form-item label="标题">
          <el-input
            v-model="timeoffForm.title"
            size="small"
            class="textareaDecs"
          ></el-input>
        </el-form-item>
       

        <el-form-item label="内容">
          <el-input
            type="textarea"
            :rows="5"
            v-model="timeoffForm.msg"
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
        <div class="crudbox">
          <el-button type="danger" icon="el-icon-plus" @click="handleTimeoff">发布通知</el-button>
        </div>

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
          // width: 200px;
        }
        span:last-child {
          // width: 300px;
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
    this.getLeaveMsgById();
    this.getHoliday();
   
  },

  methods: {

    ensure(){
       let that = this

       console.log(123)
     
        let id = localStorage.getItem("id")
        

        let obj = {
            title:that.timeoffForm.title,
            msg:that.timeoffForm.msg,
            time:that.getTime(),
            bid:id
        }
        this.axios.post("/notice/insert",this.qs.stringify(obj)).then((res)=>{
          console.log(res)
        })

      this.dialogFormVisible = false;
      this.getLeaveMsgById();

      


    },
     getTime(){
        let t= new Date();
        let y = t.getFullYear();
        let m = t.getMonth()+1;
        let d = t.getDate()
        return y+"-"+m+"-"+d
    },
    showEmployeeDialog(index,row) {    
      
         this.EmployeeMsg = row
            this.dialogVisible = true
    },
    // 根据id获取请假列表信息

    getLeaveMsgById() {
      let that = this,
        id = localStorage.getItem("id"),
        obj = { id };

      this.axios
        .post("/notice/getIdfindNotice", this.qs.stringify(obj))
        .then(res => {

          //  let len = res.data.data.length
          // that.tableData = res.data.data.slice(-10,len).reverse();
          that.tableData = res.data.data.reverse()
          

          for (let i of that.tableData) {
         
            i.howLong = that.getHoliday(i.timeStart,i.timeEnd)

           
          }

          
        });
    },
    getHoliday(timeStart, timeEnd) {
      var sdate = timeStart;
      var edate = timeEnd;
    

        var num = this.datedifference(sdate, edate);
        var lastday = num % 7;
        var weeknum = 0;
        if (num >= 7) {
          weeknum = parseInt(num / 7);
        }

        var weekday = [
          "星期日",
          "星期一",
          "星期二",
          "星期三",
          "星期四",
          "星期五",
          "星期六"
        ];
        var result = 1;
        for (var i = 0; i < lastday; i++) {
          var dd = new Date(this.addDate(sdate, i)).getDay();
          if (weekday[dd] != "星期六" && weekday[dd] != "星期日") {
            result++;
          }
        }
        return result + weeknum * 5;
     
    
    },
    addDate(date, days) {
          var d = new Date(date);
          d.setDate(d.getDate() + days);
          var m = d.getMonth() + 1;
          return d.getFullYear() + '-' + m + '-' + d.getDate();
      },
    datedifference(sDate1, sDate2) { //sDate1和sDate2是2006-12-18格式  
          var dateSpan, tempDate, iDays;
          sDate1 = Date.parse(sDate1);
          sDate2 = Date.parse(sDate2);
          dateSpan = sDate2 - sDate1;
          dateSpan = Math.abs(dateSpan);
          iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
          return iDays
      },
    handleClose() {
      this.dialogFormVisible = false;
    },
    handleTimeoff() {
      this.dialogFormVisible = true;
    },
    handleCurrentChange(item){
       this.currentPage = item
    },
    handleSizeChange(item){
       this.pagesize = item
    }
    
  }
};
</script>