<template>
  <div>
    <el-table :data="
        tableData.filter(
          (data) =>
            !search || data.name.toLowerCase().includes(search.toLowerCase())
        )
      "
              :height="300"
              style="width: 100%">
      <el-table-column label="id"
                       width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="姓名"
                       width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover"
                      placement="top">
            <p>姓名: {{ scope.row.name }}</p>
            <p>住址: {{ scope.row.address }}</p>
            <div slot="reference"
                 class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="生日"
                       prop="bir"></el-table-column>
      <el-table-column label="性别"
                       prop="sex"></el-table-column>
      <el-table-column label="地址"
                       prop="address"></el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <el-input v-model="search"
                    size="mini"
                    placeholder="输入姓名关键字搜索" />
        </template>
        <template slot-scope="scope">
          <el-button size="mini"
                     @click="handleEdit(scope.$index, scope.row)">编辑</el-button>

          <el-popconfirm confirmButtonText="好的"
                         cancelButtonText="不用了"
                         icon="el-icon-info"
                         iconColor="red"
                         title="确定删除当前用户吗？"
                         @onConfirm="handleDelete(scope.$index, scope.row)">
            <el-button size="mini"
                       type="danger"
                       slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-button style="margin: 10px 0px"
               @click="SaveUserInfo"
               type="success"
               size="mini">添加</el-button>
    <transition name="el-zoom-in-center">
      <div v-show="show"
           class="transition-box">
        <el-form :hide-required-asterisk="false"
                 :rules="rules"
                 ref="userForm"
                 :model="form"
                 label-suffix=":"
                 label-width="80px">
          <el-form-item label="姓名"
                        prop="name">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="生日"
                        prop="bir">
            <el-date-picker type="date"
                            placeholder="选择日期"
                            v-model="form.bir"
                            style="width: 100%"></el-date-picker>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="form.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="详细地址"
                        prop="address">
            <el-input type="textarea"
                      v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"
                       @click="onSubmit('userForm')">保存用户信息</el-button>
            <el-button @click="show = !show">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: "List",
  data () {
    return {
      tableData: [],
      search: "",
      show: false,
      form: {
        id: "",
        name: "",
        bir: "",
        sex: "男",
        address: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入用户姓名...", trigger: "blur" },
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        bir: [
          { required: true, message: "请输入用户生日...", trigger: "blur" },
        ],
        address: [
          { required: true, message: "请输入用户地址...", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    SaveUserInfo () {
      this.show = true;
      this.form = { sex: "男" };
    },
    handleEdit (index, row) {
      console.log(index, row);
      this.show = true; //展示编辑表单
      this.form = row; //回显编辑信息
    },
    handleDelete (index, row) {
      console.log(index, row);
      //发送axios请求处理删除
      this.$http
        .get("http://localhost:8989/user/delete/" + row.id)
        .then((res) => {
          if (res.data.status) {
            this.$message({
              message: res.data.msg,
              type: "success",
            });
            //刷新数据
            this.findAllTableData();
          } else {
            this.$message.error(res.data.msg);
          }
        });
    },
    onSubmit (userForm) {
      this.$refs[userForm].validate((valid) => {
        if (valid) {
          console.log("submit!");
          this.$http
            .post("http://localhost:8989/user/saveOrUpdate", this.form)
            .then((res) => {
              console.log(res.data);
              //判断是否保存成功
              if (res.data.status) {
                this.$message({
                  message: "恭喜你，" + res.data.msg,
                  type: "success",
                });
                //清空表单信息
                this.form = { sex: "男" };
                //隐藏表单
                this.show = false;
                //刷新数据
                this.findAllTableData();
              } else {
                this.$message.error(res.data.msg);
              }
            });
        } else {
          this.$message.error("当前输入的数据不合法!!!");
          return false;
        }
      });
    },
    //刷新数据
    findAllTableData () {
      this.$http.get("http://localhost:8989/user/findAll").then((res) => {
        this.tableData = res.data;
      });
    },
  },
  created () {
    this.findAllTableData();
  },
};
</script>

<style scoped>
.transition-box {
  margin-bottom: 10px;
  width: 100%;
  height: 350px;
  border-radius: 4px;
  padding: 40px 20px;
  box-sizing: border-box;
  margin-right: 20px;
}
</style>
