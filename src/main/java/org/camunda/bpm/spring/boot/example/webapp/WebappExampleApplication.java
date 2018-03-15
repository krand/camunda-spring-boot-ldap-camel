package org.camunda.bpm.spring.boot.example.webapp;

import org.apache.camel.CamelContext;
import org.camunda.bpm.camel.common.CamelService;
import org.camunda.bpm.camel.common.CamelServiceCommonImpl;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.identity.impl.ldap.plugin.LdapIdentityProviderPlugin;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableProcessApplication
public class WebappExampleApplication {

    public static void main(String... args) {
        SpringApplication.run(WebappExampleApplication.class, args);
    }

    @Bean
    public LdapIdentityProviderPlugin ldapIdentityProviderPlugin(
        @Value("${app.ldap.serverUrl}") String serverUrl,
        @Value("${app.ldap.managerDn}") String managerDn,
        @Value("${app.ldap.managerPassword}") String managerPassword,
        @Value("${app.ldap.baseDn}") String baseDn
    ) {
        LdapIdentityProviderPlugin ldapIdentityProviderPlugin = new LdapIdentityProviderPlugin();
        ldapIdentityProviderPlugin.setServerUrl(serverUrl);
        ldapIdentityProviderPlugin.setManagerDn(managerDn);
        ldapIdentityProviderPlugin.setManagerPassword(managerPassword);
        ldapIdentityProviderPlugin.setBaseDn(baseDn);
        return ldapIdentityProviderPlugin;
    }

//    Authorization is off
//    @Bean
//    public AdministratorAuthorizationPlugin administratorAuthorizationPlugin(){
//        AdministratorAuthorizationPlugin administratorAuthorizationPlugin = new AdministratorAuthorizationPlugin();
//        administratorAuthorizationPlugin.setAdministratorUserName("test");
//        return administratorAuthorizationPlugin;
//    }

    @Bean
    public CamelService camelService(){
        return new CamelServiceCommonImpl() {
            @Autowired
            public void setProcessEngine(ProcessEngine processEngine) {
                this.processEngine = processEngine;
            }

            @Autowired
            public void setCamelContext(CamelContext camelContext) {
                this.camelContext = camelContext;
            }
        };
    }

}
