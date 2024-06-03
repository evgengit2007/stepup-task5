package ru.stepup.course2.stepuptask5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.course2.stepuptask5.entity.AccountPool;

public interface AccountPoolRepo extends JpaRepository<AccountPool, Long> {
    AccountPool findFirstByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
            String branchCode,
            String currencyCode,
            String mdmCode,
            String priorityCode,
            String registryTypeCode
    );
}
