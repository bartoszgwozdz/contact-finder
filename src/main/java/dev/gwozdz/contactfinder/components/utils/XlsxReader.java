package dev.gwozdz.contactfinder.components.utils;

import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class XlsxReader {

    public Map<Integer, List<String>> readXlsxFile(String fileName) throws IOException {
        Map<Integer, List<String>> output = new HashMap<>();
        try (FileInputStream file = new FileInputStream(fileName); ReadableWorkbook wb = new ReadableWorkbook(file)){
            Sheet sheet = wb.getFirstSheet();
            try (Stream<Row> rows = sheet.openStream()){
                rows.filter(r -> r.getCellCount() > 0)
                        .forEach(r -> {
                    output.put(r.getRowNum(), new ArrayList<>());
                    for (Cell cell : r) {
                        output.get(r.getRowNum()).add(cell.getRawValue());
                    }
                });
            }
        }
        return output;
    }
}
