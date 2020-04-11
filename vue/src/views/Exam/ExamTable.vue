<template>
  <div>
    <el-table
      :data="tableData"
      style="width: 100%"
      :height = "650"
      >
      <el-table-column
        label="#"
        align="center"
        type="index"
        width="50">
      </el-table-column>
      <!-- 考试名称 -->
      <el-table-column
        label="考试名称"
        width="300"
        align="center"
        >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>包含题目:
              <el-divider></el-divider>
              填空题：
              <ul v-for="(item, index) in scope.row.blankList" :key="index+item">
                {{index + 1}}:{{ item }}
              </ul>
              <el-divider></el-divider>
              单选题：
              <ul v-for="(item, index) in scope.row.choiceList" :key="index+item">
                {{index + 1}}:{{ item }}
              </ul>
                <el-divider></el-divider>
              判断题：
              <ul v-for="(item, index) in scope.row.judgeList" :key="index+item">
                {{index + 1}}:{{ item }}
              </ul>
            </p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
    <!-- 总分数 -->
      <el-table-column
        label="总分数"
        align="center"
        width="100">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>各题型分数:
              <el-divider></el-divider>
              填空题:{{scope.row.blankMark}}
              <el-divider></el-divider>
              单选题:{{scope.row.choiceMark}}
              <el-divider></el-divider>
              判断题:{{scope.row.judgeMark}}
            </p>
            <div slot="reference" class="name-wrapper">
              <el-tag>{{ scope.row.totalMark }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <!-- 创建者 -->
      <el-table-column
        label="创建者"
        align="center"
        width="100">
        <template slot-scope="scope">
          <span   >{{ scope.row.creatorName }}</span>
        </template>
      </el-table-column>
       <!-- 时间 -->
      <el-table-column
        label="考试时长"
        align="center"
        width="100">
        <template slot-scope="scope">
          <span   >{{ scope.row.time }}</span>
        </template>
      </el-table-column>
      <!-- 操作 -->
      <el-table-column
        label="操作"
         width="200"
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
          :total="this.pageInfo.count">
        </el-pagination>
      </el-col>
    </el-row>
    <!-- 编辑的dialog -->
    <el-dialog title="编辑题目" :visible.sync="editDialogFormVisible" center  >
      <!-- 此处利用diff算法  把key值动态修改 使其重新挂载 -->
     <EditExam @closeEditProblemDialog = "closeED" :row = "this.row" :key="this.row.id" ></EditExam>
   </el-dialog>
  </div>
</template>

<script>
import EditExam from '@/views/Exam/EditExam.vue'
import util from '@/views/Exam/js/utils.js'
export default {
  components: { EditExam },
  data () {
    return {
      row: {},
      editDialogFormVisible: false,
      pageInfo: {
        size: 10,
        page: 1,
        count: 10
      },
      tableData: []
    }
  },
  created () {

  },
  mounted () {
    util.examPageAxios(this)
  },
  methods: {
    closeED (bool) {
      this.editDialogFormVisible = bool
    },
    // 点击编辑
    handleEdit (index, row) {
      console.log('选取的id' + row.id)
      this.row = row
      this.editDialogFormVisible = true
    },
    // 点击删除
    handleDelete (index, row) {
      // console.log('选取的id' + row.id)
      const that = this
      this.axios.post('/api/exam/deleteExamById?id=' + row.id)
        .then(res => {
          /// console.log(res)
          util.examPageAxios(that)
        })
        .catch(err => {
          console.error(err)
        })
    },
    // 改变页面尺寸和页码
    handleSizeChange (size) {
      this.pageInfo.size = size
      console.log('尺寸' + this.pageInfo.size)
      util.examPageAxios(this)
    },
    handleCurrentChange (page) {
      this.pageInfo.page = page
      console.log('当前页面：' + this.pageInfo.page)
      util.examPageAxios(this)
    }
  }
}
</script>

<style scoped lang="less">

</style>
