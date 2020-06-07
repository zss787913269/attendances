<template>
  <div class="main">
    <!-- 添加员工信息 -->

    <!-- 修改员工信息 -->

    <!-- 标题栏 -->
    <div class="title">
      <div class="left">
        <span>请假管理</span>
        <span>Ask for leave</span>
      </div>
    </div>

    <div class="dialog">
      <el-dialog title="查看请假详情单" :visible.sync="dialogVisible" width="80%">
        <div class="dialog-msg">
          <h1>请假详情单</h1>
          <div class="msg">
            <div class="msg-left">
              <p>
                <span>请假人：</span>
                <span>{{EmployeeMsg.name}}</span>
              </p>
              <p>
                <span>请假开始时间：</span>
                <span>{{EmployeeMsg.timeStart}}</span>
              </p>
              <p>
                <span>请假结束时间：</span>
                <span>{{EmployeeMsg.timeEnd}}</span>
              </p>
              <p>
                <span>请假类型：</span>
                <span>{{EmployeeMsg.leaveType}}</span>
              </p>
              <p>
                <span>请假时长：</span>
                <span>{{EmployeeMsg.howLong}}天</span>
              </p>
            </div>
            <div class="msg-right">
              <p>
                <span>请假原因：</span>
                <span>{{EmployeeMsg.reason}}</span>
              </p>

              <p v-if="!showSelect">
                <span>处理结果：</span>
                <span>{{EmployeeMsg.deal}}</span>
              </p>

              <p v-if="!showSelect">
                <span>回复内容：</span>
                <span>{{EmployeeMsg.reply}}</span>
              </p>
            </div>
          </div>

         
          <h1 v-if="showSelect" style="margin-top:50px">
             <h3 style="border:none;margin-left:300px; margin-top:50px;margin-right:300px" v-if="showSelect">
            <span>
              <span>  回复：</span>
              <el-form :model="fromMsg" label-position="right">
                <el-form-item>
                  <el-input
                    type="textarea"
                    :rows="3"
                    v-model="fromMsg.reply"
                    size="small"
                    class="textareaDecs"
                    style="font-size:22px;"
                  ></el-input>
                </el-form-item>
              </el-form>
            </span>
          </h3>
            <span>是否同意：</span>
            <span>
              <el-radio v-model="radio" label="1">同意</el-radio>
              <el-radio v-model="radio" label="2">不同意</el-radio>
            </span>
          </h1>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button type="info" @click="dialogVisible = false,radio = ''">取消</el-button>
          <el-button type="primary" @click="ensure">确 定</el-button>
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
            <el-table-column prop="name" label="姓名" align="center"></el-table-column>
            <el-table-column label="请假日期" width="200" align="center">
              <template slot-scope="scope">
                <span>{{scope.row.timeStart}}</span>
                <span>--</span>
                <span>{{scope.row.timeEnd}}</span>
              </template>
            </el-table-column>

            <el-table-column prop="howLong" label="请假时长(天)" align="center"></el-table-column>

            <el-table-column prop="leaveType" label="请假类型" align="center"></el-table-column>

            <el-table-column
              :show-overflow-tooltip="true"
              prop="reason"
              label="请假理由"
              align="center"
            ></el-table-column>

            <el-table-column label="是否处理" align="center">
              <template slot-scope="scope">
                <div
                  :class="{red: (scope.row.deal === '不同意'), green: (scope.row.deal === '同意'),blue:(scope.row.deal === '未处理') }"
                >
                  <span>{{scope.row.deal}}</span>
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
          width: 200px;
        }
        span:last-child {
          width: 300px;
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

      justify-content: flex-end;
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
      dialogVisible: false,
      dialogFormVisible: false,
      EmployeeMsg: {},
      showSelect: "",
      radio: "",
      agreen: "",
      fromMsg: {
        reply: ""
      },
      currentPage:1, //初始页
pagesize:10, 

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
          name: "",
          deal: "",
          uid: ""
        }
      ]
    };
  },
  mounted() {
    this.getLeaveMsgAll();
    this.getHoliday();
  },

  methods: {
    // 点击确定
    ensure() {
      let that = this,
        r;
      if (this.EmployeeMsg.status == 1 || this.EmployeeMsg.status == 2) {
        that.dialogVisible = false;
      } else {
        if (this.radio == 1) {
          r = "同意";
          that.agreen = "同意";
          
        that.$confirm("处理结果为" + r+"确定吗").then(_ => {
          let obj = {
            reply: that.fromMsg.reply,
            id: that.EmployeeMsg.id,
            status: that.radio,
            deal: that.radio,
            leaveType: that.EmployeeMsg.leaveType,
            reason: that.EmployeeMsg.reason,
            timeEnd: that.EmployeeMsg.timeEnd,
            timeStart: that.EmployeeMsg.timeStart,
            uid: that.EmployeeMsg.uid
          };

          that.axios.post("/leave/create", that.qs.stringify(obj)).then(res => {
            that.fromMsg = {};
            that.getLeaveMsgAll();
            that.dialogVisible = false;
            that.radio = "";
          });
        });
        } else if (this.radio == 2) {
          r = "不同意";
          that.agreen = "不同意";
          
        that.$confirm("处理结果为" + r+"确定吗").then(_ => {
          let obj = {
            reply: that.fromMsg.reply,
            id: that.EmployeeMsg.id,
            status: that.radio,
            deal: that.radio,
            leaveType: that.EmployeeMsg.leaveType,
            reason: that.EmployeeMsg.reason,
            timeEnd: that.EmployeeMsg.timeEnd,
            timeStart: that.EmployeeMsg.timeStart,
            uid: that.EmployeeMsg.uid
          };

          that.axios.post("/leave/create", that.qs.stringify(obj)).then(res => {
            that.fromMsg = {};
            that.getLeaveMsgAll();
            that.dialogVisible = false;
            that.radio = "";
          });
        });
        } else {
          this.dialogVisible = false;
            that.radio = "";
        }

      }
    },
    showEmployeeDialog(index, row) {
      let that = this;
      this.dialogVisible = true;

      this.EmployeeMsg = row;

      console.log(row);

      if (row.reply == "" || row == null) {
        that.EmployeeMsg.reply = "无";
      }

      if (row.status == 1 || row.status == 2) {
        that.showSelect = false;
      } else {
        that.showSelect = true;
      }
    },
    // 获取全部员工的请假信息
    getLeaveMsgAll() {
      let that = this;
      that.axios.get("/leave/getEmployeeLeaveMsg").then(res => {
        that.tableData = res.data.data.reverse();
       

        for (let i of that.tableData) {
          i.howLong = that.getHoliday(i.timeStart, i.timeEnd);

          if (i.deal == 0 || i.deal == null) {
            i.deal = "未处理";
          } else if (i.deal == 1) {
            i.deal = "同意";
          } else if (i.status == 2) {
            i.deal = "不同意";
          }
        }
      });
    },
    // 根据id获取请假列表信息
    getLeaveMsgById() {
      let that = this,
        id = localStorage.getItem("id"),
        obj = { id };

      this.axios
        .post("/leave/getLeaveMsg", this.qs.stringify(obj))
        .then(res => {
          that.tableData = res.data.data;

          for (let i of that.tableData) {
            i.name = "张骚骚";
            i.deal = "未处理";

            i.howLong = that.getHoliday(i.timeStart, i.timeEnd);
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
      return d.getFullYear() + "-" + m + "-" + d.getDate();
    },
    datedifference(sDate1, sDate2) {
      //sDate1和sDate2是2006-12-18格式
      var dateSpan, tempDate, iDays;
      sDate1 = Date.parse(sDate1);
      sDate2 = Date.parse(sDate2);
      dateSpan = sDate2 - sDate1;
      dateSpan = Math.abs(dateSpan);
      iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
      return iDays;
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