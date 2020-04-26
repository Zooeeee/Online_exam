<template>
    <div class = "main" @click="openNewTab(data.id)">
       <el-col :span="22" :offset="1">
        <el-card  shadow="hover" :body-style="{ padding: '0px' }" >
          <el-row class = "cardBody" >
            <el-col :span="6" :offset="1" class="cardAvatar" >
              <el-avatar :size="70" :src="$store.getters.getServerUrl +'/img/avatar/' + this.data.avatar "></el-avatar>
            </el-col>
            <el-col :span="15" class="cardInfo">
              <h4>{{this.data.name}}</h4>
              <p>{{this.data.des}}</p>
            </el-col>
          </el-row>
          <el-row class = "cardFoot">
            <p>总分:{{this.data.totalMark}}</p>
            <el-divider direction="vertical"></el-divider>
            <p>创建人:{{this.data.creatorName}}</p>
          </el-row>
        </el-card>
       </el-col>
    </div>
</template>

<script>
export default {
  inject: ['serverUrl'],
  props: ['data'],
  data () {
    return {

    }
  },
  created () {

  },
  mounted () {
    // console.log(this.data)
  },
  methods: {
    openNewTab (id) {
      const routeData = this.$router.resolve({
        name: 'examDetail'
        // query: { examId: id }
      })
      // 通过sessionStorage实现不同页面的传值
      sessionStorage.setItem('examDetail', JSON.stringify(this.data))
      window.open(routeData.href, '_blank')
    }

  }
}
</script>

<style scoped lang="less">
.main{
    //background-color: #444;
    height: 100%;
.el-col{
   // background-color: #444;
    height: 100%;
.el-card{
  background-color: #FFFAFA;
  border-radius: 15px;
  cursor: pointer
}
}
}
.cardFoot{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  background-color: #F7F7F7;
  color: #8a888d;
}
.cardBody{
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 125px
}
.cardAvatar{
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: center;
}
.cardInfo{
p{
  margin-left: 5px;
  margin-top: 5px;
  font-size: 14px;
  color: rgba(0, 0, 0, 0.45);
}
h4{
  text-align: center;
  margin-top: 5px;
  margin-bottom: 5px;
}
}
</style>
