const examPageAxios = function (that) {
  that.axios.post('/api/exam/getExamVoPage?size=' + that.pageInfo.size + '&current=' + (that.pageInfo.page - 1))
    .then(res => {
      console.log(res.data)
      that.pageInfo.count = res.data.count
      that.tableData = res.data.data
    })
    .catch(err => {
      console.error(err)
    })
}
const util = { examPageAxios }
export default util
