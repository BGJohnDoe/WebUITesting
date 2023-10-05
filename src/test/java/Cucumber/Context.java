package Cucumber;

import com.google.common.base.Preconditions;
import io.cucumber.java.Scenario;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Set;

public class Context {
	private static final HashMap<String, Object> STORAGE = new HashMap<>();

	private Context() {
	}

	public static Scenario getScenario() {
		return scenario;
	}

	private static Scenario scenario;

	public static void prepare(Scenario scenario) {
		Context.scenario = scenario;
		clearStorage();
	}

	public static Set<String> getStoredKeys() {
		return STORAGE.keySet();
	}

	public static void put(@Nonnull String name, Object val) {
		STORAGE.put(name, val);
	}

	private static Object get(String name) {
		Preconditions.checkState(STORAGE.containsKey(name), "'%s' is absent in the storage (save it first)", name);
		return STORAGE.get(name);
	}

	public static void clearStorage() {
		STORAGE.clear();
	}
}
