<template>
    <div >
      <!-- 选择题 -->
        <el-form :model="choiceForm" v-show="showForm[0]">
          <el-form-item label="题干" :label-width="formLabelWidth">
            <el-input v-model="choiceForm.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="A:" :label-width="formLabelWidth">
            <el-input v-model="choiceForm.selectValue[0]" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="B:" :label-width="formLabelWidth">
            <el-input v-model="choiceForm.selectValue[1]" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="C:" :label-width="formLabelWidth">
            <el-input v-model="choiceForm.selectValue[2]" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="D:" :label-width="formLabelWidth">
            <el-input v-model="choiceForm.selectValue[3]" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="答案:" :label-width="formLabelWidth">
            <el-select v-model="choiceForm.answer" placeholder="请选择">
              <el-option
                v-for="item in choiceForm.selectValue"
                :key="item"
                :label="item"
                :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="分析:" :label-width="formLabelWidth">
            <!-- <el-input v-model="choiceForm.analysis" autocomplete="off"></el-input> -->
            <el-input
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 4}"
              v-model="choiceForm.analysis">
            </el-input>
          </el-form-item>
          <el-form-item :label-width="formLabelWidth">
            <el-button type="primary" @click="choiceSubmit">立即修改</el-button>
          </el-form-item>
        </el-form>
        <!-- 填空题 -->
        <el-form :model="blankForm" v-show="showForm[1]" >
          <el-form-item label="题干:" :label-width="formLabelWidth">
            <el-input v-model="blankForm.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="填空前:" :label-width="formLabelWidth">
            <el-input v-model="blankForm.beforeBlank" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="填空后:" :label-width="formLabelWidth">
            <el-input v-model="blankForm.afterBlank" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="答案:" :label-width="formLabelWidth">
            <el-input v-model="blankForm.answer" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="分析:" :label-width="formLabelWidth">
            <!-- <el-input v-model="blankForm.analysis" autocomplete="off"></el-input> -->
            <el-input
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 4}"
              v-model="blankForm.analysis">
            </el-input>
          </el-form-item>
          <el-form-item label="预览:" :label-width="formLabelWidth">
              {{blankForm.beforeBlank + '('+ blankForm.answer +')' + blankForm.afterBlank}}
          </el-form-item>
          <el-form-item :label-width="formLabelWidth">
            <el-button type="primary" @click="blankSubmit">立即修改</el-button>
          </el-form-item>
        </el-form>
        <!-- 判断题 -->
        <el-form :model="judgeForm" v-show="showForm[2]" >
          <el-form-item label="题干" :label-width="formLabelWidth">
            <el-input v-model="judgeForm.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="答案" :label-width="formLabelWidth">
            <el-radio-group v-model="judgeForm.answer">
              <el-radio label="正确">正确</el-radio>
              <el-radio label="错误">错误</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="分析:" :label-width="formLabelWidth">
            <!-- <el-input v-model="judgeForm.analysis" autocomplete="off"></el-input> -->
             <el-input
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 4}"
              v-model="judgeForm.analysis">
            </el-input>
          </el-form-item>
           <el-form-item :label-width="formLabelWidth">
            <el-button type="primary" @click="judgeSubmit">立即修改</el-button>
          </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
  props: ['dialogInfo'],
  data () {
    return {
      formLabelWidth: '120px',
      showForm: [true, false, false],
      choiceForm: {
        name: '',
        answer: '',
        analysis: '',
        selectValue: []
      },
      judgeForm: {
        name: '',
        answer: '',
        analysis: '',
        content: '正确,错误'
      },
      blankForm: {
        name: '',
        answer: '',
        beforeBlank: '',
        afterBlank: '',
        analysis: ''
      }
    }
  },
  created () {

  },
  updated () {
  },
  mounted () {
    console.log(this.dialogInfo)
    // 选择渲染哪种题型
    // 单选题渲染
    if (this.dialogInfo.type === '单选') {
      this.showForm[0] = true
      this.showForm[1] = this.showForm[2] = false
      this.choiceForm.name = this.dialogInfo.name
      this.choiceForm.answer = this.dialogInfo.answer
      this.choiceForm.analysis = this.dialogInfo.analysis
      this.choiceForm.selectValue = this.dialogInfo.content.split(',')
    }
    // 填空题渲染
    if (this.dialogInfo.type === '填空') {
      this.showForm[1] = true
      this.showForm[0] = this.showForm[2] = false
      // this.blankForm.name = this.dialogInfo.name
      const content = this.dialogInfo.content.split('()')
      this.blankForm.beforeBlank = content[0]
      this.blankForm.afterBlank = content[1]
      this.blankForm.name = this.dialogInfo.name
      this.blankForm.answer = this.dialogInfo.answer
      this.blankForm.analysis = this.dialogInfo.analysis
    }
    // 判断题渲染
    if (this.dialogInfo.type === '判断') {
      this.showForm[2] = true
      this.showForm[1] = this.showForm[0] = false
      this.judgeForm.name = this.dialogInfo.name
      this.judgeForm.answer = this.dialogInfo.answer
      this.judgeForm.analysis = this.dialogInfo.analysis
    }
  },
  methods: {
    // 单选题提交
    choiceSubmit () {
      console.log(this.choiceForm)
      const arr = this.choiceForm.selectValue
      const content = arr[0].concat(',').concat(arr[1]).concat(',').concat(arr[2]).concat(',').concat(arr[3])
      const axiosInfo = {
        id: this.dialogInfo.id,
        name: this.choiceForm.name,
        analysis: this.choiceForm.analysis,
        answer: this.choiceForm.answer,
        content
      }
      console.log(axiosInfo)
      this.axios.post('/api/problem/editProblem', axiosInfo)
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.error(err)
        })
      this.$emit('problemDialogShow', false)
    },
    // 判断题提交
    judgeSubmit () {
      console.log(this.judgeForm)
      const axiosInfo = {
        id: this.dialogInfo.id,
        name: this.judgeForm.name,
        analysis: this.judgeForm.analysis,
        answer: this.judgeForm.answer,
        content: this.judgeForm.content
      }
      console.log(axiosInfo)
      this.axios.post('/api/problem/editProblem', axiosInfo)
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.error(err)
        })
      this.$emit('problemDialogShow', false)
    },
    // 填空题提交
    blankSubmit () {
      const axiosInfo = {
        id: this.dialogInfo.id,
        name: this.blankForm.name,
        analysis: this.blankForm.analysis,
        answer: this.blankForm.answer,
        content: this.blankForm.beforeBlank + '()' + this.blankForm.afterBlank
      }
      console.log('axiosInfo:' + axiosInfo)
      this.axios.post('/api/problem/editProblem', axiosInfo)
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.error(err)
        })
      this.$emit('problemDialogShow', false)
    }
  }
}
</script>

<style scoped lang="less">

</style>
