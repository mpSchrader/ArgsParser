package de.arguments.examples;

import static org.junit.Assert.*;

import java.security.Permission;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.example.BasicExample;
import de.example.FileExample;

public class ExamplesTest {

	/**
	 * Class needed to check status codes of main methods
	 * 
	 * @author Max-Philipp Schrader
	 *
	 */
	protected static class ExitException extends SecurityException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public final int status;

		public ExitException(int status) {
			super("There is no escape!");
			this.status = status;
		}
	}

	/**
	 * Class throw ExitException on every System.exit();
	 * 
	 * @author Max-Philipp Schrader
	 *
	 */
	private static class NoExitSecurityManager extends SecurityManager {
		@Override
		public void checkPermission(Permission perm) {
		}

		@Override
		public void checkPermission(Permission perm, Object context) {
		}

		@Override
		public void checkExit(int status) {
			super.checkExit(status);
			throw new ExitException(status);
		}
	}

	@Before
	public void setUp() throws Exception {
		System.setSecurityManager(new NoExitSecurityManager());
	}

	@After
	public void tearDown() {
		System.setSecurityManager(null);
	}

	@Test
	public void simpleExample() {
		try {

			String[] args = { "-t", "4", "--name", "max" };
			BasicExample.main(args);

		} catch (ExitException e) {
			assertEquals(1, e.status);
		}
	}

	@Test
	public void simpleExampleException() {
		try {

			String[] args = { "-t", "4", "max" };
			BasicExample.main(args);

		} catch (ExitException e) {
			assertEquals(1, e.status);
		}
	}

	@Test
	public void fileExample() {
		try {

			String[] args = { "-t", "4", "--name", "max" };
			FileExample.main(args);

		} catch (ExitException e) {
			assertEquals(0, e.status);
		}
	}

	@Test
	public void fileExampleException() {
		try {

			String[] args = { "" };
			FileExample.main(args);

		} catch (ExitException e) {
			assertEquals(1, e.status);
		}
	}

}
