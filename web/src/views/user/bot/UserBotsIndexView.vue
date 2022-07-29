<template>
  <content-filed>
    My bot
  </content-filed>
</template>

<script>
import ContentFiled from "@/components/ContentFiled";
import $ from 'jquery';
import {useStore} from "vuex";

export default {
  name: "UserBotsIndexView",
  components: {ContentFiled},
  setup(){
    const store=useStore();
    // $.ajax({
    //   url:"http://localhost:9090/user/bot/add/",
    //   type:'POST',
    //    data:{
    //      title:"bot的标题",
    //      description:"bot的描述",
    //      content:"bot的内容",
    //    },
    //   headers:{
    //     Authorization: "Bearer "+store.state.user.token,
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // })
    // $.ajax({
    //   url:"http://localhost:9090/user/bot/remove/",
    //   type:'POST',
    //   data:{
    //   bot_id:1,
    //   },
    //   headers:{
    //     Authorization: "Bearer "+store.state.user.token,
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // })
    // $.ajax({
    //   url:"http://localhost:9090/user/bot/update/",
    //   type:'POST',
    //   data:{
    //     bot_id:7,
    //     title:"xxx",
    //     description:"yyy",
    //     content:"代码",
    //   },
    //   headers:{
    //     Authorization: "Bearer "+store.state.user.token,
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // })
    $.ajax({
      url:"http://localhost:9090/user/bot/getlist/",
      type:'GET',
      headers:{
        Authorization: "Bearer "+store.state.user.token,
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })
  }
}
</script>

<style scoped>

</style>