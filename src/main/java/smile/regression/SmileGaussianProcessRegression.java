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

package smile.regression;

import smile.data.AttributeDataset;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;

/**
 * SMILE GaussianProcessRegression.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileGaussianProcessRegression
  extends SmileGaussianProcessRegressionBase {

  private static final long serialVersionUID = -8861088582494627633L;

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
