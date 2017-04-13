
## Including dependencies in the module

Within the `build.gradle` file a `shadow` configuration is defined.  Any dependencies 
that you wish to include in the final module **MUST** be listed here.  

See the `configurations`, `dependencies` and the `shadowJat` sections in the 
`build.gradle` file above.

> **WARNING**: if multiple modules are including different versions of jar dependencies, then the order in which that these will be picked up by the class loader is undefined.

### File: `settings.gradle`

You will also want to change the `settings.gradle` file:

