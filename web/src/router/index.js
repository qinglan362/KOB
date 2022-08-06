import { createRouter, createWebHistory } from 'vue-router'
import NotFound from "@/views/error/NotFound";
import PkIndexView from "@/views/pk/PkIndexView";
import RankListIndexView from "@/views/ranklist/RankListIndexView";
import RecordIndexView from "@/views/record/RecordIndexView";
import UserBotsIndexView from "@/views/user/bot/UserBotsIndexView";
import LoginView from "@/views/user/acount/LoginView";
import RegisterView from "@/views/user/acount/RegisterView";
import SmallGame from "@/views/smallgame/SmallGame";
import store from "@/store";
const routes = [
  {
    path:"/",
    name:"home",
     redirect:"/pk/",
    meta:{
      requestAuth:true,
    }
  },
  {
    path:"/pk/",
    name:"pk_index",
    component:PkIndexView,
    meta:{
      requestAuth:true,
    }
  },
  {
    path:"/user/account/login/",
    name:"login",
    component:LoginView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:"/smallgame/",
    name:"SmallGame",
    component:SmallGame,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:"/user/account/register/",
    name:"register",
    component:RegisterView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:"/ranklist/",
    name:"ranklist_index",
    component:RankListIndexView,
    meta:{
      requestAuth:true,
    }
  },
  {
    path:"/404/",
    name:"404",
    component: NotFound,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:"/record/",
    name:"record_index",
    component:RecordIndexView,
    meta:{
      requestAuth:true,
    }
  },
  {
    path:"/userbot/",
    name:"userbot_index",
    component:UserBotsIndexView,
    meta:{
      requestAuth:true,
    }
  },
  {
    path:"/:catchAll(.*)",
    redirect:"/404/",
  },
]
const router = createRouter({
  history: createWebHistory(),
  routes
})

//前端页面授权
router.beforeEach((to, from, next)=>{
  if(to.meta.requestAuth &&!store.state.user.is_login)
    next({name:"login"});
  else
    next();
})

export default router
