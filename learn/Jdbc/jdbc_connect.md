# JDBC 연결

1. JDBC 연결 과정
	- 단순 인터페이스인 JDBC는 구현체(Driver)에 따라 다른 Connection을 반환
	- 각각 다른 DB에 연결하기 위헤 DriverManager가 사용됨
		- DriverManager는
			1) getConnection 요청이 들어오면, 라이브러리에 등록된 드라이버 목록 인식
			2) 드라이버 들에게 순서대로 getConnection 인자를 넘겨 커넥션을 요청
			3) 각 드라이버는 URL 정보를 통해 처리할 수 있는지 확인
			4) 처리 가능한 드라이버에게 순서가 넘어가게 되고, 커넥션 구현체가 클라이언트에 반환
		
		> DriverManager는 Driver 목록 관리 및 커넥션 획득을 담당
	- 실제 반환될 Connection의 구현체는 데이터베이스 모듈(드라이버) 내부 jdbc 패키지 안에 들어있음

2. 반환받은 Connection으로 쿼리 수행
	- 반환된 Connection 객체는 Query 바인딩을 위해 String 타입의 SQL을 받아 PreparedStatement객체 반환.
	- PreparedStatement는 Set{Type}(idx, value) 메소드로 String 형태의 문자열에 값을 매핑해 사용 가능
		- String + $value + String과 같은 Statement가 아닌 파라미터 바인딩을 통해 SQL Injection 방지
		> ####Statement, PreparedStatement 차이
		> - Statement: 단일 사용 시 빠른 수행 시간 보장, 쿼리에 인자 부여 불가, 매 번 쿼리에 대한 컴파일 수행
		> - PreparedStatement: 쿼리에 Param 바인딩 가능, 처음 프리컴파일 이후 컴파일 수행 X, 반복 사용 시 Statement보다 빠름

	- PreparedStatement의 execute, executeUpdate, executeQuery 함수를 통해 데이터 변경(DML) 수행
		> ####execute, executeUpdate, executeQuery 차이
		> - execute: 수행 결과로 Boolean 타입의 값을 반환, 수행 결과가 ResultSet일 시 true, 아닐 시 false 반환. 모든 구문 수행 가능
		> - executeUpdate: 수행 결과로 int 값 반환, SELECT를 제외한 다른 구문을 수행할 때 사용하며, 쿼리가 적용 된 행의 수를 반환, CREATE/DROP 구문에서는 -1을 반환
		> - executeQuery: 수행 결과로 ResultSet을 반환, SELECT 수행에 사용

	- Query의 수행 이후에는 반환된 ResultSet, int, boolean 등이 반환되며 수행 이후 Connection 해제가 필수(Resource Leaks 방지)
		- 자원의 해제는 할당의 역순으로 진행되며, ResultSet, Statement, Connection의 순서로 해제
		- 해제를 보장하기 위해 finally로 해제를 강제하며, 해제 도중 예외가 발생할 수 있어 각 해제 단계를 try-catch로 각각 예외 처리
	
	- ResultSet 인터페이스는 Query의 레코드 셋을 저장하는 가상 테이블로 테이블 내부의 Row에 접근하기 위한 커서를 가짐
	> next() 메소드로 행을 이동하며, JDBC 1.0과 2.0의 차이가 있는데 JDBC 2.0에서는 ResultSet이 세션을 가지고 Lazy-Loading이 가능
	- ResultSet의 첫 값은 Null, next로 커서를 이동하며 레코드가 존재할 시 true, 아닐 시 false 반환
	- get{Type}(ColumnName) 메서드를 통해 커서가 위치한 레코드의 필드의 값을 추출할 수 있음

