package com.example.shuttleapi.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long>
{
    Optional<AppUser> findByEmail(String email);

    AppUser findByEmailAndPassword(String email, String password);

    @Query("select a from AppUser a where a.email = ?1")
    AppUser getAppUserByEmail(String email);

    @Query("select a.enabled from AppUser a where a.email = ?1")
    boolean isEnabled(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.locked = TRUE WHERE a.email = ?1")
    int lockAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.locked = FALSE WHERE a.email = ?1")
    int unlockAppUser(String email);
}
