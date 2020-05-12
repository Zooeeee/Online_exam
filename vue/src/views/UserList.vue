<template>
    <div ref="list" class="infinite-list-container" @scroll="scrollEvent($event)" >
      <div class="infinite-list-phantom" :style="{ height: (this.listHeight) + 'px' }"></div>
      <div  class="infinite-list" :style="{ transform: getTransform }">
        <el-table
        :data="this.showData"
        border
        :max-height="750"
        :row-style = "rowStyle"
        style="width: 100%"
        v-loading.body="this.loading"
        :show-header='false'
        >
        <el-table-column
          prop="id"
           fixed
          label="id"
          width="180">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="password"
          label="密码">
        </el-table-column>
        <el-table-column
          prop="nickName"
          label="昵称">
        </el-table-column>
        <!-- <el-table-column
          prop="email"
          label="邮箱">
        </el-table-column> -->
      </el-table>
      </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      rowStyle: {
        height: '50px'
      },
      // 可视区域高度
      screenHeight: 0,
      // 偏移量
      startOffset: 0,
      // 起始索引
      start: 0,
      // 结束索引
      end: 0,
      // 可视数量
      count: 15
    }
  },
  created () {
    this.$store.dispatch('getUserList')
  },
  mounted () {
    this.end = this.start + this.count
  },
  computed: {
    // 偏移量对应的style
    getTransform () {
      return `translate3d(0,${this.startOffset}px,0)`
    },
    // 占位表格高度
    listHeight () {
      return this.sourceData.length * parseInt(this.rowStyle.height)
    },
    // 源数据
    sourceData: function () {
      return this.$store.getters.getUserList
    },
    // 控制loading
    loading: function () {
      return !this.$store.getters.getUserList.length > 0
    },
    // 展示数据
    showData: function () {
      return this.sourceData.slice(this.start, Math.min(this.end, this.sourceData.length))
    }
  },
  methods: {
    scrollEvent () {
      // 当前滚动位置
      const scrollTop = this.$refs.list.scrollTop
      // console.log('当前滚动位置scollTop:=====' + scrollTop)
      // 此时的开始索引
      this.start = Math.floor(scrollTop / parseInt(this.rowStyle.height))
      // console.log('此时的开始索引start====' + this.start)
      // 此时的结束索引
      this.end = this.start + this.count
      // console.log('此时的结束索引end====' + this.end)
      // 此时的偏移量
      this.startOffset = scrollTop - (scrollTop % parseInt(this.rowStyle.height))
      // console.log('此时的偏移量startOffset ====' + this.startOffset)
    }
  }
}
</script>

<style scoped lang="less">
.infinite-list-container {
  height: 100%;
  overflow: auto;
  position: relative;
  -webkit-overflow-scrolling: touch;
}

.infinite-list-phantom {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  z-index:1;
}

// .infinite-list{
//   position: fixed;
// }

// .infinite-list {
//   left: 0;
//   right: 0;
//   top: 0;
//   position: absolute;
//   text-align: center;
// }

// .infinite-list-item {
//   padding: 10px;
//   color: #555;
//   box-sizing: border-box;
//   border-bottom: 1px solid #999;
// }
</style>
