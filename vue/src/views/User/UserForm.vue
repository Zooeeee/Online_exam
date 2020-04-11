<template>
    <div >
        <AvatarUpload></AvatarUpload>
         <el-divider>
          <i class="iconfont icon-touxiang"></i>
        </el-divider>
      <el-form label-position="top" label-width="80px" v-model="form" >
        <el-form-item label="昵称">
          <el-input v-model="form.nickName" >
          </el-input>
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="form.email" >
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存修改</el-button>
        </el-form-item>
      </el-form>
       <el-divider>
          <i class="iconfont icon-gerenxinxi"></i>
        </el-divider>
    </div>
</template>

<script>
import AvatarUpload from '@/views/User/AvatarUpload.vue'
export default {
  components: { AvatarUpload },
  data () {
    return {
      form: {
        nickName: '',
        email: ''
      }
    }
  },
  created () {

  },
  mounted () {
    this.axios.post('/api/user/getUserNickNameAndEmail?id=' + sessionStorage.getItem('id'))
      .then(res => {
        console.log(res.data)
        this.form = {
          nickName: res.data.nickName,
          email: res.data.email
        }
      })
      .catch(err => {
        console.error(err)
      })
  },
  methods: {
    onSubmit () {
      console.log(this.form)
      const params = {
        id: sessionStorage.getItem('id'),
        nickName: this.form.nickName,
        email: this.form.email
      }
      console.log(params)
      this.axios.post('/api/user/setUserNickNameAndEmail', params)
        .then(res => {
          console.log(res)
          this.$store.commit('setUserNickName', params.nickName)
        })
        .catch(err => {
          console.error(err)
        })
    }
  }
}
</script>

<style scoped lang="less">
i{
  font-size: 30px;
}

.el-form{
  display: flex;
  flex-direction: column;
  align-items: center;
  .el-input{
  width: 300px;
}
}
</style>
