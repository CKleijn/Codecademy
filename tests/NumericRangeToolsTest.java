package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.Validation;

public class NumericRangeToolsTest {
    /**
    * @subcontract value within valid range {
    * @requires 0 <= percentage <= 100;
    * @ensures \result = true;
    * }
    **/

    @Test
    public void testIsValidPercentageRequiresPercentage50EnsuresTrue() {
        // Arrange
        int percentage = 50;
        // Act
        boolean result = Validation.percentage(percentage);
        // Assert
        assertEquals(true, result);
    }

    /**
    * @subcontract value within valid range {
    * @requires 0 <= percentage <= 100;
    * @ensures \result = true;
    * }
    **/

    @Test
    public void testIsValidPercentageRequiresPercentage0EnsuresTrue() {
        // Arrange
        int percentage = 0;
        // Act
        boolean result = Validation.percentage(percentage);
        // Assert
        assertEquals(true, result);
    }

    /**
    * @subcontract value within valid range {
    * @requires 0 <= percentage <= 100;
    * @ensures \result = true;
    * }
    **/

    @Test
    public void testIsValidPercentageRequiresPercentage100EnsuresTrue() {
        // Arrange
        int percentage = 100;
        // Act
        boolean result = Validation.percentage(percentage);
        // Assert
        assertEquals(true, result);
    }

    /**
    * @subcontract value out of range low {
    * @requires percentage < 0;
    * @ensures \result = false;
    * }
    **/

    @Test
    public void testIsValidPercentageRequiresPercentageMinus1EnsuresFalse() {
        // Arrange
        int percentage = -1;
        // Act
        boolean result = Validation.percentage(percentage);
        // Assert
        assertEquals(false, result);
    }

    /**
    * @subcontract value out of range high {
    * @requires percentage > 100;
    * @signals \result = false;
    * }
    **/

    @Test
    public void testIsValidPercentageRequiresPercentage101EnsuresFalse() {
        // Arrange
        int percentage = 101;
        // Act
        boolean result = Validation.percentage(percentage);
        // Assert
        assertEquals(false, result);
    }
}
