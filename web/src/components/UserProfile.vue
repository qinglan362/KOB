<template>
  <div class="row">
    <div class="col-4">
      <div class="card" style="margin-top: 20px">
        <div class="card-body">
          <img :src="$store.state.user.photo" style="width: 100%" alt="">
        </div>
      </div>
    </div>
    <div class="col-8">
      <div class="card" style="margin-top: 20px">
        <div class="card-body">

          <div class="card">
            <div class="card-body">
              <div>昵称：{{$store.state.user.username}}</div>
            </div>
          </div>
          <div class="card" style="margin-top: 10px">
            <div class="card-body">
              <div style="margin-top: 5px">年龄：{{$store.state.user.age}}</div>
            </div>
          </div>
          <div class="card">
            <div class="card-body">
              <div>个性签名：{{$store.state.user.personalsignature}}</div>
            </div>
          </div>
          <div class="card" style="margin: 10px 0 10px 0">
            <div class="card-body">
              <div style="margin-top: 5px">爱好：{{$store.state.user.hobby}}</div>
            </div>
          </div>
          <button  style="margin-right: 15px" type="button" class="btn btn-primary float-end"  data-bs-toggle="modal" data-bs-target="#changepassword">
            修改密码
          </button>
          <button  style="margin-right: 70px " type="button" class="btn btn-primary float-end"  data-bs-toggle="modal" data-bs-target="#updateperinfo">
            修改个人信息
          </button>
        </div>



        <div class="modal fade" id="changepassword" tabindex="-1" >
          <div class="modal-dialog modal-xl" >
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">修改密码.</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="mb-3">
                  <label for="oldpassword" class="form-label">旧密码</label>
                  <input v-model="oldPassword" type="text" class="form-control" id="oldpassword" placeholder="旧密码">
                </div>
                <div class="mb-3">
                  <label for="newpassword" class="form-label">新密码</label>
                  <input v-model="newPassword" type="text" class="form-control" id="newpassword" placeholder="新密码">
                </div>
                <div class="mb-3">
                  <div v-if="cperror_message!==''" style="text-align: center;color: red" class="error_message">{{cperror_message}}</div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary"  @click="changepassword">提交</button>
                <button type="button" class="btn btn-secondary" style="margin-left: 20px" data-bs-dismiss="modal">取消</button>
              </div>
            </div>
          </div>
        </div>


        <!-- Modal -->
        <div class="modal fade" id="updateperinfo" tabindex="-1" >
          <div class="modal-dialog modal-xl" >
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">修改个人信息.</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">

                <div class="mb-3">
                  <label for="username" class="form-label">昵称</label>
                  <input v-model="username" type="text" class="form-control" id="username" placeholder="昵称">
                </div>

                <div class="mb-3">
                  <label for="bottitle" class="form-label">年龄</label>
                  <input v-model="age" type="text" class="form-control" id="bottitle" placeholder="年龄">
                </div>

                <div class="mb-3">
                  <label for="gexingqianming" class="form-label">个性签名</label>
                  <input v-model="personalsignature" type="text" class="form-control" id="gexingqianming" placeholder="个性签名">
                </div>

                <div class="mb-3">
                  <label for="aihao" class="form-label">爱好</label>
                  <input v-model="hobby" type="text" class="form-control" id="aihao" placeholder="爱好">
                </div>

                <div class="mb-3">
                  <div v-if="error_message!==''" style="text-align: center;color: red" class="error_message">{{error_message}}</div>
                </div>

              </div>

              <div class="modal-footer">
                <button type="button" class="btn btn-primary"  @click="updateperinfo">提交</button>
                <button type="button" class="btn btn-secondary" style="margin-left: 20px" data-bs-dismiss="modal">取消</button>
              </div>

            </div>
          </div>
        </div>


      </div>
    </div>
  </div>
</template>

<script>
import {useStore} from "vuex";
import {ref} from "vue";
import {Modal} from 'bootstrap/dist/js/bootstrap';
export default {
  name: "UserProfile",
  setup(){
    const store=useStore();
    let oldPassword=ref('');
    let newPassword=ref('');
    let cperror_message=ref('');
    let age=ref('');
    let username=ref('');
    let hobby=ref('');
    let personalsignature=ref('');
    let error_message=ref('');
    username.value=store.state.user.username;
    age.value=store.state.user.age;
    hobby.value=store.state.user.hobby;
    personalsignature.value=store.state.user.personalsignature;

    const changepassword=()=>{
       cperror_message.value='';
       store.dispatch("changepassword",{
            oldPassword:oldPassword.value,
            newPassword:newPassword.value,
         success(resp){
              if(resp.error_message==="success"){
                Modal.getInstance("#changepassword").hide();
              }else{

                cperror_message.value=resp.error_message;
              }
         },
         error(resp){
            cperror_message.value=resp.error_message;
         }
       })
    }
    const updateperinfo=()=>{
        error_message.value='';
        store.dispatch("updateinfo",{
            age:age.value,
            personalsignature:personalsignature.value,
            hobby:hobby.value,
            username:username.value,
          success(resp){
              if (resp.error_message==="success")
               Modal.getInstance('#updateperinfo').hide();
              // eslint-disable-next-line no-empty
              else {

              }
          },
          error(resp){
            error_message.value= resp;
          }
        })
    }
    return{
      updateperinfo,age,personalsignature,error_message,hobby,username,oldPassword,newPassword,changepassword,cperror_message,
    }
  }
}
</script>

<style scoped>

</style>