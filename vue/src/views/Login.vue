<template>
  <transition name="el-fade-in-linear">
  <div id='login' v-show="isShow">
    <!-- <img src='../assets/img/Starry.jpg' alt="" width="100%" height="100%"
    style="float:left;z-index:-1;"
    > -->
    <div id='bgd'>
      <canvas
        id='myCanvas'
        :width='width'
        :height='height'
      >
      </canvas>
    </div>
    <div id='loginBox'>
      <h4>在线自测系统</h4>
      <el-form
        :model='loginForm'
        :rules='loginRules'
        ref='loginForm'
        label-width='0px'
      >
        <el-form-item
          label=''
          prop='username'
          style='margin-top:40px'
        >
          <el-row>
            <el-col :span='2'>
              <span class='iconfont el-icon-user'></span>
            </el-col>
            <el-col :span='22'>
              <el-input
                class='inps'
                placeholder='用户名'
                v-model='loginForm.username'
              ></el-input>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item
          label=''
          prop='password'
        >
          <el-row>
            <el-col :span='2'>
              <span class='iconfont el-icon-lock'></span>
            </el-col>
            <el-col :span='22'>
              <el-input
                class='inps'
                placeholder='密码'
                v-model='loginForm.password'
                show-password
              ></el-input>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item style='margin-top:55px'>
          <el-button
            type='primary'
            round
            class='submitBtn'
            @click='submitForm'
          >登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
    </transition>
</template>

<script>
// 解析token
import jwtDecode from 'jwt-decode'

export default {
  data () {
    return {
      isShow: false,
      canvas: null,
      context: null,
      stars: [], // 星星数组
      shadowColorList: [
        '#39f',
        '#ec5707',
        '#b031d4',
        '#22e6c7',
        '#92d819',
        '#14d7f1',
        '#e23c66'
      ], // 阴影颜色列表
      directionList: ['leftTop', 'leftBottom', 'rightTop', 'rightBottom'], // 星星运行方向
      speed: 50, // 星星运行速度
      last_star_created_time: new Date(), // 上次重绘星星时间
      Ball: class Ball {
        constructor (radius) {
          this.x = 0
          this.y = 0
          this.radius = radius
          this.color = ''
          this.shadowColor = ''
          this.direction = ''
        }

        draw (context) {
          context.save()
          context.translate(this.x, this.y)
          context.lineWidth = this.lineWidth
          var myGradient = context.createLinearGradient(0, 0, 0, 8)
          myGradient.addColorStop(0, this.color)
          myGradient.addColorStop(1, this.shadowColor)
          context.fillStyle = myGradient
          context.beginPath()
          context.arc(0, 0, this.radius, 0, Math.PI * 2, true)
          context.closePath()

          context.shadowColor = this.shadowColor
          context.shadowOffsetX = 0
          context.shadowOffsetY = 0
          context.shadowBlur = 10

          context.fill()
          context.restore()
        }
      }, // 工厂模式定义Ball类
      width: window.innerWidth,
      height: window.innerHeight,
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  beforeCreate () {
    // 显示loading
    const loading = this.$loading({
      text: '努力加载中',
      background: '#00152A',
      type: 'circles'
    })
    // 关闭loading
    setTimeout(() => {
      loading.close()
      this.isShow = true
    }, 3000)
  },
  methods: {
    // 提交登录
    submitForm () {
      // console.log(this.loginForm.username)
      // console.log(this.loginForm.password)
      this.axios.post('/api/login/loginCheck?username=' +
      this.loginForm.username +
      '&password=' + this.loginForm.password)
        .then(res => {
          console.log(res)
          /* 通过返回的数据的状态码是不是200来判断是不是登陆成功
          * 若成功，则解析附带过来的token
          * 通过解析token 得到 用户昵称，用户id  在data中找到用户权限
          * 将用户昵称，用户id，token 权限存入sessionStorge
          * token解析出的jti是id  sub是nickname
          * 并开启axios的拦截器 将以后发送的每个http请求headers加上token
          * 跳转页面，弹窗提示
          * 若失败，则弹出失败的框，并清空输入框
          */
          if (res.data.status === '200') {
            const decode = jwtDecode(res.data.token)
            sessionStorage.setItem('id', decode.jti)
            sessionStorage.setItem('nickName', decode.sub)
            this.$store.commit('setUserNickName', decode.sub)
            // this.$store.state.userInfo.nickName = decode.sub
            sessionStorage.setItem('Authorization', res.data.token)
            sessionStorage.setItem('power', res.data.power)
            sessionStorage.setItem('role', res.data.role)
            // 此处是用服务器静态资源地址拼接而成
            sessionStorage.setItem('avatar', res.data.avatar)
            this.$store.commit('setUserAvatar', res.data.avatar)
            // this.$store.state.userInfo.avatar = res.data.avatar
            // 添加请求拦截器，在请求头中加token
            this.axios.interceptors.request.use(
              config => {
                if (sessionStorage.getItem('Authorization')) {
                  config.headers.Authorization = sessionStorage.getItem('Authorization')
                }
                return config
              },
              error => {
                return Promise.reject(error)
              })
            // 添加请求拦截器---------end
            this.$notify({
              title: '成功',
              message: '欢迎回来！' + decode.sub,
              type: 'success',
              offset: 100,
              duration: 2000
            })
            this.$router.push('/home')
          } else {
            this.$message.error('账号密码错误，请再次输入')
            this.loginForm.username = ''
            this.loginForm.password = ''
          }
        })
        .catch(err => {
          console.error(err)
        })
    },
    // 重复动画
    drawFrame () {
      requestAnimationFrame(this.drawFrame)
      this.context.clearRect(0, 0, this.canvas.width, this.canvas.height)
      this.createStar(false)
      this.stars.forEach(this.moveStar)
    },
    // 展示所有的星星
    createStar (params) {
      const now = new Date()
      if (params) {
        // 初始化星星
        for (let i = 0; i < 50; i++) {
          const radius = Math.random() * 3 + 2
          const star = new this.Ball(radius)
          star.x = Math.random() * this.canvas.width + 1
          star.y = Math.random() * this.canvas.height + 1
          star.color = '#ffffff'
          star.shadowColor = this.shadowColorList[
            Math.floor(Math.random() * this.shadowColorList.length)
          ]
          star.direction = this.directionList[
            Math.floor(Math.random() * this.directionList.length)
          ]
          this.stars.push(star)
        }
      } else if (!params && now - this.last_star_created_time > 3000) {
        // 每隔3秒重绘修改颜色其中30个球阴影颜色
        for (let i = 0; i < 30; i++) {
          this.stars[i].shadowColor = this.shadowColorList[
            Math.floor(Math.random() * this.shadowColorList.length)
          ]
        }
        this.last_star_created_time = now
      }
    },
    // 移动
    moveStar (star, index) {
      if (star.y - this.canvas.height > 0) {
        // 触底
        if (Math.floor(Math.random() * 2) === 1) {
          star.direction = 'leftTop'
        } else {
          star.direction = 'rightTop'
        }
      } else if (star.y < 2) {
        // 触顶
        if (Math.floor(Math.random() * 2) === 1) {
          star.direction = 'rightBottom'
        } else {
          star.direction = 'leftBottom'
        }
      } else if (star.x < 2) {
        // 左边
        if (Math.floor(Math.random() * 2) === 1) {
          star.direction = 'rightTop'
        } else {
          star.direction = 'rightBottom'
        }
      } else if (star.x - this.canvas.width > 0) {
        // 右边
        if (Math.floor(Math.random() * 2) === 1) {
          star.direction = 'leftBottom'
        } else {
          star.direction = 'leftTop'
        }
      }
      if (star.direction === 'leftTop') {
        star.y -= star.radius / this.speed
        star.x -= star.radius / this.speed
      } else if (star.direction === 'rightBottom') {
        star.y += star.radius / this.speed
        star.x += star.radius / this.speed
      } else if (star.direction === 'leftBottom') {
        star.y += star.radius / this.speed
        star.x -= star.radius / this.speed
      } else if (star.direction === 'rightTop') {
        star.y -= star.radius / this.speed
        star.x += star.radius / this.speed
      }
      star.draw(this.context)
    }
  },
  mounted () {
    // 画布相关
    this.canvas = document.getElementById('myCanvas')
    this.context = this.canvas.getContext('2d')

    this.createStar(true)
    this.drawFrame()

    sessionStorage.clear()
    // alert('mouted')
  }
}
</script>

<style lang='less' scoped>
#login {
  width: 100vw ;
  padding: 0 ;
  margin: 0;
  height: 100vh ;
  font-size: 16px ;
  color: #fff;
  font-family: 'Source Sans Pro';
  background-image: url('http://127.0.0.1:80/img/home/Starry.jpg');
  position: relative;
  background-size: 1920px 1080px;
  min-height: 100vh;
   font-family: pingfang;
  background-repeat: no-repeat;
  background-position: center;
  background-color: #242645;
   //background-size: 100%;
  // background:
  // url('../assets/img/Starry.jpg')
  // no-repeat center;
  // background-size: 1920px 1080px;
  #bgd {
    height: 100vh;
    width: 100vw;
    overflow: hidden;
  }
  #loginBox {
    width: 240px;
    height: 280px;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    padding: 50px 40px 40px 40px;
    box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
    opacity: 1;
    background: linear-gradient(
      230deg,
      rgba(53, 57, 74, 0) 0%,
      rgb(0, 0, 0) 100%
    );
    /deep/ .inps input {
      border: none;
      color: #fff;
      background-color: transparent;
      font-size: 12px;
    }
    .submitBtn {
      background-color: transparent;
      color: #39f;
      width: 200px
    }
    .iconfont {
      color: #fff
    }
  }
}
</style>
