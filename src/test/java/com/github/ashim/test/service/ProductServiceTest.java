package com.github.ashim.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.springframework.test.util.ReflectionTestUtils;

import com.github.ashim.persistence.entity.Product;
import com.github.ashim.persistence.impl.ProductServiceImpl;
import com.github.ashim.persistence.repo.ProductRepository;
import com.github.ashim.persistence.service.ProductService;
import com.github.ashim.test.model.ProductTest;

public class ProductServiceTest {

	private ProductService service;

	private ProductRepository repositoryMock;

	@Before
	public void setUp() {
		service = new ProductServiceImpl();

		repositoryMock = mock(ProductRepository.class);
		ReflectionTestUtils.setField(service, "productRepo", repositoryMock);
	}

	@Test
	public void add() {
		Product oldProduct = ProductTest.createModel(false);

		service.insert(oldProduct);

		ArgumentCaptor<Product> productArgument = ArgumentCaptor.forClass(Product.class);
		verify(repositoryMock, times(1)).save(productArgument.capture());
		verifyNoMoreInteractions(repositoryMock);

		Product newProduct = productArgument.getValue();

		assertNull(newProduct.getId());
		assertEquals(oldProduct.getProductName(), newProduct.getProductName());
		assertEquals(oldProduct.getProductCode(), newProduct.getProductCode());
		assertEquals(oldProduct.getDescription(), newProduct.getDescription());
	}

	@Test
	public void deleteById() throws Exception {
		Product model = ProductTest.createModel(true);
		when(repositoryMock.findById(ProductTest.ID)).thenReturn(model);

		Product actual = service.deleteById(ProductTest.ID);

		verify(repositoryMock, times(1)).findById(ProductTest.ID);
		verify(repositoryMock, times(1)).delete(model);
		verifyNoMoreInteractions(repositoryMock);

		assertEquals(model, actual);
	}

	@Test(expected = Exception.class)
	public void deleteByIdWhenToDoIsNotFound() throws Exception {
		when(repositoryMock.findById(ProductTest.ID)).thenReturn(null);

		service.deleteById(ProductTest.ID);

		verify(repositoryMock, times(1)).findById(ProductTest.ID);
		verifyNoMoreInteractions(repositoryMock);
	}

	@Test
	public void findAll() {
		List<Product> models = new ArrayList<Product>();
		when(repositoryMock.findAll()).thenReturn(models);

		List<Product> actual = service.findAll();

		verify(repositoryMock, times(1)).findAll();
		verifyNoMoreInteractions(repositoryMock);

		assertEquals(models, actual);
	}

	@Test
	public void findById() throws Exception {
		Product model = ProductTest.createModel(true);
		when(repositoryMock.findById(ProductTest.ID)).thenReturn(model);

		Product actual = service.findById(ProductTest.ID);

		verify(repositoryMock, times(1)).findById(ProductTest.ID);
		verifyNoMoreInteractions(repositoryMock);

		assertEquals(model, actual);
	}

	@Test(expected = Exception.class)
	public void findByIdWhenToDoIsNotFound() throws Exception {
		when(repositoryMock.findById(ProductTest.ID)).thenReturn(null);

		service.findById(ProductTest.ID);

		verify(repositoryMock, times(1)).findById(ProductTest.ID);
		verifyNoMoreInteractions(repositoryMock);
	}

	@Test
	public void update() throws Exception {
		Product oldProduct = ProductTest.createModel(true);
		Product newProduct = ProductTest.createUpdatedModel();

		when(repositoryMock.findById(newProduct.getId())).thenReturn(oldProduct);
		when(repositoryMock.save(newProduct)).thenReturn(newProduct);

		Product actual = service.update(newProduct);

		verify(repositoryMock, times(1)).findById(newProduct.getId());
		verify(repositoryMock, times(1)).save(newProduct);
		verifyNoMoreInteractions(repositoryMock);

		assertEquals(newProduct.getId(), actual.getId());
		assertEquals(newProduct.getProductName(), actual.getProductName());
		assertEquals(newProduct.getProductCode(), actual.getProductCode());
		assertEquals(newProduct.getDescription(), actual.getDescription());
	}

	@Test(expected = Exception.class)
	public void updateWhenToDoIsNotFound() throws Exception {
		Product dto = ProductTest.createUpdatedModel();
		when(repositoryMock.findById(dto.getId())).thenReturn(null);

		service.update(dto);

		verify(repositoryMock, times(1)).findById(dto.getId());
		verifyNoMoreInteractions(repositoryMock);
	}

	public void test1() {
		List<String> list = new ArrayList<>();
		List<String> spy = spy(list);

		doReturn("test").when(spy).get(0);

		System.out.println(spy.get(0));

		when(spy.get(0)).thenReturn("testing");

		System.out.println(spy.get(0));
	}

	public void test() {
		// create and configure mock
		Product test = mock(Product.class);
		when(test.getId()).thenReturn(1);

		// call method testing on the mock with parameter productName
		test.setProductName("productName");
		test.getId();
		test.getId();

		// now check if method testing was called with the parameter productName
		verify(test).setProductName(Matchers.eq("productName"));

		// was the method called twice?
		verify(test, times(2)).getId();

		// other alternatives for verifiying the number of method calls for a
		// method
		verify(test, never()).setProductName("never called");
		verify(test, atLeastOnce()).setProductName("productName");
		verify(test, atLeast(2)).getId();
	}

}