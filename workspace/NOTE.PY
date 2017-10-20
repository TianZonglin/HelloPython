print('hello world')
print("hello world")
print('hello','world')

ERROR:print 'hello'
ERROR:print "hello"

////Python基本数据类型
类型：Number包括整数+小数
整数：int
浮点数：float（不分精度）

----------简单运算--------------

判断数据类型 type(param)
类型合并原则：向精度更高的方向合并，结果为高精度
type(1+1.1111111)  为float类型
type(1*1.1111111) 为float类型
type(2/2) float
type(2//2) int 结果为整形

2/2 = 1.0
2//2 = 1

1//2 = 0 
1/2 = 0.5

符号 /  自动转换类型
符号 // 整除，向下取整

py2 has long but py3 dropped
ERROR：数字加字符串 不同于Java
----------进制---------------------

type(0xFFFFF) 均为int类型
但是，其默认转为进制计算，如下：
>>> 0xFFFFF
1048575

逗号运算符，自动拼为元组
>>> 0x0,'1'
(0, '1')
可以混合，可以识别元组