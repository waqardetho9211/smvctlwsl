
Websphere Liberty, Thymeleaf, Spring Security
---------------------------------------------
 
References:
 
     http://www.thymeleaf.org
     https://developer.ibm.com/tutorials/spring-boot-java-applications-for-cics-part-2-security/     
     https://www.thymeleaf.org/doc/articles/springsecurity.html



Building
--------
 
 To build this project you will need Maven 2. You can get it at:
 
     http://maven.apache.org

 Clean compilation products:
 
     mvn clean
     
 Compile:
 
     mvn compile
     
 Run in a tomcat server:
 
     mvn tomcat7:run

 Once started, the application should be available at:
 
     http://localhost:8080/springsecurity


 Websphere liberty Server.xml configuration:
    
    <?xml version="1.0" encoding="UTF-8"?>
    <server description="new server">
      <!-- Enable features -->
      <featureManager>
        <feature>webProfile-8.0</feature>
        <feature>localConnector-1.0</feature>
        <feature>javaee-8.0</feature>
        <feature>jndi-1.0</feature>
        <feature>concurrent-1.0</feature>
        <feature>jdbc-4.2</feature>
        <feature>servlet-4.0</feature>
        <feature>beanValidation-2.0</feature>
        <feature>cdi-2.0</feature>
        <feature>jaxrs-2.1</feature>
        <feature>appSecurity-2.0</feature>
        <feature>transportSecurity-1.0</feature>
        <feature>ldapRegistry-3.0</feature>
      </featureManager>
      <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
      <httpEndpoint id="defaultHttpEndpoint" httpPort="9080" httpsPort="9443" />
      <!-- Automatically expand WAR files and EAR files -->
      <quickStartSecurity userName="bob" userPassword="bobpwd" />
      <basicRegistry>
        <user name="bob" password="bobpwd" />
      </basicRegistry>
      <applicationManager autoExpand="true" />
      <applicationMonitor updateTrigger="mbean" />
      <application id="springsecurity_war" location="C:\Users\waqar.ali.detho\IdeaProjects\thymeleafexamples-springsecurity\target\springsecurity-ci.war" name="springsecurity_war" type="war">
        <application-bnd>
          <security-role name="ROLE_USER">
            <user name="bob" />
          </security-role>
        </application-bnd>
      </application>
    </server>

