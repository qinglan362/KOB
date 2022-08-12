import $ from 'jquery'
import store from "@/store/index";
import router from "@/router";
//import {Modal} from "bootstrap";

export default {
    state: {
        id: "",
        username: "",
        photo: "",
        token: "",
        is_login: false,
        pulling_info:true,
        personalsignature:"",
        age:"",
        hobby:"",
    },
    getters: {
    },
    mutations: {
        updateUser(state, user) {
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
            state.personalsignature=user.personalsignature;
            state.age=user.age;
            state.hobby=user.hobby;
        },
        updateToken(state, token) {
            state.token = token;
        },
        logout(state) {
            state.id = "";
            state.username = "";
            state.photo = "";
            state.token = "";
            state.is_login = false;
            state.personalsignature="";
            state.age="";
            state.hobby="";
        },
        updatePullingInfo(state,pulling_info){
           state.pulling_info=pulling_info;
        },
    },
    actions: {
         changepassword(context,data){
                $.ajax({
                    url:'http://localhost:9090/user/account/changePassword/',
                    type:'post',
                    data:{
                        oldPassword:data.oldPassword,
                        newPassword:data.newPassword,
                    },
                    headers:{
                        Authorization: "Bearer "+context.state.token,
                    },
                    success(resp){
                        if(resp.error_message==="success") {
                            router.push({name:'home'});
                            store.commit("logout");
                        }
                        else {
                            data.error(resp);
                        }
                       console.log(resp.error_message);
                    },
                    error:function(resp){
                       data.error(resp);
                    }
                })
        },
        updateinfo(context, data){
            $.ajax({
                url:"http://localhost:9090/user/account/updateinfo/",
                type:'POST',
                data:{
                    personalsignature:data.personalsignature,
                    age:data.age,
                    hobby:data.hobby,
                    username:data.username,
                },
                headers:{
                    Authorization: "Bearer "+context.state.token,
                },
                success(resp){
                    if (resp.error_message === "success") {
                        store.dispatch("getinfo",{
                            success(){
                              data.success(resp);
                            },
                            error(){
                               data.error(resp);
                            },
                        })
                    } else {
                        data.error(resp.error_message);
                        console.log(resp.error_message);
                    }
                },
                error(resp){
                  data.error(resp);
                }
            })
        },
        login(context, data) {
            $.ajax({
                url: "http://localhost:9090/user/account/token/",
                type: "post",
                data: {
                    username: data.username,
                    password: data.password,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        localStorage.setItem("jwt_token",resp.token);
                        context.commit("updateToken", resp.token);
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    console.log(data.username);
                    data.error(resp);
                }
            });
        },
        getinfo(context, data) {
            $.ajax({
                url: "http://localhost:9090/user/account/info/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateUser", {
                            ...resp,
                            is_login: true,
                        });
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                }
            })
        },
        logout(context) {
            localStorage.removeItem("jwt_token");
            context.commit("logout");
        }
    },
    modules: {
    }
}
