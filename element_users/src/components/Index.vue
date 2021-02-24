<template>
  <div>
    <el-carousel :interval="2000"
                 indicator-position="outside">
      <el-carousel-item v-for="item in imags"
                        :key="item">
        <el-image style="width: 1000px; height: 300px"
                  :src="item"
                  fit="contain">
        </el-image>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import homeImg1 from "../assets/indexImags/1.jpg";
import homeImg2 from "../assets/indexImags/2.jpg";
import homeImg3 from "../assets/indexImags/3.jpg";
import homeImg4 from "../assets/indexImags/4.jpg";
import homeImg5 from "../assets/indexImags/5.jpg";
export default {
  name: "Index",
  data () {
    return {
      imags: [homeImg1, homeImg2, homeImg3, homeImg4, homeImg5],
    };
  },
};
</script>

<style scoped>
</style>
