# Mybatis

### 기본적인 레퍼런스는 MyBatis.org 참조했음

1. MyBatis란?
	- SQL, 프로시저를 별도의 파일로 분리할 수 있고, 객체-릴레이션 사이의 매핑을 해주는 ORM 프레임워크
	- JDBC로 처리하는 코드와 파라미터 설정, 결과를 매핑해주는 Data Access 전반을 다룸
	- Hibernate, JPA 같은 새로운 DB 프로그래밍 패러다임의 학습 없이 SQL을 그대로 사용하여 효율적인 쿼리를 사용 가능하며 JDBC의 코드 반복 작성과 도메인/VO 객체를 중심으로 개발 가능
	- 데이터베이스 레코드와 자바 Primitive, Map, User Defined(Pojo) 클래스를 매핑하기위해 XML이나 어노테이션을 사용 가능
	- SQL과 자바 코드가 낮은 결합도를 가지게되며, SQL이 수정되더라도 새로 컴파일 할 필요가 없음
		-> 개인적으로 가장 좋은 점인듯(JPA는 어떨지 모르겠음)
	- 여러 언어의 구현체가 있음(MSA 환경에서 같은 SQL을 여러 언어, 프레임워크에서 사용 가능)

### Spring 환경에서 MyBatis-Spring의 DB Access 경로
	Service -> Repository(Mapper) -> ORM(MyBatis) -> JDBC API/DataSource -> JDBC Driver(Impl) -> DB

###### vo : 값 그 자체를 나타내는 객체, DTO와 달리 로직을 포함할 수 있으며 값이 변조가 불가
###### 		같은 값을 가지고 있다면 같은 객체임을 나타내기 위해, hashCode(), equals()를 오버라이드

## How to Use

1. 의존성 관리
	- 스프링에서 마이바티스를 사용하기 위해 mybatis-x.x.x.jar를 클래스 패스에 두어야 함
		- groupId: org.mybatis.spring.boot
		- artifactId: mybatis-spring-boot-starter
		- version: x.x.x

2. SqlSessionFactory 빌드
	- 마이바티스 어플리케이션은 SqlSessionFactoryBuilder를 통해 SqlSessionFactory 인스턴스를 사용
	- SqlSessionFactoryBuilder는 xml 설정파일을 읽어 팩토리 인스턴스를 빌드함
	- xml 설정 파일에는 매퍼의 경로, 데이터 소스, 속성 등이 포함된다.
	- Java 소스 코드에서는 바티스 config의 경로를 불러올 수 있으며, 클래스 패스를 통해 가져오거나, Resources라는 클래스를 통해 URL 스트림을 통해 클래스 패스 외부에서 자원을 로드할 수 있다.
	- 또는 xml 구성 없이 Configuration 클래스를 통해 자바 소스코드로 설정 가능

3. SqlSession Instatiation
	- SqlSession이란 데이터 베이스에 연결하기 위한 세션으로 SQL Query를 실행하기 위해 필요한 모든 메소드를 가짐.
	- 마이바티스 SQL 구문의 파라미터와 리턴값을 설명하는 인터페이스로 저장된 SQL을 실행하거나, SQL 직접 실행 가능
	- 저장된 쿼리를 사용하면 오타, 문자열 처리, 타입 캐스팅에 대해 안전
	```java
		try(SqlSession session = SqlSessionFactory.openSession()) {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User user = userMapper.findById(123);
	```
	SqlSessionFactory로 세션을 생성, 세션에서 Data Access를 위한 매퍼(인터페이스)를 만들고 xml에 정의된 findById를 호출

	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="app.youngmon.project1.user.repository.UserMapper">
		// namespace로 클래스의 네임스페이스를 지정해줌(패키지)
    	<select id="findAll" resultType="HashMap">
		// id가 findAll이므로 namespace.id에 SQL 실행 결과를 HashMap으로 반환
        	select * from users;
    	</select>
	</mapper>

	```
	>현재 마이바티스에서는 전체 경로를 표현하는 네임스페이스를 필수로 사용해야함.
	>	>네임 스페이스로 자바 소스코드로 만들어진 인터페이스에 SQL을 바인딩해 사용 가능

4. Java Source Code 계층에서 직접 쿼리
	- 복잡한 Query를 사용할 때는 xml에 저장해두고 호출해 사용하는 것이 유용할 수 있다.
	- 단순하고 짧은 코드라면 xml에 작성할 필요 없이 어노테이션을 이용해 작성 가능
	```java
	public interface UserMapper {
	@Select("SELECT * from user WHERE name = #{name}")
	User findById(String name);
	}
	```
	
5. Scope & LifeCycle
	- 스프링 컨테이너로 의존성 주입을 통해, 세션, 매퍼 등을 주입해 Thread-safe 달성 가능
	- DI 프레임워크를 통한 개발에서는 SessionFactory, FactoryBuilder에서는 일반 개발보다 생명 주기 관리가 덜 중요하나, 단순히 팩토리를 생성하는 용도의 팩토리 빌더는 유지되지 않도록 하는 것이 좋고, 세션을 생성하고 관리하는 세션팩토리는 static 싱글턴, 싱글턴 패턴으로 관리하는 것이 좋음
	
	이 중 개발 과정에서 자주 사용될 것은 Session과 Mapper 인스턴스
	- SqlSession
		- 공유되지 않고, Thread-Unsafe 하기 때문에 각 스레드는 독립적인 세션을 가져야 함
		- static, 클래스의 인스턴스 필드로 사용 X(유지하지 않고 close 필요)
		- 요청을 받을 때마다 만들고, 응답을 리턴할 때마다 닫아야 함(finally 사용)
		```java
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// Code
		}
		```
		JDK 1.7 이상에 존재하는 try-with-resources로 AutoClosable 인터페이스를 구현한 객체 이용

	- Mapper
		- 매핑된 구문을 바인딩 하기 위해 만들어야 하는 인터페이스, SqlSession에서 getMapper() 메소드로 생성.
		- Session과 같은 Scope(메서드 내부)에 두는 것이 좋으며, 사용 될 메서드가 호출되면 생성되고 끝나며, 명시적으로 닫을 필요는 없음
