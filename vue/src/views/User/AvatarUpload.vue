
<template>
  <div class = "main">
    <el-upload
      class="avatar-uploader"
      action="/api/user/uploadAvatar"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
      :on-progress = "onProgress"
      :on-change = "onChange"
      name = "image_data"
      :auto-upload="false"
      ref = "upload"
      :headers = 'headers'
      >
       <!--  -->
      <img v-if="imageUrl" :src="imageUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
    <el-button type="primary" @click="submitAvatar" size="small">保存头像</el-button>
  </div>
</template>
<style scoped lang="less">
  .main{
    display: flex;
    flex-direction: column;
    align-items: center;
  .el-button{
    margin-top: 10px;
   }
  }
  .avatar-uploader{
    display: flex ;
    flex-direction: row;
    justify-content: center;

  }
 .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
   border-radius: 6px;
 //   border-radius: 50%;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
    //
    border-radius: 50%;
  }
</style>

<script>
export default {
  data () {
    return {
      imageUrl: '',
      headers: {
        Authorization: sessionStorage.getItem('Authorization')
      }
    }
  },
  mounted () {
    this.imageUrl = sessionStorage.getItem('avatar')
  },
  methods: {
    submitAvatar () {
      this.$refs.upload.submit()
    },
    onChange (file, fileList) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    handleAvatarSuccess (res, file) {
      console.log('res:' + res)
      this.$store.commit('setUserAvatar', res)
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    onProgress (event, file, fileList) {
      console.log(event, file, fileList)
    },
    uploadAvatar () {
      console.log('手动上传')
    }
  }
}
</script>
