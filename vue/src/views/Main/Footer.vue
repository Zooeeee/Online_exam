<template>
    <div>
        <el-row>
          <el-col :xs='{span:16,offset:4}' :sm='{span:10,offset:7}' :md= '{span:8,offset:8}' :lg='{span:6,offset:9}' :xl='{span:6,offset:9}'>
            <div >
              <span>Git</span>
              <el-divider direction="vertical"></el-divider>
              <span>关于我</span>
              <el-divider direction="vertical"></el-divider>
              <span>联系我</span>
            </div>
            <div class = "copyright">
              <span>copyright</span>
              <i class = "iconfont icon-banquan" style="font-size:15px;margin:0 5px 0 5px;"></i>
               <span>Zhang Yin</span>
            </div>
          </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
  data () {
    return {

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

<style scoped lang="less">

.copyright{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
}
div{
  font-size: 14px;
  color: rgba(0,0,0,.45);
}
</style>
