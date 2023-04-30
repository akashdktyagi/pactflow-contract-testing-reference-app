
<script>
  export default {
    props: {
      msg: String
    },
    data(){
      return {
        key: 'akash'
      }
    },
    methods: {

    },
    components:{
    },
    emits: [],
    computed: {
    }
  }
</script>

<template>
  <div class="greetings">
    <h1 class="green">Welcome to Employee Management App</h1>
  </div>

</template>

<style scoped>
  h1 {
    font-weight: 500;
    font-size: 2.6rem;
    top: -10px;
  }

  h3 {
    font-size: 1.2rem;
  }

  div {
    text-align: center;
  }

  .greetings h3 {
    text-align: center;
  }



  /* .greetings h1,


  @media (min-width: 1024px) {
    .greetings h1,
    .greetings h3 {
      text-align: left;
    } 
  }*/
</style>
