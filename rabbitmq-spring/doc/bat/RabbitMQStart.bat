@ rem ----- 信息 -----
@ rem @filename RabbitMQStart.bat
@ rem @version 1.0
@ rem @author qye.zheng
@ rem @description 启动 RabbitMQ

@ rem @warning 为了防止中文环境乱码，保存文件的时候，应该保存为ANSI编码格式.
@ rem ################################################################################


@ rem 标题
@ title RabbitMQ 启动器
@ rem ########## begin  ##########

:: 调用 初始化主目录
call RabbitMQHome.bat

@ rem 关闭显示命令，使所有命令执行前不显示
@ rem @ echo off
@ echo off
@ rem 打开命令显示 @ echo on

@ rem ----- 变量声明区
:: 设置暂停标识
set stopFlag=false

@ rem ----- 程序设计区

:: 成对括号可以把多行要执行的语句包围起来，else 必须和 if 分支的右括号在同一行，否则 else 将被视为新同语句
if exist %RabbitMQ_HOME% (
cd %RabbitMQ_HOME%
:: 进入 sbin 目录
cd sbin
:: RabbitMQ 启动命令
::rabbitmq-server start
::rabbitmqctl start_app
rabbitmq-server –detached
) else (
echo %RabbitMQ_HOME% not exists, please check!
:: 设置暂停标识
set stopFlag=true
)
pause
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
