<template>
    <div>
        <!-- 弹框上方进度条  -->
        <el-steps :active="active" finish-status="success" >
            <el-step title="步骤 1"></el-step>
            <el-step title="步骤 2"></el-step>
            <el-step title="步骤 3"></el-step>
        </el-steps>
        <!-- 中间的表单部分 其中根据第一个表单的选择 渲染第二个 第三个表单 -->
        <!-- 第一个表单   设置 题干名称  题目类型 和 题目所属科目 -->
        <!-- TODO -->
        <el-form  label-position="center" label-width="80px" v-show="this.active === 0 ? true : false">
          <el-form-item label="题干" >
            <el-input v-model="submitInfo.name" ></el-input>
          </el-form-item>
          <!-- todo:这里可以用一下带建议的输入框  -->
          <el-form-item  label="科目">
            <el-select  placeholder="请选择" v-model="submitInfo.subject">
              <el-option v-for="(item, index) in selectInfo.subject" :key="index" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="类型" >
            <el-select  placeholder="请选择" v-model="submitInfo.type">
              <el-option v-for="(item, index) in selectInfo.type" :key="index" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <!-- 第二个表单 根据题型 选择渲染选项和答案 -->
        <el-form v-show="this.active === 1? true : false"   >
          <!-- 填空题 -->
          <div v-show="this.submitInfo.type === '填空'? true : false">
            <el-form-item label="填空前" >
              <el-input v-model="blankInfo.beforeBlank" ></el-input>
            </el-form-item>
            <el-form-item label="答案" >
              <el-input v-model="blankInfo.answer" ></el-input>
            </el-form-item>
            <el-form-item label="填空后" >
              <el-input v-model="blankInfo.afterBlank" ></el-input>
            </el-form-item>
          </div>
          <!-- 选择题 -->
          <div v-show="this.submitInfo.type === '单选'? true : false">
            <el-form-item label="A" >
              <el-input v-model="choiceInfo.choice.a" ></el-input>
            </el-form-item>
            <el-form-item label="B" >
              <el-input v-model="choiceInfo.choice.b" ></el-input>
            </el-form-item>
            <el-form-item label="C" >
              <el-input v-model="choiceInfo.choice.c" ></el-input>
            </el-form-item>
            <el-form-item label="D" >
              <el-input v-model="choiceInfo.choice.d" ></el-input>
            </el-form-item>
            <el-form-item label="答案" >
              <el-select  placeholder="请选择" v-model="choiceInfo.answer">
                <el-option v-for="(item, index) in choiceInfo.choice" :key="index" :label="item" :value="item"></el-option>
              </el-select>
            </el-form-item>
          </div>
          <!-- 判断题 -->
           <div v-show="this.submitInfo.type === '判断'? true : false">
            <el-form-item label="题目描述" >
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                placeholder="请输入内容"
                v-model="judgeInfo.content"
                style="width:300px;"
                >
              </el-input>
            </el-form-item>
            <el-form-item >
              <el-radio v-model="judgeInfo.answer" label="正确">正确</el-radio>
              <el-radio v-model="judgeInfo.answer" label="错误">错误</el-radio>
            </el-form-item >
          </div>
        </el-form>
        <!-- 第三个表单  添加难度 分析  -->
        <el-form label-position="right" label-width="80px"  v-show="this.active === 2? true : false">
          <el-form-item label="分析" >
            <el-input
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 4}"
              placeholder="请输入内容"
              v-model="submitInfo.analysis"
              style="width:300px;"
              >
            </el-input>
          </el-form-item>
          <el-form-item label="难度">
            <el-select  placeholder="请选择" v-model="submitInfo.difficulty">
              <el-option v-for="(item, index) in selectInfo.difficulty" :key="index" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
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
      timeout: null,
      // 步骤栏相关属性
      active: 0,
      submitShow: false,
      // 服务器请求已存在的分类
      selectInfo: { },
      submitInfo: {
        // 第一个表单相关属性
        name: '',
        subject: '',
        type: '',
        // 第二个表单相关属性
        content: '',
        answer: '',
        // 第三个表相关属性
        analysis: '',
        difficulty: '',
        creator: '',
        isUsed: 1
      },
      blankInfo: {
        answer: '',
        beforeBlank: '',
        afterBlank: ''
      },
      choiceInfo: {
        answer: '',
        choice: {
          a: '',
          b: '',
          c: '',
          d: ''
        }
      },
      judgeInfo: {
        answer: '正确'
      },
      rules: {
        required: [
          { required: true, message: '请输入值', trigger: 'blur' }
        ]
      }
    }
  },
  created () {

  },
  mounted () {
    // 向服务器请求数据到 下拉框和输入建议框
    this.axios.post('/api/problem/getSelectCondition')
      .then(res => {
        this.selectInfo = res.data
        this.submitInfo.type = res.data.type[0]
        this.submitInfo.subject = res.data.subject[0]
        this.submitInfo.difficulty = res.data.difficulty[0]
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
      if (this.submitInfo.type === '单选') {
        this.submitInfo.content = this.choiceInfo.choice.a + ',' + this.choiceInfo.choice.b + ',' + this.choiceInfo.choice.c + ',' + this.choiceInfo.choice.d
        this.submitInfo.answer = this.choiceInfo.answer
      }
      if (this.submitInfo.type === '判断') {
        this.submitInfo.content = this.judgeInfo.content
        this.submitInfo.answer = this.judgeInfo.answer
      }
      if (this.submitInfo.type === '填空') {
        this.submitInfo.content = this.blankInfo.beforeBlank + '()' + this.blankInfo.afterBlank
        this.submitInfo.answer = this.blankInfo.answer
      }
      this.submitInfo.id = 1
      this.submitInfo.creator = sessionStorage.getItem('nickName')
      console.log(this.submitInfo)

      this.axios.post('/api/problem/addProblem', this.submitInfo)
        .then(res => {
          this.$emit('closeAddProblemDialog', false)
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
.el-input ,.el-select{
  width: 300px;
}

.el-form{
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
