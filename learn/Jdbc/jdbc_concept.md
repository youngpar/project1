# JDBC Concept, 응용 기술

인프런 요약

1. JDBC란?
	- Java DataBase Connectivity
	- Application 개발에서 데이터의 저장과 사용은 주로 DB를 이용
	- 클라이언트에서 HTTP를 통해 백엔드 서버에 요청을 보내면, 백엔드 서버에서는
		1) 커넥션 생성(연결, 주로 TCP/IP)
		2) 커넥션을 통해 SQL Query 전달
		3) DB로부터 결과 수신
		4) 수신한 정보를 활용해 클라이언트의 요청에 응답
		의 과정을 거침
	- 각각의 DB에 커넥션을 연결, SQL 전달, 응답의 방식이 다르기 때문에, 자바 표준인 JDBC라는 표준 사용
		> 유연한 데이터베이스에 대한 의존성 감소
	- JDBC에는 크게 Connection(연결), Statement(쿼리), ResultSet(응답)의 기능을 인터페이스로 정의
	- 각각의 DB Vendor에서 해당 인터페이스의 구현체(드라이버)를 제공하고, 개발자는 JDBC에 해당 구현체를 캐스팅해 사용
	- 연결, 쿼리 전달, 응답이 표준화가 되었지만, 각각의 DB마다 Dialect, 자료형의 차이가 존재하며 JPA를 통해 해당 Query 역시 추상화해 사용 가능

2. JDBC 응용 기술
	- JDBC는 사용 방법이 복잡하고 오래되어, 이를 보조하는 다양한 방법이 존재하는데 대표적으로 SQL Mapper와 ORM 기술이 있음

	- SQL Mapper:
		+ JDBC의 반복적인 코드를 재사용하도록 돕고, SQL 응답 결과를 객체로 변환
		+ SQL을 개발자가 직접 작성함
		+ MyBatis, JDBC Template이 대표적
		> 애플리케이션 로직과 JDBC 중간 지점에서 SQL을 전달

	- ORM:
		+ Object-Relation Mapping, 객체 관계 매핑
		+ 객체 중심의 자바와 데이터 중심의 SQL 사이 패러다임의 충돌을 완화하기 위해 ORM 기술이 사용
		+ ORM 기술을 통해 비즈니스 로직과 데이터 구조는 독립성을 가짐
		+ JPA에 객체가 전달되면 JDBC에 동적으로 SQL을 만들어 전달하며, 반환 결과를 객체에 매핑해줌
		+ JPA 인터페이스의 구현체로 Eclipse-Link, Hibernate가 존재

	
### 요약: DB 사용 방식이 모두 달라 JDBC라는 표준 인터페이스 생겼고, JDBC를 활용하기 위한 SQL Mapper, ORM 기술 존재
