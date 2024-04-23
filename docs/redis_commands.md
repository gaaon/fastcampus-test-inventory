# redis commands

## GET

키의 값을 가져옵니다. 키가 존재하지 않으면 특수 값 nil이 반환됩니다.

> [!WARNING]
> 
> GET은 문자열 값만 처리하므로 키에 저장된 값이 문자열이 아닌 경우 오류가 반환됩니다.

```shell
redis> GET nonexisting
(nil)
redis> SET mykey "Hello"
"OK"
redis> GET mykey
"Hello"
```

## DECRBY

키에 저장된 숫자를 감소시킵니다. 키가 존재하지 않으면 연산을 수행하기 전에 0으로 설정됩니다.
키의 감소된 값을 반환합니다.

> [!WARNING]
> 
> 키에 잘못된 타입의 값이 포함되어 있거나 정수로 표현할 수 없는 문자열이 포함되어 있으면 오류가 반환됩니다.

```shell
redis> SET mykey "10"
"OK"
redis> DECRBY mykey 3
(integer) 7
redis> DECRBY nonexisting 10
(integer) -10
redis> SET abc def
"OK"
redis> DECRBY abc 10
(error) value is not an integer or out of range
```

## DEL

지정된 키를 제거합니다. 키가 존재하지 않으면 무시됩니다.
제거된 키의 수를 반환합니다.

```shell
redis> SET key1 "Hello"
"OK"
redis> SET key2 "World"
"OK"
redis> DEL key1 key2 key3
(integer) 2
````

## SET

문자열 값을 저장할 키를 설정합니다. 키가 이미 값을 가지고 있으면, 타입과 상관없이 덮어씁니다.
`OK` 문자열을 반환합니다.

```shell
redis> SET mykey "Hello"
"OK"
redis> GET mykey
"Hello"
```

## 출처
[Redis 공식 문서](https://redis.io/docs/latest/commands)