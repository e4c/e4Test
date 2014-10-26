II e4 preferences example
====

* In example preferences defined in one plug-in but used in other one.
* Preferences command, handler and view toolbar item exported into model fragment.

**NB!** If model fragment definition contains referenced command in import section, then it's not enough. You must define new model fragment for it obviously. 
In other case You handler and menu/toolbar item will not work and You will see in console something like this:
```
!ENTRY org.eclipse.e4.ui.workbench 2 0 2014-10-26 09:23:22.261
!MESSAGE Could not resolve an import element for 'null'

!ENTRY org.eclipse.e4.ui.workbench 2 0 2014-10-26 09:23:22.264
!MESSAGE Could not resolve import for null
```
![model fragment command](https://cloud.githubusercontent.com/assets/1450943/4781973/f1374eee-5ccf-11e4-94fb-80d47a8a8a4f.png)

