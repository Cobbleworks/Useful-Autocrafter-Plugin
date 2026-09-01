package com.usefulautocrafters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AutocrafterListenerTest {

    @Test
    void blocksAutomatedExtractionFromCrafterWhileEnabled() {
        assertTrue(AutocrafterListener.shouldBlockAutomatedExtraction(true, true));
    }

    @Test
    void allowsCrafterExtractionWhileDisabled() {
        assertFalse(AutocrafterListener.shouldBlockAutomatedExtraction(false, true));
    }

    @Test
    void leavesOtherSourceInventoriesUnchanged() {
        assertFalse(AutocrafterListener.shouldBlockAutomatedExtraction(true, false));
    }
}
