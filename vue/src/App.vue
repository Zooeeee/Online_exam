<template>
  <div id="app">
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>
<script>

export default {
  // app容器提供一个方法 用于刷新页面
  name: 'App',
  provide () {
  // 父组件中通过provide来提供变量，在子组件中通过inject来注入变量。
  // 配置全局变量
    return {
      reload: this.reload
    }
  },
  data () {
    return {
      isRouterAlive: true// 控制视图是否显示的变量
    }
  },
  created () {

  },
  mounted () {

  },
  methods: {
    reload () {
      this.isRouterAlive = false// 先关闭，
      this.$nextTick(function () {
        this.isRouterAlive = true// 再打开
      })
    }

  }
}
</script>
<style lang="less">
html , body ,#app{
  margin: 0px;
  padding: 0px;
  height: 100%;
  width: 100%;
  font-family: "SiYuan";
}
.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
p{
  font-family: "SiYuan";
}
b{
   font-family: "SiYuan";
}
</style>
