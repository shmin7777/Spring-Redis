1. Redis 설치

yum install redis


2. Redis 시작 및 재부팅 시 시작 설정
systemctl start redis
systemctl enable redis


3. 잘되는지 확인
redis-cli ping
>> PONG


4. Redis 설정
vi /etc/redis.conf


# 기존 설정값 주석처리
#bind 127.0.0.1

# 로컬호스트만 접속 가능 -> 모든 호스트에서 접속 가능하도록 수정
bind 0.0.0.0

# port 수정
port 1234

# 비밀번호 설정
requirepass password1234

 

cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
appendonly no



5. 서비스 재시작
systemctl restart redis


6. 레디스 외부에서 접속 가능하게 설정이 잘 되었는지 확인
netstat -nlpt | grep 1234
tcp        0      0 0.0.0.0:1234            0.0.0.0:*               LISTEN      18054/redis-server


7. 타서버에 레디스 패키지 설치
yum install redis-tools


8. 타서버에서 Redis서버에 접속
redis-cli -h 123.45.0.67 -p 1234 -a password1234
redis-cli -h [레디스 서버의 IP주소] -p [포트번호] -a [비밀번호]

명령어를 입력할 수 있도록 접속됨
123.45.0.67:1234>                       
123.45.0.67:1234>                       
123.45.0.67:1234>                       


9. Redis 설치한 서버에서 로그 확인
tail -f /var/log/redis/redis.log
