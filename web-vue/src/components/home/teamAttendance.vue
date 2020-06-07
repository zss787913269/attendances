<template lang="html">
    <div class="main">
        <div class="title">
        <div class="left">
          <span>团队考勤信息</span>
          <span>Team Attendance</span>
        </div>
      </div>
     
        <el-table
            :data="tableData.slice((currentPages-1)*pagesizes,currentPages*pagesizes)"
            stripe
            style="width: 99.9%"
            class="dataTable"
            @filter-change="handleFilterChange"
            @selection-change="handleSelectionChange"
            :default-sort = "{prop: 'attendance_date', order: 'descending'}"
            >
            <el-table-column label="序号" type="index" width="55"></el-table-column>
           <el-table-column
                prop="username"
                label="姓名"
                align="center">
            </el-table-column>
         
            <el-table-column
                label="部门"
                align="center">
                <template slot-scope="scope">
                    <div >
                        <span>{{scope.row.department}}</span>
                    </div>
                </template>
            </el-table-column>

                <el-table-column
                label="职位"
                align="center">
                <template slot-scope="scope">
                    <div >
                        <span>{{scope.row.position}}</span>
                    </div>
                </template>
            </el-table-column>
           
           
            <el-table-column
                label="查看员工详细考勤"
                align="center"
                fixed="right">
                <template slot-scope="scope">
                
                        <el-button
                            icon="el-icon-more"
                            size="small"
                            @click="openDisplayInfo(scope.row)">
                        </el-button>
                  
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChanges"
                @current-change="handleCurrentChanges"
                :current-page="currentPages"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="10"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tableData.length">
            </el-pagination>
      
        <el-dialog
            :title="userAttendanceName + ' 出勤详情'"
            :visible.sync="displayDialogVisible"
            width="60%"
            :before-close="displayHandleClose">
          <el-table
            :data="displayTableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            stripe
            style="width: 100%"
            class="dataTable"
            :default-sort = "{prop: 'worktime', order: 'descending'}"
            >
          
            <el-table-column
                prop="worktime"
                label="打卡日期"
                sortable
                align="center">
            </el-table-column>
            <el-table-column
                label="上班打卡"
                align="center">
                <template slot-scope="scope">
                    <div :class="{red: (scope.row.status === '异常'), blue: (scope.row.status === '补签成功') }">
                        <span>{{scope.row.workup}}</span>
                    </div>
                </template>
            </el-table-column>
             <el-table-column
                label="下班打卡"
                align="center">
                <template slot-scope="scope">
                    <div :class="{red: (scope.row.status === '异常'), blue: (scope.row.status === '补签成功') }">
                       
                        <span>{{scope.row.workdown}}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column
                label="工作时长(h)"
                align="center">
                <template slot-scope="scope">
                    <span :class="{red:(scope.row.status === '异常'), blue: (scope.row.status === '补签成功') }">
                        {{scope.row.updated_duration}}
                    </span>
                </template>
            </el-table-column>
            <el-table-column
                label="状态"
                prop="status"
                align="center">
                <template slot-scope="scope">
                  <div>
                      <span :class="{red: scope.row.status === '异常', blue: (scope.row.status === '补签成功')}">
                        {{scope.row.status}}
                    </span>
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
                :total="displayTableData.length">
            </el-pagination>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return {
            //用户名
            userName: '',
            //日期
            userDateRange: [],
            //信息被选中的列表
            //默认页
            currentPages:1,
            currentPage: 1,
            //编辑对话框的展示与退出
            editDialogVisible: false,
            //原始状态
            oldStatus: '',
            // 新状态
            newStatus: '补打卡',
            // 确定修改哪行
            editId: '',
            //状态选择器内备选项
            statusOptions: [{
                value: '补打卡',
                label: '补打卡'
            },{
                value: '请假',
                label: '请假'
            }],
            //出勤详情的名字
            userAttendanceName: '',
            //个人出勤详情对话框
            displayDialogVisible: false,
            //展示个人出勤详情的当前页
            displaycurrentPage: 1,
            //打卡时间初始值
            patchTime: ["08:00","08:00"],
            //日历选择器的快捷选项
            pickerOptions: {
                shortcuts: [{
                text: '最近一周',
                onClick(picker) {
                  const end = new Date();
                  const start = new Date();
                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                  picker.$emit('pick', [start, end]);
                }
                }, {
                text: '最近一个月',
                onClick(picker) {
                  const end = new Date();
                  const start = new Date();
                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                  picker.$emit('pick', [start, end]);
                }
                }, {
                text: '最近三个月',
                onClick(picker) {
                  const end = new Date();
                  const start = new Date();
                  start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                  picker.$emit('pick', [start, end]);
                }
                }]
            },
            //个人出勤详情的数据
            displayTableData: [{
                "worktime": "",
                "status": "",
                "updated_duration": "",
                "workdown": "",
                "workup": "",
                },
                ],
            //模拟表格数据
            tableData: [
              
               ],
             
            pagesize:10,  //    每页的数据
             pagesizes:10

        }
    },
    mounted(){
        this.getEmployee();
      
    },
    methods: {

        // 展示所有员工信息 
        getEmployee(){
            let that = this;

            this.axios.get("/employee/showEmployee").then(res => {
                // that.tableData = res.data

                for(let i of res.data){
                    if(i.role != 1){
                        that.tableData.push(i)
                    }
                }
                        
            });
        },
        


        // 得到员工的id 然后根据id查找员工考勤信息
        findAttendanceById(id){
              let obj = {id} ,that = this
            this.axios.post("/attendance/getIdfindAttendance",this.qs.stringify(obj)).then((res)=>{
                   that.displayTableData = res.data.data.reverse()
                 

              for(let i in that.displayTableData){

                   //在这里获取了所有员工的工作时间 console.log(res.data.data[i].workup)
                   
                if(that.displayTableData[i].status == 1){
                  that.displayTableData[i].status = "补签成功"
                  that.displayTableData[i].updated_duration = "8.0"
                }else if(that.displayTableData[i].status == 2){
                  that.displayTableData[i].status = "异常"
                  that.displayTableData[i].updated_duration = 0
                }else if(that.displayTableData[i].status == 0){
                    
                   that.displayTableData[i].updated_duration = this.formatJobTime(res.data.data[i].workup,res.data.data[i].workdown)
                  that.displayTableData[i].status = "正常"

                }
              }

            
            })

        },
        formatJobTime(uptime,downtime){

          let up = uptime.split(":")
          let down = downtime.split(":")
              
              // 算出整数的工作时长
              let a = [up[0],down[0]].reduce((x,y)=>{
                if(y-x == 0){
                  return 0
                }else{
                  return y-x-2
                }
               
              })
              
              // 上班时间减去下班时间
              let b = [up[1],down[1]].reduce((x,y)=>{
                  return y-x
              })

            let str = ""
              if(b>30){

                  str = 5
              }else{
                  str = 0
              }

            //   如果大于8:30打卡 就是迟到
            //   如果早于18：30打卡就 早退

                let cd = 0,zt=0
                          if(up[0] >= 8 && up[1]> 30){
                                cd +=1
                            }else if(down[0]<18){
                                zt +=1
                            }else{
                                cd = zt =0;
                            }

                // console.log("迟到了---"+cd+"次")
                // console.log("早退了---"+zt+"次")

              return a+"."+str

        },
        //输入框内输入姓名，1.回车 2.点击右侧的搜索图标
        // 可以获得输入框输入的内容。将值传给后台，调用接口即可。
        searchUserName(item) {
            console.log(item);
            console.log('筛选姓名');
        },
        //日历选择器上选择好日期，即可获得此时的输入内容
        searchUserDate(item) {
            console.log(item);
            console.log('筛选日期');
        },
        //如果在下侧的表格中选中了数据，则点击此按钮，可以将选中的信息发给后台
        infoNotify() {
            console.log('发送提醒');
        },
        //处理筛选条件变化，向后台发送数据，重新获取信息就可以
        handleFilterChange(filters) {
            console.log(filters);
            console.log('筛选条件变化');
        },
        //处理复选框状态修改
        handleSelectionChange(item) {
            console.log(item);
            this.selectionItem = item

        },
       
        //处理每页显示数量变化的函数
        handleSizeChanges(item) {
            this.pagesizes = item;
            console.log(item);
            console.log('每页的数量变化');
        },
        //点击编辑按钮后，打开编辑对话框
        openEditDialog(item) {
            console.log(item);
            this.editDialogVisible = true;
            this.oldStatus = item.status;
            this.editId = item.id;
            console.log('打开编辑对话框');
        },
       
   
       //展示个人的出勤详情
       openDisplayInfo(item) {
           this.userAttendanceName = item.username;
           this.displayDialogVisible = true;

           this.findAttendanceById(item.id)
       },
       //关闭个人出勤详情页面
       displayHandleClose() {
           this.userAttendanceName = '';
           this.displaycurrentPage = 1;
           this.displayDialogVisible = false;
       },
      
       handleCurrentChange(item) {
            this.currentPage = item;
            console.log('跳转到指定页');
        },
        handleCurrentChanges(item) {
            this.currentPages = item;
            console.log('跳转到指定页');
        },
        handleSizeChange(item) {
        this.pagesize = item;
        console.log('每页的数量变化');
    },
       
    }
}
</script>

<style lang="less">
.main{
   margin-left: 40px;
   margin-top: 95px;
}
.dataTable {
    margin: 40px auto;
}
.red {
    color: red;
}
.blue {
    color: #409EFF;
}
.statusEdit {
    padding-bottom: 20px;
}
.changeStatus {
    width: 350px;
}
.editStatusDistance {
    padding-top: 7px;
}
.displayTable {
    margin-bottom: 40px;
}
.title{
  margin-bottom: 20px;
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

}
</style>
