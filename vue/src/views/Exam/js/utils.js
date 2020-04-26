const examPageAxios = function (that) {
  that.loading = true
  that.axios.post('/api/exam/getExamVoPage?size=' + that.pageInfo.size + '&current=' + (that.pageInfo.page - 1))
    .then(res => {
      console.log(res.data)
      that.pageInfo.count = res.data.count
      that.tableData = res.data.data
      that.loading = false
    })
    .catch(err => {
      console.error(err)
    })
}
const util = { examPageAxios }
export default util
