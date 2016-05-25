一、服务器：
1. 开发/测试环境 120.26.218.0
    war 包路径：/data/douins/dev/agency/
    日志：/data/douins/dev/agency/logs

    
二、 SVN
    服务器地址：121.41.43.237
    路径：/alidata/server/svn/repostories/
    
三、项目部署说明
    1. 开发环境
        在 api 下运行 run.sh 脚本 或在终端中打开api的pom文件路径，执行 mvn clean package antrun:run -DskipTests $* 
     2. 线上环境
        在agency-api项目上右键-->Run As --> Maven Build; 在弹出的对话框的 goal 栏填写 clean package, profiles 栏填写
online；确定，开始打包。然后将打好的war包上传到线上服务器，重启tomcat，部署完成。 
