import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // 请求服务器地址
    // http://127.0.0.1:80
    // http://120.79.183.71:80
    serverUrl: 'http://127.0.0.1:80',
    // 存储User个人信息
    userInfo: {
      avatar: sessionStorage.getItem('avatar') === '' ? '' : sessionStorage.getItem('avatar'),
      nickName: sessionStorage.getItem('nickName') === '' ? '' : sessionStorage.getItem('nickName')
    },
    // 存储problem页面信息
    problemInfo: {
      count: 0
    },
    // 存储takeExam页面的所有考试信息
    allExamVo: []
  },
  getters: {
    // 获取地址
    getServerUrl (state) {
      return state.serverUrl
    },
    // 获取User个人信息
    getUserAvatar (state) {
      return state.userInfo.avatar
    },
    getUserNickName (state) {
      return state.userInfo.nickName
    },
    // 获取problem信息
    getProblemCount (state) {
      return state.problemInfo.count
    },
    // 获取takeExam页面的所有考试信息
    getAllExamVo (state) {
      return state.allExamVo
    }
  },
  mutations: {
    // 设置User个人信息
    setUserAvatar (state, src) {
      sessionStorage.setItem('avatar', src)
      state.userInfo.avatar = src
    },
    setUserNickName (state, nickName) {
      sessionStorage.setItem('nickName', nickName)
      state.userInfo.nickName = nickName
    },
    // 设置problem页面的数据总数
    setProblemCount (state, count) {
      state.problemInfo.count = count
    },
    // 设置takeExam页面的所有考试信息
    setAllExamVo (state, data) {
      state.allExamVo = data
    }
  },
  actions: {
    // 获取problem界面的数据条数
    getProblemVoCount (store) {
      axios.post('/api/problem/getProblemVoCount')
        .then(res => {
          store.commit('setProblemCount', res.data)
        })
        .catch(err => {
          console.error(err)
        })
    },
    // 获取takeExam界面的所有考试信息
    // 利用state做一次缓存 提高运行效率
    getAllExamVo (store, that) {
      if (store.state.allExamVo.length === 0) {
        // console.log('store.state.allExamVo.length === 0')
        that.loading = true
        axios.post('api/exam/getAllExamVoPage')
          .then(res => {
            store.commit('setAllExamVo', res.data)
            console.log('出现一次vuex的请求，若出现第二次则未实现缓存效果')
            that.loading = false
          })
          .catch(err => {
            console.error(err)
          })
      }
    }
  },
  modules: {
  }
})
