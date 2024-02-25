package com.cabbooking;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@EntityScan("com.cabbooking")
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class CabBookoningAssignmentApplicationTests {

	/*
	 * @Test public void contextLoads() { // Ensure that the application context
	 * loads successfully ApplicationContext context =
	 * SpringApplication.run(CabBookoningAssignmentApplication.class);
	 * assertNotNull(context); }
	 */

	@Test
	public void testRestTemplateConfiguration() {

		CabBookoningAssignmentApplication application = new CabBookoningAssignmentApplication();
		RestTemplate restTemplate = application.restTemplate();

		assertNotNull(restTemplate);
		assertTrue(containsFormHttpMessageConverter(restTemplate));
		assertTrue(containsMappingJackson2HttpMessageConverter(restTemplate));
	}

	private boolean containsFormHttpMessageConverter(RestTemplate restTemplate) {
		return restTemplate.getMessageConverters().stream()
				.anyMatch(converter -> converter instanceof FormHttpMessageConverter);
	}

	private boolean containsMappingJackson2HttpMessageConverter(RestTemplate restTemplate) {
		return restTemplate.getMessageConverters().stream()
				.anyMatch(converter -> converter instanceof MappingJackson2HttpMessageConverter
						&& ((MappingJackson2HttpMessageConverter) converter).getSupportedMediaTypes()
								.contains(MediaType.APPLICATION_FORM_URLENCODED));
	}
}
