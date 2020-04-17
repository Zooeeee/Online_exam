<template>
    <div>
      <!-- 查询 -->
      <el-form :inline="true" :model="selectCondition" class="demo-form-inline">
        <el-form-item label="题型" size="small" >
          <el-select v-model="selectCondition.type" placeholder="题型" filterable clearable>
            <el-option :label="item" :value="item" v-for="(item, index) in condition.type" :key="index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="科目" size="small"  >
          <el-select v-model="selectCondition.subject" placeholder="科目" filterable clearable>
            <el-option :label="item" :value="item" v-for="(item, index) in condition.subject" :key="index">
            </el-option>
          </el-select>
        </el-form-item>
         <el-form-item label="出题人" size="small"  >
        <el-select v-model="selectCondition.creator" placeholder="出题人" filterable clearable>
            <el-option :label="item" :value="item" v-for="(item, index) in condition.creator" :key="index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="难度" size="small"   >
          <el-select v-model="selectCondition.difficulty" placeholder="难度" filterable clearable>
            <el-option :label="item" :value="item" v-for="(item, index) in condition.difficulty" :key="index">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="题干" size="small"  >
            <el-input v-model="selectCondition.name" placeholder="题干" ></el-input>
        </el-form-item>
        <el-form-item style="margin-left:20px">
          <el-button icon="el-icon-search" circle @click = "conditionSubmit"></el-button>
           <el-button icon="iconfont icon-reset" circle @click = "conditionReset" style="margin-left=30px"></el-button>
        </el-form-item>
      </el-form>
      <!-- ===================================================== -->
      <!-- table -->
       <el-table
      :data="tableData"
      style="width: 100%"
      :height = "600"
      v-show="tableShow"
      >
      <el-table-column
        label="#"
        align="center"
        type="index"
        min-width="50">
      </el-table-column>
      <!-- 题干 -->
      <el-table-column
        label="题干"
         min-width="200"
        align="center"
        >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>解析: {{ scope.row.analysis }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <!-- 科目 -->
      <el-table-column
        label="科目"
        align="center"
        min-width="100">
        <template slot-scope="scope">
          <span   >{{ scope.row.subject }}</span>
        </template>
      </el-table-column>
      <!-- 题型 -->
      <el-table-column
        label="题型"
        align="center"
         min-width="100">
        <template slot-scope="scope">
          <span   >{{ scope.row.type }}</span>
        </template>
      </el-table-column>
      <!-- 难度 -->
      <el-table-column
        label="难度"
        align="center"
        min-width="100">
        <template slot-scope="scope">
          <span   >{{ scope.row.difficulty }}</span>
        </template>
      </el-table-column>
      <!-- 创建者 -->
      <el-table-column
        label="创建者"
        align="center"
         min-width="100">
        <template slot-scope="scope">
          <span   >{{ scope.row.creator }}</span>
        </template>
      </el-table-column>
      <!-- 操作 -->
      <el-table-column
        label="操作"
         min-width="150"
        align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- dialog -->
     <el-dialog title="修改题目" :visible.sync="dialogFormVisible" :center="true" >
      <!-- 此处利用differ算法 刷新该组件 -->
      <EditProblem :dialogInfo = "dialogInfo" :key="dialogInfo.id" @problemDialogShow = problemDialogShow ></EditProblem>
    </el-dialog>
    </div>
</template>

<script>
import util from '@/views/Problem/js/util.js'
import EditProblem from '@/views/Problem/EditProblem.vue'
export default {
  components: { EditProblem },
  data () {
    return {
      tableShow: false,
      condition: {
      },
      selectCondition: {
        name: '',
        creator: '',
        difficulty: '',
        type: '',
        subject: ''
      },
      dialogInfo: {},
      dialogFormVisible: false,
      tableData: [{
        // id: 1,
        // name: '以下哪个不是四大名著',
        // subject: '语文',
        // type: '单选',
        // answer: '封神榜',
        // analysis: '中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。',
        // difficulty: '简单',
        // creator: '老师1'
      }]
    }
  },
  created () {

  },
  updated () {
  },
  mounted () {
    // console.log('mounted')
    // 渲染条件选择的选项
    this.axios.post('/api/problem/getSelectCondition')
      .then(res => {
      // // console.log(res.data)
        this.condition = res.data
      })
      .catch(err => {
        console.error(err)
      })
    // 监听子组件传来的值
    this.$store.dispatch('getProblemVoCount')
  },
  methods: {
    // 子组件点击修改后 关闭dialog
    problemDialogShow (bool) {
      // // console.log('problemDialogShow')
      this.dialogFormVisible = bool
      util.tableDataLikeAxios(this.selectCondition, this)
    },
    // 点击编辑
    handleEdit (index, row) {
      // console.log('选取的第几行' + row.id)
      this.dialogFormVisible = true
      this.dialogInfo = row
    },
    // 点击删除
    handleDelete (index, row) {
      this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.axios.post('/api/problem/deleteProblemById?id=' + row.id)
          .then(res => {
            if (res.data === '删除成功') {
              this.$notify.info({
                title: '消息',
                message: res.data
              })
              util.tableDataLikeAxios(this.selectCondition, this)
            }
          })
          .catch(err => {
            console.error(err)
          })
        this.$message({
          type: 'success',
          message: '删除成功!'
        }) // then-end
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      }) // catch-end
      // console.log(row.id)
      // 重新渲染页面
    },
    // 搜索条件重置
    conditionReset () {
      this.selectCondition = {
        name: '',
        creator: '',
        difficulty: '',
        type: '',
        subject: ''
      }
    },
    // 提交搜索条件
    conditionSubmit () {
    //  // console.log(this.selectCondition)
      // this.axios.post('/api/problem/getConditionResult', this.selectCondition)
      //   .then(res => {
      //     // // console.log(res)
      //     this.tableData = res.data
      //     this.tableShow = true
      //   })
      //   .catch(err => {
      //     console.error(err)
      //   })
      util.tableDataLikeAxios(this.selectCondition, this)
    }
  }
}
</script>

<style scoped lang="less">
.el-input{
  width: 300px;
}
</style>
