<template>
    <div>
      <el-container>
        <el-header class="header">
          <SeePaperHeader></SeePaperHeader>
        </el-header>
        <el-container class="main">
          <!-- 左侧导航 -->
          <el-aside width="200px">
              <el-menu
              :unique-opened ="true"
              @select="handleSelect"
              active-text-color = "#3988FE"
              :default-active="this.choiceList.length > 0 ? this.choiceList[0].id.toString():'0'"
              class="el-menu-vertical-demo">
              <el-submenu index="1">
                <template slot="title">
                  <i  class="iconfont icon-icon-1"></i>
                  <span>单选题:每题{{ this.choiceMark }}分</span>
                </template>
                <el-menu-item v-for="(item, index) in this.choiceList" :key="item.id" :index="item.id.toString()">
                  单选题 {{ index + 1 }}
                </el-menu-item>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="iconfont icon-danxuantiankong"></i>
                  <span>填空题:每题{{ this.blankMark }}分</span>
                </template>
                <el-menu-item  v-for="(item, index) in this.blankList" :key="item.id" :index="item.id.toString()">
                  填空题 {{ index + 1 }}
                </el-menu-item>
              </el-submenu>
              <el-submenu index="3">
                <template slot="title">
                  <i class="iconfont icon-icon-"></i>
                  <span>判断题:每题{{ this.judgeMark }}分</span>
                </template>
                <el-menu-item v-for="(item, index) in this.judgeList" :key="item.id" :index="item.id.toString()">
                  判断题 {{ index + 1 }}
                </el-menu-item>
              </el-submenu>
            </el-menu>
          </el-aside>
          <!-- 主题表单 -->
          <el-main>
             <!-- 表单部分 -->
            <el-form ref="form" label-position="left" >
              <!-- 单选题 -->
              <el-form-item v-for="(item,index) in this.choiceList" :key="item.id" :label="item.name" class="choice" v-show="showItem === item.id?true:false">
                <el-radio-group :key="index" v-model="item.write"  >
                  <el-radio v-for="(item2, index2) in item.content.split(',')" :key="index2" :label="item2">{{item2}}</el-radio>
                </el-radio-group>
                <el-tag v-if="item.write === item.answer?true:false">您答对了！分数加 {{item.mark}}</el-tag>
                <el-tag type="danger" v-if="item.write === item.answer?false:true">您答错了</el-tag>
                <div>正确答案:{{ item.answer}}</div>
                <div>你的回答:{{ item.write}}</div>
                <div>解析：{{ item.analysis}}</div>
              </el-form-item>
              <!-- 填空题 -->
              <el-form-item :label="item.content.split('()').join('（ ）')"  v-for="(item,index) in this.blankList" :key="item.id" class="blank"  v-show="showItem === item.id?true:false">
                <el-input :key="index"  v-model="item.write"></el-input>
                <br/>
                <el-tag v-if="item.write === item.answer?true:false">您答对了！分数加 {{item.mark}}</el-tag>
                <el-tag type="danger" v-if="item.write === item.answer?false:true">您答错了</el-tag>
                <div>正确答案:{{ item.answer}}</div>
                <div>你的回答:{{ item.write}}</div>
                <div>解析：{{ item.analysis}}</div>
              </el-form-item>
              <!-- 判断题 -->
               <el-form-item v-for="(item,index) in this.judgeList" :key="item.id" :label="item.name" class="choice"  v-show="showItem === item.id?true:false">
                <el-radio-group  :key="index"  v-model="item.write">
                  <el-radio v-for="(item2, index2) in item.content.split(',')" :key="index2" :label="item2">{{item2}}</el-radio>
                </el-radio-group>
                <el-tag v-if="item.write === item.answer?true:false">您答对了！分数加 {{item.mark}}</el-tag>
                <el-tag type="danger" v-if="item.write === item.answer?false:true">您答错了</el-tag>
                <div>正确答案:{{ item.answer}}</div>
                <div>你的回答:{{ item.write}}</div>
                <div>解析：{{ item.analysis}}</div>
              </el-form-item>
            </el-form>
          </el-main>
        </el-container>
      </el-container>
    </div>
</template>

<script>
import SeePaperHeader from '@/views/Result/SeePaperHeader.vue'
export default {
  components: { SeePaperHeader },
  data () {
    return {
      // 控制某个item显示
      showItem: 1,
      initData: {},
      choiceList: [],
      blankList: [],
      judgeList: [],
      choiceMark: 0,
      blankMark: 0,
      judgeMark: 0,
      ansInfo: []
    }
  },
  created () {
    this.initData = JSON.parse(sessionStorage.getItem('initInfo'))
    this.axios.post('/api/result/getOneResultPaperInfo?resultId=' + this.initData.resultId + '&examId=' + this.initData.examId)
      .then(res => {
        console.log(res.data.proInfo)
        console.log(res.data.ansInfo)
        this.ansInfo = res.data.ansInfo
        this.choiceList = res.data.proInfo.choiceList
        this.blankList = res.data.proInfo.blankList
        this.judgeList = res.data.proInfo.judgeList
        this.choiceMark = res.data.choiceMark
        this.blankMark = res.data.blankMark
        this.judgeMark = res.data.judgeMark
        // 将答案写进题目集合数组中，方便操作
        for (let i = 0; i < res.data.proInfo.choiceList.length; i++) {
          res.data.proInfo.choiceList[i].write = res.data.ansInfo.choiceAnswer[i].write
          res.data.proInfo.choiceList[i].mark = this.choiceMark
        }
        for (let i = 0; i < res.data.proInfo.blankList.length; i++) {
          res.data.proInfo.blankList[i].write = res.data.ansInfo.blankAnswer[i].write
          res.data.proInfo.choiceList[i].mark = this.blankMark
        }
        for (let i = 0; i < res.data.proInfo.judgeList.length; i++) {
          res.data.proInfo.judgeList[i].write = res.data.ansInfo.judgeAnswer[i].write
          res.data.proInfo.choiceList[i].mark = this.judgeMark
        }
        // end
      })
      .catch(err => {
        console.error(err)
      })
  },
  mounted () {
  },
  methods: {
  // 导航激活
    handleSelect (key, keyPath) {
      // 此处的keyPath是题目id
      // console.log(key, keyPath)
      this.showItem = parseInt(key)
      // console.log(this.showItem)
    }
  }
}
</script>

<style scoped lang="less">
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
