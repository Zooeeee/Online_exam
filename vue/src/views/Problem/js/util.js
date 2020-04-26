const tableDataPageAxios = function (page, size, that) {
  that.loading = true
  that.axios.post('/api/problem/getProblemVoPage?page=' +
    (page - 1) + '&size=' + size)
    .then(res => {
      that.tableData = res.data
      that.loading = false
    // console.log(res)
    })
    .catch(err => {
      console.error(err)
    })
}

const tableDataLikeAxios = function (selectCondition, that) {
  that.loading = true
  // console.log(that.selectCondition)
  that.axios.post('/api/problem/getConditionResult', that.selectCondition)
    .then(res => {
      // console.log(res)
      that.tableData = res.data
      that.tableShow = true
      that.loading = false
    })
    .catch(err => {
      console.error(err)
    })
}

const util = { tableDataPageAxios, tableDataLikeAxios }
export default util
