How to make a release
=====================

Preparation
-----------

* Change the artifact ID in `pom.xml` to today's date, e.g.:

  ```
  2019.9.1-SNAPSHOT
  ```

* Update the version, date and URL in `Description.props` to reflect new
  version, e.g.:

  ```
  Version=2019.9.1
  Date=2019-09-01
  PackageURL=https://github.com/fracpete/smile-weka-package/releases/download/v2019.9.1/smile-2019.9.1.zip
  ```

* Commit/push all changes

Weka package
------------

* Run the following command to generate the package archive for version `2019.9.1`:

  ```
  ant -f build_package.xml -Dpackage=smile-2019.9.1 clean make_package
  ```

* Create a release tag on github (v2019.9.1)
* add release notes
* upload package archive from `dist`


Maven
-----

* Run the following command to deploy the artifact:

  ```
  mvn release:clean release:prepare release:perform
  ```

* log into https://oss.sonatype.org and close/release artifacts

* After successful deployment, push the changes out:

  ```
  git push
  ````

