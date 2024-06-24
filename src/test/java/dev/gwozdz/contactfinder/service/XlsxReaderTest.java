package dev.gwozdz.contactfinder.service;

import dev.gwozdz.contactfinder.components.utils.XlsxReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class XlsxReaderTest {


    private final String fileName = "src/test/resources/small-lista-gmin.xlsx";
    XlsxReader reader = new XlsxReader();

    @Test
    void shouldReadXlsxFile() {

        assertDoesNotThrow(() -> reader.readXlsxFile(fileName));
    }

    @Test
    void shouldReadAllRows() throws IOException {
        Map<Integer, List<String>> result = reader.readXlsxFile(fileName);
        assertEquals(3, result.keySet().size());
    }

    @Test
    void shouldReadAllColumns() throws IOException {
        Map<Integer, List<String>> result = reader.readXlsxFile(fileName);
        assertEquals(5, result.get(1).size());
    }
}
