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

* master를 여러개 두어 분산 저장이 가능하며(Sharding), scale out 이 가능하다.
서버를 늘릴수록 저장할수 있는 공간이 무한대로 커진다. 
* master에 하나 이상의 slave 를 둘 수 있다.
* master 1,2,3 이 있다면 데이터는 3개중에 하나에 저장되며, client 가 데이터 읽기 요청시 저장된 곳이 아닌 다른 마스터에 요청 했다면 저장된 마스터 정보를 알려주며, 클라이언트는 전달받은 마스터 정보에 다시 요청해서 데이터를 받아와야 한다.
  * But, 해당 부분은 redis-cluster 를 지원하는 라이브러리에서 다 해준다.

![image](https://user-images.githubusercontent.com/67637716/200159048-33f9a72a-41fc-4338-9f13-dc7f812455bd.png)  

* slave 가 죽어서 복제 노드가 없는 마스터가 생길시 다른 마스터 노드에 여유분이 있다면 해당 노드로 빈자리를 채울 수 있다.
  * 사용자가 개입하지 않고 클러스터가 알아서 다 해준다.  
![image](https://user-images.githubusercontent.com/67637716/200159061-b1a13107-f9ad-41ba-8bfb-09da3536199f.png)  

* 마스터가 죽었을 시 Slave가 master로 자동 failover된다.
* 최소 3개의 마스터 노드가 있어야 구성가능
* 센티널이 노드를 감시했지만, 클러스터에서는 모든 노드가 서로 감시


✔ 참고

- scale up : 단일 서버의 스펙을 올려 서버 성능을 높힌다.

- scale out : 서버를 추가하여 서버 성능을 높힌다.
