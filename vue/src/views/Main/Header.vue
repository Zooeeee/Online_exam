<template>
  <el-row :gutter="0" ref="headerComp">
    <el-col :xs='24' :sm='24' :md='{span:16,offset:4}' :lg='{span:14,offset:6}' :xl='{span:10,offset:6}'>
      <el-menu
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      :router="true"
      background-color="#01102C"
      text-color="#A1AFBC"
      active-text-color="#fff"
      menu-trigger="click"
      :default-active="$route.path"
      >
        <el-menu-item index='/home'>
          <template slot="title">
            <i class = "el-icon-s-grid"/>
            <span>首页</span>
          </template>
        </el-menu-item>
        <el-menu-item v-for="(item, index) in showList" :key="index" :index="item.path" >
          <template slot="title">
            <i :class = "item.class"/>
            <span>{{ item.des }}</span>
          </template>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :xs='24' :sm='24' :md= '4' :lg='4' :xl='8' class="user">
      <el-avatar :src="this.$store.getters.getUserAvatar" size="large"></el-avatar>
      <el-dropdown  @command="handleCommand">
        <span class="el-dropdown-link" style="cursor: pointer;" >
          {{ this.$store.getters.getUserNickName}}
        <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="pushToUser">个人设置</el-dropdown-item>
          <el-dropdown-item command="exit">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-col>
  </el-row>
</template>
<script>
export default {
  data () {
    return {
      allList: [
        {
          name: 'exam',
          path: '/exam',
          des: '考试管理',
          class: 'el-icon-document-add'
        },
        {
          name: 'takeExam',
          path: '/takeExam',
          des: '参加考试',
          class: 'el-icon-printer'
        },
        {
          name: 'problem',
          path: '/problem',
          des: '问题管理',
          class: 'el-icon-price-tag'
        },
        {
          name: 'result',
          path: '/result',
          des: '试卷列表',
          class: 'el-icon-tickets'
        }
      ],
      showList: []
      // nickName: '',
      // avatar: ''
    }
  },
  created () {

  },
  mounted () {
    const showList = sessionStorage.getItem('power').split(',')
    this.allList.forEach(item => {
      if (showList.includes(item.name)) {
        this.showList.push(item)
      }
    })
    console.log(this.showList)
    // this.nickName = sessionStorage.getItem('nickName')
    // this.avatar = this.$store.getters.getUserAvatar
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    handleCommand (command) {
      if (command === 'pushToUser') {
        this.$router.push('/user')
      }
      if (command === 'exit') {
        /* 退出按钮
         1.弹出对话框
         2.如果确定，就清空sessionStorge
         3.如果否 就算了
        */
        this.$confirm('此操作将退出账号, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 清空sessionstorge和vuex中存储的数据
          sessionStorage.clear()
          this.$router.push('/login')
          this.$message({
            type: 'success',
            message: '退出成功!'
          })
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '取消退出',
            offset: 100,
            duration: 2000
          })
        })
      }
    }
  }// metods ---end
}
</script>

<style scoped lang="less">
//  修改导航栏激活的颜色，通过修改权重的方式
.sysArea{
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}
.el-row{
    background: #00152A;
    margin: 0px;
}
.header{
  background: #00152A;
  el-menu-item{
    background-color: #fff;
  }
}
.el-menu{
  border-bottom:0px !important;
}
.el-button{
  background: #00152A;
}
.user{
  display: flex;
  flex-direction:row;
  justify-content:flex-start;
  align-items: center;
  height: 60px;
  .el-avatar{
    margin-right: 15px;
  }
  .el-dropdown-link{
    color:#D5D5D5;
  }
}
</style>
