<template>
  <content-filed>
    <div class="card y">
      <div class="card-body" style="text-align: center">
        <a style="color: hotpink" href="https://www.painterengine.com/main/instances/instance2022052203/index.html">烟花</a>
      </div>
    </div>
    <div class="card x">
      <div class="card-body" style="text-align: center">
        <a style="color: beige" href="http://www.qijishow.com/Brand/Brand23/index.htm">Silk</a>
      </div>
    </div>
  </content-filed>
</template>

<script>
import contentFiled from "@/components/ContentFiled";
export default {
  name: "SmallGame",
  components:{contentFiled}
}
</script>

<style scoped>
.x{
  font-size: 20px;
  margin-top: 20px;
  background: lightblue;
}
.y{
  font-size: 20px;
  background: antiquewhite;
}
</style>