 <a name="#documentr_top"></a>

> **This project requires JVM version of at least 1.7**






<a name="documentr_heading_0"></a>

# routemaster-module-example <sup><sup>[top](documentr_top)</sup></sup>



> example module for the routemaster





<a name="documentr_heading_1"></a>

# Quick overview <sup><sup>[top](documentr_top)</sup></sup>

This module provides an extension point for the [routemaster](https://github.com/synapticloop/routemaster) server which can be 
automatically loaded and registered by placing it in the `modules/` directory alongside
the `routemaster-server.jar`.  The module provides all dependencies and 
configuration required to fully load and execute the module.

Download from [https://github.com/synapticloop/routemaster-module-example/releases](https://github.com/synapticloop/routemaster-module-example/releases)

Within the module, at the root of the jar file will be a file named `routemaster-module-example.properties` 
which determines the configuration for the module.  The configuration file may be viewed here:

`src/main/resources/routemaster-module-example.properties`

Furthermore, the in-built configuration can be over-ridden by placing a file 
named `routemaster-module-example.properties` in the `modules` directory along side the jar 
file which will take precedence when searching for configuration.

The file format and layout is as per the `routemaster.properties` file for the 
routemaster server.





<a name="documentr_heading_2"></a>

# Table of Contents <sup><sup>[top](documentr_top)</sup></sup>



 - [routemaster-module-example](#documentr_heading_0)
 - [Quick overview](#documentr_heading_1)
 - [Table of Contents](#documentr_heading_2)
 - [Creating a new module](#documentr_heading_3)
   - [Setup](#documentr_heading_4)
   - [Configuration](#documentr_heading_5)
   - [Including dependencies in the module](#documentr_heading_7)
   - [Code](#documentr_heading_9)
 - [Deploying modules](#documentr_heading_11)
   - [Step 1](#documentr_heading_12)
   - [Step 2](#documentr_heading_13)
   - [Step 3](#documentr_heading_14)
 - [Running the server](#documentr_heading_15)
 - [Generating the default documentation](#documentr_heading_16)
 - [Building the Package](#documentr_heading_17)
   - [*NIX/Mac OS X](#documentr_heading_18)
   - [Windows](#documentr_heading_19)
 - [Running the Tests](#documentr_heading_20)
   - [*NIX/Mac OS X](#documentr_heading_21)
   - [Windows](#documentr_heading_22)
   - [Dependencies - Gradle](#documentr_heading_23)
   - [Dependencies - Maven](#documentr_heading_24)
   - [Dependencies - Downloads](#documentr_heading_25)


# Module Support

This is an example module that offers additional, modular support for [synapticloop routemaster](https://github.com/synapticloop/routemaster).

Routemaster modules can be automatically loaded from the `modules` directory that 
can automatically provide:

 - Handlers
 - Configuration options
 - RESTful routes
 - static routes



<a name="documentr_heading_3"></a>

# Creating a new module <sup><sup>[top](documentr_top)</sup></sup>



<a name="documentr_heading_4"></a>

## Setup <sup><sup>[top](documentr_top)</sup></sup>

Download the latest version of the [source code](https://github.com/synapticloop/routemaster-module-example/archive/master.zip), and unzip it into your project working directory.

The structure of the folder is as follows:

 - `build.gradle` (the build.gradle file)
 - `gradle`
 - `gradlew`
 - `gradlew.bat`
 - `settings.gradle` (the settings file for gradle)
 - `src` (the source files directory)




<a name="documentr_heading_5"></a>

## Configuration <sup><sup>[top](documentr_top)</sup></sup>

### File: `build.gradle`

This is the main build file and contains everything to build (and bundle - if 
required) the module.  It can be executed by typing the following:

`gradle build`

If you are on windows:

`gradle.bat build`

The `build.gradle` file contents are:




```
plugins {
	id 'java'
	id 'eclipse'
	id "maven"
	id "maven-publish"

	id "com.github.ben-manes.versions" version "0.13.0"
	id 'net.saliman.cobertura' version '2.2.6'
	id 'co.riiid.gradle' version '0.4.2'

	id "com.github.johnrengelman.shadow" version "1.2.4"

	id "synapticloop.copyrightr" version "1.1.2"
	id "synapticloop.documentr" version "2.9.0"
}

version = '1.1.0'

//
// ensure that you change the following values
//
group = 'synapticloop'
archivesBaseName = 'routemaster-module-example'
description = """example module for the routemaster"""

sourceCompatibility = 1.7
targetCompatibility = 1.7

repositories {
	mavenLocal()
	mavenCentral()
	jcenter()
}

//
// We create a configuration here which is used to run the shadow jar
//

configurations {
	shadow
}

//
// ensure that the dependencies are up-to-date, you can do this by running
//
//    gradle dependencyUpdates
//
// or as a shortcut (provided there are no clashes)
//
//    gradle dU
//
// this functionality is provided by the plugin:
//    id "com.github.ben-manes.versions" version "0.13.0"
//

dependencies {
	runtime 'synapticloop:routemaster:2.1.0'

	compile 'synapticloop:routemaster:2.1.0'

	// if you need to include any dependencies, then you __MUST__ add them to the
	// 'shadow configuration, e.g. uncomment the following line to include 'templar'
	// shadow 'synapticloop:templar:1.4.4'
}

// the following is the shadow jar plugin configuration which ultimately collects
// all of the dependencies together and puts them into the same jar file for
// deployment

shadowJar {
	classifier = ''
	configurations = [project.configurations.shadow]
}

//
// This will upload the file to github releases section and can be accomplished 
// by running 
//
//    gradle githubRelease
//
// or as a shortcut (provided there are no clashes)
//
//    gradle gR
//
// you __MUST__ provide the following environment key:
//    - GITHUB_TOKEN
//

github {
	owner = group
	repo = archivesBaseName
	if(System.getenv('GITHUB_TOKEN')) {
		token = System.getenv('GITHUB_TOKEN')
	}
	tagName = version
	name = version
	assets = [
		'build/libs/' + archivesBaseName + '-' + version + '.jar',
	]
}

```





<a name="documentr_heading_7"></a>

## Including dependencies in the module <sup><sup>[top](documentr_top)</sup></sup>

Within the `build.gradle` file a `shadow` configuration is defined.  Any dependencies 
that you wish to include in the final module **MUST** be listed here.  

See the `configurations`, `dependencies` and the `shadowJat` sections in the 
`build.gradle` file above.

> **WARNING**: if multiple modules are including different versions of jar dependencies, then the order in which that these will be picked up by the class loader is undefined.

### File: `settings.gradle`

You will also want to change the `settings.gradle` file:




```
rootProject.name = 'routemaster-module-example'

```



so that the `rootProject.name` value matches your project name.




<a name="documentr_heading_9"></a>

## Code <sup><sup>[top](documentr_top)</sup></sup>

All the code resides in the `src/main/java` directory, with resources in the `src/main/resources` directory.

### File: `src/main/resources/{archivesBaseName}.properties`

You **MUST** have a properties file deployed with every module jar or it will not 
be activated.  The name of the properties file is the module name (without any version 
number - (e.g. `{archivesBaseName}.properties` - in this example it is 
`routemaster-module-example.properties`

The keys and values in the properties file are the same as the default 
`routemaster.properties` file.  This file has the same standard layout as the 
default properties file, and may map options, handlers and routes 
(both RESTful and not).

Some things to note:

> All properties defined in the module jar file will **over-write** any existing properties that are set.





<a name="documentr_heading_11"></a>

# Deploying modules <sup><sup>[top](documentr_top)</sup></sup>



<a name="documentr_heading_12"></a>

## Step 1 <sup><sup>[top](documentr_top)</sup></sup>

To deploy the built module, ensure that you have downloaded the latest routemaster server from  [github routemaster releases](https://github.com/synapticloop/routemaster/releases) *(the jar file is the one that has the `server` classifier - e.g. `routemaster-2.0.0-server.jar`)*.

**Note:** The server version has **NO** functionality built in - i.e. no routes are defined - instead all functionality must come from the modules.  It will still operate successfully, however all the modules must map the routes.



<a name="documentr_heading_13"></a>

## Step 2 <sup><sup>[top](documentr_top)</sup></sup>

Create a `modules` directory which is in the same directory that the downloaded file is (your directory listing should look something like the following:




```
/routemaster-2.0.0-server.jar
/modules/
```





<a name="documentr_heading_14"></a>

## Step 3 <sup><sup>[top](documentr_top)</sup></sup>

Place the module (or multiple modules) into the `modules` directory so that it will look something like the following:



```
/routemaster-2.0.0-server.jar
/modules/
        /routemaster-module-example-1.0.0.jar
```





<a name="documentr_heading_15"></a>

# Running the server <sup><sup>[top](documentr_top)</sup></sup>

now run the server:

`java -jar routemaster-2.0.0-server.jar`

For the example above, the only routes that are mapped are: 

 - `/module/example/`
 - `/module/example/*`

If you open [http://localhost:5474/module/example/](http://localhost:5474/module/example/)

you will see something along the lines of:



```
Hello from the example module, this page was brought to you by the letter 'H'
```





<a name="documentr_heading_16"></a>

# Generating the default documentation <sup><sup>[top](documentr_top)</sup></sup>

Simply run:



```
gradle --no-daemon documentr
```



Or you can simply generate your own `README.md` file.




<a name="documentr_heading_17"></a>

# Building the Package <sup><sup>[top](documentr_top)</sup></sup>



<a name="documentr_heading_18"></a>

## *NIX/Mac OS X <sup><sup>[top](documentr_top)</sup></sup>

From the root of the project, simply run

`./gradlew build`




<a name="documentr_heading_19"></a>

## Windows <sup><sup>[top](documentr_top)</sup></sup>

`./gradlew.bat build`


This will compile and assemble the artefacts into the `build/libs/` directory.

Note that this may also run tests (if applicable see the Testing notes)



<a name="documentr_heading_20"></a>

# Running the Tests <sup><sup>[top](documentr_top)</sup></sup>



<a name="documentr_heading_21"></a>

## *NIX/Mac OS X <sup><sup>[top](documentr_top)</sup></sup>

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`gradlew --info test`



<a name="documentr_heading_22"></a>

## Windows <sup><sup>[top](documentr_top)</sup></sup>

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`./gradlew.bat --info test`


The `--info` switch will also output logging for the tests



<a name="documentr_heading_23"></a>

## Dependencies - Gradle <sup><sup>[top](documentr_top)</sup></sup>



```
dependencies {
	runtime(group: 'synapticloop', name: 'routemaster-module-example', version: '1.1.0', ext: 'jar')

	compile(group: 'synapticloop', name: 'routemaster-module-example', version: '1.1.0', ext: 'jar')
}
```



or, more simply for versions of gradle greater than 2.1



```
dependencies {
	runtime 'synapticloop:routemaster-module-example:1.1.0'

	compile 'synapticloop:routemaster-module-example:1.1.0'
}
```





<a name="documentr_heading_24"></a>

## Dependencies - Maven <sup><sup>[top](documentr_top)</sup></sup>



```
<dependency>
	<groupId>synapticloop</groupId>
	<artifactId>routemaster-module-example</artifactId>
	<version>1.1.0</version>
	<type>jar</type>
</dependency>
```





<a name="documentr_heading_25"></a>

## Dependencies - Downloads <sup><sup>[top](documentr_top)</sup></sup>


You will also need to download the following dependencies:



### cobertura dependencies

  - `net.sourceforge.cobertura:cobertura:2.0.3`: (It may be available on one of: [bintray](https://bintray.com/net.sourceforge.cobertura/maven/cobertura/2.0.3/view#files/net.sourceforge.cobertura/cobertura/2.0.3) [mvn central](http://search.maven.org/#artifactdetails|net.sourceforge.cobertura|cobertura|2.0.3|jar))


### compile dependencies

  - `synapticloop:routemaster:2.1.0`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/routemaster/2.1.0/view#files/synapticloop/routemaster/2.1.0) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|routemaster|2.1.0|jar))


### runtime dependencies

  - `synapticloop:routemaster:2.1.0`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/routemaster/2.1.0/view#files/synapticloop/routemaster/2.1.0) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|routemaster|2.1.0|jar))

**NOTE:** You may need to download any dependencies of the above dependencies in turn (i.e. the transitive dependencies)

--

> `This README.md file was hand-crafted with care utilising synapticloop`[`templar`](https://github.com/synapticloop/templar/)`->`[`documentr`](https://github.com/synapticloop/documentr/)

--
