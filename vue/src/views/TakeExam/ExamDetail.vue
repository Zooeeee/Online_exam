<template>
  <div>
      <el-container >
        <!-- 顶部信息 -->
        <el-header class = "header">
          <PaperSheetHeader :userInfo = "this.userInfo" :examData="this.examData"></PaperSheetHeader>
        </el-header>
        <el-container class="main">
          <!-- 左侧导航 -->
          <el-aside width="200px">
             <!-- 倒计时 -->
            <div>
              <h3 style="text-align:center">结束倒计时:{{time}}</h3>
            </div>
            <el-menu
            :default-active="this.examData.hasChoice.split(',')[0]"
            :unique-opened ="true"
            @select="handleSelect"
            active-text-color = "#3988FE"
            class="el-menu-vertical-demo">
              <el-submenu index="1">
                <template slot="title">
                  <i  class="iconfont icon-icon-1"></i>
                  <span>单选题:每题{{ this.examData.choiceMark }}分</span>
                </template>
                <el-menu-item v-for="(item, index) in this.examData.hasChoice.split(',')" :key="item" :index="item">
                  单选题 {{ index + 1 }}
                </el-menu-item>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="iconfont icon-danxuantiankong"></i>
                  <span>填空题:每题{{ this.examData.blankMark }}分</span>
                </template>
                <el-menu-item  v-for="(item, index) in this.examData.hasBlank.split(',')" :key="item" :index="item">
                  填空题 {{ index + 1 }}
                </el-menu-item>
              </el-submenu>
              <el-submenu index="3">
                <template slot="title">
                  <i class="iconfont icon-icon-"></i>
                  <span>判断题:每题{{ this.examData.judgeMark }}分</span>
                </template>
                <el-menu-item v-for="(item, index) in this.examData.hasJudge.split(',')" :key="item" :index="item">
                  判断题 {{ index + 1 }}
                </el-menu-item>
              </el-submenu>
            </el-menu>
            <!-- 重置 提交按钮 -->
            <div class="optionDiv">
              <el-button type="danger" round @click="resetForm">
                重置
              </el-button>
                <el-button type="primary" round @click="submitForm">
                提交
              </el-button>
            </div>
          </el-aside>
          <el-main>
            <!-- 表单部分 -->
            <el-form ref="form" label-position="left" :key="this.problemList.length">
              <!-- 单选题 -->
              <el-form-item v-for="(item,index) in this.problemList.choiceList" :key="item.id" :label="item.name" class="choice" v-show="showItem === item.id?true:false">
                <el-radio-group v-model="choiceAnswer[index].value" >
                  <el-radio  v-for="(item2, index2) in item.content.split(',')" :key="index2" :label="item2">{{item2}}</el-radio>
                </el-radio-group>
              </el-form-item>
              <!-- 填空题 -->
              <el-form-item :label="item.content.split('()').join('（ ）')"  v-for="(item,index) in this.problemList.blankList" :key="item.id" class="blank"  v-show="showItem === item.id?true:false">
                <el-input v-model="blankAnswer[index].value" ></el-input>
              </el-form-item>
              <!-- 判断题 -->
               <el-form-item v-for="(item,index) in this.problemList.judgeList" :key="item.id" :label="item.name" class="choice"  v-show="showItem === item.id?true:false">
                <el-radio-group  v-model="judgeAnswer[index].value" >
                  <el-radio v-for="(item2, index2) in item.content.split(',')" :key="index2" :label="item2">{{item2}}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-form>
          </el-main>
        </el-container>
      </el-container>
  </div>
</template>

<script>
import PaperSheetHeader from '@/views/TakeExam/PaperSheetHeader.vue'
export default {
  components: { PaperSheetHeader },
  data () {
    return {
      // 控制某个item显示
      showItem: 1,
      // 通过sessionstorage传值
      examData: JSON.parse(sessionStorage.getItem('examDetail')),
      userInfo: {
        avatar: sessionStorage.getItem('avatar'),
        id: sessionStorage.getItem('id'),
        nickName: sessionStorage.getItem('nickName')
      },
      problemList: {},
      choiceAnswer: [],
      blankAnswer: [],
      judgeAnswer: [],
      totalMark: 0,
      // 倒计时
      time: '00:00',
      timer: null
    }
  },
  created () {
  // 设置倒计时
    let maxtime = this.examData.time * 60
    let minutes = 0
    let seconds = 0
    // 箭头函数 保持this的指向
    this.timer = setInterval(() => {
      maxtime--
      if (maxtime >= 0) {
        minutes = Math.floor(maxtime / 60)
        seconds = Math.floor(maxtime % 60)
        if (minutes < 10) minutes = '0' + minutes
        if (seconds < 10) seconds = '0' + seconds
        this.time = minutes + ':' + seconds
      } else {
        // 清空定时器
        this.time = '00:00'
        this.timer = null
        //   this.$alert('详细信息请在试卷列表查看', '您的成绩为 ' + this.totalMark + ' 分'
        this.$alert('考试失败', '时间已经结束', {
          confirmButtonText: '关闭考试',
          callback: acion => {
            window.close()
          }
        })
      }
    }, 1000)
  },
  mounted () {
    console.log(this.examData)
    this.axios.post('/api/exam/getProblemsByIdsOrderByTypes?id=' + this.examData.id)
      .then(res => {
        console.log(res.data)
        this.problemList = res.data
        // 开头显示题目
        this.showItem = res.data.choiceList[0].id
        // 动态生成vmodel
        this.problemList.choiceList.forEach(element => {
          this.choiceAnswer.push({ value: '' })
        })
        this.problemList.judgeList.forEach(element => {
          this.judgeAnswer.push({ value: '' })
        })
        this.problemList.blankList.forEach(element => {
          this.blankAnswer.push({ value: '' })
        })
      })
      .catch(err => {
        console.error(err)
      })
  },
  methods: {
    // 导航激活
    handleSelect (key, keyPath) {
      // 此处的keyPath是题目id
      // console.log(key, keyPath)
      this.showItem = parseInt(key)
      // console.log(this.showItem)
    },
    // 重置
    resetForm () {
      console.log('reset')
      this.choiceAnswer = this.judgeAnswer = this.blankAnswer = []
      this.problemList.choiceList.forEach(element => {
        this.choiceAnswer.push({ value: '' })
      })
      this.problemList.judgeList.forEach(element => {
        this.judgeAnswer.push({ value: '' })
      })
      this.problemList.blankList.forEach(element => {
        this.blankAnswer.push({ value: '' })
      })
    },
    // 提交
    submitForm () {
      // console.log('submit')
      // console.log(this.blankAnswer)
      // console.log(this.choiceAnswer)
      // console.log(this.judgeAnswer)
      const choiceData = []
      const blankData = []
      const judgeData = []
      // 选择题封装
      for (let index = 0; index < this.choiceAnswer.length; index++) {
        if (this.choiceAnswer[index].value !== '') {
          choiceData.push({
            proId: this.problemList.choiceList[index].id,
            write: this.choiceAnswer[index].value
          })
        } else {
          return this.$message.error(`单选题${index + 1}未完成,请查看`)
        }
      }
      // 填空题题封装
      for (let index = 0; index < this.blankAnswer.length; index++) {
        if (this.blankAnswer[index].value !== '') {
          blankData.push({
            proId: this.problemList.blankList[index].id,
            write: this.blankAnswer[index].value
          })
        } else {
          return this.$message.error(`填空题${index + 1}未完成,请查看`)
        }
      }
      // 判断题封装
      for (let index = 0; index < this.judgeAnswer.length; index++) {
        if (this.judgeAnswer[index].value !== '') {
          judgeData.push({
            proId: this.problemList.judgeList[index].id,
            write: this.judgeAnswer[index].value
          })
        } else {
          return this.$message.error(`判断题${index + 1}未完成,请查看`)
        }
      }
      // 这里来计算分数
      let choiceCount = 0
      for (let i = 0; i < this.choiceAnswer.length; i++) {
        if (this.choiceAnswer[i].value === this.problemList.choiceList[i].answer) {
          choiceCount = choiceCount + this.examData.choiceMark
        }
      }
      let blankCount = 0
      for (let i = 0; i < this.blankAnswer.length; i++) {
        if (this.blankAnswer[i].value === this.problemList.blankList[i].answer) {
          blankCount = blankCount + this.examData.blankMark
        }
      }
      let judgeCount = 0
      for (let i = 0; i < this.judgeAnswer.length; i++) {
        if (this.judgeAnswer[i].value === this.problemList.judgeList[i].answer) {
          judgeCount = judgeCount + this.examData.judgeMark
        }
      }
      this.totalMark = judgeCount + blankCount + choiceCount
      // 这是要提交的result的结果
      const subData = {
        examId: this.examData.id,
        stuId: parseInt(this.userInfo.id),
        choiceAnswer: choiceData,
        blankAnswer: blankData,
        judgeAnswer: judgeData,
        totalMark: this.totalMark
      }
      console.log(subData)
      this.axios.post('/api/result/addResult', subData)
        .then(res => {
          console.log(res)
          this.$alert('详细信息请在试卷列表查看', '您的成绩为 ' + this.totalMark + ' 分', {
            confirmButtonText: '确定',
            callback: acion => {
              window.close()
            }
          })
          // window.close()
        })
        .catch(err => {
          console.error(err)
        })// axios------end
    }
  }
}
</script>

<style scoped lang="less">
.optionDiv{
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 10px;
}
.header{
  height: 70px !important;
  background-color: #01102D;
}
.main{
   height: calc(100vh - 70px) ;
  .el-aside{
background-color:#FFF;
}
  .el-main{
  background-color:#F1F2F6;
}
}
.el-menu{
  i{
    font-size: 20px;
  }
}

.choice /deep/ .el-form-item__label{
   font-size: 20px !important;
}
.blank /deep/ .el-form-item__label{
   font-size: 20px !important;
}
.blank{
  display: flex;
  flex-direction: column;
  .el-input{
   width: 125px;
    margin-left: 5px;
  }
}

.choice{
  display: flex;
  flex-direction: column;
  .el-radio-group{
    display: flex;
    flex-direction: column;
  }
}
.el-radio, .el-radio--medium.is-bordered{
  font-size: 30px !important;
}
</style>
