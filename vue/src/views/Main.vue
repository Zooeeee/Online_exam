<template>
  <!-- <div>
    <el-container>
      <Header></Header>
      <Info></Info>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>
        <Footer></Footer>
      </el-footer>
    </el-container>
  </div> -->
  <div>
    <el-container>
      <Header></Header>
      <Info></Info>
      <el-main>
        <el-row >
          <el-col    :xs='24' :sm='24' :md='{span:20,offset:2}' :lg='{span:16,offset:4}' :xl='{span:12,offset:6}' class = "main">
             <router-view></router-view>
          </el-col>
        </el-row>
      </el-main>
      <el-footer>
        <Footer></Footer>
      </el-footer>
    </el-container>
  </div>
</template>
<script>
import Header from '@/views/Main/Header.vue'
import Footer from '@/views/Main/Footer.vue'
import Info from '@/views/Main/Info.vue'

export default {
  components: { Header, Footer, Info },
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
<style lang="less" scoped>
.el-header{
    text-align: center;
    line-height: 60px;
}
.el-footer{
    background-color: #F1F2F6;
    width:100%;
    position:absolute;
    bottom:0;
    left:0;
    text-align: center;
    line-height: 20px;
    height: 45px !important;
}
.el-main{
  background: #F1F2F6;
  // header-60px footer-45px info-5vh
  height: calc(100vh - 5vh - 105px);
  padding: 0%;
}
.main{
  margin-top: 15px;
  background-color: #fff;
  min-height: calc(100vh - 5vh - 130px)
}
</style>
