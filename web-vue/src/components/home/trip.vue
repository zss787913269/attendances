<template>
  <div class="main">
   

    <!-- 标题栏 -->
    <div class="title">
      <div class="left">
        <span>出差信息</span>
        <span>Ask for leave</span>
      </div>
    </div>
 

      <div class="dialog">
     <el-dialog title="查看出差详情" :visible.sync="dialogVisible" width="80%">
        <div class="dialog-msg">
          <h1>出差详情</h1>
          <div class="msg">
            <div class="msg-left">
                  
              <p>
                <span>姓名：</span><span>{{EmployeeMsg.name}}</span>
              </p>
              <p>
                <span>出差开始时间：</span><span>{{EmployeeMsg.timeStart}}</span>
              </p>
               <p><span>出差结束时间：</span><span>{{EmployeeMsg.timeEnd}}</span>
              </p>

               <p>
                <span>出差地点：</span><span>{{EmployeeMsg.address}}</span>
              </p>
                <p>
                <span>出差原因：</span><span>{{EmployeeMsg.reason}}</span>
              </p>
               
               <p>
                <span>出差时长：</span><span>{{EmployeeMsg.howLong}}天</span>
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

             <el-table-column prop="name" label="姓名" align="center"></el-table-column>
            
            <el-table-column label="出差日期" align="center">
              <template slot-scope="scope">
                <span>{{scope.row.timeStart}}</span>
                <span>--</span>
                <span>{{scope.row.timeEnd}}</span>
              </template>
            </el-table-column>

             <el-table-column prop="address" label="出差地点" align="center"></el-table-column>

            <el-table-column prop="howLong" label="出差时长(天)" align="center"></el-table-column>

            <el-table-column  :show-overflow-tooltip='true' prop="reason" label="出差事由" align="center"></el-table-column>

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
        timeoff_date: [],
        timeoff_type: "",
        decs: "",
        address:""
      },
      //请假类型
    
      tableData: [
        {
          timeStart: "",
          timeEnd: "",
          reason: "",
          reply: "",
          status: "",
          leaveType: "",
          uid: "",
          howLong: "",
          address:""
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
     
        let arr = this.timeoffForm.timeoff_date ,startTime = new Date(arr[0]) ,endTime = new Date(arr[1]),id = localStorage.getItem("id")
        const startDate = startTime.getFullYear() + '-' + (startTime.getMonth() + 1) + '-' + startTime.getDate()
        const endDate = endTime.getFullYear() + '-' + (endTime.getMonth() + 1) + '-' + endTime.getDate()

        let obj = {
            timeStart:startDate,
            timeEnd:endDate,
            reason:that.timeoffForm.decs,
            leaveType:that.timeoffForm.timeoff_type,
            uid:id,
            status:0,
            address:that.timeoffForm.address
        }
        this.axios.post("/bussiness/insert",this.qs.stringify(obj)).then((res)=>{
          console.log(res)
           that.getLeaveMsgById();
        })

      this.dialogFormVisible = false;
     

     


    },
    showEmployeeDialog(index,row) {    
      
         this.EmployeeMsg = row
          
      
            this.dialogVisible = true
    },
    // 所有出差记录

    getLeaveMsgById() {
      let that = this
    
      this.axios
        .get("/bussiness/findAll")
        .then(res => {

            console.log(res.data)
          that.tableData = res.data.reverse()

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
    // 删除员工

    // 查看某个员工的详细信息

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
    }
  }
};
</script>