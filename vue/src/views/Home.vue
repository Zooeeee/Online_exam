<template>
  <div>
    <el-container>
      <Header></Header>
      <Info></Info>
      <el-main>
        <el-row>
          <el-col :xs='24' :sm='24' :md='{span:18,offset:3}' :lg='{span:16,offset:4}' :xl='{span:14,offset:5}'>
            <!-- 上半部分 -->
            <el-row class="up">
              <el-col :xs='24' :sm='24' :md='{span:10,offset:0}' :lg='{span:10,offset:0}' :xl='{span:10,offset:0}' class="title">
                <b>
                  Online  Exam
                </b>
                <p>
                   基于vue+springboot的在线考试系统
                </p>
                <p>
                   前端: vue全家桶 + elementUI
                </p>
                <p>
                   后台:springboot + spring data jpa
                </p>
              </el-col>
              <!--  轮播预览图 -->
              <el-col :xs='24' :sm='24' :md='{span:13,offset:1}' :lg='{span:13,offset:1}' :xl='{span:13,offset:1}'>
                <el-carousel :interval="4000" arrow="never" height="385px">
                  <el-carousel-item v-for="item in 5" :key="item">
                    <el-image
                      style="width: 100%; height: 100%"
                      :src="$store.getters.getServerUrl +'/img/home/'+item+'.png'"
                      fit="fit">
                    </el-image>
                  </el-carousel-item>
                </el-carousel>
              </el-col>
            </el-row>
            <!-- 下半部分 -->
            <el-row class="down">
              <el-col :xs='12' :sm='12' :md='12' :lg='8' :xl='8'
              v-for="(item, index) in this.cardData" :key="index">
                <IconCard :icon="item.icon" :textA="item.textA" :textB="item.textB">
                </IconCard>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-main>
      <el-footer>
        <Footer></Footer>
      </el-footer>
    </el-container>
  </div>
</template>
<script>
import Header from '@/views/Main/Header.vue'
import Footer from '@/views/Main/Footer.vue'
import Info from '@/views/Main/Info.vue'
import IconCard from '@/views/Home/IconCard.vue'
export default {
  components: { Header, Footer, Info, IconCard },
  data () {
    return {
      cardData: [
        {
          icon: '#icon-J-yishu',
          textA: '优雅美观',
          textB: '基于Element设计'
        },
        {
          icon: '#icon-jishurenyuan',
          textA: '最新技术栈',
          textB: 'vue+springboot'
        },
        {
          icon: '#icon-yidongduan',
          textA: '响应式',
          textB: '针对不同屏幕大小'
        },
        {
          icon: '#icon-shejimdpi',
          textA: '页面缓存',
          textB: '特殊场景中使用以减轻负载'
        },
        {
          icon: '#icon-quanxianshezhi',
          textA: '权限设计',
          textB: '不同权限对应不同的路由'
        },
        {
          icon: '#icon-zidongshibie',
          textA: '自动组装',
          textB: '避免重复，随机组装试卷'
        }
      ]
    }
  },
  created () {

  },
  mounted () {

  },
  methods: {

  }
}
</script>
<style lang="less" scoped>

.title{
  display: flex;
  flex-direction: column;
  height: 385px;
  align-items: flex-start;
  b{
    font-size: 45px;
    margin-top: 45px;
    margin-bottom: 10px;
    }
  p{
    margin-top: 10px;
    font-size: 22px;
    }
}
.el-header{
    text-align: center;
    line-height: 60px;
}
.el-footer{
    background-color: #F1F2F6;
    width:100%;
    // position:absolute;
    // bottom:0;
    // left:0;
    text-align: center;
    line-height: 20px;
    height: 45px !important;
}
.el-main{
  background: #F1F2F6;
  // header-60px footer-45px info-5vh
  height: calc(100vh - 5vh - 105px);
  padding: 0%;
}
.main{
  margin-top: 15px;
  background-color: #fff;
  min-height: calc(100vh - 5vh - 130px)
}
</style>
