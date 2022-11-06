
## Redis의 자료구조 
![image](https://user-images.githubusercontent.com/67637716/200158365-e266b15f-e7d0-4c5d-968e-5e282eb0dce8.png)  
다양한 데이터 유형에 매핑되는 키를 저장할 수 있다.  
기본적인 데이터 유형은 String으로 text또는 이진 데이터가 이에 해당하며 최대 크기는 512MB이다.  
문자열이 추가된 순서대로 유지되는 `List of Strings`, `Sets of unordered Strings`  
점수에 따라 정렬되는 `Sorted Sets`, 필드와 값 목록을 저장하는 `Hashes`, 데이터 세트에서 고유한 항목을 세는 `HyperLogLogs`를 지원한다.  
거의모든 유형의 데이터가 Redis를 사용하여 인 메모리에 저장될 수 있다.  


#### String
String 자료형은 단순히 Key/Value 형태가 값을 저장한다.
