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

package weka.classifiers.smile.classification;

import smile.classification.Classifier;
import smile.classification.SVM;
import smile.data.AttributeDataset;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;

/**
 * SMILE SVM.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileSVM
  extends SmileSVMBase {

  private static final long serialVersionUID = -8861088582494627633L;

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  @Override
  public String globalInfo() {
    return "Support vector machines for classification. The basic support vector "
      + "machine is a binary linear classifier which chooses the hyperplane that "
      + "represents the largest separation, or margin, between the two classes. "
      + "If such a hyperplane exists, it is known as the maximum-margin hyperplane "
      + "and the linear classifier it defines is known as a maximum margin classifier.\n\n"
      + "See also:\n"
      + "https://haifengl.github.io/smile/api/java/smile/classification/SVM.html";
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

    // class
    result.enable(Capability.NOMINAL_CLASS);
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
  protected Classifier<double[]> buildClassifier(AttributeDataset data) throws Exception {
    SVM<double[]>	result;
    int			numClasses;

    numClasses = m_Header.getInstances().classAttribute().numValues();
    if (numClasses > 2)
      result = new SVM(m_Kernel.getKernel(), m_Capacity, numClasses, m_MultiClassStrategy);
    else
      result = new SVM(m_Kernel.getKernel(), m_Capacity);
    result.setTolerance(m_Tolerance);
    result.learn(data.x(), data.labels());
    result.trainPlattScaling(data.x(), data.labels());

    return result;
  }

  /**
   * Executes the classifier from the commandline.
   *
   * @param args	the options
   */
  public static void main(String[] args) {
    runClassifier(new SmileSVM(), args);
  }
}
