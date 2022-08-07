<template>
  <div class="row">
    <div class="col-6">
      <div class="card" style="margin-top: 20px">
        <div class="card-body">
          <img :src="$store.state.user.photo" style="width: 100%">
        </div>
      </div>
    </div>
    <div class="col-6">
      <div class="card" style="margin-top: 20px">
        <div class="card-body">
          <div>个性签名:{{$store.state.user.personalsignature}}</div>
          <div style="margin-top: 5px">年龄:{{$store.state.user.age}}</div>
          <div style="margin-top: 5px">爱好:{{$store.state.user.hobby}}</div>
          <button type="button" class="btn btn-primary float-end"  data-bs-toggle="modal" data-bs-target="#updateperinfo">
            修改个人信息
          </button>
          <!-- Modal -->
          <div class="modal fade" id="updateperinfo" tabindex="-1" >
            <div class="modal-dialog modal-xl" >
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">修改个人信息</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <div class="mb-3">
                    <label for="bottitle" class="form-label">年龄</label>
                    <input v-model="age1" type="text" class="form-control" id="bottitle" placeholder="年龄">
                  </div>

                  <div class="mb-3">
                    <label for="gexingqianming" class="form-label">个性签名</label>
                    <input v-model="personalsignature1" type="text" class="form-control" id="gexingqianming" placeholder="个性签名">
                  </div>

                  <div class="mb-3">
                    <label for="aihao" class="form-label">爱好</label>
                    <input v-model="hobby1" type="text" class="form-control" id="aihao" placeholder="爱好">
                  </div>

                </div>

                <div class="modal-footer">
                  <div  class="error_message">{{error_message }}</div>
                  <button type="button" class="btn btn-primary"  @click="updateperinfo">提交</button>
                  <button type="button" class="btn btn-secondary" style="margin-left: 20px" data-bs-dismiss="modal">取消</button>
                </div>

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
import $ from 'jquery';
import {Modal} from 'bootstrap/dist/js/bootstrap';
export default {
  name: "UserProfile",
  setup(){
    const store=useStore();
    let age1=ref('');
    let hobby1=ref('');
    let personalsignature1=ref('');
    let error_message=ref('');
    const updateperinfo=(user)=>{
      console.log(personalsignature1);
       $.ajax({
         url:"http://localhost:9090/user/account/updateinfo/",
         type:'POST',
         data:{
           personalsignature:personalsignature1.value,
           age:age1.value,
           hobby:hobby1.value,
         },
         headers:{
           Authorization: "Bearer "+store.state.user.token,
         },
         success(resp){
           if(resp.error_message==="success"){
             Modal.getInstance('#updateperinfo').hide();
           }else {
             user.error_message=resp.error_message;
           }
         }
       })
    }
    return{
      updateperinfo,age1,personalsignature1,error_message,hobby1
    }
  }
}
</script>

<style scoped>

</style>