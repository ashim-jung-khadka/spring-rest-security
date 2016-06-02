package com.github.ashim.persistence.repo;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.ashim.persistence.common.config.PersistenceConfig;
import com.github.ashim.persistence.entity.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("userData.xml")
public class ITUserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void search_NoUserEntriesFound_ShouldReturnEmptyList() {
		List<User> userEntries = userRepository.search("NOT FOUND");
		assertThat(userEntries.size(), is(0));
	}

	@Test
	public void search_OneUserEntryFound_ShouldReturnAListOfOneEntry() {
		List<User> userEntries = userRepository.search("Ashim");

		assertThat(userEntries.size(), is(1));
		assertThat(userEntries.get(0), allOf(hasProperty("id", is(1)), hasProperty("firstName", is("Ashim")),
				hasProperty("lastName", is("Khadka"))));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void search_TwoUserEntriesFound_ShouldReturnAListOfTwoEntries() {
		List<User> userEntries = userRepository.search("Khadka");

		assertThat(userEntries.size(), is(2));
		assertThat(userEntries,
				contains(
						allOf(hasProperty("id", is(1)), hasProperty("firstName", is("Ashim")),
								hasProperty("lastName", is("Khadka"))),
						allOf(hasProperty("id", is(2)), hasProperty("firstName", is("Ashish")),
								hasProperty("lastName", is("Khadka")))));
	}

}