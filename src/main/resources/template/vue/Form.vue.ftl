<template>
  <el-drawer
    :title="drawerTitle"
    :visible.sync="drawerVisible"
    :wrapper-closable="false"
    size="50%"
  >
    <div class="drawer__content">
      <el-form ref="entityDataForm" :model="entityData" :rules="rules" label-width="120px"><#list fields as field>
        <el-form-item label="${field.title}" prop="${field.camelName}"><#if field.isBoolean>
          <el-switch
            v-model="entityData.${field.camelName}"
            :active-value="true"
            :inactive-value="false"
          /><#elseif field.isEnum>
          <el-select v-model="entityData.${field.camelName}" placeholder="请选择${field.title}"><#list field.enumValues as enumValue>
            <el-option label="${enumValue.description}" :value="${enumValue.code}"></el-option></#list>
          </el-select><#elseif field.isNumber>
          <el-input-number v-model="entityData.${field.camelName}" /><#elseif field.isDate>
          <el-date-picker type="date" placeholder="选择日期" v-model="entityData.${field.camelName}" style="width: 100%;"></el-date-picker><#elseif field.isDateTime>
          <el-date-picker type="datetime" placeholder="选择日期时间" v-model="entityData.${field.camelName}" style="width: 100%;"></el-date-picker><#else>
          <el-input v-model="entityData.${field.camelName}" /></#if>
        </el-form-item></#list>
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
        <#list fields as field><#if field.needValid>
        ${field.camelName}: [<#if field.require>
          { required: true, message: '${field.title}不能为空', trigger: '<#if field.isEnum || field.isDate || field.isDateTime || field.isBoolean>change<#else>blur</#if>' },</#if><#if field.maxValid>
          { max: ${field.maxLength}, message: '${field.title}最大长度不能超过${field.maxLength}', trigger: '<#if field.isEnum || field.isDate || field.isDateTime || field.isBoolean>change<#else>blur</#if>' },</#if>
        ],
        </#if></#list>
      }
    }
  }
}
</script>
