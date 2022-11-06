# Redis란?
* REmote DIctionary Server의 약자.  
* In-Memory DataStore Cache서버
* redis는 오픈소스 인 메모리 key value데이터 구조 저장소이다.  
* 데이터를 디스크 또는 ssd에 저장하는 대부분의 dbms와는 달리 redis는 서버의 주 메모리에 상주한다.(RAM에 데이터 저장)
* 속도가 빠르다는 이점이있지만 서버가 종료되는 경우 데이터를 보존하지 못한다는 단점이 있다.
* 메모리 + 디스크 활용을 통한 영속적인 데이터 보존 가능.
* 인 메모리 데이터베이스는 디스크에 엑세스할 필요를 없앰으로 검색 시간으로 인한 지연을 방지하고 cpu 명령을 적게 사용하는 
좀 더 간단한 알고리즘으로 데이터에 엑세스를 할 수 있다.  
* 일반적으로 작업을 실행한는 데 1ms미만이 소요.  
* 속도가 빠르고 사용이 간편하여 최고의 성능이 필요한 웹, 모바일, 게임,광고기술 및 IOT AP에서 널리 사용되고 있다.  

<br>

## Redis 설치
https://jjeongil.tistory.com/1428  

## Redis의 자료구조 
다양한 데이터 유형에 매핑되는 키를 저장할 수 있다.  
기본적인 데이터 유형은 String으로 text또는 이진 데이터가 이에 해당하며 최대 크기는 512MB이다.  
문자열이 추가된 순서대로 유지되는 `List of Strings`, `Sets of unordered Strings`  
점수에 따라 정렬되는 `Sorted Sets`, 필드와 값 목록을 저장하는 `Hashes`, 데이터 세트에서 고유한 항목을 세는 `HyperLogLogs`를 지원한다.  
거의모든 유형의 데이터가 Redis를 사용하여 인 메모리에 저장될 수 있다.  


#### String
String 자료형은 단순히 Key/Value 형태가 값을 저장한다.

출처 : https://www.psjco.com/26
