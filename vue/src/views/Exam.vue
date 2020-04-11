<template>
 <div>
   <el-row class="option">
     <el-col :span="18" :offset="3">
      <el-button  type="primary" size="medium"  @click="addDialogFormVisible = true" >手动添加</el-button>
      <el-button  type="primary" size="medium"  @click="packageDialogFormVisible = true" >自动组装</el-button>
      <!-- <el-button v-show="!switchValue" type="primary" size="medium" >重置</el-button> -->
    </el-col>
   </el-row>
   <el-row>
     <el-col :span="20" :offset="2">
      <ExamTable ref="examTable"></ExamTable>
      </el-col>
   </el-row>
   <el-dialog title="手动添加考试" :visible.sync="addDialogFormVisible" center  >
     <AddExam @closeAddExamDialog = "closeAD"></AddExam>
   </el-dialog>
   <el-dialog title="自动组装考试" :visible.sync="packageDialogFormVisible" center  >
     <PackageExam @closePackageProblemDialog = "closePackage"></PackageExam>
   </el-dialog>
  </div>
</template>

<script>
import AddExam from '@/views/Exam/AddExam.vue'
import PackageExam from '@/views/Exam/PackageExam.vue'
import ExamTable from '@/views/Exam/ExamTable.vue'
export default {
  inject: ['reload'],
  components: { AddExam, ExamTable, PackageExam },
  data () {
    return {
      addDialogFormVisible: false,
      packageDialogFormVisible: false
    }
  },
  created () {

  },
  mounted () {
  },
  methods: {
    closeAD (bool) {
      this.addDialogFormVisible = bool
      // this.$router.go(0)
      this.reload()
    },
    closePackage (bool) {
      this.packageDialogFormVisible = bool
      this.reload()
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
  justify-content: flex-end;
  height: 50px;
}
}
</style>
