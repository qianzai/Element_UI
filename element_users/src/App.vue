<template>
  <div id="app">
    <el-header>
      <!--导航菜单-->
      <el-menu :default-active="activeIndex"
               class="el-menu-demo"
               mode="horizontal"
               @select="handleSelect">
        <el-menu-item index="/index">Bzm主页</el-menu-item>
        <el-menu-item index="/users">用户管理</el-menu-item>
        <el-menu-item index="/msgs">消息中心</el-menu-item>
        <el-menu-item index="/orders">订单管理</el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <router-link to="/index"></router-link>
    </el-main>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      activeIndex: this.$route.path,  //激活索引为路由路径
    };
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath);
      this.$router.push(key)    //切换路由
    }
  }
}
</script>

<style>
</style>
