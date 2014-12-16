package ru.fizteh.fivt.students.NikolaiKrivchanskii.storable;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ru.fizteh.fivt.students.NikolaiKrivchanskii.Shell.Parser;
import ru.fizteh.fivt.storage.structured.ColumnFormatException;
import ru.fizteh.fivt.storage.structured.Table;

public class LocalUtils {
	public static List<Object> parseValues(List<String> valuesRepresentation, Table table) 
			throws ColumnFormatException {
        List<Object> values = new ArrayList<>(valuesRepresentation.size() - 1);

        for (int index = 1; index < valuesRepresentation.size(); ++index) {
            Object value = StoreableTypes.parseByClass(valuesRepresentation.get(index),
            		table.getColumnType(index - 1));
            values.add(value);
        }
        return values;
    }

    public static String join(List<?> list) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (final Object listEntry : list) {
            if (!first) {
                sb.append(" ");
            }
            first = false;
            if (listEntry == null) {
                sb.append("null");
            } else {
                sb.append(listEntry.toString());
            }
        }
        return sb.toString();
    }

    public static TableInfo parseCreateCommand(String parameters) throws IllegalArgumentException {
        parameters = parameters.trim();
        String tableName = parameters.split("\\s+")[0];
        parameters = parameters.replaceAll("\\s+", " ");
        int spaceIndex = parameters.indexOf(' ');
        if (spaceIndex == -1) {
            throw new IllegalArgumentException("wrong type (no column types)");
        }
        String columnTypesString = parameters.substring(spaceIndex).replaceAll("\\((.*)\\)", "$1");
        List<String> columnTypes = Parser.parseCommandArgs(columnTypesString);

        TableInfo info = new TableInfo(tableName);
        for (final String columnType : columnTypes) {
            info.addColumn(StoreableTypes.getTypeByName(columnType));
        }

        return info;
    }

    public static List<String> formatColumnTypes(List<Class<?>> columnTypes) {
        List<String> formattedColumnTypes = new ArrayList<String>();
        for (final Class<?> columnType : columnTypes) {
            formattedColumnTypes.add(StoreableTypes.getSimpleName(columnType));
        }
        return formattedColumnTypes;
    }

    public static void checkValue(Object value, Class<?> type) throws ParseException {
        if (value == null) {
            return;
        }
        //String t = StoreableTypes.getSimpleName(type);
        switch (StoreableTypes.getSimpleName(type)) {
            case "String":
                String stringValue = (String) value;
                /*if (checkStringCorrect(stringValue)) 
                    throw new ParseException("value cannot be null", 0);*/   
                break;
            default:
            	break;
        }
    }

    public static boolean checkStringCorrect(String string) {
    	
        return (string.matches("\\s*") || string.split("\\s+").length != 1);
    }

}
