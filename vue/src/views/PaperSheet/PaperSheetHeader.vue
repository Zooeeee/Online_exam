<template>
   <el-row>
      <el-col :xs='24' :sm='12' :md='12' :lg='{span:6,offset:2}' :xl='{span:6,offset:4}' class="left">
        <el-avatar :size="60" :src="this.creatorAvatar" ></el-avatar>
        <h3> {{ this.examData.name }} </h3>
        <p> {{ this.examData.des }}</p>
      </el-col>
      <el-col :xs='0' :sm='{span:6,offset:6}' :md='{span:8,offset:4}' :lg='{span:6,offset:10}' :xl='{span:6,offset:8}' class="right">
         <el-avatar :size="40" :src="this.userAvatar"></el-avatar>
          <h5> {{ this.userInfo.nickName }} </h5>
      </el-col>
   </el-row>
</template>

<script>
export default {
  props: ['userInfo', 'examData'],
  data () {
    return {
      creatorAvatar: '',
      userAvatar: ''
    }
  },
  created () {

  },
  mounted () {
    // console.log('===============')
    // console.log(this.userInfo)
    // console.log(this.examData)
    this.creatorAvatar = this.$store.getters.getServerUrl + '/img/avatar/' + this.examData.avatar
    this.userAvatar = this.userInfo.avatar
  },
  methods: {

  }
}
</script>

<style scoped lang="less">
.left{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  *{margin-left: 10px;}
  h3{
    color:#fff;
  }
  p{
    color:#bbb;
  }
}
.right{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  *{margin-left: 10px;}
  h5{
    color: #fff;
  }
}
</style>
