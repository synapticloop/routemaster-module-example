
so that the `rootProject.name` value matches your project name.


## Code

All the code resides in the `src/main/java` directory, with resources in the `src/main/resources` directory.

### File: `src/main/resources/routemaster.properties`

You **MUST** have a `routemaster.properties` file deployed with every module jar or it will not be activated.

The `routemaster.properties` file has the same standard layout as the default properties file, and may map options, handlers and routes (both RESTful and not).

Some things to note:

> All properties defined in the module jar file will **over-write** any existing properties that are set.

The `rouetmaster.properties` file deployed with this module is as follows:


