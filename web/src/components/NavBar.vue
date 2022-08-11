<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <router-link class="navbar-brand" :to="{name:'home'}">XiaoYu</router-link>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link :to="{name:'pk_index'}" :class="route_name==='pk_index'?'nav-link active':'nav-link'" aria-current="page">对战</router-link>
          </li>
          <li class="nav-item">
            <router-link :class="route_name==='ranklist_index'?'nav-link active':'nav-link'" :to="{name:'ranklist_index'}">对局列表</router-link>
          </li>
          <li class="nav-item">
            <router-link :class="route_name==='record_index'?'nav-link active':'nav-link'" :to="{name:'record_index'}">排行榜</router-link>
          </li>


          <li class="nav-item">
            <router-link :class="route_name==='SmallGame'?'nav-link active':'nav-link'" :to="{name:'SmallGame'}">For...</router-link>
          </li>
        </ul>
        <ul class="navbar-nav" v-if="$store.state.user.is_login">
          <li>
            <img :src="$store.state.user.photo" alt="" class="perimage ">
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              {{$store.state.user.username}}
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><router-link class="dropdown-item" :to="{name:'userbot_index'}">My(Bot&PerInfo)</router-link></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item" @click="logout" href="#">Log Out</a></li>
            </ul>
          </li>
        </ul>
        <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
          <li class="nav-item ">
            <router-link class="nav-link" :to="{name:'login'}"  role="button"  >
             登录
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link"  :to="{name:'register'}"  role="button">
              注册
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import {useRoute} from 'vue-router';
import {computed} from "vue";
import {useStore} from "vuex";
export default {
  name: "NavBar",
  setup(){
    const route=useRoute();
    const store=useStore();
    let route_name=computed(()=>route.name);


     const logout=()=>{
         store.dispatch("logout");
     }
     return{
       route_name,logout,
     }
  }
}
</script>

<style scoped>
.perimage{
  width: 40px;
  margin-top: 10px;
  border-radius:20px;
}
</style>