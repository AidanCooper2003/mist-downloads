package edu.iu.c322.mist.downloads.repository;

import edu.iu.c322.mist.downloads.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListRepository extends JpaRepository<CustomerProfile, Integer> {
}
