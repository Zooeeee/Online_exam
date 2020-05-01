<template>
  <div class = "main" v-loading.lock="loading">
    <el-col  :xs='24' :sm='12' :md='8' :lg='8' :xl='8' v-for="(item, index) in this.$store.getters.getAllExamVo" :key="index">
      <ExamCard :data = "item" class="ExamCard"></ExamCard>
    </el-col>
    <!-- <ExamCard class="ExamCard" v-for="(item, index) in 20" :key="index"></ExamCard> -->
      <el-backtop target=".main" :bottom="100">
  </el-backtop>
  </div>
</template>

<script>
import ExamCard from '@/views/TakeExam/ExamCard.vue'
export default {
  components: { ExamCard },
  data () {
    return {
      loading: false
    }
  },
  created () {
    // this.$store.dispagetAllExamVo()
    this.$store.dispatch('getAllExamVo', this)
  },
  mounted () {
    // console.log(this.$store.getters.getAllExamVo)
    // console.log(this.$store.state.allExamVo)
  },
  methods: {

  }
}
</script>

<style scoped lang="less">
  .el-col{
    height: calc(20vh);
    margin:20px 0 20px 0;
    min-height: 175px;
  }

</style>
