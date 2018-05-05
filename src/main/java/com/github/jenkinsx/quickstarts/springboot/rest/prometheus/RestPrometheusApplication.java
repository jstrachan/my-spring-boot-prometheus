package com.github.jenkinsx.quickstarts.springboot.rest.prometheus;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static java.util.Collections.singletonMap;

@SpringBootApplication
@Controller
public class RestPrometheusApplication {

	@Autowired
	private MeterRegistry registry;

	@GetMapping(path = "/", produces = "application/json")
	@ResponseBody
	public Map<String, Object> landingPage() {
		Counter.builder("mymetric").tag("foo", "bar").register(registry).increment();
        return singletonMap("hello", "world");
	}

	public static void main(String[] args) {
		SpringApplication.run(RestPrometheusApplication.class, args);
	}

}
