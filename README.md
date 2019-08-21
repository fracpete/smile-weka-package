# smile-weka-package
Weka package wrapping SMILE algorithms (https://haifengl.github.io/smile/).

## Algorithms

* Classification

  * [weka.classifiers.smile.classification.SmileRandomForest](https://haifengl.github.io/smile/api/java/smile/classification/RandomForest.html)
  
* Regression
  
  * [weka.classifiers.smile.regression.SmileRidgeRegression](https://haifengl.github.io/smile/api/java/smile/regression/RidgeRegression.html)

* Clustering

  * [weka.clusterers.SmileKMeans](https://haifengl.github.io/smile/api/java/smile/clustering/KMeans.html)


## Releases

* TODO


## How to use packages

For more information on how to install the package, see:

https://waikato.github.io/weka-wiki/packages/manager/


## Maven

Add the following dependency in your `pom.xml` to include the package:

```xml
    <dependency>
      <groupId>com.github.fracpete</groupId>
      <artifactId>smile-weka-package</artifactId>
      <version>TODO</version>
      <type>jar</type>
      <exclusions>
        <exclusion>
          <groupId>nz.ac.waikato.cms.weka</groupId>
          <artifactId>weka-dev</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```

