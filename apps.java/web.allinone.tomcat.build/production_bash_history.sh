su root
exit
ls
passwd
exit
sudo ps ef -o
sudo ps ef -o command, vsize, rss
sudo ps -e -orss=,args=|sort -b -k1, 1n
sudo ps -e -orss=,args=|sort -b -k1,1n
sudo ps -ef -o command,vsize,rss,%mem,size
top -n 1
java
ps - eo pmem,pcpu,vsize,pid,cmd|sort -k 1 -nr | head -5
ps - eo pmem,pcpu,vsize,pid,cmd | sort -k 1 -nr | head -5
ps ax
ps -eo rss,pid,command --sort -size
ps -eo pid,command --sort -size
exit
ps -auxf
ps -auxf | sort -nr -k 4 | head
clear
ps -auxf | sort -nr -k 4 | head
ps -auxf | sort -nr -k 4 | head 5
ps -auxf | sort -nr -k 4 | head -3
ps -auxf | sort -nr -k 4 | head -30
ps -aux | sort -nr -k 4 | head -30
ps -auxf | sort -nr -k 4 | head
ps -auxf | sort -nr -k 4
ps -aux | sort -nr -k 4 | head -20
ps -auxf | sort -nr -k 4 | head -20
ps -auxf | sort -nr -k 4 | head -3
ps -aux | sort -nr -k 4 | head -3
clear
ps -aux | sort -nr -k4
ps -aux
ps -au
ps au
ps aux | sort -nr -k 4 | head -3
ps aux | sort -nr -k 4 | head -20
ps auxf | sort -nr -k 4 | head -20
ps auxf | sort -nr -k 2 | head -20
clear
ps auxf | sort -nr -k 2 | head -20
ps auxf | sort -nr -k 4 | head -20
ps auxf | sort -nr -k 2 | head -20
ps auxf | sort -nr -k 4 | head -20ps auxf | sort -nr -k 4 | head -20
clear
ls
tar zxvf jdk-7u40-linux-x64.gz
ls
jdk1.7.0_40/bin/java
jdk1.7.0_40/bin/java -version
ls
cd jdk1.7.0_40/bin/
ls
java -version
pwd
cd ~
jdk1.7.0_40/bin/java -version
cd jdk1.7.0_40/bin/
ls java
java
pwd
EXIT
exit
java -version
tar zxvf apache-tomcat-7.0.42.tar.gz
ls
cd tomcat-7.0.42-tst/
ls
cd bin/
ls
./startup.sh-
./shutdown.sh
tail ../logs/catalina.out
./startup.sh-
tail ../logs/catalina.out
tail ../logs/catalina.out -n 200
tail ../logs/catalina.out -n 400
sudo chmod 700 *.sh
./startup.sh-
tail ../logs/catalina.out -n 400
ps auxf | sort -nr -k 4 | head -20ps auxf | sort -nr -k 4 | head -20
ps auxf | sort -nr -k 4 | head -20
./shutdown.sh
ps auxf | sort -nr -k 4 | head -20
ps auxf | sort -nr -k 4 | head -200
./startup.sh-
tail ../logs/catalina.out -n 400

./shutdown.sh
./startup.sh-
cd /etc/sysconfig/
ls
cd ~
ls
iptables
sudo vi /etc/sysconfig/iptables
sudo iptables -t nat -A PREROUTING -p tcp -M TCP --dport 80 -j REDIRECT --TO-PORTS 8080
sudo iptables -t nat -A PREROUTING -p tcp -M TCP --dport 80 -j REDIRECT --to-ports 8080
sudo iptables -t nat -A PREROUTING -p tcp -m tcp --dport 80 -j REDIRECT --to-ports 8080
ls
cd tomcat-7.0.42-tst/
cd bin/
./startup.sh-
tail ../logs/catalina.out
tail ../logs/catalina.out -n 200
./shutdown.sh
tail ../logs/catalina.out -n 200
./startup.sh-
tail ../logs/catalina.out -n 200
tail ../logs/catalina.out -n 200 -f
./shutdown.sh
./startup.sh-
exit
ls
ssh -L 8888:localhost:80
exit
ls
exit
cd tomcat-7.0.42-80/bin/
./shutdown.sh
./startup.sh-
./shutdown.sh
./startup.sh-
ps auxf | sort -nr -k4
ls
./shutdown.sh
./startup.sh-
tail ../logs/catalina.out -n 200
tail ../logs/catalina.out -n 200 -f
cd ../conf/
cd ../bin/
ls
./shutdown.sh
./startup.sh-
tail ../logs/catalina.out
tail ../logs/catalina.out -n 200 -f
exit
cd tomcat-7.0.42-80/
ls
cd bin/
./shutdown.sh
./startup.sh-
./shutdown.sh
tail ../logs/catalina.out -n 200
clear
tail ../logs/catalina.out -n 200
./shutdown.sh
clear
./startup.sh-
./shutdown.sh
tail ../logs/catalina.out -n 200
ps auxf|sort -nr -k4
ps auxf|sort -nr -k4 | head -10
kill 22791
ps auxf|sort -nr -k4 | head -10
./startup.sh-
tail ../logs/catalina.out -n 200
tail ../logs/catalina.out -f
exit
ls
net stat -nlp
netstat -nlp
clear
netstat -nlp
exit
ls
jvisualvm
cat /etc/redhat-release
clear
netstat -a
ps auxf|sort -nr -k 4
ps auxf|sort -nr -k 4 | head -20
net stat -lptu
netstat -lptu
netstat -tulpn
sudo netstat -tulpn
exit
ls
cd tomcat-7.0.42-8099/logs/
tail catalina.out -n 200 -f
ls
cd ..
cd tomcat-7.0.42-80
cd logs/
tail catalina.out -n 200 -f
cd ../bin/
./startup.sh
cat /proc/meminfo
htp
htop
cat /proc/
cat /proc/diskstats
exit
pwd
cd solr.home/
pwd
exit
cd tomcat-7.0.42-80/logs/
tail catalina.out -f
ls
netstat -ntlp
clear
netstat -ntlp
clear
netstat -ntlp
tail catalina.out
tail catalina.out -n 200
tail catalina.out -n 200 -f
clear
tail catalina.out -n 200
tail catalina.2013-09-18.log
tail catalina.out
tail catalina.out  -f
cd ../../tomcat-7.0.42-8099/logs/
tail catalina.out
tail catalina.out -f
exit
ls
ls -lart
tar -zxvf mongodb-linux-x86_64-2.4.6.tgz
ls
cd mongodb-linux-x86_64-2.4.6
ls
cd bin/
cd ~
ls
cd mongodData/
pwd
cd ..
cd mongodb2.4.6/
ls
pwd
cd bin/
./mongod --config mongodb.conf
netstat
netstat -ntlp
exit
cd mongodb2.4.6/
ls
tail logs
tail logs -f
ps auxf
ps auxf|sort -nr k4| head -20
ps auxf|sort -nr k 4| head -20
ps auxf|sort -nr -k 4| head -20
clear
ps auxf|sort -nr -k 4| head -20
sshfs
yum install sshfs
sudo yum install sshfs
 wget http://download.fedoraproject.org/pub/epel/6/i386/epel-release-6-8.noarch.rpm
rpm
sudo rpm -ivh epel-release-6-8.noarch.rpm
ls
sudo rpm -ivh epel-release-6-8.noarch.rpm
sudo yum install sshfs
sshfs yanggang@mbni.org:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org
sshfs yanggang@mbni.org:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA
sudo sshfs yanggang@mbni.org:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA
sudo yum install postgresql
service postgresql initdb
service postgresql
sudo service postgresql initdb
postgresql
ps auxf
yum uninstall
sudo yum remove  postgresql
cat /etc/release
cat /etc/redhat-release
yum uninstallls
ls
clear
yum list postgr*
yum list postgr*server
curl
rpm -ivh pgdg-centos93-9.3-1.noarch.rpm
sudo rpm -ivh pgdg-centos93-9.3-1.noarch.rpm
yum list postgr*server
sudo yum install postgresql93-server.x86_64
service postgresql93
service postgresql9.3
cd /var/lib/pgsql/
sudo ls /var/lib/pssql/
sudo ls /var/lib/pgsql/
service postgresql-9.3
service postgresql-9.3 initdb
sudo service postgresql-9.3 initdb
chkconfig postgresql-9.3 on
sudo chkconfig postgresql-9.3 on
ps auxf
clear
ps auxf|sort -nr -r 4
ps auxf|sort -nr -k 4
clear
sort ps auxf|sort -nr -k 4
sudo  ps auxf|sort -nr -k 4
sudo netstat -sT -O localhost
sudo map -sT -O localhost
sudo nmap -sT -O localhost
sudo netstat -apnt
sudo service postgresql-9.3
sudo service postgresql-9.3 start
sudo netstat -apnt
exit
tail tomcat-7.0.42-80/logs/teamcity-agentPush.log
tail tomcat-7.0.42-80/logs/teamcity-activities.log
tail tomcat-7.0.42-80/logs/teamcity-server.log
tail tomcat-7.0.42-80/logs/teamcity-auth.log
tail tomcat-7.0.42-80/logs/teamcity-javaLogging-2013-09-19.log
exit
ls
sudo netstat -apnt
su -postgresql
su - postgresql
postgres
su postgres
su - postgres
sudo su - postgres
sudo vi /var/lib/pgsql/9.3/data/postgresql.conf
clear
sudo vi /var/lib/pgsql/9.3/data/postgresql.conf
sudo vi /var/lib/pgsql/9.3/data/pg_hba.conf
sudo service posgresql-9.3 restart
sudo service posgresql9.3 restart
sudo service postgresql9.3 restart
sudo service postgresql-9.3 restart
sudo netstat -apnt
sudo su postgres
sudo vi /var/lib/pgsql/9.3/data/pg_hba.conf
sudo service postgresql9.3
sudo service postgresql-9.3 restart
sudo service postgresql-9.3 stop
sudo install postgresql -contrib
sudo yum install postgresql -contrib
sudo yum search post*contrib
sudo yum search postgresql
sudo yum search postgresql9
sudo yum install postgresql93-contrib.x86_64
sudo service postgresql-9.3 start
ls
tail tomcat-7.0.42-8119/logs/catalina.out
tail tomcat-7.0.42-8119/logs/catalina.out -n 200
tail tomcat-7.0.42-8119/logs/catalina.out -n 200 -f
tail tomcat-7.0.42-80/logs/catalina.out -n 200
tail tomcat-7.0.42-80/logs/catalina.out -n 200 -f
tail tomcat-7.0.42-8119/logs/catalina.out -n 200
tail tomcat-7.0.42-8119/logs/catalina.out -n 200 -f
ps auxf

ps auxf
tail tomcat-7.0.42-8119/logs/catalina.out -n 200 -f
ps auxf
ls
tail tomcat-7.0.42-8099/logs/catalina.out -n 200 -f
clear
ls
tail tomcat-7.0.42-80/logs/catalina.out
tail tomcat-7.0.42-80/logs/catalina.out -n 200
tail tomcat-7.0.42-80/logs/catalina.out -n 2000
clear
tail tomcat-7.0.42-8119/logs/catalina.out -n 2000
IOException: Stale file handle
cd .BuildAgent/
cd bin/
chmod 700 *.sh
./agent.sh
./agent.sh start
cd ..
pwd
cd logs/
tail teamcity-agent.log -n 200
cd ~
.BuildAgent/bin/agent.sh
.BuildAgent/bin/agent.sh stop
.BuildAgent/bin/agent.sh start
tail .BuildAgent/logs/teamcity-agent.log
clear
tail .BuildAgent/logs/teamcity-agent.log -n 200
.BuildAgent/bin/agent.sh stop
.BuildAgent/bin/agent.sh start
.BuildAgent/bin/agent.sh stop kill
.BuildAgent/bin/agent.sh start
.BuildAgent/bin/agent.sh stop
.BuildAgent/bin/agent.sh stop kill
.BuildAgent/bin/agent.sh start
tail .BuildAgent/logs/teamcity-agent.log -n 200
tail .BuildAgent/logs/teamcity-agent.log -n 200 -f
ls
.BuildAgent/bin/agent.sh
.BuildAgent/bin/agent.sh stop force
ls
pwd
clear
tail tomcat-7.0.42-8119/logs/catalina.out -n 200 -f
clear
ls
tomcat-7.0.42-8119/bin/shutdown.sh
tail tomcat-7.0.42-8119/logs/catalina.out -n 200 -f
exit
tail tomcat-7.0.42-8119/logs/catalina.out -n 200 -f
ps auxf
tail tomcat-7.0.42-8119/logs/catalina.out -n 200 -f
sshfs
sshfs yanggang@camel1:/home/mengf-lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/
sudo sshfs yanggang@camel1:/home/mengf-lab/club_house/mgrep_for_PubAnat ~/mbni.org_pubA/
ssh camel1
sudo sshfs yanggang@camel1:/home/mengf-lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/
sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/
ls
ls mbni.org_pubA/
sudo unmount mbni.org_pubA
unmount mbni.org_pubA
sudo umount mbni.org_pubA
sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/
sudo chmod -R 777 mbni.org_pubA
ls
sudo umount mbni.org_pubA
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o allow_other
clear
tail tomcat-7.0.42-8119/logs/catalina.out -f
tail tomcat-7.0.42-8119/logs/catalina.out -f -n200
ls
tail tomcat-7.0.42-80/logs/catalina.out
tail tomcat-7.0.42-80/logs/catalina.out -f -n 200
clear
ps auxf
tail tomcat-7.0.42-80/logs/catalina.out -f -n 200
sudo ps auxf
tail tomcat-7.0.42-80/logs/catalina.out -f -n 200
sudo ps auxf
tail tomcat-7.0.42-80/logs/catalina.out -f -n 200
tail tomcat-7.0.42-8099/logs/catalina.out
sudo ps auxf
tail tomcat-7.0.42-80/logs/catalina.out -f -n 200
sudo ps auxf
clear
 ps auxf
sudo ps auxf
kill 29637
sudo ps auxf
clear
sudo ps auxf
tomcat-7.0.42-8099/bin/startup.sh
tomcat-7.0.42-8119/bin/startup.sh
tomcat-7.0.42-8080/bin/startup.sh
tomcat-7.0.42-80/bin/startup.sh
tail tomcat-7.0.42-80/logs/catalina.out -n 200 -p
tail tomcat-7.0.42-80/logs/catalina.out -n 200 -f
tail tomcat-7.0.42-80/logs/catalina.out
tail tomcat-7.0.42-80/logs/catalina.out -n 200 -f
tomcat-7.0.42-80/bin/shutdown.sh
tomcat-7.0.42-8099/bin/shutdown.sh
tomcat-7.0.42-8119/bin/shutdown.sh
ls
mongodb2.4.6/bin/mongod --config mongodb2.4.6/bin/mongodb.conf
tail mongodb2.4.6/logs
exit
tomcat-7.0.42-8099/bin/startup.sh
tomcat-7.0.42-8119/bin/startup.sh
tomcat-7.0.42-80/bin/startup.sh
tail tomcat-7.0.42-80/logs/catalina.out
tail tomcat-7.0.42-80/logs/catalina.out -500
tail tomcat-7.0.42-80/logs/catalina.out -n 500
tail tomcat-7.0.42-80/logs/catalina.out -n 500 -f
sudo iptables -t nat -A PREROUTING -p tcp -m tcp --dport 80 -j REDIRECT --to-ports 8080
clear
sudo iptables -t nat -A PREROUTING -p tcp -m tcp --dport 80 -j REDIRECT --to-ports 8080
tail tomcat-7.0.42-80/logs/catalina.out -n 500 -f
tomcat-7.0.42-80/bin/shutdown.sh
tomcat-7.0.42-80/bin/startup.sh
tail tomcat-7.0.42-8119/logs/catalina.out
tail tomcat-7.0.42-8119/logs/catalina.out -f
exit
tail tomcat-7.0.42-8119/logs/catalina.out -n 500 -f
ls
cd mbni.org_pubA/
ls
sshfs yanggang@mbni.org:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org -o allow_other
sshfs yanggang@mbni.org:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o allow_other
sudo sshfs yanggang@mbni.org:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o allow_other
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o allow_other
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA -o allow_other
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o allow_other
ls
cd ~
ls
tail tomcat-7.0.42-8119/logs/catalina.out -f
tail tomcat-7.0.42-80/logs/catalina.out -f
tail tomcat-7.0.42-80/logs/catalina.out -n 500
exit
tail tomcat-7.0.42-8119/logs/catalina.out -n 500
exit
ls
cd .BuildSettings/
ls
pwd
exit
pwd
ls
cd batch_write/
pwd
exit
tail tomcat-7.0.42-8119/logs/catalina.out
tail tomcat-7.0.42-8119/logs/catalina.out -n 200
tail tomcat-7.0.42-80/logs/catalina.out -n 200
ps auxf
yanggang  2906  0.3  2.1 465906776 4230468 ?   Sl   Sep26  33:25 mongodb2.4.6/bin/mongod --config mongodb2.4.6/bin/mongodb.conf
root     12684  0.0  0.0  62604  3788 ?        S    Sep27   2:06 ssh -x -a -oClearAllForwardings=yes -2 yanggang@camel1 -s sftp
root     12685  0.0  0.0 397396  5140 ?        Ssl  Sep27   0:42 sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ /home/yanggang/mbni.org_pubA/ -o allow_other
exit
ls
sudo chmod 777 batch_write/
sudo ps auxf
tomcat-7.0.42-80/bin/startup.sh
find teamcity-server-log4j.xml
whereis teamcity-server-log4j.xml
find -name teamcity-server-log4j.xml
find .BuildServer/  -name teamcity-server-log4j.xml
find .BuildServer/  -name *log4j.xml
find -name teamcity-server-log4j.xml
find tomcat-7.0.42-80/webapps/auto-build  -name *log4j.xml
find tomcat-7.0.42-80/webapps/auto-build  -name teamcity-server-log4j.xml
find tomcat-7.0.42-80/webapps/auto-build  -name web.xml
ls /home/yanggang/.BuildSettings/settings-production.xml
ps axuf
tomcat-7.0.42-8099/bin/shutdown.sh
tomcat-7.0.42-8199/bin/shutdown.sh
tomcat-7.0.42-8119/bin/shutdown.sh
ls
mv batch_write/master.solr.home/solr.home/ batch_write/
ls
cd batch_write/master.solr.home/
ls
pwd
exit
sudo iptables -t nat -A PREROUTING -p tcp -m tcp --dport 80 -j REDIRECT --to-ports 8080
sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o -allow_other
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o -allow_other
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o allow_other
mongodb2.4.6/bin/mongod --config mongodb2.4.6/bin/mongodb.conf
tail tomcat-7.0.42-80/logs/catalina.out -f
exit
ls
ls /etc/
ls /etc/httpd/
sudo /bin/sftp-server
sudo /usr/lib/openssh/sft-server
sudo vi /etc/httpd/conf/httpd.conf
sudo vi /etc/httpd/conf.d/mbni.conf
tail tomcat-7.0.42-8119/logs/catalina.out -n 300
tomcat-7.0.42-8119/bin/shutdown.sh
ps auxf
ps auxf | sort -nr -k 4
ps auxf | sort -n -k 4
ps auxf | sort -n -k 4|grep "yanggang"
ps auxf | sort -n -k 4|grep "tomcat"
clear
ps auxf | sort -n -k 4|grep "tomcat"
ps auxf | sort -n -k 4|grep "tomcat-7"
ps auxf | sort -n
tail tomcat-7.0.42-8119/logs/catalina.out -n 300
tomcat-7.0.42-8119/bin/startup.sh
tail tomcat-7.0.42-8119/logs/catalina.out -n 300
tail tomcat-7.0.42-8119/logs/catalina.out -n 300 -f
tomcat-7.0.42-8119/bin/startup.sh
tail tomcat-7.0.42-8119/logs/catalina.out -n 300 -f
ls
netstat -a
netstat -a | grep "listen"
netstat -a | grep "LISTEN"
netstat -tupln
sudo netstat -tupln
sudo netstat -tupln | grep "8119"
ps auxf | grep "17223"
tomcat-7.0.42-8119/bin/shutdown.sh
sudo netstat -tupln | grep "8119"
tomcat-7.0.42-8119/bin/startup.sh
tail tomcat-7.0.42-8119/logs/catalina.out
tail tomcat-7.0.42-8119/logs/catalina.out  -f
exit
tail tomcat-7.0.42-8119/logs/catalina.out -n 300
tail tomcat-7.0.42-8119/logs/catalina.out -f
exit
tail mongodb2.4.6/logs
tail mongodb2.4.6/logs -f
exit
tail tomcat-7.0.42-80/logs/catalina.out -f
tomcat-7.0.42-80/bin/startup.sh
ps auxf | grep "tomcat"
ps auxf | grep "80"
ps auxf | grep "-80"
ps auxf | grep "80/conf"
tomcat-7.0.42-80/bin/shutdown.sh
ps auxf | grep "80/conf"
kill 15163
ps auxf | grep "80/conf"
kill -9 15163
ps auxf | grep "80/conf"
tomcat-7.0.42-80/bin/startup.sh
ps auxf | grep "80/conf"
tomcat-7.0.42-80/bin/shutdown.sh
ps auxf | grep "80/conf"
tomcat-7.0.42-80/bin/startup.sh
tomcat-7.0.42-80/bin/shutdown.sh
ps auxf | grep "80/conf"
tomcat-7.0.42-80/bin/startup.sh
cd .BuildServer/logs/
ls
pwd
tomcat-7.0.42-80/bin/shutdown.sh
cd
tomcat-7.0.42-80/bin/shutdown.sh
tomcat-7.0.42-80/bin/startup.sh
tail tomcat-7.0.42-8119/logs/catalina.out
tomcat-7.0.42-8119/bin/shutdown.sh
tomcat-7.0.42-8119/bin/startup.sh
ps auxf | grep 'yanggang'
ps auxf |sort -nr -k4 |  grep 'yanggang'
clear
ps auxf |sort -nr -k4 |  grep 'tomcat'
kill 17420
ps auxf |sort -nr -k4 |
ps auxf |sort -nr -k4 |  grep '8119'
kill 7924
ps auxf |sort -nr -k4 |  grep '8119'
sudo kill 17420
ps auxf |sort -nr -k4 |  grep '8119'
sudo kill -9 17420
ps auxf |sort -nr -k4 |  grep '8119'
tomcat-7.0.42-8119/bin/startup.sh
tail tomcat-7.0.42-8119/logs/catalina.out
tail tomcat-7.0.42-8119/logs/catalina.out -f
sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o -allow_other
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o -allow_other
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o allow_other
tail tomcat-7.0.42-8119/logs/catalina.out -f
tail tomcat-7.0.42-8080/logs/catalina.out -f
tail tomcat-7.0.42-80/logs/catalina.out
tail tomcat-7.0.42-80/logs/catalina.out  -f
tomcat-7.0.42-80/bin/shutdown.sh
tail tomcat-7.0.42-80/logs/catalina.out  -f
tail tomcat-7.0.42-80/logs/catalina.out  -f -n 300
mongodb2.4.6/bin/mongod --config mongodb.conf
cd mongodb2.4.6/

ls
mongod --config mongodb.conf
./mongod --config mongodb.conf
cd ~
ls
tomcat-7.0.42-80/bin/startup.sh
tomcat-7.0.42-8099/bin/
tomcat-7.0.42-8099/bin/startup.sh
service httpd start
sudo service httpd start
tail tomcat-7.0.42-80/logs/catalina.out  -f -n 300
ls
cd tomcat-7.0.42-80/home_cache/
pwd
cd tomcat-7.0.42-80/home_cache/
tail tomcat-7.0.42-80/logs/catalina.out  -f -n 300
cd ~
tail tomcat-7.0.42-80/logs/catalina.out  -f -n 300
cls
clear
tail tomcat-7.0.42-80/logs/catalina.out  -f -n 300
clear
tail tomcat-7.0.42-80/logs/catalina.out  -f
clear
tail tomcat-7.0.42-80/logs/catalina.out  -f
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o allow_other
tomcat-7.0.42-8119/bin/startup.sh
ls
cd mongodb2.4.6/bin/
mongo
./mongo
cd ..
bin/mongo
cd ~
tail tomcat-7.0.42-8119/logs/catalina.out -f
tail tomcat-7.0.42-8119/logs/catalina.out -f -n 500
tail tomcat-7.0.42-80/logs/catalina.out -f
tail tomcat-7.0.42-8119/logs/catalina.out -f
tail tomcat-7.0.42-80/logs/catalina.out -f
du -sh
tail tomcat-7.0.42-80/logs/catalina.out -f
ssh 192.168.1.5
exit
clear
sudo sshfs yanggang@camel1:/home/mengf_lab/club_house/mgrep_for_PubAnatomy/ ~/mbni.org_pubA/ -o allow_other
ls
mongodb2.4.6/bin/mongod --config mongodb2.4.6/bin/mongodb.conf
tomcat-7.0.42-8099/bin/startup.sh
tomcat-7.0.42-8119/bin/startup.sh
tomcat-7.0.42-80/bin/startup.sh
sudo service httpd start
du ./
du -h ./
du -h batch_write/
du -h .BuildServer/
du -h -c .BuildServer/
du -h -c .cache/
du -h -c .m2/
du -h -c .subversion/
du -h -c mongodData/
du -h -c pubanatomy_*
du -h -c pubanatomy_batch_*
du -h -c teamsysdata/
df
df -a
df -h
df -hT /home/
tail tomcat-7.0.42-80/logs/catalina.out
ssh 52c34b42500446d4670000a2@wildfly-mbnimedumich.rhcloud.com
ls
cd batch_write/
ls
cd ..
ls
cd tomcat-7.0.42-8099/b
cd tomcat-7.0.42-8099/bin/
cd ..
ls

tail tomcat-7.0.42-8119/logs/catalina.2013-11-19.log
tail tomcat-7.0.42-8119/logs/catalina.out
tail tomcat-7.0.42-8119/logs/catalina.out -n 200
tail tomcat-7.0.42-8119/logs/catalina.out -n 500
 tomcat-7.0.42-8119/bin/
cd tomcat-7.0.42-8119/bin/
ls
./shutdown.sh
./startup.sh
./startup.sh && tail ../logs/*
cd ~
du -s -h
ls
cp mongodData batch_write/mongodData
df batch_write/
mk batch_write/mongodData
mkdir batch_write/mongodData
cp mongodData batch_write/mongodData
cp -avr mongodData batch_write/mongodData
ls
cd mongodb2.4.6/bin/
ls
cd ..
cd ../batch_write/mongodData/mongodData/
pwd
cd ..
cd ~/mongodb2.4.6/
mongd --shutdown
ls
cd bin
mongod --shutdown
ls
./mongod --shutdown
./mongod
service mongod stop
mongod --config ~/mongodb2.4.6/bin/mongodb.conf
./mongod
mongod --config ~/mongodb2.4.6/bin/mongodb.conf --shutdown
./mongod --config ~/mongodb2.4.6/bin/mongodb.conf --shutdown
./mongod --config ~/mongodb2.4.6/bin/mongodb.conf
pwd
ls
tail tomcat-7.0.42-80/logs/catalina.out
tail tomcat-7.0.42-80/logs/catalina.out -f
tail tomcat-7.0.42-8119/logs/catalina.out
tail tomcat-7.0.42-8119/logs/catalina.out -t 4888
tail tomcat-7.0.42-8119/logs/catalina.out -n 4888
tail tomcat-7.0.42-8119/logs/catalina.out -f
sudo vm /etc/sysconfig/iptables
sudo vi /etc/sysconfig/iptables
sudo vi /etc/sysconfig/selinux
