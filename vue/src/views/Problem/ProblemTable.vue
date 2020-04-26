<template>
  <div class = "main" >
    <el-table
     v-loading.lock="loading"
      :data="tableData"
      style="width: 100%"
      :height = "600"
      >
      <el-table-column
        label="#"
        align="center"
        type="index"
        min-width ="50">
      </el-table-column>
      <!-- 题干 -->
      <el-table-column
        label="题干"
        min-width ="200"
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
        min-width ="100">
        <template slot-scope="scope">
          <span   >{{ scope.row.subject }}</span>
        </template>
      </el-table-column>
      <!-- 题型 -->
      <el-table-column
        label="题型"
        align="center"
        min-width ="100">
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
    <!-- 分页 -->
    <el-row>
      <el-col :xs='24' :sm='24' :md='{span:18,offset:3}' :lg='{span:14,offset:5}' :xl='{span:14,offset:5}'>
         <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          current-page.sync="1"
          :page-sizes="[10, 20, 50]"
          :page-size="10"
          layout="sizes, prev, pager, next"
          :total="this.$store.getters.getProblemCount">
        </el-pagination>
      </el-col>
    </el-row>
    <el-dialog title="修改题目" :visible.sync="dialogFormVisible" :center="true" >
      <!-- 此处利用differ算法 刷新该组件 -->
      <EditProblem :dialogInfo = "dialogInfo" :key="dialogInfo.id" @problemDialogShow = problemDialogShow ></EditProblem>
    </el-dialog>
  </div>
</template>

<script>
// 将axios请求封装成一个函数，并且实现缓存效果
import util from '@/views/Problem/js/util.js'
import EditProblem from '@/views/Problem/EditProblem.vue'
export default {
  components: { EditProblem },
  data () {
    return {
      loading: true,
      pageInfo: {
        size: 10,
        page: 1
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
  methods: {
    // 子组件点击修改后 关闭dialog
    problemDialogShow (bool) {
      // console.log('problemDialogShow')
      this.dialogFormVisible = bool
      util.tableDataPageAxios(this.pageInfo.page, this.pageInfo.size, this)
    },
    // 点击编辑
    handleEdit (index, row) {
      console.log('选取的第几行' + row.id)
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
              util.tableDataPageAxios(this.pageInfo.page, this.pageInfo.size, this)
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
    handleSizeChange (size) {
      this.pageInfo.size = size
      console.log('尺寸' + this.pageInfo.size)
      util.tableDataPageAxios(this.pageInfo.page, this.pageInfo.size, this)
    },
    handleCurrentChange (page) {
      this.pageInfo.page = page
      console.log('当前页面：' + this.pageInfo.page)
      util.tableDataPageAxios(this.pageInfo.page, this.pageInfo.size, this)
    }
  },
  mounted () {
    this.$store.dispatch('getProblemVoCount')
    util.tableDataPageAxios(this.pageInfo.page, this.pageInfo.size, this)
  }
}

</script>

<style scoped lang="less">
// .main{
//   display: flex;
//   flex-direction: column;
//   justify-content: center;
// }
</style>
