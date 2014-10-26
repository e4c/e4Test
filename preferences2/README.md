II e4 preferences example
====

* In example preferences defined in one plug-in but used in other one.
* Preferences command, handler and view toolbar item exported into model fragment.

**NB!** If model fragment definition contains referenced command in import section, then it's not enough. You must define new model fragment for it obviously. 
In other case You handler and menu/toolbar item will not work and You will see in console something like this:
```
!MESSAGE Could not resolve import for null
```
