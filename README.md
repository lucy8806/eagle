# eagle
基于Vue、SpringBoot、Shiro、JWT等框架开发的前后端分离的基础框架平台，提供基础服务，拿来即用，为企业的业务需求开发保驾护航。

# 技术栈
## 后端
* Spring Boot 2.1.3.RELEASE
* Shiro 1.4.0
* Mybatis 3.4.5 + 插件TK.MyBatis
* JWT 3.4.1
* Druid 0.2.26
* Jedis 2.9.1
* Swagger 2.8.0
* MySQL5.7,Redis
## 前端
* [Vue 2.5.17](https://cn.vuejs.org/),[Vuex](https://vuex.vuejs.org/zh/),[Vue Router](https://router.vuejs.org/zh/)
* [ant-design-vue](https://vue.ant.design/docs/vue/introduce-cn/)
* [webpack](https://www.webpackjs.com/),[yarn](https://yarnpkg.com/zh-Hans/)

# 模块划分

| 模块         | 说明                      |    
| ---------- | ----------------------- |
| eagle-admin  | 系统层面的基础模块：用户管理、角色管理、资源管理、部门管理、数据字典等等。 |      
| eagle-core  | 框架核心模块，提供工具类,通用Mappper, 通用Service,统一异常处理，分页逻辑封装等等。|    
| eagle-sso | 单点登录-认证中心模块，未完成，待续...... |
| eagle-generator | 未实现，待续...... |
