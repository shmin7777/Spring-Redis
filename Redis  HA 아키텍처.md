# HA(High Availability, 무중단 서비스) 아키텍처
### Standalone(다른 컴퓨터와의 연결 없이 독립적으로 존재) : No HA, 마스터  
![image](https://user-images.githubusercontent.com/67637716/200158607-f331fba5-309c-4706-a885-869285a43189.png)  
* Redis 서버 1대로 구성하며, 이를 마스터 노드라고 한다.
* 서버 다운 시 AOF또는 Snapshot 파일을 이용해 재 시작

### Replication: Half HA, Master-slave
![image](https://user-images.githubusercontent.com/67637716/200158634-f6945adc-3225-4ed7-9033-bafdc82ea5bc.png)  
* redis server 2대(마스터-슬레이브)로 구성됨. slave는 master의 데이터를 실시간으로 전달받아 보관
* 마스터 다운 시 슬레이브 서버를 통해 서비스를 계속할 수 있다. 이 때에는 수동으로 슬레이브 서버를 마스터로 변경해야한다.
* 한 마스터에 슬레이브를 여러 대 구성할 수도 있다.


### 이중화 + 센티널(Sentinel) : HA, 무중단 서비스 가능
![image](https://user-images.githubusercontent.com/67637716/200158715-e4cb3930-d595-459e-963b-ba2ed8057035.png)  
* 마스터-슬레이브 구성에 센티널을 추가해서 각 서버를 감시하도록 하며, 센티널은 마스터 서버를 감시하고 있다가
다운되면 슬레이브를 마스터로 승격시킨다.  
* 다운된 마스터가 다시 시작되면 센티널이 슬레이브로 전환
* 마스터 노드가 모든 데이터를 가지고 있으며 슬레이브는 마스터에 대한 복제본을 유지하고 있으며, 데이터를 분산하지 않는다.
* 센티널은 Redis 서버마다 하나씩 설치하며, 서버와 분리된 프로세스이며 다른포트를 사용한다. 
* 센티널은 높은 가용성과 자동 fail over를 해결하지만 `데이터 분산 문제를 해결하지 못한다.` 

### 레디스 클러스터(Cluster) : HA, 무중단 서비스 가능
![image](https://user-images.githubusercontent.com/67637716/200158902-82ee2d80-e832-42fa-9f9c-00f1e2d451ee.png)  



