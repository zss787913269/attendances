<template>
  <div class="main">
    <!-- 添加员工信息 -->

    <!-- 修改员工信息 -->

    <!-- 标题栏 -->
    <div class="title">
      <div class="left">
        <span>请假信息</span>
        <span>Ask for leave</span>
      </div>
    </div>
    <!-- 请假弹框 -->
    <el-dialog title="请假申请单" :visible.sync="dialogFormVisible" >
      <el-form :model="timeoffForm" label-position="right" label-width="140px">
        <el-form-item label="请假起止日期">
          <el-date-picker
            v-model="timeoffForm.timeoff_date"
            type="daterange"
            align="right"
            unlink-panels
            range-separator="~"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            size="small"
          ></el-date-picker>
        </el-form-item>
       
        <el-form-item label="请假类型">
          <el-select
            v-model="timeoffForm.timeoff_type"
            placeholder="请选择请假类型"
            size="small"
            class="inputForm"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="请假原因">
          <el-input
            type="textarea"
            :rows="5"
            v-model="timeoffForm.decs"
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
     <el-dialog title="查看请假详情单" :visible.sync="dialogVisible" width="80%">
        <div class="dialog-msg">
          <h1>请假详情单</h1>
          <div class="msg">
            <div class="msg-left">
              <p>
                <span>请假开始时间：</span><span>{{EmployeeMsg.timeStart}}</span>
              </p>
               <p><span>请假结束时间：</span><span>{{EmployeeMsg.timeEnd}}</span>
              </p>
               <p>
                <span>请假类型：</span><span>{{EmployeeMsg.leaveType}}</span>
              </p>
               <p>
                <span>请假时长：</span><span>{{EmployeeMsg.howLong}}天</span>
              </p>
               
              

            </div>
            <div class="msg-right">
              <p>
                <span>请假原因：</span><span>{{EmployeeMsg.reason}}</span>
              </p>
               <p>
                <span>审批状态：</span><span>{{EmployeeMsg.status}}</span>
              </p>
               
               <p>
                <span>请假回复：</span><span>{{EmployeeMsg.reply}}</span>
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
        <div class="crudbox">
          <el-button type="danger" icon="el-icon-plus" @click="handleTimeoff">请假申请</el-button>
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
            <el-table-column label="请假日期" align="center">
              <template slot-scope="scope">
                <span>{{scope.row.timeStart}}</span>
                <span>--</span>
                <span>{{scope.row.timeEnd}}</span>
              </template>
            </el-table-column>

            <el-table-column prop="howLong" label="请假时长(天)" align="center"></el-table-column>

            <el-table-column prop="leaveType" label="请假类型" align="center"></el-table-column>

            <el-table-column  :show-overflow-tooltip='true' prop="reason" label="请假理由" align="center"></el-table-column>

            <el-table-column label="状态" align="center">
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

      // justify-content: flex-end;
      button {
        margin-right: 50px;
        margin-bottom: 20px;
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
     
      currentPage:1, 
      pagesize:10, 
      dialogVisible:false,
      dialogFormVisible: false,
      EmployeeMsg:{},
      //请假单
      timeoffForm: {
        timeoff_date: [],
        timeoff_type: "",
        decs: ""
      },
      //请假类型
      options: [
        {
          value: "事假",
          label: "事假"
        },
        {
          value: "病假",
          label: "病假"
        },
        {
          value: "婚假",
          label: "婚假"
        },
        {
          value: "年假",
          label: "年假"
        },
        {
          value: "产假",
          label: "产假"
        },
        {
          value: "丧假",
          label: "丧假"
        },
        {
          value: "陪产假",
          label: "陪产假"
        }
      ],
      tableData: [
        {
          timeStart: "",
          timeEnd: "",
          reason: "",
          reply: "",
          status: "",
          leaveType: "",
          uid: "",
          howLong: ""
        }
      ]
    };
  },
  mounted() {
    this.getLeaveMsgById();
    this.getHoliday();
   
  },

  methods: {
      getTime(){
        let t= new Date();
        let y = t.getFullYear();
        let m = t.getMonth()+1;
        let d = t.getDate()
        return y+"年"+m+"月"+d+"日"
    },

    ensure(){
       let that = this
      //  把时间格式化 然后传给后台
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
            createTime:that.getTime()
        }
        this.axios.post("/leave/create",this.qs.stringify(obj)).then((res)=>{
          console.log(res)
             that.getLeaveMsgById();
        })

      this.dialogFormVisible = false;
   


    },
    showEmployeeDialog(index,row) {    
      
         this.EmployeeMsg = row
           if (this.EmployeeMsg.status == 0) {
              this.EmployeeMsg.status = "审批中";
            } else if (this.EmployeeMsg.status == 1) {
              this.EmployeeMsg.status = "同意";
            } else if (this.EmployeeMsg.status  == 2) {
              this.EmployeeMsg.status= "不同意";
            }
      
            this.dialogVisible = true
    },
    // 根据id获取请假列表信息

    getLeaveMsgById() {
      let that = this,
        id = localStorage.getItem("id"),
        obj = { id };

      this.axios
        .post("/leave/getLeaveMsg", this.qs.stringify(obj))
        .then(res => {

          let len = res.data.data.length

  
          that.tableData = res.data.data

          for (let i of that.tableData) {
         
            i.howLong = that.getHoliday(i.timeStart,i.timeEnd)

            

            if (i.status == 0) {
              i.status = "审批中";
            } else if (i.status == 1) {
              i.status = "同意";
            } else if (i.status == 2) {
              i.status = "不同意";
            }
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

   

    handleSelectionChange(val) {
      this.selectionVal = val;
    },
   
    handleCurrentChange(item) {
        this.currentPage = item;
        console.log('跳转到指定页');
    },
    //处理每页显示数量变化的函数
    handleSizeChange(item) {
        this.pagesize = item;
        console.log('每页的数量变化');
    },
  }
};
</script>