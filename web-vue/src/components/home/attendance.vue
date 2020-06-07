<template lang="html">
    <div class="main">
      <div class="title">
        <div class="left">
          <span>个人考勤信息</span>
          <span>Personal Attendance</span>
        </div>
      </div>
    
        <el-row :gutter="10">
           
            <el-col :span="17">
               
               
            </el-col>
         
        </el-row>
        <el-table
            :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            stripe
            style="width: 100%"
            class="dataTable"
            :default-sort = "{prop: 'worktime', order: 'descending'}"
            >
             <el-table-column
              type="index"
              width="50">
            </el-table-column>
          
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
                    <div :class="{red: (scope.row.status === '异常'), success: (scope.row.status === '补签成功')}">
                        <span>{{scope.row.workup}}</span>
                    </div>
                </template>
            </el-table-column>
             <el-table-column
                label="下班打卡"
                align="center">
                <template slot-scope="scope">
                    <div :class="{red: (scope.row.status === '异常'), success: (scope.row.status === '补签成功')}">
                       
                        <span>{{scope.row.workdown}}</span>
                    </div>
                </template>
            </el-table-column>
            <el-table-column
                label="工作时长(h)"
                align="center">
                <template slot-scope="scope">
                    <span :class="{red:(scope.row.status === '异常'),success: (scope.row.status === '补签成功') }">
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
                      <span :class="{red: scope.row.status === '异常',success: (scope.row.status === '补签成功')}">
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
            :total="tableData.length">
        </el-pagination>
      
      
    </div>
</template>

<script>
export default {
    data() {
        return {
            currentPage:1, //初始页
            pagesize:10,    //    每页的数据　
            //用户名
            userName: '',
            //日期
            userDateRange: [],
            //默认页
            currentPage: 1,
            //编辑对话框的展示与退出
           
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
            len:"",
         
            //模拟表格数据
            tableData: [],

        }
    },
    created(){
          this.getAttendanceMsg()
    },
    updated(){
      // 计算一共有几页
        
  
    },
    methods: {
     

        // 获取个人员工考勤信息
        getAttendanceMsg(){
            let id = localStorage.getItem("id") , obj = {id} ,that = this
            this.axios.post("/attendance/getIdfindAttendance",this.qs.stringify(obj)).then((res)=>{
              
              that.tableData = res.data.data.reverse()

    






                 



              for(let i in that.tableData){


            

                    let msgData =  that.tableData[i]



                  

                  // if(msgData.workup >  )


                if(msgData.status == 1){
                  msgData.status = "补签成功"
                  msgData.updated_duration = "8.0"
                }else if(msgData.status == 2){
                msgData .status = "异常"
                  msgData.updated_duration = 0
                }else if(msgData.status == 0){
                  msgData.updated_duration = this.formatJobTime(res.data.data[i].workup,res.data.data[i].workdown)

                   if(msgData.updated_duration < 8){
                     msgData.status = "异常"
                   }else{
                     msgData.status = "正常"
                   }
                
                }



                // 
                          // let myTime =  that.tableData[i].workup
          
                          // let up = myTime.split(":")

                          // if(up[0] > 9){
                          //      msgData.status = "迟到"
                          // }else if (up[0]>9 && up[1] > 0 ){
                          //        msgData.status = "迟到"
                          // }else{
                          //   console.log("考勤正常")
                          // }
 
              }

  
            })
        },
        // 计算工作时间 计算每个班的时间（超出半小时按半小时算，不足半小时直接忽略
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


            
              return a+"."+str

        },
        
        //日历选择器上选择好日期，即可获得此时的输入内容
        searchUserDate(item) {
            console.log(item);
        },
     
        //要跳转的页
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
}
</script>

<style lang="less">
.success{
  color: #67C23A;
}
.main{
   margin-left: 40px;
   margin-top: 95px;
   width: 100%;
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
