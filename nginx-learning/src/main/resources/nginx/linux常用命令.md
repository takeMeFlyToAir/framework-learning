
## linux端口
1. centos7查看端口是否被使用: 
    > ss -luntp
2. centos6查看端口是否被使用：
    > netstat -luntp


## 安装基础的包
```
yum -y install gcc gcc-c++ autoconf pcre pcre-devel make automake
yum -y install wget httpd-tools vim
```

## iptables
1. 查看
    > iptables -L；iptables -t nat -L
2. 关闭
    > iptables -F；iptables -t nat -F
    
## 压测工具ab
```
ab -n 50 -c 20 http://127.0.0.1/server.html
```

## awk
```
cat /etc/passwd | awk -F ':' '{print $1- $7}'
cat /etc/passwd | awk -F ':' 'BEGIN {print "name,shell"}{print $1 "," $7} END {print "blue,/bin/nosh"}'
awk  -F ':'  '{print "filename:" FILENAME ",linenumber:" NR ",columns:" NF ",linecontent:"$0}' /etc/passwd
awk  -F ':'  '{printf("filename:%10s,linenumber:%s,columns:%s,linecontent:%s\n",FILENAME,NR,NF,$0)}' /etc/passwd
awk '{count++;print $0;} END{print "user count is ", count}' /etc/passwd
ls -l |awk 'BEGIN {size=0;} {size=size+$5;} END{print "[end]size is ", size}'
ls -l |awk 'BEGIN {size=0;} {size=size+$5;} END{print "[end]size is ", size/1024/1024,"M"}'
```
1. awk命令写到文件中，然后awk直接执行文件
```
##文件内容
{ if ($3 > 0) print $1,$2*$3}
##命令
awk -f awkCommand.sh
```
echo '19-07-15.14:33:10.756 [JSF-BZ-22000-15-T-16] DEBUG RecommenderServiceImp   - [20190715143310627974] RecommenderImp.recommend(100091) costs 129 ms' |  awk -F ' ' '{print $1}'
tail -f server1/logs/history.log |grep 'RecommenderImp.recommend(100091)' |  awk -F ' ' '{print $1}'
tail -f server1/logs/history.log |grep 'RecommenderImp.recommend(100091)' |  awk -F ' ' '{{ if ($3 > 0) print $1,$2*$3}}'
cat  /export/Instances/recommender-system/server1/logs/history.log |grep "RecommenderServiceImp.recommend(100079)" |  awk -F ' ' '{{ if ($9 > 200) print $6,$7,$8,$9,$10}}' > result.txt
cat /export/Instances/recommender-system/server1/logs/history.log | grep "20190830154056286369" > info.txt
cat /export/Instances/recommender-system/server1/logs/history.log | grep "19-08-30.15:40:56" > info.txt


cat /export/Instances/http-jsf/server1/logs/history.log | grep 'StockValidServiceImpl.skusSize oldSku' > result.txt
cat result.txt | awk -F ' ' '{{ if ($11 < 100) print $6,$7,$8,$9,$10,$11}}' > time.txt
cat /export/Instances/http-jsf/server1/logs/history.log | grep '20190911103149920308'
cat /export/Instances/http-jsf/server1/logs/history.log | grep '20190911115116389198'
cat /export/Instances/http-jsf/server1/logs/history.log | grep '20190911115118055268'
cat /export/Instances/http-jsf/server1/logs/history.log | grep '20190911115118795733'
cat /export/Instances/http-jsf/server1/logs/history.log | grep '20190911115122849242'
cat /export/Instances/http-jsf/server1/logs/history.log | grep '20190911115210403886'
cat /export/Instances/http-jsf/server1/logs/history.log | grep '20190911115517770179'
cat /export/Instances/http-jsf/server1/logs/history.log | grep '20190911125110864827'