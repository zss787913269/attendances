<template>
  <div class="main">
    <!-- 添加员工信息 -->
    <div class="dialog">
      <el-dialog title="添加员工信息" :visible.sync="dialogAdd" width="80%">
        <div class="dialog-add">
          <h1>员工信息</h1>
          <div class="s-msg">
            <div class="add-left">
              <el-form
                :model="form"
                ref="form"
                label-width="100px"
                class="demo-ruleForm"
                label-position="right"
              >
                <el-form-item label="姓名：" prop="username">
                  <el-input style="width:400px" v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item label="性别：" prop="gender">
                  <el-radio-group v-model="form.gender">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="部门：" prop="department">
                  <el-select style="width:400px" v-model="form.department" placeholder="请选择部门">
                    <el-option label="开发部" value="开发部"></el-option>
                    <el-option label="人事部" value="人事部"></el-option>
                    <el-option label="财务部" value="财务部"></el-option>
                    <el-option label="销售部" value="销售部"></el-option>
                     <el-option label="董事会" value="董事会"></el-option>
                  </el-select>
                </el-form-item>

                <el-form-item label="职位：" prop="position">
                  <el-input style="width:400px" v-model="form.position"></el-input>
                </el-form-item>

                <el-form-item label="员工编号：" prop="num">
                  <el-input type="number" style="width:400px" v-model="form.num"></el-input>
                </el-form-item>

                <el-form-item label="登录密码：" prop="password">
                  <el-input style="width:400px" v-model="form.password"></el-input>
                </el-form-item>
              </el-form>
            </div>
            <div class="add-right">
              <el-form :model="form" ref="form" label-width="100px" class="demo-ruleForm">
                <el-form-item label="年龄：" prop="age">
                  <el-input type="number" style="width:400px" v-model="form.age"></el-input>
                </el-form-item>

                <el-form-item label="手机号码：" prop="phone">
                  <el-input type="number" style="width:400px" v-model="form.phone"></el-input>
                </el-form-item>

                <el-form-item label="入职时间：" prop="time">
                  <el-date-picker
                    style="width:400px"
                    v-model="form.time"
                    type="date"
                    placeholder="选择日期"
                  ></el-date-picker>
                </el-form-item>

                <el-form-item label="电子邮件：" prop="email">
                  <el-input type="email" style="width:400px" v-model="form.email"></el-input>
                </el-form-item>

                <el-form-item label="籍贯：" prop="address">
                  <el-input style="width:400px" v-model="form.address"></el-input>
                </el-form-item>
              </el-form>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogAdd = false">取 消</el-button>
          <el-button type="primary" @click="addEmployee()">确 定</el-button>
        </span>
      </el-dialog>
    </div>

    <!-- 修改员工信息 -->
    <div class="dialog">
      <el-dialog title="修改员工信息" :visible.sync="editDialog" width="80%">
        <div class="dialog-add">
          <h1>修改员工信息</h1>
          <div class="s-msg">
            <div class="add-left">
              <el-form
                :model="editValue"
                ref="form"
                label-width="100px"
                class="demo-ruleForm"
                label-position="right"
              >
                <el-form-item label="姓名：" prop="username">
                  <el-input style="width:400px" v-model="editValue.username"></el-input>
                </el-form-item>
                <el-form-item label="性别：" prop="gender">
                  <el-radio-group v-model="editValue.gender">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="部门：" prop="department">
                  <el-select style="width:400px" v-model="editValue.department" placeholder="请选择部门">
                    <el-option label="开发部" value="开发部"></el-option>
                    <el-option label="人事部" value="人事部"></el-option>
                    <el-option label="财务部" value="财务部"></el-option>
                    <el-option label="销售部" value="销售部"></el-option>
                      <el-option label="董事会" value="董事会"></el-option>
                  </el-select>
                </el-form-item>

                <el-form-item label="职位：" prop="position">
                  <el-input style="width:400px" v-model="editValue.position"></el-input>
                </el-form-item>

                <el-form-item label="员工编号：" prop="num">
                  <el-input type="number" style="width:400px" v-model="editValue.num"></el-input>
                </el-form-item>

                <el-form-item label="登录密码：" prop="password">
                  <el-input style="width:400px" v-model="editValue.password"></el-input>
                </el-form-item>
              </el-form>
            </div>
            <div class="add-right">
              <el-form :model="editValue" ref="form" label-width="100px" class="demo-ruleForm">
                <el-form-item label="年龄：" prop="age">
                  <el-input type="number" style="width:400px" v-model="editValue.age"></el-input>
                </el-form-item>

                <el-form-item label="手机号码：" prop="phone">
                  <el-input type="number" style="width:400px" v-model="editValue.phone"></el-input>
                </el-form-item>

                <el-form-item label="入职时间：" prop="time">
                  <el-date-picker
                    style="width:400px"
                    v-model="editValue.time"
                    type="date"
                    placeholder="选择日期"
                  ></el-date-picker>
                </el-form-item>

                <el-form-item label="电子邮件：" prop="email">
                  <el-input type="email" style="width:400px" v-model="editValue.email"></el-input>
                </el-form-item>

                <el-form-item label="籍贯：" prop="address">
                  <el-input style="width:400px" v-model="editValue.address"></el-input>
                </el-form-item>
              </el-form>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="editDialog = false">取 消</el-button>
          <el-button type="primary" @click="editEmployee()">确 定</el-button>
        </span>
      </el-dialog>
    </div>


    <!-- 标题栏 -->
    <div class="title">
      <div class="left">
        <span>人事管理</span>
        <span>Personnel</span>
      </div>

    
    </div>
    <!-- 查看员工档案 -->
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
               <p>
                <span>员工编号：</span><span>{{EmployeeMsg.num}}</span>
              </p>
               <p>
                <span>登录密码：</span><span>{{EmployeeMsg.password}}</span>
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
                <span>入职时间：</span><span>{{EmployeeMsg.jointime}}</span>
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
      <div class="inputbox">
        <span>员工姓名/编号 ：</span>
        <input v-model="searchMsg" type="text" />
        <el-button type="danger" @click="searchEmployee">查询</el-button>
        <el-button @click="clearSearch">清除查询</el-button>
      </div>
      <!-- 操作 -->
      <div class="tablemain">
        <div class="crudbox">
          <el-button type="danger" icon="el-icon-plus" @click="openEmployee()">添加</el-button>
          <el-button type="info" icon="el-icon-delete" @click="deleteEmployess">删除</el-button>
          <!-- <el-button type="success" icon="el-icon-folder-add">导入</el-button> -->
          <el-button type="success" icon="el-icon-folder-remove" @click="exportToExcel">导出</el-button>
        </div>

        <!-- 表格 -->
        <div class="tablebox">
          <el-table
            ref="multipleTable"
            :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
           
            :border="true"
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
                <el-button
                  size="mini"
                  type="danger"
                  @click="openEditEmployeee(scope.$index, scope.row)"
                >修改</el-button>
              </template>
            </el-table-column>
            <el-table-column prop="username" label="姓名" width="140"></el-table-column>
            <el-table-column prop="gender" label="性别" width="140"></el-table-column>
            <el-table-column prop="department" label="部门" width="140"></el-table-column>
            <el-table-column prop="position" label="职位" width="140"></el-table-column>
            <el-table-column prop="phone" label="电话号码"></el-table-column>
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
      editDialog:false, // 显示编辑框
      selectionVal: [],// 选择数据
      searchMsg: [],//搜索数据
      formLabelWidth: "120px",
      radioGender: 1,
      EmployeeMsg:{},
      dialogAdd: false, //添加员工信息
      dialogVisible: false, //显示员工详细信息
      value1: "2020-02-14",
      editValue:{//编辑信息
        username: "",
        age: "",
        gender: "",
        position: "",
        department: "",
        num: "",
        phone: "",
        password: "",
        jointime: "",
        email: "",
        address: "",
        role:""
      },
      form: {
        username: "",
        age: "",
        gender: "",
        position: "",
        department: "",
        num: "",
        phone: "",
        password: "",
        jointime: "",
        email: "",
        address: "",
        role: 0
      },
      tableData: [
        {
          id: "",
          gender: "",
          position: "",
          department: "",
          username: "",
          phone: "",
          email: "",
          num: "",
          jointime: "",
          role: "",
          password: "",
          address: ""
        }
      ],
      currentPage:1, //初始页
      pagesize:10
    };
  },
  mounted() {
    this.showEmployee();
  },
  // 监听输入框是否为空
  watch: {
    searchMsg(value) {
      if (value == "") {
        //  console.log(123);
        this.showEmployee();
      }
    }
  },
  methods: {
    // 搜索员工信息
    searchEmployee() {
      if (this.searchMsg == "") {
        this.$message({
          offset: 100,
          type: "error",
          message: "请输入关键字!",
          duration: 2000,
          showClose: true
        });
      } else {
        let _this = this;
        let obj = {
          username: this.searchMsg
        };
        this.axios
          .post("/employee/findByNameLike", this.qs.stringify(obj))
          .then(res => {
            _this.tableData = res.data.data;

              for(let i of _this.tableData){
                if(i.gender == 0){
                  i.gender = "女"
                }else{
                  i.gender = "男"
                }
              }

          });
      }
    },
    // 导出excel表格
    exportToExcel() {
      this.$confirm("是否导出成xls文件？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message({
            offset: 100,
            type: "success",
            message: "导出成功!",
            duration: 2000
          });

          let newArr = this.tableData.map(function(item) {
            return {
              序号: item["id"],
              名字: item["username"],
              密码: item["password"],
              性别: item["gender"],
              职位: item["department"],
              职位: item["position"],
              角色: item["role"],
              员工编号: item["num"],
              电话号码: item["phone"],
              加入公司时间: item["jointime"],
              邮件: item["email"],
              籍贯: item["address"]
            };
          });

          var filename = "员工信息表";
          let ws = this.XLSX.utils.json_to_sheet(newArr);
          let wb = this.XLSX.utils.book_new();
          this.XLSX.utils.book_append_sheet(wb, ws, "People");
          this.XLSX.writeFile(wb, filename + ".xlsx");
        })
        .catch(() => {
          this.$message({
            offset: 100,
            type: "info",
            message: "已取消导出",
            duration: 2000
          });
        });
    },
    // 清除查询
    clearSearch() {
      this.searchMsg = "";
    },
    // 打开添加员工信息
    openEmployee() {
      this.dialogAdd = true;
    },
     getTime(){
        let t= new Date(),y = t.getFullYear(), m = t.getMonth()+1,d = t.getDate()
        return y+"-"+m+"-"+d
    },
    // 添加员工信息
    addEmployee() {
      let _this = this;
      if (
        _this.form.name == "" ||
        _this.form.gender == "" ||
        _this.form.position == "" ||
        _this.form.department == "" ||
        _this.form.num == "" ||
        _this.form.password == "" ||
        _this.form.time == ""
      ) {
        _this.$message.error({
          message: "表单未完填写完成",
          offset: 200,
          duration: 2000,
          showClose: true
        });
      } else {
      
        // 1是男 0是女
        if ((_this.form.gender == "女")) {
          _this.form.gender = 0;
        } else if ((this.form.gender == "男")) {
          _this.form.gender = 1;
        }

        _this.form.jointime = _this.getTime()

        

        this.axios
          .post("/employee/editOrAddMsg", this.qs.stringify(this.form))
          .then(res => {
            // console.log(res.data);

            _this.showEmployee();

            if (res.data.code == 200) {
              _this.dialogAdd = false;
              _this.$message({
                type: "success",
                message: "添加成功",
                offset: 200,
                duration: 2000,
                showClose: true
              });
            }
          });
      }
    },
    // 展示所有员工信息
    showEmployee() {
      let _this = this;
      this.axios.get("/employee/showEmployee").then(res => {
        this.tableData = res.data;

        for (let i in this.tableData) {
          if (_this.tableData[i].gender == 0) {
            _this.tableData[i].gender = "女";
          } else if(_this.tableData[i].gender == 1){
            _this.tableData[i].gender = "男";
          }
        }
      });
    },
    // 删除员工
    deleteEmployess(rows) {
      let _this = this;

      if(this.selectionVal.length == 0){
          _this.$message({
                  offset: 100,
                  type: "error",
                  message: "还未选择人员",
                  duration: 1000,
                  showClose:true
                });
      }
      else if(this.selectionVal.length == 1){
      this.$confirm("是否删除此员工", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log(_this.selectionVal[0].id);
          let obj = {
            id:_this.selectionVal[0].id
          };
          _this.axios
            .post("/employee/deleteByIds", this.qs.stringify(obj))
            .then(res => {
              if (res.data.code == 200) {
                _this.showEmployee();
                _this.$message({
                  offset: 100,
                  type: "success",
                  message: "删除成功!",
                  duration: 2000
                });
              }
            });
        })
        .catch(() => {
          this.$message({
            offset: 100,
            type: "info",
            message: "已取消删除",
            duration: 2000
          });
        });
      }else{
            _this.$message({
                  offset: 100,
                  type: "error",
                  message: "只能删除单个",
                  duration: 2000,
                  showClose:true
                });
      }


    
    },
    // 打开修改窗口
    openEditEmployeee(index,row){
        this.editValue = row
        this.editDialog = true
    },
    // 修改员工信息
    editEmployee(index, row) {
           // 1是男 0是女
        let _this = this

        if ((_this.editValue.gender == "女")) {
          _this.editValue.gender = 0;
        } else if ((_this.editValue.gender == "男")) {
          _this.editValue.gender = 1;
        }

      console.log(this.editValue);

        _this.editValue.jointime = "2019年10月8日";

        this.axios
          .post("/employee/editOrAddMsg", this.qs.stringify(this.editValue))
          .then(res => {
            // console.log(res.data);

            _this.showEmployee();

            if (res.data.code == 200) {
              _this.editDialog = false;
              _this.$message({
                type: "success",
                message: "修改成功",
                offset: 200,
                duration: 2000,
                showClose: true
              });
            }
          });


        
        console.log(this.editValue)


    },
    // 查看某个员工的详细信息
    showEmployeeDialog(index, row) {
      this.EmployeeMsg = row
      this.dialogVisible = true;
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