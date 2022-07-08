<template>
  <div>
    <div>姓名:{{name}}</div>
    <div>分数:{{rate}}</div>
  </div>
  <router-view/>
</template>

<script>
import $ from 'jquery';
import { ref} from "vue";
export default {
  name:"app",
  setup:()=>{
   let name=ref("");
   let  rate=ref("");
   $.ajax({
     url:"http://localhost:9090/pk/get/",
     type:"get",
     success: resp=>{
        name.value=resp.name;
        rate.value=resp.rate;
     }
   })
   return {
     name,rate
   }
 }
}
</script>

<style scoped>

</style>
