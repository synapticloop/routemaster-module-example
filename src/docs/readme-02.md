
so that the `rootProject.name` value matches your project name.


## Code

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



# Deploying modules

## Step 1

To deploy the built module, ensure that you have downloaded the latest routemaster server from  [github routemaster releases](https://github.com/synapticloop/routemaster/releases) *(the jar file is the one that has the `server` classifier - e.g. `routemaster-2.0.0-server.jar`)*.

**Note:** The server version has **NO** functionality built in - i.e. no routes are defined - instead all functionality must come from the modules.  It will still operate successfully, however all the modules must map the routes.

## Step 2

Create a `modules` directory which is in the same directory that the downloaded file is (your directory listing should look something like the following:


```
/routemaster-2.0.0-server.jar
/modules/
```

## Step 3

Place the module (or multiple modules) into the `modules` directory so that it will look something like the following:

```
/routemaster-2.0.0-server.jar
/modules/
        /routemaster-module-example-1.0.0.jar
```

# Running the server

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

# Generating the default documentation

Simply run:

```
gradle --no-daemon documentr
```

Or you can simply generate your own `README.md` file.
