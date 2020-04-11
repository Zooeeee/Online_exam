<template>
 <div>
   <el-row class="option">
     <el-col :span="18" :offset="3">
        <el-switch
        style="display: block"
        v-model="switchValue"
        active-color="#13ce66"
        inactive-color="#ff4949"
        @change="switchChange"
        active-text="总览"
        inactive-text="模糊查询">
      </el-switch>
      <el-button  type="primary" size="medium"  @click="dialogFormVisible = true" >添加</el-button>
      <!-- <el-button v-show="!switchValue" type="primary" size="medium" >重置</el-button> -->
    </el-col>
   </el-row>
   <el-row>
     <el-col :span="20" :offset="2">
      <ProblemTable v-show="switchValue"></ProblemTable>
      <ProblemSearch v-show="!switchValue"></ProblemSearch>
      </el-col>
   </el-row>
   <el-dialog title="添加新题目" :visible.sync="dialogFormVisible" center  >
     <AddProblem @closeAddProblemDialog = "closeAD"></AddProblem>
   </el-dialog>
  </div>
</template>

<script>
import ProblemSearch from '@/views/Problem/ProblemSearch.vue'
import ProblemTable from '@/views/Problem/ProblemTable.vue'
import AddProblem from '@/views/Problem/AddProblem.vue'
export default {
  inject: ['reload'],
  components: { ProblemSearch, ProblemTable, AddProblem },
  data () {
    return {
      dialogFormVisible: false,
      switchValue: true
    }
  },
  created () {

  },
  mounted () {

  },
  methods: {
    closeAD (bool) {
      this.dialogFormVisible = bool
      this.reload()
    },
    switchChange () {
    //  console.log(this.switchValue)
    }
  }
}
</script>

<style scoped lang="less">
.option{
.el-col{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  height: 50px;
}
}
</style>
