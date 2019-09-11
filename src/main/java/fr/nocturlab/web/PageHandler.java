package fr.nocturlab.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class PageHandler {
	@Value("${server.https}")
	public boolean https;
}