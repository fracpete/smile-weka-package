# smile-weka-package
Weka package wrapping SMILE algorithms (https://haifengl.github.io/smile/).

## Algorithms

* Classification

  * [weka.classifiers.smile.classification.SmileRandomForest](https://haifengl.github.io/smile/api/java/smile/classification/RandomForest.html)
  * [weka.classifiers.smile.classification.SmileSVM](https://haifengl.github.io/smile/api/java/smile/classification/SVM.html)
  
* Regression
  
  * [weka.classifiers.smile.regression.SmileGaussianProcessRegression](https://haifengl.github.io/smile/api/java/smile/regression/GaussianProcessRegression.html)
  * [weka.classifiers.smile.regression.SmileRandomForest](https://haifengl.github.io/smile/api/java/smile/regression/RandomForest.html)
  * [weka.classifiers.smile.regression.SmileRidgeRegression](https://haifengl.github.io/smile/api/java/smile/regression/RidgeRegression.html)
  * [weka.classifiers.smile.regression.SmileSVR](https://haifengl.github.io/smile/api/java/smile/regression/SVR.html)

* Kernels

  * [weka.classifiers.smile.math.kernel.SmileGaussianKernel](https://haifengl.github.io/smile/api/java/smile/math/kernel/GaussianKernel.html)
  * [weka.classifiers.smile.math.kernel.SmileHellingerKernel](https://haifengl.github.io/smile/api/java/smile/math/kernel/HellingerKernel.html)
  * [weka.classifiers.smile.math.kernel.SmileHyperbolicTangentKernel](https://haifengl.github.io/smile/api/java/smile/math/kernel/HyperbolicTangentKernel.html)
  * [weka.classifiers.smile.math.kernel.SmileLaplacianKernel](https://haifengl.github.io/smile/api/java/smile/math/kernel/LaplacianKernel.html)
  * [weka.classifiers.smile.math.kernel.SmileLinearKernel](https://haifengl.github.io/smile/api/java/smile/math/kernel/LinearKernel.html)
  * [weka.classifiers.smile.math.kernel.SmilePearsonKernel](https://haifengl.github.io/smile/api/java/smile/math/kernel/PearsonKernel.html)
  * [weka.classifiers.smile.math.kernel.SmilePolynomialKernel](https://haifengl.github.io/smile/api/java/smile/math/kernel/PolynomialKernel.html)
  * [weka.classifiers.smile.math.kernel.SmileThinPlateSplineKernel](https://haifengl.github.io/smile/api/java/smile/math/kernel/ThinPlateSplineKernel.html)

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

