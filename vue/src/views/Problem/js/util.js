const tableDataPageAxios = function (page, size, that) {
  that.axios.post('/api/problem/getProblemVoPage?page=' +
    (page - 1) + '&size=' + size)
    .then(res => {
      that.tableData = res.data
    // console.log(res)
    })
    .catch(err => {
      console.error(err)
    })
}

const tableDataLikeAxios = function (selectCondition, that) {
  // console.log(that.selectCondition)
  that.axios.post('/api/problem/getConditionResult', that.selectCondition)
    .then(res => {
      // console.log(res)
      that.tableData = res.data
      that.tableShow = true
    })
    .catch(err => {
      console.error(err)
    })
}

const util = { tableDataPageAxios, tableDataLikeAxios }
export default util
