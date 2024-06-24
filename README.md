# ServerConcurrent
This is a development of server concurrent and client for consume it. The implementation allow three request at the same time.
[Repository in GitHub here](https://github.com/JffMv/ServerConcurrent)

## Getting Started

For clone this repository you use this command:
 ```
 git clone https://github.com/JffMv/ServerConcurrent.git
 ```

### Prerequisites

Have installed:
maven 3.9.6
[Install Maven](https://maven.apache.org/download.cgi#Installation)


git 2.44
[Install Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

java
[Install Java](https://www.oracle.com/co/java/technologies/downloads/)


## Running the tests

This classes haven´t unit test

## Deployment

The process were building project maven with the command:

```
mvn archetype:generate -DgroupId=org.example -DartifactId=URLS -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

```

Then we have verify the class App.java and the pom.xml, run the project with:

```
mvn package

```

For generate documentation update the pom.xml add and later use "mvn package":

```
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <version>3.3.1</version>
                <configuration>
                    <reportOutputDirectory>${project.basedir}/documentation/javadocs</reportOutputDirectory>
                    <destDir>${project.basedir}/documentation/javadocs</destDir>
                </configuration>
                <executions>
                    <execution>
                        <id>generate-javadocs</id>
                        <goals>
                            <goal>javadoc</goal>
                            <goal>aggregate</goal>
                            <goal>aggregate-jar</goal>
                        </goals>
                        <configuration>
                            <reportOutputDirectory>${project.basedir}/documentation/javadocs</reportOutputDirectory>
                            <destDir>${project.basedir}/documentation/javadocs</destDir>
                        </configuration>
                    </execution>
                    <execution>
                        <id>generate-test-javadocs</id>
                        <goals>
                            <goal>test-javadoc</goal>
                            <goal>test-aggregate</goal>
                            <goal>test-aggregate-jar</goal>
                        </goals>
                        <configuration>
                            <destDir>${project.basedir}/documentation/testJavadocs</destDir>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

```
With the before plugins the documentation generate in the URLS/documentation/

### Server web (return images and files html):
This return images and files html save at directory **src/main/resource**
- For run program location in the root of URLS
  For run server:
```
java -cp "target/classes" org.example.ConcurrentServerSendImageDocument

```
For run client,
you have into to browser and put this url:
```
localhost:35000/xxxxxxxxx
```
you replace xxxxxxxxx for the name of the file. The files exist are:
- Among_Us_cover_art.jpg
- proof.html

You can add more files to **src/main/resource** and then call for before URL channel.



## Built With

* [Java](https://www.java.com/es/) - The language used
* [Maven](https://maven.apache.org/) - Dependency Management



## Authors

* **Yeferson Mesa**

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details