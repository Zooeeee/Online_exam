<template>
    <div class = "navdiv">
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        mode = "vertical"
        >
        <el-menu-item index="1" @click="clickHandle('true')" >
          <i class="iconfont icon-jibenxinxi"></i>
          <span slot="title">个人信息</span>
        </el-menu-item>
        <el-menu-item index="2" @click="clickHandle('false')"  >
          <i class="iconfont icon-xiugaimima"></i>
          <span slot="title">修改密码</span>
        </el-menu-item>
      </el-menu>
    </div>
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
    clickHandle (el) {
      this.$emit('user-nav-show', el)
    }
  }
}
</script>

<style scoped lang="less">
i{
  margin:0 5px;
}
.el-menu{
  border: 0 !important;
}

</style>
