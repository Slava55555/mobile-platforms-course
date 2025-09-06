package test;

import org.junit.jupiter.api.Test;
import src.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMain_OutputMatchesExpectedPattern() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(outputStream));
            Main.main(new String[]{});
            String output = outputStream.toString().trim();

            assertAll("Output validation",
                    () -> assertFalse(output.isEmpty(), "Should not be empty"),
                    () -> assertTrue(isValidOutput(output), "Should have valid format")
            );

        } finally {
            System.setOut(originalOut);
        }
    }

    private boolean isValidOutput(String output) {
        if ("Password not found".equals(output)) {
            return true;
        }
        return output.matches("^[a-zA-Z]+[0-9]{1,4}$");
    }
}