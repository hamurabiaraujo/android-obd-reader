/*
 * TODO put header
 */
package eu.lighthouselabs.obd.commands;

import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import eu.lighthouselabs.obd.commands.engine.ThrottleObdCommand;

/**
 * Tests for ThrottleObdCommand class.
 */
@PrepareForTest(InputStream.class)
public class ThrottleObdCommandTest {
	private ThrottleObdCommand command;
	private InputStream mockIn;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public void setUp() throws Exception {
		command = new ThrottleObdCommand();
	}

	/**
	 * Test for valid InputStream read, maximum value of 100%
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMaxThrottlePositionValue() throws IOException {
		// mock InputStream read
		mockIn = createMock(InputStream.class);
		mockIn.read();
		expectLastCall().andReturn(0x41);
		expectLastCall().andReturn(0x11);
		expectLastCall().andReturn(0xFF);
		expectLastCall().andReturn(0x0D);
		expectLastCall().andReturn(0x3E); // '>'

		replayAll();

		// call the method to test
		command.readResult(mockIn);
		assertEquals(command.getFormattedResult(), "100.0%");

		verifyAll();
	}

	/**
	 * Test for valid InputStream read, 58.4%
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSomeThrottlePositionValue() throws IOException {
		// mock InputStream read
		mockIn = createMock(InputStream.class);
		mockIn.read();
		expectLastCall().andReturn(0x41);
		expectLastCall().andReturn(0x11);
		expectLastCall().andReturn(0x95);
		expectLastCall().andReturn(0x0D);
		expectLastCall().andReturn(0x3E); // '>'

		replayAll();

		// call the method to test
		command.readResult(mockIn);
		assertEquals(command.getFormattedResult(), "58.4%");

		verifyAll();
	}

	/**
	 * Test for valid InputStream read, minimum value 0%
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMinThrottlePositionValue() throws IOException {
		// mock InputStream read
		mockIn = createMock(InputStream.class);
		mockIn.read();
		expectLastCall().andReturn(0x41);
		expectLastCall().andReturn(0x11);
		expectLastCall().andReturn(0x00);
		expectLastCall().andReturn(0x0D);
		expectLastCall().andReturn(0x3E); // '>'

		replayAll();

		// call the method to test
		command.readResult(mockIn);
		assertEquals(command.getFormattedResult(), "0.0%");

		verifyAll();
	}

	/**
	 * Clear resources.
	 */
	@AfterClass
	public void tearDown() {
		command = null;
		mockIn = null;
	}

}