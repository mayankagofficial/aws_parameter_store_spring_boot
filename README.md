# Spring Boot Application with AWS Parameter Store

This Spring Boot application demonstrates how to retrieve configuration values from the AWS Parameter Store, which allows you to store configuration data securely in the AWS cloud.

## Getting Started
### Prerequisites
* Java 8 or later
* AWS Account
* AWS CLI

### Installing
1. Clone the repository or download the source code.
2. Run `mvn clean install` to build the application.

### Configuration
To use AWS Parameter Store to store configuration values for your application, you must first set up the parameter store in your AWS account.

1. Open the AWS Management Console and navigate to the Parameter Store service.
2. Create a new parameter by clicking the "Create Parameter" button.
3. Enter a name for the parameter and the value you want to store. For example, if you want to store the database username, you could enter "db.username" as the parameter name and the username as the parameter value.
Save the parameter.
4. Now that you have created a parameter, now you can set the permission in IAM
5. Go to IAM and set either a custom policy like below or use AWS policy (***AmazonSSMReadOnlyAccess*** or
   ***AmazonSSMFullAccess***)
    ```JSON
    {
      "Version": "2012-10-17",
      "Statement": [
        {
          "Sid": "VisualEditor0",
          "Effect": "Allow",
          "Action": "ssm:DescribeParameters",
          "Resource": "*"
        },
        {
          "Sid": "VisualEditor1",
          "Effect": "Allow",
          "Action": [
            "ssm:GetParameters",
            "ssm:GetParameter",
            "ssm:GetParametersByPath"
          ],
          "Resource": "arn:aws:ssm:eu-central-1:*:parameter/config/*"
        }
      ]
    }
    ```
6. Now you can retrieve the parameter value in your Spring Boot application by using the AWS library for Java.


### Spring Boot Implementation
1. Add the below lines of code to your pom.xml file to add dependency to your application.
    ```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
            <version>4.0.0</version>
        </dependency>
        
        <dependency>
            <groupId>io.awspring.cloud</groupId>
            <artifactId>spring-cloud-starter-aws-parameter-store-config</artifactId>
            <version>2.4.0</version>
        </dependency>
    </dependencies>
    ```
2. Create **bootstrap.properties** file inside the resource file *(if not present)* and enter the below parameters in it.
    ```text
    cloud.aws.stack.auto=false
    spring.application.name=
    aws.access.key=
    aws.secret.key=
    spring.profiles.active=
    ```
3. To run the application in your local machine (outside AWS EC2) then set the below environment variables
    ```text
    AWS_ACCESS_KEY=<AWS-Access-key>
    AWS_EC2_METADATA_DISABLED=true
    AWS_REGION=<Region>
    AWS_SECRET_KEY=<AWS-Secret-key>
    CLOUD_AWS_STACK_AUTO=false
    SPRING_APPLICATION_NAME=<App-name>
    SPRING_PROFILES_ACTIVE=<Profile>
    ```

## Running the tests
Run `mvn test` to run the tests for this application.

## Deployment
To deploy the application, you can build a JAR file and run it on a server or cloud platform of your choice. You can also use the Spring Boot Maven plugin to package and run the application as a JAR file:
```shell
mvn spring-boot:run
```