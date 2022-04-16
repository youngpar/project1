# Lombok

### 구글 검색과 Official 뒤져서 찾았는데 별 내용이 없음
[Lombok Feature][https://projectlombok.org/features/all]

1. Lombok이란?
	- Java 코드 작성 시, Get/Setter, Constructor, Equal, HashCode 함수를 자동으로 정의, 오버라이딩 해주는 모듈
	- @NonNull, @EqualsAndHashCode, @Data, @Syncronized, @With 등이 있음.
	- 자주 사용되는 메서드를 애너테이션으로 지정해두면, 컴파일 시점에서 해당 메소드가 Class 파일에 포함되는데, 쓰다가 런타임 에러가 나면 이걸 바로 찾을 수 있을까? 하는 고민 약간 있음
