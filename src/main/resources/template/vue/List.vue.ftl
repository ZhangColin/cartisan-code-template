<template>
  <div class="app-container">
    <el-form :inline="true" @keyup.enter.native="handleSearch">
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      :data="dataSource"
      row-key="id"
      class="table-container"
      element-loading-text="加载中..."
      stripe
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="${primaryKey.title}" prop="${primaryKey.camelName}" /><#list fields as field><#if field.isBoolean>
      <el-table-column align="center" label="${field.title}" prop="${field.camelName}">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.${field.camelName}"
            :active-value="true"
            :inactive-value="false"
            disabled
          />
        </template>
      </el-table-column><#elseif field.isEnum>
      <el-table-column align="center" label="${field.title}" prop="${field.camelName}" >
        <template slot-scope="scope">
          <span>{{ ({<#list field.enumValues as enumValue>${enumValue.code}:'${enumValue.description}'<#if enumValue_has_next>,</#if></#list>})[scope.row.${field.camelName}] }}</span>
        </template>
      </el-table-column><#else>
      <el-table-column align="center" label="${field.title}" prop="${field.camelName}" /></#if></#list>
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit( scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleDelete(scope.row)">删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page.sync="page.currentPage"
      :page-sizes="[5, 10, 20, 50, 100]"
      :page-size="page.pageSize"
      :total="page.total"
      class="pagination-container"
      background
      align="right"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <${camelModule}-form ref="${camelModule}Form" @addSuccess="fetchData" @editSuccess="fetchData" />
  </div>
</template>

<script>
  import { PaginationMixin } from '@/mixins/pagination-mixin'
  import { DeleteMixin } from '@/mixins/delete-mixin'

  import ${pascalModule}Form from './${pascalModule}Form'

  export default {
    name: '${pascalModule}',
    components: { ${pascalModule}Form },
    mixins: [PaginationMixin, DeleteMixin],
    data() {
      return {
        apiBaseUrl: '/${camelModules}'
      }
    },
    created() {
    },
    methods: {
      handleAdd() {
        this.$refs.${camelModule}Form.add()
      },
      handleEdit(row) {
        this.$refs.${camelModule}Form.edit(row)
      }
    }
  }
</script>
