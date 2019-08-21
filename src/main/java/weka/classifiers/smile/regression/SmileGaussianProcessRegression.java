/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * SmileGaussianProcessRegression.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.classifiers.smile.regression;

import smile.data.AttributeDataset;
import smile.regression.GaussianProcessRegression;
import smile.regression.Regression;
import weka.classifiers.AbstractSmileRegressor;
import weka.classifiers.smile.math.kernel.AbstractSmileKernel;
import weka.classifiers.smile.math.kernel.SmileGaussianKernel;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;
import weka.core.WekaOptionUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * SMILE GaussianProcessRegression.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileGaussianProcessRegression
  extends AbstractSmileRegressor {

  private static final long serialVersionUID = -8861088582494627633L;

  public static final String KERNEL = "kernel";

  public static final String LAMBDA = "lambda";

  /** the kernel. */
  protected AbstractSmileKernel m_Kernel = getDefaultKernel();

  /** the shrinkage/regularization parameter. */
  protected double m_Lambda = getDefaultLambda();

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  @Override
  public String globalInfo() {
    return "Gaussian Process for Regression. A Gaussian process is a stochastic "
      + "process whose realizations consist of random values associated with "
      + "every point in a range of times (or of space) such that each such "
      + "random variable has a normal distribution. Moreover, every finite "
      + "collection of those random variables has a multivariate normal "
      + "distribution.\n\n"
      + "See also:\n"
      + "https://haifengl.github.io/smile/api/java/smile/regression/GaussianProcessRegression.html";
  }

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration listOptions() {
    Vector result = new Vector();
    WekaOptionUtils.addOption(result, kernelTipText(), "" + getDefaultKernel(), KERNEL);
    WekaOptionUtils.addOption(result, lambdaTipText(), "" + getDefaultLambda(), LAMBDA);
    WekaOptionUtils.add(result, super.listOptions());
    return WekaOptionUtils.toEnumeration(result);
  }

  /**
   * Parses a given list of options.
   *
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  @Override
  public void setOptions(String[] options) throws Exception {
    setKernel((AbstractSmileKernel) WekaOptionUtils.parse(options, KERNEL, getDefaultKernel()));
    setLambda(WekaOptionUtils.parse(options, LAMBDA, getDefaultLambda()));
    super.setOptions(options);
  }

  /**
   * Gets the current settings.
   *
   * @return an array of strings suitable for passing to setOptions
   */
  @Override
  public String[] getOptions() {
    List<String> result = new ArrayList<String>();
    WekaOptionUtils.add(result, KERNEL, getKernel());
    WekaOptionUtils.add(result, LAMBDA, getLambda());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default kernel.
   *
   * @return		the default
   */
  protected AbstractSmileKernel getDefaultKernel() {
    return new SmileGaussianKernel();
  }

  /**
   * Sets the kernel.
   *
   * @param value	the kernel
   */
  public void setKernel(AbstractSmileKernel value) {
    m_Kernel = value;
    reset();
  }

  /**
   * Returns the kernel.
   *
   * @return		the kernel
   */
  public AbstractSmileKernel getKernel() {
    return m_Kernel;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String kernelTipText() {
    return "The kernel to use.";
  }

  /**
   * The default shrinkage/regularization.
   *
   * @return		the default
   */
  protected double getDefaultLambda() {
    return 0.01;
  }

  /**
   * Sets the shrinkage/regularization.
   *
   * @param value	the lambda (>= 0)
   */
  public void setLambda(double value) {
    if (value >= 0.0) {
      m_Lambda = value;
      reset();
    }
  }

  /**
   * Returns the shrinkage/regularization.
   *
   * @return		the lambda (>= 0)
   */
  public double getLambda() {
    return m_Lambda;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String lambdaTipText() {
    return "The shrinkage/regularization factor (>= 0).";
  }

  /**
   * Returns default capabilities of the classifier.
   *
   * @return the capabilities of this classifier
   */
  @Override
  public Capabilities getCapabilities() {
    Capabilities result = super.getCapabilities();
    result.disableAll();

    // attributes
    result.enable(Capability.NOMINAL_ATTRIBUTES);
    result.enable(Capability.NUMERIC_ATTRIBUTES);
    result.enable(Capability.DATE_ATTRIBUTES);
    result.enable(Capability.MISSING_VALUES);

    // class
    result.enable(Capability.NUMERIC_CLASS);
    result.enable(Capability.DATE_CLASS);
    result.enable(Capability.MISSING_CLASS_VALUES);

    return result;
  }

  /**
   * Builds the classifier.
   *
   * @param data	the data to use for training
   * @return 		the generated model
   * @throws Exception	if training fails or data does not match capabilities
   */
  @Override
  protected Regression<double[]> buildClassifier(AttributeDataset data) throws Exception {
    return new GaussianProcessRegression(data.x(), data.y(), m_Kernel.getKernel(), m_Lambda);
  }

  /**
   * Executes the classifier from the commandline.
   *
   * @param args	the options
   */
  public static void main(String[] args) {
    runClassifier(new SmileGaussianProcessRegression(), args);
  }
}
