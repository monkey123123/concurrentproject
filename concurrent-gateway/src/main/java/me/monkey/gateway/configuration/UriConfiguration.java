package me.monkey.gateway.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "api-application")
@Component
public class UriConfiguration {
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