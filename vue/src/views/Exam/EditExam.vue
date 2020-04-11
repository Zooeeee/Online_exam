<template>
    <div >
        <!-- 弹框上方进度条  -->
        <el-steps :active="active" finish-status="success" >
            <el-step title="基本信息"></el-step>
            <el-step title="设置分数"></el-step>
            <el-step title="选择题目"></el-step>
        </el-steps>
        <!-- 表单主体 -->
        <el-form :rules="rules" :model="submitInfo"  label-position="left" label-width="80px">
          <!-- form第1部分-->
          <div v-show="this.active === 0?true:false">
            <el-form-item label="考试名称"  prop="name" >
              <el-input v-model="submitInfo.name" ></el-input>
            </el-form-item>
            <el-form-item  label="考试介绍"  prop="des">
             <el-input
              v-model="submitInfo.des"
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 4}"
              placeholder="请输入内容"
              >
            </el-input>
            </el-form-item>
          </div >
          <!-- form第2部分 -->
          <div v-show="this.active === 1?true:false">
            <el-form-item label="选择题分数" label-width="100px"  prop="choiceMark" >
               <el-input-number v-model="submitInfo.choiceMark"  controls-position="right" size="small"  :min="1" :max="20"></el-input-number>
            </el-form-item>
            <el-form-item label="填空题分数" label-width="100px" prop="blankMark">
               <el-input-number   v-model="submitInfo.blankMark"  controls-position="right" size="small"  :min="1" :max="20"></el-input-number>
            </el-form-item>
            <el-form-item label="判断题分数" label-width="100px" prop="judgeMark">
               <el-input-number  v-model="submitInfo.judgeMark"   controls-position="right" size="small"  :min="1" :max="20"></el-input-number>
            </el-form-item>
          </div>
          <!-- form第3部分 -->
          <div v-show="this.active === 2?true:false">
            <el-form-item label="选择题" label-width="100px" prop="choiceList">
              <el-select v-model="submitInfo.choiceList" multiple placeholder="请选择">
                <el-option
                  v-for="item in formInfo.choiceList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="填空题" label-width="100px" prop="blankList">
              <el-select v-model="submitInfo.blankList" multiple placeholder="请选择">
                <el-option
                  v-for="item in formInfo.blankList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="判断题" label-width="100px" prop="judgeList">
              <el-select v-model="submitInfo.judgeList" multiple placeholder="请选择">
                <el-option
                  v-for="item in formInfo.judgeList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item  label="总分" label-width="100px">
              {{this.totalMark}}
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
  props: ['row'],
  computed: {
    totalMark: function () {
      return this.submitInfo.choiceMark * this.submitInfo.choiceList.length + this.submitInfo.blankMark * this.submitInfo.blankList.length + this.submitInfo.judgeMark * this.submitInfo.judgeList.length
    }
  },
  data () {
    return {
      // 步骤栏相关属性
      active: 0,
      submitShow: false,
      name: '',
      formInfo: {
        choiceList: [{
          id: '',
          name: ''
        }],
        blankList: [{
          id: '',
          name: ''
        }],
        judgeList: [{
          id: '',
          name: ''
        }]
      },
      submitInfo: {
        name: '',
        des: '',
        choiceMark: 5,
        blankMark: 5,
        judgeMark: 5,
        choiceList: [],
        blankList: [],
        judgeList: [],
        totalMark: 0,
        creator: sessionStorage.getItem('id')
      },
      rules: {
        name: [
          { required: true, message: '请输入考试名称', trigger: 'blur' }
        ],
        des: [
          { required: true, message: '请输入考试简介', trigger: 'blur' }
        ],
        choiceMark: [
          { required: true, message: '请选择选择题分数', trigger: 'blur' }
        ],
        blankMark: [
          { required: true, message: '请选择填空题分数', trigger: 'blur' }
        ],
        judgeMark: [
          { required: true, message: '请选择判断题分数', trigger: 'blur' }
        ],
        choiceList: [
          { required: true, message: '请选择单选题', trigger: 'blur' }
        ],
        blankList: [
          { required: true, message: '请选择填空题', trigger: 'blur' }
        ],
        judgeList: [
          { required: true, message: '请选择判断题', trigger: 'blur' }
        ]
      }
    }
  },
  created () {

  },
  mounted () {
    console.log('row:====================')
    console.log(this.row)
    // 渲染题目本已存在的信息
    // this.submitInfo = {
    //   name: this.row.name,
    //   des: this.row.des,
    //   choiceMark: this.row.choiceMark,
    //   blankMark: this.row.blankMark,
    //   judgeMark: this.row.judgeMark,
    //   choiceList: this.row.hasChoice.split(','),
    //   blankList: this.row.hasBlank.split(','),
    //   judgeList: this.row.hasJudge.split(','),
    //   totalMark: this.row.totalMark,
    //   creator: sessionStorage.getItem('id')
    // }
    // 渲染选择题目所需要的数据
    this.axios.post('/api/exam/getIdsAndNamesOrderByType')
      .then(res => {
        console.log(res.data)
        this.formInfo.choiceList = res.data.choice
        this.formInfo.blankList = res.data.blank
        this.formInfo.judgeList = res.data.judge
        this.submitInfo = {
          name: this.row.name,
          des: this.row.des,
          choiceMark: this.row.choiceMark,
          blankMark: this.row.blankMark,
          judgeMark: this.row.judgeMark,
          //  这里用map方法把下拉多选框里的值从默认字符串转换为int型 从而实现
          choiceList: this.row.hasChoice.split(',').map(item => parseInt(item)),
          blankList: this.row.hasBlank.split(',').map(item => parseInt(item)),
          judgeList: this.row.hasJudge.split(',').map(item => parseInt(item)),
          totalMark: this.row.totalMark,
          creator: sessionStorage.getItem('id')
        }
        console.log(this.submitInfo.choiceList)
      })
      .catch(err => {
        console.error(err)
      })
  },
  methods: {
    prev () {
      if (this.active > 0) {
        this.active--
        if (this.active === 1) {
          this.submitShow = false
        }
      }
    },
    next () {
      if (this.active < 2) this.active++
      if (this.active === 2) this.submitShow = true
    },
    submit () {
      console.log('submit')
      this.submitInfo.totalMark = this.totalMark
      const data = this.submitInfo
      data.hasChoice = data.choiceList.join(',')
      data.hasBlank = data.blankList.join(',')
      data.hasJudge = data.judgeList.join(',')
      data.id = this.row.id
      console.log(data)
      this.axios.post('/api/exam/addExam', data)
        .then(res => {
          this.$emit('closeEditProblemDialog', false)
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
</style>
