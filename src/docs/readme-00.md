# Module Support

This is an example module that offers additional, modular support for [synapticloop routemaster](https://github.com/synapticloop/routemaster).

Routemaster modules can be automatically loaded from the `modules` directory that 
can automatically provide:

 - Handlers
 - Configuration options
 - RESTful routes
 - static routes

# Creating a new module

## Setup

Download the latest version of the [source code](https://github.com/synapticloop/routemaster-module-example/archive/master.zip), and unzip it into your project working directory.

The structure of the folder is as follows:

 - `build.gradle` (the build.gradle file)
 - `gradle`
 - `gradlew`
 - `gradlew.bat`
 - `settings.gradle` (the settings file for gradle)
 - `src` (the source files directory)


## Configuration

### File: `build.gradle`

This is the main build file and contains everything to build the module.  It can be executed by typing the following:

`gradle build`

If you are on windows:

`gradle.bat build`

The file contents are:

