package me.monkey.gateway.filter;


import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfiguration {
//  lb:application
//  @Value("${api-application.uri:http://httpbin.org:80}")
  String uri;

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

}