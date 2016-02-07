package de.arguments.json;

import static org.junit.Assert.*;

import org.junit.Test;

import de.arguments.exceptions.JSONException;

public class JSONObjectTest {

	@Test
	public void constructorWithValue() throws JSONException {
		JSONObject obj = new JSONObject("key", "myValue");

		JSONObject obj2 = new JSONObject();
		obj2.putString("key", "myValue");

		assertTrue(obj.equals(obj2));
	}

	@Test
	public void constructorFromString() throws JSONException {
		JSONObject obj = new JSONObject(
				"{\"a\" : true, \"key\" : \"myValue\", \"number\" : 234}");

		JSONObject obj2 = new JSONObject();
		obj2.putString("key", "myValue");
		obj2.putInteger("number", 234);
		obj2.putBoolean("a", true);

		assertEquals(obj, obj2);
	}

	@Test
	public void constructorFromStringSmall() throws JSONException {
		JSONObject obj = new JSONObject("{\"myValue\" : 234}");

		JSONObject obj2 = new JSONObject();
		obj2.putInteger("myValue", 234);

		assertEquals(obj, obj2);
	}

	@Test
	public void constructorFromStringComplex() throws JSONException {
		JSONObject obj = new JSONObject(
				"{\"a\" : {\"a\" : 123, \"test\" : \"myvalue\"}, \"key\" : [{\"a\" : 123 , \"test\" : \"myvalue\"}, true], \"number\" : 234}");

		JSONObject obj2 = new JSONObject();
		obj2.putInteger("number", 234);
		JSONObject innerObj = new JSONObject("a", 123);
		innerObj.putString("test", "myvalue");
		obj2.putJSONObject("a", innerObj);
		JSONArray inner = new JSONArray();
		inner.append(innerObj);
		inner.append(true);
		obj2.putJSONArray("key", inner);

		assertEquals(obj, obj2);
	}

	@Test
	public void constructorFromStringComplex_2() throws JSONException {
		JSONObject obj = new JSONObject(
				"{\"a\" : {\"a\" : 123, \"test\" : \"myvalue\"}, \"key\" : [{\"a\" : 123 , \"test\" : \"myvalue\"}, {\"a\" : 123 , \"test\" : \"myvalue\"}, true], \"number\" : 234}");

		JSONObject obj2 = new JSONObject();
		obj2.putInteger("number", 234);

		JSONObject innerObj = new JSONObject("a", 123);
		innerObj.putString("test", "myvalue");
		obj2.putJSONObject("a", innerObj);

		JSONArray inner = new JSONArray();
		inner.append(innerObj);
		inner.append(innerObj);
		inner.append(true);
		obj2.putJSONArray("key", inner);

		assertEquals(obj, obj2);
	}

	@Test(expected = JSONException.class)
	public void constructorFromStringBadFormatStart() throws JSONException {
		new JSONObject("myValue, 234, true}");
	}

	@Test(expected = JSONException.class)
	public void constructorFromStringBadFormatEnd() throws JSONException {
		new JSONObject("{myValue, 234, true");
	}

	@Test
	public void equlas() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "myValue");

		JSONObject obj2 = new JSONObject();
		obj2.putString("key", "myValue");

		assertTrue(obj.equals(obj2));
	}

	@Test
	public void equlas2() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putInteger("number", 234);
		JSONObject innerObj = new JSONObject("a", 123);
		innerObj.putString("test", "myvalue");
		obj.putJSONObject("a", innerObj);
		JSONArray inner = new JSONArray();
		inner.append(innerObj);
		inner.append(innerObj);
		inner.append(true);
		obj.putJSONArray("key", inner);

		JSONObject obj2 = new JSONObject();
		obj2.putInteger("number", 234);
		obj2.putJSONObject("a", innerObj);
		obj2.putJSONArray("key", inner);

		assertTrue(obj.equals(obj2));
	}

	@Test
	public void equlas3() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putInteger("number", 234);
		JSONObject innerObj = new JSONObject("a", 123);
		innerObj.putString("test", "myvalue");
		obj.putJSONObject("a", innerObj);

		JSONObject obj2 = new JSONObject();
		obj2.putInteger("number", 234);
		obj2.putJSONObject("a", innerObj);

		assertTrue(obj.equals(obj2));
	}

	@Test
	public void equlas4() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putInteger("number", 234);
		JSONObject innerObj = new JSONObject("a", 123);
		innerObj.putString("test", "myvalue");
		obj.putJSONObject("a", innerObj);
		JSONArray inner = new JSONArray();
		inner.append(innerObj);
		inner.append(true);
		obj.putJSONArray("key", inner);

		JSONObject obj2 = new JSONObject();
		obj2.putInteger("number", 234);
		obj2.putJSONObject("a", innerObj);
		obj2.putJSONArray("key", inner);

		assertTrue(obj.equals(obj2));
	}

	@Test
	public void equlasNot() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "myValue");

		JSONObject obj2 = new JSONObject();
		obj2.putString("key", "myValue");
		obj2.putString("id", "myValue");

		assertFalse(obj.equals(obj2));
	}

	@Test
	public void equlasNot2() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "myValue");

		JSONObject obj2 = new JSONObject();
		obj2.putString("key", "myValues");

		assertFalse(obj.equals(obj2));
	}

	@Test
	public void test_toString() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "myValue");
		obj.putInteger("number", 234);
		obj.putBoolean("a", true);

		String actual = obj.toString();
		String expected = "{\"a\" : true, \"key\" : \"myValue\", \"number\" : 234}";

		assertEquals(expected, actual);
	}

	@Test
	public void test_toString_Empty() throws JSONException {
		JSONObject obj = new JSONObject();

		String actual = obj.toString();
		String expected = "{}";

		assertEquals(expected, actual);
	}

	@Test
	public void test_hashCode() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "myValue");

		JSONObject obj2 = new JSONObject();
		obj2.putString("key", "myValue");

		assertEquals(obj.hashCode(), obj2.hashCode());
	}

	@Test
	public void test_hashCodeNotSame() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "myValue");

		JSONObject obj2 = new JSONObject();
		obj2.putString("key", "myValues");

		assertNotEquals(obj.hashCode(), obj2.hashCode());
	}

	@Test
	public void put_get_String() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "myValue");

		String actual = obj.getString("key");
		String expected = "myValue";

		assertEquals(expected, actual);
	}

	@Test
	public void put_get_StringBlank() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "\"myValue, 234\"");

		String actual = obj.getString("key");
		String expected = "\"myValue, 234\"";

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideString() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "myValue");
		obj.putString("key", "new Value");

		String actual = obj.getString("key");
		String expected = "new Value";

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putNoKeyString() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "myValue");

		obj.getString("notAKey");

	}

	@Test
	public void put_get_Integer() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putInteger("key", 1234);

		int actual = obj.getInteger("key");
		int expected = 1234;

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideInteger() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putInteger("key", 1234);
		obj.putInteger("key", 4321);

		int actual = obj.getInteger("key");
		int expected = 4321;

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putNoKeyInteger() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "1234");

		obj.getInteger("key");

	}

	@Test
	public void put_get_Double() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putDouble("key", 1234.4321);

		Double actual = obj.getDouble("key");
		Double expected = 1234.4321;

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideDouble() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putDouble("key", 1234.0);
		obj.putDouble("key", 43.21);

		Double actual = obj.getDouble("key");
		Double expected = 43.21;

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putNoKeyDouble() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "1234");

		obj.getDouble("key");

	}

	@Test
	public void put_get_JSONObject() throws JSONException {
		JSONObject obj = new JSONObject();

		JSONObject input = new JSONObject();
		input.putDouble("test", 12.34);

		obj.putJSONObject("key", input);

		JSONObject actual = obj.getJSONObject("key");
		JSONObject expected = input;

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideJSONObject() throws JSONException {
		JSONObject obj = new JSONObject();

		JSONObject input = new JSONObject();
		input.putDouble("test", 12.34);
		obj.putJSONObject("key", input);

		JSONObject override = new JSONObject();
		override.putDouble("test", 34.21);
		obj.putJSONObject("key", override);

		JSONObject actual = obj.getJSONObject("key");
		JSONObject expected = override;

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putNoKeyJSONObject() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putString("key", "1234");

		obj.getJSONObject("key");

	}

	@Test
	public void put_get_Boolean() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putBoolean("key", true);

		Boolean actual = obj.getBoolean("key");

		assertTrue(actual);
	}

	@Test
	public void putOverrideBoolean() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putBoolean("key", false);

		obj.putBoolean("key", true);

		Boolean actual = obj.getBoolean("key");
		assertTrue(actual);
	}

	@Test(expected = JSONException.class)
	public void putNoKeyBoolean() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.putBoolean("key", false);

		obj.getJSONObject("key");

	}
}
