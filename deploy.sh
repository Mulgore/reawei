cp -R ../reawei ../root
zip -r ../root.zip ../root
scp -r ../root.zip root@123.206.231.180:/opt/reawei/jetty/webapps/
rm -rf ../root
rm ../root.zip