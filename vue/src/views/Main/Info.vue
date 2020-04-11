<template>
  <el-row>
    <el-col  :xs='24' :sm='24' :md='{span:18,offset:3}' :lg='{span:14,offset:5}' :xl='{span:14,offset:5}'>
      <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item v-for="(item, index) in $route.meta" :key="index">{{item}}</el-breadcrumb-item>
    </el-breadcrumb>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data () {
    return {

    }
  },
  created () {

  },
  mounted () {
  },
  methods: {

  }
}
</script>

<style scoped lang="less">
.el-breadcrumb{
  height: 5vh;
  background-color: #fff;
  display: flex;
  align-items: center;
}
</style>
