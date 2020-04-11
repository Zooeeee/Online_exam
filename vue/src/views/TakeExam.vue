<template>
  <div class = "main" >
    <el-col  :xs='24' :sm='12' :md='8' :lg='8' :xl='8' v-for="(item, index) in this.$store.getters.getAllExamVo" :key="index">
      <ExamCard :data = "item" ></ExamCard>
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

    }
  },
  created () {
    // this.$store.dispagetAllExamVo()
    this.$store.dispatch('getAllExamVo')
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
  }
</style>
