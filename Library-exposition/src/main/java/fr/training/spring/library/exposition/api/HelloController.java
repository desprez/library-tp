package fr.training.spring.library.exposition.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller example
 */
@RestController
@Validated
public class HelloController {

	@ApiOperation(value = "", nickname = "helloGet", notes = "Compute Welcome message for the given user name", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Name supplied"),
			@ApiResponse(code = 404, message = "get not found") })
	@GetMapping(value = "/hello")
	public ResponseEntity<String> helloGet(
			@NotNull @ApiParam(value = "query parameter 'Name' for hello operation", required = true) @Valid @RequestParam(value = "name", required = true) final String name) {

		return new ResponseEntity<String>("Hello " + name + " !", HttpStatus.OK);
	}

}