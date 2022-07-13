import { createRouter, createWebHistory } from 'vue-router'
import NotFound from "@/views/error/NotFound";
import PkIndexView from "@/views/pk/PkIndexView";
import RankListIndexView from "@/views/ranklist/RankListIndexView";
import RecordIndexView from "@/views/record/RecordIndexView";
import UserBotsIndexView from "@/views/user/bot/UserBotsIndexView";
const routes = [
  {
    path:"/",
    name:"home",
    redirect:"/pk/",
  },
  {
    path:"/pk/",
    name:"pk_index",
    component:PkIndexView,
  },
  {
    path:"/ranklist/",
    name:"ranklist_index",
    component:RankListIndexView,
  },
  {
    path:"/404/",
    name:"404",
    component: NotFound,
  },
  {
    path:"/record/",
    name:"record_index",
    component:RecordIndexView,
  },
  {
    path:"/userbot/",
    name:"userbot_index",
    component:UserBotsIndexView,
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
export default router