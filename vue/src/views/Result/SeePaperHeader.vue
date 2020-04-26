<template>
   <el-row>
      <el-col :xs='24' :sm='12' :md='{span:8,offset:0}' :lg='{span:8,offset:0}' :xl='{span:6,offset:2}' class="left">
        <el-avatar :size="55" :src="$store.getters.getServerUrl +'/img/avatar/' +this.creatorInfo.avatar" ></el-avatar>
        <h3> {{ this.initData.name }} </h3>
        <p> {{ this.initData.des }}</p>
      </el-col>
      <el-col :xs='24' :sm='12' :md='{span:8,offset:0}' :lg='{span:8,offset:0}' :xl='{span:8,offset:0}' class="mid">
        <p>日期：{{this.initData.createTime}}</p>
        <el-divider direction="vertical"></el-divider>
        <p>成绩：</p>
        <p style="color:red;font-size:20px;">{{this.initData.totalMark}}</p>
        <p>分</p>
      </el-col>
      <el-col :xs='24' :sm='24' :md='{span:8,offset:0}' :lg='{span:8,offset:0}' :xl='{span:6,offset:2}' class="right">
        <el-avatar :size="50" :src=" $store.getters.getServerUrl +'/img/avatar/' +this.initData.avatar" ></el-avatar>
        <h5> {{ this.initData.stuNickname }} </h5>
      </el-col>
   </el-row>
</template>

<script>
export default {
  data () {
    return {
      initData: {},
      creatorInfo: { avatar: '1.jpg' }
    }
  },
  created () {
    this.initData = JSON.parse(sessionStorage.getItem('initInfo'))
    // console.log(this.initData)
    this.axios.post('/api/result/getCreatorInfoByExam?examId=' + this.initData.examId)
      .then(res => {
        this.creatorInfo = res.data
        // console.log(this.creatorInfo)
      })
      .catch(err => {
        console.error(err)
      })
  },
  mounted () {

  },
  methods: {

  }
}
</script>

<style scoped lang="less">
.el-row{
  background-color: #01102d;
  color: #fff;
}
.left {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
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
  justify-content: center;
  *{margin-left: 10px;}
}
.mid{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  *{margin-left: 10px;}
  p{
   font-size: 15px;
  }
}
</style>
