package com.cinema.api.config.web.resolver;

import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FrontendResolver implements ResourceResolver {

    private static final String APP_DIR = "/static/";

    private Resource index = new ClassPathResource(APP_DIR + "index.html");

    private List<String> rootStaticFiles = Arrays.asList("favicon.io",
            "asset-manifest.json", "manifest.json", "service-worker.js");

    @Override
    public Resource resolveResource(HttpServletRequest request, String requestPath,
                                    List<? extends Resource> locations, ResourceResolverChain chain) {
        return resolve(requestPath, locations);
    }

    @Override
    public String resolveUrlPath(String resourcePath, List<? extends Resource> locations, ResourceResolverChain chain) {
        Resource resolvedResource = resolve(resourcePath, locations);
        if (resolvedResource == null) {
            return null;
        }
        try {
            return resolvedResource.getURL().toString();
        } catch (IOException e) {
            return resolvedResource.getFilename();
        }
    }

    private Resource resolve(String requestPath, List<? extends Resource> locations) {
        if (requestPath == null) return null;

        if (rootStaticFiles.contains(requestPath)) {
            return new ClassPathResource(APP_DIR + requestPath);
        } else {
            return index;
        }
    }
}
