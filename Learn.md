# 하면서 공부한거 대충

1. Spring Boot <-> Spring FrameWork
	- Spring Framework
		- 객체 지향적인 '어플리케이션' 개발을 위한 자바 오픈소스 프레임워크
		- 단순 웹 프레임워크 X, POJO, DI, AOP를 중심으로 설계된 컨테이너로 의존성 관리
		- 스프링 클라우드, 시큐리티, 웹, 데이터 등 개발을 돕는 스프링 환경의 핵심
	
	- Spring Boot
		- 스프링 개발환경에 필요한 의존성, 구성을 CoC 원칙에 따라 관례화해놓음
		- 반복적인 구성을 기본값으로 사용해 코드 작성에만 집중 가능하지만, 불필요한 구성으로 프로젝트 크기가 필요 이상으로 커질 수 있음
		- 내장 서버로 단독 실행이 가능한 어플리케이션 구축 가능
		
1. Initializer 구성 요소
	1) Maven vs Gradle
		- Build tool : 프로젝트 빌드, 테스트, 배포 등의 작업을 위한 도구
			- 크게 봤을 때 Make가 소스코드의 오브젝트 파일 생성, 링크(의존성 관리), 빌드 하는 것과 같음
		- 초기 자바 빌더로 Ant가 있었지만 필요 스크립트 작성이 많고, 라이브러리간의 의존성 관리가 불편
		
		- 공통점 : Ant, Maven, Gradle 전부 프로젝트 전체의 라이프 사이클을 관리

		- Maven : Ant의 프로젝트 빌드 + 프로젝트 관리 기능이 추가됨
			- 컴파일 -> 테스트 -> 패키징
			- pom.xml(Project Object Model)을 통해 프로젝트 구성을 객체로 관리
			- 정적 포맷인 xml을 통해 프로젝트 정보, 빌드 설정, 빌드 환경(프로파일), 의존성 관리
			- Maven Repository를 통해 필요 라이브러리를 네트워크로 관리 가능

		- Gradle : Groovy 스크립트를 통한 프로젝트 관리, 구성, 테스트, 배포 툴
			- 초기화 -> 작업 구성(의존성 트리) -> 실행
			- Groovy : JVM으로 실행 가능한 스크립트 언어, Java와 달리 컴파일 필요 X
			- Gradle Build Script를 통해 구성에 필요한 PATH, 조건분기를 동적으로 관리 가능
			- 기본 Repository는 Maven Repository, gradle.setting에 레포 추가 가능

		! Maven은 외부 구성을 '상속'으로 관리, Gradle은 '주입'을 통해 비 의존적으로 관리
		! Gradle은 자체 캐싱으로 변경된 부분만 관리하고 멀티스레딩 가능-> Maven보다 빠름

		- 로컬에서 캐시를 관리하기 때문에, 여러 프로젝트 진행 시 문제 발생 가능함.(아직 부정확)
			- ./gradlew cleanBuildCache 로 캐시 지울 수 있음

	1) Java vs Kotlin vs Groovy
		- 프로젝트 언어, 개취
	1) Versions
		- M1,2,3 은 마일스톤 빌드, 완전하지 않은 기능이 포함됨. 스냅샷보다는 안정적이지만 Unstable
	1) Group
		- 주로 도메인 네임 사용, 이름 뭐로 할까
	1) Artifact
	1) Name
	1) Description
		- 프로젝트 설명
	1) Package name
	1) Packaging : 배포 방법
		- JAR : Java ARchive
			- 어플리케이션이 동작하도록 프로젝트를 압축, CLASS 파일, 라이브러리 파일 포함
			- JRE로 실행 가능
			- BOOT-INF, META-INF, org로 구성
				- BOOT-INF : 개발자가 작성한 클래스, 라이브러리의 jar과 리스트로 구성
				- META-INF : 시작 클래스, 클래스 목록, 버전 등의 메타데이터
				- org : 스프링 부트 로더의 클래스들이 들어있음(import org.spring{...}.* )

		- WAR : Web Application aRchive
			- Servlet, Jsp 컨테이너에 배치 가능한 웹 어플리케이션 압축포맷
			- JSP, Servlet, JAR, Class, XML 등등 리소스 포함
			- 별도 웹서버, WAS 필요 -> 외부 JSP나 WAS를 사용할 예정이라면 WAR로 구성해야함
			- BOOT_INF의 이름이 WEB-INF

	1) Java ver
		- 자바 버전, 프로젝트에서는 지원 기간, 안정성 고려해서 11로 선택

1. H2 DB : 자바 임베디드 데이터베이스, 작은 용량이며 사용이 간단해 개발 환경에 씀
	- 메모리 모드 동작 시 동시 커넥션 불가하지만, DB 구동 없이 스키마만 사용 가능(휘발성)
	- web-console의 사용 가능 여부를 통해, 서버 실행없이 톰캣 서버로 콘솔에 접근 가능

1. yaml vs properties
	- properties 파일이 스프링 생성시에 자동으로 생기지만, 가독성이 낮음
	- 설정을 yml로 관리하면 설정을 계층 구조로 관리하고, 한 파일에 여러 옵션을 분리해서 넣을 수 있음
	- 더 자세한 사용 법 찾는 중, @Configuration 찾는 중인데 알면 알려주셈
	- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.properties-and-configuration.expand-properties.gradle

1. 회원 도메인
	1) 
