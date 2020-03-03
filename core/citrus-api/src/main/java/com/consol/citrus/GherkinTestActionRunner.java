package com.consol.citrus;

/**
 * Runner adds default alias methods using Gherkin behavior driven development style (GIVEN, WHEN, THEN).
 * @author Christoph Deppisch
 */
public interface GherkinTestActionRunner extends TestActionRunner {

    /**
     * Behavior driven style alias for run method.
     * @param builder
     * @param <T>
     * @return
     */
    default <T extends TestAction> T given(TestActionBuilder<T> builder) {
        return run(builder);
    }

    /**
     * Behavior driven style alias for run method.
     * @param builder
     * @param <T>
     * @return
     */
    default <T extends TestAction> T when(TestActionBuilder<T> builder) {
        return run(builder);
    }

    /**
     * Behavior driven style alias for run method.
     * @param builder
     * @param <T>
     * @return
     */
    default <T extends TestAction> T then(TestActionBuilder<T> builder) {
        return run(builder);
    }

    /**
     * Behavior driven style alias for run method.
     * @param builder
     * @param <T>
     * @return
     */
    default <T extends TestAction> T and(TestActionBuilder<T> builder) {
        return run(builder);
    }
}
