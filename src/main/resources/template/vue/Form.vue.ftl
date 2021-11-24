<template>
  <el-drawer
    :title="drawerTitle"
    :visible.sync="drawerVisible"
    :wrapper-closable="false"
    size="50%"
  >
    <div class="drawer__content">
      <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px"><#list fields as field>
        <el-form-item label="${field.description}" prop="${field.camelName}">
          <el-input v-model="entityData.${field.camelName}" />
        </el-form-item>
        </#list>
      </el-form>
      <div class="drawer__footer">
        <el-button @click="handleCancel()">取消</el-button>
        <el-button type="primary" @click="handleConfirm()">确定</el-button>
      </div>
    </div>
  </el-drawer>
</template>

<script>

import { FormMixin } from '@/mixins/form-mixin'

export default {
  name: '${pascalModule}Form',
  mixins: [FormMixin],
  data() {
    return {
      apiBaseUrl: '/${camelModules}',
      title: '${moduleName}',

      defaultData: {
        <#list fields as field>
        ${field.camelName}: <#if field.isNumber || field.isBoolean>${field.defaultValue}<#else>'${field.defaultValue}'</#if><#if field_has_next>,</#if>
        </#list>
      },
      rules: {
        <#list fields as field>
        <#if field.require>${field.camelName}: [{ required: true, message: '${field.description}不能为空', trigger: 'blur' }],</#if>
        </#list>
      }
    }
  }
}
</script>
