# kafka test binder 주의사항

원본 ([링크](https://docs.spring.io/spring-cloud-stream/reference/spring-cloud-stream/spring_integration_test_binder.html#special-note-on-mixing-test-binder-and-regular-middleware-binder-for-testing))

> The Spring Integration based test binder is provided for testing the application without involving an actual middleware based binder such as the Kafka or RabbitMQ binder. 
> 
> As described in the sections above, the test binder helps you to verify the application behavior quickly by relying on the in-memory Spring Integration channels. When the test binder is present on the test classpath, Spring Cloud Stream will try to use this binder for all testing purposes wherever it needs a binder for communication. In other words, you cannot mix both the test binder and a regular middleware binder for testing purposes in the same module. After testing the application with the test binder, if you want to continue doing further integration tests using the actual middleware binder, it is recommended to add those tests that use the actual binder in a separate module so that those tests can make the proper connection to the actual middleware rather than relying on the in-memory channels provided by the test binder.

번역 (by DeepL)

> Spring 통합 기반 test binder는 Kafka 또는 RabbitMQ 바인더와 같은 실제 미들웨어 기반 바인더를 사용하지 않고 애플리케이션을 테스트하기 위해 제공됩니다. 
> 
> 위 섹션에서 설명한 대로 테스트 바인더는 인메모리 Spring 통합 채널에 의존하여 애플리케이션 동작을 빠르게 검증할 수 있도록 도와줍니다. 
> 
> 테스트 바인더가 테스트 클래스 경로에 있으면 Spring Cloud Stream은 통신을 위해 바인더가 필요한 모든 테스트 목적에 이 바인더를 사용하려고 시도합니다. 
> 
> 즉, 테스트 바인더와 일반 미들웨어 바인더를 동일한 모듈에서 테스트 목적으로 혼합할 수 없습니다. 
> 
> 테스트 바인더로 애플리케이션을 테스트한 후 실제 미들웨어 바인더를 사용하여 추가 통합 테스트를 계속하려면 테스트 바인더가 제공하는 인메모리 채널에 의존하지 않고 실제 미들웨어에 제대로 연결할 수 있도록 실제 바인더를 사용하는 테스트를 별도의 모듈에 추가하는 것이 좋습니다.