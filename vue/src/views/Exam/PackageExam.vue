<template>
    <div>
        <!-- 弹框上方进度条  -->
        <el-steps :active="active" finish-status="success" >
            <el-step title="基本信息"></el-step>
            <el-step title="选择题"></el-step>
            <el-step title="填空题"></el-step>
            <el-step title="判断题"></el-step>
        </el-steps>
        <!-- 表单 -->
        <el-form label-position="left" label-width="80px" :key = this.subject>
          <!-- 第一部分 基本信息 选择科目 -->
          <div v-show="this.active === 0?true:false">
            <el-form-item label="考试名称"   >
              <el-input v-model="submitInfo.name" ></el-input>
            </el-form-item>
            <el-form-item  label="考试介绍"  >
              <el-input
                v-model="submitInfo.des"
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                placeholder="请输入内容"
                >
              </el-input>
            </el-form-item>
            <el-form-item label="考试时长"  class="timeChoice" >
              <el-input-number size="mini" style="width:100px"
              controls-position="right" v-model="submitInfo.time"
              :min="20" :max="300" :step="10" :step-strictly="false"
              >
              </el-input-number>
              <span style="font-size:16px;margin-left:10px;">分钟</span>
            </el-form-item>
            <el-form-item label="考试科目">
               <el-select v-model="subject" placeholder="请选择" @change = "selectChange">
                <el-option
                  v-for="(item,index) in this.initData"
                  :key="index"
                  :label="index"
                  :value="index">
                </el-option>
              </el-select>
            </el-form-item>
          </div>
          <!-- 单选题 -->
          <div v-show="this.active === 1?true:false">
            <el-form-item label="每题分数" label-width="80px" >
               <el-input-number v-model="submitInfo.choiceMark"  controls-position="right" size="small"  :min="1" :max="20"></el-input-number>
            </el-form-item>
            <el-form-item v-for="(item, index) in this.countData[0].data" :key="index" :label="'难度:'+item.difficulty">
              <el-input-number v-model="submitInfo.choiceCount[index]" controls-position="right" size="small"  :min="0" :max="item.count">
              </el-input-number>
            </el-form-item>
          </div>
          <!-- 填空题 -->
          <div v-show="this.active === 2?true:false">
            <el-form-item label="每题分数" label-width="80px" >
               <el-input-number v-model="submitInfo.blankMark"  controls-position="right" size="small"  :min="1" :max="20"></el-input-number>
            </el-form-item>
            <el-form-item v-for="(item, index) in this.countData[1].data" :key="index" :label="'难度:'+item.difficulty">
              <el-input-number v-model="submitInfo.blankCount[index]" controls-position="right" size="small"  :min="0" :max="item.count">
              </el-input-number>
            </el-form-item>
          </div>
          <!-- 判断题 -->
          <div v-show="this.active === 3?true:false">
            <el-form-item label="每题分数" label-width="80px" >
               <el-input-number v-model="submitInfo.judgeMark"  controls-position="right" size="small"  :min="1" :max="20"></el-input-number>
            </el-form-item>
            <el-form-item v-for="(item, index) in this.countData[2].data" :key="index" :label="'难度:'+item.difficulty">
              <el-input-number v-model="submitInfo.judgeCount[index]" controls-position="right" size="small"  :min="0" :max="item.count">
              </el-input-number>
            </el-form-item>
          </div>
        </el-form>
        <!-- 弹框底部操作按钮 -->
        <el-footer>
          <el-button style="margin-top: 12px;" @click="prev" icon="el-icon-arrow-left" round>后退</el-button>
          <el-button v-show="!submitShow" style="margin-top: 12px;" @click="next"  round>前进<i class="el-icon-arrow-right el-icon--right"/></el-button>
          <el-button v-show="submitShow" style="margin-top: 12px;" @click="submit" type="primary"  round>提交<i class="el-icon-upload el-icon--right"></i></el-button>
        </el-footer>
    </div>
</template>

<script>
export default {
  data () {
    return {
    // 步骤栏相关属性
      active: 0,
      submitShow: false,
      // init 表单数据
      initData: { },
      subject: '',
      countData: [{ data: null }, { data: null }, { data: null }],
      // 提交表单信息
      submitInfo: {
        name: '',
        des: '',
        time: 0,
        subject: '',
        choiceMark: 5,
        blankMark: 5,
        judgeMark: 5,
        choiceDiffi: ['A', 'B'],
        choiceCount: [0, 0],
        blankDiffi: ['A', 'B'],
        blankCount: [0, 0],
        judgeDiffi: ['A', 'B'],
        judgeCount: [0, 0],
        totalMark: 0,
        creator: sessionStorage.getItem('id')
      }
    }
  },
  created () {
    // 获得渲染初始表单的数据
    this.axios.post('/api/problem/getInitInfo')
      .then(res => {
        this.initData = res.data
        // console.log(this.initData)
        this.subject = Object.keys(res.data)[0]
        this.submitInfo.subject = Object.keys(res.data)[0]
        this.countData = this.initData[this.subject]
        console.log(this.countData[0])
        console.log(this.countData[1])
        console.log(this.countData[2])
        // console.log(this.initData[this.subject])
      })
      .catch(err => {
        console.error(err)
      })
  },
  mounted () {
  },
  methods: {
    // subject选中框改变
    selectChange (value) {
      // console.log(value)
      this.countData = this.initData[value]
      this.submitInfo.subject = this.initData[value]
      // console.log(this.countData)
    },
    // 导航栏的上一面 下一面
    prev () {
      if (this.active > 0) {
        this.active--
        if (this.active === 2) {
          this.submitShow = false
        }
      }
    },
    next () {
      if (this.active < 3) this.active++
      if (this.active === 3) this.submitShow = true
    },
    // 提交表单
    submit () {
      console.log('submit')
      // 计算总分
      let totalMark = 0
      this.submitInfo.choiceCount.forEach((ele) => { totalMark += this.submitInfo.choiceMark * ele })
      this.submitInfo.blankCount.forEach((ele) => { totalMark += this.submitInfo.blankMark * ele })
      this.submitInfo.judgeCount.forEach((ele) => { totalMark += this.submitInfo.judgeMark * ele })
      this.submitInfo.totalMark = totalMark
      this.submitInfo.subject = this.subject
      console.log(this.submitInfo)
      this.axios.post('/api/exam/packageExam', this.submitInfo)
        .then(res => {
          console.log(res)
          this.$emit('closePackageProblemDialog', false) // 传递给父组件 让其隐藏dialog
        })
        .catch(err => {
          console.error(err)
        })
    }
  }
}
</script>

<style scoped lang="less">
.el-footer{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.el-form{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.timeChoice{
  input{
    width:50px;
  }
}
</style>
