@ rem ----- 信息 -----
@ rem @filename x.bat
@ rem @version 1.0
@ rem @description 
@ rem @author qye.zheng

@ rem @warning 为了防止中文环境乱码，保存文件的时候，应该保存为ANSI编码格式.
@ rem ################################################################################


@ rem 标题
@ title 初始化 RabbitMQ 主目录
@ rem ########## begin  ##########

@ rem 关闭显示命令，使所有命令执行前不显示
@ rem @ echo off
@ echo off
@ rem 打开命令显示 @ echo on

@ rem ----- 变量声明区
:: 设置 RabbitMQ 主目录
set RabbitMQ_HOME=D:\"software\RabbitMQServer\rabbitmq_server-3.7.4"


:: 截取所在的磁盘驱动
set diskDriver=%RabbitMQ_HOME:~0,2%


@ rem ----- 程序设计区

:: 进入 home 路径所在的磁盘驱动器
%diskDriver%

@ rem pause

@ rem

@ rem
@ rem 输出提示信息

::
:: 1) 
:: 2)
:: 3)
:: 4)
:: 5)
:: 6)
:: 7)
:: 8)
:: 9)
:: 10)

:: 在程序的末尾，可以根据执行的结果(成功或失败) 给出提示信息，成功可以直接执行exit，而失败
:: 可以执行pause，然后可以通过控制台输出信息来调试、定位问题.
:: 可以在程序中设置一个成功或失败的标志-布尔值，来决定最后程序的执行流程.

@ rem echo
@ rem exit
@ rem ########## end of ##########

@ rem 注释说明: @ rem 注释内容  或者 :: 注释内容
@ rem rem 或两个冒号 后面 都可以写注释
