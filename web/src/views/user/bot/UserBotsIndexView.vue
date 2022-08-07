<template>
<div class="container">
  <div class="row">
    <div class="col-4">
<!--      名片部分-->
    <UserProfile></UserProfile>

    </div>
    <div class="col-8">
      <div class="card" style="margin-top: 20px">
        <div class="card-header">
          <span style="font-size: 120%">我的bot</span>
          <button type="button" class="btn btn-primary float-end"  data-bs-toggle="modal" data-bs-target="#add_bot_button">
            创建Bot
          </button>
          <!-- Modal -->
          <div class="modal fade" id="add_bot_button" tabindex="-1" >
            <div class="modal-dialog modal-xl" >
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">创建Bot</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                  <div class="mb-3">
                    <label for="bottitle" class="form-label">名称</label>
                    <input v-model="botadd.title" type="text" class="form-control" id="bottitle" placeholder="请输入bot名称">
                  </div>
                  <div class="mb-3">
                    <label for="botdescription" class="form-label">简介</label>
                    <input v-model="botadd.description" type="text" class="form-control" id="botdescription" placeholder="请输入bot简介">
                  </div>
                  <div class="mb-3">
                    <label for="botcode" class="form-label">Bot</label>
                    <VAceEditor
                        v-model:value="botadd.content"
                        @init="editorInit"
                        lang="c_cpp"
                        theme="textmate"
                        style="height: 300px" />
                  </div>

                </div>
                <div class="modal-footer">
                  <div  class="error_message">{{ botadd.error_message }}</div>
                  <button type="button" class="btn btn-primary" @click="add_bot">提交</button>
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                </div>
              </div>
            </div>
          </div>

        </div>
        <div class="card-body">
        <table class="table table-striped table-hover">
           <thead>
            <tr>
              <th>名称</th>
              <th>Bot简介</th>
              <th>创建时间</th>
              <th>操作</th>
            </tr>
           </thead>
          <tbody>
          <tr v-for="bot in bots" :key="bot.id">
              <td>{{bot.title}}</td>
              <td>{{bot.description}}</td>
             <td>{{bot.createtime}}</td>
               <td>
                 <button type="button" class="btn btn-secondary"  data-bs-toggle="modal" :data-bs-target="'#updatebot-'+bot.id">修改</button>
                 <button type="button" class="btn btn-danger" style="margin-left: 20px" @click="remove_bot(bot)">删除</button>

                 <!-- Modal -->
                 <div class="modal fade" :id="'updatebot-'+bot.id" tabindex="-1" >
                   <div class="modal-dialog modal-xl" >
                     <div class="modal-content">
                       <div class="modal-header">
                         <h5 class="modal-title" >修改Bot</h5>
                         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                       </div>

                       <div class="modal-body">
                         <div class="mb-3">
                           <label for="bottitle" class="form-label">名称</label>
                           <input v-model="bot.title" type="text" class="form-control" id="bottitle" placeholder="请输入bot名称">
                         </div>
                         <div class="mb-3">
                           <label for="botdescription" class="form-label">简介</label>
                           <input v-model="bot.description" type="text" class="form-control" id="botdescription" placeholder="请输入bot简介">
                         </div>
                         <div class="mb-3">
                           <label for="botcode" class="form-label">Bot</label>
                           <VAceEditor
                               v-model:value="bot.content"
                               @init="editorInit"
                               lang="c_cpp"
                               theme="textmate"
                               style="height: 300px" />
                         </div>

                       </div>
                       <div class="modal-footer">
                         <div  class="error_message">{{ bot.error_message }}</div>
                         <button type="button" class="btn btn-primary"  @click="update_bot(bot)">提交</button>
                         <button type="button" class="btn btn-secondary" style="margin-left: 20px" data-bs-dismiss="modal">取消</button>
                       </div>
                     </div>
                   </div>
                 </div>


               </td>
          </tr>
          </tbody>
         </table>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import {ref,reactive} from "vue";
import $ from 'jquery';
import {useStore} from "vuex";
 import {Modal} from 'bootstrap/dist/js/bootstrap';
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';
import UserProfile from "@/components/UserProfile";

export default {
  name: "UserBotsIndexView",
  components:{
    UserProfile,
    VAceEditor
  },
  setup(){
    ace.config.set(
        "basePath",
        "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")

    const store=useStore();
    let bots=ref([]);

    const botadd=reactive({
       title: "",
       description:"",
       content :"",
      error_message :"",
    });

    const refresh_bot=()=>{
      $.ajax({
        url:"http://localhost:9090/user/bot/getlist/",
        type:'GET',
        headers:{
          Authorization: "Bearer "+store.state.user.token,
        },
        success(resp){
          bots.value=resp;
        },
        error(resp){
          console.log(resp);
        }
      })
    }
    refresh_bot();

    const update_bot=(bot)=>{
      botadd.error_message="";
      $.ajax({
  url:"http://localhost:9090/user/bot/update/",
  type:'POST',
  data:{
    bot_id:bot.id,
    title:bot.title,
    description:bot.description,
    content:bot.content,
  },
  headers:{
    Authorization: "Bearer "+store.state.user.token,
  },
  success(resp){
    if(resp.error_message==="success"){
      Modal.getInstance('#updatebot-'+bot.id).hide();
      refresh_bot();
    }else {
     botadd.error_message=resp.error_message;
    }
  }
})
    }
    const remove_bot=(bot)=>{
  $.ajax({
  url:"http://localhost:9090/user/bot/remove/",
  type:'POST',
  data:{
  bot_id:bot.id,
  },
  headers:{
    Authorization: "Bearer "+store.state.user.token,
  },
  success(resp){
   if(resp.error_message==="success")
     refresh_bot();
  }
})
    }

    const add_bot=()=>{
       botadd.error_message="";
      $.ajax({
        url:"http://localhost:9090/user/bot/add/",
        type:'POST',
        data:{
          title:botadd.title,
          description:botadd.description,
          content:botadd.content,
        },
        headers:{
          Authorization: "Bearer "+store.state.user.token,
        },
        success(resp){
          if(resp.error_message==="success"){
            botadd.title="";
            botadd.content="";
            botadd.description="";
            Modal.getInstance("#add_bot_button").hide();
            refresh_bot();
          }else {
             botadd.error_message=resp.error_message;
          }
        },
        error(resp){
          console.log(resp);
        }
      })
    }
    return{
      bots,botadd,add_bot,remove_bot,update_bot
    }
  }
}
</script>

<style scoped>
div.error_message{
  color: red;
}
</style>