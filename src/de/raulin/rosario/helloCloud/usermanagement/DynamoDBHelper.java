package de.raulin.rosario.helloCloud.usermanagement;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.GetItemRequest;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.PutItemRequest;

public class DynamoDBHelper {

	private final static String ACCOUNT_ID = "";
	private final static String ACCOUNT_KEY = "";
	private final static String DB_SERVER = "http://dynamodb.eu-west-1.amazonaws.com";
	private final static String DB_TABLE = "";
	private final static AmazonDynamoDBClient DB_CLIENT;

	private DynamoDBHelper() {
	}

	static {
		DB_CLIENT = new AmazonDynamoDBClient(new BasicAWSCredentials(
				ACCOUNT_ID, ACCOUNT_KEY));
		DB_CLIENT.setEndpoint(DB_SERVER);
	}

	protected static Map<String, AttributeValue> getUserValues(final User user) {
		final Map<String, AttributeValue> values = new HashMap<String, AttributeValue>();

		values.put("name", new AttributeValue().withS(user.getName()));
		values.put("email", new AttributeValue().withS(user.getEmail()));
		values.put("password",
				new AttributeValue().withB(ByteBuffer.wrap(user.getPassword())));

		return values;
	}

	protected static Map<String, AttributeValue> getAccountInfo(final User user) {
		final GetItemRequest req = new GetItemRequest(DB_TABLE, new Key(
				new AttributeValue().withS(user.getEmail())));
		try {
			return DB_CLIENT.getItem(req).getItem();
		} catch (AmazonServiceException e) {
			return null;
		}
	}

	public static void add(final User user) throws InUseException {
		if (getAccountInfo(user) != null) {
			throw new InUseException();
		} else {
			PutItemRequest req = new PutItemRequest(DB_TABLE,
					getUserValues(user));
			try {
				DB_CLIENT.putItem(req);
			} catch (AmazonServiceException e) {
				throw new InUseException();
			}
		}
	}

	public static void lookup(final User user) throws InvalidLoginException {
		final Map<String, AttributeValue> m = getAccountInfo(user);
		if (m != null) {
			final byte[] password = m.get("password").getB().array();
			if (Arrays.equals(password, user.getPassword()) == false) {
				throw new InvalidLoginException();
			}
		} else {
			throw new InvalidLoginException();
		}
	}

}
