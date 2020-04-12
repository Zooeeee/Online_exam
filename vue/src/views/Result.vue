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
      :data="searchData"
      style="width: 100%"
      :height = "700"
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
         <el-avatar :size="50" :src="'http://localhost/img/avatar/' + scope.row.avatar"></el-avatar>
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
        min-width="180">
        <template slot-scope="scope">
          <span   >{{ scope.row.name }}</span>
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
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  inject: ['reload'],
  data () {
    return {
      searchData: null,
      tableData: null,
      search: ''
    }
  },
  created () {
    this.axios.post('/api/result/getResultVo?role=' + sessionStorage.getItem('role') + '&id=' + sessionStorage.getItem('id'))
      .then(res => {
        this.tableData = res.data
        this.searchData = res.data
        console.log(this.tableData)
      })
      .catch(err => {
        console.error(err)
      })
  },
  mounted () {
  },
  methods: {
    searchChange () {
      this.searchData = this.tableData.filter(ele => ele.stuNickname.indexOf(this.search) > -1 || ele.name.indexOf(this.search) > -1)
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
      console.log(row.resultId)
      this.axios.post('/api/result/deleteOneResult?resultId=' + row.resultId)
        .then(res => {
          console.log(res)
          this.reload()
        })
        .catch(err => {
          console.error(err)
        })
    }
  }
}
</script>

<style scoped lang="less">

</style>
