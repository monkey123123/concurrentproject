package me.monkey.admin.appmonitor;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@SpringBootApplication
@EnableAdminServer //admin server 启动器配置
public class AppMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppMonitorApplication.class, args);
	}
	@Configuration
	public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
		private final String adminContextPath;

		public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
			this.adminContextPath = adminServerProperties.getContextPath();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
			successHandler.setTargetUrlParameter("redirectTo");

			http.authorizeRequests()
					.antMatchers(adminContextPath + "/assets/**").permitAll()
					.antMatchers(adminContextPath + "/login").permitAll()
					.anyRequest().authenticated()
					.and()
					.formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
					.logout().logoutUrl(adminContextPath + "/logout").and()
					.httpBasic().and()
					.csrf().disable();
			// @formatter:on
		}
	}
}