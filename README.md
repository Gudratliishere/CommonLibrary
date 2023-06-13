# CommonLibrary

CommonLibrary is a Java library that provides utility classes for logging HTTP requests and responses using WebClient
and applying filter, pagination, and sorting operations to Spring Data JPA repositories.

## Features

- `LoggerHttpClient`: A utility class for logging HTTP requests and responses sent via WebClient.
- `SearchFilterService`: A utility class for applying filter, pagination, and sorting operations to Spring Data JPA
  repositories.

## Getting Started

1. Import maven dependency:

```xml
<dependency>
  <groupId>com.gudratli</groupId>
  <artifactId>common-library</artifactId>
  <version>0.0.2</version>
</dependency>
```

2. Create beans in your spring boot project:  

```java
import com.gudratli.commonlibrary.queryfilter.service.SearchFilterService;
import com.gudratli.commonlibrary.queryfilter.service.gimpl.GSearchFilterService;

@Configuration
public class GCommonLibraryConfig
{
    @Bean
    public SearchFilterService searchFilterService ()
    {
        return new GSearchFilterService();
    }
}
```

Here `Configuration` annotation declares this class as configuration class for spring boot.`@Bean` 
annotation tells spring boot for create instance of this class on runtime. So you can use this class 
wherever you want by autowiring.  

3. Autowire instance where you want:  
```java
@Autowired
private SearchFilterService searchFilterService;
```

This method is not recommended usually by spring boot. You can use as alternatively constructor dependency
injection:  
```java
private final SearchFilterService searchFilterService;

public YourClass (SearchFilterService searchFilterService){
  this.searchFilterService = searchFilterService;
}
```

## Examples

### LoggerHttpClient

```java
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class GAuthService implements AuthService
{
  @Override
  public JwtResponse login (LoginRequest request)
  {
    return WebClient.builder()
            .baseUrl("https://domain")
            //here you pass object of class to webclient by wrapping with JettyClientHttpConnector
            .clientConnector(new JettyClientHttpConnector(new LoggerHttpClient()))
            .build()
            .post()
            .uri("/some-url")
            .body(Mono.just(request), LoginRequest.class)
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(JwtResponse.class))
            .block();
  }
}
```

That is all, all requests will log with log4j. You can log this requests to file via configuring log4j properties.

### SearchFilterService

```java
import com.example.common.SearchFilter;
import com.example.common.SearchFilterService;

@Service
@RequiredArgsConstructor
public class GDeviceService implements DeviceService
{
    private final DeviceRepository deviceRepository;
    private final SearchFilterService searchFilterService;

    @Override
    public Page<Device> getAll (SearchFilter searchFilter)
    {
        Specification<Device> specification = searchFilterService.getSpecification(searchFilter);
        Pageable pageable = searchFilterService.getPageable(searchFilter);

        return deviceRepository.findAll(specification, pageable);
    }
}
```

## Contributing

Contributions to CommonLibrary are welcome! If you have any bug reports, feature requests, or would like to contribute
code, please open an issue or submit a pull request following the guidelines outlined in the CONTRIBUTING.md file.

## License

CommonLibrary is licensed under the [MIT License](https://opensource.org/licenses/MIT). See the LICENSE file for more
details.

## Contact

For any further questions or inquiries, please contact [Gudratliishere](https://github.com/Gudratliishere).
d expand this README file based on your project's specific requirements and guidelines.