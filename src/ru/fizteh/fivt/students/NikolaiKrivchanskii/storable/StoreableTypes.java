package ru.fizteh.fivt.students.NikolaiKrivchanskii.storable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum StoreableTypes {
	
	INTEGER("int", Integer.class) {
		public Object parseValue(String arg) {
			return Integer.parseInt(arg);
		}
	},
	
	LONG("long", Long.class) {
		public Object parseValue(String arg) {
			return Long.parseLong(arg);
		}
	},
	
	FLOAT("float", Float.class) {
		public Object parseValue(String arg) {
			return Float.parseFloat(arg);
		}
	},
	
	DOUBLE("double", Double.class) {
		public Object parseValue(String arg) {
			return Double.parseDouble(arg);
		}
	},
	
	STRING("String", String.class) {
		public Object parseValue(String arg) {
			return arg;
		}
	},
	
	BYTE("byte", Byte.class) {
		public Object parseValue(String arg) {
			return Byte.parseByte(arg);
		}
	},
	
	BOOLEAN("boolean", Boolean.class) {
		public Object parseValue(String arg) {
			return Boolean.parseBoolean(arg);
		}
	};
	
	private final String name;
	private final Class<?> type;
	
	private StoreableTypes(String name, Class<?> type) {
		this.name = name;
		this.type = type;
	}
	
	private static final Map<String, StoreableTypes> TypesByName;
	private static final Map<Class<?>, StoreableTypes> TypesByClass;
	
	static {
		HashMap<String, StoreableTypes> tempByName = new HashMap<>();
		HashMap<Class<?>, StoreableTypes> tempByClass = new HashMap<>();
		for (StoreableTypes value : values()) {
			tempByName.put(value.name, value);
			tempByClass.put(value.type, value);
		}
		TypesByName = Collections.unmodifiableMap(tempByName);
		TypesByClass = Collections.unmodifiableMap(tempByClass);
	}
	public static Class<?> getTypeByName(String name) {
		StoreableTypes formatter = TypesByName.get(name);
		if (formatter == null) {
			throw new IllegalArgumentException("wrong type (" + name + ')');
		}
		return formatter.type;
	}
	
	public static String getSimpleName(Class<?> type) {
		StoreableTypes formatter = TypesByClass.get(type);
		if (formatter == null) {
			throw new IllegalArgumentException("unknown format");
		}
		return formatter.name;
	}
	
	public abstract Object parseValue(String string);
	
	public static Object parseByClass(String string, Class<?> type) {
		StoreableTypes formatter = TypesByClass.get(type);
		if (formatter == null) {
			throw new IllegalArgumentException("wrong type (" + type + ')');
		}
		return formatter.parseValue(string);
	}
} 
