package com.cinema.api.config.security.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.cinema.api.util.HttpHeaderValue.APPLICATION_JSON;

public class HttpServletResponseDecorator extends javax.servlet.http.HttpServletResponseWrapper {
     /**
      * Constructs a response adaptor wrapping the given response.
      *
      * @param response The response to be wrapped
      * @throws IllegalArgumentException if the response is null
      */
     public HttpServletResponseDecorator(HttpServletResponse response) {
          super(response);
     }

     public void setJSONBody(String body) throws IOException {
          setContentType(APPLICATION_JSON);
          getResponse().getWriter().write(body);
     }

     public void setStatusOK() {
          setStatus(HttpServletResponse.SC_OK);
     }
}
