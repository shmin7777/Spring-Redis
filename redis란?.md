# Redis란?
* REmote DIctionary Server의 약자.  
* In-Memory DataStore Cache서버
* redis는 오픈소스 인 메모리 key value데이터 구조 저장소이다.  
* 리스트, 해시, 셋, 정렬된 셋등 여러 형식의 자료구조를 지원하는 NOSQL
* 데이터를 디스크 또는 ssd에 저장하는 대부분의 dbms와는 달리 redis는 서버의 주 메모리에 상주한다.(RAM에 데이터 저장)
* 속도가 빠르다는 이점이있지만 서버가 종료되는 경우 데이터를 보존하지 못한다는 단점이 있다.
* 메모리 + 디스크 활용을 통한 영속적인 데이터 보존 가능.
* 인 메모리 데이터베이스는 디스크에 엑세스할 필요를 없앰으로 검색 시간으로 인한 지연을 방지하고 cpu 명령을 적게 사용하는 
좀 더 간단한 알고리즘으로 데이터에 엑세스를 할 수 있다.  
* 일반적으로 작업을 실행한는 데 1ms미만이 소요.  
* 속도가 빠르고 사용이 간편하여 최고의 성능이 필요한 웹, 모바일, 게임,광고기술 및 IOT AP에서 널리 사용되고 있다.  
* C언어로 작성
* 라인, 삼성전자, 네이버, Stackoverflow, 인스타그램 등 여러 IT대기업에서도 사용하는 검증된 오픈소스 솔루션


## Redis 사용 이유
* web - was - db 의 전형적인 구조에서는 사용자가 늘어남에 따라 DB에 과부하가 생기게 되고  
데이터베이스의 물리적인 한계로 인해 매 transaction 마다 디스크를 접근해야해서 느려질 수 밖에 없다.
* 이러한 속도에 대한 문제를 해결하기 위해 Cache 를 찾게 됨 ,  
cache 는 한자주 사용하는 데이터나 값을 미리 복사해 놓는 임시 장소를 말한다.  
그래서 같은 요청이 여러번 들어와도 캐시서버에서 바로 결과값을 주기 때문에 DB부하를 줄이고 서비스 개선도 이루어질 수 있음
* cache 서버에 데이터가 있으면 바로 클라이언트로 데이터를 반환 ( cache Hit ) ,  
cache 서버에 데이터가 없으면 DB에 해당 데이터를 요청 함( cache Miss )  



## Redis 아키텍처
#### Redis Topology
Master-slave 형태로 데이터를 복제해서 운영할 수 있다.  
Master-slave간의 복제는 non-blocking 상태로 이루어진다. 

#### Redis Sharding
https://nesoy.github.io/articles/2018-05/Database-Shard
같은 테이블 스키마를 가진 데이터를 다수의 데이터베이스에 분산하여 저장하는 방법  
단점 : 운영이 복잡,  
가능하면 Sharding을 피하거나 지연시킬 수 있는 방법을 찾는 것이 우선 => Scale in, Cache 사용 등..  

Redis에서 데이터를 sharding하여 redis의 read성능을 높일 수 있다.  
예로들어, #1~#999, #1000~#1999 ID형태로 데이터를 나누어서 데이터의 용량을 확장시키고,  
각 서버에 있는 Redis의 부하를 나우어 줄일 수 있다.  

#### Redis Cluster
Redis는 이전에는 Clustering을지원하지 않았지만, Clustering을 지원하면서 대부분의 회사가 Redis를 클러스터로 묶어서  
가용성 및 안정성있는 캐시 매니져로서 사용하고 있다.  
Single Instance로서 Redis를 사용할 때는 Sharding이나 Topology로서 커버해야했던 부분을 Clustering을 이용함으로서 AP를 설계하는데 
좀 더 수월해졌다.  

![image](https://user-images.githubusercontent.com/67637716/200158037-c3801571-6adb-4044-aa17-686e68db8f11.png)  


## 사용시 주의할 점
#### 장애가 났을 경우 그에 대비한 운영 플랜이 세워줘야 함
인메모리 데이터 저장소로서 서버에 장애가 났을 경우, 데이터 유실이 발생.  

snapshot : 어떤 특정 시점의 데이터를 DISK에 옮겨 담는 방식.  
Blocking방식의 SAVE와 Non-blocking방식의 BGSAVE방식이 있다.  

AOF : Redis의 모든 wrtie/update 연산 자체를 모두 log파일에 기록하는 형태.  
서버가 재시작 시, write/update를 순차적으로 재실행, 데티어를 복구한다.  

Redis 공식 문서에서의 권장사항은 RDBMS의 rollback 시스템같이 두 방식을 혼용해서 사용하는것.  
주기적으로 snapshot으로 백업하고 다음 snapshot까지의 저장을 AOF방식으로 수행하는 것.  

#### 캐시솔루션으로 사용할 시 잘못된 데이터가 캐시되는 것을 방지, 예방해야함
Redis를 운영중 잘못된 로직으로 캐시 데이터가 잘못 캐싱되어 올바르지 않은 데이터가 FETCH되어 데이터가 꼬이는 일을 방지. 
캐싱하고자 하는 데이터 저장소의 데이터가 서로 일치하는 지 주기적인 모니터링과 이를 방지하기 위한 사내 솔루션을 개발하는 것이 좋음.  

## Redis 설치
https://jjeongil.tistory.com/1428  



