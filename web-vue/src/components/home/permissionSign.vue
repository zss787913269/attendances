<template>
  <div class="main">
    <!-- 添加员工信息 -->

    <!-- 修改员工信息 -->

    <!-- 标题栏 -->
    <div class="title">
      <div class="left">
        <span>考勤管理</span>
        <span>permissionSign</span>
      </div>
    </div>

    <div class="dialog">
      <el-dialog title="查看请假详情单" :visible.sync="dialogVisible" width="80%">
        <div class="dialog-msg">
          <h1>补签详情单</h1>
          <div class="msg">
            <div class="msg-left">
              <p>
                <span>申请人：</span>
                <span>{{EmployeeMsg.name}}</span>
              </p>
              <p>
                <span>补签时间：</span>
                <span>{{EmployeeMsg.timeStart}}</span>
              </p>
              <p>
                <span>补签理由</span>
                <span>{{EmployeeMsg.reason}}</span>
              </p>
            </div>
            <div class="msg-right">
              <p v-if="!showSelect">
                <span>处理结果：</span>
                <span>{{EmployeeMsg.status}}</span>
              </p>

              <p>
                <span>提交时间：</span>
                <span>{{EmployeeMsg.createtime}}</span>
              </p>
            </div>
          </div>
          <h1 v-if="showSelect" style="margin-top:50px">
            <span>是否同意：</span>
            <span>
              <el-radio v-model="radio" label="1">同意</el-radio>
              <el-radio v-model="radio" label="2">不同意</el-radio>
            </span>
          </h1>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button type="info" @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="ensure">确 定</el-button>
        </span>
      </el-dialog>
    </div>

    <div class="search-box">
      <div class="tablemain">
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
            <el-table-column label="补签日期" width="200" align="center">
              <template slot-scope="scope">
                <span>{{scope.row.timeStart}}</span>
              </template>
            </el-table-column>

            <el-table-column
              :show-overflow-tooltip="true"
              prop="reason"
              label="补签理由"
              align="center"
            ></el-table-column>

            <el-table-column label="是否处理" align="center">
              <template slot-scope="scope">
                <div
                  :class="{red: (scope.row.status === '不同意'), green: (scope.row.status === '同意'),blue:(scope.row.status === '未处理') }"
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
      currentPage:1, //初始页
      pagesize:10,
      dialogVisible: false,
      dialogFormVisible: false,
      EmployeeMsg: {},
      showSelect: "",
      radio: "",
      agreen: "",
      fromMsg: {
        reply: ""
      },

      tableData: [
        {
          timeStart: "",
          timeEnd: "",
          reason: "",
          reply: "",
          status: "",
          leaveType: "",
          uid: "",
          name: "",
          deal: "",
          uid: ""
        }
      ]
    };
  },
  mounted() {
    this.getLeaveMsgAll();
  },

  methods: {
    // 点击确定
    ensure() {

     let that = this,r;
        
      if (
        this.EmployeeMsg.status == "同意" ||
        this.EmployeeMsg.status == "不同意"
      ) {
        that.dialogVisible = false;
      } else {
        if (this.radio == 1) {
          r = "同意";
          that.agreen = "同意";
          that.$confirm("处理结果为" + r).then(_ => {
            let obj = {
              id: that.EmployeeMsg.id,
              status: that.radio,
              reason: that.EmployeeMsg.reason,
              timeStart: that.EmployeeMsg.timeStart,
              uid: that.EmployeeMsg.uid,
              createtime: that.EmployeeMsg.createtime
            };
            that.axios
              .post("/sign/create", that.qs.stringify(obj))
              .then(res => {
                that.fromMsg = {};
                that.getLeaveMsgAll();
                that.dialogVisible = false;
             
              });

                let mytime = this.EmployeeMsg.timeStart
                let myuid = this.EmployeeMsg.uid
                let mytype = this.radio
                let myobj = {
                    worktime:mytime,
                    eid:myuid,
                    type:mytype
                }

                    that.axios.post('/attendance/findByWorktimeAndEid',that.qs.stringify(myobj)).then((res)=>{
                            
                               that.radio = "";
            })

          });
        } else if (this.radio == 2) {
          r = "不同意";
          that.agreen = "不同意";
          that.$confirm("处理结果为" + r).then(_ => {
            let obj = {
              id: that.EmployeeMsg.id,
              status: that.radio,
              reason: that.EmployeeMsg.reason,
              timeStart: that.EmployeeMsg.timeStart,
              uid: that.EmployeeMsg.uid,
              createtime: that.EmployeeMsg.createtime
            };
            that.axios
              .post("/sign/create", that.qs.stringify(obj))
              .then(res => {
                that.fromMsg = {};
                that.getLeaveMsgAll();
                that.dialogVisible = false;
           
              });

                let mytime = this.EmployeeMsg.timeStart
                let myuid = this.EmployeeMsg.uid
                let mytype = this.radio
                let myobj = {
                    worktime:mytime,
                    eid:myuid,
                    type:mytype
                }

                    that.axios.post('/attendance/findByWorktimeAndEid',that.qs.stringify(myobj)).then((res)=>{
                            
                                 that.radio = "";
            })
          });
        } else if (this.radio == "") {
          that.dialogVisible = false;
          that.radio = "";
        }
      }
    },
    showEmployeeDialog(index, row) {
      let that = this;
      this.dialogVisible = true;

      console.log(row)

      this.EmployeeMsg = row;

      if (row.status == "不同意" || row.status == "同意") {
        that.showSelect = false;
      } else {
        that.showSelect = true;
      }
    },
    // 获取全部员工的请假信息
    getLeaveMsgAll() {
      let that = this;
      that.axios.get("/sign/findAll").then(res => {
        that.tableData = res.data;

        for (let i of that.tableData) {
          if (i.status == "0" || i.status == null) {
            i.status = "未处理";
          } else if (i.status == "1") {
            i.status = "同意";
          } else if (i.status == "2") {
            i.status = "不同意";
          }
        }
      });
    },
    // 根据id获取请假列表信息

    handleClose() {
      this.dialogFormVisible = false;
    },
    handleTimeoff() {
      this.dialogFormVisible = true;
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
    }
  }
};
</script>