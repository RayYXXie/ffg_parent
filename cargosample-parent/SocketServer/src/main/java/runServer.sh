#!/bin/sh

while true ;
do
   result1=`curl -w %{http_code}"\n" http://192.168.4.37:8080/web1/index.jsp|grep 200`
   
   if [ ! -z $result1 ] && [ $result1 -eq 200 ];
   then
	break;
   fi
done

while true ;
do
   result2=`curl -w %{http_code}"\n" http://192.168.4.37:8080/web2/index.jsp|grep 200`
   if [ ! -z $result2 ] && [ $result2 -eq 200 ];
   then
	break;
   fi
done

while true ;
do
   result3=`curl -w %{http_code}"\n" http://192.168.4.37:8080/web3/index.jsp|grep 200`
   if [ ! -z $result3 ] && [ $result3 -eq 200 ];
   then
	 break;
   fi
done

while true ;
do
   result4=`curl -w %{http_code}"\n" http://192.168.4.37:18080/web1/index.jsp|grep 200`
   if [ ! -z $result4 ] && [ $result4 -eq 200 ];
   then
	 break;
   fi
done

while true ;
do
   result5=`curl -w %{http_code}"\n" http://192.168.4.37:18080/web2/index.jsp|grep 200`
   if [ ! -z $result5 ] && [ $result5 -eq 200 ];
   then
	 break;
   fi
done

while true ;
do
   result6=`curl -w %{http_code}"\n" http://192.168.4.37:18080/web4/index.jsp|grep 200`
   if [ ! -z $result6 ] && [ $result6 -eq 200 ];
   then
	 break;
   fi
done

while true ;
do
   result8=`curl -w %{http_code}"\n" http://192.168.4.37:28080/web1/index.jsp|grep 200`
   if [ ! -z $result8 ] && [ $result8 -eq 200 ];
   then
	 break;
   fi
done

while true ;
do
   result9=`curl -w %{http_code}"\n" http://192.168.4.37:28080/web5/index.jsp|grep 200`
   if [ ! -z $result9 ] && [ $result9 -eq 200 ];
   then
	 break;
   fi
done

echo "all webapp are stared"

while true ;
do
   result10=`curl -w %{http_code}"\n" curl  http://192.168.4.37:18080/SocketServer/index.jsp|grep 200`
   if [ ! -z $result9 ] && [ $result9 -eq 200 ];
   then
	 break;
   fi
done

echo "SocketServer is stared"
