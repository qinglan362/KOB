<template>
  <ContentFiled>
    <div class="row justify-content-md-center">
      <div class="col-3">
        <form @submit.prevent="register">
          <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input  v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
          <div class="mb-3">
            <label for="confirmpassword" class="form-label">ConfirmPassword</label>
            <input  v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="再次请输入密码">
          </div>
          <div class="error_message">{{error_message}}</div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>
  </ContentFiled>
</template>

<script>
import ContentFiled from "@/components/ContentFiled";
import {ref} from "vue";
import router from "@/router";
import $ from "jquery";
export default {
  name: "RegisterView",
  components: {ContentFiled},
  setup(){
  let username=ref('');
    let password=ref('');
    let confirmedPassword=ref('');
    let error_message=ref('');

    const register=()=>{
        $.ajax({
          url: "http://localhost:9090/user/account/register/",
          type: "post",
          data:{
            username:username.value,
            password:password.value,
            confirmedPassword:confirmedPassword.value,
          },
          success(resp){
            if(resp.error_message==="success")
              router.push({name:"login"});
            else
              error_message.value=resp.error_message;
          },
      });
    }
    return{
      username,password,confirmedPassword,error_message,register,
    }
  }
}
</script>

<style scoped>
button{
  width: 100%;
}
div.error_message{
  color: red;
}
</style>