package app.youngmon.project1.user.repository;

import app.youngmon.project1.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user VALUES( #{id}, #{userName}, #{userId}, #{userPw} )")
    Long save(User user);
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Long id);
}
