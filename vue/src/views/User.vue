<template>
  <div>
    <el-row >
      <el-col  :xs='24' :sm='24' :md='4' :lg='4' :xl='4'  class="navClass">
          <UserNav @user-nav-show='handleChild'></UserNav>
      </el-col>
      <el-col :xs='24' :sm='24' :md='20' :lg='20' :xl='20'>
          <UserForm class = "form" v-show="showFlag"></UserForm>
          <AcountForm class = "form" v-show="!showFlag"></AcountForm>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AcountForm from '@/views/User/AcountForm.vue'
import UserForm from '@/views/User/UserForm.vue'
import UserNav from '@/views/User/UserNav.vue'
export default {
  components: { AcountForm, UserForm, UserNav },
  data () {
    return {
      showFlag: true
    }
  },
  created () {

  },
  mounted () {

  },
  methods: {
    handleChild (el) {
      if (el === 'true') {
        this.showFlag = true
      } else {
        this.showFlag = false
      }
    }
  }
}
</script>

<style scoped lang="less">

.form{
  min-height:calc(95vh - 150px) ;
  background-color: #fff;
  border-left: solid 1px #e6e6e6;
  margin: 20px 0px 0px 0px;
}
// .navClass{
//   background:black;margin-top:30px;
// }
</style>
