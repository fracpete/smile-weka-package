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
 * SmileSVR.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package smile.regression;

import smile.data.AttributeDataset;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;

/**
 * SMILE SVR.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileSVR
  extends SmileSVRBase {

  private static final long serialVersionUID = -8861088582494627633L;

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  @Override
  public String globalInfo() {
    return "Support vector regression. Like SVMs for classification, the model "
      + "produced by SVR depends only on a subset of the training data, because "
      + "the cost function ignores any training data close to the model "
      + "prediction (within a threshold ε).\n\n"
      + "See also:\n"
      + "https://haifengl.github.io/smile/api/java/smile/regression/SVR.html";
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
    return new SVR(data.x(), data.y(), m_Kernel.getKernel(), m_Epsilon, m_Capacity, m_Tolerance);
  }

  /**
   * Executes the classifier from the commandline.
   *
   * @param args	the options
   */
  public static void main(String[] args) {
    runClassifier(new SmileSVR(), args);
  }
}
