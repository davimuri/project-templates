
Database Configuration
======================


Wildfly Configuration
=====================

1. Datasource
--------------
In standalone.xml file, inside datasources tag: 

<subsystem xmlns="urn:jboss:domain:datasources:4.0">
      <datasources>

Add the following datasource definition:

<datasource jndi-name="java:/datasources/appdb" pool-name="appdb" enabled="true" use-java-context="true">
	<connection-url>jdbc:mysql://localhost:3306/appdb?autoReconnect=true&amp;useSSL=false</connection-url>
	<driver>mysql</driver>
	<security>
		<user-name>appuser</user-name>
		<password>appuser123</password>
	</security>
	<statement>
		<prepared-statement-cache-size>100</prepared-statement-cache-size>
		<share-prepared-statements>true</share-prepared-statements>
	</statement>
</datasource>

2. Security Realm
------------------
In standalone.xml file, inside  security-domains tag:

<subsystem xmlns="urn:jboss:domain:security:1.2">
	<security-domains>

Add the following:

<security-domain name="app" cache-type="default">
	<authentication>
		<login-module code="Database" flag="required">
			<module-option name="dsJndiName" value="java:/datasources/appdb"/>
			<module-option name="rolesQuery" value="SELECT ur.role, 'Roles' FROM user_role ur JOIN user u ON u.id = ur.user_id WHERE u.username = ?"/>
			<module-option name="principalsQuery" value="SELECT u.password FROM user u WHERE u.username = ?"/>
		</login-module>
	</authentication>
	<authorization>
		<policy-module code="Database" flag="required">
			<module-option name="dsJndiName" value="java:/datasources/appdb"/>
			<module-option name="rolesQuery" value="SELECT ur.role, 'Roles' FROM user_role ur JOIN user u ON u.id = ur.user_id WHERE u.username = ?"/>
			<module-option name="principalsQuery" value="SELECT u.password FROM user u WHERE u.username = ?"/>
		</policy-module>
	</authorization>
</security-domain>


3. Logs
--------
In standalone.xml file, inside server tag, after extensions:

<server xmlns="urn:jboss:domain:4.2">

    <extensions>
	  ...
    </extensions>

Add the following system property to reference the logback.xml configuration file

<system-properties>
	<property name="logback.configurationFile" value="<path>/logback.xml"/>
</system-properties>
