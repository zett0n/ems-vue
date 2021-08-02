# ems-vue

基于 Vue, SpringBoot, Mybatis, Redis 的入门前后端分离员工管理系统

作为练手项目，项目仅包含两张表，注册、登录、增删改模块，引入 Redis 作为缓存提高查询性能

项目不足之处：
- 前端的页面都是 HTML 页面写 Vue，没有工程化且代码重复处很多
- 注册模块验证码校验不支持并发（用的是 ServletContext 储存）
- 登录没有使用令牌
- 员工头像文件上传做的比较简单，后台对文件过大没有处理，存储路径也设为了绝对路径
- 虽然是前后端分离，但并没有跨域，前后端部署在同一服务器下
- 业务很简单，全是单表查询

但是练手足够了
