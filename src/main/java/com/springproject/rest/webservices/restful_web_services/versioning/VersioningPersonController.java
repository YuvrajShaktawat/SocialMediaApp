package com.springproject.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	@GetMapping(value = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonUsingParams() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person", params = {"version=2"})
	public PersonV2 getSecondVersionOfPersonUsingParams() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	@GetMapping(value = "/v1/person/header", headers= "Xttp-version=1")
	public PersonV1 getFirstVersionOfPersonUsingRequestHeaders() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/v2/person/header", headers= "Xttp-version=1")
	public PersonV2 getSecondVersionOfPersonUsingRequestHeaders() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	@GetMapping(value = "/v1/person/accept", produces= "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonUsingMediaType() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/v2/person/accept", produces= "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonUsingMediaType() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}