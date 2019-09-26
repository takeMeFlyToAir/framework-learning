###注意事项
>>1、变量赋值不能有空格，即=两边不能有空格；2、变量在字符串拼接中要加引号；3、变量以$符号开始，没有大括号
#单引号字符串
```
#!/bin/bash
name = 'zzr'
hello = 'hello, I am '$name''
echo $hello
```
输出结果: hello, I am zzr
#双引号字符串
```
#!/bin/bash
name = 'zzr'
hello = "hello, I am "$name""
echo $hello
```
输出结果: hello, I am zzr

#单双引号hu字符串
```
#!/bin/bash
name = 'zzr'
hello = "hello, I am '$name'"
echo $hello
```
输出结果: hello, I am 'zzr'

#单双引号hu字符串
```
#!/bin/bash
name = 'zzr'
hello = 'hello, I am "$name"'
echo $hello
```
输出结果: hello, I am "$name"

#获得字符串长度
```
#!/bin/bash
name="SnailClimb"
namelength=${#name}
echo ${namelength}
```
#截取字符串
```
str="SnailClimb is a great man"
echo ${str:0:10} #输出:SnailClimb
```
#shell数组
```
#!/bin/bash
array=(1 2 3 4 5);
# 获取数组长度
length=${#array[@]}
# 或者
length2=${#array[*]}
#输出数组长度
echo $length #输出：5
echo $length2 #输出：5
# 输出数组第三个元素
echo ${array[2]} #输出：3
unset array[1]# 删除下标为1的元素也就是删除第二个元素
for i in ${array[@]};do echo $i ;done # 遍历数组，输出： 1 3 4 5 
unset arr_number; # 删除数组中的所有元素
for i in ${array[@]};do echo $i ;done # 遍历数组，数组元素为空，没有任何输出内容
```