# DtoDao
**DAO** 

DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 객체

**DTO** (= **VO** : VO 는 read only) 

계층(컨트롤러~뷰..)간 데이터 교환을 위한 자바빈즈. DTO 에는 로직이 없고 getter, setter 메소드만 가진 클래스를 말함.

##

JdbcUtil : db와 자바 연동 클래스

MemberDao : DAO 클래스

MemberDto : DTO 클래스
