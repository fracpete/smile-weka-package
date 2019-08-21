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
 * SmileRidgeRegression.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.classifiers.functions;

import smile.data.AttributeDataset;
import smile.regression.Regression;
import smile.regression.RidgeRegression;
import weka.classifiers.AbstractSmileRegressor;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;
import weka.core.WekaOptionUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * SMILE RidgeRegression.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileRidgeRegression
  extends AbstractSmileRegressor {

  private static final long serialVersionUID = -8861088582494627633L;

  public static final String LAMBDA = "lambda";

  /** the shrinkage/regularization parameter. */
  protected double m_Lambda = getDefaultLambda();

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  @Override
  public String globalInfo() {
    return "Ridge Regression. Coefficient estimates for multiple linear regression "
      + "models rely on the independence of the model terms. When terms are "
      + "correlated and the columns of the design matrix X have an approximate "
      + "linear dependence, the matrix X'X becomes close to singular. As a result, "
      + "the least-squares estimate becomes highly sensitive to random errors in "
      + "the observed response Y, producing a large variance. ";
  }

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration listOptions() {
    Vector result = new Vector();
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
    WekaOptionUtils.add(result, LAMBDA, getLambda());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default shrinkage/regularization.
   *
   * @return		the default
   */
  protected double getDefaultLambda() {
    return 1.0e-8;
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
    return new RidgeRegression(data.x(), data.y(), m_Lambda);
  }

  /**
   * Executes the classifier from the commandline.
   *
   * @param args	the options
   */
  public static void main(String[] args) {
    runClassifier(new SmileRidgeRegression(), args);
  }
}
