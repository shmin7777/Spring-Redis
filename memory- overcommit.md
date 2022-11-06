## commit이란
commit이란, 프로세스가 커널에 메모리 요청을 할 때 malloc()과 같은 시스템 콜을 사용한다.  
커널은 시스템 콜 요청을 받고 해당하는 메모리 영역의 주소를 전달자로 넘겨준다.  
이 때 프로세스가 할당받고 사용하지 않을 수 있기 때문에 할당해준 메모리 영역을 물리 메모리에 바인딩하지는 않는다.  
즉, 프로세스는 받았다고 생각하지만 실제로는 물리 메모리 어느곳에도 할당되어지지 않은 상태.  
이것을 memory commit이라고 한다.  

![image](https://user-images.githubusercontent.com/67637716/200155743-7ff91770-2e56-432f-87ce-58868d426aaa.png)  

## memory commit이 필요한 이유 
순간적으로 많은 양의 메모리를 필요로 하는 작업이 필요하기 때문.  
득히 fork()와 같은 프로세스를 생성하는 시스템 콜을 처리할 수 있어야 하기 때문.  
fork()는 새로운 프로세스를 만드는 시스템 콜인데, 자식 프로세스는 부모 프로세스의 모든 주소 공간을 그대로 복사해 온다.  
하지만 대부분의 경우 fork() -> exec()콜을 통해 새로운 작업으로 넘어가는 경우가 많기 때문에 
복사해 온 공간을 그대로 사용하는 경우는 매우 드물게 일어난다.  
부모 프로세스의 메모리 영역을 복사하는 과정에서 memory commit이 일어나게 되고, 경우에 따라서는 overcommit이 필요한 경우가 생긴다.  

![image](https://user-images.githubusercontent.com/67637716/200155992-f5606381-61ea-4fb1-a987-45cd7714d7bb.png)  
 
예를 들면 물리 메모리가 4기가 일 때, 3기가 프로세스 A가 돌고 있다고 해 보자.  
해당 프로세스가 자식 프로세스 B를 생성하게 (fork) 되면 자식 프로세스 B는 부모 프로세스 A와 마찬가지로 3기가 프로세스일 것이다.  
하지만 이미 전체 메모리 4기가에서 3기가를 부모 프로세스 A 가 사용하고 있기 때문에 가용 메모리는 1기가 밖에 되지 않는다.  
메모리가 부족하기 때문에 fork 는 실패할 것이라고 생각 되지만 (자식 프로세스 B 생성 실패) 실제로는 성공한다.  
커널에 의해서 memory commit 이 되어 자식 프로세스 B 에게 3기가를 준 척(?) 할 수 있는 것이다.  
만약 memory commit 이 없다면 자식 프로세스는 생성 되지 못할 것이다.  
이때 남은 가용 메모리가 1기가 였기 때문에 자식 프로세스 B 는 2기가가 overcommit 이 되었다고 한다.  

## overcommit의 옵션
첫 번째 옵션은 vm.overcommit_memory이다.  
overcommit에 대한 enable/disable을 결정하는 중요한 파라미터.  

0 - 커널에서 설정하는 기본값이며, Heuristic하게 overcommit을 허용하는 옵션.  
![image](https://user-images.githubusercontent.com/67637716/200156116-f2585617-48b0-46ef-a3cb-c24aa4327436.png)  

Page Cache + Swap Memory, Slab Reclaimable을 합친 수가 요청한 메모리 수보다 클 경우 commit이 일어난다.  
실제 시스템의 free 메모리를 가지고 계산하지 않는다.  

1 - 항상 overcommit 허용, 메모리 할당이 항상 성공한다.  
![image](https://user-images.githubusercontent.com/67637716/200156177-85cb4f04-a0d2-4d8b-ad26-f8b252324cb8.png)  


2 - 제한적 overcommit을 허용한다.
현재 시스템에 설정되어 있는 Swap 영역의 크기 + vm.overcommit_ratio값으로 결정된 메모리 영역만큼 overcommit을 할 수 있다.  
지정된 영역을 넘어가게 되면 commit 실패  
![image](https://user-images.githubusercontent.com/67637716/200156187-a8900273-e16a-401b-8f4a-df5c172810e6.png)  


출처 : https://brunch.co.kr/@alden/16  

