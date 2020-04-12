<template>
    <div class="iconCard">
        <div class="imgContainer">
          <svg class="icon" aria-hidden="true">
            <use :href="this.icon"></use>
          </svg>
        </div>
        <div class="text">
            <p> {{this.textA}}</p>
            <p> {{this.textB}} </p>
        </div>
    </div>
</template>

<script>
export default {
  props: ['icon', 'textA', 'textB'],
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
.iconCard{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 200px;
}
.imgContainer{
    background-color: #fff;
    border-radius: 50%;
    height: 85px;
    width: 85px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    position: relative;
    transition: transform 0.5s;
    &:hover{
        transform: translateY(-10px)
    }
    svg{
           font-size: 30px;
    }
}

.text{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 10px;
    position: relative;
    bottom: 0;
    p{
        margin-block-start: 5px;
        margin-block-end: 0;
    }
}
</style>
