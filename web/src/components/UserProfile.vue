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
            创建Bot
          </button>
          <!-- Modal -->
          <div class="modal fade" id="updateperinfo" tabindex="-1" >
            <div class="modal-dialog modal-xl" >
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">创建Bot</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <div class="mb-3">
                    <label for="bottitle" class="form-label">年龄</label>
                    <input v-model="$store.state.user.age" type="text" class="form-control" id="bottitle" placeholder="请输入bot名称">
                  </div>
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
export default {
  name: "UserProfile",
  setup(){
    // eslint-disable-next-line no-unused-vars
    const store=useStore();
  }
}
</script>

<style scoped>

</style>