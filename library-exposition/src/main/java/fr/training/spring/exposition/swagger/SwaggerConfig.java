package fr.training.spring.exposition.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		// Adding Headers parameters documentation
		final List<Parameter> headersParameters = new ArrayList<Parameter>();

		return new Docket(DocumentationType.SWAGGER_2).select() //
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(Predicates.not(PathSelectors.regex("/error.*"))) //
				.build() //
				.apiInfo(apiInfo()) //
				.globalOperationParameters(headersParameters);
	}

	ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
				.title("Swagger Sample-service") //
				.description("No description provided") //
				.license("Apache 2.0") //
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html") //
				.termsOfServiceUrl("") //
				.version("1.0") //
				.contact(new Contact("", "", "SomeOne@acme.fr")) //
				.build();
	}

	// @Bean
	// public Docket customImplementation() {
	//
	// return new Docket(DocumentationType.SWAGGER_2).select()
	// .apis(RequestHandlerSelectors.basePackage("fr.training.spring.account.server.app.api")).build()
	// .directModelSubstitute(LocalDate.class, java.sql.Date.class)//
	// .directModelSubstitute(ZonedDateTime.class, java.util.Date.class) //
	// .apiInfo(apiInfo()) //
	// .globalOperationParameters(headersParameters);
	// }

}