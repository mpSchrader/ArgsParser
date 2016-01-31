package de.arguments.json;

import static org.junit.Assert.*;

import org.junit.Test;

import de.arguments.exceptions.JSONException;

public class JSONArrayTest {

	@Test
	public void constructorFromString() throws JSONException {
		JSONArray obj = new JSONArray("[myValue,234, true]");

		JSONArray obj2 = new JSONArray();
		obj2.append("myValue");
		obj2.append(new Integer(234));
		obj2.append(true);
		
		assertEquals(obj2,obj);
	}
	
	@Test
	public void constructorFromStringEscaped() throws JSONException {
		JSONArray obj = new JSONArray("[\"myValue, 234\", true]");

		JSONArray obj2 = new JSONArray();
		obj2.append("\"myValue, 234\"");
		obj2.append(true);

		assertEquals(obj.toString(),obj2.toString());
	}
	
	@Test
	public void constructorFromStringWithJSONObject() throws JSONException {
		JSONArray obj = new JSONArray("[{\"myValue\" : 234, \"test\" : \"myvalue\"}, true]");

		JSONArray obj2 = new JSONArray();
		JSONObject innerObj = new JSONObject("myValue",234);
		innerObj.putString("test","myvalue");
		obj2.append(innerObj);
		obj2.append(true);

		assertEquals(obj,obj2);
	}
	
	@Test
	public void constructorFromStringWithJSONObjects() throws JSONException {
		JSONArray obj = new JSONArray("[{\"a\" : 123 , \"test\" : \"myvalue\"}, {\"a\" : 123 , \"test\" : \"myvalue\"}, true]");

		JSONArray obj2 = new JSONArray();
		JSONObject innerObj = new JSONObject("a",123);
		innerObj.putString("test","myvalue");
		obj2.append(innerObj);
		obj2.append(innerObj);
		obj2.append(true);

		for (int i = 0; i < obj.length(); i++){
			System.out.println(obj.get(i));
		}
		
		assertEquals(obj,obj2);
	}
	
	@Test
	public void constructorFromStringWithJSONArray() throws JSONException {
		JSONArray obj = new JSONArray("[[myValue, 234], true]");

		JSONArray obj2 = new JSONArray();
		JSONArray inner = new JSONArray();
		inner.append("myValue");
		inner.append(234);
		obj2.append(inner);
		obj2.append(true);

		assertEquals(obj,obj2);
	}
	
	@Test(expected = JSONException.class)
	public void constructorFromStringBadFormatStart() throws JSONException {
		new JSONArray("myValue, 234, true]");
	}
	
	@Test(expected = JSONException.class)
	public void constructorFromStringBadFormatEnd() throws JSONException {
		new JSONArray("[myValue, 234, true");
	}
	
	@Test
	public void equlas() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append("myValue");

		JSONArray obj2 = new JSONArray();
		obj2.append("myValue");

		assertTrue(obj.equals(obj2));
	}

	@Test
	public void length() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append("myValue");
		obj.append("myValue");
		obj.append(new JSONArray());

		int expected = 3;
		int actual = obj.length();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_toString() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append("myValue");
		obj.append(1.234);
		obj.append(234);

		String expected = "[myValue, 1.234, 234]";
		String actual = obj.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_toStringEmpty() throws JSONException {
		JSONArray obj = new JSONArray();

		String expected = "[]";
		String actual = obj.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void test_hashCodeNotSame() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append("myValue");

		JSONArray obj2 = new JSONArray();
		obj2.append("myValues");

		assertNotEquals(obj.hashCode(), obj2.hashCode());
	}

	@Test
	public void test_hashCode() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append("myValue");

		JSONArray obj2 = new JSONArray();
		obj2.append("myValue");

		assertEquals(obj.hashCode(), obj2.hashCode());
	}

	@Test
	public void append_get_String() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append("myValue");

		String actual = obj.getString(0);
		String expected = "myValue";

		assertEquals(expected, actual);
	}
	
	@Test
	public void append_get_StringBlanks() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append("\"max, peter\"");

		String actual = obj.getString(0);
		String expected = "\"max, peter\"";

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideString() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append("myValue");
		obj.append("new Value");

		String actual = obj.getString(0);
		String expected = "myValue";

		assertEquals(expected, actual);

		actual = obj.getString(1);
		expected = "new Value";

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putIndexOutOfBoundString() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append("myValue");

		obj.getString(1);

	}

	@Test
	public void append_get_Integer() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(1234);

		Integer actual = obj.getInteger(0);
		Integer expected = 1234;

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideInteger() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(3);
		obj.append(5);

		Integer actual = obj.getInteger(0);
		Integer expected = 3;

		assertEquals(expected, actual);

		actual = obj.getInteger(1);
		expected = 5;

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putIndexOutOfBoundInteger() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(123);

		obj.getInteger(1);

	}

	@Test
	public void append_get_Double() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(1234.2);

		Double actual = obj.getDouble(0);
		Double expected = 1234.2;

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideDouble() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(3.2);
		obj.append(5.5);

		Double actual = obj.getDouble(0);
		Double expected = 3.2;

		assertEquals(expected, actual);

		actual = obj.getDouble(1);
		expected = 5.5;

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putIndexOutOfBoundDouble() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(123.4);

		obj.getDouble(1);

	}

	@Test
	public void append_get_JSONObject() throws JSONException {
		JSONArray obj = new JSONArray();
		JSONObject input = new JSONObject("with", "content");
		obj.append(input);

		JSONObject actual = obj.getJSONObject(0);
		JSONObject expected = input;

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideJSONObject() throws JSONException {
		JSONArray obj = new JSONArray();
		JSONObject input = new JSONObject("with", "content");
		obj.append(input);
		JSONObject input2 = new JSONObject("with", "other");
		obj.append(input2);

		JSONObject actual = obj.getJSONObject(0);
		JSONObject expected = input;

		assertEquals(expected, actual);

		actual = obj.getJSONObject(1);
		expected = input2;

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putIndexOutOfBoundJSONObject() throws JSONException {
		JSONArray obj = new JSONArray();
		JSONObject input = new JSONObject("with", "content");
		obj.append(input);

		obj.getJSONObject(1);

	}

	@Test
	public void append_get_JSONArray() throws JSONException {
		JSONArray obj = new JSONArray();
		JSONArray input = new JSONArray();
		input.append(123);
		obj.append(input);

		JSONArray actual = obj.getJSONArray(0);
		JSONArray expected = input;

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideJSONArray() throws JSONException {
		JSONArray obj = new JSONArray();
		JSONArray input = new JSONArray();
		input.append(123);
		obj.append(input);

		JSONArray input2 = new JSONArray();
		input2.append("MyString");
		obj.append(input2);

		JSONArray actual = obj.getJSONArray(0);
		JSONArray expected = input;

		assertEquals(expected, actual);

		actual = obj.getJSONArray(1);
		expected = input2;

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putIndexOutOfBoundJSONArray() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(new JSONArray());

		obj.getInteger(1);

	}

	@Test
	public void append_get_Boolean() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(true);

		Boolean actual = obj.getBoolean(0);
		Boolean expected = true;

		assertEquals(expected, actual);
	}

	@Test
	public void putOverrideBoolean() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(false);
		obj.append(true);

		Boolean actual = obj.getBoolean(0);
		Boolean expected = false;

		assertEquals(expected, actual);

		actual = obj.getBoolean(1);
		expected = true;

		assertEquals(expected, actual);
	}

	@Test(expected = JSONException.class)
	public void putIndexOutOfBoundBoolean() throws JSONException {
		JSONArray obj = new JSONArray();
		obj.append(true);

		obj.getInteger(1);

	}

}
