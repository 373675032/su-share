## 素材分享网
这是一个多用户的资源共享平台，是一个专为文件共享而设计的新概念网盘系统。这是一个综合性非常强、灵活度非常高的素材网站，注册成为用户之后可以上传自己所喜欢的素材供他人浏览和高速下载。

同时设立网站管理员来审核用户上传的素材以及创建不同的素材分类来方便用户去上传、搜索，以此来为素材来设立分区，比如：壁纸区、PPT模版、简历模版、前端模版等。

![视频介绍](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626091881409-image.png)

> 演示网址：http://xuewei.world/su-share/


## 功能介绍
未登录系统那么便默认为游客角色，涉及到以下的权限和功能：

### 游客角色
1. 注册、登录系统
2. 正常的浏览网站主页、素材详情页
3. 访问他人空间主页
4. 分类查看素材
5. 搜索素材


<![用户登陆](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092130861-image.png),![用户注册](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092159306-image.png),![网站主页](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092256018-0C4D00CA-36FA-4E8D-9A41-49BB32D2C3FC.png),![他人主页](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092339534-74B17E8B-E97D-4506-B017-D50F5F5E3D8A.png),![搜索素材](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092391040-image.png)>

### 普通用户
已经登录系统那么便成为了系统的用户，享有以下的权限和功能：

1. 下载素材、收藏素材
2. 评论素材并为素材评星
3. 上传自己的素材，填写名称、简介，上传封面与源文件并选择素材分区
4. 管理自己的素材、编辑或移入回收站
5. 收藏管理、回收站管理
6. 编辑资料、修改登录密码等
7. 消息通知管理
8. 查看审核进度
9. 包含游客所具备的全部功能

<![普通用户菜单](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626093328548-9D755951-B549-44D1-BAFF-DF8638CC6AB8.png),![下载和收藏](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092525882-DEE54158-B1ED-46D2-890F-4A3C5238A20A.png),![评论和评星](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092575038-image.png),![上传素材](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092643724-2F0B3016-E262-48C9-9B05-40F93859381C.png),![管理素材](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092703486-DA1F4227-1654-4376-BE28-E3021A9DA6C8.png),![消息管理](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092860310-image.png),![审核队列](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626092994777-image.png)>

### 管理员角色
1. 审核、预览、下载普通用户上传的素材文件
2. 编辑网站公告
3. 管理素材的分类分区
4. 管理站点的菜单以及友情链接
5. 用户管理：修改普通用户的资料、密码以及角色
6. 包含普通用户所具备的全部功能

<![管理员菜单](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626093242814-image.png),![管理员审核](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626093060497-image.png),![预览素材](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626093096802-image.png),![站点设置](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626093132865-image.png),![用户管理](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626093175074-image.png)>

## 技术介绍
### 前端技术
- HTML、CSS、JavaScript、Jquery
- Catalog模版、BootStrap框架
- ThymeLeaf模版引擎
- 其他Jquery插件

### 后端技术
- SpringBoot、MyBatis框架
- MySQL数据库、EhCache缓存
- 码云API
- 阿里云OSS对象存储
- Lombok、图片水印工具类


## 修改配置
1. 码云图床类：`com.share.config.GiteeImgBedConfig`
2. 阿里云OSS配置类：`com.share.utils.AliyunOssConfigConstant`
3. 项目核心配置文件：`application.yml`


### 码云图床类

需填写`ACCESS_TOKEN`、`OWNER`、`REPO_NAME`

只需在Gitee上创建公开项目，之后找到设置，然后私人令牌，按照提示创建之后便获取到了`ACCESS_TOKEN`，`OWNER`就是你码云的用户名，`REPO_NAME`为仓库名称。

<![](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626094058672-image.png),![](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626094135194-image.png),![](https://gitee.com/cn_moti/mdnice/raw/master/2021-7-12/1626094228562-image.png)>

### 阿里云OSS配置类
需要在阿里云开通相关服务，然后创建bucket，之后获取到AccessKey_ID和AccessKey_Secret，具体怎么做可以自行百度。

### 项目核心配置文件
需要使用mysql创建数据库，运行sql脚本之后，修改数据源信息。

## 最后
附上项目的Github地址，之前莫提开源了其他比较有意思的项目哦。点个Star🌟，加个关注是对我最大的支持！

> 素材分享网：https://github.com/373675032/su-share

> 莫提网盘：https://github.com/373675032/moti-cloud

> 莫提博客：https://github.com/373675032/moti-blog

> 技术社区：https://github.com/373675032/Molihub