<template>
  <div >
    <el-tooltip content="根据学生名字或考试名字模糊查询" placement="right" style="margin:20px 0 20px 25px;">
      <el-input
        placeholder="请输入内容"
        prefix-icon="el-icon-search"
        v-model="search"
        @input="searchChange"
        style="width:300px;"
      >
      </el-input>
    </el-tooltip>
    <el-table
     v-loading.lock="loading"
      :data="searchData"
      style="width: 100%"
      :height = "600"
    >
      <el-table-column
        label="#"
        align="center"
        type="index"
        min-width="50">
      </el-table-column>
       <!-- 考生 -->
      <el-table-column
        label="考生"
        align="center"
        min-width="100">
        <template slot-scope="scope">
         <el-avatar :size="50" :src="$store.getters.getServerUrl + '/img/avatar/' + scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <!-- 考生姓名 -->
      <el-table-column
        label="考生姓名"
        align="center"
        min-width="150">
        <template slot-scope="scope">
          <span   >{{ scope.row.stuNickname }}</span>
        </template>
      </el-table-column>
      <!-- 考试名称 -->
      <el-table-column
        label="考试名称"
        align="center"
        min-width="150">
        <template slot-scope="scope">
          <span   >{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <!-- 考试分数 -->
      <el-table-column
        label="考试分数"
        align="center"
        min-width="150">
        <template slot-scope="scope">
          <span   >{{ scope.row.totalMark }}</span>
        </template>
      </el-table-column>
      <!-- 考试时间 -->
      <el-table-column
        label="考试时间"
        align="center"
        min-width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime}}</span>
        </template>
      </el-table-column>
       <!-- 操作 -->
      <el-table-column
        label="操作"
         min-width="200"
        align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleClick(scope.$index, scope.row)">查看</el-button>
          <el-button
            size="mini"
            type="danger"
            v-if="role === 'teacher'?true:false"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loading: true,
      searchData: null,
      tableData: null,
      search: '',
      role: 'teacher'
    }
  },
  created () {
    this.role = sessionStorage.getItem('role')
    this.axios.post('/api/result/getResultVo?role=' + sessionStorage.getItem('role') + '&id=' + sessionStorage.getItem('id'))
      .then(res => {
        this.tableData = res.data
        this.searchData = res.data
        console.log(this.tableData)
        this.loading = false
      })
      .catch(err => {
        console.error(err)
      })
  },
  mounted () {
  },
  methods: {
    searchChange () {
      this.searchData = this.tableData.filter(ele =>
        ele.stuNickname.indexOf(this.search) > -1 || ele.name.indexOf(this.search) > -1)
    },
    // 点击查看，跳到新页面
    handleClick (index, row) {
      console.log(row)
      const routeData = this.$router.resolve({
        name: 'seePaper'
      })
      // 通过sessionStorage实现不同页面的传值
      sessionStorage.setItem('initInfo', JSON.stringify(row))
      window.open(routeData.href, '_blank')
    },
    // 点击删除
    handleDelete (index, row) {
      this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.axios.post('/api/result/deleteOneResult?resultId=' + row.resultId)
          .then(res => {
            console.log(res)
            this.reload()
          })
          .catch(err => {
            console.error(err)
          })// axxios-end
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
      // console.log(row.resultId)
    }
  }
}
</script>

<style scoped lang="less">

</style>
