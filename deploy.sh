cp -R ../reawei ../root
zip -r ../root.zip ../root
scp -r ../root.zip reawei@123.206.181.24:/home/reawei/jetty/webapps/
rm -rf ../root
rm ../root.zip