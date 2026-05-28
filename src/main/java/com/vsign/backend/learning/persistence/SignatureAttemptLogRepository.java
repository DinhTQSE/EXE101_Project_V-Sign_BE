package com.vsign.backend.learning.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureAttemptLogRepository extends JpaRepository<SignatureAttemptLogEntity, String> {
}
