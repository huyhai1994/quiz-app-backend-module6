package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.UserWithApprovalsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query(value = "CALL  getuserswithapprovals()", nativeQuery = true)
    List<UserWithApprovalsProjection> getUsersWithApprovals();

}
