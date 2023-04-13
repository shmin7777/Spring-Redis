# Spring Redis
![image](https://user-images.githubusercontent.com/67637716/231635542-0ffddb61-21ec-4f9b-ae0e-94ad9b79e964.png)  

## Redis 사용

Redis를 사용하려면 RedisTemplate 또는 RedisRepository를 사용할 수 있다.  
#### RedisTemplate
```  java
@Autowired
private RedisTemplate<String, String> redisTemplate;

public void setValue(String key, String value) {
    redisTemplate.opsForValue().set(key, value);
}

public String getValue(String key) {
    return redisTemplate.opsForValue().get(key);
}
```  

#### RedisRepository
``` java
@Repository
public interface UserRepository extends CrudRepository<User, String> {}

@Autowired
private UserRepository userRepository;

public void saveUser(User user) {
    userRepository.save(user);
}

public User getUser(String id) {
    return userRepository.findById(id).orElse(null);
}
```   





## reference
https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/  
http://redisgate.kr/redis/server/server_cmd_intro.php    
