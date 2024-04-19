package com.grizz.inventoryapp.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class ArchUnitTest {
    private final JavaClasses targetClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.grizz.inventoryapp.inventory");

    private final Architectures.LayeredArchitecture baseRule = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer("Controller").definedBy("..controller..")
            .layer("Service").definedBy("..service..")
            .layer("Repository").definedBy("..repository..");

    @DisplayName("Controller 레이어는 Service 레이어에만 의존한다")
    @Test
    void test1() {
        final ArchRule rule = baseRule
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Controller").mayOnlyAccessLayers("Service");

        rule.check(targetClasses);
    }

    @DisplayName("Service 레이어는 그 어떤 레이어에 의존하지 않는다")
    @Test
    void test2() {
        final ArchRule rule = baseRule
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Repository")
                .whereLayer("Service").mayNotAccessAnyLayer();

        rule.check(targetClasses);
    }

    @DisplayName("Repository 레이어는 Service 레이어에만 의존한다")
    @Test
    void test3() {
        final ArchRule rule = baseRule
                .whereLayer("Repository").mayNotBeAccessedByAnyLayer()
                .whereLayer("Repository").mayOnlyAccessLayers("Service");

        rule.check(targetClasses);
    }
}