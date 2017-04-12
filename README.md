 <a name="#documentr_top"></a>

> **This project requires JVM version of at least 1.7**






<a name="documentr_heading_0"></a>

# routemaster-module-example <sup><sup>[top](documentr_top)</sup></sup>



> example module for the routemaster






<a name="documentr_heading_1"></a>

# Table of Contents <sup><sup>[top](documentr_top)</sup></sup>



 - [routemaster-module-example](#documentr_heading_0)
 - [Table of Contents](#documentr_heading_1)
 - [Creating a new module](#documentr_heading_2)
   - [Setup](#documentr_heading_3)
 - [Building the Package](#documentr_heading_6)
   - [*NIX/Mac OS X](#documentr_heading_7)
   - [Windows](#documentr_heading_8)
 - [Running the Tests](#documentr_heading_9)
   - [*NIX/Mac OS X](#documentr_heading_10)
   - [Windows](#documentr_heading_11)
   - [Dependencies - Gradle](#documentr_heading_12)
   - [Dependencies - Maven](#documentr_heading_13)
   - [Dependencies - Downloads](#documentr_heading_14)


# Module Support

This is an example module that offers additional, modular support for [synapticloop routemaster](https://github.com/synapticloop/routemaster).

Routemaster modules can be automatically loaded from the `modules` directory that 



<a name="documentr_heading_2"></a>

# Creating a new module <sup><sup>[top](documentr_top)</sup></sup>



<a name="documentr_heading_3"></a>

## Setup <sup><sup>[top](documentr_top)</sup></sup>

Download the latest version of the [source code](https://github.com/synapticloop/routemaster-module-example/archive/master.zip), and unzip it into your project working directory.

The structure of the folder is as follows:

 - `build.gradle` (the build.gradle file)
 - `gradle`
 - `gradlew`
 - `gradlew.bat`
 - `settings.gradle` (the settings file for gradle)
 - `src` (the source files directory)

The following files are of interest:

### `build.gradle`

This is the main build file and contains everything to build the module.  It can be executed by typing the following:

`gradle build`

If you are on windows:

`gradle.bat build`

The file contents are:




```
plugins {
	id 'java'
	id 'eclipse'
	id "maven"
	id "maven-publish"

	id "com.github.ben-manes.versions" version "0.13.0"
	id 'net.saliman.cobertura' version '2.2.6'
	id 'co.riiid.gradle' version '0.4.2'

	id "synapticloop.copyrightr" version "1.1.2"
	id "synapticloop.documentr" version "2.9.0"
}

version = '1.0.0'

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
	runtime 'synapticloop:routemaster:2.0.0'

	compile 'synapticloop:routemaster:2.0.0'
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


### `settings.gradle`

You will also want to change the `settings.gradle` file:




```
rootProject.name = 'routemaster-module-example'

```






<a name="documentr_heading_6"></a>

# Building the Package <sup><sup>[top](documentr_top)</sup></sup>



<a name="documentr_heading_7"></a>

## *NIX/Mac OS X <sup><sup>[top](documentr_top)</sup></sup>

From the root of the project, simply run

`./gradlew build`




<a name="documentr_heading_8"></a>

## Windows <sup><sup>[top](documentr_top)</sup></sup>

`./gradlew.bat build`


This will compile and assemble the artefacts into the `build/libs/` directory.

Note that this may also run tests (if applicable see the Testing notes)



<a name="documentr_heading_9"></a>

# Running the Tests <sup><sup>[top](documentr_top)</sup></sup>



<a name="documentr_heading_10"></a>

## *NIX/Mac OS X <sup><sup>[top](documentr_top)</sup></sup>

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`gradlew --info test`



<a name="documentr_heading_11"></a>

## Windows <sup><sup>[top](documentr_top)</sup></sup>

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`./gradlew.bat --info test`


The `--info` switch will also output logging for the tests



<a name="documentr_heading_12"></a>

## Dependencies - Gradle <sup><sup>[top](documentr_top)</sup></sup>



```
dependencies {
	runtime(group: 'synapticloop', name: 'routemaster-module-example', version: '1.0.0', ext: 'jar')

	compile(group: 'synapticloop', name: 'routemaster-module-example', version: '1.0.0', ext: 'jar')
}
```



or, more simply for versions of gradle greater than 2.1



```
dependencies {
	runtime 'synapticloop:routemaster-module-example:1.0.0'

	compile 'synapticloop:routemaster-module-example:1.0.0'
}
```





<a name="documentr_heading_13"></a>

## Dependencies - Maven <sup><sup>[top](documentr_top)</sup></sup>



```
<dependency>
	<groupId>synapticloop</groupId>
	<artifactId>routemaster-module-example</artifactId>
	<version>1.0.0</version>
	<type>jar</type>
</dependency>
```





<a name="documentr_heading_14"></a>

## Dependencies - Downloads <sup><sup>[top](documentr_top)</sup></sup>


You will also need to download the following dependencies:



### cobertura dependencies

  - `net.sourceforge.cobertura:cobertura:2.0.3`: (It may be available on one of: [bintray](https://bintray.com/net.sourceforge.cobertura/maven/cobertura/2.0.3/view#files/net.sourceforge.cobertura/cobertura/2.0.3) [mvn central](http://search.maven.org/#artifactdetails|net.sourceforge.cobertura|cobertura|2.0.3|jar))


### compile dependencies

  - `synapticloop:routemaster:2.0.0`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/routemaster/2.0.0/view#files/synapticloop/routemaster/2.0.0) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|routemaster|2.0.0|jar))


### runtime dependencies

  - `synapticloop:routemaster:2.0.0`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/routemaster/2.0.0/view#files/synapticloop/routemaster/2.0.0) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|routemaster|2.0.0|jar))

**NOTE:** You may need to download any dependencies of the above dependencies in turn (i.e. the transitive dependencies)

--

> `This README.md file was hand-crafted with care utilising synapticloop`[`templar`](https://github.com/synapticloop/templar/)`->`[`documentr`](https://github.com/synapticloop/documentr/)

--
